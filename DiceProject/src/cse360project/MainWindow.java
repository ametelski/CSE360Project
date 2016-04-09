package cse360project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.MaskFormatter; 

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CardLayout card = new CardLayout(); 
	
	private JFrame _jfRulesFrame; 
	private JPanel _jplStartMenu;
	private JPanel _jplGamePanel;

	private JFormattedTextField _tfNumOfPlayer;
	private JFormattedTextField _tfSizeOfbet;
	
	private JButton _btnStart; 
	private JButton _btnRules; 
	private JButton _btnMainMenu; 
	
	public int _numOfPlayers = 0; 
	
	public MainWindow() {
		Toolkit tk = Toolkit.getDefaultToolkit(); 
		Dimension dim = tk.getScreenSize(); 
		
		this.setSize(500,600);
		
		// Determines the dimension of the computer and frame size for centering the program
		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);
		
		
		this.setLocation(xPos, yPos);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Liar's Dice V1.0"); 
		this.setResizable(false);
		this.setLayout(card);
		 
		// create new JPanel for the start menu
		_jplStartMenu = new JPanel(); 
	
		
		// creates JPanel for game screen. 
		_jplGamePanel = new JPanel(); 
		_jplGamePanel.setVisible(false);
	
		
		// add start menu and game menu to main window
		this.add(_jplGamePanel); 
		this.add(_jplStartMenu);
		
		// create start button
		_btnStart = new JButton("Start"); 
		
		// add start button to start menu panel
		_jplStartMenu.add(_btnStart); 
		
		// create action listener for start button
		ListenFor_btnStart l_btnStart = new ListenFor_btnStart();  
		
		// activate action listener for start button
		_btnStart.addActionListener(l_btnStart);
		
		// creates text field for entering the number of players for the game. 
		_tfNumOfPlayer = new JFormattedTextField(createFormatter("#"));;
		_tfNumOfPlayer.setColumns(5);
		_tfNumOfPlayer.setToolTipText("Enter the number of players");
		_jplStartMenu.add(_tfNumOfPlayer);
		
		// create field for starting pot size
		_tfSizeOfbet = new JFormattedTextField(createFormatter("##"));;
		_tfSizeOfbet.setColumns(5);
		_tfSizeOfbet.setToolTipText("Enter in starting Pot size");
		_jplStartMenu.add(_tfSizeOfbet);
	 
		
		// create Rules button and actions for press
		_btnRules = new JButton("Rules");
		ListenFor_btnRules  l_btnRules =  new ListenFor_btnRules(); 
		_btnRules.addActionListener(l_btnRules);
		_jplGamePanel.add(_btnRules); 
		
		// create Main Menu button and action for press
		_btnMainMenu = new JButton("Main Menu"); 
		ListenFor_btnMainMenu l_btnMainMenu = new ListenFor_btnMainMenu(); 
		_btnMainMenu.addActionListener(l_btnMainMenu); 
		_jplGamePanel.add(_btnMainMenu); 
		
		// set objects to visible
		_jplStartMenu.setVisible(true);
		this.setVisible(true);
	}
	
	private class ListenFor_btnMainMenu implements ActionListener{
		@Override 
		public void actionPerformed(ActionEvent e){
			System.out.println("Main Menu button pressed");
			_jplStartMenu.setVisible(true); // Start menu will be displayed 
			 
			_jplGamePanel.setVisible(false); // Game frame will be hidden.
		}
	}
	
	
	
	private class ListenFor_btnRules implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			System.out.println("Rules button pressed");
			_jfRulesFrame = new JFrame("Rules"); 
			_jfRulesFrame.setSize(500,600);
			
			// code here for displaying rules 
			
			_jfRulesFrame.setVisible(true); 
		}
	}
	
	/**
	 * Method listens for start button to be press. Them will execute the a page flip to the game page.  
	 * 
	 * @author Adam Metelski
	 *
	 */
	private class ListenFor_btnStart implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Start button pressed"); 
			String text = _tfNumOfPlayer.getText();
			
			System.out.println("Text = " + text);
			
			if (text != ""){
				_numOfPlayers = Integer.parseInt(text);
				
				if(_numOfPlayers > 0){
					_jplStartMenu.setVisible(false); // Start menu will be hidden 
					 
					_jplGamePanel.setVisible(true); // Game frame will be displayed.
				}
			}
				
		}
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
}



/*
	
	public MainWindow() {
		
		
		_startMenu = new JPanel();
		_startMenu.setLayout(null);
		_startMenu.setBounds(12, 11, 408, 229);
		
		_gameFrame = new JPanel();
		_gameFrame.setVisible(false);
		_gameFrame.setLayout(null);
		_gameFrame.setBounds(12, 11, 408, 229);
		_gameFrame.setBackground(Color.GREEN);
		
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
	
	
	private void initialize() {
		_frame = new JFrame("Liar's Dice V1.0");
		_frame.setVisible(true);
		_frame.setBounds(100, 100, 450, 300);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		_frame.getContentPane().add(startMenu);
		_frame.getContentPane().add(gameFrame);
		
		_frame.getContentPane().setLayout(null); // Sets layout to null for free style layout. 
		JButton btnNewButton = new JButton("Start");
		_frame.getContentPane().add(btnNewButton, BorderLayout.CENTER);
		
		
		_numOfPlayer = new JFormattedTextField(createFormatter("#"));
		_numOfPlayer.setBounds(122, 138, 154, 22);
		_startMenu.add(numOfPlayer);
		_numOfPlayer.setColumns(10);
		
		JButton btnStart = new JButton("Start");
		
		
		btnStart.setBounds(146, 64, 97, 25);
		_startMenu.add(btnStart);
		
		JTextPane txtpnEnterNumberOf = new JTextPane();
		txtpnEnterNumberOf.setBackground(UIManager.getColor("Button.background"));
		txtpnEnterNumberOf.setText("Enter Number of PLayer");
		txtpnEnterNumberOf.setBounds(122, 103, 154, 22);
		startMenu.add(txtpnEnterNumberOf);
		
		
		
		// Start button action below. 
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String text = numOfPlayer.getText();
				int players = 0;
				
				System.out.println("Text = " + text);
				
				if (text != ""){
					players = Integer.parseInt(text);
					
					if(players > 0){
						startMenu.setVisible(false); // Start menu will be hidden 
						gameFrame.setVisible(true); // Game frame will be displayed. 
					}
				}
				
				System.out.println(players);
				
				
			}
		});
	}
}

*/ 