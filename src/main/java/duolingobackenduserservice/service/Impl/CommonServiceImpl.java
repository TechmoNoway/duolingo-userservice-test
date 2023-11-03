package duolingobackenduserservice.service.Impl;

import duolingobackenduserservice.service.CommonService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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
}
