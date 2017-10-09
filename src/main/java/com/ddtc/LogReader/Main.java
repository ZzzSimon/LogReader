package com.ddtc.LogReader;

import java.io.*;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/9/21.
 */
public class Main {

    private static Executor threadPool = Executors.newCachedThreadPool();
    public static BlockingQueue<MatchedRequest> blockingquque = new LinkedBlockingQueue<>();

    private static final int LINES_EVERY_FILE = 3;//每个文件读几行日志，建议  值  >=3



    public static void main(String[] args) throws IOException, InterruptedException {
        //启动上传线程
        new Thread(new LogUpLoader()).start();

        String logFilePath = "./logs/logback-access.2017-09-21.log";
        //先将源文件读取到内存中
        File srcFile = new File(logFilePath);//创建一个文件对象
        BufferedReader bufferedReader = new BufferedReader(new FileReader(srcFile));

        int fileNum = 0;
        String s = bufferedReader.readLine();
        while (s != null) {
            CopyOnWriteArrayList<String> spilts = new CopyOnWriteArrayList<>();
            fileNum++;
//            File splitFile = new File(logFilePath+"["+fileNum+"]"+".split");
//            splitFile.createNewFile();
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(splitFile));
            for (int i = 0; i < LINES_EVERY_FILE; i++) {

                if (s != null) {
                    //  " " 开头的为response  " "结尾的为request
                    if (!(s.endsWith(" ") && s.startsWith(" "))) {
                        System.out.println(s + "\n----------");
                        spilts.add(s);

                    }
                    s = bufferedReader.readLine();
                } else {
                    break;
                }

            }


            //System.out.println("**************");
            threadPool.execute(new SplitTask(spilts));


        }


        bufferedReader.close();



    }
}
