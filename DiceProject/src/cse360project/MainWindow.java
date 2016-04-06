package cse360project;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow {

	private JFrame frame;
	private JTextField textField;

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
		
		frame.getContentPane().setLayout(null); // Sets layout to null for free style layout. 
		JButton btnNewButton = new JButton("Start");
		frame.getContentPane().add(btnNewButton, BorderLayout.CENTER);
		
		JPanel startMenu = new JPanel();
		startMenu.setLayout(null);
		startMenu.setBounds(12, 11, 408, 229);
		frame.getContentPane().add(startMenu);
		
		textField = new JTextField();
		textField.setBounds(122, 138, 154, 22);
		startMenu.add(textField);
		textField.setColumns(10);
		
		JButton btnStart = new JButton("Start");
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		
		});
		btnStart.setBounds(146, 64, 97, 25);
		startMenu.add(btnStart);
		
		JTextPane txtpnEnterNumberOf = new JTextPane();
		txtpnEnterNumberOf.setBackground(UIManager.getColor("Button.background"));
		txtpnEnterNumberOf.setText("Enter Number of PLayer");
		txtpnEnterNumberOf.setBounds(122, 103, 154, 22);
		startMenu.add(txtpnEnterNumberOf);
	}
}
