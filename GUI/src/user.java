import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Image;
public class user {
    String userId, userPassword, date, time, accountId;
    int adultCount, youthCount, movieId;
    ImageIcon poster;

    public user(String userId, String userPassword){
        this.userId=userId;
        this.userPassword=userPassword;
    }

    public void setReserveMovie(String date, String time, int adultCount, int youthCount, int movieId){
        this.date=date;
        this.time=time;
        this.adultCount=adultCount;
        this.youthCount=youthCount;
        this.movieId=movieId;
    }
    public ImageIcon getsmallIcon() {
        Image img=poster.getImage();
		Image resizedImage = img.getScaledInstance(160, 240, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}
    public String getDate(){
        return this.date;
    }
    public String getTime(){
        return this.time;
    }
    public int getadultCount(){
        return this.adultCount;
    }
    public int getyouthCount(){
        return this.youthCount;
    }
    public void setIcon(ImageIcon poster){
        this.poster=poster;
    }
    public Icon getIcon(){        
        return this.poster;
    }
    public void setmoviedId(int movieId){
        this.movieId=movieId;
    }
    public int getmovieId(){
        return this.movieId;
    }
}
