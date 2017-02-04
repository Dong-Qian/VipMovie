/*
* FILE :            MovieRegisterActivity.java
* PROJECT :         MAD - Assignment #1
* PROGRAMMER :      Dong Qian (6573448) / Austin Che / Monira Sultana (7308182)
* FIRST VERSION :   2017-01-25
* DESCRIPTION : This is the code-behind for the MovieRegisterActivity. The purpose
*       of this activity is to simulate the purchasing of a ticket for a particular movie.
*       Elements such as the date, time, and ticket quantity are attributed to a ticket purchasing
*       screen.
*
*       Once the user has completed filling out the information - then they may purchase the ticket!
*/
package com.qiandongyqgmail.vipmovie;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
import android.widget.Toast;

import java.util.Calendar;

import static android.app.DatePickerDialog.*;

public class MovieRegisterActivity extends AppCompatActivity
    implements View.OnClickListener
{
    /*-PRIVATE ATTRIBUTES-*/
    //-Widget specific fields-//
    private TextView tvMovieTitle = null;
    private TextView tvTotalPrice = null;
    private EditText etName = null;
    private EditText etDate = null;
    private EditText etTime = null;
    private Button bttnDate = null;
    private Button bttnTime = null;
    private Button bttnPurchase = null;
    private RadioButton radioRegular = null;
    private RadioButton radioIMAX = null;
    private Spinner spinnerQuantity = null;

    /*-String specific registration variables-*/
    private int arrayPosition;
    private String[] arrayTitle = null;
    private String[] arrayPrice = null;
    private String ticketQuantity = null;
    Ticket ticket = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_register);

        // Loading all the specified widget IDs
        tvMovieTitle = (TextView) findViewById(R.id.TV_Register_Title);
        tvTotalPrice = (TextView) findViewById(R.id.TV_Register_Price);
        spinnerQuantity = (Spinner) findViewById(R.id.Spinner_Quantity);

        etDate = (EditText) findViewById(R.id.ET_Register_Date);
        etTime = (EditText) findViewById(R.id.ET_Register_Time);
        bttnDate = (Button) findViewById(R.id.Bttn_Register_Date);
        bttnTime = (Button) findViewById(R.id.Bttn_Register_Time);
        bttnPurchase = (Button) findViewById(R.id.Bttn_Register_Purchase);
        radioRegular = (RadioButton) findViewById(R.id.Radio_Register_Regular);
        radioIMAX = (RadioButton) findViewById(R.id.Radio_Register_IMAX);

        // Setting up the OnClickListener events
        bttnDate.setOnClickListener(this);
        bttnTime.setOnClickListener(this);
        bttnPurchase.setOnClickListener(this);
        radioRegular.setOnClickListener(this);
        radioIMAX.setOnClickListener(this);

        // Load any resources from array.xml or Intent data...
        // This method will is declared below
        loadResources();

        // Instantiate a Ticket object
        ticket = new Ticket();
        ticket.setMovieTitle(arrayTitle[arrayPosition]);
        tvMovieTitle.setText(ticket.getMovieTitle());
        radioRegular.setChecked(true);
        calculateCurrentPrice();

        // The Name EditText widget
        //
        etName = (EditText) findViewById(R.id.ET_Register_Name);

