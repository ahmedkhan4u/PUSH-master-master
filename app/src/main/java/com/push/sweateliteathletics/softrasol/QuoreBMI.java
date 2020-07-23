package com.push.sweateliteathletics.softrasol;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class QuoreBMI extends Fragment {
    private Double BMI;
    private ArrayAdapter<CharSequence> adapterT;
    private ArrayAdapter<CharSequence> adapterV;
    private FontTextView bmi;
    private Button btnCalculate;
    private Context context;
    private Double height;
    private EditText heightEt;
    private String heightStr;
    private String mHeight;
    private String mWeight;
    private Spinner spinHeight;
    private Spinner spinWeight;
    private Double weight;
    private EditText weightEt;
    private String weightStr;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_bmi, container, false);
        this.context = getActivity();
        this.spinHeight = (Spinner) rootView.findViewById(R.id.spHeight);
        this.spinWeight = (Spinner) rootView.findViewById(R.id.spWeight);
        this.heightEt = (EditText) rootView.findViewById(R.id.etHeightBMI);
        this.weightEt = (EditText) rootView.findViewById(R.id.etWeightBMI);
        this.btnCalculate = (Button) rootView.findViewById(R.id.btnCalculate);
        this.bmi = (FontTextView) rootView.findViewById(R.id.tvBMI);
        this.adapterT = ArrayAdapter.createFromResource(this.context, R.array.Weight, 17367048);
        this.adapterT.setDropDownViewResource(17367049);
        this.spinWeight.setAdapter(this.adapterT);
        this.adapterV = ArrayAdapter.createFromResource(this.context, R.array.Height, 17367048);
        this.adapterV.setDropDownViewResource(17367049);
        this.spinHeight.setAdapter(this.adapterV);
        this.btnCalculate.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreBMI.this.heightStr = QuoreBMI.this.heightEt.getText().toString();
                QuoreBMI.this.weightStr = QuoreBMI.this.weightEt.getText().toString();
                QuoreBMI.this.mHeight = QuoreBMI.this.spinHeight.getSelectedItem().toString();
                QuoreBMI.this.mWeight = QuoreBMI.this.spinWeight.getSelectedItem().toString();
                if (QuoreBMI.this.heightStr.trim().isEmpty()) {
                    Toast.makeText(QuoreBMI.this.context, QuoreBMI.this.getResources().getString(R.string.not_entered_height), 0).show();
                } else if (QuoreBMI.this.weightStr.trim().isEmpty()) {
                    Toast.makeText(QuoreBMI.this.context, QuoreBMI.this.getResources().getString(R.string.not_entered_weight), 0).show();
                } else {
                    try {
                        QuoreBMI.this.height = Double.valueOf(Double.parseDouble(QuoreBMI.this.heightStr));
                        QuoreBMI.this.weight = Double.valueOf(Double.parseDouble(QuoreBMI.this.weightStr));
                        if (QuoreBMI.this.mHeight.equalsIgnoreCase("inch")) {
                            QuoreBMI.this.height = Double.valueOf(QuoreBMI.this.height.doubleValue() * 2.54d);
                        }
                        if (QuoreBMI.this.mWeight.equalsIgnoreCase("lb")) {
                            QuoreBMI.this.weight = Double.valueOf(QuoreBMI.this.weight.doubleValue() * 0.453592d);
                        }
                        QuoreBMI.this.BMI = Double.valueOf(QuoreBMI.this.weight.doubleValue() / Math.pow(QuoreBMI.this.height.doubleValue() / 100.0d, 2.0d));
                    } catch (Exception e) {
                        Toast.makeText(QuoreBMI.this.context, QuoreBMI.this.getResources().getString(R.string.Error_in_calc), 0).show();
                    }
                    QuoreBMI.this.bmi.setText("BMI = - -");
                    if (QuoreBMI.this.BMI.doubleValue() > 0.0d) {
                        QuoreBMI.this.bmi.setText("BMI = " + String.format("%.2f", new Object[]{QuoreBMI.this.BMI}));
                    }
                }
            }
        });
        return rootView;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onDetach() {
        super.onDetach();
    }
}
