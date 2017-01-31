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
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
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
    private int arrayPosition;
    private String arrayTitle[] = null;
    private String arrayPrice[] = null;

    Ticket ticket = null;


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
        radioRegular = (RadioButton) findViewById(R.id.Radio_Register_Regular);
        radioIMAX = (RadioButton) findViewById(R.id.Radio_Register_IMAX);
        ticketDropDown = (Spinner) findViewById(R.id.Spinner_Quantity);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ticket_quantity, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        ticketDropDown.setAdapter(adapter);

        // Instantiate a Ticket object
        ticket = new Ticket();

        // From the MainActivity, get the position...
        arrayPosition = getIntent().getIntExtra("title", 0);
        Resources resources = getResources();
        arrayTitle = resources.getStringArray(R.array.movie_title);
        arrayPrice = resources.getStringArray(R.array.ticket_quantity);

        movieTitle.setText(arrayTitle[arrayPosition]);
        radioRegular.setChecked(true);

        etName.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager imm =
                            (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(etName.getWindowToken(), 0);
                }

                return false;
            }
        });


        bttnDate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setDateEvent(v);
            }
        });

        bttnTime.setOnClickListener(new View.OnClickListener()
        {
            /*
             * METHOD : onClick
             * DETAILS : On the button click, display the TimePickerDialog.
             * PARAM : v - View - The view (widget) that was clicked
             * RETURN : NONE
             */
            @Override
            public void onClick(View v)
            {
                setTimeEvent();
            }
        });
    }


    //==============================================
    // DATE AND TIME PICKER DIALOGS
    //----------------------------------------------
    /*
     * METHOD :
     * DETAILS :
     * PARAM :
     * RETURN : None
     */
    public void setDateEvent(View v)
    {
        Calendar now = Calendar.getInstance();
        final int currentYear = now.get(Calendar.YEAR);
        final int currentMonth = now.get(Calendar.MONTH);
        final int currentDay = now.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(MovieRegisterActivity.this, new OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
            {
                String movieDate = null;
                if (year >= currentYear) {
                    if (month == currentMonth) {
                        if (dayOfMonth >= currentDay) {
                            movieDate = String.format("%d - %02d - %02d", year, month + 1, dayOfMonth);
                        }
                    }
                }

                etDate.setText(movieDate);
            }

        }, currentYear, currentMonth, currentDay).show();
    }


    /*
     * METHOD :
     * DETAILS :
     * PARAM :
     * RETURN : None
     */
    public void setTimeEvent()
    {
        new TimePickerDialog(MovieRegisterActivity.this, new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute)
            {
                System.out.println(String.format("Hour of day %d", hourOfDay));
                String movieTime = null;

                // Since the hourOfDay uses 24:00 hour standard
                // we have to adjust it to correspond with AM and PM
                if (hourOfDay >= 12)
                {
                    if (hourOfDay >= 13) {
                        hourOfDay = hourOfDay - 12;
                    }
                    movieTime = String.format("%02d : %02d PM", hourOfDay, minute);
                }
                else
                {
                    // Else the time in the AM period
                    movieTime = String.format("%02d : %02d AM", hourOfDay, minute);
                }

                etTime.setText(movieTime);
            }

        }, 0, 0, false).show();
    }
}
