/*
* FILE :            MovieRegisterActivity.java
* PROJECT :         MAD - Assignment #1
* PROGRAMMER :      Dong Qian (6573448) / Austin Che (7243322) / Monira Sultana ()
* FIRST VERSION :   2017-01-25
* DESCRIPTION :
*/
package com.qiandongyqgmail.vipmovie;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;

public class MovieRegisterActivity extends AppCompatActivity
{
    /*-PRIVATE ATTRIBUTES-*/
    //-Widget specific fields-//
    private TextView movieTitle = null;
    private EditText etName = null;
    private EditText etDate = null;
    private EditText etTime = null;
    private Button bttnDate = null;
    private Button bttnTime = null;
    private RadioButton radioRegular = null;
    private RadioButton radioIMAX = null;
    private Spinner ticketDropDown = null;

    /*-String specific registration variables-*/
    private String title[] = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_register);

        // Loading all the specified widget IDs
        movieTitle = (TextView) findViewById(R.id.TV_Register_Title);
        ticketDropDown = (Spinner) findViewById(R.id.Spinner_Quantity);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ticket_quantity, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        ticketDropDown.setAdapter(adapter);


        // From the MainActivity, get the position...
        // We must access the resources to acquire the position?
        int mPosition = getIntent().getIntExtra("title", 0);
        Resources resources = getResources();
        title = resources.getStringArray(R.array.movie_title);

        movieTitle.setText(title[mPosition]);
//        Log.i("Array Position", String.valueOf(mPosition));
    }
}
