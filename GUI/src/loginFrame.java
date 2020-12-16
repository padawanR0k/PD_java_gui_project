import DB.DB;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

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

		// ImageIcon("C:\\0_dowon\\Playdata\\Project\\Java_GUI\\GUI\\image\\bg_loginFrame.jpg").getImage());
		ImagePanel bgPanel = new ImagePanel(new ImageIcon("./image/bg_loginFrame.jpg").getImage());
		frame.setSize(bgPanel.getWidth(), bgPanel.getHeight());
		frame.getContentPane().add(bgPanel);
		bgPanel.setLayout(null);

		id = new JTextField();
		id.setBounds(519, 312, 313, 45);
		bgPanel.add(id);
		id.setColumns(10);

		password = new JPasswordField();
		password.setBounds(519, 421, 313, 45);
		bgPanel.add(password);

		JButton btn_login = new JButton("New button");
		btn_login.setIcon(new ImageIcon("./image/btn/login1.jpg"));
		btn_login.setRolloverIcon(new ImageIcon("./image/btn/login2.jpg"));
		btn_login.setBounds(519, 518, 313, 57);

		btn_login.addActionListener(new btnAction());
		bgPanel.add(btn_login);

		JButton btn_signUp = new JButton("Sign Up");
		btn_signUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerFrame r = new registerFrame();
				r.setVisible(true);
				frame.dispose();
			}
		});
		btn_signUp.setFont(new Font("Arial", Font.BOLD, 17));
		btn_signUp.setBorderPainted(false);
		btn_signUp.setFocusPainted(false);
		btn_signUp.setForeground(Color.WHITE);
		btn_signUp.setBackground(Color.BLACK);
		btn_signUp.setBounds(734, 585, 123, 29);
		bgPanel.add(btn_signUp);

		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}


	class btnAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String ID = id.getText();
			String PW = password.getText();
			db = new DB();
			String query = String.format("""
					SELECT
						(SELECT count(*) FROM heroku_dcf5f8a801138d1.account WHERE id = '%s' and pw = MD5('%s')) as isAllValid,
						(SELECT count(*) FROM heroku_dcf5f8a801138d1.account WHERE id = '%s') as isIdValid;
					""", ID, PW, ID);
			List<Map<String, Object>> response = db.query(query);
			Map<String, Object> result = response.get(0);
			Long isIdValid = (Long) result.get("isIdValid");
			Long isAllValid = (Long)result.get("isAllValid");
			String  message = "";
			if (isIdValid == 0) {
				message = "존재하지 않는 아이디입니다.";
			} else {
				if (isAllValid == 1) {
					message = "로그인되었습니다..";
				} else {
					message = "비밀번호가 일치하지 않습니다.";
				}
			}

			JOptionPane.showMessageDialog(null, message);
			System.out.println(response);

		}
	}
}

class ImagePanel extends JPanel {
	private Image img;

	public ImagePanel(Image img) {
		this.img = img;
		setSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null))); 
		setLayout(null);
	}

	public int getWidth() {
		return img.getWidth(null);
	}

	public int getHeight() {
		return img.getHeight(null);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

}