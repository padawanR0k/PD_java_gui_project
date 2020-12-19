public class movie {
    private String date, time;
    private int adultCount, youthCount, price;

    public movie(String date, String time, int adultCount, int youthCount){
        this.date = date;
        this.time = time;
        this.adultCount = adultCount;
        this.youthCount = youthCount;
        this.price = adultCount*12000 + youthCount*8000;
    }
    public String getDate(){
        return date;
    }
    public String getTime(){
        return time;
    }
    public int getAdultCount(){
        return adultCount;
    }
    public int getYouthCount(){
        return youthCount;
    }
    public int getPrice(){
        return price;
    }
}
