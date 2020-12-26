import DB.DB;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class loginFrame {
	private JFrame frame;
	private JTextField id;
	private JPasswordField password;
	private DB db;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginFrame window = new loginFrame();
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
	public loginFrame() {
		initialize();
		db = new DB();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();

		ImagePanel bgPanel = new ImagePanel(new ImageIcon("./image/bg_loginFrame.jpg").getImage());
		frame.setSize(bgPanel.getWidth(), bgPanel.getHeight());
		frame.getContentPane().add(bgPanel);
		bgPanel.setLayout(null);

		this.id = this.drawText(bgPanel, new int[]{519, 312, 313, 45}); // ID
		this.password = this.drawPassword(bgPanel, new int[]{519, 421, 313, 45}); // password
		this.drawSignupButton(bgPanel);

		JButton btn_login = this.makeImageButton("login1.jpg", "login2.jpg");
		btn_login.setBounds(519, 518, 313, 57);

		btn_login.addActionListener(new btnAction());
		bgPanel.add(btn_login);

		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	class btnAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String ID = id.getText();
			String PW = password.getText();
			db = new DB();
			String query = String.format(
					"SELECT" +
						"(SELECT count(*) FROM theater.account WHERE id = '%s' and pw = MD5('%s')) as isAllValid," +
						"(SELECT count(*) FROM theater.account WHERE id = '%s') as isIdValid;"
					, ID, PW, ID);
			List<Map<String, Object>> response = db.query(query);
			Map<String, Object> result = response.get(0);
			Long isIdValid = (Long) result.get("isIdValid");
			Long isAllValid = (Long)result.get("isAllValid");
			String  message = "";
			if (isIdValid == 0) {
				JOptionPane.showMessageDialog(null, "존재하지 않는 아이디입니다.");
			} else {
				if (isAllValid == 1) {
					List<Map<String, Object>> response2 = db
							.query(String.format("select AccountId, nick, id from theater.account where id = '%s'", ID));
					User user = new User(response2.get(0));

					JOptionPane.showMessageDialog(null, "로그인되었습니다..");
					dispose();
					mainFrame frame = new mainFrame(user);
					frame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
				}
			}

			System.out.println(response);

		}
	}

	public JTextField drawText(ImagePanel bgPanel, int[] bounds) {
		JTextField txt = new JTextField();
		txt.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
		bgPanel.add(txt);
		return txt;
	}

	public JPasswordField drawPassword(ImagePanel bgPanel, int[] bounds) {
		JPasswordField txt = new JPasswordField();
		txt.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
		bgPanel.add(txt);
		return txt;
	}

	public void drawSignupButton(ImagePanel bgPanel) {
		JButton button = new JButton("Sign Up");
		button.setBounds(734, 585, 123, 29);
		button.setFont(new Font("Arial", Font.BOLD, 17));
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setForeground(Color.WHITE);
		button.setBackground(Color.BLACK);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerFrame r = new registerFrame();
				r.setVisible(true);
				frame.dispose();
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