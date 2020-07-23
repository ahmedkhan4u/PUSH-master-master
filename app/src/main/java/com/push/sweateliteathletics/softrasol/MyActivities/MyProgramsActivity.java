package com.push.sweateliteathletics.softrasol.MyActivities;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.push.sweateliteathletics.softrasol.Adapters.TabsAccessorAdapter;
import com.push.sweateliteathletics.softrasol.MyFragments.FitBodyFragment;
import com.push.sweateliteathletics.softrasol.MyFragments.FitMindFragment;
import com.push.sweateliteathletics.softrasol.R;

public class MyProgramsActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ImageButton mBtnBack;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_programs);
        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.black));//


        widgetsInitialization();
        btnBackClick();

        TabsAccessorAdapter adapter = new TabsAccessorAdapter(getSupportFragmentManager());
        adapter.setFragment(new FitMindFragment(), "Fit Mind");
        adapter.setFragment(new FitBodyFragment(), "Fit Body");

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(adapter);

    }

    private void btnBackClick() {

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void widgetsInitialization() {

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        mBtnBack = findViewById(R.id.btn_back);

    }

}
