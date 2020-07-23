package com.push.sweateliteathletics.softrasol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;



import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class SignIn extends AppCompatActivity {


    EditText signInEmail;
    EditText signInPassword;

    Button btnSignIn;

    ProgressBar signInProgress;

    TextView sendToSignUp;

    ImageButton btnGoogleSign;
    ImageButton btnFacebookSignIn;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private AccessTokenTracker accessTokenTracker;

    GoogleSignInClient mGoogleSignInClient;

    private CallbackManager mCallbackManager;
    private LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            sendTOMain();
            return;
        }


        //Facebook Sign In
        FacebookSdk.sdkInitialize(getApplicationContext());
        //AppEventsLogger.activateApp(this);
        loginButton = findViewById(R.id.login_button);
        btnFacebookSignIn = findViewById(R.id.facebook_login);
        loginButton.setReadPermissions("email", "public_profile", "user_friends");
        mCallbackManager = CallbackManager.Factory.create();

        // Callback registration

        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.d("dxdiag", "On Success" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                // App code
                Log.d("dxdiag", "On Cancel");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.d("dxdiag", "On Error" + exception);
            }
        });
/*
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser currentUser = mAuth.getCurrentUser();
                if (currentUser != null)
                {
                    sendTOMain();
                }
                else {
                    Toast.makeText(SignIn.this, "Current User Null", Toast.LENGTH_SHORT).show();
                }
            }
        };

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if(currentAccessToken == null)
                {
                    mAuth.signOut();
                }
            }
        };*/


        //Google Sign In
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        signInEmail = findViewById(R.id.signin_email);
        signInPassword = findViewById(R.id.signin_password);
        signInProgress = findViewById(R.id.signin_progressbar);
        sendToSignUp = findViewById(R.id.dont_have_account);
        btnSignIn = findViewById(R.id.btn_signin);
        btnGoogleSign = findViewById(R.id.google_signin);





        btnGoogleSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });




        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = signInEmail.getText().toString().trim();
                String password = signInPassword.getText().toString().trim();

                signInProgress.setVisibility(View.VISIBLE);

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {

                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("dxdiag", "signInWithEmail:success");

                                            sendTOMain();

                                        signInProgress.setVisibility(View.INVISIBLE);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("dxdiag", "signInWithEmail:failure", task.getException());
                                        signInProgress.setVisibility(View.INVISIBLE);
                                        Toast.makeText(SignIn.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        // ...
                                    }

                                    // ...
                                }
                            });

                }

            }
        });

        sendToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SignIn.this, SignUp.class);
                startActivity(intent);
                finish();

            }
        });


    }



    private void sendTOMain()
    {
        Intent i = new Intent(SignIn.this,
                QuoreMain.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();

    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 007);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Facebook
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 007) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("dxdiag", "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("Dxdiag", "Google sign in failed", e);
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("dxdiag", "signInWithCredential:success");

                                sendTOMain();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("dxdiag", "signInWithCredential:failure", task.getException());
                            //Snackbar.make(mBinding.mainLayout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            Toast.makeText(SignIn.this, "Google Login Error", Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("dxdiag", "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("dxdiag", "signInWithCredential:success");
                                sendTOMain();

                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("dxdiag", "signInWithCredential:failure", task.getException());
                            Toast.makeText(SignIn.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void onClick(View view) {

        if (view == btnFacebookSignIn) {
            loginButton.performClick();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), SignUp.class));
        finish();
    }
}
