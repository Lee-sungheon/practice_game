package dynamic_beats_01;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {

	@Override
	public void keyPressed(KeyEvent e) {
		if(DynamicBeat.game == null) {
			return;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_A) {
			DynamicBeat.game.pressA();
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			DynamicBeat.game.pressS();
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			DynamicBeat.game.pressD();
		}
		if (e.getKeyCode() == KeyEvent.VK_F) {
			DynamicBeat.game.pressF();
		}

		if (e.getKeyCode() == KeyEvent.VK_H) {
			DynamicBeat.game.pressH();
		}
		if (e.getKeyCode() == KeyEvent.VK_J) {
			DynamicBeat.game.pressJ();
		}
		if (e.getKeyCode() == KeyEvent.VK_K) {
			DynamicBeat.game.pressK();
		}
		if (e.getKeyCode() == KeyEvent.VK_L) {
			DynamicBeat.game.pressL();
		}

	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A) {
			DynamicBeat.game.releaseA();
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			DynamicBeat.game.releaseS();
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			DynamicBeat.game.releaseD();
		}
		if (e.getKeyCode() == KeyEvent.VK_F) {
			DynamicBeat.game.releaseF();
		}

		if (e.getKeyCode() == KeyEvent.VK_H) {
			DynamicBeat.game.releaseH();
		}
		if (e.getKeyCode() == KeyEvent.VK_J) {
			DynamicBeat.game.releaseJ();
		}
		if (e.getKeyCode() == KeyEvent.VK_K) {
			DynamicBeat.game.releaseK();
		}
		if (e.getKeyCode() == KeyEvent.VK_L) {
			DynamicBeat.game.releaseL();
		}
	}

}
