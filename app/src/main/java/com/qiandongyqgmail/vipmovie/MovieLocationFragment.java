package com.qiandongyqgmail.vipmovie;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;




/**
 * Created by Dong Qian on 1/20/2017.
 */

public class MovieLocationFragment extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.map, container, false);



        return root;
    }

}
