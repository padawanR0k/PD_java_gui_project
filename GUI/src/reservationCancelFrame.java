import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import DB.DB;

import javax.swing.JButton;

public class reservationCancelFrame {
	private final Image BG_IMAGE = new ImageIcon("./image/bg_checkFrame(cancel).jpg").getImage();
	private JFrame frame;
	private JLabel title;
	private JLabel detail;
	private JLabel count;
	private JLabel seats;
	private JLabel poster;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					reservationCancelFrame window = new reservationCancelFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public reservationCancelFrame() {
		initialize();
		this.getResverationInfo();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();

		ImagePanel bgPanel = new ImagePanel(this.BG_IMAGE);
		frame.setSize(bgPanel.getWidth(), bgPanel.getHeight());
		frame.getContentPane().add(bgPanel);
		bgPanel.setLayout(null);

		this.drawInfoButton(bgPanel);
		this.drawCheckButton(bgPanel);
		this.drawCancelButton(bgPanel);
		this.drawBackButton(bgPanel);
		this.drawReservInfo(bgPanel);

		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void getResverationInfo() {
		DB db = new DB();
		String query = """
		select * from heroku_dcf5f8a801138d1.reservation as res
			join heroku_dcf5f8a801138d1.screening as scr ON scr.ScreeningId = res.ScreeningId
    	join heroku_dcf5f8a801138d1.movie as mv ON mv.Movieid = scr.MovieId
		where
			groupId = 1
		""";
		List<Map<String, Object>> res = db.query(query);
		Map movie =  res.get(0);
		String title = (String) movie.get("title");
		String time = (String) movie.get("time");
		String openDate = (String) movie.get("openDate");
		Integer count = res.size();
		String seats = "";
		for(Map<String, Object> m: res) {
			String s =  (String)m.get("seatId");
			seats += s + ", ";
		}
		this.title.setText(title);
		this.detail.setText(openDate + " | " + time);
		this.count.setText(count.toString());
		this.seats.setText(seats);
	}

	public void drawInfoButton(ImagePanel bgPanel) {
		JButton button = this.makeImageButton("info1.jpg", "info2.jpg");
		button.setBounds(100, 360, 225, 54);
		button.setBorderPainted(false);
		button.setFocusPainted(false);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "My Information");
			}
		});

		bgPanel.add(button);
	}

	public void drawCheckButton(ImagePanel bgPanel) {
		JButton button = this.makeImageButton("check1.jpg", "check2.jpg");
		button.setBounds(100, 450, 225, 54);
		button.setBorderPainted(false);
		button.setFocusPainted(false);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Check Reservation");
			}
		});

		bgPanel.add(button);
	}

	public void drawCancelButton(ImagePanel bgPanel) {
		JButton button = this.makeImageButton("cancel1.jpg", "cancel2.jpg");
		button.setBounds(765, 570, 225, 54);
		button.setBorderPainted(false);
		button.setFocusPainted(false);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Check Reservation");
			}
		});

		bgPanel.add(button);
	}

	public void drawBackButton(ImagePanel bgPanel) {
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("./image/back_btn.png"));
		button.setBounds(1245, 10, 85, 85);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setBackground(Color.WHITE);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame m = new mainFrame();
				m.setVisible(true);
				frame.dispose();
			}
		});

		bgPanel.add(button);
	}

	public void drawReservInfo(ImagePanel bgPanel) {
		title = new JLabel("영화제목");
		detail = new JLabel("세부정보");
		count = new JLabel("2");
		seats = new JLabel("J4 J5");

		// ImageIcon icon = new
		// ImageIcon("https://img.cgv.co.kr/Movie/Thumbnail/Poster/000083/83854/83854_320.jpg");
		poster = new JLabel();

		poster.setOpaque(true);
		poster.setBackground(new Color(0, 0, 0));
		title.setBounds(780, 200, 200, 50);
		detail.setBounds(780, 250, 400, 50);
		count.setBounds(654, 416, 50, 50);
		seats.setBounds(774, 438, 100, 50);
		poster.setBounds(640, 195, 120, 160);

		this.setFontWeight(title, TextAttribute.WEIGHT_SEMIBOLD);
		this.setFontWeight(detail, TextAttribute.WEIGHT_SEMIBOLD);
		this.setFontWeight(seats, TextAttribute.WEIGHT_BOLD);
		this.setFontWeight(count, TextAttribute.WEIGHT_EXTRABOLD);
		this.setFontSize(title, 30);
		this.setFontSize(detail, 24);
		this.setFontSize(seats, 32);
		this.setFontSize(count, 40);

		bgPanel.add(title);
		bgPanel.add(detail);
		bgPanel.add(count);
		bgPanel.add(seats);
		bgPanel.add(poster);
	}

	public void setFontWeight(JLabel label, Float weightBold) {
		Font font = label.getFont();
		font = font.deriveFont(Collections.singletonMap(TextAttribute.WEIGHT, weightBold));
		label.setFont(font);
	}

	public void setFontSize(JLabel label, int size) {
		Font font = label.getFont();
		font = font.deriveFont(Collections.singletonMap(TextAttribute.SIZE, size));
		label.setFont(font);
	}

	/**
	 * 이미지 버튼 생성
	 *
	 * @param img      기본 이미지명
	 * @param hoverImg hover 이미지명
	 * @return
	 */
	public JButton makeImageButton(String img, String hoverImg) {
		Icon IMG = new ImageIcon("./image/btn/" + img);
		Icon IMG_HOVER = new ImageIcon("./image/btn/" + hoverImg);
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
