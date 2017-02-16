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
import com.psi1.utils.ExecutionUtils;

import javax.swing.JPanel;

import java.awt.CardLayout;

public class Window {

	private JFrame frmJ;
	private JTextField textField;
	private JTextField textField_1;
	JLabel lblTimeOfExecution;
	private GlobalConfiguration config;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
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
		frmJ.getContentPane().setLayout(new CardLayout(0, 0));

		JPanel panel1 = new JPanel();
		frmJ.getContentPane().add(panel1, "name_506056528830070");
		panel1.setLayout(null);

		JPanel panel2 = new JPanel();
		frmJ.getContentPane().add(panel2, "name_506056538446282");
		panel2.setLayout(null);

		textField = new JTextField();
		textField.setBounds(67, 59, 276, 22);
		panel1.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(65, 123, 278, 22);
		panel1.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblInsertPathOf = new JLabel("Insert path of configuration file");
		lblInsertPathOf.setBounds(67, 25, 176, 16);
		panel1.add(lblInsertPathOf);

		JLabel lblTimer = new JLabel("00 : 00 : 00");

		JButton btnExamine = new JButton("Next");
		btnExamine.setBounds(251, 173, 92, 22);
		panel1.add(btnExamine);
		btnExamine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				config = new GlobalConfiguration("", "");
				config.setConfigurationFile(textField.getText());
				config.setLogsDirectory(textField_1.getText());
				panel2.setVisible(true);
				panel1.setVisible(false);

			}
		});

		JLabel lblInsertLogsDirectory = new JLabel("Insert logs directory");
		lblInsertLogsDirectory.setBounds(67, 94, 114, 16);
		panel1.add(lblInsertLogsDirectory);

		JButton btnHelp = new JButton("Help");
		btnHelp.setBounds(71, 172, 97, 25);
		panel1.add(btnHelp);

		JButton btnNewButton = new JButton("Run ");
		JButton btnNewButton_1 = new JButton("Stop");
		btnNewButton_1.setBounds(231, 174, 97, 25);
		panel2.add(btnNewButton_1);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ExecutionUtils.RunUtility(lblTimer);
				btnNewButton.setEnabled(false);
				btnNewButton_1.setEnabled(true);

			}
		});
		btnNewButton.setBounds(98, 174, 97, 25);
		panel2.add(btnNewButton);

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ExecutionUtils.StopUtility();
				btnNewButton.setEnabled(true);
				btnNewButton_1.setEnabled(false);

			}
		});

		lblTimeOfExecution = new JLabel("Time of execution");
		lblTimeOfExecution.setFont(new Font("Arial", Font.BOLD, 18));
		lblTimeOfExecution.setBounds(127, 26, 176, 16);
		panel2.add(lblTimeOfExecution);

		lblTimer.setFont(new Font("Arial", Font.PLAIN, 40));
		lblTimer.setBounds(98, 79, 264, 68);
		panel2.add(lblTimer);

		JPanel helpPanel = new JPanel();
		frmJ.getContentPane().add(helpPanel, "name_601527980303771");
		helpPanel.setLayout(null);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(152, 177, 97, 25);
		helpPanel.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				panel2.setVisible(false);
				panel1.setVisible(true);
				helpPanel.setVisible(false);

			}
		});

		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				panel2.setVisible(false);
				panel1.setVisible(false);
				helpPanel.setVisible(true);

			}
		});

		JLabel lblNewLabel = new JLabel("The Configuration File should be a .properties file.");
		lblNewLabel.setBounds(66, 55, 287, 16);
		helpPanel.add(lblNewLabel);

		JLabel lblTheLogFiles = new JLabel("The Log files are stored in the logs directory file.");
		lblTheLogFiles.setBounds(66, 103, 287, 16);
		helpPanel.add(lblTheLogFiles);
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
