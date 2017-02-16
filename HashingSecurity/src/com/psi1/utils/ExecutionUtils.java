package com.psi1.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

public class ExecutionUtils {
	private static Timer t;
	private static Integer hora = 0;
	private static Integer minuto = 0;
	private static Integer segundo = 0;

	public static void RunUtility(JLabel lblTimer) {
		System.out.println("I am functioning");

		t = new Timer(1000, clock(lblTimer));
		t.start();
	}
	public static void StopUtility() {
		System.out.println("I am stopping");

		
		t.stop();
	}

	public static ActionListener clock(JLabel lblTimer) {

		ActionListener action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				segundo++;
				if (segundo == 60) {
					minuto++;
					segundo = 0;
				}
				if (minuto == 60) {
					hora++;
					minuto = 0;
				}
				updateLabel();
			}

			private void updateLabel() {
				// TODO Auto-generated method stub
				String time = (hora <= 9 ? "0" : "") + hora + " : " + (minuto <= 9 ? "0" : "") + minuto + " : "
						+ (segundo <= 9 ? "0" : "") + segundo;
				lblTimer.setText(time);
			}
		};

		return action;

	}

}
