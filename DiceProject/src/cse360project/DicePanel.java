package cse360project;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class DicePanel extends JPanel {
	private JPanel  _rollDisplay;
	private JButton _btnRoll;
	private DiceRoll    _diceRoll;
	private int _numDice;

	public DicePanel(int numDice) {
		this.setLayout(null);
		this.setVisible(true);
		this.setBackground(Color.black);

		_numDice = numDice;
		
		_rollDisplay = new JPanel();
		int displayWidth = _numDice * 50 + 15;
		int displayHeight = 50 + 10;
		_rollDisplay.setBounds(0, 0, displayWidth, displayHeight);
		_rollDisplay.setLayout(null);
		this.add(_rollDisplay);

		try {
			_diceRoll = new DiceRoll(numDice, _rollDisplay);
		} catch (InvalidDiceQuantity e) {
			e.printStackTrace();
		}

		_btnRoll = new JButton("Roll");
		ListenFor_btnMainMenu l_btnRoll = new ListenFor_btnMainMenu();
		_btnRoll.addActionListener(l_btnRoll);
		int btnWidth = 80;
		int btnHeight = 30;
		int xCord = _rollDisplay.getWidth() / 2 - btnWidth / 2;
		int yCord = _rollDisplay.getHeight();
		_btnRoll.setBounds(xCord, yCord, btnWidth, btnHeight);
		this.add(_btnRoll);

		this.setBounds(0, 0, _rollDisplay.getWidth(), _rollDisplay.getHeight() + btnHeight + 5);
	
		Display();
	}

	// Same functionality as button click
	public void Roll() {
		_diceRoll.Roll();
		Display();
	}

	public int[] GetRollData()
	{
		return _diceRoll.GetRollData();
	}
	
	public void Display() {
		_rollDisplay.removeAll();
		_rollDisplay.repaint();

		// Adjusts the size of the panel, so that all dice will fit
		ImageIcon background = getImage(-1);

		final int BORDER_WIDTH = 5;
		final int IMAGE_WIDTH = background.getIconWidth();
		final int IMAGE_HEIGHT = background.getIconHeight();

		int width = _numDice * IMAGE_WIDTH + BORDER_WIDTH * 2 + _numDice;
		int height = IMAGE_HEIGHT + BORDER_WIDTH * 2;
		_rollDisplay.setSize(width, height);

		// Adjust the background of the panel
		_rollDisplay.setBackground(Color.BLACK);

		// To display the roll, we add n number of images to the JPanel display
		JLabel[] lblDiceImages = new JLabel[_numDice];
		int[] _rollData = _diceRoll.GetRollData();
		
		for (int index = 0; index < _numDice; index++) {
			background = getImage(_rollData[index]);

			lblDiceImages[index] = new JLabel();
			lblDiceImages[index].setBounds(0, 0, 50, 50);
			lblDiceImages[index].setIcon(background);
			lblDiceImages[index].setLocation(index * 50 + index + BORDER_WIDTH, BORDER_WIDTH);
			_rollDisplay.add(lblDiceImages[index]);
		}
	}
	
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
	
	class ListenFor_btnMainMenu implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Roll button pressed...");
			_diceRoll.Roll();
			Display();
		}
	}
}

class DiceRoll {
	private int _numDice;
	private int[] _rollData;
	private Random _randomizer;

	public DiceRoll(int numDice, JPanel panel) throws InvalidDiceQuantity {
		if (numDice <= 0)
			throw new InvalidDiceQuantity("Dice(int, int, int) constructor.");

		_numDice = numDice;
		_rollData = new int[_numDice];
		_randomizer = new Random();
	}

	public void Roll() {
		for (int index = 0; index < _rollData.length; index++) {
			_rollData[index] = _randomizer.nextInt(6) + 1;
			System.out.print(_rollData[index] + " ");
		}
		System.out.println();
	}

	public int[] GetRollData()
	{
		// Make a copy of data
		int[] tmp = new int[_rollData.length];
		for (int index = 0; index < _rollData.length; index++)
			tmp[index] = _rollData[index];
		return tmp;
	}
	
}

class InvalidDiceQuantity extends Exception {
	public InvalidDiceQuantity(String msg) {
		super(msg);
	}
}