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
	private String groupId;
	private Integer MovieId;
	private long canceled = 0;
	private user my;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	}

	public reservationCancelFrame(user my, String groupId) {
		this.my = my;
		this.groupId = groupId;
		initialize();
		this.getResverationInfo(this.groupId);
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

	private void getResverationInfo(String groupId) {
		DB db = new DB();
		String query = String.format("SELECT " +
				"mov.title," +
				"scr.screenDate," +
				"mov.MovieId," +
				"res.date," +
				"scr.time," +
				"scr.ScreeningId AS ScreeningId," +
				"res.groupId," +
				"res.AccountId," +
				"res.cancled," +
				"res.seatId" +
		" FROM " +
				"theater.reservation AS res" +
						" JOIN " +
				"theater.screening AS scr ON res.ScreeningId = scr.ScreeningId" +
						" JOIN " +
				"theater.movie AS mov ON mov.MovieId = scr.MovieId" +
		" WHERE " +
				"groupId = '%s'"
		, groupId);

		List<Map<String, Object>> res = db.query(query);

		Map movie = res.get(0);
		String title = (String) movie.get("title");
		String time = (String) movie.get("time");
		String screenDate = (String) movie.get("screenDate");
		Integer count = res.size();
		String seats = "";
		for (Map<String, Object> m : res) {
			int n = Integer.parseInt((String) m.get("seatId"));
			String seatName = (char)(65 + Math.floorDiv(n, 10)) + Integer.toString(n % 10);

			seats += seatName + " ";
		}
		this.title.setText(title);
		this.detail.setText(screenDate + " | " + time);
		this.count.setText(count.toString());
		this.seats.setText(seats);

		this.canceled = (Integer) movie.get("cancled");
		this.MovieId = (Integer) movie.get("MovieId");
		ImageIcon posterImg = resizeIcon(new ImageIcon("./image/poster/poster_" + this.MovieId.toString() + ".jpg"), 120, 168);

		poster.setIcon(posterImg);
	}

	private static ImageIcon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
		Image img = icon.getImage();
		Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}

	public void drawInfoButton(ImagePanel bgPanel) {
		JButton button = this.makeImageButton("info1.jpg", "info2.jpg");
		button.setBounds(100, 360, 225, 54);
		button.setBorderPainted(false);
		button.setFocusPainted(false);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				mypageFrame mypageframe = new mypageFrame(my);
				mypageframe.setVisible(true);
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
				frame.dispose();
				checkFrame checkframe = new checkFrame(my);
				checkframe.setVisible(true);
			}
		});

		bgPanel.add(button);
	}

	public void drawCancelButton(ImagePanel bgPanel) {
		String btnOff = "cancel1.jpg";
		String btnOn = "cancel2.jpg";
		JButton button = this.makeImageButton(btnOff, btnOn);
		button.setBounds(765, 570, 225, 54);
		button.setBorderPainted(false);
		button.setFocusPainted(false);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (canceled == 1) {
					JOptionPane.showMessageDialog(bgPanel, "이미 예매취소되었습니다.");
				} else {
					int res = JOptionPane.showConfirmDialog(bgPanel, "정말 취소하시겠습니까?");
					if (res == JOptionPane.OK_OPTION) {
						// 예매취소
						cancel(groupId);
					}

				}
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
				mainFrame m = new mainFrame(my);
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
		poster = new JLabel();

		title.setBounds(780, 200, 200, 50);
		detail.setBounds(780, 250, 400, 50);
		count.setBounds(654, 416, 50, 50);
		seats.setBounds(774, 438, 200, 50);
		poster.setBounds(640, 195, 120, 160);

		this.setFontWeight(title, TextAttribute.WEIGHT_SEMIBOLD);
		this.setFontWeight(detail, TextAttribute.WEIGHT_SEMIBOLD);
		this.setFontWeight(seats, TextAttribute.WEIGHT_BOLD);
		this.setFontWeight(count, TextAttribute.WEIGHT_EXTRABOLD);
		this.setFontSize(title, 30);
		this.setFontSize(detail, 24);
		this.setFontSize(seats, 25);
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
		Icon IMG = new ImageIcon("./image/" + img);
		Icon IMG_HOVER = new ImageIcon("./image/" + hoverImg);
		JButton btn = new JButton();

		btn.setIcon(IMG);
		btn.setRolloverIcon(IMG_HOVER);

		return btn;
	}

	public void cancel(String groupId) {
		DB db = new DB();
		int result = db.update(String.format(
				"UPDATE theater.reservation" +
					" SET " +
						"cancled=1" +
				" WHERE groupId = '%s'"
			, groupId));
		if (result == 1) {
			JOptionPane.showMessageDialog(null, "취소되었습니다.");
		} else {
			JOptionPane.showMessageDialog(null, "취소 실패");
		}
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	public void dispose() {
		frame.dispose();
	}
}
