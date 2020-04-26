package dynamic_beats_01;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame {

	private static final long serialVersionUID = 1L;
	private Image screenImage;
	private Graphics screenGraphic;
	private int mouseX, mouseY;
	private String difficulty;

	private Image background = new ImageIcon(Main.class.getResource("images/intro_background.jpg")).getImage();
	private Image startBackground = new ImageIcon(Main.class.getResource("images/start_background.jpg")).getImage();
	
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("images/menuBar.png")));

	private ImageIcon exitButtonentered = new ImageIcon(Main.class.getResource("images/exitButtonentered.png"));
	private ImageIcon exitButtonBasic = new ImageIcon(Main.class.getResource("images/exitButtonBasic.png"));
	private ImageIcon startButtonBasic = new ImageIcon(Main.class.getResource("images/startButtonBasic2.png"));
	private ImageIcon startButtonEntered = new ImageIcon(Main.class.getResource("images/startButtonEntered2.png"));
	private ImageIcon quitButtonBasic = new ImageIcon(Main.class.getResource("images/quitButtonBasic2.png"));
	private ImageIcon quitButtonEntered = new ImageIcon(Main.class.getResource("images/quitButtonEntered2.png"));
	private ImageIcon rightButtonBasic = new ImageIcon(Main.class.getResource("images/rightButtonBasic.png"));
	private ImageIcon rightButtonEntered = new ImageIcon(Main.class.getResource("images/rightButtonEntered.png"));
	private ImageIcon leftButtonBasic = new ImageIcon(Main.class.getResource("images/leftButtonBasic.png"));
	private ImageIcon leftButtonEntered = new ImageIcon(Main.class.getResource("images/leftButtonEntered.png"));
	private ImageIcon easyButtonBasic = new ImageIcon(Main.class.getResource("images/easyButtonBasic.png"));
	private ImageIcon easyButtonEntered = new ImageIcon(Main.class.getResource("images/easyButtonEntered.png"));
	private ImageIcon hardButtonBasic = new ImageIcon(Main.class.getResource("images/hardButtonBasic.png"));
	private ImageIcon hardButtonEntered = new ImageIcon(Main.class.getResource("images/hardButtonEntered.png"));
	private ImageIcon backButtonBasic = new ImageIcon(Main.class.getResource("images/backButtonBasic.png"));
	private ImageIcon backButtonEntered = new ImageIcon(Main.class.getResource("images/backButtonEntered.png"));

	private JButton exitButton = new JButton(exitButtonBasic);
	private JButton startButton = new JButton(startButtonBasic);
	private JButton quitButton = new JButton(quitButtonBasic);
	private JButton rightButton = new JButton(rightButtonBasic);
	private JButton leftButton = new JButton(leftButtonBasic);
	private JButton easyButton = new JButton(easyButtonBasic);
	private JButton hardButton = new JButton(hardButtonBasic);
	private JButton backButton = new JButton(backButtonBasic);

	private boolean isMainScreen = false;
	private boolean isGameScreen = false;

	private ArrayList<Track> trackList = new ArrayList<>();

	private Music introMusic = new Music("introMusic.mp3", true);
	
	private Music selectedMusic;
	private Image selectedImage;
	private Image titleImage;
	private int nowSelected = 0;

	public static Game game;
	
	public DynamicBeat() {
		trackList.add(new Track("gahoTitle.png", "가호시작intro.jpg", "가호시작main.jpg", "004 가호 (Gaho) - 시작 selected.mp3",
				"004 가호 (Gaho) - 시작.mp3", "가호 (Gaho) - 시작"));
		trackList.add(new Track("choTitle.png", "조정석아로하intro.jpg", "조정석아로하main.jpg", "002 조정석 - 아로하 selected.mp3",
				"002 조정석 - 아로하.mp3", "조정석 - 아로하"));
		trackList.add(new Track("apinkTitle.png", "에이핑크덤더럼intro.jpg", "에이핑크덤더럼main.jpg",
				"001 Apink - 덤더럼 selected.mp3", "001 Apink - 덤더럼.mp3", "Apink - 덤더럼"));
				
		setUndecorated(true);
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);

		addKeyListener(new dynamic_beats_01.KeyListener());
		
		introMain();

		
				exitButton.setBounds(1250, 0, 30, 30);
				exitButton.setBorderPainted(false);
				exitButton.setContentAreaFilled(false);
				exitButton.setFocusPainted(false);
				exitButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						exitButton.setIcon(exitButtonentered);
						exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
						Music buttonEnteredMusic = new Music("buttonEntered.mp3", false);
						buttonEnteredMusic.start();
					}

					@Override
					public void mouseExited(MouseEvent e) {
						exitButton.setIcon(exitButtonBasic);
						exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}

					@Override
					public void mousePressed(MouseEvent e) {
						Music buttonBasicMusic = new Music("buttonBasic.mp3", false);
						buttonBasicMusic.start();
						try {
							Thread.sleep(500);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						System.exit(0);
					}

				});

				add(exitButton);
				
		
				menuBar.setBounds(0, 0, 1280, 30);
				menuBar.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						mouseX = e.getX();
						mouseY = e.getY();
					}
				});
				menuBar.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseDragged(MouseEvent e) {
						int x = e.getXOnScreen();
						int y = e.getYOnScreen();
						setLocation(x - mouseX, y - mouseY);
					}
				});
				add(menuBar);

		
		startButton.setBounds(800, 420, 400, 100);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEntered);
				startButton.setBounds(796, 420, 400, 100);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEntered.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasic);
				startButton.setBounds(800, 420, 400, 100);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonBasicMusic = new Music("buttonBasic.mp3", false);
				buttonBasicMusic.start();
				selectedMain();
			}

		});

		add(startButton);

		
		quitButton.setBounds(800, 550, 400, 100);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setBounds(796, 550, 400, 100);
				quitButton.setIcon(quitButtonEntered);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEntered.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setBounds(800, 550, 400, 100);
				quitButton.setIcon(quitButtonBasic);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonBasicMusic = new Music("buttonBasic.mp3", false);
				buttonBasicMusic.start();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

				System.exit(0);
			}

		});

		add(quitButton);

		
		leftButton.setBounds(280, 330, 60, 60);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEntered);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEntered.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasic);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonBasicMusic = new Music("buttonBasic.mp3", false);
				buttonBasicMusic.start();
				selectLeft();
			}
		});

		add(leftButton);

		
		rightButton.setBounds(950, 330, 60, 60);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEntered);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEntered.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasic);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonBasicMusic = new Music("buttonBasic.mp3", false);
				buttonBasicMusic.start();
				selectRight();
			}
		});

		add(rightButton);

		
		easyButton.setBounds(370, 640, 250, 67);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEntered);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEntered.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasic);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonBasicMusic = new Music("buttonBasic.mp3", false);
				buttonBasicMusic.start();
				difficulty = "EASY";
				gameStart(nowSelected, difficulty);
			}
		});

		add(easyButton);

		
		hardButton.setBounds(660, 640, 250, 67);
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardButtonEntered);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEntered.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasic);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonBasicMusic = new Music("buttonBasic.mp3", false);
				buttonBasicMusic.start();
				difficulty = "HARD";
				gameStart(nowSelected, difficulty);
			}
		});

		add(hardButton);

		
		backButton.setBounds(10, 30, 60, 60);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEntered);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEntered.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasic);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonBasicMusic = new Music("buttonBasic.mp3", false);
				buttonBasicMusic.start();
				backMain();
			}
		});

		add(backButton);

	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D) screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null); 
		if (isMainScreen) {
			g.drawImage(selectedImage, 370, 40, null);
			g.drawImage(titleImage, 345, 520, null);
		}
		
		if (isGameScreen) {
			game.screenDraw(g);
		}
		
		paintComponents(g); 
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.repaint();
	}

	public void introMain() {
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		backButton.setVisible(false);
		introMusic.start();
	}
	
	public void selectedMain() {
		background = startBackground;
		startButton.setVisible(false);
		quitButton.setVisible(false);
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		isMainScreen = true;
		introMusic.close();
		selectTrack(0);
	}
	
	public void selectTrack(int nowSelected) {
		if (selectedMusic != null) {
			selectedMusic.close();
		}
		titleImage = new ImageIcon(Main.class.getResource("images/" + trackList.get(nowSelected).getTitleImage()))
				.getImage();
		selectedImage = new ImageIcon(Main.class.getResource("images/" + trackList.get(nowSelected).getStartImage()))
				.getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start();
	}

	public void selectLeft() {
		if (nowSelected == 0)
			nowSelected = trackList.size() - 1;
		else
			nowSelected--;
		selectTrack(nowSelected);
	}

	public void selectRight() {
		if (nowSelected == trackList.size() - 1)
			nowSelected = 0;
		else
			nowSelected++;
		selectTrack(nowSelected);
	}

	public void gameStart(int nowSelected, String difficulty) {
		if (selectedMusic != null) {
			selectedMusic.close();
		}
		isMainScreen = false;
		background = new ImageIcon(Main.class.getResource("images/" + trackList.get(nowSelected).getGameImage()))
				.getImage();
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		backButton.setVisible(true);
		isGameScreen = true;
		game = new Game(trackList.get(nowSelected).getTitleName(), difficulty, trackList.get(nowSelected).getGameMusic());
		game.start();
		setFocusable(true);			
	}
	
	public void backMain() {
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		backButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("images/start_background.jpg")).getImage();
		selectTrack(nowSelected);
		isGameScreen = false;
		game.close();
	}
}
