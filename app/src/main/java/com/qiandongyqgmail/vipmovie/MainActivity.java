package com.qiandongyqgmail.vipmovie;


import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    private Context context;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = (RecyclerView) findViewById(R.id.rv);
        MovieAdapter adapter = new MovieAdapter(MainActivity.this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(rv.getContext(),
                new LinearLayoutManager(this).getOrientation());
        rv.addItemDecoration(mDividerItemDecoration);


    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView movie_avator;
        private TextView movie_title;
        private TextView movie_actor;


        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.cell_movies, parent, false));
            movie_avator = (ImageView) itemView.findViewById(R.id.movie_avator);
            movie_title = (TextView) itemView.findViewById(R.id.movie_title);
            movie_actor = (TextView) itemView.findViewById(R.id.movie_actor);


        }
    }

    public class MovieAdapter extends RecyclerView.Adapter<ViewHolder> {

        private final int LENGTH;
        private final String[] mTitle;
        private final String[] mActor;
        private final int[] mAvator;


        public MovieAdapter(Context context) {
            Resources resources = context.getResources();
            mTitle = resources.getStringArray(R.array.movie_title);
            mActor = resources.getStringArray(R.array.movie_actor);

            TypedArray ar = resources.obtainTypedArray(R.array.movie_avator);
            LENGTH = ar.length();
            mAvator = new int[LENGTH];

            for (int i = 0; i < LENGTH; i++) {
                mAvator[i] = ar.getResourceId(i, 0);
            }
            ar.recycle();
        }


        public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {

            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.movie_title.setText(mTitle[position]);
            holder.movie_actor.setText(mActor[position]);
            holder.movie_avator.setImageResource(mAvator[position]);

        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }

    }



}
