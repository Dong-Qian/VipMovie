/*
* FILE :            MovieReviewFragment.java
* PROJECT :         MAD - Assignment #1
* PROGRAMMER :      Dong Qian (6573448) / Austin chen () / Monira Sultana ()
* FIRST VERSION :   2017-01-25
* DESCRIPTION :     Review Screen which shows a list of reviews
*                       1. name
*                       2. content (reviews)
*                       3. date (time)
*                       4. Like button
*/


package com.qiandongyqgmail.vipmovie;

import java.lang.reflect.Type;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import static android.content.Context.MODE_PRIVATE;


public class MovieReviewFragment extends Fragment{

    /*-PRIVATE ATTRIBUTES-*/
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
    LinearLayout mLayout;

    // Function to get the movie title
    public void setParameter(String title){
        this.title = title;
    }


    // Override onPause
    // When this active is onPause, save all the reviewList in to SharedPreferences
    // Because SharedPreferences is hard to save a list of objects.
    // We use Gson to help us to transfer objects into json format and save as SharedPreferences
    @Override
    public void onPause() {
        super.onPause();

        // Save reviews use the specific key for different movies (movie title)
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

        // Retrieve reviews back from SharedPreferences when the View created
        SharedPreferences  mPrefs = getActivity().getPreferences(MODE_PRIVATE);
        // Setup recyclerView use ViewHolder and Adapter
        rv = (RecyclerView) root.findViewById(R.id.review_rv);
        adapter = new ReviewAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
        mLayout = (LinearLayout) root.findViewById(R.id.review_layout);

        Review_Key = title; // Key for retrieve reviews for specific movies

        // Load Reviews back use the same key when we save it (movie title)
        if(mPrefs.contains(Review_Key)) {
            String xxxReviews = getActivity().getPreferences(MODE_PRIVATE).getString(Review_Key, null);
            // Load reviews
            Type type = new TypeToken<ArrayList<Reviews>>() {
            }.getType();
            Gson gson = new Gson();
            reviewsList = gson.fromJson(xxxReviews, type);
        }

        // Link Widgets
        et_content = (EditText) root.findViewById(R.id.et_content);
        btn_sendReview = (Button) root.findViewById(R.id.btn_sendReview);

        // When the Send button clicked, add the reviews to the reviewList
        btn_sendReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create a review object to save the review information
                Reviews reviews = new Reviews();
                Date date = new Date();
                content = et_content.getText().toString();
                reviews.setContent(content);
                // Default name
                // Not implement user in this assignment
                reviews.setName("Guest");
                String formattedDate = dateFormat.format(date);
                reviews.setDate(formattedDate);
                reviewsList.add(reviews);

                // Notify the adapter data is changed, new review added update the view
                adapter.notifyDataSetChanged();
                // Clean the editText filed
                et_content.setText("");

                // Hide the softkeyboard input panel when send button clicked
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mLayout.getWindowToken(),0);

            }
        });

        return root;
    }


    // ViewHolder for the Reviews to link the widgets
    public class ReviewViewHolder extends RecyclerView.ViewHolder{

        /*-PRIVATE ATTRIBUTES-*/
        private TextView showName = null;
        private TextView showReview = null;
        private TextView showTime = null;
        private ImageView showLike = null;
        private int changeFlag = 0;

        public ReviewViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.cell_reviews, parent, false));

            showName = (TextView) itemView.findViewById(R.id.review_name);
            showReview = (TextView) itemView.findViewById(R.id.review_review);
            showTime = (TextView) itemView.findViewById(R.id.review_time);
            showLike = (ImageView) itemView.findViewById(R.id.review_like);

            // A like image beside each reviews
            // Change the image when clicked
            // Status not implement in this assignment. It will reset each time the activity onCreate
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


    // Adapter to retrieve the data adn link it to the View (Widgets)
    public class ReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder>{

        @Override
        public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ReviewViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        // Bind the data to the specific widgets (buttons,textView, image...etc)
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
}
