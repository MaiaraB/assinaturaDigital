package janelas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.LookAndFeel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.synth.SynthLookAndFeel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import bd.AcessoBD;
import net.miginfocom.swing.MigLayout;

public class JanelaPrincipal {

	private JFrame frame;
	//private final static String LOOKANDFEEL = UIManager.getSystemLookAndFeelClassName();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaPrincipal window = new JanelaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JanelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		initLookAndFeel();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 680, 509);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			frame.getContentPane().setLayout(new MigLayout("", "0[grow]0", "0[grow]0"));
			
			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BorderLayout(0, 0));
			
			JPanel panel = new JPanelWithBackground("imagens/banner_fundo2.gif", true);
			//panel.setPreferredSize(new Dimension(panel.getWidth(), 80));
			//mainPanel.add(panel, "cell 0 0, grow, aligny top");
			panel.setLayout(new MigLayout("insets 0", "0[grow]0[grow]0", "0[]0[]0[grow]0"));
			
			JPanel panel_1 = new JPanelWithBackground("imagens/sigadex2.png", false);
			panel_1.setOpaque(false);
			panel.add(panel_1, "gapleft 5, align left, height 25, width 94!");
			
			JPanel panel_2 = new JPanelWithBackground("imagens/banner2.png", false);
			panel_2.setOpaque(false);
			panel.add(panel_2, "wrap, align right, width 320:640:640, height 43!");

			panel.add(getUsuarioLogadoPanel(), "spanx 2, growx, alignx left, height 19!, wrap");
			
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setFont(new Font("Arial", Font.BOLD, 11));
			//tabbedPane.setBackground(new Color(224, 224, 208));
			panel.add(tabbedPane, "spanx 2,alignx left, aligny top, grow");
			
			JPanel docsAssinarPanel = new JPanel();
			docsAssinarPanel.setBackground(new Color(242, 242, 238));
			tabbedPane.addTab("Documentos para assinar", docsAssinarPanel);
			
			JTable table;
			JScrollPane scrollPane;
			try {
				docsAssinarPanel.setLayout(new MigLayout("", "10[]0[grow, fill]10", "10[]0[]0[]10"));
				
				//scrollPane = new JScrollPane();
				//scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
				//docsAssinarPanel.add(scrollPane, "cell 0 0,grow");
				
				table = buildTableModel(); 
				//new JTable(buildTableModel());
				//scrollPane.setViewportView(table);
				docsAssinarPanel.add(table.getTableHeader(), "cell 0 0, span 2, grow");
				docsAssinarPanel.add(table, "cell 0 1, span 2, grow");
				
				JButton botaoAssinar = new JButton("Assinar");
				docsAssinarPanel.add(botaoAssinar, "cell 0 2, width 100!, gapleft 10, gaptop 10");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			JPanel docsAssinadosPanel = new JPanel();
			docsAssinadosPanel.setOpaque(false);
			tabbedPane.addTab("Documentos assinados", docsAssinadosPanel);
			
			JScrollPane mainScroll = new JScrollPane(panel);
			mainScroll.setBackground(new Color(242, 242, 238));
			mainPanel.add(mainScroll);
			frame.getContentPane().add(mainPanel, "cell 0 0, grow, aligny top, alignx left");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public JTable buildTableModel() throws SQLException{
		
		AcessoBD sessaoBanco = new AcessoBD();
		sessaoBanco.iniciarConexaoBD();
		
		ResultSet rs = sessaoBanco.getDocumentosParaAssinar();
		
		sessaoBanco.fecharConexaoBD();
	    
	    JTable table = new JTable(new DocumentosTableModel(rs));
	    JTableHeader header = table.getTableHeader();
	    adjustColumnSizes(table, 9, 0);
	    
	    //Centralizando os titulos e valores na tabela
	    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
	    table.setDefaultRenderer(String.class, centerRenderer);
	    DefaultTableCellRenderer centerRendererTitle = (DefaultTableCellRenderer) header.getDefaultRenderer();
	    centerRendererTitle.setHorizontalAlignment(JLabel.CENTER);
	    
	    //Setando as cores e fonte
	   // header.setBackground(new Color(31, 59, 8)); //1f3b08
	   // header.setForeground(Color.WHITE);
	    header.setFont(new Font("Arial", Font.BOLD, 12));
	   // table.setBackground(Color.WHITE);//#bdbdbd #e0e0e0 new Color(208, 208, 208)
	   // table.setForeground(Color.BLACK);
	    table.setFont(new Font("Arial", Font.PLAIN, 12));
	    
	    return table;

	}
	
	private JPanel getUsuarioLogadoPanel() {
		JPanel panel_3 = new JPanel();
		panel_3.setForeground(Color.WHITE);
		panel_3.setBackground(Color.BLACK);
		panel_3.setName("painelUsuario");
		panel_3.setOpaque(true);
		
		panel_3.setLayout(new MigLayout("insets 0", "5[]0[]20[]0[]20[]0[]", "[]"));
		
		JLabel pessoaLabel = new JLabel("Pessoa: ");
		pessoaLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		pessoaLabel.setFont(new Font("Verdana", Font.PLAIN, 11));
		pessoaLabel.setForeground(Color.WHITE);
		panel_3.add(pessoaLabel, "cell 0 0,alignx left,aligny top");
		
		JLabel pessoaLabel_ = new JLabel("Fulano".toUpperCase());
		pessoaLabel_.setVerticalAlignment(SwingConstants.BOTTOM);
		pessoaLabel_.setFont(new Font("Verdana", Font.BOLD, 10));
		pessoaLabel_.setForeground(Color.WHITE);
		panel_3.add(pessoaLabel_, "cell 1 0,alignx left,aligny bottom");
		
		JLabel contaLabel = new JLabel("Conta: ");
		contaLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		contaLabel.setFont(new Font("Verdana", Font.PLAIN, 11));
		contaLabel.setForeground(Color.WHITE);
		panel_3.add(contaLabel, "cell 2 0,alignx left,aligny bottom");
		
		JLabel contaLabel_ = new JLabel("Nome da Conta".toUpperCase());
		contaLabel_.setVerticalAlignment(SwingConstants.BOTTOM);
		contaLabel_.setFont(new Font("Verdana", Font.BOLD, 10));
		contaLabel_.setForeground(Color.WHITE);
		panel_3.add(contaLabel_, "cell 3 0,alignx left,aligny bottom");
		
		JLabel perfilLabel = new JLabel("Perfil: ");
		perfilLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		perfilLabel.setFont(new Font("Verdana", Font.PLAIN, 11));
		perfilLabel.setForeground(Color.WHITE);
		panel_3.add(perfilLabel, "cell 4 0,alignx left,aligny bottom");
		
		JLabel perfilLabel_ = new JLabel("Tipo de Perfil");
		perfilLabel_.setVerticalAlignment(SwingConstants.BOTTOM);
		perfilLabel_.setFont(new Font("Verdana", Font.BOLD, 10));
		perfilLabel_.setForeground(Color.WHITE);
		panel_3.add(perfilLabel_, "cell 5 0,alignx left,aligny bottom");
		
		return panel_3;
	}
	
	public void adjustColumnSizes(JTable table, int nCol, int margin) {
        DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.getColumnModel();
        int offset = 10;
        for (int column=0; column<nCol; column++) {
        	TableColumn col = colModel.getColumn(column);
            int width;

            TableCellRenderer renderer = col.getHeaderRenderer();
            
            if (renderer == null) {
                renderer = table.getTableHeader().getDefaultRenderer();
            }
            
            Component comp = renderer.getTableCellRendererComponent(table, col.getHeaderValue(), false, false, 0, 0);
            width = comp.getPreferredSize().width;

            for (int r = 0; r < table.getRowCount(); r++) {
                renderer = table.getCellRenderer(r, column);
                comp = renderer.getTableCellRendererComponent(table, table.getValueAt(r, column), false, false, r, column);
                int currentWidth = comp.getPreferredSize().width;
                width = Math.max(width, currentWidth);
            }

            width += 2 * margin + offset;
            
            col.setMinWidth(width);
            col.setPreferredWidth(width);
            col.setWidth(width);
        }
    }

	private void initLookAndFeel() {
		
		//SynthLookAndFeel laf = new SynthLookAndFeel();
		//UIManager.put("nimbusBase", new Color(31,59,8));//247,248,250
		//UIManager.put("control", Color.RED);
		UIManager.put("nimbusFocus", new Color(204,204,153));//cccc99
		UIManager.put("TableHeader.background", new Color(31,59,8));
		UIManager.put("Table.background", Color.WHITE);
		UIManager.put("Table.alternateRowColor", Color.WHITE);
		UIManager.put("Table.textForeground", Color.BLACK);
		UIManager.put("Table.showGrid", true);
		UIManager.put("Table.gridColor", new Color(208,208,208));
		UIManager.put("Table[Enabled+Selected].textBackground", new Color(204,204,153));
		UIManager.put("CheckBox.background", new Color(214,217,223));
		UIManager.put("CheckBox[Disabled].textForeground", new Color(214,217,223));
		UIManager.put("TabbedPane.background", Color.RED);
		UIManager.put("TabbedPane.shadow", Color.RED);
		
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
			/*InputStream in = JanelaPrincipal.class.getResourceAsStream("/xmlfiles/laf.xml");
			laf.load(in, JanelaPrincipal.class);
			UIManager.setLookAndFeel(laf);*/
			//UIManager.setLookAndFeel(LOOKANDFEEL);
		} 
		catch (UnsupportedLookAndFeelException e) {
			 System.err.println("Can't use the specified look and feel ("
                     + "" + ") on this platform.");
			 System.err.println("Using the default look and feel.");
		}
		/*catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		catch (ClassNotFoundException e) {
			System.err.println("Couldn't find class for specified look and feel:"
                    + "");
			System.err.println("Did you include the L&F library in the class path?");
			System.err.println("Using the default look and feel.");
		}
		
		catch (InstantiationException e) {
		   // handle exception
		}
		catch (IllegalAccessException e) {
		   // handle exception
		}
	}
}
