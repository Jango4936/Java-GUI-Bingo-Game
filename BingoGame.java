import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import java.awt.Cursor;

public class BingoGame extends JFrame implements ActionListener, MouseListener  {
	
	// Player grids, panels, and numbers caller
	Player_grid p1_grid;
	Player_grid p2_grid;
	Player_Panel Player_1_Panel;
	Player_Panel Player_2_Panel;
	Calling numCaller;

	// Player colors
	Color player_1_color;
	Color player_2_color;

	// UI components
	JPanel background_panel;
	JButton start_button;
	JButton stop_button;
	JButton reset_button;
	JButton exit_button;
	JButton P1_bingo_button;
	JButton P2_bingo_button;
	JLabel background_scene;
	JLabel background_transparent;

	// Timer for calling numbers
	Timer timer;

	// Game state variables
	static Boolean game_is_Running = false;
	static int count;
	static int num_of_calllings = 15;
	static char winner = '0';
	static int DELAY = 3000;

	// Import Images
	ImageIcon TitleLogo = new ImageIcon("Assets/Pictures/BINGO_logo.jpg");
	ImageIcon background = new ImageIcon("Assets/Pictures/background02.gif");
	final int size = 5;

	//Constructor
	BingoGame() {
		// JFrame setup
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		this.setSize(1380, 820);
		this.setTitle("Bingo Game v0.1");
		this.setLocationRelativeTo(null);
		this.setIconImage(TitleLogo.getImage());
		
		// Player colors
		player_1_color = new Color(32, 35, 206);
		player_2_color = new Color(206, 32, 107);

		// Background animated scene
		background_scene = new JLabel("");
		background_scene.setSize(1380, 820);
		background_scene.setIcon(background);
		
		// Background transparent box
		background_transparent = new JLabel("");
		background_transparent.setOpaque(true);
		background_transparent.setLocation(42, 30);
		background_transparent.setSize(1280, 720);
		background_transparent.setBackground(new Color(0, 0, 0, 99));
		
		// Main Panel behind
		background_panel = new JPanel(); 
		
		// Numbers Caller setup at the center
		numCaller = new Calling(); // 
		numCaller.setBounds(441, 47, 482, 657);

		// Player Bingo Grids Setup
		p1_grid = new Player_grid(player_1_color);
		p1_grid.setBounds(61, 284, 370, 370);
		p2_grid = new Player_grid(player_2_color);
		p2_grid.setBounds(933, 284, 370, 370);
		
		
		// Player's Bingo Buttons Setup
		P1_bingo_button = new JButton("BINGO");
		P1_bingo_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		P1_bingo_button.setSize(150, 50);
		P1_bingo_button.setLocation(181, 665);
		P1_bingo_button.addActionListener(this);
		P1_bingo_button.addMouseListener(this);
		P1_bingo_button.setBackground(new Color(255, 255, 255, 70));
		P1_bingo_button.setForeground(new Color(92, 255, 230));
		P1_bingo_button.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(92, 255, 230)));
		P1_bingo_button.setFocusable(false);
		
		P2_bingo_button = new JButton("BINGO");
		P2_bingo_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		P2_bingo_button.setSize(150, 50);
		P2_bingo_button.setLocation(1033, 665);
		P2_bingo_button.addActionListener(this);
		P2_bingo_button.addMouseListener(this);
		P2_bingo_button.setBackground(new Color(255, 255, 255, 70));
		P2_bingo_button.setForeground(new Color(92, 255, 230));
		P2_bingo_button.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(92, 255, 230)));
		P2_bingo_button.setFocusable(false);

		// Player Panel Setup (Panel above bingo grid)
		Player_1_Panel = new Player_Panel("Player 1", player_1_color);
		Player_1_Panel.setBounds(61, 47, 370, 226);
		Player_2_Panel = new Player_Panel("Player 2", player_2_color);
		Player_2_Panel.setBounds(933, 47, 370, 226);

		// Timer Setup
		timer = new Timer(DELAY, this);

		// Adding components to the frame
		this.getContentPane().add(background_panel);
		background_panel.setLayout(null);
		background_panel.add(numCaller);

		
		// Buttons Setup
		// Exit button
		exit_button = new JButton("Exit Game");
		exit_button.setBounds(272, 584, 150, 50);
		numCaller.add(exit_button);
		exit_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exit_button.addActionListener(this);
		exit_button.addMouseListener(this);
		exit_button.setBackground(new Color(255, 255, 255, 70));
		exit_button.setForeground(new Color(92, 255, 230));
		exit_button.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(92, 255, 230)));
		exit_button.setFocusable(false);
		
		// Start button
		start_button = new JButton("Start Game");
		start_button.setBounds(60, 584, 150, 50);
		numCaller.add(start_button);
		start_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		start_button.addActionListener(this);
		start_button.addMouseListener(this);
		start_button.setBackground(new Color(255, 255, 255, 70));
		start_button.setForeground(new Color(92, 255, 230));
		start_button.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(92, 255, 230)));
		start_button.setFocusable(false);

		// Stop button
		stop_button = new JButton("Stop Game");
		stop_button.setBounds(60, 584, 150, 50);
		numCaller.add(stop_button);
		stop_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		stop_button.addActionListener(this);
		stop_button.addMouseListener(this);
		stop_button.setBackground(new Color(255, 255, 255, 70));
		stop_button.setForeground(new Color(92, 255, 230));
		stop_button.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(92, 255, 230)));
		stop_button.setFocusable(false);

		 // Reset button
		reset_button = new JButton("Reset Game");
		reset_button.setBounds(60, 584, 150, 50);
		numCaller.add(reset_button);
		reset_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		reset_button.addActionListener(this);
		reset_button.addMouseListener(this);
		reset_button.setBackground(new Color(255, 255, 255, 70));
		reset_button.setForeground(new Color(92, 255, 230));
		reset_button.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(92, 255, 230)));
		reset_button.setFocusable(false);

		// Initial button visibility
		reset_button.setVisible(false);
		stop_button.setVisible(false);
		start_button.setVisible(true);

		// Adding remiang components to the frame
		background_panel.add(p1_grid);
		background_panel.add(P1_bingo_button);
		background_panel.add(p2_grid);
		background_panel.add(P2_bingo_button);
		background_panel.add(Player_1_Panel);
		background_panel.add(Player_2_Panel);
		background_panel.add(background_transparent);
		background_panel.add(background_scene);

		this.setVisible(true);
	}

	// Method to end the game
	public void endGame() {
		numCaller.repaint(); // update the numbers caller
		
		System.out.println("winner:  " + winner);
		timer.stop();
		
		game_is_Running = false;
		start_button.setVisible(false);
		stop_button.setVisible(false);
		reset_button.setVisible(true);
		System.out.println("Game Ended");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// when start button is clicked
		if (e.getSource() == start_button) {
			if (game_is_Running == true) {

			} else {
				game_is_Running = true;
				count = 0;
			}
			timer.start();
			start_button.setVisible(false);
			stop_button.setVisible(true);
			reset_button.setVisible(false);
		}
		// exit to main menu button
		else if (e.getSource() == exit_button) {

			int result = JOptionPane.showConfirmDialog(null, "Exit to Mainmenu?", "Confirm Quit",
					JOptionPane.YES_NO_CANCEL_OPTION); // ask user to quit the game to main menu
			if (result == JOptionPane.YES_OPTION) {
				this.setVisible(false);
				this.dispose();
				new MainMenu();
				winner = '0';
				endGame();
				count = 0;
				numCaller.reset();
				p1_grid.reset();
				p2_grid.reset();
				timer.stop();

			} else {
				timer.start();
			}
		}
		
		// when stop button is clicked
		else if (e.getSource() == stop_button) {
			timer.stop();
			start_button.setVisible(true);
			stop_button.setVisible(false);
			reset_button.setVisible(false);
		}

		// reset button
		else if (e.getSource() == reset_button) {

			count = 0;
			numCaller.reset();
			p1_grid.reset();
			p2_grid.reset();
			winner = '0';
			timer.stop();

			start_button.setVisible(true);
			stop_button.setVisible(false);
			reset_button.setVisible(false);
		}

		// when player 1 click bingo button
		else if (e.getSource() == P1_bingo_button && game_is_Running == true) {
			p1_grid.isBingo();

			if (p1_grid.bingo == true) {
				winner = '1';
				endGame();
			}
		}

		// when player 2 click bingo button
		else if (e.getSource() == P2_bingo_button && game_is_Running == true) {
			p2_grid.isBingo();

			if (p2_grid.bingo == true) {
				winner = '2';
				endGame();

			}
		}
		
		// when nothing is clicked but the games is still running
		else if (game_is_Running == true) {

			numCaller.repaint();
			count++;

			if (count >= num_of_calllings) {
				endGame();
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

		// When mouse entered any buttons, change its appearances
		if (e.getSource() == start_button) {
			start_button.setForeground(new Color(206, 32, 107));
			start_button.setBackground(Color.lightGray);
			start_button.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(206, 32, 107)));
		} else if (e.getSource() == stop_button) {
			stop_button.setForeground(new Color(206, 32, 107));
			stop_button.setBackground(Color.lightGray);
			stop_button.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(206, 32, 107)));
		} else if (e.getSource() == reset_button) {
			reset_button.setForeground(new Color(206, 32, 107));
			reset_button.setBackground(Color.lightGray);
			reset_button.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(206, 32, 107)));
		} else if (e.getSource() == exit_button) {
			exit_button.setForeground(new Color(206, 32, 107));
			exit_button.setBackground(Color.lightGray);
			exit_button.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(206, 32, 107)));
		} else if (e.getSource() == P1_bingo_button) {
			P1_bingo_button.setForeground(player_1_color);
			P1_bingo_button.setBackground(Color.lightGray);
			P1_bingo_button.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, player_1_color));
		} else if (e.getSource() == P2_bingo_button) {
			P2_bingo_button.setForeground(player_2_color);
			P2_bingo_button.setBackground(Color.lightGray);
			P2_bingo_button.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, player_2_color));
		}

	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		
		// When mouse exited any buttons, change its appearances to default
		if (e.getSource() == start_button) {
			start_button.setForeground(new Color(92, 255, 230));
			start_button.setBackground(new Color(255, 255, 255, 70));
			start_button.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(92, 255, 230)));
		} else if (e.getSource() == stop_button) {
			stop_button.setForeground(new Color(92, 255, 230));
			stop_button.setBackground(new Color(255, 255, 255, 70));
			stop_button.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(92, 255, 230)));
		} else if (e.getSource() == reset_button) {
			reset_button.setForeground(new Color(92, 255, 230));
			reset_button.setBackground(new Color(255, 255, 255, 70));
			reset_button.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(92, 255, 230)));
		} else if (e.getSource() == exit_button) {
			exit_button.setForeground(new Color(92, 255, 230));
			exit_button.setBackground(new Color(255, 255, 255, 70));
			exit_button.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(92, 255, 230)));
		} else if (e.getSource() == P1_bingo_button) {
			P1_bingo_button.setForeground(new Color(92, 255, 230));
			P1_bingo_button.setBackground(new Color(255, 255, 255, 70));
			P1_bingo_button.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(92, 255, 230)));
		} else if (e.getSource() == P2_bingo_button) {
			P2_bingo_button.setForeground(new Color(92, 255, 230));
			P2_bingo_button.setBackground(new Color(255, 255, 255, 70));
			P2_bingo_button.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(92, 255, 230)));
		}

	}
}
