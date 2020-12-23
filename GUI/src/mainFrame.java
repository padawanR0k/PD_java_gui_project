
// import main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import DB.DB;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.awt.event.ActionEvent;

public class mainFrame {
	private JFrame frame;
	JButton[] jb;
	ImagePanel bgPanel = new ImagePanel(new ImageIcon("./image/bg_mainFrame.jpg").getImage());
	int page = 0;
	int page_max;
	int poster_num;
	ArrayList fileList;
	ImageIcon[] posterList;

	final int POSTER_GUTTER = 256;
	final int POSTER_WIDTH = 230;
	final int POSTER_HEIGHT = 328;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new main();
					mainFrame window = new mainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public mainFrame() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		jb = new JButton[5];
		for (int i = 0; i < 5; i++) {
			jb[i] = new JButton();
		}
		frame.setSize(bgPanel.getWidth(), bgPanel.getHeight());
		frame.getContentPane().add(bgPanel);
		bgPanel.setLayout(null);

		this.drawMypageButton(bgPanel);
		this.drawPreviousButton(bgPanel);
		this.drawNextButton(bgPanel);

		String folderPath = "./image/poster/";
		File path = new File(folderPath);
		String[] fileNames = path.list();

		// int count = main.posterList == null ? 0 : main.posterList.length;

		List movies = this.getMovies();
		saveImg s = new saveImg();

		Consumer<Map<String, Object>> downloadImages = n -> {
			String poster = (String) n.get("poster");
			int MovieId = (int) n.get("MovieId");
			String filename = "poster_" + ((Integer)MovieId).toString() + ".jpg";

			File file = new File(folderPath + filename);

			if (file.exists()) {

			} else {
				try {
					int result = s.saveImgFromUrlToLocal(poster, MovieId);
					if (result == 1) {
						System.out.println(filename);
						ImageIcon img = main.resizeIcon(new ImageIcon("./image/poster/" + filename),230,328);
						main.posterList.put(MovieId, img);
						// main.posterList.set(MovieId, img);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

		movies.stream().forEach(downloadImages);

		// posterList = new ImageIcon[count];
		this.posterList = main.posterList.values().toArray(new ImageIcon[main.posterList.size()]);

		poster_num = new File("./image/poster/").listFiles().length;
		page_max = (int) Math.ceil((double) poster_num / 5);


		if (fileNames.length > 1) {
			for (int i = 0; i < 5; i++) {
				int[] bounds = new int[] { 50 + i * POSTER_GUTTER, 221, POSTER_WIDTH, POSTER_HEIGHT };
				String filename = fileNames[page + i];
				System.out.println(filename);

				int MovieId =  Integer.parseInt(filename.split("_")[1].replace(".jpg", ""));
				this.drawPosterButton(bgPanel, i, bounds, filename, MovieId);
			}

		}

		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * 개봉일이 오늘 이전인 영화정보를 가져온다.
	 */
	public List<Map<String, Object>> getMovies() {
		DB db = new DB();
		String today = new SimpleDateFormat("YYYY-MM-DD").format(new Date());

		List<Map<String, Object>> response = db
				.query(String.format("select * from theater.movie where openDate <= '%s' order by openDate desc limit 17;", today));

		return response;
	}

	// resizedHeight = 230, resizedHeight = 328
	private static ImageIcon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
		Image img = icon.getImage();
		Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}

	public void drawPosterButton(ImagePanel bgPanel, int i, int[] bounds, Object object, int MovieId) {
		jb[i] = this.makePosterButton(object, MovieId);
		jb[i].setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
		jb[i].setBackground(Color.BLACK);
		jb[i].setBorderPainted(false);
		jb[i].setFocusPainted(false);
		bgPanel.add(jb[i]);
	}

	public JButton makePosterButton(Object object, int MovieId) {
		Icon IMG = resizeIcon(new ImageIcon("./image/poster/" + object), 230, 328);
		JButton btn = new JButton();

		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JButton button = (JButton) e.getSource();
				for (int i = 0; i < 5; i++) {
					if (button.equals(jb[i])) {
						reserveFrame s = new reserveFrame(resizeIcon(posterList[page + i], 400, 600));
						s.setVisible(true);
						frame.dispose();
						break;
					}
				}
				// ImageIcon clickedMovie = this.posterList.get(MovieId);
				// reserveFrame s = new reserveFrame(resizeIcon(clickedMovie, 400, 600));
				// s.setVisible(true);
				// frame.dispose();
			}
		});

		btn.setIcon(IMG);

		return btn;
	}

	public void drawMypageButton(ImagePanel bgPanel) {
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("./image/user.png"));
		button.setBounds(1245, 15, 85, 85);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setBackground(Color.BLACK);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mypageFrame p = new mypageFrame();
				p.setVisible(true);
				frame.dispose();
			}
		});

		bgPanel.add(button);
	}

	public void drawPreviousButton(ImagePanel bgPanel) {
		JButton button = this.makeImageButton("previous1.png", "previous2.png");
		button.setBounds(563, 631, 90, 90);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setBackground(Color.BLACK);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (button.hasFocus()) {
					if (page == 0) {
						System.out.println("a");
					} else {
						page = page - 5;
						if ((posterList.length - page) < 5) {
							int a = posterList.length - page;
							System.out.println(a);
							int i;
							for (i = 0; i < a; i++) {
								jb[i].setIcon(posterList[page + i]);
							}

							for (int j = a; j < 5; j++) {
								jb[j].setIcon(new ImageIcon());
							}
							return;
						}
						for (int i = 0; i < 5; i++) {
							jb[i].setIcon(posterList[page + i]);
						}
						System.out.println(page);
					}
				}
			}
		});

		button.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				if (page == 0) {
					button.setRolloverIcon(new ImageIcon("./image/previous1.png"));
				} else {
					button.setRolloverIcon(new ImageIcon("./image/previous2.png"));
				}
			}

		});

		bgPanel.add(button);
	}

	public void drawNextButton(ImagePanel bgPanel) {
		JButton button = this.makeImageButton("next1.png", "next2.png");
		button.setBounds(703, 631, 90, 90);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setBackground(Color.BLACK);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (button.hasFocus()) {
					if (page == (page_max - 1) * 5) {
						System.out.println("a");
					} else {
						page = page + 5;
						if ((posterList.length - page) < 5) {
							int a = posterList.length - page;
							System.out.println(a);
							int i;
							for (i = 0; i < a; i++) {
								jb[i].setIcon(posterList[page + i]);
							}

							for (int j = a; j < 5; j++) {
								jb[j].setIcon(new ImageIcon());
							}
							return;
						}
						for (int i = 0; i < 5; i++) {
							jb[i].setIcon(posterList[page + i]);
						}
						System.out.println(page);
					}
				}
			}
		});

		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (page == (page_max - 1) * 5) {
					button.setRolloverIcon(new ImageIcon("./image/next1.png"));
				} else {
					button.setRolloverIcon(new ImageIcon("./image/next2.png"));
				}
			}

		});

		bgPanel.add(button);
	}

	public JButton makeImageButton(String img, String hoverImg) {
		Icon IMG = new ImageIcon("./image/" + img);
		Icon IMG_HOVER = new ImageIcon("./image/" + hoverImg);
		JButton btn = new JButton();

		btn.setIcon(IMG);
		btn.setRolloverIcon(IMG_HOVER);

		return btn;
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	public void dispose() {
		frame.dispose();
	}

}
