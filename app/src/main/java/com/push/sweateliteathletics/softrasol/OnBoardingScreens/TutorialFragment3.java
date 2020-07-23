package com.push.sweateliteathletics.softrasol.OnBoardingScreens;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.push.sweateliteathletics.softrasol.R;
import com.push.sweateliteathletics.softrasol.SignIn;

/**
 * A simple {@link Fragment} subclass.
 */
public class TutorialFragment3 extends Fragment {


    Button btnGetStarted;

    public TutorialFragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tutorial_fragment3, container, false);

        btnGetStarted = view.findViewById(R.id.btn_getStarted);

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences
                        ("tutorial", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("first_time", "false");
                editor.apply();

                startActivity(new Intent(getActivity(), SignIn.class));
                getActivity().finish();
            }
        });


        return view;
    }

}
