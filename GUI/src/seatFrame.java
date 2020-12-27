import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
    private int count, choice, adultCount, youthCount, movieId;
    private String date, time;
    private user my;
    public seatFrame(){}
    
    //seatFrame p = new seatFrame(date.getSelectedItem().toString(), time.getSelectedItem().toString(), adultCount, youthCount, movieId);
    //public seatFrame(String date, String time, int adultCount, int youthCount, int movieId) { // 좌석 개수
    public seatFrame(user my) { // 좌석 개수
        this.my = my;
        this.adultCount=my.getadultCount();
        this.youthCount=my.getyouthCount();
        this.count=adultCount+youthCount;
        this.movieId=my.getmovieId();
        this.date=my.getDate();
        this.time=my.getTime();
        this.choice = 0;
        initComponents();
        button_x = 574;
        button_y = 135;
        icon = new ImageIcon("./image/bg_seatFrame.jpg");
        ImageIcon backBtn = new ImageIcon("./image/btn/back_btn.png");

        contentPane = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), 0, 0, null);
                g.drawImage(my.getsmallIcon().getImage(),133,30,null);
                //my.getIcon().paintIcon(this, g, 133, 30);
                setOpaque(false); //그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
            }
        };
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton backButton = new JButton(backBtn); // 뒤로가기 버튼
        backButton.setBounds(1245, 10, 85, 85);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setBackground(Color.WHITE);
        contentPane.add(backButton);
        backButton.addActionListener(new ActionListener() { // mainFrame으로 이
			public void actionPerformed(ActionEvent e) {
				reserveFrame m = new reserveFrame(my);
				m.setVisible(true);
				dispose();
			}
		});

        // 추가해야함
        JLabel dateLabel = new JLabel(date);
        JLabel timeLabel = new JLabel(time);
        JLabel adultC = new JLabel(adultCount+"");
        JLabel youthC = new JLabel(youthCount+"");
        JLabel price = new JLabel(adultCount*13000+youthCount*10000+"");
        dateLabel.setBounds(160,340,200,100);
        timeLabel.setBounds(160,390,200,100);
        
        adultC.setBounds(160,500,200,100);
        youthC.setBounds(160,550,200,100);

        price.setBounds(160,630,200,100);
        contentPane.add(dateLabel);
        contentPane.add(timeLabel);
        contentPane.add(adultC);
        contentPane.add(youthC);
        contentPane.add(price);

        JButton reserveBtn = new JButton("예약하기");
        reserveBtn.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                payFrame s = new payFrame(my);
				s.setVisible(true);
				dispose();
            }
        });
        reserveBtn.setBounds(300, 560, 100, 100);
        contentPane.add(reserveBtn);
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
                        System.out.println(choice+button.getText());
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
}
