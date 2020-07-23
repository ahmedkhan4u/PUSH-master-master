package com.push.sweateliteathletics.softrasol.OnBoardingScreens;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.push.sweateliteathletics.softrasol.Adapters.TabsAccessorAdapter;
import com.push.sweateliteathletics.softrasol.R;

public class OnBoardingActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        TabsAccessorAdapter adapter = new TabsAccessorAdapter(getSupportFragmentManager());
        adapter.setFragment(new TutorialFragment1(), "");
        adapter.setFragment(new TutorialFragment2(), "");
        adapter.setFragment(new TutorialFragment3(), "");

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(adapter);

        viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                page.setRotationY(position * -30);
            }
        });
    }
}
