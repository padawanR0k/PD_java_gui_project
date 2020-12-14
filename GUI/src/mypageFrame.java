import DB.DB;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class mypageFrame {
	private final Image BG_IMAGE = new ImageIcon("./image/bg_checkFrame(info).jpg").getImage();
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mypageFrame window = new mypageFrame();
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
	public mypageFrame() {
		initialize();
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

		this.drawCheckButton(bgPanel);
		this.drawCancelButton(bgPanel);

		DB db = new DB();
		// TODO - 로그인시 회원 고유값을 저장한 후 그 값을 AccountId 부분에 넣는 로직필요 함
		List response = db.query("select * from heroku_dcf5f8a801138d1.account where AccountId = 1");
		Map<String, ?> user = (Map)response.get(0);
		String nick = (String)user.get("nick");
		String id = (String)user.get("id");
		this.drawText(bgPanel, nick, new int[]{800, 250, 225, 60});
		this.drawText(bgPanel, id, new int[]{800, 340, 225, 60});

		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void drawText(ImagePanel bgPanel, String text, int[] bounds) {
		JTextField btn = new JTextField(text);
		btn.setEditable(false);
		btn.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
		bgPanel.add(btn);
	}

	public void drawCheckButton(ImagePanel bgPanel) {
		JButton button = this.makeImageButton("check1.jpg", "check2.jpg");
		button.setBounds(100, 360, 225, 60);

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
		button.setBounds(100, 450, 225, 60);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "cancela Reservation");
			}
		});

		bgPanel.add(button);
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