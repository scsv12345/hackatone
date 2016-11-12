package com.dexample.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;

/**
 * Created by 개코1 on 2016-11-13.
 */
public class Example extends AppCompatActivity {


    private Button join_do, phone_confirm;
    RelativeLayout back;
    private EditText u_phoneNumj, u_passwordj, u_Namej , u_password_cf ;
    private CheckBox person_agree,over_agree;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.example);



    }



    @Override
    protected void onResume() {
        super.onResume();
        join_do = (Button)findViewById(R.id.join_Do_BT);
        phone_confirm = (Button)findViewById(R.id.userPhoneNumber_CF_BT);
        u_phoneNumj = (EditText)findViewById(R.id.userPhoneNumber_edit);
        u_passwordj = (EditText)findViewById(R.id.userPassword_edit);
        u_Namej = (EditText)findViewById(R.id.userName_edit);
        u_password_cf = (EditText)findViewById(R.id.userPasswordCF_edit);
        person_agree = (CheckBox)findViewById(R.id.Cbox_infoshare);
        over_agree = (CheckBox)findViewById(R.id.Cbox_agree);
        back = (RelativeLayout)findViewById(R.id.backbutton_layout);

        back.setOnClickListener(new View.OnClickListener() {//다음과 같이 행동
            @Override
            public void onClick(View v) {//각각 엑티비티로 이동
                Intent a = new Intent(Example.this, MainActivity.class);
                startActivity(a);

            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();//정지상태일 때 무조건 꺼준다. 이유는 메인엑티비티와 같다
    }
}
