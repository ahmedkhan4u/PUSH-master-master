package com.push.sweateliteathletics.softrasol.MyActivities;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.push.sweateliteathletics.softrasol.Adapters.FitBodyProgramsAdapter;
import com.push.sweateliteathletics.softrasol.Models.ProgramsModel;
import com.push.sweateliteathletics.softrasol.R;

import java.util.ArrayList;
import java.util.List;

public class FitBodyProgramsActivity extends AppCompatActivity {

    private List<ProgramsModel> list = new ArrayList<>();

    private RecyclerView recyclerView;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fit_mind_program);
        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.black));//


        recyclerView = findViewById(R.id.recyclerview_fit_body_programs);
        recyclerViewImplementation();

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

        recyclerView .setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        FitBodyProgramsAdapter adapter = new FitBodyProgramsAdapter(FitBodyProgramsActivity.this, list);
        recyclerView.setAdapter(adapter);


    }

    public void BackClick(View view) {
        onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }
}
