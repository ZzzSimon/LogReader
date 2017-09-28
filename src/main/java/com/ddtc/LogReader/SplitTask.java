package com.ddtc.LogReader;

import java.io.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/9/28.
 */
public class SplitTask implements Runnable{

    //private static String pattern = "((\\d+\\.){3}\\d+) (\\d+/\\W+/(\\d+:){3}\\d+ \\+\\d+) (\\D+) (\\S*) HTTP/1\\.0 (\\d{3}) - spend:(\\d+) ";
    private static String pattern = ".*";
    private static Pattern r = Pattern.compile(pattern);



    private CopyOnWriteArrayList<String> spilts;


    SplitTask(CopyOnWriteArrayList<String> spilts){
        this.spilts = spilts;
    }


    @Override
    public void run() {


                for (int i = 0;i<spilts.size();i++
                     ) {
                    //TODO 正则表达式匹配后，把String分装成一个MatchedRequest对象
                    //System.out.println(spilts.get(i));
                    Matcher m = r.matcher(spilts.get(i));

                    if(m.find()) {
                        MatchedRequest matchedRequest = new MatchedRequest();
                        //System.out.println("g(0):"+m.group(0));
                        matchedRequest.setIp(m.group(0));
                        Main.blockingquque.add(matchedRequest);
                        matchedRequest = null;
                    }

                }





    }
}
