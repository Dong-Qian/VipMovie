/*
* FILE :            MovieRegisterActivity.java
* PROJECT :         MAD - Assignment #1
* PROGRAMMER :      Dong Qian (6573448) / Austin Che / Monira Sultana ()
* FIRST VERSION :   2017-01-30
* DESCRIPTION :
*/
package com.qiandongyqgmail.vipmovie;


import android.util.Log;

/**
 * \class Ticket
 * \brief The Ticket class encapsulates the characteristics of a movie ticket. The Ticket has
 *      attributes pertaining to the title of the movie, the purchaser's name, the date and time
 *      at which the movie plays, and many other attributes.
 */
public class Ticket
{
    private String movieTitle;
    private String buyerName = null;
    private String movieDate = null;
    private String movieTime = null;
    private String movieType = null;
    private String ticketQuantity = null;
    private String totalPrice = null;

    /**
     * \brief Basic constructor for the Ticket class.
     */
    public Ticket()
    {
        movieTitle = "N/A";
        buyerName = "John Doe";
        movieDate = "0000 - 00 - 00";
        movieTime = "00 : 00 AM";
        movieType = "Regular";
        ticketQuantity = "0";
        totalPrice = "0.00";
    }


    //==============================================
    // SETTERS
    //----------------------------------------------
    public void setMovieTitle(String title)
    {
        movieTitle = title;
    }

    public void setBuyerName(String name)
    {
        buyerName = name;
    }

    public void setMovieDate(String date)
    {
        movieDate = date;
    }

    public void setMovieTime(String time)
    {
        movieTime = time;
    }

    public void setMovieType(String type)
    {
        movieType = type;
    }

    public void setTicketQuantity(String quantity)
    {
        ticketQuantity = quantity;
    }

    public void setTicketQuantity(int quantity)
    {
        ticketQuantity = String.valueOf(quantity);
    }

    public void setTotalPrice(String price)
    {
        ticketQuantity = price;
    }

    public void setTotalPrice(int price)
    {
        totalPrice = String.valueOf(price);
    }


    //==============================================
    // GETTERS
    //----------------------------------------------
    public String getMovieTitle()
    {
        return movieTitle;
    }

    public String getBuyerName()
    {
        return buyerName;
    }

    public String getMovieDate()
    {
        return movieDate;
    }

    public String getMovieTime()
    {
        return movieTime;
    }

    public String getMovieType()
    {
        return movieType;
    }

    public String getTicketQuantity()
    {
        return ticketQuantity;
    }

    public String getTotalPrice()
    {
        return totalPrice;
    }
}
