package cse360project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextField;

public class MainWindow {

	private JFrame frame;
	private JTextField numOfPlayers;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
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
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().setLayout(null);
		JButton btnNewButton = new JButton("Start");
		frame.getContentPane().add(btnNewButton, BorderLayout.CENTER);
		
		JButton btnStart = new JButton("Start");
		btnStart.setBounds(154, 108, 116, 25);
		frame.getContentPane().add(btnStart);
		
		numOfPlayers = new JTextField();
		numOfPlayers.setBounds(154, 160, 116, 25);
		frame.getContentPane().add(numOfPlayers);
		numOfPlayers.setColumns(10);
	}
}
