package com.push.sweateliteathletics.softrasol;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class PushFragmentWeb extends Fragment {

    private WebView webView;
    private ProgressDialog progress;
    FirebaseAuth mAuth;


    public PushFragmentWeb() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_push, container, false);


            webView = v.findViewById(R.id.push_web);
        webView.getSettings().setJavaScriptEnabled(true);

        progress = new ProgressDialog(getActivity());
        webView.loadUrl("https://portal.sweateliteathletics.com/client/push/");

        progress.setTitle("Please Wait");
        progress.setMessage("Please wait web page is loading...");
        progress.show();


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


