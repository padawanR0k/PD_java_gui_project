import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
		
		ImagePanel bgPanel = new ImagePanel(new ImageIcon("./image/bg_mainFrame.jpg").getImage());
		frame.setSize(bgPanel.getWidth(), bgPanel.getHeight());
		frame.getContentPane().add(bgPanel);
		bgPanel.setLayout(null);
		
		this.drawMypageButton(bgPanel);
		this.drawPreviousButton(bgPanel);
		this.drawNextButton(bgPanel);		
		
		this.drawPosterButton(bgPanel, new int[]{50, 221, 230, 328}, "test.jpg");
		this.drawPosterButton(bgPanel, new int[]{306, 221, 230, 328}, "test2.jfif");
		this.drawPosterButton(bgPanel, new int[]{563, 221, 230, 328}, "test3.jfif");
		this.drawPosterButton(bgPanel, new int[]{818, 221, 230, 328}, "test4.jpg");
		this.drawPosterButton(bgPanel, new int[]{1075, 221, 230, 328}, "test5.jfif");
		
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// 포스터 이미지를 리사이즈하는 메서드
	// resizedHeight = 230, resizedHeight = 328
	private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
	       Image img = icon.getImage();  
	       Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);  
	       return new ImageIcon(resizedImage);
	}
	
	public void drawPosterButton(ImagePanel bgPanel, int[] bounds, String posterName) {
		JButton poster = this.makePosterButton(posterName);
		poster.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
		poster.setBackground(Color.BLACK);
		poster.setBorderPainted(false);
		poster.setFocusPainted(false);
		bgPanel.add(poster);
	}
	
	public JButton makePosterButton(String img) {
		Icon IMG = resizeIcon(new ImageIcon("./image/poster/" + img), 230, 328);
		JButton btn = new JButton();

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
				mypageFrame p = new mypageFrame();
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
		
		bgPanel.add(button);
	}
	
	public void drawNextButton(ImagePanel bgPanel) {
		JButton button = this.makeImageButton("next1.png", "next2.png");
		button.setBounds(703, 631, 90, 90);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setBackground(Color.BLACK);
		
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