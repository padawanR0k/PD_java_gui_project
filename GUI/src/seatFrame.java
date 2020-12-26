import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DB.DB;


/**
 *
 * @author newjihwan
 * @last mdf 20-12-13 15:26:10
 */

public class seatFrame extends javax.swing.JFrame {
    private JPanel contentPane;
    private ImageIcon icon;
    private int button_x, button_y;
    private int count, choice;

    private int adultCount;
    private int youthCount;
    private int movieId;
    private User user;
    private ArrayList<Integer> selectedSeat = new ArrayList<>();

    public seatFrame(int movieId, User user, int adultCount, int youthCount) { // 좌석 개수
        this.user = user;
        this.adultCount = adultCount;
        this.youthCount = youthCount;
        this.count = adultCount + youthCount;
        this.choice = 0;
        initComponents();
        button_x = 574;
        button_y = 135;
        icon = new ImageIcon("./image/bg_seatFrame.jpg");
        contentPane = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), 0, 0, null);
                setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
            }
        };
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        this.drawDetails(adultCount, youthCount);

        // 버튼 배치
        JToggleButton[][] jb = new JToggleButton[10][8];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                jb[i][j] = new JToggleButton((char) (65 + j) + "" + i); // 아스키
                jb[i][j].setBackground(new Color(170, 170, 170));
                int seatNum =  i * 10 + j;
                jb[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        JToggleButton button = (JToggleButton) e.getSource();
                        System.out.println(choice);
                        if (choice == count && button.isSelected() == true) {
                            selectedSeat.remove(selectedSeat.indexOf(seatNum));
                            choice -= 1;
                            button.setIcon(new ImageIcon());
                        } else if (choice == count && button.isSelected() == false) {
                            selectedSeat.add(seatNum);
                            button.setSelected(true);
                        } else if (button.isSelected() == false) {
                            selectedSeat.add(seatNum);
                            choice += 1;
                            button.setIcon(new ImageIcon("./image/choosebutton.jpg"));
                        } else if (button.isSelected() == true) {
                            choice -= 1;
                            selectedSeat.remove(selectedSeat.indexOf(seatNum));
                            button.setIcon(new ImageIcon());
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
                jb[i][j].setBounds(button_x + 60 * i, button_y + 66 * j, 50, 50);
                contentPane.add(jb[i][j]);
            }
        }

        JButton confirmButton = new JButton("");
        confirmButton.setText("예매하기");
        confirmButton.setFont(confirmButton.getFont().deriveFont(20.0F));
        confirmButton.setBounds(300, 680, 100, 50);
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DB db = new DB();
                String query = "";
                String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                String uuid =  UUID.randomUUID().toString();
                for(Integer seat: selectedSeat) {
                    query += String.format(
                        "INSERT INTO theater.reservation set MovieId = %s, AccountId = %s, groupId = '%s', date = '%s', cancled = 0, seatId = %s ;"
                        , movieId, user.AccountId, uuid, today, seat);
                }
                // for (int i = 0; i < adultCount; i++) {
                // }

                // for (int j = 0; j < youthCount; j++) {
                //     query += String.format(
                //         "INSERT INTO theater.reservation ScreeningId = '%s', AccountId = '%s', groupId = '%s', date = '%s', cancled = 0, seatId = '%s' "
                //         , , user.AccountId, uuid, today, );
                // }


                int res = db.update(query);
                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "예약되엇습니다.");
                    dispose();
                    checkFrame checkframe = new checkFrame(user);
                    checkframe.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "예약에 실패했습니다.");

                }
            }
        });
        contentPane.add(confirmButton);
    }

    public void drawDetails(int adultCount, int youthCount) {
        this.drawLabel(adultCount, new int[] { 150, 515,100, 50 });
        this.drawLabel(youthCount, new int[] { 150, 560,100, 50 });
        this.drawLabel(adultCount * 13000 + youthCount * 10000, new int[] { 150, 680,300, 50 });
    }

    public void drawLabel(int value, int[] bounds) {
        JLabel label = new JLabel(Integer.toString(value));
        label.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
        label.setFont(label.getFont().deriveFont(40.0F));
        label.setForeground(Color.white);
        contentPane.add(label);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(seatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(seatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(seatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(seatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        // java.awt.EventQueue.invokeLater(new Runnable() {
        //     public void run() {
        //         new seatFrame(5).setVisible(true);
        //     }
        // });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
