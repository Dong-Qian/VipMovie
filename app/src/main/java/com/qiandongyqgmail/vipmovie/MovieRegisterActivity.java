package com.qiandongyqgmail.vipmovie;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

public class MovieRegisterActivity extends AppCompatActivity
{
    /*-PRIVATE ATTRIBUTES-*/
    private TextView movieTitle = null;


    /*-String specific registration variables-*/
    private String title[] = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_register);

        // Loading all the specified widget IDs
        movieTitle = (TextView) findViewById(R.id.TV_Register_Title);

        // From the MainActivity, get the position...
        // We must access the resources to acquire the position?
        int mPosition = getIntent().getIntExtra("title", 0);
        Resources resources = getResources();
        title = resources.getStringArray(R.array.movie_title);

        movieTitle.setText(title[mPosition]);
//        Log.i("Array Position", String.valueOf(mPosition));
    }
}
