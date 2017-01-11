package com.jk.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by user on 2016/12/14.
 */

public class HttpThread extends Thread{
    String url;
    String username;
    String password;

    public HttpThread(String url, String username, String password) {
        // TODO Auto-generated constructor stub
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private void doGet() throws IOException {
        /*将username和password传给Tomcat服务器*/
        url=url+"?username="+username+"&password="+password;
        try {
            URL httpUrl = new URL(url);
            /*获取网络连接*/
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            /*设置请求方法为GET方法*/
            conn.setRequestMethod("GET");
            /*设置访问超时时间*/
            conn.setReadTimeout(5000);
            BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String str;
            StringBuffer sb=new StringBuffer();
            //读取服务器返回的信息
            while((str=reader.readLine())!=null)
            {
                sb.append(str);
            }
            //把服务端返回的数据打印出来
            System.out.println("result"+sb.toString());
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /*在run中调用doGet*/
    @Override
    public void run() {
        try {
            doGet();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
