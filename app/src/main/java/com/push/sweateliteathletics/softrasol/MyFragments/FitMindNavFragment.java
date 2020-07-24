package com.push.sweateliteathletics.softrasol.MyFragments;


import android.app.ActivityManager;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.push.sweateliteathletics.softrasol.BroadcastService;
import com.push.sweateliteathletics.softrasol.Helper.UploadGoalData;
import com.push.sweateliteathletics.softrasol.MyActivities.BreathworkActivity;
import com.push.sweateliteathletics.softrasol.MyActivities.FitBodyProgramsActivity;
import com.push.sweateliteathletics.softrasol.MyActivities.FitMindProgramsActivity;
import com.push.sweateliteathletics.softrasol.MyActivities.MeditationCategoryActivity;
import com.push.sweateliteathletics.softrasol.MyActivities.MotivationActivity;
import com.push.sweateliteathletics.softrasol.MyActivities.MindsetActivity;
import com.push.sweateliteathletics.softrasol.R;
import com.push.sweateliteathletics.softrasol.services.Alarm60min;
import com.push.sweateliteathletics.softrasol.services.AlarmReceiver;
import com.push.sweateliteathletics.softrasol.services.Mt60minService;
import com.push.sweateliteathletics.softrasol.services.utils.NotificationScheduler;

import org.w3c.dom.Text;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.push.sweateliteathletics.softrasol.services.Alarm60min.milisecremaingin;

/**
 * A simple {@link Fragment} subclass.
 */
public class FitMindNavFragment extends Fragment {


    public FitMindNavFragment() {
        // Required empty public constructor
    }

    private View mView;
    private AlertDialog alert11;
    private RelativeLayout mBreathWork, mMindset, mMeditation, mMotivation, mPrograms
            , mMyPush;

    private String mtxtGoal;
    private String mChoosedDay;

    private String date = "";
    private int mYear;
    private int mMonth;
    private int mDay;

    private List<String> list = new ArrayList<>();
    long ms;

