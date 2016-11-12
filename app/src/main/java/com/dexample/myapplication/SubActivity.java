package com.dexample.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by 개코1 on 2016-03-30.
 */
public class SubActivity extends Activity {
    WebView webView;
    Button but3;
    Button linkgo;
    Button findword;
    String rec;
    TextView tv;
    URL url;
    String link = "http://portal.kut.ac.kr";
    String link2 = "http://portal.koreatech.ac.kr/p/S12397?sel_menu_id=S12760";
    ProgressBar loadingbar;
    HttpURLConnection con;
    @Override
    public void onCreate(Bundle savedInstanceState) {//엑티비티가 생성될때
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.subactivity);
        loadingbar = (ProgressBar)findViewById(R.id.loadingbar);
        but3 = (Button) findViewById(R.id.button3);
        linkgo = (Button) findViewById(R.id.button4);
        findword = (Button)findViewById(R.id.button5);
        tv = (TextView)findViewById((R.id.textView));
        webView = (WebView)findViewById(R.id.webView);

        try{
            url = new URL(link);

    } catch (MalformedURLException e) {

        e.printStackTrace();
    }
        //webView.goBack();//뒤로가기
       // webView.goForward();//앞으로가기
       //// webView.reload();//새로고침
       // webView.zoomIn();
      //  webView.zoomOut();
        WebSettings setting = webView.getSettings();
        setting.setDomStorageEnabled(true); // 만약의 오류를 대비해 옵션을 true라 설정
        setting.setJavaScriptEnabled(true);
        setting.setSupportZoom(true);
        webView.setWebViewClient(new WebViewClient()); // 이걸 안해주면 새창이 뜸
        webView.getSettings().setJavaScriptEnabled(true); // javascript를 실행할 수 있도록 설정
        webView.getSettings().setDomStorageEnabled(true); // javascript를 실행할 수 있도록 설정
        webView.getSettings().setSupportZoom(true); // 확대,축소 기능을 사용할 수 있도록 설정
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); // 웹뷰가 캐시를 사용하지 않도록 설정
        webView.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                loadingbar.setVisibility(View.VISIBLE);
            }
            public void onPageFinished(WebView view,String Url){
                loadingbar.setVisibility(View.GONE);
            }
        });


/*
sf_1.setWebViewClient(new WebClient()); //직접 url 처리



 */

        //webView.loadUrl("https://google.co.kr");
        Intent intent2 = this.getIntent();
        String search = intent2.getStringExtra("search");
        rec = search;
   //     webView.loadUrl("https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&ie=utf8&query="+rec);
        webView.loadUrl(link);
        Toast.makeText(getApplicationContext(), "네이버", Toast.LENGTH_SHORT).show();


        //쿠키저장

        String  m_strFacebookCookieData = null;
        CookieSyncManager m_cookieSyncManager  = null;
        CookieManager m_cookieManager  = null;
        m_cookieSyncManager = CookieSyncManager.createInstance(this);
        m_cookieSyncManager.sync();
        m_cookieManager = CookieManager.getInstance();
        m_strFacebookCookieData = m_cookieManager.getCookie("http://portal.kut.ac.kr");

        tv.setText(m_strFacebookCookieData);
        //
////1112 세션유지
        try {
            con = (HttpURLConnection) url.openConnection();

            String COOKIES_HEADER = "Set-Cookie";


            con.connect();


            Map<String, List<String>> headerFields = con.getHeaderFields();
            List<String> cookiesHeader = headerFields.get(COOKIES_HEADER);

            if (cookiesHeader != null) {
                for (String cookie : cookiesHeader) {
                    String cookieName = HttpCookie.parse(cookie).get(0).getName();
                    String cookieValue = HttpCookie.parse(cookie).get(0).getValue();

                    String cookieString = cookieName + "=" + cookieValue;

                    CookieManager.getInstance().setCookie("http://portal.kut.ac.kr", cookieString);

                }
            }//////
        }
            catch (IOException e) {

                e.printStackTrace();
            }
        but3.setOnClickListener(new View.OnClickListener() {//다음과 같이 행동
            @Override
            public void onClick(View v) {//각각 엑티비티로 이동
                Intent b = new Intent(SubActivity.this, MainActivity.class);
                startActivity(b);

            }
        });
        linkgo.setOnClickListener(new View.OnClickListener() {//다음과 같이 행동
            @Override
            public void onClick(View v) {//각각 엑티비티로 이동
                webView.loadUrl(link2);

            }
        });
        findword.setOnClickListener(new View.OnClickListener() {//다음과 같이 행동
            @Override
            public void onClick(View v) {//각각 엑티비티로 이동
                Toast.makeText(getApplicationContext(), "네이버", Toast.LENGTH_SHORT).show();
                webView.findAllAsync("사무"); //works for API levels 16 and up

            }
        });




    }

    //뒤로가기 버튼이 검색//
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            event.startTracking();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.isTracking()
                && !event.isCanceled()) {
            if(webView.canGoBack())   //뒤로갈페이지가있으면
                webView.goBack();
            else
                finish();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();//정지상태일 때 무조건 꺼준다. 이유는 메인엑티비티와 같다
    }

    class WebClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(link);
            return true;
        }
    }
}