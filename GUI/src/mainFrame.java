import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class mainFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame window = new mainFrame();
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
	public mainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		// 패널에 이미지 설정
		//ImagePanel bgPanel = new ImagePanel(new ImageIcon("C:\\0_dowon\\Playdata\\Project\\Java_GUI\\GUI\\image\\bg_mainFrame.jpg").getImage());
		ImagePanel bgPanel = new ImagePanel(new ImageIcon("./image/bg_mainFrame.jpg").getImage());
		// 이미지 크기를 가져와서 이미지 크기만큼 패널을 만들도록 설정
		frame.setSize(bgPanel.getWidth(), bgPanel.getHeight());
		frame.getContentPane().add(bgPanel);
		bgPanel.setLayout(null);
		
		JButton btn_myPage = new JButton("");
		btn_myPage.setBackground(Color.WHITE);
		btn_myPage.setBorderPainted(false);
		btn_myPage.setFocusPainted(false);
		//btn_myPage.setIcon(new ImageIcon("C:\\0_dowon\\Playdata\\Project\\Java_GUI\\GUI\\image\\user_btn.png"));
		btn_myPage.setIcon(new ImageIcon("./image/user_btn.png"));
		btn_myPage.setBounds(1245, 15, 85, 85);
		bgPanel.add(btn_myPage);
		
		JButton poster1 = new JButton("");
		poster1.setBounds(130, 255, 253, 328);
		bgPanel.add(poster1);
		
		JButton poster2 = new JButton("");
		poster2.setBounds(411, 255, 253, 328);
		bgPanel.add(poster2);
		
		JButton poster3 = new JButton("");
		poster3.setBounds(692, 255, 253, 328);
		bgPanel.add(poster3);
		
		JButton poster4 = new JButton("");
		poster4.setBounds(971, 255, 253, 328);
		bgPanel.add(poster4);
		
		JButton btn_previous = new JButton("");
		btn_previous.setBackground(Color.WHITE);
		btn_previous.setBorderPainted(false);
		btn_previous.setFocusPainted(false);
		//btn_previous.setIcon(new ImageIcon("C:\\0_dowon\\Playdata\\Project\\Java_GUI\\GUI\\image\\previous1.png"));
		//btn_previous.setRolloverIcon(new ImageIcon("C:\\0_dowon\\Playdata\\Project\\Java_GUI\\GUI\\image\\previous2.png"));
		btn_previous.setIcon(new ImageIcon("./image/previous1.png"));
		btn_previous.setRolloverIcon(new ImageIcon("./image/previous2.png"));
		btn_previous.setBounds(15, 382, 90, 90);
		bgPanel.add(btn_previous);
		
		JButton btn_next = new JButton("");
		//btn_next.setIcon(new ImageIcon("C:\\0_dowon\\Playdata\\Project\\Java_GUI\\GUI\\image\\next1.png"));
		//btn_next.setRolloverIcon(new ImageIcon("C:\\0_dowon\\Playdata\\Project\\Java_GUI\\GUI\\image\\next2.png"));
		btn_next.setIcon(new ImageIcon("./image/next1.png"));
		btn_next.setRolloverIcon(new ImageIcon("./image/next2.png"));
		btn_next.setBackground(Color.WHITE);
		btn_next.setBorderPainted(false);
		btn_next.setFocusPainted(false);
		btn_next.setBounds(1245, 382, 90, 90);
		bgPanel.add(btn_next);
		
		
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