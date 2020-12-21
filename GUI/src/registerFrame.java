import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DB.DB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;

public class registerFrame {

	private JFrame frame;
	private JTextField id;
	private JPasswordField password;
	private JTextField name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registerFrame window = new registerFrame();
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
	public registerFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();

		// 패널에 이미지 설정
		ImagePanel bgPanel = new ImagePanel(new ImageIcon("./image/bg_registerFrame.jpg").getImage());
		// 이미지 크기를 가져와서 이미지 크기만큼 패널을 만들도록 설정
		frame.setSize(bgPanel.getWidth(), bgPanel.getHeight());
		frame.getContentPane().add(bgPanel);
		bgPanel.setLayout(null);

		this.drawIdFeild(bgPanel);
		this.drawPwFeild(bgPanel);
		this.drawNameFeild(bgPanel);
		this.drawRegisterBtn(bgPanel);

		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void drawIdFeild(ImagePanel bgPanel) {
		id = new JTextField();
		id.setBounds(519, 387, 313, 45);
		id.setColumns(10);
		bgPanel.add(id);
	}

	public void drawPwFeild(ImagePanel bgPanel) {
		password = new JPasswordField();
		password.setBounds(519, 495, 313, 45);
		bgPanel.add(password);
	}

	public void drawNameFeild(ImagePanel bgPanel) {
		name = new JTextField();
		name.setBounds(519, 279, 313, 45);
		name.setColumns(10);
		bgPanel.add(name);
	}

	public void drawRegisterBtn(ImagePanel bgPanel) {
		JButton btn_register = new JButton("New button");
		btn_register.setBackground(Color.LIGHT_GRAY);
		btn_register.setForeground(Color.WHITE);
		btn_register.setIcon(new ImageIcon("./image/register1.jpg"));
		btn_register.setRolloverIcon(new ImageIcon("./image/register2.jpg"));
		btn_register.setBounds(519, 586, 313, 57);
		btn_register.addActionListener(new RegisterAction());
		bgPanel.add(btn_register);
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	public void dispose() {
		frame.dispose();
	}

	class RegisterAction implements ActionListener {
		public boolean checkDuplicatedId(String Id) {
			DB db = new DB();
			List<Map<String, Object>> response = db.query(String.format("select IF(EXISTs(select * from theater.account where id = '%s'), true, false) as result", id));
			Map<String, Object> responseRow = response.get(0);
			long result = (long)(responseRow.get("result"));
			return result == 1 ? true : false;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String ID = id.getText();
			String PW = password.getText();
			String NICK = name.getText();

			// 1. 아이디 중복 확인
			// 	1.1 중복 -> 얼럿창
			//  1.2 로그인
			if (ID.length() == 0) {
				JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");
			} else if (PW.length() == 0) {
				JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
			} else if (NICK.length() == 0) {
				JOptionPane.showMessageDialog(null, "닉네임을 입력해주세요.");
			} else if (this.checkDuplicatedId(ID)) {
				JOptionPane.showMessageDialog(null, "중복된 아이디입니다.");
			} else {
				DB db = new DB();
				int response = db.update(String.format("insert into theater.account (id, pw, nick) values('%s', MD5('%s'), '%s')", ID, PW, NICK));
				System.out.println(response);
				JOptionPane.showMessageDialog(null, "회원가입 되었습니다.");
				if (response > 0) {
					dispose();
					// TODO mainFrame 이동시키는 코드 추가 필요!
				}
			}
		}

	}
}