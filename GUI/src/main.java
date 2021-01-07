import java.awt.Image;
import java.io.File;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class main {
    static HashMap<Integer, ImageIcon> posterList = new HashMap<>(); // 영화 포스터 리스트

    public main(){
        File path = new File("./image/poster/");
        if (path.exists()) {
        	System.out.println(path.list().length);
    		String[] fileNames = path.list();
    		for (int i = 0; i < fileNames.length; i++) {
                Integer movieId = (Integer) Integer.parseInt(fileNames[i].replace("poster_", "").replace(".jpg", ""));
    			posterList.put(movieId, resizeIcon(new ImageIcon("./image/poster/" + fileNames[i]),230,328));
            }
        }
        new loginFrame().setVisible(true);
    }

    public static ImageIcon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
		Image img = icon.getImage();
		Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}

    public static void main(String args[]){
        new main();
    }
}
