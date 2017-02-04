/*
* FILE :            MovieLocationFragment.java
* PROJECT :         MAD - Assignment #1
* PROGRAMMER :      Dong Qian (6573448) / Austin Che (7243322) / Monira Sultana (7308182)
* FIRST VERSION :   2017-01-25
* DESCRIPTION :     Location Screen to show the VIP Movie location and some pictures
*/

package com.qiandongyqgmail.vipmovie;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ViewSwitcher;


/**
 * Created by Dong Qian on 1/20/2017.
 */

public class MovieLocationFragment extends Fragment {

    /*-PRIVATE ATTRIBUTES-*/
    private Button location_left = null;
    private Button location_right = null;
    private ImageSwitcher location_is = null;
    private int vipCount = 0;
    private int [] vip = {  R.drawable.vip,
                    R.drawable.vip1,
                    R.drawable.vip2,
                    R.drawable.vip3,
                    R.drawable.vip4,
                    R.drawable.vip5,
    };

    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_movie_location, container, false);

        location_left = (Button) root.findViewById(R.id.btn_location_left);
        location_right = (Button) root.findViewById(R.id.btn_location_right);
        location_is = (ImageSwitcher) root.findViewById(R.id.location_is);

        location_is.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                // Create a new imageView set it;s properties
                ImageView location_im = new ImageView(getActivity());
                location_im.setScaleType(ImageView.ScaleType.CENTER_CROP);
                location_im.setLayoutParams(new ImageSwitcher.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                return location_im;

            }
        });

        // Create in and out animations and load them using AnimationUtils class
         Animation in = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in);
         Animation out = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out);
        // Set the animation type to ImageSwitcher
        location_is.setInAnimation(in);
        location_is.setOutAnimation(out);
        location_is.setImageResource(vip[0]);
        // Set the button for next image or perious image
        location_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vipCount != 0) {
                    vipCount--;
                    location_is.setImageResource(vip[vipCount]);
                }

            }
        });

        location_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vipCount != 5) {
                    vipCount++;
                    location_is.setImageResource(vip[vipCount]);
                }
            }
        });

        return root;
    }
}
