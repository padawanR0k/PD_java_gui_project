import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DB.DB;

/**
 *
 * @author newjihwan
 */
public class reserveFrame extends javax.swing.JFrame {
    private JPanel contentPane;
    private ImageIcon icon;
    private int count;
    private int adultCount = 0, youthCount = 0;
    private user my;
    private int movieId;
    private Icon poster;
    private Map<String, Integer> ScreeningIds = new HashMap<>();

    JComboBox<String> dateCbox;
    JComboBox<String> timeCbox;

    /**
     * Creates new form reserveFrame
     */
    public reserveFrame() {
    }

    // public reserveFrame(user my, int movieId, Icon poster) {
    public reserveFrame(user my) {
        this.my = my;
        this.movieId = my.getmovieId();
        this.poster = my.getIcon();
        adultCount = 0;
        youthCount = 0;
        DecimalFormat formats = new DecimalFormat("###,###");
        ImageIcon reserve1 = new ImageIcon("./image/reserve1.jpg");
        ImageIcon reserve2 = new ImageIcon("./image/reserve2.jpg");
        ImageIcon backBtn = new ImageIcon("./image/btn/back_btn2.png");
        icon = new ImageIcon("./image/bg_reserveFrame.jpg");

        initComponents();

        contentPane = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), 0, 0, null);
                poster.paintIcon(this, g, 100, 100);
                setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
            }
        };
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        JButton backButton = new JButton(backBtn); // 뒤로가기 버튼
        backButton.setBounds(1245, 10, 85, 85);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setBackground(Color.BLACK);
        contentPane.add(backButton);
        backButton.addActionListener(new ActionListener() { // mainFrame으로 이
            public void actionPerformed(ActionEvent e) {
                mainFrame m = new mainFrame(my);
                m.setVisible(true);
                dispose();
            }
        });
        dateCbox = new JComboBox<>();
        timeCbox = new JComboBox<>();
        this.drawDateComboBox();
        this.drawTimeComboBox();

        JLabel price = new JLabel(""); // 가격 price
        price.setBounds(700, 500, 1500, 50);
        price.setForeground(Color.white);
        price.setFont(price.getFont().deriveFont(30.0F));
        
        JLabel price2 = new JLabel(""); // 가격 price
        price2.setBounds(1080, 500, 1500, 50);
        price2.setForeground(Color.white);
        price2.setFont(price.getFont().deriveFont(30.0F));
        contentPane.add(price);
        contentPane.add(price2);

        JButton[] adult_btn_list = new JButton[10]; // 성인 adult
        for (int i = 0; i < 10; i++) {
            adult_btn_list[i] = new JButton("" + (i));
            adult_btn_list[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JButton button = (JButton) e.getSource();
                    if (Integer.parseInt(button.getText()) + youthCount > 10) {
                        JOptionPane.showMessageDialog(null, "10인 이하만 예매 가능합니다.");
                        return;
                    }
                    for (int i = 0; i < 10; i++) {
                        adult_btn_list[i].setIcon(new ImageIcon(""));
                    }
                    adult_btn_list[Integer.parseInt(button.getText())].setIcon(new ImageIcon("./image/selectedButton.png"));
                    adultCount = Integer.parseInt(button.getText());
                    //price.setText(adultCount * 13000 + youthCount * 10000 + " Won");
                    price.setText(String.format("13,000 X %d + 10,000 X %d", adultCount,youthCount));
                    price2.setText(String.format("= ￦%s", formats.format(adultCount*13000+youthCount*10000)));
                }
            });
            adult_btn_list[i].setBounds(700 + 60 * i, 342, 50, 50);
            contentPane.add(adult_btn_list[i]);
        }

        JButton[] youth_btn_list = new JButton[10]; // 어린이 youth
        for (int i = 0; i < 10; i++) {
            youth_btn_list[i] = new JButton("" + (i));
            youth_btn_list[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JButton button = (JButton) e.getSource();
                    if (Integer.parseInt(button.getText()) + adultCount > 10) {
                        JOptionPane.showMessageDialog(null, "10인 이하만 예매 가능합니다.");
                        return;
                    }
                    for (int i = 0; i < 10; i++) {
                        youth_btn_list[i].setIcon(new ImageIcon(""));
                    }
                    youth_btn_list[Integer.parseInt(button.getText())].setIcon(new ImageIcon("./image/selectedButton.png"));
                    youthCount = Integer.parseInt(button.getText());
                    price.setText(String.format("13,000 X %d + 10,000 X %d", adultCount,youthCount));
                    price2.setText(String.format("= ￦%s", formats.format(adultCount*13000+youthCount*10000)));
                    //String.format("13000 * %d + 10000 * %d", adultCount,youthCount)
                }
            });
            youth_btn_list[i].setBounds(700 + 60 * i, 395, 50, 50);
            contentPane.add(youth_btn_list[i]);
        }

        youth_btn_list[0].setIcon(new ImageIcon("./image/selectedButton.png"));
        adult_btn_list[0].setIcon(new ImageIcon("./image/selectedButton.png"));

        JButton choiceButton = new JButton(reserve1); // choose you seat 버튼
        choiceButton.setRolloverIcon(reserve2);
        choiceButton.setBounds(760, 620, 327, 68);
        contentPane.add(choiceButton);
        choiceButton.addActionListener(new ActionListener() { // seatFrame 불러오기
            public void actionPerformed(ActionEvent e) {
            	if(adultCount+youthCount==0){return;}
                my.setReserveMovie(dateCbox.getSelectedItem().toString(), timeCbox.getSelectedItem().toString(), adultCount,
                        youthCount, movieId);
                // seatFrame p = new seatFrame(date.getSelectedItem().toString(),
                // time.getSelectedItem().toString(), adultCount, youthCount, movieId);
                seatFrame p = new seatFrame(my);
                p.setVisible(true);
                dispose();
            }
        });
    }

    // DB 불러오기
    // 모든 영화관 정보 추가해야함.
    public void drawDateComboBox() {
        dateCbox.setBounds(700, 125, 250, 50);
        List<Map<String, Object>> info = this.getScreenByMovie(movieId);
        // date 컴포넌트에 일주일 치 날짜 추가.
        for (int i = 0; i < info.size(); i++) {
            String d = (String) info.get(i).get("screenDate");
            dateCbox.addItem(d);
        }

        fetchTime((String)(info.get(0).get("screenDate")));

        dateCbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    String date = (String)event.getItem();
                    fetchTime(date);
                }
            }
        });

        contentPane.add(dateCbox);
    }

    public void drawTimeComboBox() {
        timeCbox.setBounds(700, 200, 250, 50);
        timeCbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    String time = (String) event.getItem();
                    // fetchTime(date);
                    my.ScreeningId = ScreeningIds.get(time);
                }
            }
        });
        contentPane.add(timeCbox);
    }

    public void fetchTime(String date) {
        List<Map<String, Object>> info = getScreenByMovieAndDate(movieId, date);
        Map<String, Integer> ScreeningIds = new HashMap<>();
        timeCbox.removeAllItems();
        for (int i = 0; i < info.size(); i++) {
            String time  = (String) info.get(i).get("time");
            int ScreeningId  = (int) info.get(i).get("ScreeningId");
            timeCbox.addItem(time);
            ScreeningIds.put(time, ScreeningId);

            if (i == 0) {
                my.ScreeningId = ScreeningId;
            }
        }

        this.ScreeningIds = ScreeningIds;
    }

    /**
     * 해당영화의 예매가능한 날짜정보
     * @param MovieId
     * @return
     */
    public List<Map<String, Object>> getScreenByMovie(int MovieId) {
		DB db = new DB();
		List<Map<String, Object>> sub = db.query(String.format("select screenDate from theater.screening where MovieId = %d group by screenDate;",MovieId));
		return sub;
    }

    /**
     * 해당영화의 예매가능한 날짜정보
     * @param MovieId
     * @return
     */
    public List<Map<String, Object>> getScreenByMovieAndDate(int MovieId, String date) {
		DB db = new DB();
		List<Map<String, Object>> sub = db.query(String.format("select time, ScreeningId from theater.screening where MovieId = %d and screenDate = '%s';",MovieId, date));
		return sub;
    }

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
}
