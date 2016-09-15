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
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class JanelaPrincipal {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
        // Set System L&F
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (UnsupportedLookAndFeelException e) {
		   // handle exception
		}
		catch (ClassNotFoundException e) {
		   // handle exception
		}
		catch (InstantiationException e) {
		   // handle exception
		}
		catch (IllegalAccessException e) {
		   // handle exception
		}
		 
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
		frame.setBounds(100, 100, 680, 509);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			frame.getContentPane().setLayout(new MigLayout("", "0[grow]0", "0[grow]0"));
			
			JPanel panel = new JPanelWithBackground("imagens/banner_fundo2.gif", true);
			panel.setPreferredSize(new Dimension(panel.getWidth(), 80));
			frame.getContentPane().add(panel, "cell 0 0, grow, aligny top");
			panel.setLayout(new MigLayout("insets 0", "0[][grow]", "0[]0[]0[grow]0"));
			
			JPanel panel_1 = new JPanelWithBackground("imagens/sigadex2.png", false);
			panel_1.setOpaque(false);
			panel.add(panel_1, "gapleft 5, align left, height 25, width 94!");
			
			JPanel panel_2 = new JPanelWithBackground("imagens/banner2.png", false);
			panel_2.setOpaque(false);
			panel.add(panel_2, "wrap, align right, width 320:640:640, height 43!");
			
			JPanel panel_3 = new JPanel();
			panel_3.setForeground(Color.WHITE);
			panel_3.setBackground(Color.BLACK);
			panel.add(panel_3, "spanx 2, growx, alignx left, height 19!, wrap");
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
			
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setFont(new Font("Arial", Font.BOLD, 11));
			tabbedPane.setBackground(Color.WHITE);
			panel.add(tabbedPane, "spanx 2,alignx left, grow");
			
			JPanel docsAssinarPanel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) docsAssinarPanel.getLayout();
			flowLayout.setVgap(0);
			flowLayout.setHgap(0);
			tabbedPane.addTab("Documentos para assinar", docsAssinarPanel);
			JPanel docsAssinadosPanel = new JPanel();
			docsAssinadosPanel.setOpaque(false);
			tabbedPane.addTab("Documentos assinados", docsAssinadosPanel);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*JPanel panel_4 = new JPanel();
		frame.getContentPane().add(panel_4, "cell 0 1,grow");
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Arial", Font.BOLD, 11));
		tabbedPane.setBackground(Color.WHITE);
		panel_4.add(tabbedPane);
		
		JPanel docsAssinarPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) docsAssinarPanel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		tabbedPane.addTab("Documentos para assinar", docsAssinarPanel);
		JPanel docsAssinadosPanel = new JPanel();
		docsAssinadosPanel.setOpaque(false);
		tabbedPane.addTab("Documentos assinados", docsAssinadosPanel);*/
		
	}

}
