import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class Inicio extends JFrame implements ActionListener {

	private final JFrame window;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Inicio() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\luana\\Music\\M\u00FAsicas\\SEMESTRES - UFRN\\SEMESTRE 2021.1\\LP2\\TRABALHO 4\\EditorDeTexto-main\\ufrn-logo.png"));
		this.window = new JFrame();
		setTitle("Editor de Texto - LP2");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 100, 600, 350); // Frame (Janela)
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.EAST);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\luana\\OneDrive\\Imagens\\UFRN.jpg")); //Icone
		lblNewLabel.setLabelFor(this);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 17));
		panel.add(lblNewLabel); 
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Editor().setVisible(true);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Editor de Texto - LP2"); //Título
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setLabelFor(this);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1.setFont(new Font("Lucida Handwriting", Font.BOLD, 16));
		panel.add(lblNewLabel_1);
		btnNewButton.setBackground(UIManager.getColor("Viewport.foreground"));
		panel.add(btnNewButton);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		
	}

}
