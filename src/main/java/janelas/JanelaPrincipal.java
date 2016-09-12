package janelas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import javax.swing.JLayeredPane;

public class JanelaPrincipal {

	private JFrame frame;

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
		frame = new JFrame();
		frame.setBounds(100, 100, 673, 431);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		try {
			
			JLayeredPane layeredPane = new JLayeredPane();
			frame.getContentPane().add(layeredPane, BorderLayout.SOUTH);
			JPanel panel = new JPanelWithBackground("imagens/banner_fundo.gif", true);
			panel.setPreferredSize(new Dimension(panel.getWidth(), 80));
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			frame.getContentPane().add(panel, BorderLayout.PAGE_START);
			
			JPanel panel_1 = new JPanelWithBackground("imagens/sigadex2.png", false);
			FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
			flowLayout_1.setVgap(15);
			flowLayout_1.setHgap(45);
			panel.add(panel_1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel_1.add(tabbedPane);
		
		JPanel docsAssinar = new JPanel();
		tabbedPane.addTab("Documentos para assinar", docsAssinar);
		JPanel docsAssinados = new JPanel();
		tabbedPane.addTab("Documentos assinados", docsAssinados);
		
	}

}