//        etName.setOnEditorActionListener(new TextView.OnEditorActionListener()
//        {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
//            {
//                if (actionId == EditorInfo.IME_ACTION_DONE)
//                {
//                    InputMethodManager imm = null;
//                    imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(etName.getWindowToken(), 0);
//
//                    ticket.setBuyerName(etName.getText().toString());
//                }
//
//                return false;
//            }
//        });

        // The Ticket Type Spinner widget
        //
        spinnerQuantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                // Everytime the Spinner value changes - recalculate the total ticket price
                ticketQuantity = parent.getItemAtPosition(position).toString();
                calculateCurrentPrice();
                ticket.setTicketQuantity(ticketQuantity);
                ticket.setTicketPrice(arrayPrice[arrayPosition]); // added by dong

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }


    /*
     * METHOD : onClick
     * DETAILS : Handling any onClickListener events here.
     *      The events that are handled range from Buttons to RadioButtons.
     *      If the resource (of the widget) is identified by the switch case,
     *      then the tasks are delegated to other methods.
     * PARAM : v - View - The view that was triggered.
     * RETURN : None
     */
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.Bttn_Register_Date:
                bttnDateOnClick();
                break;

            case R.id.Bttn_Register_Time:
                bttnTimeOnClick();
                break;

            case R.id.Bttn_Register_Purchase:
                bttnPurchaseOnClick();
                break;

            case R.id.Radio_Register_Regular: {
                String type = radioRegular.getText().toString();
                radioBttnTicketTypeOnClick(type);
                break;
            }
            case R.id.Radio_Register_IMAX: {
                String type = radioIMAX.getText().toString();
                radioBttnTicketTypeOnClick(type);
                break;
            }
            default:
                break;
        }
    }


    //==============================================
    // LOAD RESOURCES
    //----------------------------------------------
    /*
     * METHOD : loadResources
     * DETAILS : Load any resources from array.xml or other sources.
     *      The Spinner widget and all the resources are allocated here.
     * PARAM : None
     * RETURN : None
     */
    public void loadResources()
    {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ticket_quantity, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerQuantity.setAdapter(adapter);

        Resources resources = getResources();
        // From the MainActivity, get the position...
        arrayPosition = getIntent().getIntExtra("title", 0);
        arrayTitle = resources.getStringArray(R.array.movie_title);
        arrayPrice = resources.getStringArray(R.array.ticket_price);
        // For the ticket quantity - we must get it from the current selected spinner item
        ticketQuantity = spinnerQuantity.getSelectedItem().toString();
    }


    //==============================================
    // DATE AND TIME PICKER DIALOGS
    //----------------------------------------------
    /*
     * METHOD : bttnDateOnClick
     * DETAILS : Get the calendar date.
     *      The user must choose a date of when to see the movie.
     *      There are validation checks to ensure the date is in the future.
     * PARAM : None
     * RETURN : None
     */
    public void bttnDateOnClick()
    {
        // Get the current date (year, month, and day)
        Calendar now = Calendar.getInstance();
        final int currentYear = now.get(Calendar.YEAR);
        final int currentMonth = now.get(Calendar.MONTH);
        final int currentDay = now.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(MovieRegisterActivity.this, new OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
            {
                Boolean validDate = false;
                String movieDate = null;

                if (year > currentYear)
                {
                    validDate = true;
                    movieDate = String.format("%d - %02d - %02d", year, month + 1, dayOfMonth);
                }
                else if (year == currentYear)
                {
                    if (month > currentMonth)
                    {
                        // Set the date - since it is future date
                        validDate = true;
                        movieDate = String.format("%d - %02d - %02d", year, month + 1, dayOfMonth);
                    }
                    else if (month == currentMonth)
                    {
                        if (dayOfMonth >= currentDay)
                        {
                            // Set the date - since it is a future date
                            validDate = true;
                            movieDate = String.format("%d - %02d - %02d", year, month + 1, dayOfMonth);
                        }
                    }
                }

                if (validDate) {
                    etDate.setText(movieDate);
                    ticket.setMovieDate(movieDate);   // add this line by dong
                }
                else {
                    Toast.makeText(MovieRegisterActivity.this,
                            "Invalid Date. Please choose again.",
                            Toast.LENGTH_SHORT).show();
                }
            }

        }, currentYear, currentMonth, currentDay).show();
    }

    /*
     * METHOD : bttnTimeOnClick
     * DETAILS : Get the time (hours and minutes).
     *      A TimePickerDialog is shown to the user. The user
     *      picks the time of when to see the movie.
     *      This method also converts the 24-hour format to
     *      the 12-hour format with the periods (am and pm).
     * PARAM : None
     * RETURN : None
     */
    public void bttnTimeOnClick()
    {
        new TimePickerDialog(MovieRegisterActivity.this, new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute)
            {
                String movieTime;

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
                    if (hourOfDay == 0) {
                        hourOfDay = 12;
                    }

                    movieTime = String.format("%02d : %02d AM", hourOfDay, minute);
                }

                ticket.setMovieTime(movieTime);
                etTime.setText(movieTime);

            }

        }, 12, 0, false).show();
    }



    //==============================================
    // RADIO BUTTON - MOVIE TYPE
    //----------------------------------------------
    /*
     * METHOD : radioBttnTicketTypeOnClick
     * DETAILS : Get the ticket type.
     *      Once the ticket type changes (depending on the
     *      radio button chosen) the ticket price must be
     *      recalculated.
     * PARAM : ticketType - String - The type of ticket
     * RETURN : None
     */
    public void radioBttnTicketTypeOnClick(String ticketType)
    {
        ticket.setMovieType(ticketType);
        calculateCurrentPrice();
    }



    //==============================================
    // PURCHASING OF THE TICKET
    //----------------------------------------------
    /*
     * METHOD : bttnPurchaseOnClick
     * DETAILS : The purchase button on click event handler.
     *      The purchase button will take the user to another
     *      activity. Inside this method, widget validation occurs
     *      to ensure the EditText are not empty. If they are not
     *      empty then the user can proceed.
     * PARAM : None
     * RETURN : None
     */
    public void bttnPurchaseOnClick()
    {
        Boolean canPurchase = false;
        Boolean iSEmptyName = false;
        Boolean isEmptyDate = false;
        Boolean isEmptyTime = false;

        iSEmptyName = TextUtils.isEmpty(etName.getText());
        isEmptyDate = TextUtils.isEmpty(etDate.getText());
        isEmptyTime = TextUtils.isEmpty(etTime.getText());
        canPurchase = !iSEmptyName && !isEmptyDate && !isEmptyTime;

        if (canPurchase)
        {
            ticket.setBuyerName(etName.getText().toString()); // add this by dong
            // The ticket can be PURCHASED!
            // Pass everything to the next activity using Intents
            Intent intent = new Intent(MovieRegisterActivity.this, MovieReceiptActivity.class);
            intent.putExtra("ticket", ticket);
            startActivity(intent);
        }
        else
        {
            // The ticket cannot be PURCHASED :(
            // Display a toast message
            String error = "Please fill in all the information.";
            Toast.makeText(MovieRegisterActivity.this, error, Toast.LENGTH_LONG).show();
        }
    }



    //==============================================
    // CALCULATE TICKET PRICE
    //----------------------------------------------
    /*
     * METHOD : calculateCurrentPrice
     * DETAILS : Calculate the ticket price.
     *      Whenever the user uses a widget related to price,
     *      this method is called to calculate the ticket price.
     * PARAM : None
     * RETURN : None
     */
    public void calculateCurrentPrice()
    {
        ticket.calculatePrice(arrayPrice[arrayPosition], ticketQuantity);
        // Set the TextView to reflect the current total price
        String output = ticket.getTotalPrice();
        tvTotalPrice.setText("$ " + output);
    }
}
