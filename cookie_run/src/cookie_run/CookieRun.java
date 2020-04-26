package cookie_run;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class CookieRun {
	public int x, dx, y, dy, nx, nx2, left;
	Image character = new ImageIcon(Main.class.getResource("images/characterMove.gif")).getImage();
	
	static ArrayList<Bullet> bullets;
	
	int ammo = 100;
	
	public CookieRun() {
		x = 75;
		left = 75;
		nx2 = 1265;
		y = 220;
		nx = 0;
		bullets = new ArrayList<Bullet>();
	}
	
	public Rectangle getBounds() {
		return new Rectangle(left, y, 213, 213);
	}
	
	public static ArrayList<Bullet> getBullets() {
		return bullets;
	}
	
	public void fire() {
		if(ammo > 0) {
			ammo--;
			Bullet z = new Bullet(120, (y+154/2));
			bullets.add(z);
		}
		else return;
	}
	
	public void move() {
		x = x + dx;
		nx2 = nx2 + dx;
		nx = nx+ dx;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getdx() {
		return dx;
	}
	
	public Image getImage() {
		return character;
	}
	
	public void keyPressed(KeyEvent e) {		
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_LEFT) {
			character = new ImageIcon(Main.class.getResource("images/characterMove2.gif")).getImage();
			dx = -3;
		}
		
		if(key == KeyEvent.VK_RIGHT) {
			character = new ImageIcon(Main.class.getResource("images/characterMove.gif")).getImage();
			dx = 3;
		}
		
		if(key == KeyEvent.VK_UP) {
			dy = 1;
		}
		
		if (key == KeyEvent.VK_DOWN) {
			character = new ImageIcon(Main.class.getResource("images/characterSliding.png")).getImage();
		}
		
		if (key == KeyEvent.VK_SPACE) {
			character = new ImageIcon(Main.class.getResource("images/characterAttack3.png")).getImage();
			fire();
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_LEFT) {
			dx = 0;
		}
		
		if(key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}
		
		if(key == KeyEvent.VK_UP) {
			dy = 0;
		}
		
		if(key == KeyEvent.VK_DOWN) {
			character = new ImageIcon(Main.class.getResource("images/characterMove.gif")).getImage();
		}
		
		if (key == KeyEvent.VK_SPACE) {
			character = new ImageIcon(Main.class.getResource("images/characterMove.gif")).getImage();
		}
	}
	
	
}
