package cookie_run;

import javax.swing.JFrame;

public class Main {
	
	public Main() {
		JFrame frame = new JFrame("Cookie Run");
		Board board = new Board();
		frame.add(board);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280,720);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		new Main();
	}
   
}
