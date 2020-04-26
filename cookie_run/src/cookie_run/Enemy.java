package cookie_run;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy {
	Image image;
	int x, y;
	boolean isAlive = true;
	
	public Enemy(int startX, int startY, String location) {
		x = startX;
		y = startY;
		ImageIcon i = new ImageIcon(Main.class.getResource(location));
		image = i.getImage(); 
	}
	
	public int getX() {
		return x;
	} 
	
	public int getY() {
		return y;
	}
	
	public Image getImage() {
		return image;
	}
	
	public boolean getisAlive() {
		return isAlive;
	}
	
	public void move(int dx) {
		x = x - dx;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 202, 250);
	}
}
