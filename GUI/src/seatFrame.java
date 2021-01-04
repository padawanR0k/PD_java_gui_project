import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DB.DB;

import java.awt.event.ActionEvent;

/**
 *
 * @author newjihwan
 * @last mdf 20-12-13 15:26:10
 */

public class seatFrame extends javax.swing.JFrame {
    private JPanel contentPane;
    private ImageIcon icon;
    private int button_x, button_y;
    private int count, choice, adultCount, youthCount, movieId;
    private String date, time;
    private user my;
    private ArrayList<Integer> reservedSeat = new ArrayList<>();

    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
                    new main();
                    user mockUser = new user("test");
                    user.accountId = 41;
                    mockUser.setReserveMovie("2021-01-01", "12:30", 1, 0, 1411);
					seatFrame window = new seatFrame(mockUser);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

    public seatFrame(user my) { // 좌석 개수
        this.my = my;
        this.adultCount = my.getadultCount();
        this.youthCount = my.getyouthCount();
        this.count = adultCount + youthCount;
        this.movieId = my.getmovieId();
        this.date = my.getDate();
        this.time = my.getTime();
        this.choice = 0;
        initComponents();
        button_x = 574;
        button_y = 165;
        icon = new ImageIcon("./image/bg_seatFrame.jpg");
        this.getSeat();

        // 버튼 배치
        JToggleButton[][] jb = new JToggleButton[10][8];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                int seatNum = j * 10 + i;
                jb[i][j] = new JToggleButton((char) (65 + j) + "" + i); // 아스키
                jb[i][j].setBackground(new Color(170, 170, 170));

                if (this.reservedSeat.contains(seatNum) == true) {
                    jb[i][j].setIcon(new ImageIcon("./image/choosebutton2.png"));
                } else {

                    jb[i][j].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            JToggleButton button = (JToggleButton) e.getSource();
                            System.out.println(choice + button.getText());
                            if (choice == count) {
                                if (my.selectedSeat.contains(seatNum)) {
                                    System.out.println("감소 1");
                                    System.out.println(my.selectedSeat);

                                    choice -= 1;
                                    my.selectedSeat.remove((Integer) (seatNum));
                                    button.setIcon(new ImageIcon());
                                } else {
                                    JOptionPane.showMessageDialog(null, "모두 선택하셨습니다.");
                                }
                            } else {
                                if (my.selectedSeat.contains(seatNum) == false) {
                                    System.out.println("증가 11");
                                    System.out.println(my.selectedSeat);
                                    choice += 1;
                                    my.selectedSeat.add(seatNum);
                                    button.setIcon(new ImageIcon("./image/choosebutton.png"));
                                } else if (my.selectedSeat.contains(seatNum) == true) {
                                    System.out.println("감소 2");
                                    System.out.println(my.selectedSeat);
                                    choice -= 1;
                                    my.selectedSeat.remove((Integer) (seatNum));
                                    button.setIcon(new ImageIcon());
                                }
                            }
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            JToggleButton button = (JToggleButton) e.getSource();
                            button.setBackground(new Color(200, 125, 125));
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            JToggleButton button = (JToggleButton) e.getSource();
                            button.setBackground(new Color(170, 170, 170));

                        }
                    });
                }

                jb[i][j].setBounds(button_x + 60 * i, button_y + 66 * j, 50, 50);
                contentPane.add(jb[i][j]);
            }
        }
    }

    public void getSeat() {
        DB db = new DB();
        List<Map<String, Object>> info = db.query(String
                .format("SELECT * FROM theater.reservation where ScreeningId = %d and cancled = 0;", my.ScreeningId));
        for (int i = 0; i < info.size(); i++) {
            int seatId = Integer.parseInt((String) info.get(i).get("seatId"));
            this.reservedSeat.add(seatId);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        contentPane = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), 0, 0, null);
                g.drawImage(my.getsmallIcon().getImage(), 160, 45, null);
                // my.getIcon().paintIcon(this, g, 133, 30);
                setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
            }
        };
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton backButton = new JButton(new ImageIcon("./image/btn/back_btn.png")); // 뒤로가기 버튼
        backButton.setBounds(1245, 10, 85, 85);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setBackground(Color.WHITE);
        contentPane.add(backButton);
        backButton.addActionListener(new ActionListener() { // mainFrame으로 이동
            public void actionPerformed(ActionEvent e) {
                reserveFrame m = new reserveFrame(my);
                m.setVisible(true);
                dispose();
            }
        });

        // 추가해야함
        JLabel dateLabel = new JLabel(date);
        JLabel timeLabel = new JLabel(time);
        JLabel adultC = new JLabel(adultCount + "");
        JLabel youthC = new JLabel(youthCount + "");
        JLabel price = new JLabel(adultCount * 13000 + youthCount * 10000 + "");
        dateLabel.setBounds(160, 285, 200, 100);
        timeLabel.setBounds(160, 335, 200, 100);
        adultC.setBounds(160, 410, 200, 100);
        youthC.setBounds(160, 460, 200, 100);
        price.setBounds(160, 533, 200, 100);

        dateLabel.setForeground(Color.white);
        dateLabel.setFont(dateLabel.getFont().deriveFont(27.0F));
        timeLabel.setForeground(Color.white);
        timeLabel.setFont(timeLabel.getFont().deriveFont(30.0F));
        adultC.setForeground(Color.white);
        adultC.setFont(adultC.getFont().deriveFont(30.0F));
        youthC.setForeground(Color.white);
        youthC.setFont(youthC.getFont().deriveFont(30.0F));
        price.setForeground(Color.white);
        price.setFont(price.getFont().deriveFont(30.0F));

        contentPane.add(dateLabel);
        contentPane.add(timeLabel);
        contentPane.add(adultC);
        contentPane.add(youthC);
        contentPane.add(price);

        JButton reserveBtn = new JButton(new ImageIcon("./image/seat1.jpg"));
        reserveBtn.setRolloverIcon(new ImageIcon("./image/seat2.jpg"));
        reserveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (choice != count) {
                    JOptionPane.showMessageDialog(null, "선택하신 좌석의 수가 올바르지 않습니다.");
                    return;
                } else {
                    payFrame s = new payFrame(my);
                    s.setVisible(true);
                    dispose();
                }
            }
        });
        reserveBtn.setBounds(85, 640, 310, 60);
        contentPane.add(reserveBtn);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(1366, 768));
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setPreferredSize(new java.awt.Dimension(1366, 768));
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 1366, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 768, Short.MAX_VALUE));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
}
