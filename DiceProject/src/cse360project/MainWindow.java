package cse360project;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.text.MaskFormatter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.Scanner;

public class MainWindow {

	private JFrame frame;
	private JPanel startMenu;
	private JPanel gameFrame;
	private JFormattedTextField numOfPlayer;

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
		startMenu = new JPanel();
		startMenu.setLayout(null);
		startMenu.setBounds(12, 11, 408, 229);
		
		gameFrame = new JPanel();
		gameFrame.setVisible(false);
		gameFrame.setLayout(null);
		gameFrame.setBounds(12, 11, 408, 229);
		gameFrame.setBackground(Color.GREEN);
		
		initialize();
	}

	MaskFormatter createFormatter(String s) {
	    MaskFormatter formatter = null;
	    try {
	        formatter = new MaskFormatter(s);
	    } catch (java.text.ParseException exc) {
	        System.err.println("formatter is bad: " + exc.getMessage());
	        System.exit(-1);
	    }
	    return formatter;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Liar's Dice V1.0");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(startMenu);
		frame.getContentPane().add(gameFrame);
		
		frame.getContentPane().setLayout(null); // Sets layout to null for free style layout. 
		JButton btnNewButton = new JButton("Start");
		frame.getContentPane().add(btnNewButton, BorderLayout.CENTER);
		
		
		numOfPlayer = new JFormattedTextField(createFormatter("##"));
		numOfPlayer.setBounds(122, 138, 154, 22);
		startMenu.add(numOfPlayer);
		numOfPlayer.setColumns(10);
		
		JButton btnStart = new JButton("Start");
		
		
		btnStart.setBounds(146, 64, 97, 25);
		startMenu.add(btnStart);
		
		JTextPane txtpnEnterNumberOf = new JTextPane();
		txtpnEnterNumberOf.setBackground(UIManager.getColor("Button.background"));
		txtpnEnterNumberOf.setText("Enter Number of PLayer");
		txtpnEnterNumberOf.setBounds(122, 103, 154, 22);
		startMenu.add(txtpnEnterNumberOf);
		
		
		
		// code for start button click below 
		btnStart.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("resource")
			@Override
			public void mouseClicked(MouseEvent e) {
				String text = numOfPlayer.getText();
				int players = 0;
				
				System.out.println("Text = " + text);
				
				if (text != "")
					players = Integer.parseInt(text);
				
				System.out.println(players);
				
				startMenu.setVisible(false);
				gameFrame.setVisible(true);
			}
		});
	}
}
