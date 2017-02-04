/*
* FILE :            MainActivity.java
* PROJECT :         MAD - Assignment #1
* PROGRAMMER :      Dong Qian (6573448) / Austin chen () / Monira Sultana (7308182)
* FIRST VERSION :   2017-01-25
* DESCRIPTION :     Launch Screen with now playing movies.
*                   Detail: button take user to website of that particular movies
*                   Ticket: button take user to purchase the movie ticket
*/


package com.qiandongyqgmail.vipmovie;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity{

    // create a recyclerView to show list of movies
    private RecyclerView rv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up recyclerView LayoutManager (Linearlayout)
        // Set up the recyclerView Adapter
        rv = (RecyclerView) findViewById(R.id.rv);
        MovieAdapter adapter = new MovieAdapter(MainActivity.this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }


    // In order to use recyclerView, we need ViewHolder and adapter
    // MovieViewHolder is a custom ViewHolder extends of recyclerView.View, use to link each widgets to the code behind variable
    static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private  ImageView movie_avator = null;
        private  TextView movie_title = null;
        private  TextView movie_actor = null;
        private  TextView movie_category = null;
        private  TextView movie_director = null;
        private  RatingBar movie_rate = null;
        private  Button movie_detail = null;
        private  Button movie_ticket = null;


        // Construct
        // link the widgets
        public MovieViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.cell_movies, parent, false));
            movie_avator = (ImageView) itemView.findViewById(R.id.movie_avator);
            movie_title = (TextView) itemView.findViewById(R.id.movie_title);
            movie_actor = (TextView) itemView.findViewById(R.id.movie_actor);
            movie_category = (TextView) itemView.findViewById(R.id.movie_category);
            movie_director = (TextView) itemView.findViewById(R.id.movie_director);
            movie_rate = (RatingBar) itemView.findViewById(R.id.movie_ratingBar);
            movie_detail = (Button) itemView.findViewById(R.id.btn_movie_detail);
            movie_ticket = (Button) itemView.findViewById(R.id.btn_movie_tickets);
            // Set up click listener to the detail and ticket buttons
            movie_detail.setOnClickListener(this);
            movie_ticket.setOnClickListener(this);
        }


        // Detail button click: 1. Get position of the current button in the recyclerView
        //                      2. Call the MovieActivity with position as parameter
        @Override
        public void onClick(View v) {

            Intent intent = null;
            // Get the position from the click - used to get an element from the array.xml
            int mPosition = Integer.valueOf(getAdapterPosition());

            switch (v.getId()) {
                case R.id.btn_movie_detail:
                    intent = new Intent(v.getContext(), MovieActivity.class);
                    intent.putExtra("title", mPosition);
                    v.getContext().startActivity(intent);
                    break;
                case R.id.btn_movie_tickets:
                    intent = new Intent(v.getContext(), MovieRegisterActivity.class);
                    intent.putExtra("title", mPosition);
                    v.getContext().startActivity(intent);
                    break;
            }
        }
    }


    // MovieAdapter extend form RecyclerView.Adapter
    // Use to retrieve data from source (arrary.xml)
    public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

        private  int LENGTH = 0;
        private  String[] mTitle = null;
        private  String[] mActor = null;
        private  int[] mAvator = null;
        private  String[] mCategory = null;
        private  String[] mDirector = null;
        private  String[] mRate = null;


        // Constructor
        // Get all data from the array.xml and put into local variables
        public MovieAdapter(Context context) {
            Resources resources = context.getResources();
            mTitle = resources.getStringArray(R.array.movie_title);
            mActor = resources.getStringArray(R.array.movie_actor);
            mCategory = resources.getStringArray(R.array.movie_category);
            mDirector = resources.getStringArray(R.array.movie_director);
            mRate = resources.getStringArray(R.array.rate);

            // For image resource, we need use typeArray to obtain
            TypedArray avator = resources.obtainTypedArray(R.array.movie_avator);
            LENGTH = avator.length();
            mAvator = new int[LENGTH];

            for (int i = 0; i < LENGTH; i++) {
                mAvator[i] = avator.getResourceId(i, 0);
            }

            avator.recycle();
        }


        public MovieViewHolder onCreateViewHolder(ViewGroup parent, int i) {

            return new MovieViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        // Most important part of MovieAdapter
        // Bind the data to the specific widgets (buttons,textView, image...etc)
        @Override
        public void onBindViewHolder(MovieViewHolder holder, int position) {
            holder.movie_title.setText(mTitle[position]);
            holder.movie_actor.setText(mActor[position]);
            holder.movie_avator.setImageResource(mAvator[position]);
            holder.movie_category.setText(mCategory[position]);
            holder.movie_director.setText(mDirector[position]);
            holder.movie_rate.setRating(Float.parseFloat(mRate[position]));

        }

        // how many you wan to show on the recyclerView (screen)
        @Override
        public int getItemCount() {
            return LENGTH;
        }

    }
}
