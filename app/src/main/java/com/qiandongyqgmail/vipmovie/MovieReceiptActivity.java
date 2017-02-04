/*
* FILE :            MovieReceiptActivity.java
* PROJECT :         MAD - Assignment #1
* PROGRAMMER :      Dong Qian (6573448) / Austin chen () / Monira Sultana (7308182)
* FIRST VERSION :   2017-01-25
* DESCRIPTION :     Location Screen to show the VIP Movie location and some pictures
*/

package com.qiandongyqgmail.vipmovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class MovieReceiptActivity extends AppCompatActivity {

    private TextView movieTiltel = null;
    private TextView name = null;
    private TextView date = null;
    private TextView time = null;
    private TextView type = null;
    private TextView uPrice = null;
    private TextView nOfTicket = null;
    private TextView totalPrice = null;
    private TextView thank = null;
    private Ticket ticket = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_receipt);
        // Get the ticket object from the register activity
        Intent intent = getIntent();
        ticket = (Ticket) intent.getSerializableExtra("ticket");
        // set all text according to the object we got
        movieTiltel = (TextView) findViewById(R.id.receiptTitle);
        movieTiltel.setText(ticket.getMovieTitle());
        name= (TextView)findViewById(R.id.receiptName);
        name.setText("Name:   " + ticket.getBuyerName());
        date= (TextView)findViewById(R.id.receiptDate);
        date.setText("Movie Date:   " + ticket.getMovieDate());
        time= (TextView)findViewById(R.id.receiptTime);
        time.setText("Movie Time:   " + ticket.getMovieTime());
        type =(TextView)findViewById(R.id.receiptType);
        type.setText("Movie Type:   " + ticket.getMovieType());
        uPrice=(TextView)findViewById(R.id.receiptPrice);
        uPrice.setText("Ticket Price:   $" + ticket.getTicketPrice());
        nOfTicket=(TextView) findViewById(R.id.receiptHowMany);
        nOfTicket.setText("Tickets Number:   " + ticket.getTicketQuantity());
        totalPrice=(TextView)findViewById(R.id.receiptTotalPrice);
        totalPrice.setText("Total Price:   $" + ticket.getTotalPrice());
        thank = (TextView) findViewById(R.id.receiptThank);
        thank.setText("Thanks - Enjoy the Movie!");
    }
}
