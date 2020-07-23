package com.push.sweateliteathletics.softrasol;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class QuoreChallenges extends Fragment {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_challenges, container, false);
        Context context = getActivity();
        ProgressBar pb1 = (ProgressBar) rootView.findViewById(R.id.pbChallenges1);
        ProgressBar pb2 = (ProgressBar) rootView.findViewById(R.id.pbChallenges2);
        ProgressBar pb3 = (ProgressBar) rootView.findViewById(R.id.pbChallenges3);
        ProgressBar pb4 = (ProgressBar) rootView.findViewById(R.id.pbChallenges4);
        ProgressBar pb5 = (ProgressBar) rootView.findViewById(R.id.pbChallenges5);
        ProgressBar pb6 = (ProgressBar) rootView.findViewById(R.id.pbChallenges6);
        ProgressBar pb7 = (ProgressBar) rootView.findViewById(R.id.pbChallenges7);
        ProgressBar pb8 = (ProgressBar) rootView.findViewById(R.id.pbChallenges8);
        ProgressBar pb9 = (ProgressBar) rootView.findViewById(R.id.pbChallenges9);
        ProgressBar pb10 = (ProgressBar) rootView.findViewById(R.id.pbChallenges10);
        ProgressBar pb11 = (ProgressBar) rootView.findViewById(R.id.pbChallenges11);
        ProgressBar pb12 = (ProgressBar) rootView.findViewById(R.id.pbChallenges12);
        ProgressBar pb13 = (ProgressBar) rootView.findViewById(R.id.pbChallenges13);
        ProgressBar pb14 = (ProgressBar) rootView.findViewById(R.id.pbChallenges14);
        ProgressBar pb15 = (ProgressBar) rootView.findViewById(R.id.pbChallenges15);
        ProgressBar pb16 = (ProgressBar) rootView.findViewById(R.id.pbChallenges16);
        ProgressBar pb17 = (ProgressBar) rootView.findViewById(R.id.pbChallenges17);
        ImageView imDistance1 = (ImageView) rootView.findViewById(R.id.ivChallenges2);
        ImageView imDistance2 = (ImageView) rootView.findViewById(R.id.ivChallenges4);
        ImageView imDistance3 = (ImageView) rootView.findViewById(R.id.ivChallenges6);
        ImageView imDistance4 = (ImageView) rootView.findViewById(R.id.ivChallenges8);
        ImageView imDistance5 = (ImageView) rootView.findViewById(R.id.ivChallenges32);
        ImageView imDuration1 = (ImageView) rootView.findViewById(R.id.ivChallenges10);
        ImageView imDuration2 = (ImageView) rootView.findViewById(R.id.ivChallenges12);
        ImageView imDuration3 = (ImageView) rootView.findViewById(R.id.ivChallenges14);
        ImageView imDuration4 = (ImageView) rootView.findViewById(R.id.ivChallenges16);
        ImageView imDuration5 = (ImageView) rootView.findViewById(R.id.ivChallenges34);
        ImageView imSpeed1 = (ImageView) rootView.findViewById(R.id.ivChallenges18);
        ImageView imSpeed2 = (ImageView) rootView.findViewById(R.id.ivChallenges20);
        ImageView imCalories1 = (ImageView) rootView.findViewById(R.id.ivChallenges22);
        ImageView imCalories2 = (ImageView) rootView.findViewById(R.id.ivChallenges24);
        ImageView imCalories3 = (ImageView) rootView.findViewById(R.id.ivChallenges26);
        ImageView imCalories4 = (ImageView) rootView.findViewById(R.id.ivChallenges28);
        ImageView imCalories5 = (ImageView) rootView.findViewById(R.id.ivChallenges30);
        FontTextView tvDistance1 = (FontTextView) rootView.findViewById(R.id.tvChallenges2);
        FontTextView tvDistance2 = (FontTextView) rootView.findViewById(R.id.tvChallenges4);
        FontTextView tvDistance3 = (FontTextView) rootView.findViewById(R.id.tvChallenges6);
        FontTextView tvDistance4 = (FontTextView) rootView.findViewById(R.id.tvChallenges8);
        FontTextView tvDistance5 = (FontTextView) rootView.findViewById(R.id.tvChallenges32);
        FontTextView tvDuration1 = (FontTextView) rootView.findViewById(R.id.tvChallenges10);
        FontTextView tvDuration2 = (FontTextView) rootView.findViewById(R.id.tvChallenges12);
        FontTextView tvDuration3 = (FontTextView) rootView.findViewById(R.id.tvChallenges14);
        FontTextView tvDuration4 = (FontTextView) rootView.findViewById(R.id.tvChallenges16);
        FontTextView tvDuration5 = (FontTextView) rootView.findViewById(R.id.tvChallenges34);
        FontTextView tvSpeed1 = (FontTextView) rootView.findViewById(R.id.tvChallenges18);
        FontTextView tvSpeed2 = (FontTextView) rootView.findViewById(R.id.tvChallenges20);
        FontTextView tvCalories1 = (FontTextView) rootView.findViewById(R.id.tvChallenges22);
        FontTextView tvCalories2 = (FontTextView) rootView.findViewById(R.id.tvChallenges24);
        FontTextView tvCalories3 = (FontTextView) rootView.findViewById(R.id.tvChallenges26);
        FontTextView tvCalories4 = (FontTextView) rootView.findViewById(R.id.tvChallenges28);
        FontTextView tvCalories5 = (FontTextView) rootView.findViewById(R.id.tvChallenges30);
        FontTextView tvDistanceText1 = (FontTextView) rootView.findViewById(R.id.tvChallenges1);
        FontTextView tvDistanceText2 = (FontTextView) rootView.findViewById(R.id.tvChallenges3);
        FontTextView tvDistanceText3 = (FontTextView) rootView.findViewById(R.id.tvChallenges5);
        FontTextView tvDistanceText4 = (FontTextView) rootView.findViewById(R.id.tvChallenges7);
        FontTextView tvDistanceText5 = (FontTextView) rootView.findViewById(R.id.tvChallenges31);
        String PREFS_NAME = "qA1sa2";
        SharedPreferences qasa2 = getActivity().getSharedPreferences("qA1sa2", 0);
        double totalDuration = Double.longBitsToDouble(qasa2.getLong("ukupnoTrajanje", Double.doubleToLongBits(0.0d)));
        double totalDistance = Double.longBitsToDouble(qasa2.getLong("ukupnoRastojanje", Double.doubleToLongBits(0.0d)));
        double maxSpeed = Double.longBitsToDouble(qasa2.getLong("rekordBrzina", Double.doubleToLongBits(0.0d)));
        double totalCalories = Double.longBitsToDouble(qasa2.getLong("ukupnoKalorije", Double.doubleToLongBits(0.0d)));
        if (qasa2.getString("units", "Metric").equalsIgnoreCase("Metric")) {
            tvDistanceText1.setText(getResources().getString(R.string.Run_total_20_km));
            tvDistanceText2.setText(getResources().getString(R.string.Run_total_200_km));
            tvDistanceText3.setText(getResources().getString(R.string.Run_total_1000_km));
            tvDistanceText4.setText(getResources().getString(R.string.Run_total_5000_km));
            tvDistanceText5.setText(getResources().getString(R.string.Run_total_10000_km));
            if (totalDistance < 20.0d) {
                tvDistance1.setText(String.format("%.2f", new Object[]{Double.valueOf(totalDistance)}) + "/20");
                pb1.setMax(20);
                pb1.setProgress((int) totalDistance);
            } else {
                tvDistance1.setText("20/20");
                imDistance1.setImageResource(R.drawable.ok);
                pb1.setMax(1);
                pb1.setProgress(1);
            }
            if (totalDistance < 200.0d) {
                tvDistance2.setText(String.format("%.2f", new Object[]{Double.valueOf(totalDistance)}) + "/200");
                pb2.setMax(200);
                pb2.setProgress((int) totalDistance);
            } else {
                tvDistance2.setText("200/200");
                imDistance2.setImageResource(R.drawable.ok);
                pb2.setMax(1);
                pb2.setProgress(1);
            }
            if (totalDistance < 1000.0d) {
                tvDistance3.setText(String.format("%.2f", new Object[]{Double.valueOf(totalDistance)}) + "/1 000");
                pb3.setMax(1000);
                pb3.setProgress((int) totalDistance);
            } else {
                tvDistance3.setText("1 000/1 000");
                imDistance3.setImageResource(R.drawable.ok);
                pb3.setMax(1);
                pb3.setProgress(1);
            }
            if (totalDistance < 5000.0d) {
                tvDistance4.setText(String.format("%.2f", new Object[]{Double.valueOf(totalDistance)}) + "/5 000");
                pb4.setMax(5000);
                pb4.setProgress((int) totalDistance);
            } else {
                tvDistance4.setText("5 000/5 000");
                imDistance4.setImageResource(R.drawable.ok);
                pb4.setMax(1);
                pb4.setProgress(1);
            }
            if (totalDistance < 10000.0d) {
                tvDistance5.setText(String.format("%.2f", new Object[]{Double.valueOf(totalDistance)}) + "/10 000");
                pb16.setMax(10000);
                pb16.setProgress((int) totalDistance);
            } else {
                tvDistance5.setText("10 000/10 000");
                imDistance5.setImageResource(R.drawable.ok);
                pb16.setMax(1);
                pb16.setProgress(1);
            }
        } else {
            tvDistanceText1.setText(getResources().getString(R.string.Run_total_12_mi));
            tvDistanceText2.setText(getResources().getString(R.string.Run_total_125_mi));
            tvDistanceText3.setText(getResources().getString(R.string.Run_total_620_mi));
            tvDistanceText4.setText(getResources().getString(R.string.Run_total_3000_mi));
            tvDistanceText5.setText(getResources().getString(R.string.Run_total_10000_mi));
            totalDistance *= 0.621371d;
            if (totalDistance < 12.0d) {
                tvDistance1.setText(String.format("%.2f", new Object[]{Double.valueOf(totalDistance)}) + "/12");
                pb1.setMax(12);
                pb1.setProgress((int) totalDistance);
            } else {
                tvDistance1.setText("12/12");
                imDistance1.setImageResource(R.drawable.ok);
                pb1.setMax(1);
                pb1.setProgress(1);
            }
            if (totalDistance < 125.0d) {
                tvDistance2.setText(String.format("%.2f", new Object[]{Double.valueOf(totalDistance)}) + "/125");
                pb2.setMax(125);
                pb2.setProgress((int) totalDistance);
            } else {
                tvDistance2.setText("125/125");
                imDistance2.setImageResource(R.drawable.ok);
                pb2.setMax(1);
                pb2.setProgress(1);
            }
            if (totalDistance < 620.0d) {
                tvDistance3.setText(String.format("%.2f", new Object[]{Double.valueOf(totalDistance)}) + "/620");
                pb3.setMax(620);
                pb3.setProgress((int) totalDistance);
            } else {
                tvDistance3.setText("620/620");
                imDistance3.setImageResource(R.drawable.ok);
                pb3.setMax(1);
                pb3.setProgress(1);
            }
            if (totalDistance < 3000.0d) {
                tvDistance4.setText(String.format("%.2f", new Object[]{Double.valueOf(totalDistance)}) + "/3 000");
                pb4.setMax(3000);
                pb4.setProgress((int) totalDistance);
            } else {
                tvDistance4.setText("3 000/3 000");
                imDistance4.setImageResource(R.drawable.ok);
                pb4.setMax(1);
                pb4.setProgress(1);
            }
            if (totalDistance < 10000.0d) {
                tvDistance5.setText(String.format("%.2f", new Object[]{Double.valueOf(totalDistance)}) + "/10 000");
                pb16.setMax(10000);
                pb16.setProgress((int) totalDistance);
            } else {
                tvDistance5.setText("10 000/10 000");
                imDistance5.setImageResource(R.drawable.ok);
                pb16.setMax(1);
                pb16.setProgress(1);
            }
        }
        if (60.0d * totalDuration < 500.0d) {
            tvDuration1.setText(String.format("%.2f", new Object[]{Double.valueOf(60.0d * totalDuration)}) + "/500");
            pb5.setMax(500);
            pb5.setProgress((int) (60.0d * totalDuration));
        } else {
            tvDuration1.setText("500/500");
            imDuration1.setImageResource(R.drawable.ok);
            pb5.setMax(1);
            pb5.setProgress(1);
        }
        if (60.0d * totalDuration < 1000.0d) {
            tvDuration2.setText(String.format("%.2f", new Object[]{Double.valueOf(60.0d * totalDuration)}) + "/1 000");
            pb6.setMax(1000);
            pb6.setProgress((int) (60.0d * totalDuration));
        } else {
            tvDuration2.setText("1 000/1 000");
            imDuration2.setImageResource(R.drawable.ok);
            pb6.setMax(1);
            pb6.setProgress(1);
        }
        if (60.0d * totalDuration < 5000.0d) {
            tvDuration3.setText(String.format("%.2f", new Object[]{Double.valueOf(60.0d * totalDuration)}) + "/5 000");
            pb7.setMax(5000);
            pb7.setProgress((int) (60.0d * totalDuration));
        } else {
            tvDuration3.setText("5 000/5 000");
            imDuration3.setImageResource(R.drawable.ok);
            pb7.setMax(1);
            pb7.setProgress(1);
        }
        if (60.0d * totalDuration < 10000.0d) {
            tvDuration4.setText(String.format("%.2f", new Object[]{Double.valueOf(60.0d * totalDuration)}) + "/10 000");
            pb8.setMax(10000);
            pb8.setProgress((int) (60.0d * totalDuration));
        } else {
            tvDuration4.setText("10 000/10 000");
            imDuration4.setImageResource(R.drawable.ok);
            pb8.setMax(1);
            pb8.setProgress(1);
        }
        if (60.0d * totalDuration < 50000.0d) {
            tvDuration5.setText(String.format("%.2f", new Object[]{Double.valueOf(60.0d * totalDuration)}) + "/50 000");
            pb17.setMax(50000);
            pb17.setProgress((int) (60.0d * totalDuration));
        } else {
            tvDuration5.setText("50 000/50 000");
            imDuration5.setImageResource(R.drawable.ok);
            pb17.setMax(1);
            pb17.setProgress(1);
        }
        if (maxSpeed < 20.0d) {
            tvSpeed1.setText(String.format("%.2f", new Object[]{Double.valueOf(maxSpeed)}) + "/20");
            pb9.setMax(20);
            pb9.setProgress((int) maxSpeed);
        } else {
            tvSpeed1.setText("20/20");
            imSpeed1.setImageResource(R.drawable.ok);
            pb9.setMax(1);
            pb9.setProgress(1);
        }
        if (maxSpeed < 30.0d) {
            tvSpeed2.setText(String.format("%.2f", new Object[]{Double.valueOf(maxSpeed)}) + "/30");
            pb10.setMax(30);
            pb10.setProgress((int) maxSpeed);
        } else {
            tvSpeed2.setText("30/30");
            imSpeed2.setImageResource(R.drawable.ok);
            pb10.setMax(1);
            pb10.setProgress(1);
        }
        if (totalCalories < 3000.0d) {
            tvCalories1.setText(String.format("%.2f", new Object[]{Double.valueOf(totalCalories)}) + "/3 000");
            pb11.setMax(3000);
            pb11.setProgress((int) totalCalories);
        } else {
            tvCalories1.setText("3 000/3 000");
            imCalories1.setImageResource(R.drawable.ok);
            pb11.setMax(1);
            pb11.setProgress(1);
        }
        if (totalCalories < 5000.0d) {
            tvCalories2.setText(String.format("%.2f", new Object[]{Double.valueOf(totalCalories)}) + "/5 000");
            pb12.setMax(5000);
            pb12.setProgress((int) totalCalories);
        } else {
            tvCalories2.setText("5 000/5 000");
            imCalories2.setImageResource(R.drawable.ok);
            pb12.setMax(1);
            pb12.setProgress(1);
        }
        if (totalCalories < 10000.0d) {
            tvCalories3.setText(String.format("%.2f", new Object[]{Double.valueOf(totalCalories)}) + "/10 000");
            pb13.setMax(10000);
            pb13.setProgress((int) totalCalories);
        } else {
            tvCalories3.setText("10 000/10 000");
            imCalories3.setImageResource(R.drawable.ok);
            pb13.setMax(1);
            pb13.setProgress(1);
        }
        if (totalCalories < 20000.0d) {
            tvCalories4.setText(String.format("%.2f", new Object[]{Double.valueOf(totalCalories)}) + "/20 000");
            pb14.setMax(20000);
            pb14.setProgress((int) totalCalories);
        } else {
            tvCalories4.setText("20 000/20 000");
            imCalories4.setImageResource(R.drawable.ok);
            pb14.setMax(1);
            pb14.setProgress(1);
        }
        if (totalCalories < 50000.0d) {
            tvCalories5.setText(String.format("%.2f", new Object[]{Double.valueOf(totalCalories)}) + "/50 000");
            pb15.setMax(50000);
            pb15.setProgress((int) totalCalories);
        } else {
            tvCalories5.setText("50 000/50 000");
            imCalories5.setImageResource(R.drawable.ok);
            pb15.setMax(1);
            pb15.setProgress(1);
        }
        return rootView;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onDetach() {
        super.onDetach();
    }

    public void onResume() {
        super.onResume();
    }
}
