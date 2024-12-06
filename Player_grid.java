import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Player_grid extends JPanel implements ActionListener, MouseListener {

	
	JButton[][] btn = new JButton[5][5]; // Array to hold buttons

	numGenerator B_random_num; // Generates numbers for 'B' column
    numGenerator I_random_num; // Generates numbers for 'I' column
    numGenerator N_random_num; // Generates numbers for 'N' column
    numGenerator G_random_num; // Generates numbers for 'G' column
    numGenerator O_random_num; // Generates numbers for 'O' column
	
    Color Player_Color; // Player's color
	Color primary_color = new Color(92, 255, 230); // Primary color for buttons
	Color background_color = new Color(255, 255, 255, 50); // Background color for buttons

	int button_status[][] = new int[5][5]; // Status of each button
	int button_numbers[][] = new int[5][5]; // Numbers assigned to each buttons
	boolean bingo = false; // Flag indicating if bingo is achieved

	// Constructor
	Player_grid(Color Player_Color) {
		this.Player_Color = Player_Color;
		this.setPreferredSize(new Dimension(300, 300));
		this.setBackground(new Color(0, 0, 0, 100));
		this.setLayout(new GridLayout(5, 5, 5, 5));

		generate_num(); // Generate numbers for each column
		for (int i = 0; i <= button_status.length - 1; i++) {
			for (int j = 0; j <= button_status[0].length - 1; j++) {

				btn[i][j] = new JButton();
				btn[i][j].setFont(new Font("Dialog", Font.BOLD, 18));
				btn[i][j].addActionListener(this);
				btn[i][j].addMouseListener(this);
				btn[i][j].setFocusable(false);
				btn[i][j].setForeground(primary_color);
				btn[i][j].setBackground(background_color);
				btn[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(92, 255, 230)));
				btn[i][j].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				button_status[i][j] = 0; // Initialize button status
				button_modify(i, j); // Modify button based on its position

				this.add(btn[i][j]); // Add button to panel
			}
		}
		
		// Set the center button as free
		btn[2][2].setBackground(Color.white);
		btn[2][2].setForeground(Player_Color);
		btn[2][2].setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Player_Color));
		btn[2][2].setEnabled(true);
		button_status[2][2] = 2; // Set button status as free in the middle
	}

	// Method to reset the grid
	public void reset() {
		bingo = false; // Reset bingo flag
		generate_num(); // Regenerate numbers for each column
		for (int i = 0; i <= button_status.length - 1; i++) {
			for (int j = 0; j <= button_status[0].length - 1; j++) {
				btn[i][j].setForeground(primary_color);
				btn[i][j].setBackground(background_color);
				btn[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(92, 255, 230)));
				button_status[i][j] = 0; // Reset button status
				button_modify(i, j); // Modify button based on its position
			}
		}
		// Set the center button as free
		btn[2][2].setBackground(Color.white);
		btn[2][2].setForeground(Player_Color);
		btn[2][2].setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Player_Color));
		btn[2][2].setEnabled(true);
		button_status[2][2] = 2; // Set button status as free in the middle

	}

	// Method to generate numbers for each column
	public void generate_num() {
		B_random_num = new numGenerator(1, 15, 5); // "B" is 1-15
		I_random_num = new numGenerator(16, 30, 5); // "I" is 16-30
		N_random_num = new numGenerator(31, 45, 5); // "N" is 31-45
		G_random_num = new numGenerator(46, 60, 5); // "G" is 46-60
		O_random_num = new numGenerator(61, 75, 5); // "O" is 61-75
	}

	// Method to modify button text based on its current selected position
	public void button_modify(int i, int j) {

		switch (j) {
		case 0: // Column "B"
			btn[i][0].setText(Integer.toString(B_random_num.numbers.get(i)));
			button_numbers[i][0] = B_random_num.numbers.get(i);
			break;
		case 1: // Column "I"
			btn[i][1].setText(Integer.toString(I_random_num.numbers.get(i)));
			button_numbers[i][1] = I_random_num.numbers.get(i);
			break;
		case 2: // Column "N"
			if (i == 2) {
				btn[2][2].setText("free"); // Set the center button as free
			} else {
				btn[i][2].setText(Integer.toString(N_random_num.numbers.get(i)));
				button_numbers[i][2] = N_random_num.numbers.get(i);
			}
			break;
		case 3: // Column "G"
			btn[i][3].setText(Integer.toString(G_random_num.numbers.get(i)));
			button_numbers[i][3] = G_random_num.numbers.get(i);
			break;
		case 4: // Column "O"
			btn[i][4].setText(Integer.toString(O_random_num.numbers.get(i)));
			button_numbers[i][4] = O_random_num.numbers.get(i);
			break;

		}

	}
	
	// Method to check if bingo is achieved
	public void isBingo() {

		// check horizontally
		for (int row = 0; row < button_status.length; row++) {
			if (button_status[row][0] == 2 && button_status[row][1] == 2 && button_status[row][2] == 2
					&& button_status[row][3] == 2 && button_status[row][4] == 2) {

				for (int i = 0; i < 5; i++) {
					btn[row][i].setBackground(Color.green); // set button to green horizontally
				}

				bingo = true;
				System.out.println("Bingo horizontally at row " + (row + 1));
			}
		}

		// check vertically
		for (int column = 0; column < button_status[0].length; column++) {

			if (button_status[0][column] == 2 && button_status[1][column] == 2 && button_status[2][column] == 2
					&& button_status[3][column] == 2 && button_status[4][column] == 2) {

				for (int i = 0; i < 5; i++) {
					btn[i][column].setBackground(Color.green); // set button to green vertically
				}

				bingo = true;
				System.out.println("Bingo vertically at column " + (column + 1));
			}
		}

		// check diagonally
		if (button_status[0][0] == 2 && button_status[1][1] == 2 && button_status[2][2] == 2 && button_status[3][3] == 2
				&& button_status[4][4] == 2) {

			for (int i = 0; i < 5; i++) {
				btn[i][i].setBackground(Color.green); // set button to green diagonally
			}

			bingo = true;
			System.out.println("Bingo true 3");
		} else if (button_status[4][0] == 2 && button_status[3][1] == 2 && button_status[2][2] == 2
				&& button_status[1][3] == 2 && button_status[0][4] == 2) {

			for (int i = 0; i < 5; i++) {
				btn[i][4 - i].setBackground(Color.green); // set button to green diagonally
			}
			bingo = true;
			System.out.println("Bingo true 4");
		}

	}

	// -------Button Status-------
	// "0" = deactivated button
	// "1" = activated button
	// "2" = activated button that contain one of the numbers from calling

	@Override
	public void actionPerformed(ActionEvent e) {

		// when one of the buttons in grid was clicked
		if (bingo != true && BingoGame.game_is_Running == true) {
			
			for (int i = 0; i <= button_status.length - 1; i++) {
				for (int j = 0; j <= button_status[0].length - 1; j++) {
					
					if (e.getSource() == btn[i][j] && button_status[i][j] == 0 && e.getSource() != btn[2][2]) {
						btn[i][j].setBackground(Color.cyan); // activate current button
						button_status[i][j] = 1; // status set to activated

						// if current button contains previously called numbers
						if (Calling.called_numbers.contains(button_numbers[i][j])) {
							button_status[i][j] = 2; // status set to activated (contain bingo numbers)
							if (Settings.showCalledNunbers == true) // if cheat is on
								btn[i][j].setBackground(Color.blue); // highlight button that contains previously called numbers
						}

					}
					// if activated button was clicked again
					else if (e.getSource() == btn[i][j] && (button_status[i][j] == 1 || button_status[i][j] == 2)
							&& e.getSource() != btn[2][2]) {
						btn[i][j].setBackground(Color.white);
						button_status[i][j] = 0; // deactivated the button
					}

				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		// When mouse exited any buttons, change its appearances
		for (int i = 0; i <= button_status.length - 1; i++) {
			for (int j = 0; j <= button_status[0].length - 1; j++) {
				if (e.getSource() == btn[i][j] && button_status[i][j] == 0 && e.getSource() != btn[2][2]) {
					btn[i][j].setForeground(Player_Color);
					btn[i][j].setBackground(Color.white);
					btn[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Player_Color));

				}
			}
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		// When mouse exited any buttons, change its appearances to default
		for (int i = 0; i <= button_status.length - 1; i++) {
			for (int j = 0; j <= button_status[0].length - 1; j++) {
				if (e.getSource() == btn[i][j] && button_status[i][j] == 0 && e.getSource() != btn[2][2]) {
					btn[i][j].setForeground(primary_color);
					btn[i][j].setBackground(background_color);
					btn[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(92, 255, 230)));
				}
			}
		}
	}
}
