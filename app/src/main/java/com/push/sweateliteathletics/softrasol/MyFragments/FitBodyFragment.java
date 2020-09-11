package com.push.sweateliteathletics.softrasol.MyFragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.push.sweateliteathletics.softrasol.Adapters.FitBodyProgramsAdapter;
import com.push.sweateliteathletics.softrasol.Models.ProgramsModel;
import com.push.sweateliteathletics.softrasol.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FitBodyFragment extends Fragment {

    private List<ProgramsModel> list = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private View mView;

    public FitBodyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         mView = inflater.inflate(R.layout.fragment_fit_body, container, false);

         mRecyclerView = mView.findViewById(R.id.recyclerview_fit_body);

         recyclerViewImplementation();

         return mView;
    }

    private void recyclerViewImplementation() {

        list.add(new ProgramsModel("TAKE // OFF", "Strength & Conditioning Training Program"
        , R.drawable.fitbody_takeoff_min));
        list.add(new ProgramsModel("S.W.E.A.T", "6 Week Full body Dynamic Training Program"
                , R.drawable.fitbody_takeoff_min));
        list.add(new ProgramsModel("BOX", "Boxing Cardio Program"
                , R.drawable.fitbody_box_min));
        list.add(new ProgramsModel("MOBILITY", "7 Day Mobility Program"
                , R.drawable.fitbody_mobility_min));
        list.add(new ProgramsModel("NUTRITION", "Healthy meal plan", R.drawable.fit_body_nutrition_program));

        mRecyclerView .setLayoutManager(new LinearLayoutManager(getActivity()));
        FitBodyProgramsAdapter adapter = new FitBodyProgramsAdapter(getActivity(), list);
        mRecyclerView.setAdapter(adapter);


    }

}
