import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class Player_Panel extends JPanel{
	
	private Color primary_color = new Color(92, 255, 230);
	private Color background_color = new Color(0, 0, 0, 90);
	private String Player;
	private Color player_color;
	
	Player_Panel(String Player , Color player_color){
		this.Player = Player;
		this.player_color = player_color;
		this.setLayout(null);
		this.setOpaque(false);
		
	}
	
	public void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g);
		
		// anti-aliasing
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		// draw background transparent box
		g2d.setColor(new Color(255,255,255));
		g2d.fillRect(0, 30, 370, 103);
		g2d.setColor(player_color);
		g2d.fillRect(0, 123, 370, 10);
		g2d.setColor(background_color);
		g2d.fillRect(0, 140, 370, 93);
		
		// draw Player words
		g2d.setFont(new Font("Dialog", Font.BOLD, 70));
		g2d.setColor(player_color);
		g2d.drawString(Player, 50, 100);
		
		
		// draw BINGO words
		g2d.setFont(new Font("Dialog", Font.BOLD, 70));
		g2d.setColor(primary_color);
		g2d.drawString("B", 10, 210);
		g2d.drawString("I", 100, 210);
		g2d.drawString("N", 160, 210);
		g2d.drawString("G", 234, 210);
		g2d.drawString("O", 308, 210);
		
	}
}
