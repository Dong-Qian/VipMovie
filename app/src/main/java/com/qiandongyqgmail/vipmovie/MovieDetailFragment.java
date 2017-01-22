package com.qiandongyqgmail.vipmovie;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * Created by Dong Qian on 1/20/2017.
 */

public class MovieDetailFragment extends Fragment {

    private String url = null;
    WebView movie_wv = null;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public void setParameter(String url){
        this.url = url;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_movie_detail, container, false);



        movie_wv = new WebView(getActivity());

        movie_wv = (WebView) root.findViewById(R.id.webView);
        WebSettings webSettings = movie_wv.getSettings();
        webSettings.setJavaScriptEnabled(true);


        movie_wv.setWebViewClient(new MyWebViewClient());
        movie_wv.loadUrl(url);

        return root;
    }



    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }



}
