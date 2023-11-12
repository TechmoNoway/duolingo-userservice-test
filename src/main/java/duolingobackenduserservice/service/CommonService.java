package duolingobackenduserservice.service;

public interface CommonService {
    String createCurrentDate();

    String generateRandomNumber(int length);

    boolean areTwoDatesMoreThanOneDayApart(String dateString1, String dateString2);
}
