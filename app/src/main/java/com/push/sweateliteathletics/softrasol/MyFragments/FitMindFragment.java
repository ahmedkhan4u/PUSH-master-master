package com.push.sweateliteathletics.softrasol.MyFragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.push.sweateliteathletics.softrasol.Adapters.FitMindAdapter;
import com.push.sweateliteathletics.softrasol.Models.ProgramsModel;
import com.push.sweateliteathletics.softrasol.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FitMindFragment extends Fragment {


    public FitMindFragment() {
        // Required empty public constructor
    }

    private RelativeLayout mLayoutMyProgram;
    private View mView;
    private RecyclerView recyclerView;
    private List<ProgramsModel> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_fit_mind, container, false);

        recyclerView = mView.findViewById(R.id.recyclerView);
        list = new ArrayList<>();
        recyclerViewImplementation();

        return mView;
    }

    private void recyclerViewImplementation() {

        list.add(new ProgramsModel("Meditation", "Mindful Meditation Program"
                , R.drawable.fitmind_meditation_min));
        list.add(new ProgramsModel("Breathwork", "7 Day Stress Management Course"
                , R.drawable.fitmind_breathwork_min));


        recyclerView .setLayoutManager(new LinearLayoutManager(getActivity()));
        FitMindAdapter adapter = new FitMindAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);


    }


}
