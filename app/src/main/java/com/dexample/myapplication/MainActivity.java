package com.dexample.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dexample.myapplication.models.Room;
import com.dexample.myapplication.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = MainActivity.class.getSimpleName();


    /**
     * Called when the activity is first created.
     */
    static int a = 0;
    Button but;
    Button but2;
    ImageView img;
    String search;

    Drawable Img1;
    Drawable Img2;
    Drawable Img3;
    EditText edt;//이걸 인텐트로 넘겨야함

    private DatabaseReference mUserReference;
    private ValueEventListener mValueEventListener;

    String[] RoomName;
    String[] RoomDate;
    String[] RoomMaxSize;
    String[] RoomSize_now;
    String[] Roomnumber;

    String[] UserName;
    String[] UserPhone;
    String[] UserDate;
    String[] UserPaWd;
    public static Context mContext;

    Button examplego,startgo,backgo;
    int num_User;
    int num_Room;
    int num_all;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mContext=this;

        startgo = (Button) findViewById(R.id.start_bt);
        startgo.setOnClickListener(buttonListener);

        examplego = (Button)findViewById(R.id.listen_examp);
        startgo = (Button)findViewById(R.id.start_bt);
        backgo = (Button)findViewById(R.id.finish_bt);


        examplego.setOnClickListener(new View.OnClickListener() {//다음과 같이 행동
            @Override
            public void onClick(View v) {//각각 엑티비티로 이동

                Intent a = new Intent(MainActivity.this, Example.class);
                startActivity(a);
            }
        });
        startgo.setOnClickListener(new View.OnClickListener() {//다음과 같이 행동
            @Override
            public void onClick(View v) {//각각 엑티비티로 이동

                Intent a = new Intent(MainActivity.this, Login.class);

                startActivity(a);
            }
        });

        backgo.setOnClickListener(new View.OnClickListener() {//다음과 같이 행동
            @Override
            public void onClick(View v) {//각각 엑티비티로 이동

                finish();

            }
        });


        mUserReference = FirebaseDatabase.getInstance().getReference().child("users");

       // UserController.createUser("김남훈", "0000", "010-3901-6292");
       // UserController.createUser("KL", "0000", "010-3996-3197");
       // RoomController.createRoom("TestRoom","dd","123","테스트용입니다","5",false);
    }

    @Override
    protected void onStart() {
        super.onStart();

        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                num_all=0;
                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    num_all++;
                    Log.e("num_all = ",num_all+"");
                }
                RoomName        =new String[num_all];
                RoomDate         =new String[num_all];
                RoomMaxSize     =new String[num_all];
                RoomSize_now    =new String[num_all];
                Roomnumber      =new String[num_all];

                UserName         = new String[num_all];
                UserPhone        = new String[num_all];
                UserDate        = new String[num_all];
                UserPaWd        = new String[num_all];
                num_User =0;
                for(DataSnapshot child : dataSnapshot.getChildren()) {


                    Log.e("num_User = ",num_User+"");
                    User user = User.parseSnapshot(child);
                    if(user.phoneNumber!=null) {
                        Log.d(TAG, "User Name        : " + user.userName);
                        Log.d(TAG, "User Date        : " + user.userDate);
                        Log.d(TAG, "User PhoneNumber : " + user.phoneNumber);
                        UserName[num_User] = user.userName;
                        UserPhone[num_User] = user.userDate;
                        UserDate[num_User] = user.phoneNumber;
                        UserPaWd[num_User]  = user.userPassword;
                        num_User++;
                    }

                    Room room = Room.parseSnapshot(child);
                    if(room.roomName!=null) {
                        Log.e("num_User = ",num_Room+"");

                        RoomName[num_User] = room.roomName;
                        RoomDate[num_User] = room.roomdate;
                        RoomMaxSize[num_User] = room.roomsize;
                        RoomSize_now[num_User] = room.roomsize_now;
                        Roomnumber[num_User] = room.roomNumber;

                        Log.d(TAG, "Room Name        : " + room.roomName);
                        Log.d(TAG, "Room Date        : " + room.roomdate);
                        Log.d(TAG, "Room info : " + room.roominfo);
                        Log.d(TAG, "Room number : " + room.roomNumber);

                        num_Room++;

                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        if(mUserReference != null) {
            mUserReference.addValueEventListener(userListener);
        }
        mValueEventListener = userListener;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mValueEventListener != null) {
            if(mUserReference != null) {
                mUserReference.removeEventListener(mValueEventListener);
            }
        }
    }

    View.OnClickListener buttonListener = new View.OnClickListener() {

        public void onClick(View V) {
            switch (V.getId()) {
                case R.id.start_bt:
                    a++;
                    if(a % 3 ==0) {
                        Toast.makeText(getApplicationContext(), "브라움", Toast.LENGTH_SHORT).show();
                        img.setImageDrawable(Img1);
                    }
                    else if(a % 3 == 1) {
                        img.setImageDrawable(Img2);
                        Toast.makeText(getApplicationContext(), "말파이트", Toast.LENGTH_SHORT).show();
                    }
                    else if(a % 3 == 2) {
                        img.setImageDrawable(Img3);
                        Toast.makeText(getApplicationContext(), "야스오", Toast.LENGTH_SHORT).show();
                    }
                    break;
                    //webView.goBack();
            }
        }
    };
    @Override
    protected void onPause() {
        super.onPause();
    }
}