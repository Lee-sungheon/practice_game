package dynamic_beats_01;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {

	private Image gameInfoImage = new ImageIcon(Main.class.getResource("images/gameInfo.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("images/judgementLine.png")).getImage();
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("images/noteRouteLine.png")).getImage();
	private Image blueFlareImage;
	private Image judgeImage;

	private Image noteRouteAImage = new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();
	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();
	private Image noteRouteHImage = new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();

	private Image keyPadAImage = new ImageIcon(Main.class.getResource("images/keyPadBasic.png")).getImage();
	private Image keyPadSImage = new ImageIcon(Main.class.getResource("images/keyPadBasic.png")).getImage();
	private Image keyPadDImage = new ImageIcon(Main.class.getResource("images/keyPadBasic.png")).getImage();
	private Image keyPadFImage = new ImageIcon(Main.class.getResource("images/keyPadBasic.png")).getImage();
	private Image keyPadHImage = new ImageIcon(Main.class.getResource("images/keyPadBasic.png")).getImage();
	private Image keyPadJImage = new ImageIcon(Main.class.getResource("images/keyPadBasic.png")).getImage();
	private Image keyPadKImage = new ImageIcon(Main.class.getResource("images/keyPadBasic.png")).getImage();
	private Image keyPadLImage = new ImageIcon(Main.class.getResource("images/keyPadBasic.png")).getImage();

	private String combo = "0";
	private String titleName;
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;
	private int score = 0;
	private int index;

//	private boolean gameMaker = true;

	ArrayList<Note> noteList = new ArrayList<>();

	Beat[] beats = null;
	
	public Game(String titleName, String difficulty, String musicTitle) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);
	}

	public void screenDraw(Graphics2D g) {

		g.drawImage(noteRouteAImage, 228, 30, null);
		g.drawImage(noteRouteSImage, 332, 30, null);
		g.drawImage(noteRouteDImage, 436, 30, null);
		g.drawImage(noteRouteFImage, 540, 30, null);
		g.drawImage(noteRouteHImage, 640, 30, null);
		g.drawImage(noteRouteJImage, 744, 30, null);
		g.drawImage(noteRouteKImage, 848, 30, null);
		g.drawImage(noteRouteLImage, 952, 30, null);

		for (int i = 0; i < 4; i++) {
			g.drawImage(noteRouteLineImage, 224 + 104 * i, 30, null);
		}
		for (int i = 0; i < 4; i++) {
			g.drawImage(noteRouteLineImage, 636 + 104 * i, 30, null);
		}
		g.drawImage(noteRouteLineImage, 1052, 30, null);

		g.drawImage(gameInfoImage, 0, 660, null);
		g.drawImage(judgementLineImage, 0, 580, null);

		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (note.getY() > 660) {
				judgeImage = new ImageIcon(Main.class.getResource("images/miss.png")).getImage();
				combo = "0";
			}
			if (!note.isProceeded()) {
				noteList.remove(i);
				i--;
			} else {
				note.screenDraw(g);
			}
		}

		g.drawImage(blueFlareImage, -85, 130, null);
		g.drawImage(judgeImage, 460, 420, null);

		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); // 占쌔쏙옙트 占쏙옙占쏙옙 占쏙옙占쏙옙
		g.setFont(new Font("占쏙옙占쏙옙", Font.BOLD, 30));
		g.drawString(titleName, 20, 700);
		g.drawString(difficulty, 1190, 700);

		if (combo != "0") {
			g.setColor(Color.orange);
			g.setFont(new Font("Elephant", Font.BOLD, 60));
			g.drawString(combo + "Combo", 510, 400);
		}

		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.drawString("000" + Integer.toString(score), 550, 700);
		g.setFont(new Font("Arial", Font.BOLD, 26));
		g.setColor(Color.DARK_GRAY);
		g.drawString("A", 270, 609);
		g.drawString("S", 374, 609);
		g.drawString("D", 478, 609);
		g.drawString("F", 582, 609);
		g.drawString("H", 686, 609);
		g.drawString("J", 790, 609);
		g.drawString("K", 894, 609);
		g.drawString("L", 998, 609);

		g.drawImage(keyPadAImage, 228, 580, null);
		g.drawImage(keyPadSImage, 332, 580, null);
		g.drawImage(keyPadDImage, 436, 580, null);
		g.drawImage(keyPadFImage, 540, 580, null);
		g.drawImage(keyPadHImage, 640, 580, null);
		g.drawImage(keyPadJImage, 744, 580, null);
		g.drawImage(keyPadKImage, 848, 580, null);
		g.drawImage(keyPadLImage, 952, 580, null);

	}

	public void pressA() {
		noteRouteAImage = new ImageIcon(Main.class.getResource("images/noteRoutePressed.png")).getImage();
		keyPadAImage = new ImageIcon(Main.class.getResource("images/keyPadPressed.png")).getImage();
		Music drumMusic = new Music("drumSound1.mp3", false);
		drumMusic.start();
//		if (gameMaker == true) {
//			System.out.println(gameMusic.getTime() + " A");
//		}
		judge("A");
	}

	public void releaseA() {
		noteRouteAImage = new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();
		keyPadAImage = new ImageIcon(Main.class.getResource("images/keyPadBasic.png")).getImage();
	}

	public void pressS() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("images/noteRoutePressed.png")).getImage();
		keyPadSImage = new ImageIcon(Main.class.getResource("images/keyPadPressed.png")).getImage();
		Music drumMusic = new Music("drumSound1.mp3", false);
		drumMusic.start();
