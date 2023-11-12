package duolingobackenduserservice.service.Impl;

import duolingobackenduserservice.service.CommonService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Random;
@Service
public class CommonServiceImpl implements CommonService {
    @Override
    public String createCurrentDate() {
        Date currentDate = new Date();

        // Create a SimpleDateFormat object with the desired date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Format the current date using the SimpleDateFormat
        String formattedDate = dateFormat.format(currentDate);
        return formattedDate;
    }

    @Override
    public String generateRandomNumber(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }

        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10); // generates a random digit between 0 and 9
            sb.append(digit);
        }

        return sb.toString();
    }

    @Override
    public boolean areTwoDatesMoreThanOneDayApart(String dateString1, String dateString2) {
        if (dateString1 == null || dateString2 == null) {
            // Handle the case where either date string is null
            return false;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate date1 = LocalDate.parse(dateString1, formatter);
            LocalDate date2 = LocalDate.parse(dateString2, formatter);

            // Check if the two dates are more than one day apart
            return date1.plusDays(1).isEqual(date2) || date1.minusDays(1).isEqual(date2);
        } catch (DateTimeParseException e) {
            // Handle the case where date string is not in the expected format
            e.printStackTrace();
            return false;
        }
    }
}
