package com.push.sweateliteathletics.softrasol;


import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class ElevateFragment extends Fragment {

    private WebView webView;
    private ProgressDialog progress;


    public ElevateFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_elevate, container, false);
        getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.black));//


        webView = v.findViewById(R.id.elevate_web);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl("https://sweateliteathletics.com/fit-mind/");

        progress = ProgressDialog.show(getActivity(), "Please Wait",
                "Loading...", true);

        webView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                if (progress != null)
                    progress.dismiss();
            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }



}


