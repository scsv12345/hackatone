package com.dexample.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by 개코1 on 2016-11-13.
 */
public class Login extends AppCompatActivity {

    private Context context;
    private ImageButton login_do,join_do_IB;
    private EditText u_phoneNum, u_password;
    private Boolean isLogin;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        context = this;

        init();
    }

    private void init() {
//        login_do = (ImageButton)findViewById(R.id.login_Do_IB);
//        join_do_IB = (ImageButton)findViewById(R.id.join_do_IB);

//        u_phoneNum = (EditText)findViewById(R.id.login_PNum_edit);
//        u_password = (EditText)findViewById(R.id.login_PS_edit);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();//정지상태일 때 무조건 꺼준다. 이유는 메인엑티비티와 같다
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.login_Do_IB:
//                for(int i = 0; i > ((MainActivity)MainActivity.mContext).num_User; i++){
//                    if(((MainActivity)MainActivity.mContext).UserPhone[i].equals(u_phoneNum.getText().toString())) {
//                        //하나라도 같으면
//                        if (((MainActivity) MainActivity.mContext).UserPaWd[i].equals(u_password.getText().toString()))
//                        //비번까지 같으면 로그인 성공!
//                        {
//                            isLogin = true;
//                            Intent a = new Intent(Login.this, MainActivity.class);
//                            startActivity(a);
//                        }
//                    }
//                    else
//                        Log.e("비밀번호가 틀렷습니다.","   ");
//
//                }
//                break;
//            case R.id.join_do_IB:
//                Intent a = new Intent(Login.this, Join.class);
//                startActivity(a);
//                break;
//        }
//    }
}
