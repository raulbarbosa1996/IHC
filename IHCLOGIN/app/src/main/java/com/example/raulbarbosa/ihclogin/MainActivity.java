package com.example.raulbarbosa.ihclogin;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.estimote.mustard.rx_goodness.rx_requirements_wizard.Requirement;
import com.estimote.mustard.rx_goodness.rx_requirements_wizard.RequirementsWizardFactory;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;


public class MainActivity extends AppCompatActivity {





    LoginButton logginButton;
    CallbackManager callbackManager;
    private static final String EMAIL = "email";
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    TextView textView;
    Typeface font;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main);
        final MyApplication application = (MyApplication) getApplication();

        RequirementsWizardFactory
                .createEstimoteRequirementsWizard()
                .fulfillRequirements(this,
                        new Function0<Unit>() {
                            @Override
                            public Unit invoke() {
                                Log.d("app", "requirements fulfilled");
                                application.enableBeaconNotifications();
                                return null;
                            }
                        },
                        new Function1<List<? extends Requirement>, Unit>() {
                            @Override
                            public Unit invoke(List<? extends Requirement> requirements) {
                                Log.e("app", "requirements missing: " + requirements);
                                return null;
                            }
                        },
                        new Function1<Throwable, Unit>() {
                            @Override
                            public Unit invoke(Throwable throwable) {
                                Log.e("app", "requirements error: " + throwable);
                                return null;
                            }
                        });
        logginButton=(LoginButton)findViewById(R.id.login_button);
        //textView=(TextView)findViewById(R.id.textView);
        callbackManager=CallbackManager.Factory.create();
        logginButton.setReadPermissions(Arrays.asList(EMAIL));
        mAuth = FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();

        if(user==null) {
            logginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    //handleFacebookAccessToken(loginResult.getAccessToken());
                    Toast.makeText(MainActivity.this, "Sucesso",
                            Toast.LENGTH_LONG).show();
                    handleFacebookAccessToken(loginResult.getAccessToken());
                }

                @Override
                public void onCancel() {
                    Toast.makeText(MainActivity.this, "Sucesso de saida",
                            Toast.LENGTH_LONG).show();
                    // ...
                }

                @Override
                public void onError(FacebookException error) {
                    // ...
                }
            });
        }
        else {
            Intent iActivity= new Intent(MainActivity.this, Home.class);
            //iActivity.putExtra("string1","If you were able to do this, you are FABULOUS!");
            startActivity(iActivity);
        }





    }



    private void handleFacebookAccessToken(AccessToken token) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            Intent iActivity= new Intent(MainActivity.this, Home.class);
                            //iActivity.putExtra("string1","If you were able to do this, you are FABULOUS!");
                            startActivity(iActivity);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void Register(View v){
        Intent iActivity= new Intent(this, Register.class);

        //iActivity.putExtra("string1","If you were able to do this, you are FABULOUS!");
        startActivity(iActivity);
    }

    public void LogIn(View v){
        Intent iActivity= new Intent(this, LogIn.class);

        //iActivity.putExtra("string1","If you were able to do this, you are FABULOUS!");
        startActivity(iActivity);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }






}