//		if (gameMaker == true) {
//			System.out.println(gameMusic.getTime() + " S");
//		}
		judge("S");
	}

	public void releaseS() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();
		keyPadSImage = new ImageIcon(Main.class.getResource("images/keyPadBasic.png")).getImage();
	}

	public void pressD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("images/noteRoutePressed.png")).getImage();
		keyPadDImage = new ImageIcon(Main.class.getResource("images/keyPadPressed.png")).getImage();
		Music drumMusic = new Music("drumSound1.mp3", false);
		drumMusic.start();
//		if (gameMaker == true) {
//			System.out.println(gameMusic.getTime() + " D");
//		}
		judge("D");
	}

	public void releaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();
		keyPadDImage = new ImageIcon(Main.class.getResource("images/keyPadBasic.png")).getImage();
	}

	public void pressF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("images/noteRoutePressed.png")).getImage();
		keyPadFImage = new ImageIcon(Main.class.getResource("images/keyPadPressed.png")).getImage();
		Music drumMusic = new Music("drumSound1.mp3", false);
		drumMusic.start();
//		if (gameMaker == true) {
//			System.out.println(gameMusic.getTime() + " F");
//		}
		judge("F");
	}

	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();
		keyPadFImage = new ImageIcon(Main.class.getResource("images/keyPadBasic.png")).getImage();
	}

	public void pressH() {
		noteRouteHImage = new ImageIcon(Main.class.getResource("images/noteRoutePressed.png")).getImage();
		keyPadHImage = new ImageIcon(Main.class.getResource("images/keyPadPressed.png")).getImage();
		Music drumMusic = new Music("drumSound1.mp3", false);
		drumMusic.start();
//		if (gameMaker == true) {
//			System.out.println(gameMusic.getTime() + " H");
//		}
		judge("H");
	}

	public void releaseH() {
		noteRouteHImage = new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();
		keyPadHImage = new ImageIcon(Main.class.getResource("images/keyPadBasic.png")).getImage();
	}

	public void pressJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("images/noteRoutePressed.png")).getImage();
		keyPadJImage = new ImageIcon(Main.class.getResource("images/keyPadPressed.png")).getImage();
		Music drumMusic = new Music("drumSound1.mp3", false);
		drumMusic.start();
//		if (gameMaker == true) {
//			System.out.println(gameMusic.getTime() + " J");
//		}
		judge("J");
	}

	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();
		keyPadJImage = new ImageIcon(Main.class.getResource("images/keyPadBasic.png")).getImage();
	}

	public void pressK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("images/noteRoutePressed.png")).getImage();
		keyPadKImage = new ImageIcon(Main.class.getResource("images/keyPadPressed.png")).getImage();
		Music drumMusic = new Music("drumSound1.mp3", false);
		drumMusic.start();
//		if (gameMaker == true) {
//			System.out.println(gameMusic.getTime() + " K");
//		}
		judge("K");
	}

	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();
		keyPadKImage = new ImageIcon(Main.class.getResource("images/keyPadBasic.png")).getImage();
	}

	public void pressL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("images/noteRoutePressed.png")).getImage();
		keyPadLImage = new ImageIcon(Main.class.getResource("images/keyPadPressed.png")).getImage();
		Music drumMusic = new Music("drumSound1.mp3", false);
		drumMusic.start();
