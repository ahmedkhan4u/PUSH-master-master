package com.push.sweateliteathletics.softrasol.MyFragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.push.sweateliteathletics.softrasol.Adapters.FavoritesAdapter;
import com.push.sweateliteathletics.softrasol.FontTextView;
import com.push.sweateliteathletics.softrasol.Main2Activity;
import com.push.sweateliteathletics.softrasol.Models.FavoritesModel;
import com.push.sweateliteathletics.softrasol.MyActivities.EditNameActivity;
import com.push.sweateliteathletics.softrasol.MyActivities.MyProgramsActivity;
import com.push.sweateliteathletics.softrasol.MyActivities.SettingsActivity;
import com.push.sweateliteathletics.softrasol.R;
import com.push.sweateliteathletics.softrasol.SignIn;
import com.push.sweateliteathletics.softrasol.TitileTextView;
import com.push.sweateliteathletics.softrasol.services.Alarm60min;
import com.push.sweateliteathletics.softrasol.services.OreoNotification;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PushFragment extends Fragment {
    private Chronometer cDuration;
    private double maxCalories;
    private double maxDistance;
    private double maxDuration;
    private double maxPace;
    private double maxSpeed;
    private SharedPreferences qasa2;
    private FontTextView tvCalories;
    private FontTextView tvDistance;
    private FontTextView tvPace;
    private FontTextView tvSpeed;
    private String units;

    private View rootView;

    private TextView mTxtName;
    private TextView mTxtDate;
    private String name;
    private Button mBtnMyPrograms, mBtnEdit;
    private TextView mTxtFitmindGoal,
    mTxtFitBodyGoal;
    private Button mBtnSignOut;

    private RecyclerView mRecyclerView;
    CollectionReference collectionReference;
    private List<FavoritesModel> list = new ArrayList<>();


    private Toolbar toolbar;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private TextView mTxtMindGoal, mTxtBodyGoal;

    long millisUntilFinishe = 1596543062299L;

    CountDownTimer countDownTimer;

    CountDownTimer countDownTimer2;

    private TextView mTxtMindDay, mTxtMindHour, mTxtMindMinute, mTxtMindSeconds, mTxtBodyDay,
            mTxtBodyHour, mTxtBodyMinute, mTxtBodySeconds;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.push_fragment, container, false);
        setHasOptionsMenu(true);
        toolbarInitilization();
        databaseReference();
        widgetsInitialization();
        getPersonNameFromFirebaseFirestore();
        setDateCurrentDate();
        btnMyProgramsClick();

        //getFitMindGoal();
        //getFitBodyGoal();
        showMindCoundDownTimer();
        showBodyCountDownTimer();

        String PREFS_NAME = "qA1sa2";
        this.qasa2 = getActivity().getSharedPreferences("qA1sa2", 0);
        readData();

        return rootView;
    }

