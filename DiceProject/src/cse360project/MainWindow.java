package cse360project;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.border.BevelBorder; 

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	Toolkit tk; 
	
	private static final long serialVersionUID = 1L;
	private static CardLayout card = new CardLayout(); 
	
	private JFrame _jfRulesFrame; 
	private JPanel _jplStartMenu;
	private JPanel _jplGamePanel;
	private DicePanel _jplDicePanel;

	private JFormattedTextField _tfNumOfPlayer;
	private JFormattedTextField _tfSizeOfbet;
	
	private JButton _btnStart; 
	private JButton _btnRules; 
	private JButton _btnMainMenu; 
	
	// testing images
	Image picture; 
	ImageIcon _image; 
	JLabel _jlBackgroundImage; 
	
	public int _numOfPlayers = 0; 
	
	public MainWindow() {
	    tk = Toolkit.getDefaultToolkit(); 
		Dimension dim = tk.getScreenSize(); 
		
		this.setSize(500,600);
		
		// Determines the dimension of the computer and frame size for centering the program
		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);
		
		
		this.setLocation(xPos, yPos);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Liar's Dice V1.0"); 
		this.setResizable(false);
		getContentPane().setLayout(card);
		
		// Start menu and game panel creation methods
		createStartMenuPanel();
		createGamePanel();
		
		
		this.setVisible(true);
	}
	
	/**
	 * Method create the game panel and game panel buttons
	 */
	private void createGamePanel(){
		// creates JPanel for game screen. 
		_jplGamePanel = new JPanel(); 
		_jplGamePanel.setLayout(null);
		_jplGamePanel.setVisible(false);
		
		// adds game menu to main window 
		getContentPane().add(_jplGamePanel); 
		
		// Create dice roll panel
		_jplDicePanel = new DicePanel(5);
		_jplDicePanel.setVisible(true);
		_jplDicePanel.setLocation(100, 250);
		_jplGamePanel.add(_jplDicePanel);
		
		// create Rules button and actions for press
		_btnRules = new JButton("Rules");
		ListenFor_btnRules  l_btnRules =  new ListenFor_btnRules(); 
		_btnRules.setBounds(146, 13, 107, 25);
		_btnRules.addActionListener(l_btnRules);
		_jplGamePanel.add(_btnRules); 
		
				
		// create Main Menu button and action for press
		_btnMainMenu = new JButton("Main Menu"); 
		_btnMainMenu.setSize(107, 25);
		_btnMainMenu.setLocation(253, 13);
		ListenFor_btnMainMenu l_btnMainMenu = new ListenFor_btnMainMenu(); 
		_btnMainMenu.addActionListener(l_btnMainMenu); 
		_jplGamePanel.add(_btnMainMenu);
		
	}
	
	/** 
	 * Method creates and the start menu panel, start button, number of players field, and pot size field. 
	 */
	private void createStartMenuPanel(){
		// create new JPanel for the start menu
		_jplStartMenu = new JPanel(); 
		
		// add start menu main window
		getContentPane().add(_jplStartMenu);
		
		// create start button
		_btnStart = new JButton("Start"); 
		
		// add start button to start menu panel
		_jplStartMenu.add(_btnStart); 
		
		// create action listener for start button
		ListenFor_btnStart l_btnStart = new ListenFor_btnStart();  
		
		// activate action listener for start button
		_btnStart.addActionListener(l_btnStart);
		
		// creates text field for entering the number of players for the game. 
		_tfNumOfPlayer = new JFormattedTextField(createFormatter("#"));
		_tfNumOfPlayer.setColumns(5);
		_tfNumOfPlayer.setToolTipText("Enter the number of players");
		_jplStartMenu.add(_tfNumOfPlayer);
		
		// create field for starting pot size
		_tfSizeOfbet = new JFormattedTextField(createFormatter("##"));;
		_tfSizeOfbet.setColumns(5);
		_tfSizeOfbet.setToolTipText("Enter in starting Pot size");
		_jplStartMenu.add(_tfSizeOfbet);
		
		// set objects to visible
		_jplStartMenu.setVisible(true);
	}
	
	/**
	 * Class Listens for the main menu button to be pressed and executes a page flip back to the start menu. 
	 * @author Adam Metelski
	 *
	 */
	private class ListenFor_btnMainMenu implements ActionListener{
		@Override 
		public void actionPerformed(ActionEvent e){
			System.out.println("Main Menu button pressed");
			_jplStartMenu.setVisible(true); // Start menu will be displayed 
			 
			_jplGamePanel.setVisible(false); // Game frame will be hidden.
		}
	}
	
	
	/**
	 * Class Listens for the rules button to be pressed and executes opening up the rules panel. 
	 * @author Adam Metelski
	 *
	 */
	private class ListenFor_btnRules implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			System.out.println("Rules button pressed");
			_jfRulesFrame = new JFrame("Rules"); 
			_jfRulesFrame.setSize(500,600);
			
			// testing adding a picture to the rules page
		
			try{
				_jfRulesFrame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("index1.jpg")))));
			}catch(IOException e1){
				System.out.println("Image Doesn't exist");
			}
			
			_jfRulesFrame.setVisible(true); 
		}
	}
	
	/**
	 * Class listens for start button to be press. Them will execute the a page flip to the game page.  
	 * 
	 * @author Adam Metelski
	 *
	 */
	private class ListenFor_btnStart implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Start button pressed"); 
			
			
			String text = _tfNumOfPlayer.getText();
			
			System.out.println("Text =" + text );
			
			if (!text.equals(" ")){
				_numOfPlayers = Integer.parseInt(text);
				
				if(_numOfPlayers > 0){
					_jplStartMenu.setVisible(false); // Start menu will be hidden 
					 
					_jplGamePanel.setVisible(true); // Game frame will be displayed.
				}
			}
				
		}
	}	
	
	/** 
	 *  Method creates a mask for the text field
	 * @param s String input for digits to mask
	 * @return 
	 */
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
