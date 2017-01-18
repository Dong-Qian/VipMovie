package com.qiandongyqgmail.vipmovie;


import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;



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

        rv.addItemDecoration(new DividerItemDecoration(this,null));


    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView movie_avator;
        private TextView movie_title;
        private TextView movie_actor;
        private TextView movie_category;
        private TextView movie_director;
        private RatingBar movie_rate;


        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.cell_movies, parent, false));
            movie_avator = (ImageView) itemView.findViewById(R.id.movie_avator);
            movie_title = (TextView) itemView.findViewById(R.id.movie_title);
            movie_actor = (TextView) itemView.findViewById(R.id.movie_actor);
            movie_category = (TextView) itemView.findViewById(R.id.movie_category);
            movie_director = (TextView) itemView.findViewById(R.id.movie_director);
            movie_rate = (RatingBar) itemView.findViewById(R.id.movie_ratingBar);


        }
    }

    public class MovieAdapter extends RecyclerView.Adapter<ViewHolder> {

        private final int LENGTH;
        private final String[] mTitle;
        private final String[] mActor;
        private final int[] mAvator;
        private final String [] mCategory;
        private final String [] mDirector;
        private final String [] mRate;

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





        public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {

            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
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

    // Add Item decoration (divider)
    public class DividerItemDecoration extends RecyclerView.ItemDecoration {

        private Drawable mDivider;

        public DividerItemDecoration(Context context, AttributeSet attrs) {
            final TypedArray a = context
                    .obtainStyledAttributes(attrs, new int[]{android.R.attr.listDivider});
            mDivider = a.getDrawable(0);
            a.recycle();
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            if (parent.getChildAdapterPosition(view) == 0) {
                return;
            }

            outRect.top = mDivider.getIntrinsicHeight();
        }

        @Override
        public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
            int dividerLeft = parent.getPaddingLeft();
            int dividerRight = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount - 1; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int dividerTop = child.getBottom() + params.bottomMargin;
                int dividerBottom = dividerTop + mDivider.getIntrinsicHeight();

                mDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom);
                mDivider.draw(canvas);
            }
        }


    }
}
