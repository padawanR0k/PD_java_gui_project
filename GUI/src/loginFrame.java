import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

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
		//ImagePanel bgPanel = new ImagePanel(new ImageIcon("C:\\0_dowon\\Playdata\\Project\\Java_GUI\\GUI\\image\\bg_loginFrame.jpg").getImage());
		ImagePanel bgPanel = new ImagePanel(new ImageIcon("./image/bg_loginFrame.jpg").getImage());
		// 이미지 크기를 가져와서 이미지 크기만큼 패널을 만들도록 설정
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
		
		JButton btn_register = new JButton("New button");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		//btn_register.setBackground(Color.LIGHT_GRAY);
		//btn_register.setForeground(Color.WHITE);
		btn_register.setIcon(new ImageIcon("C:\\0_dowon\\Playdata\\Project\\Java_GUI\\GUI\\image\\login1.jpg"));
		btn_register.setRolloverIcon(new ImageIcon("C:\\0_dowon\\Playdata\\Project\\Java_GUI\\GUI\\image\\login2.jpg"));
		btn_register.setIcon(new ImageIcon("./image/login1.jpg"));
		btn_register.setRolloverIcon(new ImageIcon("./image/login2.jpg"));
		btn_register.setBounds(519, 518, 313, 57);
		bgPanel.add(btn_register);
		
		JLabel signUp = new JLabel("Sign Up");
		signUp.setFont(new Font("Arial", Font.BOLD, 17));
		signUp.setForeground(Color.WHITE);
		signUp.setBounds(760, 585, 72, 25);
		bgPanel.add(signUp);
		
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