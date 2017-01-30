/*
* FILE :            MovieRegisterActivity.java
* PROJECT :         MAD - Assignment #1
* PROGRAMMER :      Dong Qian (6573448) / Austin Che / Monira Sultana ()
* FIRST VERSION :   2017-01-25
* DESCRIPTION :
*/
package com.qiandongyqgmail.vipmovie;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.app.DatePickerDialog.*;

public class MovieRegisterActivity extends AppCompatActivity

{
    /*-PRIVATE ATTRIBUTES-*/
    //-Widget specific fields-//
    private TextView movieTitle = null;
    private TextView totalPrice = null;
    private EditText etName = null;
    private EditText etDate = null;
    private EditText etTime = null;
    private Button bttnDate = null;
    private Button bttnTime = null;
    private Button bttnPurchase = null;
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
        totalPrice = (TextView) findViewById(R.id.TV_Register_Price);
        etName = (EditText) findViewById(R.id.ET_Register_Name);
        etDate = (EditText) findViewById(R.id.ET_Register_Date);
        etTime = (EditText) findViewById(R.id.ET_Register_Time);
        bttnDate = (Button) findViewById(R.id.Bttn_Register_Date);
        bttnTime = (Button) findViewById(R.id.Bttn_Register_Time);
        bttnPurchase = (Button) findViewById(R.id.Bttn_Register_Purchase);
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

        bttnDate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Calendar now = Calendar.getInstance();
                int currentYear = now.get(Calendar.YEAR);
                int currentMonth = now.get(Calendar.MONTH);
                int currentDay = now.get(Calendar.DAY_OF_MONTH);

                new DatePickerDialog(MovieRegisterActivity.this, new OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                    {
                        // The month needs to be offset by 1
                        String movieDate = String.format("%d - %02d - %02d", year, month + 1, dayOfMonth);
                        etDate.setText(movieDate);
                    }

                }, currentYear, currentMonth, currentDay).show();
            }
        });

        bttnTime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new TimePickerDialog(MovieRegisterActivity.this, new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute)
                    {
                        String movieTime = String.format("%02d : %02d", hourOfDay, minute);
                        etTime.setText(movieTime);
                    }
                }, 0, 0, false).show();
            }
        });
    }

    /*
     * METHOD : onClick
     * DETAILS : The onClick method overrides the View onClick and
     *      determines the widget that was clicked.
     * PARAM :
     * RETURN : None
     */
    //@Override
//    public void onClick(View v) {
//        switch(v.getId())
//        {
//            case R.id.Bttn_Register_Date:
//                // Call method to handle Date dialog
//                // Pass the view to the method
//                setDate(v);
//                break;
//
//            case R.id.Bttn_Register_Time:
//                // Call method to handle Time dialog
//                break;
//
//            case R.id.Bttn_Register_Purchase:
//                // Implement a "Buy/Purchase/Confirm" button
//                // Call method to handle purchasing the ticket
//                break;
//
//            default:
//                break;
//        }
//    }



    //==============================================
    // DATE AND TIME PICKER DIALOGS
    //----------------------------------------------
    /*
     * METHOD : SetDate
     * DETAILS :
     * PARAM :
     * RETURN : None
     */
//    public void setDate(View v)
//    {
//        new DatePickerDialog(MovieRegisterActivity.this, new DatePickerDialog.OnDateSetListener()
//        {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
//            {
//
//            }
//        })
//    }
}
