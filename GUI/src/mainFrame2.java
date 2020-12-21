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

import DB.DB;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;

public class mainFrame2 {
	private JFrame frame;
	JButton[] jb;
	ImagePanel bgPanel = new ImagePanel(new ImageIcon("./image/bg_mainFrame.jpg").getImage());
	int page = 0;
	int page_max;
	int poster_num;
	ArrayList fileList;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame2 window = new mainFrame2();
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
	public mainFrame2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		jb = new JButton[5];
		for(int i=0;i<5;i++){
			jb[i]=new JButton();
		}
		//ImagePanel bgPanel = new ImagePanel(new ImageIcon("./image/bg_mainFrame.jpg").getImage());
		frame.setSize(bgPanel.getWidth(), bgPanel.getHeight());
		frame.getContentPane().add(bgPanel);
		bgPanel.setLayout(null);
		this.drawMypageButton(bgPanel);
		this.drawPreviousButton(bgPanel);
		this.drawNextButton(bgPanel);		
		
		// poster ���丮 ���� ��ü ��� �������� �޼���
		File path = new File("./image/poster/");
		System.out.println(path.list().length);
		String[] fileNames = path.list();
		fileList = new ArrayList();
		
		for (int i = 0; i < fileNames.length; i++) {
			fileList.add(fileNames[i]);
			System.out.println(fileNames[i]);
		}
		System.out.println(fileList);
		
		poster_num = new File("./image/poster/").listFiles().length;
		page_max = (int) Math.ceil((double) poster_num / 5);
		System.out.println(page_max);
		
		this.drawPosterButton(bgPanel,0, new int[]{50, 221, 230, 328}, fileList.get(page));
		this.drawPosterButton(bgPanel,1, new int[]{306, 221, 230, 328}, fileList.get(page+1));
		this.drawPosterButton(bgPanel,2, new int[]{563, 221, 230, 328}, fileList.get(page+2));
		this.drawPosterButton(bgPanel,3, new int[]{818, 221, 230, 328}, fileList.get(page+3));
		this.drawPosterButton(bgPanel,4, new int[]{1075, 221, 230, 328}, fileList.get(page+4));

		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// ������ �̹����� ���������ϴ� �޼���
	// resizedHeight = 230, resizedHeight = 328
	private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
	       Image img = icon.getImage();  
	       Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);  
	       return new ImageIcon(resizedImage);
	}
	
	public void drawPosterButton(ImagePanel bgPanel,int i, int[] bounds, Object object) {
		jb[i] = this.makePosterButton(object);
		jb[i].setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
		jb[i].setBackground(Color.BLACK);
		jb[i].setBorderPainted(false);
		jb[i].setFocusPainted(false);
		bgPanel.add(jb[i]);
	}
	
	public JButton makePosterButton(Object object) {
		Icon IMG = resizeIcon(new ImageIcon("./image/poster/" + object), 230, 328);
		//Icon IMG = resizeIcon(new ImageIcon(img), 230, 328);
		JButton btn = new JButton();

		btn.addMouseListener(new MouseAdapter() {
			@Override  
			public void mouseClicked(MouseEvent e) {
				JButton button = (JButton)e.getSource();
				
				reserveFrame s = new reserveFrame(button.getIcon());
				s.setVisible(true);
				frame.dispose();
			}
		});
		
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
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (page == 0) {
					//button.setEnabled(false);
				}
				else {
					page = page - 5;
					System.out.println(page);
				}
			}
		});
		
		bgPanel.add(button);
	}
	
	public void drawNextButton(ImagePanel bgPanel) {
		JButton button = this.makeImageButton("next1.png", "next2.png");
		button.setBounds(703, 631, 90, 90);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setBackground(Color.BLACK);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (button.hasFocus()) {
					if (page == (page_max-1) * 5) {
						System.out.println("a");
					}
					else {
						page = page + 5;
						jb[0].setIcon(resizeIcon(new ImageIcon("./image/poster/" + fileList.get(page)), 230, 328));
						System.out.println(page);
					}
				}
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

