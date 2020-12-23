import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private 
    public seatFrame(){}
    public seatFrame(int count) { // 좌석 개수
        this.count=count;
        this.choice = 0;
        initComponents();
        button_x = 574;
        button_y = 135;
        icon = new ImageIcon("./image/bg_seatFrame.jpg");
        contentPane = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), 0, 0, null);
                setOpaque(false); //그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
            }
        };
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //버튼 배치  
        JToggleButton[][] jb = new JToggleButton[10][8];
        for(int i=0;i<10;i++) {
            for(int j=0;j<8;j++) {
                jb[i][j] = new JToggleButton((char)(65+j)+""+i); // 아스키
                jb[i][j].setBackground(new Color(170,170,170));
                jb[i][j].addMouseListener(new MouseAdapter() {
                    @Override  
                    public void mousePressed(MouseEvent e) {
                        JToggleButton button = (JToggleButton)e.getSource();
                        System.out.println(choice);
                        if(choice==count && button.isSelected()==true){
                            choice -= 1;
                            button.setIcon(new ImageIcon());
                        }
                        else if(choice==count && button.isSelected()==false){
                            button.setSelected(true);
                        }
                        else if(button.isSelected()==false){
                            choice += 1;
                            button.setIcon(new ImageIcon("./image/choosebutton.jpg"));
                        }
                        else if(button.isSelected()==true){
                            choice -= 1;
                            button.setIcon(new ImageIcon());
                        }
                    }
                    @Override  
                    public void mouseEntered(MouseEvent e) {
                        JToggleButton button = (JToggleButton)e.getSource();
                        button.setBackground(new Color(200,125,125));
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        JToggleButton button = (JToggleButton)e.getSource();
                        button.setBackground(new Color(170,170,170));
                        
                    }
                });
                jb[i][j].setBounds(button_x+60*i,button_y+66*j,50,50);
                contentPane.add(jb[i][j]);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1366, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 768, Short.MAX_VALUE)
        );

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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new seatFrame(5).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
