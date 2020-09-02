package com.netposa.template.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrUtil {

    /**
     * 生成6位随机数
     */
    public static int getRandownBy6() {
        int result = (int) (Math.random() * (999999 - 100000) + 100000);
        return result;
    }

    /**
     * 生成5位随机数
     */
    public static int getRandownBy5() {
        int result = (int) (Math.random() * (99999 - 10000) + 10000);
        return result;
    }

    /**
     * 生成8位随机数
     */
    public static int getRandownBy8() {
        int result = (int) (Math.random() * (99999999 - 10000000) + 10000000);
        return result;
    }

    /**
     * 取某个区间范围内的随机数
     */
    public static String getRandom(int min, int max) {
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return String.valueOf(s);

    }

    /**
     * 填空白
     * x 需要填几个空白字符串
     */
    public static String fixEmpty(int x) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < x; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    public static List<String> imgSuffix() {
        String str = "png,jpg,jpeg,bmp,gif,webp,psd,svg,tiff";
        return Arrays.asList(str.split(","));
    }

    /**
     * unicode转中文
     */

    public static String unicodeToCN(String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            //group 6728
            String group = matcher.group(2);
            //ch:'木' 26408
            ch = (char) Integer.parseInt(group, 16);
            //group1 \u6728
            String group1 = matcher.group(1);
            str = str.replace(group1, ch + "");
        }
        return str;
    }

    /**
     * 生成X位随机字符串
     *
     * @param Len
     * @return
     */
    public static String getRandomString(int Len) {
        String[] baseString = {"0", "1", "2", "3",
                "4", "5", "6", "7", "8", "9",
                "a", "b", "c", "d", "e",
                "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o",
                "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y",
                "z", "A", "B", "C", "D",
                "E", "F", "G", "H", "I",
                "J", "K", "L", "M", "N",
                "O", "P", "Q", "R", "S",
                "T", "U", "V", "W", "X", "Y", "Z"};
        Random random = new Random();
        int length = baseString.length;
        String randomString = "";
        for (int i = 0; i < length; i++) {
            randomString += baseString[random.nextInt(length)];
        }
        random = new Random(System.currentTimeMillis());
        String resultStr = "";
        for (int i = 0; i < Len; i++) {
            resultStr += randomString.charAt(random.nextInt(randomString.length() - 1));
        }
        return resultStr;
    }


}
