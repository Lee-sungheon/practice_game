package cookie_run;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener, Runnable{

	private static final long serialVersionUID = 1L;
	CookieRun ck;
	Image img;
	Timer time;
	Thread animator;
	int vector = 220;
	Enemy en;
	Enemy en2;

	boolean h = false;
	boolean done = false;
	boolean isJump = false;
	boolean lost = false;
	
	static Font font = new Font("SanSerif", Font.BOLD, 24);
	
	public Board() {
		ck = new CookieRun();
		addKeyListener(new AL());
		setFocusable(true);
		ImageIcon i = new ImageIcon(Main.class.getResource("images\\background.png"));
		img = i.getImage();
		time = new Timer(5, this);
		time.start();
		en = new Enemy(1200, 200, "images\\enemy.png");
		en2 = new Enemy(1600, 200, "images\\enemy.png");
	}

	public void actionPerformed(ActionEvent e) {
		checkCollisions();
		ArrayList<Bullet> bullets = CookieRun.getBullets();
		for(int i=0 ; i < bullets.size() ; i++) {
			Bullet m = (Bullet) bullets.get(i);
			if(m.getisVisible() == true) {
				m.move();
			}
			else {
				bullets.remove(i);
			}
		}
		
		ck.move();
		
		if(ck.x > 1200) {
			en.move(ck.getdx());
		}
		if(ck.x > 1600) {
			en2.move(ck.getdx());
		}
		
		repaint();
	}
	
	public void checkCollisions() {
		Rectangle r1 = en.getBounds();
		Rectangle r2 = en2.getBounds();
		ArrayList<Bullet> bullets = CookieRun.getBullets();
		for(int i=0 ; i < bullets.size() ; i++) {
			Bullet m = (Bullet) bullets.get(i);
			Rectangle m1 = m.getBounds();
			if(r1.intersects(m1) && en.getisAlive()) {
				en.isAlive = false;
				m.visible = false;
			}
			else if(r2.intersects(m1) && en2.getisAlive()) {
				en2.isAlive = false;
				m.visible = false;
			}
		}
		
		Rectangle d = ck.getBounds();
		if(d.intersects(r1)&&en.getisAlive() || d.intersects(r2)&&en2.getisAlive()) {
			lost = true;
		}
	}
	
	public void paint(Graphics g) {
		if (lost) {
			System.exit(0);
		}
		if (ck.dy == 1 && isJump == false) {
			animator = new Thread(this);
			animator.start();
			isJump = true;
		}
		
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g.drawImage(img, -ck.nx2, 0, null);
		g.drawImage(img, 2320 - ck.nx2, 0, null);
		if (ck.nx2 > 2320)
			ck.nx2 = 0;
		else if (ck.nx2 < 0)
			ck.nx2 = 2320;
		
		g2d.drawImage(ck.getImage(), 75, vector, null);
		
		ArrayList<Bullet> bullets = CookieRun.getBullets();
		for(int i=0 ; i < bullets.size() ; i++) {
			Bullet m = (Bullet) bullets.get(i);
			g2d.drawImage(m.getImg(), m.getX(), m.getY(), null);
		}
		g2d.setFont(font);
		g2d.setColor(Color.RED);
		g2d.drawString("Ammo left : " + ck.ammo, 1050, 30);
		
		if(ck.x > 1200) {
			if (en.getisAlive() == true) {
				g2d.drawImage(en.getImage(), en.getX(), en.getY(), null);
			}
		}
		if(ck.x > 1600) {
			if (en2.getisAlive() == true) {
				g2d.drawImage(en2.getImage(), en2.getX(), en2.getY(), null);
			}
		}
	}

	public class AL extends KeyAdapter {
		public void keyReleased(KeyEvent e) {
			ck.keyReleased(e);
		}
		public void keyPressed(KeyEvent e) {
			ck.keyPressed(e);
		}
	}

	@Override
	public void run() {
		while(done == false) {
			cycle();
			try {
				Thread.sleep(1);	
			} catch (Exception e) {	}
		}
		done = false;
		h = false;
		isJump = false;
	}
	
	public int getVector() {
		return vector;
	}

	public void cycle() {
		if (h == false)
			vector--;
		if (vector == 50) {
			try {
				Thread.sleep(100);	
			} catch (Exception e) {	}
			h = true;
		}
		if (h == true && vector <= 220) {
			vector++;
		}
		if (vector == 220) {
			done = true;
		}
	}
}
