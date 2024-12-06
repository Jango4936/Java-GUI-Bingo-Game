import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Calling extends JPanel {

	// Colors for drawing
	private Color primary_color = new Color(92, 255, 230);
	private Color background_color = new Color(0, 0, 0, 90);
	
	// Alphabets group (B, I, N, G, O)
	private String alph_group; 
	
	// Array List to store called numbers
	static ArrayList<Integer> called_numbers = new ArrayList<Integer>();
	
	// Random number generator
	static numGenerator random_num;
	int num;
	



	// Constructor
	Calling() {
		this.setBackground(null); // Set background to null
		this.setOpaque(false); // Set transparency
		this.setLayout(null); // Set layout to null for custom positioning

		// Initialize random number generator
		random_num = new numGenerator(1, 75, BingoGame.num_of_calllings);
		repaint(); // Call repaint to update the panel
    }
	
	
	// Method to reset the panel
	public void reset() {
		num = 0; // Reset current number
		alph_group = ""; // Reset alphabet group
		random_num = new numGenerator(1, 75, BingoGame.num_of_calllings); // Reset random number generator
		called_numbers.clear(); // Clear the list of called numbers
		repaint(); // Call repaint to update the panel
	}
	
	// Method to paint the panel
	@Override
	public void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g; // Cast Graphics to Graphics2D
		super.paintComponent(g); // Call superclass paintComponent method
		
		// Fill background with background color
		g2d.setColor(background_color);
		g2d.fillRect(0, 0, 481, 657);
		
		// Smoothen text with anti-aliasing
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		// Set font and color for drawing "Calling:"
		g2d.setFont(new Font("Dialog", Font.BOLD, 50));
		g2d.setColor(primary_color);
		g2d.drawString("Calling:", 30, 100);

		
		// If the game is running, get the next number and update alph_group accordingly
		if (BingoGame.game_is_Running == true) {
			num = random_num.numbers.get(BingoGame.count);  // Get the next number
			called_numbers.add(num); // Add the current number to the list of called numbers
			;
			if (num <= 0) {
                alph_group = "X"; // Set alph_group to "X" if the number is less than or equal to 0
            } else if (num <= 15) {
                alph_group = "B"; // Set alph_group to "B" for numbers 1-15
            } else if (num <= 30) {
                alph_group = "I"; // Set alph_group to "I" for numbers 16-30
            } else if (num <= 45) {
                alph_group = "N"; // Set alph_group to "N" for numbers 31-45
            } else if (num <= 60) {
                alph_group = "G"; // Set alph_group to "G" for numbers 46-60
            } else if (num <= 75) {
                alph_group = "O"; // Set alph_group to "O" for numbers 61-75
            }
		} else {
			num = 0;
			alph_group = "X"; // If the game is not running, set alph_group to "X"
		}

		 // Draw the current number and alph_group
		g2d.setFont(new Font("Dialog", Font.BOLD, 80));
		g2d.drawString(alph_group + "-" + Integer.toString(num), 30, 180);

		// Draw the remaining number of calls
		g2d.setFont(new Font("Dialog", Font.BOLD, 50));
		g2d.drawString("Remaing:", 30, 250);
		g2d.setFont(new Font("Dialog", Font.BOLD, 80));
		g2d.drawString(Integer.toString(BingoGame.num_of_calllings - BingoGame.count), 30, 330);

		 // Check for game end conditions and display winner message accordingly
		if (BingoGame.num_of_calllings - BingoGame.count == 0 || BingoGame.winner == '1' || BingoGame.winner == '2') {

			switch (BingoGame.winner) {
			case '0':
				g2d.setFont(new Font("Dialog", Font.BOLD, 50));
				g2d.setColor(Color.red);
				g2d.drawString("No one win!!", 30, 500);
				break;
			case '1':
				g2d.setFont(new Font("Dialog", Font.BOLD, 50));
				g2d.setColor(primary_color);
				g2d.drawString("Player 1 won!!", 30, 500);
				break;
			case '2':
				g2d.setFont(new Font("Dialog", Font.BOLD, 50));
				g2d.setColor(primary_color);
				g2d.drawString("Player 2 won!!", 30, 500);
				break;
			default:

			}
		}
		
		
		

	}

}
