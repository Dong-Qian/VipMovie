package com.qiandongyqgmail.vipmovie;

import java.lang.reflect.Type;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Dong Qian on 1/20/2017.
 */

public class MovieReviewFragment extends Fragment{

    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private RecyclerView rv = null;
    private List<Reviews> reviewsList = new ArrayList<Reviews>();
    private EditText et_content = null;
    private Button btn_sendReview =null;
    private String content = null;
    private ReviewAdapter adapter = null;
    private SharedPreferences  mPrefs = null;
    private String Review_Key = null;
    private String title = null;
    private int visited = 0;
    private String user = null;


    public void setParameter(String title){
        this.title = title;
    }


    @Override
    public void onPause() {
        super.onPause();

        // Save reviews
        if(reviewsList.size() != 0) {
            mPrefs = getActivity().getPreferences(MODE_PRIVATE);
            // Save reviews
            SharedPreferences.Editor editor = mPrefs.edit();
            Gson gson = new Gson();
            String xxxReviews = gson.toJson(reviewsList);
            editor.putString(Review_Key, xxxReviews);
            editor.commit();
        }
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View root = inflater.inflate(R.layout.fragment_movie_review, container, false);






        SharedPreferences  mPrefs = getActivity().getPreferences(MODE_PRIVATE);

        rv = (RecyclerView) root.findViewById(R.id.review_rv);
        adapter = new ReviewAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);

        Review_Key = title; // Key for retrived reviews for specific moviews

        // Load Reviws
        if(mPrefs.contains(Review_Key)) {
            String xxxReviews = getActivity().getPreferences(MODE_PRIVATE).getString(Review_Key, null);
            // Load reviews
            Type type = new TypeToken<ArrayList<Reviews>>() {
            }.getType();
            Gson gson = new Gson();
            reviewsList = gson.fromJson(xxxReviews, type);
        }





        et_content = (EditText) root.findViewById(R.id.et_content);
        btn_sendReview = (Button) root.findViewById(R.id.btn_sendReview);
        btn_sendReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Reviews reviews = new Reviews();
                Date date = new Date();
                content = et_content.getText().toString();
                reviews.setContent(content);
                reviews.setName(user);
                String formattedDate = dateFormat.format(date);
                reviews.setDate(formattedDate);
                reviewsList.add(reviews);
                adapter.notifyDataSetChanged();
                et_content.setText("");


            }
        });

        return root;
    }




    public class ReviewViewHolder extends RecyclerView.ViewHolder{

        private TextView showName;
        private TextView showReview;
        private TextView showTime;
        private ImageView showLike;
        private int changeFlag = 0;

        public ReviewViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.cell_reviews, parent, false));


            showName = (TextView) itemView.findViewById(R.id.review_name);
            showReview = (TextView) itemView.findViewById(R.id.review_review);
            showTime = (TextView) itemView.findViewById(R.id.review_time);
            showLike = (ImageView) itemView.findViewById(R.id.review_like);

            showLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(changeFlag == 0){
                        showLike.setImageResource(R.drawable.heart_click);
                        changeFlag = 1;

                    }else{
                        showLike.setImageResource(R.drawable.heart);
                        changeFlag = 0;
                    }
                }
            });
        }
    }


    public class ReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder>{



        @Override
        public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ReviewViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ReviewViewHolder holder, int position) {

            holder.showName.setText(reviewsList.get(position).getName());
            holder.showReview.setText(reviewsList.get(position).getContent());
            holder.showTime.setText(reviewsList.get(position).getDate());
        }

        @Override
        public int getItemCount() {
            if(reviewsList.size() != 0) {
                return reviewsList.size();
            }else{
                return 0;
            }
        }
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            if(visited == 0)
            {
                // Get the name of user
                final EditText input = new EditText(getActivity());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(input);
                builder.setTitle("What's your name?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        user = input.getText().toString();
                        System.out.println(user);
                        if(user.isEmpty())
                        {
                            user = "Guest";
                        }
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.show();

                visited = 1;
            }
        }
    }
}