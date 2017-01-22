package com.qiandongyqgmail.vipmovie;

import java.util.Date;

import javax.xml.datatype.DatatypeConfigurationException;

/**
 * Created by Dong Qian on 1/21/2017.
 */

public class Reviews {

    private String name;
    private String content;
    private String date;


    public void setName(String name) {
        this.name = "@" + name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = "at " + date;
    }

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
