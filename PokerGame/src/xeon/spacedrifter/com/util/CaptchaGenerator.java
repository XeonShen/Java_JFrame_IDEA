package xeon.spacedrifter.com.util;

import java.util.ArrayList;
import java.util.Random;

public class CaptchaGenerator {
    public static String getCaptcha() {
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            list.add((char)('a' + i));
            list.add((char)('A' + i));
        }

        String result = "";
        Random r = new Random();
        for (int i = 0; i < 3; i++) {
            int randomIndex = r.nextInt(list.size());
            char c = list.get(randomIndex);
            result = result + c;
        }

        int number = r.nextInt(10);
        result = result + number;

        char[] chars = result.toCharArray();
        int randomIndex = r.nextInt(chars.length);
        char temp = chars[3];
        chars[3] = chars[randomIndex];
        chars[randomIndex] = temp;

        return new String(chars);
    }

}
