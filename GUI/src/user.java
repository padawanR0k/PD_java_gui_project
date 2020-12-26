public class user {
    String userId, userPassword, date, time, movieId, accountId;
    int adultCount, youthCount;

    public user(String userId, String userPassword){
        this.userId=userId;
        this.userPassword=userPassword;
    }

    public user(String userId, String date, String time, String accountId, int adultCount, int youthCount){
        this.userId = userId;
        this.date = date;
        this.time = time;
        this.accountId = accountId;
        this.adultCount = adultCount;
        this.youthCount = youthCount;
    }

    public void setReserveMovie(String date, String time, int adultCount, int youthCount){
        this.date=date;
        this.time=time;
        this.adultCount=adultCount;
        this.youthCount=youthCount;
    }
}
