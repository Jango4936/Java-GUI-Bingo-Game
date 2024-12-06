import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;
import javax.swing.JTextField;

public class Settings extends JPanel implements ActionListener, MouseListener {

	private Color primary_color = new Color(92, 255, 230);
	private Color background_color = new Color(0, 0, 0, 200);
	private Color background_box_color = new Color(255, 255, 255, 30);
	
	static Boolean showCalledNunbers = false;
	
	private JLabel Title;
	private JLabel Options_1;
	private JLabel Options_2;
	private JLabel Options_3;
	private JButton exit_button;
	private JButton opt1switch;
	private JFormattedTextField opt2text;
	private JFormattedTextField opt3text;
	
	private NumberFormat format1;
	private NumberFormatter formatter1;
	private NumberFormat format2;
	private NumberFormatter formatter2;
	

	Settings() {
		// JPanel setup
		this.setPreferredSize(new Dimension(839, 583));
		this.setBackground(background_color);
		this.setLayout(null); // No layout manager
		this.setOpaque(true);
		Font default_font = new Font("Dialog", Font.BOLD, 20);

		// Save & Exit Button Setup
		exit_button = new JButton("Save & Exit");
		exit_button.setBounds(344, 504, 150, 50);
		exit_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exit_button.addActionListener(this);
		exit_button.addMouseListener(this);
		exit_button.setBackground(new Color(255, 255, 255, 70));
		exit_button.setForeground(new Color(92, 255, 230));
		exit_button.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(92, 255, 230)));
		exit_button.setFocusable(false);

		// Title Label Setup
		Title = new JLabel("Settings");
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setBounds(65, 34, 709, 37);
		Title.setBackground(background_color);
		Title.setFont(new Font("Dialog", Font.BOLD, 30));
		Title.setForeground(primary_color);
		Title.setOpaque(true);
		
		// Options 1 Label
		Options_1 = new JLabel(" Highlight previously called numbers when clicked");
		Options_1.setHorizontalAlignment(SwingConstants.LEFT);
		Options_1.setBounds(65, 79, 709, 37);
		Options_1.setBackground(background_box_color);
		Options_1.setFont(default_font);
		Options_1.setForeground(primary_color);
		Options_1.setOpaque(true);
		
		// Options 2 Label
		Options_2 = new JLabel(" Numbers of Call (default = 15 ; min = 1 ; max = 75 )");
		Options_2.setHorizontalAlignment(SwingConstants.LEFT);
		Options_2.setBounds(65, 127, 709, 37);
		Options_2.setBackground(background_box_color);
		Options_2.setFont(default_font);
		Options_2.setForeground(primary_color);
		Options_2.setOpaque(true);
		
		// Options 3 Label
		Options_3 = new JLabel(" Delay (default 3 000ms ; min = 1ms ; max = 10 000ms)");
		Options_3.setBounds(65, 175, 709, 37);
		Options_3.setHorizontalAlignment(SwingConstants.LEFT);
		Options_3.setBackground(background_box_color);
		Options_3.setFont(default_font);
		Options_3.setForeground(primary_color);
		Options_3.setOpaque(true);
		
		// Options 1 Toggle Switch
		opt1switch = new JButton("off");
		opt1switch.setBounds(687, 82, 79, 29);
		opt1switch.addActionListener(this);
		opt1switch.addMouseListener(this);
		opt1switch.setBackground(background_box_color);
		opt1switch.setForeground(primary_color);
		opt1switch.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, primary_color));
		opt1switch.setFocusable(false);
		updateButton(opt1switch, showCalledNunbers);
		
		// Restricting JTextField input to Integers
		format1 = NumberFormat.getInstance();
		formatter1 = new NumberFormatter(format1);
		formatter1.setValueClass(Integer.class);
		formatter1.setMinimum(1);
		formatter1.setMaximum(75);
		formatter1.setAllowsInvalid(false);
		formatter1.setCommitsOnValidEdit(true);
		opt2text = new JFormattedTextField(formatter1);
		
		format2 = NumberFormat.getInstance();
		formatter2 = new NumberFormatter(format2);
		formatter2.setValueClass(Integer.class);
		formatter2.setMinimum(1); // 0.1 second
		formatter2.setMaximum(10000); // 10 second
		formatter2.setAllowsInvalid(false);
		formatter2.setCommitsOnValidEdit(true);
		opt3text = new JFormattedTextField(formatter2);

		// JFormattedTextField Setups
		// Options 2 TextField
		opt2text.setText(String.valueOf(BingoGame.num_of_calllings));
		opt2text.setHorizontalAlignment(SwingConstants.CENTER);
		opt2text.setBounds(687, 130, 79, 29);
		opt2text.addActionListener(this);
		opt2text.addMouseListener(this);
		opt2text.setBackground(background_box_color);
		opt2text.setForeground(primary_color);
		opt2text.setCaretColor(primary_color);
		opt2text.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, primary_color));
		// Options 3 TextField
		opt3text.setText(String.valueOf(BingoGame.DELAY));
		opt3text.setHorizontalAlignment(SwingConstants.CENTER);
		opt3text.setBounds(687, 178, 79, 29);
		opt3text.addActionListener(this);
		opt3text.addMouseListener(this);
		opt3text.setBackground(background_box_color);
		opt3text.setForeground(primary_color);
		opt3text.setCaretColor(primary_color);
		opt3text.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, primary_color));

		// Add All The Components
		this.add(exit_button);
		this.add(Title);
		this.add(Options_1);
		this.add(Options_2);
		this.add(Options_3);
		this.add(opt1switch);
		this.add(opt2text);
		this.add(opt3text);

	}
	
	// Method to toggle the state of a button
	public boolean toggleButton(JButton button, boolean status) {
		if (status == true) {
			button.setForeground(primary_color);
			button.setBackground(new Color(255, 255, 255, 70));
			button.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, primary_color));
			button.setText("Off");
			status = false;
			return status;
		} else if (status == false) {
			button.setForeground(Color.green);
			button.setBackground(new Color(255, 255, 255, 70));
			button.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Color.green));
			button.setText("On");
			status = true;
			return status;
		}
		return status;
	}
	
	// Method to update the appearance of a button based on its status
	public void updateButton(JButton button, boolean status) {
		if (status == true) {
			button.setForeground(Color.green);
			button.setBackground(new Color(255, 255, 255, 70));
			button.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Color.green));
			button.setText("On");
		} else if (status == false) {
			button.setForeground(primary_color);
			button.setBackground(new Color(255, 255, 255, 70));
			button.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, primary_color));
			button.setText("Off");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exit_button) {
			// Save settings and exit
			BingoGame.num_of_calllings = Integer.parseInt(opt2text.getText());
			BingoGame.DELAY = Integer.parseInt((opt3text.getText()).replaceAll(",", ""));
			this.setVisible(false);
			MainMenu.setMainMenuUI(true);
		} else if (e.getSource() == opt1switch) {
			// Toggle option 1
			showCalledNunbers = toggleButton(opt1switch, showCalledNunbers);
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
		// When mouse exited any buttons or text field, change its appearances
		if (e.getSource() == exit_button) {
			exit_button.setForeground(new Color(206, 32, 107));
			exit_button.setBackground(Color.white);
			exit_button.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(206, 32, 107)));
			
		} else if (e.getSource() == opt1switch) {
			opt1switch.setForeground(new Color(206, 32, 107));
			opt1switch.setBackground(Color.white);
			opt1switch.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(206, 32, 107)));

		} else if (e.getSource() == opt2text) {
			opt2text.setForeground(new Color(206, 32, 107));
			opt2text.setBackground(Color.white);
			opt2text.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(206, 32, 107)));
			opt2text.setCaretColor(new Color(206, 32, 107));

		} else if (e.getSource() == opt3text) {
			opt3text.setForeground(new Color(206, 32, 107));
			opt3text.setBackground(Color.white);
			opt3text.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(206, 32, 107)));
			opt3text.setCaretColor(new Color(206, 32, 107));

		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// When mouse exited any buttons or text field, change its appearances to default
		if (e.getSource() == exit_button) {
			exit_button.setForeground(primary_color);
			exit_button.setBackground(new Color(255, 255, 255, 70));
			exit_button.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, primary_color));
		} else if (e.getSource() == opt1switch) {
			if (showCalledNunbers == true) {
				opt1switch.setForeground(Color.green);
				opt1switch.setBackground(new Color(255, 255, 255, 70));
				opt1switch.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Color.green));
			} else {
				opt1switch.setForeground(primary_color);
				opt1switch.setBackground(new Color(255, 255, 255, 70));
				opt1switch.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, primary_color));
			}
		} else if (e.getSource() == opt2text) {
			opt2text.setForeground(primary_color);
			opt2text.setBackground(new Color(255, 255, 255, 70));
			opt2text.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, primary_color));
			opt2text.setCaretColor(primary_color);
		} else if (e.getSource() == opt3text) {
			opt3text.setForeground(primary_color);
			opt3text.setBackground(new Color(255, 255, 255, 70));
			opt3text.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, primary_color));
			opt3text.setCaretColor(primary_color);
		}

	}
}
