package io.gig.catchreview.core.domain.common;

import java.util.Random;

/**
 * @author : Jake
 * @date : 2021/08/12
 */
public class Utils {

    public static String randomDigitsGenerator() {
        Random rd = new Random();
        int number = rd.nextInt(999999);

        return String.format("%06d", number);
    }

}
