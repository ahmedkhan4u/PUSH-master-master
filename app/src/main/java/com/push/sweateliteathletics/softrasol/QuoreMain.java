package com.push.sweateliteathletics.softrasol;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Process;
import androidx.annotation.NonNull;

import com.facebook.login.LoginManager;
import com.facebook.share.Share;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.google.android.gms.ads.AdRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.onesignal.OneSignal;
import com.push.sweateliteathletics.softrasol.MyActivities.NameSettingActivity;
import com.push.sweateliteathletics.softrasol.MyFragments.FitBodyNavFragment;
import com.push.sweateliteathletics.softrasol.MyFragments.FitMindNavFragment;
import com.push.sweateliteathletics.softrasol.MyFragments.PushFragment;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class QuoreMain extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {
    public static boolean isStarted = false;
    private FragmentDrawer drawerFragment;
    private Toolbar mToolbar;
    private SharedPreferences qasa2;
    Menu menuitems;

    private BottomNavigationView mBottomNavigationView;
    ImageView music;

    FirebaseAuth mAuth;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



//        if (QuoreGlavna.isTrainingStarted || isStarted) {
//            finish();
//            return;
//        }
        setContentView(R.layout.activity_main);

        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(QuoreMain.this,R.color.black));//

        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        mAuth = FirebaseAuth.getInstance();
        checkUserNameSet();

        this.mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.whitefontcolorr));

        setFragment(new PushFragment());
        mToolbar.setVisibility(View.GONE);

        implementingBottomNavigationView();


        RelativeLayout bnnr = (RelativeLayout) findViewById(R.id.bnr);
        //Banner(bnnr, QuoreMain.this);


        music = (ImageView) findViewById(R.id.music);


        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent = Intent.makeMainSelectorActivity("android.intent.action.MAIN", "android.intent.category.APP_MUSIC");
                    intent.addFlags(268435456);
                    startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();

                }

            }
        });


        this.drawerFragment = (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        this.drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), this.mToolbar);
        this.drawerFragment.setDrawerListener(this);
        displayView(10);
        try {
            String PREFS_NAME = "qA1sa2";
            this.qasa2 = getSharedPreferences("qA1sa2", 0);
            AdRequest.Builder builder = new AdRequest.Builder();
            int year = Calendar.getInstance(Locale.GERMANY).get(Calendar.YEAR) - this.qasa2.getInt("godine", 25);
            int gender = this.qasa2.getInt("pol", 0);
            try {
                double lat = Double.longBitsToDouble(this.qasa2.getLong("latitude", Double.doubleToLongBits(0.0d)));
                double lon = Double.longBitsToDouble(this.qasa2.getLong("longitude", Double.doubleToLongBits(0.0d)));
                if (!(lat == 0.0d || lon == 0.0d)) {
                    Location loc = new Location(BuildConfig.FLAVOR);
                    loc.setLatitude(lat);
                    loc.setLongitude(lon);
                    builder.setLocation(loc);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (year > 1920 && year < 2005) {
                builder.setBirthday(new GregorianCalendar(year, 1, 1).getTime());
            }
            if (gender == 0) {
                builder.setGender(1);
            } else {
                builder.setGender(2);
            }

        } catch (Exception e2) {
            try {

            } catch (Exception e1) {
                e1.printStackTrace();
            }
            e2.printStackTrace();
        }
        isStarted = true;

    }


    private void checkUserNameSet() {

        CollectionReference collectionReference =
                FirebaseFirestore.getInstance().collection("users");

        DocumentReference documentReference = collectionReference.document(mAuth.getUid());

        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    if (!task.getResult().exists()){
                        startActivity(new Intent(getApplicationContext(), NameSettingActivity.class));
                    }
                }
            }
        });

    }


    @Override
    public void onBackPressed() {
        exit();
    }

    public void exit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_warning_black_24dp);
        builder.setTitle(R.string.Exit);
        builder.setMessage(getString(R.string.exit_text));
        builder.setNegativeButton("No", null);
        builder.setPositiveButton("Yes", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (QuoreGlavna.isTrainingStarted) {
                    Toast.makeText(QuoreMain.this.getApplicationContext(), QuoreMain.this.getResources().getString(R.string.first_stop), Toast.LENGTH_SHORT).show();
                    return;
                }
                QuoreMain.this.finish();
                Process.killProcess(Process.myPid());
                System.exit(1);
            }
        });
        builder.show();
    }

    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0 /*0*/:
                if (!QuoreGlavna.isTrainingStarted) {
                    fragment = new QuoreGlavna();
                    title = getString(R.string.app_name);
                    mToolbar.setVisibility(View.VISIBLE);
                    break;
                }
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.first_stop), Toast.LENGTH_LONG).show();
                break;
            case 1 /*1*/:
                if (!QuoreGlavna.isTrainingStarted) {
                    fragment = new QuoreGraphs();
                    title = getString(R.string.app_name);
                    mToolbar.setVisibility(View.VISIBLE);
                    //Google_Itrestial_Ads(QuoreMain.this, getString(R.string.ads_inter));
                    break;
                }
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.first_stop), Toast.LENGTH_LONG).show();
                break;
            case 2 /*2*/:
                if (!QuoreGlavna.isTrainingStarted) {
                    fragment = new QuoreChallenges();
                    title = getString(R.string.app_name);
                    mToolbar.setVisibility(View.VISIBLE);
                    break;
                }
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.first_stop), Toast.LENGTH_LONG).show();
                break;
            case 3 /*3*/:
                if (!QuoreGlavna.isTrainingStarted) {
                    fragment = new QuoreRecords();
                    title = getString(R.string.app_name);
                    mToolbar.setVisibility(View.VISIBLE);

                    //Google_Itrestial_Ads(QuoreMain.this, getString(R.string.ads_inter));
                    break;
                }
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.first_stop), Toast.LENGTH_LONG).show();
                break;
            case 4 /*4*/:
                if (!QuoreGlavna.isTrainingStarted) {
                    fragment = new QuoreHistory();
                    title = getString(R.string.app_name);
                    mToolbar.setVisibility(View.VISIBLE);
                    break;
                }
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.first_stop), Toast.LENGTH_LONG).show();
                break;
            case 5 /*5*/:
                if (!QuoreGlavna.isTrainingStarted) {
                    fragment = new QuoreOverall();
                    title = getString(R.string.app_name);
//                    fb_interstal(QuoreMain.this);
                    mToolbar.setVisibility(View.VISIBLE);
                    break;
                }
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.first_stop), Toast.LENGTH_SHORT).show();
                break;
            case 6 /*6*/:
                if (!QuoreGlavna.isTrainingStarted) {
                    fragment = new QuoreBMI();
                    title = getString(R.string.app_name);
                    mToolbar.setVisibility(View.VISIBLE);
                    break;
                }
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.first_stop), Toast.LENGTH_LONG).show();
                break;

            case 7 /*8*/:
                if (!QuoreGlavna.isTrainingStarted) {
                    startActivity(new Intent(this, QuoreAppSettings.class));
                    mToolbar.setVisibility(View.VISIBLE);
                    break;
                }
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.first_stop), Toast.LENGTH_LONG).show();
                break;

            case 8:
                if (!QuoreGlavna.isTrainingStarted) {
                    fragment = new PushFragment();
                    title = getString(R.string.app_name);
                    mToolbar.setVisibility(View.VISIBLE);
                    break;
                }
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.first_stop), Toast.LENGTH_LONG).show();
                break;
        }
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right);
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
            getSupportActionBar().setTitle(title);
        }
    }



    private void implementingBottomNavigationView() {

        mBottomNavigationView = findViewById(R.id.bottom_nav);

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.nav1:
                        setFragment(new PushFragment());
                        mToolbar.setVisibility(View.GONE);
                        return true;

                    case R.id.nav2:
                        setFragment(new QuoreGlavna());
                        mToolbar.setVisibility(View.VISIBLE);
                        return true;

                    case R.id.nav3:
                        setFragment(new CameraFragment());
                        mToolbar.setVisibility(View.GONE);
                        return true;

                    case R.id.nav4:
                        setFragment(new FitMindNavFragment());
                        mToolbar.setVisibility(View.GONE);
                        return true;

                    case R.id.nav5:
                        setFragment(new FitBodyNavFragment());
                        mToolbar.setVisibility(View.GONE);
                        return true;

                        default:
                            return false;
                }
            }
        });

    }

    private void setFragment(Fragment fragment){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);
        transaction.replace(R.id.container_body, fragment);
        transaction.commit();


    }

}
