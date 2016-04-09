package cse360project;

import java.awt.EventQueue;

import javax.swing.*;

/**
 * Liar's Dice Game
 * 
 * @author Nicole Gettelman
 * @author Adam Metelski
 * @author Zackary Crosley
 * @author Miguel Deniz Lopez
 * @author Michael Kintscher
 * @version March 31, 2016
 *
 */


public class Dice {
	

	private static MainWindow _window; 
	
	public static void main(String []args){
		System.out.println("Adam Metelski was here!");
		System.out.println("Zackary Crosley standing by.");
		System.out.printf("Miguel Deniz Lopez\n");
		System.out.println("Michael Kintscher ready for battle.");
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 _window = new MainWindow();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}
}
