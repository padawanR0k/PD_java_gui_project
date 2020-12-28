import java.awt.Color;
import java.awt.EventQueue;
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
import java.util.Iterator;
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
	ImageIcon[] posterList;
	ArrayList<Integer> MovieIds;
	user my;
	final int POSTER_GUTTER = 256;
	final int POSTER_WIDTH = 230;
	final int POSTER_HEIGHT = 328;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new main();
					mainFrame window = new mainFrame(new user("test"));
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public mainFrame(user my) {
		this.my = my;
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
		//Arrays.sort(fileNames);

		List movies = this.getMovies();
		saveImg s = new saveImg();

		// 불러온 영화의 포스터가 로컬에 없으면 다운로드한다.
		Consumer<Map<String, Object>> downloadImages = n -> {
			String poster = (String) n.get("poster");
			int MovieId = (int) n.get("MovieId");
			String filename = "poster_" + ((Integer)MovieId).toString() + ".jpg";

			File file = new File(folderPath + filename);

			if (file.exists() == false) {
				try {
					int result = s.saveImgFromUrlToLocal(poster, MovieId);
					if (result == 1) {
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

		int size = main.posterList.size();
		this.posterList = main.posterList.values().toArray(new ImageIcon[size]);
		this.MovieIds = new ArrayList<>();
		Iterator it =  main.posterList.entrySet().iterator();

		int i = 0;
		while (it.hasNext()) {
			Map.Entry<Integer, ImageIcon> p = (Map.Entry<Integer, ImageIcon>)it.next();
			int id =  p.getKey();
			ImageIcon value = p.getValue();
			this.posterList[i] = value;
			this.MovieIds.add(id);
			i = i+1;
		}

		poster_num = new File("./image/poster/").listFiles().length;
		page_max = (int) Math.ceil((double) poster_num / 5);


		if (this.posterList.length > 1) {
			for (int j = 0; j < 5; j++) {
				int[] bounds = new int[] { 50 + j * POSTER_GUTTER, 221, POSTER_WIDTH, POSTER_HEIGHT
				};
				this.drawPosterButton(bgPanel, j, bounds, this.posterList[j], this.MovieIds.get(j));
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
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

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

	public void drawPosterButton(ImagePanel bgPanel, int i, int[] bounds, ImageIcon poster, int MovieId) {
		jb[i] = this.makePosterButton(poster, MovieId);
		jb[i].setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
		jb[i].setBackground(Color.BLACK);
		jb[i].setBorderPainted(false);
		jb[i].setFocusPainted(false);
		bgPanel.add(jb[i]);
	}

	public JButton makePosterButton(ImageIcon poster, int MovieId) {
		Icon IMG = resizeIcon(poster, 230, 328);

		JButton btn = new JButton();

		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JButton button = (JButton) e.getSource();
				for (int i = 0; i < 5; i++) {
					if (button.equals(jb[i])) {
						my.setIcon(resizeIcon(posterList[page + i], 400, 570));
						my.setmoviedId(MovieIds.get(page+i));
						reserveFrame s = new reserveFrame(my);
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
				mypageFrame p = new mypageFrame(my);
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
					} else {
						page = page - 5;
						if ((posterList.length - page) < 5) {
							int a = posterList.length - page;
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
					} else {
						page = page + 5;
						if ((posterList.length - page) < 5) {
							int a = posterList.length - page;
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
