/*
* FILE :            MovieDetailFragment.java
* PROJECT :         MAD - Assignment #1
* PROGRAMMER :      Dong Qian (6573448) / Austin chen () / Monira Sultana ()
* FIRST VERSION :   2017-01-25
* DESCRIPTION :     Detail WebView Screen with the specific Movies.
*                   Open the movie IMDB url in the WebView
*/

package com.qiandongyqgmail.vipmovie;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MovieDetailFragment extends Fragment {

    /*-PRIVATE ATTRIBUTES-*/
    private String url = null;
    WebView movie_wv = null;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    // Use to get the url of the specific movie when detail button click on the main screen
    public void setParameter(String url){
        this.url = url;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        // Create the WebView
        movie_wv = new WebView(getActivity());
        // Link the WebView to the widget
        movie_wv = (WebView) root.findViewById(R.id.webView);
        // Create the WebView Setting to set some attributes for WebView
        WebSettings webSettings = movie_wv.getSettings();
        // Enable JavaScrip in WebView
        webSettings.setJavaScriptEnabled(true);
        // Create the WebViewCLient in order to open url in the Our app not in the Android Browser
        movie_wv.setWebViewClient(new MyWebViewClient());
        // Open the url
        movie_wv.loadUrl(url);

        return root;
    }


    // Create WebViewClient and overrid the load url to use WebView widget open url
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
