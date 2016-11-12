package com.dexample.myapplication;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Created by 개코1 on 2016-08-22.
 */
public class Splash extends AppCompatActivity {
    Intent intent;
    String result_down;
    Button reset;
    public static Context lodingContext;
    static boolean end = false;
    RelativeLayout a,b;
    static boolean connectF = false;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.splashview);
        reset = (Button)findViewById(R.id.net_errbut) ;
        reset.setVisibility(View.GONE);
        if (isNetWork()) {
            connectF = false;

            if (end == true) {
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            } else {
                intent = new Intent(this, MainActivity.class);
                startActivityWithDelay();
            }
        }
        else {

            reset.setVisibility(View.VISIBLE);
            Log.e("연결 안 됨", "연결이 다시 한번 확인해주세요");
            TextView a = (TextView) findViewById(R.id.net_errtext);
            a.setText("인터넷을 연결해주세요.");
            a.setTextSize(30);


            connectF = true;
        }

    }
    public void startActivityWithDelay(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            public void run() {
                end = true;
                startActivity( intent );
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
            //Do Something 1000 = 1s
        }, 1000);

    }
    public void onBackPressed(){}//로딩중 백버튼 막기
    /////////////////////////스케줄, 알람 웹앱과 연동/////////////////////////////

    private Boolean isNetWork(){
        ConnectivityManager manager = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);
        boolean isMobileAvailable = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isAvailable();
        boolean isMobileConnect = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        boolean isWifiAvailable = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isAvailable();
        boolean isWifiConnect = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();

        if ((isWifiAvailable && isWifiConnect) || (isMobileAvailable && isMobileConnect)){
            return true;
        }else{
            return false;
        }
    }
    public void reset(View v) {


        if (v.getId() == R.id.net_errbut && connectF) {
            connectF =false;
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();

        }
    }
}

