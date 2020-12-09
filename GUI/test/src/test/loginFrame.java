package test;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;

public class loginFrame {

	private JFrame frame;
	private JTextField id;
	private JPasswordField password;

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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		// 패널에 이미지 설정
		ImagePanel bgPanel = new ImagePanel(new ImageIcon("C:/0_dowon/Playdata/Project/Java_GUI/GUI/test/image/loginFrame.jpg").getImage());
		// 이미지 크기를 가져와서 이미지 크기만큼 패널을 만들도록 설정
		frame.setSize(bgPanel.getWidth(), bgPanel.getHeight());
		frame.getContentPane().add(bgPanel);
		bgPanel.setLayout(null);
		
		id = new JTextField();
		id.setBounds(519, 311, 326, 52);
		bgPanel.add(id);
		id.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(519, 419, 326, 52);
		bgPanel.add(password);
		
		JButton btn_login = new JButton("New button");
		btn_login.setBackground(Color.GRAY);
		btn_login.setForeground(Color.WHITE);
		btn_login.setIcon(new ImageIcon("C:\\0_dowon\\Playdata\\Project\\Java_GUI\\GUI\\test\\image\\login.jpg"));
		btn_login.setRolloverIcon(new ImageIcon("C:\\0_dowon\\Playdata\\Project\\Java_GUI\\GUI\\test\\image\\login_click.jpg"));
		btn_login.setBounds(519, 520, 316, 57);
		bgPanel.add(btn_login);
		
		// 로그인 버튼을 눌렀을 때 아이디, 비번을 확인하도록
		btn_login.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				if(id.getText().equals("dowon")&&Arrays.equals(password.getPassword(), "1234".toCharArray())) {
					System.out.println("Welcome Dowon");
//					bgPanel.setVisible(false);
					System.exit(0);
				} else {
					JOptionPane.showMessageDialog(null, "아이디가 존재하지 않거나 비밀번호가 틀립니다.");
				}
			}
		});
				
		bgPanel.add(btn_login);
		
		
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

	class ImagePanel extends JPanel{
		private Image img;
		
		public ImagePanel(Image img){
			this.img = img;
			setSize(new Dimension(img.getWidth(null),img.getHeight(null))); // 사이즈 최대
			setPreferredSize(new Dimension(img.getWidth(null),img.getHeight(null))); // 이미지 크기로 사이즈 설정
			setLayout(null);
		}
		
		// 이미지 크기를 가져오도록
		public int getWidth() {
			return img.getWidth(null);
		}
		
		public int getHeight() {
			return img.getHeight(null);
		}
		
		// 이미지를 업로드하는 함수
		public void paintComponent(Graphics g){
			g.drawImage(img, 0, 0, null);
		}
		
	}

