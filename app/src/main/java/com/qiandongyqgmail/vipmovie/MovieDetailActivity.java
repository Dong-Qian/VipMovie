package com.qiandongyqgmail.vipmovie;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MovieDetailActivity extends AppCompatActivity {

    private WebView movie_detail_vb = null;
    private int mPosition = 0;
    private String[] url = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent intent = getIntent();
        mPosition =  intent.getIntExtra("title", 0);

        movie_detail_vb = (WebView) findViewById(R.id.movie_detail_vb);
        WebSettings webSettings = movie_detail_vb.getSettings();
        webSettings.setJavaScriptEnabled(true);



        Resources resources = MovieDetailActivity.this.getResources();
        url = resources.getStringArray(R.array.movie_url);

        System.out.println(mPosition);

        System.out.println(url[mPosition]);

        movie_detail_vb.setWebViewClient(new MyWebViewClient());
        movie_detail_vb.loadUrl(url[mPosition]);
    }


    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
