package com.psi1.window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;

public class Popup {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void popUp() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Popup window = new Popup();
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
	public Popup() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, "name_855899136829055");
		panel.setLayout(null);
		
		JButton btnSaveAndClose = new JButton("Save and close");
		btnSaveAndClose.setBounds(146, 198, 129, 25);
		panel.add(btnSaveAndClose);
		
		JLabel lblIfYouSave = new JLabel("If you save and close there will be a security vulnerability");
		lblIfYouSave.setBounds(60, 34, 326, 16);
		panel.add(lblIfYouSave);
		
		JTextPane txtpnPleaseDoThis = new JTextPane();
		txtpnPleaseDoThis.setText("Please, do this on your own responsibility. If you do not want to generate the report, just close this window.");
		txtpnPleaseDoThis.setBounds(89, 63, 271, 60);
		panel.add(txtpnPleaseDoThis);
	}
}
