package com.dexample.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import com.dexample.myapplication.controllers.UserController;

/**
 * Created by 개코1 on 2016-11-13.
 */
public class Join extends AppCompatActivity {

    private ImageButton join_do;
    private Button  phone_confirm;
    private EditText u_phoneNumj, u_passwordj, u_Namej , u_password_cf ;
    private CheckBox person_agree,over_agree;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.join);

        //UserController.createUser("김남훈", "0000", "010-3901-6292");

    }




    @Override
    protected void onResume() {
        super.onResume();


        join_do = (ImageButton)findViewById(R.id.join_Do);
        phone_confirm = (Button)findViewById(R.id.userPhoneNumber_CF_BT);
        u_phoneNumj = (EditText)findViewById(R.id.userPhoneNumber_edit);
        u_passwordj = (EditText)findViewById(R.id.userPassword_edit);
        u_Namej = (EditText)findViewById(R.id.userName_edit);
        u_password_cf = (EditText)findViewById(R.id.userPasswordCF_edit);
        person_agree = (CheckBox)findViewById(R.id.Cbox_infoshare);
        over_agree = (CheckBox)findViewById(R.id.Cbox_agree);

        phone_confirm.setOnClickListener(new View.OnClickListener() {//다음과 같이 행동
            @Override
            public void onClick(View v) {//각각 엑티비티로 이동


            }
        });


        join_do.setOnClickListener(new View.OnClickListener() {//다음과 같이 행동
            @Override
            public void onClick(View v) {//각각 엑티비티로 이동

                if(u_phoneNumj!=null&&u_passwordj!=null&&u_Namej!=null) {
                    UserController.createUser(u_Namej.getText().toString(), u_passwordj.getText().toString(), u_phoneNumj.getText().toString());
                    Log.e("회원가입 완료! : ",u_Namej.getText().toString()+"님");
                }
            }
        });


    }


}
