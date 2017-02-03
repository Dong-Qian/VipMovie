/*
* FILE :            Ticket.java
* PROJECT :         MAD - Assignment #1
* PROGRAMMER :      Dong Qian (6573448) / Austin Che / Monira Sultana ()
* FIRST VERSION :   2017-01-30
* DESCRIPTION : The class definition and implementation for the Ticket class.
*/
package com.qiandongyqgmail.vipmovie;



/*
 * METHOD : Ticket
 * DETAILS : The Ticket class encapsulates the characteristics of a movie ticket. The Ticket has
 *      attributes pertaining to the title of the movie, the purchaser's name, the date and time
 *      at which the movie plays, and many other attributes.
 */
public class Ticket
{
    private final float kIMAXPrice = 3.0f;
    private final String kIMAXType = "IMAX";

    private String movieTitle = null;
    private String buyerName = null;
    private String movieDate = null;
    private String movieTime = null;
    private String movieType = null;
    private String ticketQuantity = null;
    private String totalPrice = null;


    //==============================================
    // CONSTRUCTORS
    //----------------------------------------------
    /*
     * METHOD : Ticket
     * DETAILS : Basic constructor.
     */
    public Ticket()
    {
        movieTitle = "N/A";
        buyerName = "N/A";
        movieDate = "1970 - 01 - 01";
        movieTime = "00 : 00 AM";
        movieType = "Regular";
        ticketQuantity = "0";
        totalPrice = "0.00";
    }


    //==============================================
    // TICKET PRICE CALCULATIONS
    //----------------------------------------------
    /*
     * METHOD : calculatePrice
     * DETAILS : The ticket price is calculated by the product
     *      of price and quantity plus extra fees. The extra fee
     *      is dependent on the type of ticket (Regular, IMAX, etc.)
     * PARAM : price - float - The price of the ticket
     *         quantity - in - How many tickets are being bought
     * RETURN : None
     */
    public void calculatePrice(float price, int quantity)
    {
        float total = 0;
        float extra = 0;

        if (movieType.equalsIgnoreCase(kIMAXType)) {
            extra = kIMAXPrice;
        }

        total = (price * quantity) + extra;
        setTotalPrice(total);
    }


    /*
     * METHOD : calculatePrice
     * DETAILS : The ticket price is calculated by the product
     *      of price and quantity plus extra fees. The extra fee
     *      is dependent on the type of ticket (Regular, IMAX, etc.)
     * PARAM : price - String - The price of the ticket
     *         quantity - String - How many tickets are being bought
     * RETURN : None
     */
    public void calculatePrice(String price, String quantity)
    {
        float total = 0;
        float extra = 0;

        if (movieType.equalsIgnoreCase(kIMAXType)) {
            extra = kIMAXPrice;
        }

        // Special case - need to convert String to Float
        // for mathematical calculations
        total = (Float.valueOf(price) * Float.valueOf(quantity)) + extra;
        setTotalPrice(total);
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

    public void setTotalPrice(float price)
    {
        totalPrice = String.format("%2.2f", price);
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
