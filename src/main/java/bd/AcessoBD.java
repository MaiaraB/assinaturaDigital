package bd;


import java.util.logging.Logger;
import java.util.logging.Level;
import org.postgresql.core.Version;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AcessoBD {
	
	private Connection con = null;
	//private Statement st = null;
	
	public void iniciarConexaoBD() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		try {
			this.con = DriverManager.getConnection("jdbc:postgresql://10.67.123.62:5432/spedDB", "sped", "sped");//120.123
			//this.st = con.createStatement();
			//rs = st.executeQuery("SELECT VERSION()");
		} catch (SQLException e) {
			Logger lgr = Logger.getLogger(Version.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		} /*finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				Logger lgr = Logger.getLogger(Version.class.getName());
				lgr.log(Level.WARNING, e.getMessage(), e);
			}
		}*/
	}
	
	public void fecharConexaoBD() {
		try {
			con.close();
		} catch (SQLException e) {
			Logger lgr = Logger.getLogger(Version.class.getName());
			lgr.log(Level.WARNING, e.getMessage(), e);
		}
	}
	
	public ResultSet getDocumentosParaAssinar (){
		
		try {
			StringBuilder sqlInstrucao = new StringBuilder();
			
			sqlInstrucao.append("SELECT distinct DOC.id_documento, DOC.id_usuario,  DOC.cd_documento, DOC.id_protocolo, DOC.ds_om_origem, DOC.ds_orgao_destino,  \n");
			sqlInstrucao.append("	    DOC.ds_assunto, DOC.cd_estado, DOC.dt_elaboracao, DOC.cd_prioridade, DOC.id_tipo, DOC.nm_sigla as sg_secao, \n");
			sqlInstrucao.append(" 	     ' (' ||posto_graduacao(ED.cd_patente,1)|| ' ' || ED.nm_guerra || ') ' ||ED.nm_usuario as nm_usuario,  ED.dt_entrada, ED.regIsDespacho,  \n");
			sqlInstrucao.append("		cd_patente, nm_guerra,  DOC.nup_protocolo, \n");
			sqlInstrucao.append("	    ED.id_providencia, ED.dt_prazo, ED.regIsDespacho, DOC.in_lido, DOC.encaminhou, DOC.despachou \n");
			
			StringBuilder sqlFrom = new StringBuilder();
			sqlFrom.append(" FROM \n\n");
			sqlFrom.append("( ");
			sqlFrom.append(getSQLDadosBasicosDocumentos());
			sqlFrom.append("\n ) AS DOC \n\n\n\n");	
			sqlFrom.append(" LEFT JOIN \n\n\n\n");
			sqlFrom.append(" ( ");
			
			sqlFrom.append(getSQLDadosEncaminhamentosDespachosDocumentos());
			sqlFrom.append(" ) AS ED \n\n\n\n");
			
			sqlFrom.append(" ON ED.id_documento = DOC.id_documento  \n");
			
			sqlFrom.append(" ORDER BY dt_elaboracao DESC");
			sqlFrom.append(" LIMIT ? OFFSET ?");
			
			sqlInstrucao.append(sqlFrom);
			
			ResultSet rs = getResultSet(this.con, 6, 1, 0, sqlInstrucao, 100);
			return rs;
		
		} catch (SQLException e) {
			Logger lgr = Logger.getLogger(Version.class.getName());
			lgr.log(Level.WARNING, e.getMessage(), e);
		}
		
		return null;
	}
	
	private String getSQLDadosBasicosDocumentos() throws SQLException {

		StringBuilder sql = getStringBuilderSQLDocumentos();

		sql.append(" WHERE (UD.id_caixa = ? and UD.id_subcaixa = ?) \n ");
		sql.append("		AND U.id_usuario = ?\n");

		return sql.toString();
	}
	
	private StringBuilder getStringBuilderSQLDocumentos() {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT U.id_usuario, D.id_documento, U.nm_usuario,  D.cd_documento, P.id_protocolo, P.dt_entrada, P.nup_protocolo, D.ds_om_origem,D.ds_orgao_destino, \n ");
		sql.append("	    D.ds_assunto, D.cd_estado, D.dt_elaboracao, D.cd_prioridade, D.id_tipo, S.nm_sigla, UD.in_lido, \n ");
		sql.append(" 		CASE WHEN ENC.id_encaminhamento is null THEN 'false' ELSE 'true' END AS encaminhou, \n ");
		sql.append(" 		CASE WHEN PROV.id_providencia is null THEN 'false' ELSE 'true' END AS despachou \n ");
		sql.append(" FROM \n ");
		sql.append("	DOCUMENTO D LEFT JOIN DOCUMENTO_ELABORADO DE \n ");
		sql.append("	ON D.id_documento = DE.id_documento\n ");
		sql.append("	LEFT JOIN SECAO S \n ");
		sql.append("	ON DE.id_secao = S.id_secao \n ");
		sql.append("	LEFT JOIN USUARIO_DOCUMENTO UD \n");
		sql.append(" 	ON D.id_documento = UD.id_documento \n ");
		sql.append("	LEFT JOIN USUARIO U  \n ");
		sql.append("	ON UD.id_usuario = U.id_usuario \n ");
		sql.append("	LEFT JOIN PROTOCOLO P  \n ");
		sql.append("	ON D.id_documento = P.id_documento \n ");
		sql.append("	LEFT JOIN ENCAMINHAMENTO ENC  \n ");
		sql.append("	ON ENC.id_autor = U.id_usuario and ENC.id_documento = D.id_documento   \n ");
		sql.append("	LEFT JOIN PROVIDENCIA_DESPACHO PROV  \n ");
		sql.append("	ON PROV.id_autor = U.id_usuario and PROV.id_documento = D.id_documento \n ");

		return sql;
	}

	private String getSQLDadosEncaminhamentosDespachosDocumentos() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT U.id_usuario, U.nm_usuario, P.cd_patente, P.nm_guerra,  E.dt_entrada, E.id_documento,  \n ");
		sql.append("'false' as regIsDespacho, null as id_providencia, null as dt_prazo  \n ");
		sql.append("FROM USUARIO U INNER JOIN ENCAMINHAMENTO E ON E.id_autor = U.id_usuario  \n ");
		sql.append("INNER JOIN PESSOA as P ON P.id_pessoa = E.id_pessoa_autor  \n ");
		sql.append("INNER JOIN USUARIO_ENCAMINHAMENTO UE ON E.id_encaminhamento = UE.id_encaminhamento  \n ");
		sql.append("AND UE.id_usuario = ? \n ");
		sql.append("UNION  \n ");
		sql.append("SELECT U.id_usuario, U.nm_usuario, P.cd_patente, P.nm_guerra, PD.dt_entrada, PD.id_documento,  \n ");
		sql.append("'true' as regIsDespacho, id_providencia, dt_prazo   \n ");
		sql.append("FROM USUARIO U INNER JOIN PROVIDENCIA_DESPACHO PD ON PD.id_autor = U.id_usuario  \n ");
		sql.append("INNER JOIN PESSOA as P ON P.id_pessoa = PD.id_pessoa_autor  \n ");
		sql.append("AND PD.id_destinatario = ? \n ");
		// sql.append("ORDER BY dt_entrada desc ");
		
		return sql.toString();
	}
	
	private ResultSet getResultSet(Connection con, Integer idUsuario, Integer idCaixa, Integer idSubcaixa, StringBuilder sql, Integer paginacao) throws SQLException {

		PreparedStatement ps = con.prepareStatement(sql.toString());
		
		ps.setInt(1, idCaixa);
		ps.setInt(2, idSubcaixa);
		ps.setInt(3, idUsuario);
		ps.setInt(4, idUsuario);
		ps.setInt(5, idUsuario);
		ps.setInt(6, 100);
		ps.setInt(7, 0);

		ResultSet rs = ps.executeQuery();
		return rs;
	}

}