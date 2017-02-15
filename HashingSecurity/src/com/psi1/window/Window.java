package com.psi1.window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.UIManager;

import com.psi1.config.GlobalConfiguration;

import java.awt.Window.Type;

public class Window {

	private JFrame frmJ;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frmJ.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJ = new JFrame();
		frmJ.setForeground(Color.WHITE);
		frmJ.setFont(new Font("Arial", Font.PLAIN, 12));
		frmJ.setTitle("Security");
		frmJ.getContentPane().setForeground(Color.BLUE);
		frmJ.getContentPane().setLayout(null);
		
		JButton btnExamine = new JButton("Run");
		btnExamine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				btnExamine.setLabel("help");
				GlobalConfiguration n=new GlobalConfiguration();
				n.setConfigurationFile(textField.getText()); 
				n.setLogsDirectory(textField_1.getText()); 

				System.out.println(n.getConfigurationFile());
				System.out.println(n.getLogsDirectory());
			}
		});
		btnExamine.setBounds(323, 215, 97, 25);
		frmJ.getContentPane().add(btnExamine);
		
		textField = new JTextField();
		textField.setBounds(54, 65, 335, 22);
		frmJ.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(54, 131, 340, 22);
		frmJ.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblInsertPathOf = new JLabel("Insert path of configuration file");
		lblInsertPathOf.setBounds(54, 49, 257, 16);
		frmJ.getContentPane().add(lblInsertPathOf);
		
		JLabel lblInsertLogsDirectory = new JLabel("Insert logs directory");
		lblInsertLogsDirectory.setBounds(54, 112, 295, 16);
		frmJ.getContentPane().add(lblInsertLogsDirectory);
		frmJ.setBounds(100, 100, 450, 300);
		frmJ.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
