package com.push.sweateliteathletics.softrasol.MyActivities;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.push.sweateliteathletics.softrasol.Adapters.FitMindAdapter;
import com.push.sweateliteathletics.softrasol.Models.ProgramsModel;
import com.push.sweateliteathletics.softrasol.R;

import java.util.ArrayList;
import java.util.List;

public class FitMindProgramsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<ProgramsModel> list = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fit_body_program);
        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.black));//

        recyclerView = findViewById(R.id.recyclerView);

        recyclerViewImplementation();
    }

    public void BackClick(View view) {
        onBackPressed();
    }

    private void recyclerViewImplementation() {

        list.add(new ProgramsModel("Meditation", "Mindful Meditation Program"
                , R.drawable.fitmind_meditation_min));
        list.add(new ProgramsModel("Breathwork", "7 Day Stress Management Course"
                , R.drawable.fitmind_breathwork_min));


        recyclerView .setLayoutManager(new LinearLayoutManager(FitMindProgramsActivity.this));
        FitMindAdapter adapter = new FitMindAdapter(FitMindProgramsActivity.this, list);
        recyclerView.setAdapter(adapter);


    }

}
