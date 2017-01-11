package com.jk.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends Activity {

    EditText editText1;
    boolean isLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.main_activity);

                isLogin=false;

                final Intent iLogin = new Intent(MainActivity.this,LoginActivity.class);
                final Intent iLogout = new Intent(MainActivity.this,LogoutActivity.class);
                final Intent info = new Intent(MainActivity.this,InfoActivity.class);
                final Intent isign = new Intent(MainActivity.this,SignActivity.class);
                if(!isLogin) {
                    startActivity(iLogin);
                    //startActivity(iLogout);
                }
                ImageButton imageButton1 = (ImageButton) findViewById(R.id.scan_button);
                editText1 = (EditText) findViewById(R.id.editText);
                imageButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, DecoderActivity.class);
                        startActivityForResult(intent, 1);
                        //textView.setText(intValue);
            }
        });

        //test
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.b1) {//按钮1
                    startActivity(isign);
                } else if (v.getId() == R.id.b2) {//按钮2
                    startActivity(iLogout);
                }else if (v.getId()==R.id.b3){
                    //b3
                     startActivity(info);
                }
            }
        };

        Button b1,b2,b3;
        b1= (Button) findViewById(R.id.b1);
        b2= (Button) findViewById(R.id.b2);
        b3= (Button) findViewById(R.id.b3);
        

        b1.setOnClickListener(clickListener);
        b2.setOnClickListener(clickListener);
        b3.setOnClickListener(clickListener);


    }


                    @Override
                    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                        switch (requestCode) {
                            case 1:
                                if (resultCode == RESULT_OK) {
                                    String returnedData = data.getStringExtra("data_return");
                                    editText1.setText(returnedData);
                    Log.d("FirstActivity", returnedData);
                }
                break;
            default:
        }
    }
}