//    private void scheduleNotification(){
//        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+5:30"));
////        calendar.setTime(System.currentTimeMillis());
//        calendar.set(Calendar.SECOND,0);
//        Intent intent = new Intent(getContext(), NotifierAlarm.class);
//        intent.putExtra("Message","Hi");
//        intent.putExtra("RemindDate","Bye");
//        intent.putExtra("id","1001");
//        PendingIntent intent1 = PendingIntent.getBroadcast(getContext(), 1001, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        AlarmManager alarmManager = (AlarmManager)getContext().getSystemService(ALARM_SERVICE);
//        alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),intent1);
//    }

    private void showBodyCountDownTimer() {

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("fitbody", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        mTxtFitBodyGoal.setText(sharedPreferences.getString("goal", "Your Goal"));

        long remaining_time0 = System.currentTimeMillis() -  Long.parseLong(sharedPreferences.getString("destroy_timer","0")) ;
        Log.d("counter_timer", "time0  "+remaining_time0);
        final long milliSeceonds = Long.parseLong(sharedPreferences.getString("time","0"));

        Log.d("counter_timer", "time1  "+milliSeceonds);
        long finaltime_Remaining = milliSeceonds - remaining_time0;
        Log.d("counter_timer", "time2  "+finaltime_Remaining);


        if (finaltime_Remaining != milliSeceonds) {

            editor.putString("time", finaltime_Remaining + "");
            editor.apply();

            String is_started = sharedPreferences.getString("is_started", "false");

            if (is_started.equalsIgnoreCase("true")) {


                countDownTimer2 = new CountDownTimer(finaltime_Remaining, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                        Log.d("counter", "Counter Values " + Alarm60min.milisecremaingin + "");


                        long days = millisUntilFinished / (24 * 60 * 60 * 1000);
                        millisUntilFinished -= days * (24 * 60 * 60 * 1000);
                        long hours = millisUntilFinished / (60 * 60 * 1000);
                        millisUntilFinished -= hours * (60 * 60 * 1000);
                        long minutes = millisUntilFinished / (60 * 1000);
                        millisUntilFinished -= minutes * (60 * 1000);
                        long seconds = millisUntilFinished / 1000;

                        long diffInDays = TimeUnit.MILLISECONDS.toDays(millisUntilFinished);
                        long diffInHours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
                        long diffInMin = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                        long diffInSec = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);

                        System.out.println("date cal is " + (int) days + "_" + (int) hours + "_" + (int) minutes + "_" + (int) seconds);

                        mTxtBodyDay.setText(days+"");
                        mTxtBodyHour.setText(hours+"");
                        mTxtBodyMinute.setText(minutes+"");
                        mTxtBodySeconds.setText(seconds+"");




//                        if (days < 1) {
//                            Log.d("counter", "meeeeee in 0");
//
//                            if (hours < 1) {
//                                Log.d("counter", "meeeeee in 1");
//
//                                if (minutes < 1) {
//                                    Log.d("counter", "meeeeee in 2");
//
//                                    if (seconds == 0) {
//                                        Log.d("counter", "meeeeee in 3");
//
//                                        mTxtBodyGoal.setTextColor(Color.RED);
//                                        mTxtBodyGoal.setText("Time Over");
//                                        countDownTimer2.cancel();
//                                    }
//                                }
//                            }
//                        }


                    }

                    @Override
                    public void onFinish() {

                    }
                };

                countDownTimer2.start();


            }
        }else {

            mTxtMindGoal.setTextColor(Color.RED);
            mTxtMindGoal.setText("Time Over");
        }

    }

    @SuppressLint("ResourceAsColor")
    private void toolbarInitilization() {
        toolbar = rootView.findViewById(R.id.toolbar_main);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        toolbar.getOverflowIcon().setColorFilter(android.R.color.white, PorterDuff.Mode.SRC_ATOP);
        toolbar.setTitle("");
    }

    private void showMindCoundDownTimer() {


        sharedPreferences = getActivity().getSharedPreferences("fitmind", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        mTxtFitmindGoal.setText(sharedPreferences.getString("goal", "Your Goal"));

        long remaining_time0 = System.currentTimeMillis() -  Long.parseLong(sharedPreferences.getString("destroy_timer","0")) ;
        Log.d("counter_timer", "time0  "+remaining_time0);
        final long milliSeceonds = Long.parseLong(sharedPreferences.getString("time","0"));

        Log.d("counter_timer", "time1  "+milliSeceonds);
        long finaltime_Remaining = milliSeceonds - remaining_time0;
        Log.d("counter_timer", "time2  "+finaltime_Remaining);


        if (finaltime_Remaining != milliSeceonds) {

            editor.putString("time", finaltime_Remaining + "");
            editor.apply();

            String is_started = sharedPreferences.getString("is_started", "false");

            if (is_started.equalsIgnoreCase("true")) {


                countDownTimer = new CountDownTimer(finaltime_Remaining, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                        Log.d("counter", "Counter Values " + Alarm60min.milisecremaingin + "");


                        long days = millisUntilFinished / (24 * 60 * 60 * 1000);
                        millisUntilFinished -= days * (24 * 60 * 60 * 1000);
                        long hours = millisUntilFinished / (60 * 60 * 1000);
                        millisUntilFinished -= hours * (60 * 60 * 1000);
                        long minutes = millisUntilFinished / (60 * 1000);
                        millisUntilFinished -= minutes * (60 * 1000);
                        long seconds = millisUntilFinished / 1000;

                        System.out.println("date cal is " + (int) days + "_" + (int) hours + "_" + (int) minutes + "_" + (int) seconds);

                        mTxtMindDay.setText(days+"");
                        mTxtMindHour.setText(hours+"");
                        mTxtMindMinute.setText(minutes+"");
                        mTxtMindSeconds.setText(seconds+"");

//                        if (days < 1) {
//                            Log.d("counter", "meeeeee in 0");
//
//                            if (hours < 1) {
//                                Log.d("counter", "meeeeee in 1");
//
//                                if (minutes < 1) {
//                                    Log.d("counter", "meeeeee in 2");
//
//                                    if (seconds == 0) {
//                                        Log.d("counter", "meeeeee in 3");
//
//
//
//
//                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                                            Log.d("counter", "meeeeee in 44");
//
//                                            PushFragment.sendOreoNotification(getActivity(),"Timer Up","Dear User Your Timer is up");
//                                        } else {
//
//                                            Log.d("counter", "meeeeee in 55");
//                                            PushFragment.sendNotification(getActivity(),"Timer Up","Dear User Your Timer is up");
//                                        }
//
//
//
//                                        mTxtMindGoal.setTextColor(Color.RED);
//                                        mTxtMindGoal.setText("Time Over");
//                                        countDownTimer.cancel();
//
//                                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("fitbody", Context.MODE_PRIVATE);
//                                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                                        editor.putString("time", "0");
//                                        editor.apply();
//
//                                    }
//                                }
//                            }
//                        }


                    }

                    @Override
                    public void onFinish() {

                    }
                };

                countDownTimer.start();


            }
        }else {

            mTxtMindGoal.setTextColor(Color.RED);
            mTxtMindGoal.setText("Time Over");
        }
    }



    public static void sendNotification(Context context, String notificationTitle, String notificationBody) {

        Random random1 = new Random();
        int j = random1.nextInt(5);

        Intent intent = new Intent(context, Main2Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, j, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSound = Uri.parse("android.resource://"+context.getPackageName()+"/"+R.raw.notification);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_date)
                .setContentTitle(notificationTitle)
                .setContentText(notificationBody)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
        NotificationManager noti = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Random random = new Random();
        int num = random.nextInt(5);

        noti.notify(num, builder.build());
    }


    public static void sendOreoNotification(Context context, String notificationTitle, String notificationBody) {

        Random random1 = new Random();
        int j = random1.nextInt(5);

        Intent intent = new Intent(context, Main2Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, j, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSound = Uri.parse("android.resource://"+context.getPackageName()+"/"+R.raw.notification);

        OreoNotification oreoNotification = new OreoNotification(context);
        Notification.Builder builder = oreoNotification.getOreoNotification(notificationTitle, notificationBody, pendingIntent,
                defaultSound, R.drawable.ic_date);

        Random random = new Random();
        int num = random.nextInt(5);

        oreoNotification.getManager().notify(num, builder.build());

    }






    @Override
    public void onDestroy() {

        Log.d("counter_timer", "time destroyed  ");
        sharedPreferences = getActivity().getSharedPreferences("fitmind", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        editor.putString("destroy_timer",System.currentTimeMillis()+"");
        editor.apply();

        Log.d("counter_timer", "time destroyed  ");
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("fitbody", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("destroy_timer",System.currentTimeMillis()+"");
        editor.apply();

        super.onDestroy();
    }

    private void getFavoritesData() {

        list.clear();
        CollectionReference collectionReference = FirebaseFirestore.getInstance()
                .collection("users").document(FirebaseAuth.getInstance().getUid())
                .collection("favorites");

        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().size()>=0){
                        for (QueryDocumentSnapshot snapshot : task.getResult()){
                            FavoritesModel model = snapshot.toObject(FavoritesModel.class);
                            list.add(model);
                        }

                        if (list.isEmpty()){
                            list.add(new FavoritesModel("videos",
                                    "", "Add Favorite"));

                            list.add(new FavoritesModel("videos",
                                    "", "Add Favorite"));

                            list.add(new FavoritesModel("videos",
                                    "", "Add Favorite"));

                            implementRecyclerView();

                        }

                        else if (list.size() == 1){


                            list.add(new FavoritesModel("videos",
                                    "", "Add Favorite"));

                            list.add(new FavoritesModel("videos",
                                    "", "Add Favorite"));
                            implementRecyclerView();

                        }

                        else if (list.size() == 2){


                            list.add(new FavoritesModel("videos",
                                    "", "Add Favorite"));

                            implementRecyclerView();

                        }

                        else {
                            implementRecyclerView();
                        }

                    }
                }
            }
        });

    }

    private void implementRecyclerView() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL,false));

        FavoritesAdapter adapter = new FavoritesAdapter(getActivity(), list);
        mRecyclerView.setAdapter(adapter);

    }


    private void databaseReference() {
        collectionReference = FirebaseFirestore.getInstance()
                .collection("users").document(FirebaseAuth.getInstance().getUid())
                .collection("goals");
    }


    private void btnMyProgramsClick() {
        mBtnMyPrograms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyProgramsActivity.class));
            }
        });
    }

    private void setDateCurrentDate() {
        Date date = new Date();
        String current_date = date.toLocaleString();
        String sub_string = current_date.substring(0, 12);
        mTxtDate.setText(sub_string);
    }

    private void getPersonNameFromFirebaseFirestore() {



        CollectionReference collectionReference =
                FirebaseFirestore.getInstance().collection("users");

        DocumentReference documentReference = collectionReference
                .document(FirebaseAuth.getInstance().getUid());

        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        name = task.getResult().getString("name");
                        mTxtName.setText("Hi "+name+",");

                    }
                }
            }
        });


    }

    private void widgetsInitialization() {
        this.cDuration = (Chronometer) rootView.findViewById(R.id.chronoRec);
        this.tvDistance = (FontTextView) rootView.findViewById(R.id.tvRecordDistance);
        this.tvSpeed = (FontTextView) rootView.findViewById(R.id.tvRecordSpeed);
        this.tvPace = (FontTextView) rootView.findViewById(R.id.tvRecordPace);
        this.tvCalories = (FontTextView) rootView.findViewById(R.id.tvRecordCalories);

        mTxtName = rootView.findViewById(R.id.txt_push_name);
        mTxtDate = rootView.findViewById(R.id.txt_push_current_date);
        mTxtFitmindGoal = rootView.findViewById(R.id.txt_fitmind_goal);
        mTxtFitBodyGoal = rootView.findViewById(R.id.txt_fitbody_goal);

        mBtnMyPrograms = rootView.findViewById(R.id.btn_push_myprograms);

        mRecyclerView = rootView.findViewById(R.id.recyclerview_favorite);

        mTxtMindDay = rootView.findViewById(R.id.fitmind_day);
        mTxtMindHour = rootView.findViewById(R.id.fitmind_hour);
        mTxtMindMinute = rootView.findViewById(R.id.fitmind_minute);
        mTxtMindSeconds = rootView.findViewById(R.id.fitmind_seconds);
        mTxtBodyDay = rootView.findViewById(R.id.fitbody_day);
        mTxtBodyHour = rootView.findViewById(R.id.fitbody_hour);
        mTxtBodyMinute = rootView.findViewById(R.id.fitbody_minute);
        mTxtBodySeconds = rootView.findViewById(R.id.fitbody_seconds);

        mTxtBodyGoal = rootView.findViewById(R.id.txtBodyTime);
        mTxtMindGoal = rootView.findViewById(R.id.txtMindTime);


    }

    private void readData() {
        this.maxDuration = Double.longBitsToDouble(this.qasa2.getLong("rekordTrajanje", Double.doubleToLongBits(0.0d)));
        this.maxDistance = Double.longBitsToDouble(this.qasa2.getLong("rekordRastojanje", Double.doubleToLongBits(0.0d)));
        this.maxSpeed = Double.longBitsToDouble(this.qasa2.getLong("rekordBrzina", Double.doubleToLongBits(0.0d)));
        this.maxPace = Double.longBitsToDouble(this.qasa2.getLong("rekordRitamRas", Double.doubleToLongBits(0.0d)));
        this.maxCalories = Double.longBitsToDouble(this.qasa2.getLong("rekordKalorije", Double.doubleToLongBits(0.0d)));
    }

    private void showData() {
        if (this.maxDuration < 3600000.0d) {
            this.cDuration.setFormat("00:%s");
        }
        this.cDuration.setBase(SystemClock.elapsedRealtime() - ((long) this.maxDuration));
        this.tvCalories.setText(String.format("%.1f", new Object[]{Double.valueOf(this.maxCalories)}) + " " + getResources().getString(R.string.kcal));
        if (this.units.equalsIgnoreCase("Metric")) {
            this.tvDistance.setText(String.format("%.3f", new Object[]{Double.valueOf(this.maxDistance)}) + " " + getResources().getString(R.string.km));
            this.tvSpeed.setText(String.format("%.2f", new Object[]{Double.valueOf(this.maxSpeed)}) + " " + getResources().getString(R.string.kph));
            this.tvPace.setText(String.format("%.0f", new Object[]{Double.valueOf(this.maxPace)}) + " " + getResources().getString(R.string.min) + "/" + getResources().getString(R.string.km));
            return;
        }
        this.tvDistance.setText(String.format("%.3f", new Object[]{Double.valueOf(this.maxDistance * 0.621371d)}) + " " + getResources().getString(R.string.mi));
        this.tvSpeed.setText(String.format("%.2f", new Object[]{Double.valueOf(this.maxSpeed * 0.621371d)}) + " " + getResources().getString(R.string.mph));
        this.tvPace.setText(String.format("%.0f", new Object[]{Double.valueOf(this.maxPace * 1.609344d)}) + " " + getResources().getString(R.string.min) + "/" + getResources().getString(R.string.mi));
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onDetach() {
        super.onDetach();
    }

    public void onResume() {
        super.onResume();
        this.units = this.qasa2.getString("units", "Metric");
        showData();
        getFavoritesData();

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.settings:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
//            case R.id.signout:
//                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
//                builder1.setMessage("Are you sure you want to sign out.");
//                builder1.setCancelable(true);
//
//                builder1.setPositiveButton(
//                        "Yes",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                FirebaseAuth.getInstance().signOut();
//                                startActivity(new Intent(getActivity(), SignIn.class));
//                                getActivity().finish();
//                                dialog.cancel();
//                            }
//                        });
//
//                builder1.setNegativeButton(
//                        "No",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                dialog.cancel();
//                            }
//                        });
//
//                AlertDialog alert11 = builder1.create();
//                alert11.show();
//
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
