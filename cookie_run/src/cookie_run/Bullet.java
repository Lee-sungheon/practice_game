package cookie_run;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Bullet {
	
	int x,y;
	Image img;
	boolean visible;
	
	public Bullet(int startX, int startY) {
		x = startX;
		y = startY;
		img = new ImageIcon(Main.class.getResource("images\\bullet.png")).getImage();
		visible = true;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 100, 100);
	}
	
	public int getX() {
		return x;
	} 
	
	public int getY() {
		return y;
	}
	
	public Image getImg() {
		return img;
	}

	public boolean getisVisible() {
		return visible;
	}

	public void move() {
		x = x+4;
		if (x>1280) {
			visible = false;
		}
	}
}