    long miliSecsDate;

//adfasdf

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_fit_mind_nav, container, false);


        fillListWithDays();

        widgetsInitialization();
        breathWorkClick();
        mindSetClick();
        meditationClick();
        motivationClick();
        programsClick();
        myPushClick();

        return mView;
    }

    private void fillListWithDays() {

        list.add("Choose Day");

        for (int i=1; i<=30; i++){
            list.add(i+"");
        }

    }

    private void myPushClick() {
        mMyPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().
                        getSharedPreferences("fitmind",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                final String time = sharedPreferences.getString("time","");
                final String goal = sharedPreferences.getString("goal","");
                final String date = sharedPreferences.getString("date","");

                if (!sharedPreferences.contains("time")){
                    Log.d("dxdiag", "Share Preg Not Exists "+time);
                    showAlertDialog("", "");
                }

                else if (time.equals("") || time.equals("0") || Long.parseLong(time) < 0){
                    showAlertDialog("", "");
                    Log.d("dxdiag", "Time Up"+time);
                }

                else {
                    Log.d("dxdiag", " Data Exista "+time);
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                    builder1.setTitle("Push Goal");
                    builder1.setMessage(

                            "My goal is " + goal+ " till " +
                                    date
                    );

                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Update",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    showAlertDialog(goal, date);
                                }
                            });

                    builder1.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }

                Log.d("dxdiag", "Share Pref Time "+time);

                }

        });
    }

    private void showAlertDialog(String pGoal, final String pDate) {

        boolean forEdit = false;

        if (!pGoal.equals("") && !pDate.equals("")) {
            forEdit = true;
        }

        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setCancelable(true);
        View myView = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_set_push_goal, null);
        builder1.setView(myView);
        builder1.setCancelable(false);

        final EditText mEdtGoal = myView.findViewById(R.id.edt_dialog_fitmind_goal);
        final TextView mTxtDate = myView.findViewById(R.id.txt_dialog_fitmind_goal_date);
        final TextView mTxtHeader = myView.findViewById(R.id.txt_dialog_header);

        mEdtGoal.setText(pGoal);
        mTxtDate.setText(pDate);

        mTxtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker(mTxtDate, pDate);
            }
        });

        Button mBtnCancel = myView.findViewById(R.id.btn_cancel_dialog_fitmind);
        Button mBtnSubmit = myView.findViewById(R.id.btn_submit_dialog_fitmind);

        if (forEdit) {
            mBtnSubmit.setText("Edit");
            mTxtHeader.setText("Edit Push Goal");
        }

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert11.cancel();
            }
        });

        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                mtxtGoal = mEdtGoal.getText().toString().trim();

                if (date.isEmpty()){
                    Toast.makeText(getActivity(), "Choose Date", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (mtxtGoal.isEmpty()){
                    mEdtGoal.setError("Required");
                    mEdtGoal.requestFocus();
                    return;
                }


                isMyServiceRunning(BroadcastService.class);

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("fitmind", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();

                SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences("fitbody", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor1 = sharedPreferences1.edit();

                editor.putString("time", miliSecsDate+"");
                editor.putString("date", date);
                editor.putString("is_started","true");
                editor.putString("goal", mtxtGoal);
                editor1.putString("type2", "fitmind");
                editor1.putString("content2", mtxtGoal);
                editor.apply();
                editor1.apply();
                Log.d("counterisrunning", "work here 0");

                NotificationScheduler.setReminder(getContext(), AlarmReceiver.class, ms, 1000);

                 if(!isMyServiceRunning(BroadcastService.class)){
                     milisecremaingin  = 0;


                    getActivity().startService(new Intent(getActivity(), Mt60minService.class));
               }
                alert11.cancel();

            }
        });


        alert11 = builder1.create();
        alert11.show();
    }


    private void programsClick() {
        mPrograms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FitMindProgramsActivity.class));
            }
        });
    }

    private void motivationClick() {
        mMotivation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MotivationActivity.class));
            }
        });
    }

    private void meditationClick() {
        mMeditation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MeditationCategoryActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    private void mindSetClick() {
        mMindset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MindsetActivity.class));
            }
        });
    }

    private void breathWorkClick() {
        mBreathWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BreathworkActivity.class));
            }
        });
    }

    private void widgetsInitialization() {

        mBreathWork = mView.findViewById(R.id.fitmind_breathwork);
        mMindset = mView.findViewById(R.id.fitmind_mindset);
        mMeditation = mView.findViewById(R.id.fitmind_meditation);
        mMotivation = mView.findViewById(R.id.fitmind_motivation);
        mPrograms = mView.findViewById(R.id.fitmind_programs);
        mMyPush = mView.findViewById(R.id.fitmind_mypush);
    }


    private void datePicker(final TextView mTxtDate, String prevSelectedDate) {

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        if (!prevSelectedDate.equals("")) {
            Log.e("hi5", prevSelectedDate);
            String[] dateArr = prevSelectedDate.split("/");
            mYear = Integer.parseInt(dateArr[0]);
            mMonth = Integer.parseInt(dateArr[1]) - 1;
            mDay = Integer.parseInt(dateArr[2]);
        } else {
            Log.e("hi5", prevSelectedDate);
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
        }



        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        date = year+"/"+(monthOfYear + 1)+"/"+dayOfMonth;
                        mTxtDate.setText(date);

                        Date date = new Date();
                        String cday          = (String) DateFormat.format("dd",   date); // 20
                        String cmonthNumber  = (String) DateFormat.format("MM",   date); // 06
                        String cyear         = (String) DateFormat.format("yyyy", date); // 2013

                        LocalDate start = LocalDate.of(Integer.parseInt(cyear), Integer.parseInt(cmonthNumber),
                                Integer.parseInt(cday));
                        LocalDate end = LocalDate.of(year,(monthOfYear + 1),dayOfMonth);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            Date mDate = sdf.parse(String.valueOf(end));
                            long timeInMilliseconds = mDate.getTime();
                            ms = timeInMilliseconds;
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String futureDays = ChronoUnit.DAYS.between(start, end)+"";

                        miliSecsDate = TimeUnit.DAYS.toMillis(Long.parseLong(futureDays));


                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}
