import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;
import javax.swing.JButton;

public class payFrame {
	private JFrame frame;
	private JTextField cardNumber;
	private JTextField expirationDate;
	private JTextField cvv;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					payFrame window = new payFrame();
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
	public payFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		ImagePanel bgPanel = new ImagePanel(new ImageIcon("./image/bg_payFrame.jpg").getImage());
		frame.setSize(bgPanel.getWidth(), bgPanel.getHeight());
		frame.getContentPane().add(bgPanel);
		bgPanel.setLayout(null);
		
		this.drawText(bgPanel, new int[]{369, 205, 261, 31}); // cardNumber
		this.drawText(bgPanel, new int[]{75, 311, 151, 31}); // expirationDate
		this.drawFormattedText(bgPanel, new int[]{369, 311, 151, 31}); // cvv
		this.drawPayButton(bgPanel);
		this.drawComboBox(bgPanel);
		
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	public void drawComboBox(ImagePanel bgPanel) {
		String[] banks={"신한은행", "우리은행", "농협", "국민은행", "카카오뱅크", "하나은행", "기타"};
		JComboBox<?> bankCombo = new JComboBox<Object>(banks);
		bankCombo.setBackground(Color.WHITE);
		bankCombo.setBounds(75, 205, 224, 31);
		bgPanel.add(bankCombo);
	}
	
	public void drawText(ImagePanel bgPanel, int[] bounds) {
		JTextField btn = new JTextField();
		btn.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
		bgPanel.add(btn);
	}
	
	public void drawFormattedText(ImagePanel bgPanel, int[] bounds) {
		JFormattedTextField btn = new JFormattedTextField(new NumberFormatter());
		btn.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
		bgPanel.add(btn);
	}
	
	public void drawPayButton(ImagePanel bgPanel) {
		JButton button = this.makeImageButton("pay1.jpg", "pay2.jpg");
		button.setBounds(69, 388, 160, 45);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Payment is completed.");
				mainFrame m = new mainFrame();
				m.setVisible(true);
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