package com.ddtc.LogReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/9/29.
 */
class LogUpLoader implements Runnable{

    private static final String URL = "jdbc:mysql://127.0.0.1/web_logs?useSSL=false";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    @Override
    public void run() {
        Connection conn = null;
        PreparedStatement pst = null;
        int count = 0;
        try {

            //1.加载驱动程序
            Class.forName(DRIVER);
            //2.获得数据库链接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            //3.通过数据库的连接操作数据库，实现增删改查（使用PreparedStatement类）
            String sql = "INSERT INTO A_LOGS(ip) " +
                    "VALUES(?)";

            pst = conn.prepareStatement(sql);
            //TODO 上传到数据库


            for (; ; ) {
                count++;
                MatchedRequest matchedRequest = Main.blockingquque.poll(10, TimeUnit.SECONDS);
                if(matchedRequest != null) {
                    String ip = matchedRequest.getIp();
                    System.out.println(ip);
                    pst.setString(1, ip);
                    pst.execute();
                }else {
                    break;
                }

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }finally {
            try {
                pst.close();
                conn.close();
                System.out.println("本次共上传 【" +count+"】 条日志");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
