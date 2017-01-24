/*
* FILE :            Review.java
* PROJECT :         MAD - Assignment #1
* PROGRAMMER :      Dong Qian (6573448) / Austin chen () / Monira Sultana ()
* FIRST VERSION :   2017-01-25
* DESCRIPTION :     A class to create a review object
*/

package com.qiandongyqgmail.vipmovie;


public class Reviews {

    /*-PRIVATE ATTRIBUTES-*/
    private String name; // user name, in this case, it is always "Guest"
    private String content; // Content of reviews
    private String date; // The time when the review sended


    /*- Setter -*/
    public void setName(String name) {
        this.name = "@" + name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = "at " + date;
    }

    /*- Getter -*/
    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }
}



