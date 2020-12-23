import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author newjihwan
 */
public class reserveFrame extends javax.swing.JFrame {
    private JPanel contentPane;
    private ImageIcon icon;
    private int count;
    private int adultCount, youthCount;
    /**
     * Creates new form reserveFrame
     */
    public reserveFrame() {
    }
    public reserveFrame(Icon poster) {
        adultCount = 0;
        youthCount = 0;
        ImageIcon reserve1 = new ImageIcon("./image/reserve1.jpg");
        ImageIcon reserve2 = new ImageIcon("./image/reserve2.jpg");
        icon = new ImageIcon("./image/bg_reserveFrame.jpg");
        initComponents();

        contentPane = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), 0, 0, null);
                poster.paintIcon(this, g, 100, 100);
                setOpaque(false); //그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
            }
        };
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        JButton choiceButton = new JButton(reserve1); // choose you seat 버튼
        choiceButton.setRolloverIcon(reserve2);
        choiceButton.setBounds(760,620,327,68);
        contentPane.add(choiceButton);
        choiceButton.addActionListener(new ActionListener() { // seatFrame 불러오기
			public void actionPerformed(ActionEvent e) {
				seatFrame p = new seatFrame(adultCount+youthCount);
				p.setVisible(true);
				dispose();
			}
        });

        JComboBox date = new JComboBox<>(); // 날짜 date
        date.setBounds(700,125,250,50);
        contentPane.add(date);

        JComboBox time = new JComboBox<>(); // 시간 time
        time.setBounds(700,200,250,50);
        contentPane.add(time);

        JLabel price = new JLabel(""); // 가격 price
        price.setBounds(700,500,1000,50);
        price.setForeground(Color.white);
        price.setFont(price.getFont().deriveFont(40.0F));
        contentPane.add(price);

        JButton[] adult_btn_list = new JButton[10]; // 성인 adult
        for(int i=0;i<10;i++){
            adult_btn_list[i] = new JButton(""+(i));
            adult_btn_list[i].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JButton button = (JButton)e.getSource();
                        if(Integer.parseInt(button.getText())+youthCount>5){
                            JOptionPane.showMessageDialog(null, "5인 이하만 예매 가능합니다.");
                            return;
                        }
                        for(int i=0;i<10;i++){
                            adult_btn_list[i].setBackground(new Color(170,170,170));
                        }
                        adult_btn_list[Integer.parseInt(button.getText())].setBackground(new Color(255,0,0));
                        adultCount = Integer.parseInt(button.getText());
                        price.setText(adultCount*13000+youthCount*10000+" Won");
                    }
                });
            adult_btn_list[i].setBounds(700+60*i,342,50,50);
            contentPane.add(adult_btn_list[i]);
        }

        JButton[] youth_btn_list = new JButton[10]; // 어린이 youth
        for(int i=0;i<10;i++){
            youth_btn_list[i] = new JButton(""+(i));
            youth_btn_list[i].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JButton button = (JButton)e.getSource();
                        if(Integer.parseInt(button.getText())+adultCount>5){
                            JOptionPane.showMessageDialog(null, "5인 이하만 예매 가능합니다.");
                            return;
                        }
                        for(int i=0;i<10;i++){
                            youth_btn_list[i].setBackground(new Color(170,170,170));
                        }
                        youth_btn_list[Integer.parseInt(button.getText())].setBackground(new Color(255,0,0));
                        youthCount = Integer.parseInt(button.getText());
                        price.setText(adultCount*13000+youthCount*10000+" Won");
                    }
                });
            youth_btn_list[i].setBounds(700+60*i,395,50,50);
            contentPane.add(youth_btn_list[i]);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1366, 768));
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1366, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 768, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(reserveFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reserveFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reserveFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reserveFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new reserveFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    // End of variables declaration
}
