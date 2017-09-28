package com.ddtc.LogReader;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/9/28.
 */
public class RegExpText {
    //((\d+\.)\d+)


    public static void main(String[] args) {
        String pattern = "((\\d+\\.){3}\\d+) (\\d+/\\W+/(\\d+:){3}\\d+ \\+\\d+) (\\D+) (\\S*) HTTP/1\\.0 (\\d{3}) - spend:(\\d+) ";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher("223.3.134.130 20/九月/2017:12:52:42 +0800 GET " +
                "/ddServerOffline/wxapp/queryScanLockInfo?locks=%5B%5D&appVersion=1.4.0&OSVersion=iOS%2010.3.3&requestSource=wxapp " +
                "HTTP/1.0 200 - spend:0 ");

        if (m.find()) {
            System.out.println(m.group(0));
            System.out.println(m.group(1));
            System.out.println(m.group(2));
            System.out.println(m.group(3));

            System.out.println(m.group(4));
            System.out.println(m.group(5));
            System.out.println(m.group(6));

            System.out.println(m.group(7));
            System.out.println(m.group(8));
        } else {
            System.out.println("no");
        }

    }
}
