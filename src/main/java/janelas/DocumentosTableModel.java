package janelas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import util.TipoDocumento;
import util.Util;

public class DocumentosTableModel extends AbstractTableModel{
	
	private Vector<String> columnNames;
	private Vector<Vector<Object>> data;
	
	public DocumentosTableModel (ResultSet rs) throws SQLException{
		// names of columns
	    this.columnNames = new Vector<String>();
	    columnNames.add("");
	    columnNames.add("!");
	    columnNames.add("Tipo");
	    columnNames.add("Nº Doc");
	    columnNames.add("OM Origem");
	    columnNames.add("Assunto");
	    columnNames.add("Anexo");
	    columnNames.add("Data do Documento");
	    columnNames.add("Destinatário");
	    columnNames.add("Data Ação");
	    

	    // data of the table
	    this.data = new Vector<Vector<Object>>();
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy   HH:mm");
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        vector.add(new Boolean(false));
	        vector.add(Util.getPrioridadeString(rs.getInt("cd_prioridade")));
	        vector.add(TipoDocumento.getNomeDocumento(rs.getInt("id_tipo")));
	        vector.add(rs.getString("cd_documento"));
	        vector.add(rs.getString("ds_om_origem"));
	        vector.add(rs.getString("ds_assunto"));
	        vector.add("-");
	        vector.add(sdf.format(rs.getTimestamp("dt_elaboracao")));
	        vector.add("-");
	        vector.add("-");
	        
	        this.data.add(vector);
	    }
	    
	}
	
	public int getRowCount() {
		return data.size();
	}

	public int getColumnCount() {
		return columnNames.size();
	}

	public String getColumnName(int col) {
		return columnNames.elementAt(col);
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data.elementAt(rowIndex).elementAt(columnIndex);
	}
	
	public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 1) {
            return true;
        } else {
            return false;
        }
    }
	
    public void setValueAt(Object value, int row, int col) {
        data.elementAt(row).set(col, value);
        fireTableCellUpdated(row, col);
    }
    
    /*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     */
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}
