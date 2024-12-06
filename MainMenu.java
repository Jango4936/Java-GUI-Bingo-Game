import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MainMenu extends JFrame implements ActionListener,MouseListener {

	ImageIcon TitleLogo = new ImageIcon("Assets/Pictures/BINGO_logo.jpg");
	ImageIcon mmBackground = new ImageIcon("Assets/Pictures/main_menu_background.gif");
	ImageIcon animated_logo = new ImageIcon("Assets/Pictures/BINGO.gif");
	static JButton Start_Button;
	static JButton Settings_Button;
	static JButton Exit_Button;
	static JLabel logo;
	JLabel background = new JLabel("");
	JPanel CenterPanel = new JPanel();
	Settings settings = new Settings();

	//Constructor
	MainMenu() {
		
		// ----------JFrame Setup----------
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		this.setSize(1280, 720);
		this.setTitle("Bingo Game v0.1");
		this.setIconImage(TitleLogo.getImage());
		this.setLocationRelativeTo(null);
		this.getContentPane().add(CenterPanel, BorderLayout.CENTER);
		
		Start_Button = new JButton("Start Game");
		Settings_Button = new JButton("Settings");
		Exit_Button = new JButton("Exit");
		logo = new JLabel("");
		
		// ----------Buttons Setup----------
		Start_Button.setBorder(null);
		Start_Button.setBounds(482, 339, 300, 70);
		Start_Button.setBackground(new Color (255, 255, 255, 70));
		Start_Button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Start_Button.setFont(new Font("Dialog", Font.BOLD, 20));
		Start_Button.setForeground(new Color (92, 255, 230));
		Start_Button.setBorder(BorderFactory.createMatteBorder(0,0,4,0,new Color (92, 255, 230)));
		Start_Button.setFocusable(false);
		Start_Button.setPreferredSize(new Dimension(200, 50));
		Start_Button.addActionListener(this);
		Start_Button.addMouseListener(this);
		
		Settings_Button.setBorder(null);
		Settings_Button.setBounds(482, 430, 300, 70);
		Settings_Button.setBackground(new Color (255, 255, 255, 70));
		Settings_Button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Settings_Button.setFont(new Font("Dialog", Font.BOLD, 20));
		Settings_Button.setForeground(new Color (92, 255, 230));
		Settings_Button.setBorder(BorderFactory.createMatteBorder(0,0,4,0,new Color (92, 255, 230)));
		Settings_Button.setFocusable(false);
		Settings_Button.setPreferredSize(new Dimension(200, 50));
		Settings_Button.addActionListener(this);
		Settings_Button.addMouseListener(this);
		
		Exit_Button.setBorder(null);
		Exit_Button.setBounds(482, 521, 300, 70);
		Exit_Button.setBackground(new Color (255, 255, 255, 70));
		Exit_Button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Exit_Button.setFont(new Font("Dialog", Font.BOLD, 20));
		Exit_Button.setForeground(new Color (92, 255, 230));
		Exit_Button.setBorder(BorderFactory.createMatteBorder(0,0,4,0,new Color (92, 255, 230)));
		Exit_Button.setFocusable(false);
		Exit_Button.setPreferredSize(new Dimension(200, 50));
		Exit_Button.addActionListener(this);
		Exit_Button.addMouseListener(this);
		
		//----------Main Menu Background Scene Setup----------
		background.setBorder(null);
		background.setBounds(0, 0, 1264, 681);
		background.setIcon(mmBackground);
		
		//----------Main Menu Logo Setup----------
		logo.setBounds(372, 44, 520, 240);
		logo.setIcon(animated_logo);
		
		
		//---------Main Panel Setup----------
		// setup center panel and add all the components
		CenterPanel.setBackground(new Color(239, 235, 229));
		CenterPanel.setPreferredSize(new Dimension(200, 200));
		CenterPanel.setLayout(null);
		CenterPanel.add(settings);
		CenterPanel.add(Start_Button);
		CenterPanel.add(Settings_Button);
		CenterPanel.add(Exit_Button);
		CenterPanel.add(logo);
		CenterPanel.add(background);
		
		//----------Settings Layout Setup----------
		settings.setBounds(212, 44, 839, 583);
		settings.setVisible(false);
		
		
		// show Main Menu
		this.setVisible(true);

	}
	
	
	// show or hide all the UI on the Main Menu
	public static void setMainMenuUI(Boolean bool) {
		Start_Button.setVisible(bool);
		Exit_Button.setVisible(bool);
		Settings_Button.setVisible(bool);
		logo.setVisible(bool);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Start game button
		if (e.getSource() == Start_Button) {
			this.setVisible(false);
			this.dispose(); // close current window
			new BingoGame(); // open bingo game
			
		}
		
		// Exit Button, for closing the game
		else if (e.getSource() == Exit_Button) {
			int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Confirm Quit",
					JOptionPane.YES_NO_CANCEL_OPTION); // ask user to close the program
			if (result == JOptionPane.YES_OPTION) {
				this.dispose(); // close current window
				System.exit(0); // terminate program
			}
		}
		
		// Show setting menu for the game
		else if (e.getSource() == Settings_Button) {
			setMainMenuUI(false); // hide MainMenu UI
			settings.setVisible(true); // show setting menu
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
		if (e.getSource()==Start_Button) {
			Start_Button.setForeground(new Color (206, 32, 107));
			Start_Button.setBackground(Color.white);
			Start_Button.setBorder(BorderFactory.createMatteBorder(0,0,4,0,new Color (206, 32, 107)));
		}
		else if (e.getSource()==Settings_Button) {
			Settings_Button.setForeground(new Color (206, 32, 107));
			Settings_Button.setBackground(Color.white);
			Settings_Button.setBorder(BorderFactory.createMatteBorder(0,0,4,0,new Color (206, 32, 107)));
		}
		else if (e.getSource()==Exit_Button) {
			Exit_Button.setForeground(new Color (206, 32, 107));
			Exit_Button.setBackground(Color.white);
			Exit_Button.setBorder(BorderFactory.createMatteBorder(0,0,4,0,new Color (206, 32, 107)));
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		// When mouse exited any buttons, change its appearances to default
		if (e.getSource()==Start_Button) {
			Start_Button.setForeground(new Color (92, 255, 230));
			Start_Button.setBackground(new Color (255, 255, 255, 70));
			Start_Button.setBorder(BorderFactory.createMatteBorder(0,0,4,0,new Color (92, 255, 230)));
		}
		else if (e.getSource()==Settings_Button) {
			Settings_Button.setForeground(new Color (92, 255, 230));
			Settings_Button.setBackground(new Color (255, 255, 255, 70));
			Settings_Button.setBorder(BorderFactory.createMatteBorder(0,0,4,0,new Color (92, 255, 230)));
		}
		else if (e.getSource()==Exit_Button) {
			Exit_Button.setForeground(new Color (92, 255, 230));
			Exit_Button.setBackground(new Color (255, 255, 255, 70));
			Exit_Button.setBorder(BorderFactory.createMatteBorder(0,0,4,0,new Color (92, 255, 230)));
		}
		
	}
}
