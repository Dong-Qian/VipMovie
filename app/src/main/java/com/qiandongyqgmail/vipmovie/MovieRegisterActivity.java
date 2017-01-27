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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;

public class MovieRegisterActivity extends AppCompatActivity
    implements View.OnClickListener
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
        etName = (EditText) findViewById(R.id.ET_Register_Name);
        etDate = (EditText) findViewById(R.id.ET_Register_Date);
        etTime = (EditText) findViewById(R.id.ET_Register_Time);
        bttnDate = (Button) findViewById(R.id.Bttn_Register_Date);
        bttnTime = (Button) findViewById(R.id.Bttn_Register_Time);
        ticketDropDown = (Spinner) findViewById(R.id.Spinner_Quantity);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ticket_quantity, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        ticketDropDown.setAdapter(adapter);


        // From the MainActivity, get the position...
        int mPosition = getIntent().getIntExtra("title", 0);
        Resources resources = getResources();
        title = resources.getStringArray(R.array.movie_title);

        movieTitle.setText(title[mPosition]);
    }

    /*
     * METHOD : onClick
     * DETAILS : The onClick method overrides the View onClick and
     *      determines the widget that was clicked.
     * PARAM :
     * RETURN : None
     */
    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.Bttn_Register_Date:
                // Call method to handle Date dialog
                break;

            case R.id.Bttn_Register_Time:
                // Call method to handle Time dialog
                break;

            // Implement a "Buy/Purchase/Confirm" button
            default:
                break;
        }
    }
}
