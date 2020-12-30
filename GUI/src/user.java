import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.ArrayList;

public class user {
    String userId, date, time;
    int adultCount, youthCount, movieId, ScreeningId;
    ImageIcon poster;
    public ArrayList<Integer> selectedSeat = new ArrayList<>();
    static int accountId;

    public user(String userId) {
        this.userId = userId;
    }

    public void setReserveMovie(String date, String time, int adultCount, int youthCount, int movieId) {
        this.date = date;
        this.time = time;
        this.adultCount = adultCount;
        this.youthCount = youthCount;
        this.movieId = movieId;
    }

    public ImageIcon getsmallIcon() {
        Image img = poster.getImage();
        Image resizedImage = img.getScaledInstance(180, 252, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    public void resetSeat() {
        this.selectedSeat = new ArrayList<>();
    }

    public String getDate() {
        return this.date;
    }

    public String getTime() {
        return this.time;
    }

    public int getadultCount() {
        return this.adultCount;
    }

    public int getyouthCount() {
        return this.youthCount;
    }

    public void setIcon(ImageIcon poster) {
        this.poster = poster;
    }

    public Icon getIcon() {
        return this.poster;
    }

    public void setmoviedId(int movieId) {
        this.movieId = movieId;
    }

    public int getmovieId() {
        return this.movieId;
    }
}
