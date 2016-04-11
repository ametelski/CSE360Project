package cse360project;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

/**
 * @author Miguel Deniz Lopez Last Update: 4/11/16
 */

/*
 * @This class is in charge of simulating the roll of 'n' dice, and storing the
 * results in a JPanel. The JPanel can then be added to any frame or embedded
 * within another JPanel for display.
 * 
 * Initialization: DicePanel panel = new DicePanel(numberOfDice);
 * panel.setVisible(true); panel.setLocation(xCord, yCord); // Coordinates
 * relative to parent panel parentPanel.add(panel);
 */
public class DicePanel extends JPanel {
	private JPanel _jplRollDisplay;
	private JButton _btnRoll;
	private DiceRoll _diceRoll;
	private int _numDice;

	final private int IMAGE_SIZE = 50;
	final private int BORDER_WIDTH = 5;
	final private Color DISP_COLOR = Color.DARK_GRAY;
	final private int BTN_WIDTH = 80;
	final private int BTN_HEIGHT = 30;

	/**
	 * DicePanel(int) (Constructor) This will create a JPanel with the correct
	 * dimensions to fit the entire display. The function setBounds() should not
	 * be used, the dimensions will automatically get adjusted on instantiation.
	 */
	public DicePanel(int numDice) {
		// Property setup of the panel
		this.setLayout(null);
		this.setVisible(true);
		this.setBackground(DISP_COLOR);

		_numDice = numDice;

		// Setup of roll display
		_jplRollDisplay = new JPanel();
		int displayWidth = _numDice * IMAGE_SIZE + 2 * BORDER_WIDTH + _numDice;
		int displayHeight = IMAGE_SIZE + 2 * BORDER_WIDTH;

		// Adjust size of the panel
		int width = Math.max(displayWidth, 90);
		int height = displayHeight + BTN_HEIGHT + BORDER_WIDTH;
		this.setBounds(0, 0, width, height);

		// Adjust size of roll display
		int xPos = width / 2 - displayWidth / 2;
		int yPos = 0;
		_jplRollDisplay.setBounds(xPos, yPos, displayWidth, displayHeight);
		_jplRollDisplay.setLayout(null);
		this.add(_jplRollDisplay);

		try {
			_diceRoll = new DiceRoll(numDice);
		} catch (InvalidDiceQuantity e) {
			e.printStackTrace();
		}

		// Roll button setup, and event listener
		_btnRoll = new JButton("Roll");
		ListenFor_btnMainMenu l_btnRoll = new ListenFor_btnMainMenu();
		_btnRoll.addActionListener(l_btnRoll);
		int xCord = this.getBounds().width / 2 - BTN_WIDTH / 2;
		int yCord = this.getBounds().height - BTN_HEIGHT - BORDER_WIDTH;
		_btnRoll.setBounds(xCord, yCord, BTN_WIDTH, BTN_HEIGHT);
		this.add(_btnRoll);

		Display();
	}

	/**
	 * This will simulate the roll of all the dice and will update the panel
	 * display.
	 * 
	 * @param none
	 * 
	 * @return none
	 */
	public void Roll() {
		_diceRoll.Roll();
		Display();
	}

	/**
	 * Returns an array containing all the dice rolls.
	 * 
	 * @param none
	 * 
	 * @return (int[]) An array containing the last roll the dice. Will return
	 *         all 0's if there has been no rolls.
	 */
	public int[] GetRollData() {
		return _diceRoll.GetRollData();
	}

	/**
	 * Updates the panel display. This does NOT simulate a roll.
	 * 
	 * @param none
	 * 
	 * @return none
	 */
	public void Display() {
		// Clear display before adding any more components
		// New components will not display properly if this is not done
		_jplRollDisplay.removeAll();
		_jplRollDisplay.repaint();

		ImageIcon image = getImage(1);

		// Adjust the background of the panel
		_jplRollDisplay.setBackground(DISP_COLOR);

		// To display the roll, we add n number of images to the JPanel display
		JLabel[] lblDiceImages = new JLabel[_numDice];
		int[] _rollData = _diceRoll.GetRollData();

		for (int index = 0; index < _numDice; index++) {
			image = getImage(_rollData[index]);

			lblDiceImages[index] = new JLabel();
			lblDiceImages[index].setBounds(0, 0, IMAGE_SIZE, IMAGE_SIZE);
			lblDiceImages[index].setIcon(image);
			lblDiceImages[index].setLocation(index * IMAGE_SIZE + index + BORDER_WIDTH, BORDER_WIDTH);

			_jplRollDisplay.add(lblDiceImages[index]);
		}
	}

	/**
	 * Returns an ImageIcon with the image of the number. For example, if 1, it
	 * returns a picture of the side 1 of a dice.
	 * 
	 * @param rollValue,
	 *            the number of the desired image to display
	 * 
	 * @return ImageIcon
	 */
	private ImageIcon getImage(int rollValue) {
		ImageIcon image;

		switch (rollValue) {
		case 1:
			image = new ImageIcon("src/1Dice.jpg");
			break;
		case 2:
			image = new ImageIcon("src/2Dice.jpg");
			break;
		case 3:
			image = new ImageIcon("src/3Dice.jpg");
			break;
		case 4:
			image = new ImageIcon("src/4Dice.jpg");
			break;
		case 5:
			image = new ImageIcon("src/5Dice.jpg");
			break;
		case 6:
			image = new ImageIcon("src/6Dice.jpg");
			break;
		default:
			image = new ImageIcon("src/InvalidDice.jpg");
			break;
		}

		return image;
	}

	// Event listener for btnRoll
	class ListenFor_btnMainMenu implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Roll button pressed...");
			_diceRoll.Roll();
			Display();
		}
	}
}

/**
 * Simulates the roll of the given number or dice.
 */
class DiceRoll {
	private int _numDice;
	private int[] _rollData;
	private Random _randomizer;

	/** Setup the object with correct int[] and randomizer.
	 * 
	 * @param numDice - number of dice to roll
	 * @throws InvalidDiceQuantity if the number of dice <=0
	 */
	public DiceRoll(int numDice) throws InvalidDiceQuantity {
		if (numDice <= 0)
			throw new InvalidDiceQuantity("Dice(int, int, int) constructor.");

		_numDice = numDice;
		_rollData = new int[_numDice];
		_randomizer = new Random();
	}

	/**
	 * Simulates a dice roll.
	 * 
	 * @param none
	 * @return none
	 */
	public void Roll() {
		for (int index = 0; index < _rollData.length; index++) {
			_rollData[index] = _randomizer.nextInt(6) + 1;
			System.out.print(_rollData[index] + " ");
		}
		System.out.println();
	}

	/**
	 * Returns an array containing all the dice rolls.
	 * 
	 * @param none
	 * 
	 * @return (int[]) An array containing the last roll the dice. Will return
	 *         all 0's if there has been no rolls.
	 */
	public int[] GetRollData() {
		// Make a copy of data
		int[] tmp = new int[_rollData.length];
		for (int index = 0; index < _rollData.length; index++)
			tmp[index] = _rollData[index];
		return tmp;
	}

}

// Custom exception
class InvalidDiceQuantity extends Exception {
	public InvalidDiceQuantity(String msg) {
		super(msg);
	}
}