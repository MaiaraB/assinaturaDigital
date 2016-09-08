package bd;


import java.util.logging.Logger;
import java.util.logging.Level;
import org.postgresql.core.Version;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AcessoBanco {
	
	public ResultSet getDocumentosParaAssinar() {
		//to do
		return null;
	}

	public static void main(String[] args) {
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection("jdbc:postgresql://10.67.120.123:5432/spedDB", "sped", "sped");
			st = con.createStatement();
			rs = st.executeQuery("SELECT VERSION()");
			if (rs.next()) {
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			Logger lgr = Logger.getLogger(Version.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		} finally {
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
		}
		
	}

}
