package com.qiandongyqgmail.vipmovie;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


public class MainActivity extends Activity{

    private RecyclerView rv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        rv = (RecyclerView) findViewById(R.id.rv);
        MovieAdapter adapter = new MovieAdapter(MainActivity.this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
        rv.addItemDecoration(new DividerItemDecoration(this));

    }


    static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView movie_avator;
        private final TextView movie_title;
        private final TextView movie_actor;
        private final TextView movie_category;
        private final TextView movie_director;
        private final RatingBar movie_rate;
        private final Button movie_detail;
        private final Button movie_ticket;


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
            movie_detail.setOnClickListener(this);
            movie_ticket.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_movie_detail:
                    int mPosition= Integer.valueOf(getAdapterPosition());
                    Intent intent = new Intent(v.getContext(), MovieDetailActivity.class);
                    intent.putExtra("title", mPosition);
                    v.getContext().startActivity(intent);
                    break;
                case R.id.btn_movie_tickets:
                    break;
            }
        }
    }

    public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

        private final int LENGTH;
        private final String[] mTitle;
        private final String[] mActor;
        private final int[] mAvator;
        private final String[] mCategory;
        private final String[] mDirector;
        private final String[] mRate;


        public MovieAdapter(Context context) {
            Resources resources = context.getResources();
            mTitle = resources.getStringArray(R.array.movie_title);
            mActor = resources.getStringArray(R.array.movie_actor);
            mCategory = resources.getStringArray(R.array.movie_category);
            mDirector = resources.getStringArray(R.array.movie_director);
            mRate = resources.getStringArray(R.array.rate);


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

        @Override
        public void onBindViewHolder(MovieViewHolder holder, int position) {
            holder.movie_title.setText(mTitle[position]);
            holder.movie_actor.setText(mActor[position]);
            holder.movie_avator.setImageResource(mAvator[position]);
            holder.movie_category.setText(mCategory[position]);
            holder.movie_director.setText(mDirector[position]);
            holder.movie_rate.setRating(Float.parseFloat(mRate[position]));

        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }

    }

    @Override
    public AssetManager getAssets() {
        return getResources().getAssets();
    }

}
