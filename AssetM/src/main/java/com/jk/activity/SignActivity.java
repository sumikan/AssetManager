package com.jk.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jk.threads.HttpThread;

public class SignActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button signup;
    private String url_ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取ip
        url_ip = getResources().getString(R.string.url_ip);

        username = (EditText) findViewById(R.id.user_sign);
        password = (EditText) findViewById(R.id.password_sign);
        signup = (Button) findViewById(R.id.sign_up_button);
        setContentView(R.layout.activity_sign);
    }

    //发起Http请求
    public void onLogin(View v)
    {
        //获取对应的servlet地址
        String url="http://"+url_ip+":8080/webtest/";
        new HttpThread(url, username.getText().toString(), password.getText().toString()).start();
    }

}
