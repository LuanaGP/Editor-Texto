import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.TextArea;
import javax.swing.JMenu;
import java.awt.Toolkit;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Scrollbar;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.JProgressBar;
import javax.swing.JToolBar;
import javax.swing.tree.DefaultTreeModel;

import junit.framework.Test;

import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.HierarchyListener;
import java.awt.event.HierarchyEvent;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Editor extends JFrame implements ActionListener {
	
	private final JFrame window;
	public TextArea textArea;
	private JFileChooser jFileChooser;
	private JTextField textField;
	private JTextArea area;
	private JToolBar toolBar;
	String caminho;
	private JTree tree;



    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editor frame = new Editor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});

	}
	/**
	 * Create the frame.
	 * @throws BadLocationException 
	 */

	public Editor() throws BadLocationException {
		
		this.window = new JFrame();
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\luana\\Music\\M\u00FAsicas\\SEMESTRES - UFRN\\SEMESTRE 2021.1\\LP2\\TRABALHO 4\\EditorDeTexto-main\\ufrn-logo.png"));
		setTitle("Editor de Texto - LP2");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 100, 700, 500); // Frame (Janela)
		
		JScrollPane scrollPane_3 = new JScrollPane();
		getContentPane().add(scrollPane_3, BorderLayout.SOUTH); //Barra de Rolagem Horizontal Sul 
		
		toolBar  = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.SOUTH); //Usado para ser a base do JTextField (caminho)
		
		textField = new JTextField();
		toolBar.add(textField);
		textField.setColumns(30); //Mostrar o caminho do arquivo	

		
		area = new JTextArea(); 
		toolBar.add(area);
		area.setColumns(30);

		tree  = new JTree();
		tree.setToolTipText("");
		tree.setModel(new FileSystemModel(new File("C:\\Users\\luana\\Music\\SEMESTRES - UFRN")));
		tree.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				caminho=tree.getSelectionPath().toString().replaceAll("[\\[\\]]", "").replace(", ", "\\");
				
			}
		}); 
		
		getContentPane().add(tree, BorderLayout.WEST);;

		textArea   = new TextArea();
		getContentPane().add(textArea, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("Abrir Arquivo da \u00C1rvore");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File selecionar=new File(caminho);
					if(selecionar.exists()) {
						if(Desktop.isDesktopSupported()) {
							Desktop.getDesktop().open(selecionar);
						}else {
							JOptionPane.showMessageDialog(btnNewButton, this, "Esse arquivo não é suportado", getDefaultCloseOperation());
						}
						}else {
						JOptionPane.showMessageDialog(btnNewButton, this, "Esse arquivo não existe", getDefaultCloseOperation());
					}
				}catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		getContentPane().add(btnNewButton, BorderLayout.NORTH);

		JMenuBar menuBar = new JMenuBar(); //Barra de Menu
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Arquivo"); // O menu arquivo
		mnNewMenu.setForeground(Color.BLACK);
		menuBar.add(mnNewMenu);
		
		JMenuItem abrir = new JMenuItem("Abrir"); //Opção do menu de abrir
		mnNewMenu.add(abrir);
		
		JMenuItem novo = new JMenuItem("Novo"); //Opção do menu de uma nova aba (JFrame)
		mnNewMenu.add(novo);
		
		JMenuItem salvarcomo = new JMenuItem("Salvar como"); //Opção do menu de salvar como
		mnNewMenu.add(salvarcomo);
		 
		JMenuItem salvar = new JMenuItem("Salvar"); //Opção do menu de salvar
		mnNewMenu.add(salvar);
		
		JMenuItem Fechar = new JMenuItem("Fechar"); //Opção do menu de fechar
		mnNewMenu.add(Fechar);
		
		JMenuItem Limpar = new JMenuItem("Limpar"); // Opção de Limpar o campo e iniciar novamente
		mnNewMenu.add(Limpar);

		
		novo.addActionListener(this);
		abrir.addActionListener(this);
		salvar.addActionListener(this);
		salvarcomo.addActionListener(this);
		Limpar.addActionListener(this);
		Fechar.addActionListener(this);
		
		
		
		mnNewMenu.add(novo);
		mnNewMenu.add(abrir);
		mnNewMenu.add(salvar);
		mnNewMenu.add(salvarcomo);
		mnNewMenu.add(Limpar);
		mnNewMenu.add(Fechar);
		
		
		area.addCaretListener(new CaretListener() {	//Linha Corrente (Linha e Coluna)
		
		@Override
		public void caretUpdate(CaretEvent e) { //Linha Corrente (Linha e Coluna)
				
			
			area = (JTextArea)e.getSource();
			
			int linenum = 1;
            int columnnum = 1;
            
            try {
                
                int caretpos = area.getCaretPosition();
                linenum = area.getLineOfOffset(caretpos);
                int rowNum = (caretpos == 0) ? 1 : 0;
                for (int offset = caretpos; offset > 0;) {
                    offset = Utilities.getRowStart(area, offset) - 1;
                    rowNum++;
                }
                System.out.println("Linha: " + rowNum); 

                columnnum = caretpos - area.getLineStartOffset(linenum);
                int offset = Utilities.getRowStart(area, caretpos);
                int colNum = caretpos - offset + 1;
                System.out.println("Coluna: " + colNum);
                
                linenum += 1;
            }
            catch(Exception ex) { }

      
            updateStatus(linenum, columnnum);

            updateStatus(1,1);
          
        }

		private void updateStatus(int linenum, int columnnum) {
			area.setText("Line: " + linenum + " Column: " + columnnum);
			
			
		}
    });
		
}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();

		
	switch (action){
	
	
	case "Fechar" -> { //Fechar a aba/programa
	                System.exit(DISPOSE_ON_CLOSE);
	            }
	
	case "Salvar como" -> {
		 jFileChooser = new JFileChooser("file: ");

	        int r = jFileChooser.showSaveDialog(null);

	        if (r == JFileChooser.APPROVE_OPTION) {
	            File file = new File(jFileChooser.getSelectedFile().getAbsolutePath() + ".txt");

	            try {
	                FileWriter fileWriter = new FileWriter(file, false);
	                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

	                bufferedWriter.write(textArea.getText());

	                bufferedWriter.flush();
	                bufferedWriter.close();
	            } catch (Exception evt) {
	                JOptionPane.showMessageDialog(window, evt.getMessage());
	            }
	        } else {
	            JOptionPane.showMessageDialog(window, "Cancelado");
	        }
		
	}
	case "Salvar" -> { //Salvar

		  jFileChooser = new JFileChooser("file: ");

	        int r = jFileChooser.showSaveDialog(null);

	        if (r == JFileChooser.APPROVE_OPTION) {
	            File file = new File(jFileChooser.getSelectedFile().getAbsolutePath() + ".txt");

	            try {
	                FileWriter fileWriter = new FileWriter(file, false);
	                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

	                bufferedWriter.write(textArea.getText());

	                bufferedWriter.flush();
	                bufferedWriter.close();
	            } catch (Exception evt) {
	                JOptionPane.showMessageDialog(window, evt.getMessage());
	            }
	        } else {
	            JOptionPane.showMessageDialog(window, "Cancelado");
	        }
	    }

	   case "Abrir" -> { //Abrir arquivos 
		   
           JFileChooser fileChooser = new JFileChooser("file: ");
           int r = fileChooser.showOpenDialog(this);

           if (r == JFileChooser.APPROVE_OPTION) {
        	   
               File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
               
              
               try {
                   String s1;
                   StringBuilder sl;

                   FileReader fileReader = new FileReader(file);

                   BufferedReader bufferedReader = new BufferedReader(fileReader);

                   sl = new StringBuilder(bufferedReader.readLine());

                   while ((s1 = bufferedReader.readLine()) != null) {
                       sl.append("\n").append(s1);
                   }

                   textArea.setText(sl.toString());
                   textField.setText(file.toString());

                  
                   
               } catch (Exception evt) {
                   JOptionPane.showMessageDialog(window, evt.getMessage());
               }
           } else JOptionPane.showMessageDialog(window, "Cancelado");
       }
	   
	   case "Novo" -> { //Manipular novo arquivo - Abrir nova aba 
		
		Editor frame = null;
		try {
			frame = new Editor();
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame.setVisible(true);				
		
	}
		case "Limpar" ->  textArea.setText("");
		}    
	}
	
}
	
	

