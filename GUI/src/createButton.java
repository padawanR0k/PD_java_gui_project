import javax.swing.JToggleButton;
import javax.swing.ImageIcon;

public class createButton {
    final int button_x = 574;
    final int button_y = 165;
    public createButton(){

    }
    public JToggleButton createJToggleButton(int i, int j){
        JToggleButton button = new JToggleButton((char) (65 + j) + "" + i); // 아스키
        button.setIcon(new ImageIcon("./image/choosebutton2.png"));
        button.setBounds(button_x + 60 * i, button_y + 66 * j, 50, 50);
        return new JToggleButton();
    }
}