//		if (gameMaker == true) {
//			System.out.println(gameMusic.getTime() + " L");
//		}
		judge("L");
	}

	public void releaseL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();
		keyPadLImage = new ImageIcon(Main.class.getResource("images/keyPadBasic.png")).getImage();
	}

	@Override
	public void run() {
		try {
			dropNotes(this.titleName);
		} catch (Exception e) {
		}
	}

	public void close() {
		gameMusic.close();
		this.interrupt();
	}

	public void dropNotes(String titleName) throws Exception {

		if (titleName.equals("조정석 - 아로하") && difficulty.equals("EASY")) {
			noteMake();
		}
		
		else if (titleName.equals("조정석 - 아로하") && difficulty.equals("HARD")) {
			noteMake();
		}
		
		else if (titleName.equals("Apink - 덤더럼") && difficulty.equals("EASY")) {
			noteMake();
		} 
		else if (titleName.equals("Apink - 덤더럼") && difficulty.equals("HARD")) {
			noteMake();
		}

		else if (titleName.equals("가호 (Gaho) - 시작") && difficulty.equals("EASY")) {
			noteMake();
		}
		else if (titleName.equals("가호 (Gaho) - 시작") && difficulty.equals("HARD")) {
			noteMake();
		}

		int i = 0;
		try {
			Thread.sleep(1500);
		} catch (Exception e) {
			e.printStackTrace();
		}
		gameMusic.start();
		while (i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			if (beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			if (!dropped) {
				try {
					Thread.sleep(5);
				} catch (Exception e) {
				}
			}
		}

	}

	public void judge(String input) {
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (input.equals(note.getNoteType())) {
				judgeEvent(note.judge());
				break;
			}
		}
	}

	public void judgeEvent(String judge) {
		if (!judge.equals("None")) {
			blueFlareImage = new ImageIcon(Main.class.getResource("images/blueFlare.png")).getImage();
		}
		if (judge.equals("Miss")) {
			judgeImage = new ImageIcon(Main.class.getResource("images/miss.png")).getImage();
		} else if (judge.equals("Late")) {
			judgeImage = new ImageIcon(Main.class.getResource("images/late.png")).getImage();
			score += 10;
			combo = "0";
		} else if (judge.equals("Good")) {
			judgeImage = new ImageIcon(Main.class.getResource("images/good.png")).getImage();
			score += 30;
			int com = Integer.parseInt(combo);
			com++;
			combo = Integer.toString(com);
		} else if (judge.equals("Great")) {
			judgeImage = new ImageIcon(Main.class.getResource("images/great.png")).getImage();
			score += 50;
			int com = Integer.parseInt(combo);
			com++;
			combo = Integer.toString(com);
		} else if (judge.equals("Perfect")) {
			judgeImage = new ImageIcon(Main.class.getResource("images/perfect.png")).getImage();
			score += 100;
			int com = Integer.parseInt(combo);
			com++;
			combo = Integer.toString(com);
		} else if (judge.equals("Early")) {
			judgeImage = new ImageIcon(Main.class.getResource("images/early.png")).getImage();
			score += 10;
			combo = "0";
		}
	}

	public void index(String titleName, String difficulty) throws Exception {
		int index = 0;
		BufferedReader br = new BufferedReader(
				new FileReader("C:\\Users\\HONEY\\eclipse-workspace\\dynamic_beats\\src\\dynamic_beats_01\\text\\"
						+ titleName + difficulty + ".txt"));
		String str = "";
		while ((str = br.readLine()) != null) {
			index++;
		}
		this.index = index;
		br.close();
	}
	
	public void noteMake() throws Exception {
		index(titleName, difficulty);
		int[] time = new int[this.index];
		String[] noteType = new String[this.index];
		int i = 0;
		BufferedReader br = new BufferedReader(
				new FileReader("C:\\Users\\HONEY\\eclipse-workspace\\dynamic_beats\\src\\dynamic_beats_01\\text\\" + titleName
						+ difficulty + ".txt"));
		String str = "";
		while ((str = br.readLine()) != null) {
			String[] tmp = str.split(" ");
			String st = tmp[0];
			int k = Integer.parseInt(st);
			time[i] = k;
			noteType[i] = tmp[1];
			i++;
		}
		br.close();

		beats = new Beat[index];
		for (int k = 0; k < index; k++) {
			beats[k] = new Beat(time[k], noteType[k]);
		}
	}
}
