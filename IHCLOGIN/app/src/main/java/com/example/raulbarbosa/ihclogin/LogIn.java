package com.example.raulbarbosa.ihclogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends Activity {
    private FirebaseAuth mAuth;
    private EditText email,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mAuth = FirebaseAuth.getInstance();

    }

    public void Login(View v){
       email=(EditText)findViewById(R.id.editText);
        password=(EditText)findViewById(R.id.editText2);
        String email1=email.getText().toString();
        String password1=password.getText().toString();
        mAuth.signInWithEmailAndPassword(email1, password1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(LogIn.this, "Log In com Sucesso",
                                    Toast.LENGTH_SHORT).show();
                            Intent iActivity= new Intent(LogIn.this, Home.class);
                            //iActivity.putExtra("string1","If you were able to do this, you are FABULOUS!");
                            startActivity(iActivity);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LogIn.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });

    }


}

