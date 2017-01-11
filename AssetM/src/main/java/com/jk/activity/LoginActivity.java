package com.jk.activity;


import android.app.Activity;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends Activity {
     //声明用到的组件
     private Button login,logout;
     private EditText userName, userPassword;
     private String url_ip;

     /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //获取ip
        url_ip = getResources().getString(R.string.url_ip);
        //事例画所有控件
        login = (Button) this.findViewById(R.id.sign_in_button);
        logout = (Button) this.findViewById(R.id.sign_out_button);
        userName = (EditText) this.findViewById(R.id.user);
        userPassword = (EditText) this.findViewById(R.id.password);
        //给按钮添加监听事件
        login.setOnClickListener(onClickListener);
        logout.setOnClickListener(onClickListener);
        //toast test
        /*Toast.makeText(getApplicationContext(), "1111111111",
                Toast.LENGTH_LONG).show();*/

    }

    /**
     * 按钮监听类，处理按钮事件
     */
    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if (v.getId() == R.id.sign_in_button) {
                String nameString = userName.getText().toString();
                String password = userPassword.getText().toString();
                login(nameString, password);
            }
            if (v.getId() == R.id.sign_out_button) {
                //退出，关闭当前栈中所有activity
                finishAffinity();
            }

            //保留。。。。。
        /*    if(v.getId()==R.id.no){
                LoginActivity.this.finish();
            }*/
        }
    };

    /**
     * 屏蔽返回键
     *
     */
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        System.out.println("按下了back键   onBackPressed()");
    }

    /**
     * 自定义一个消息提示窗口
     *
     * @param msg
     */
    private void showDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg).setCancelable(false).setPositiveButton("确定",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // TODO Auto-generated method stub

                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void login(String username, String password) {
        //发起请求，要访问的HttpServlet

        String urlStr = "http://"+url_ip+":8080/webtest/login?";
        //要传递的数据
        String query = "username=" + username + "&password=" + password;
        urlStr += query;
        //调用异步线程处理
        new LoginAsyncTask().execute(urlStr);
    }

    //异步任务处理
    private class LoginAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String result = null;
            if (params != null && params.length != 0) {
                String urlStr = params[0];
                HttpURLConnection conn = null;
                InputStream is = null;
                try {
                    URL url = new URL(urlStr);
                    //获得连接
                    conn = (HttpURLConnection) url.openConnection();
                    if (conn != null) {
                        //获得输入流
                        is = conn.getInputStream();
                        //创建一个缓冲字节数
                        byte[] buffer = new byte[1024];
                        int readCount = 0;
                        StringBuilder sb = new StringBuilder();
                        while ((readCount = is.read(buffer)) != -1) {
                            sb.append(new String(buffer, 0, readCount));
                        }
                        //将字节转换成字符串
                        result = sb.toString();
                    } else {
                        result = "连接失败";
                    }
                } catch (Exception e) {
                    result = e.getMessage();
                } finally {
                    if (conn != null) {
                        conn.disconnect();
                    }
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return result;
        }

        //获取网页处理返回登录结果
        @Override
        protected void onPostExecute(String s) {
            if (s != null) {
                showDialog(s);
                if(s.equals("登录成功")){
                    finish();
                }
            } else {
                showDialog("登录失败");
            }
        }
    }


}

