package edu.neu.madcourse.theloop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainLoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText usernameLogin, passwordLogin;
    Button loginNow;

    // Firebase
    FirebaseAuth mAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        usernameLogin = findViewById(R.id.username_login);
        passwordLogin = findViewById(R.id.password_login);
        loginNow = findViewById(R.id.button_login_now);
        loginNow.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.button_login_now:
                userLogin();
                
        }

    }

    private void userLogin() {
        String email = usernameLogin.getText().toString().trim();
        String password = passwordLogin.getText().toString();

        if(email.isEmpty()){
            usernameLogin.setError("Email cannot be empty.");
            usernameLogin.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            usernameLogin.setError("Please provide a valid Email Address.");
            usernameLogin.requestFocus();
            return;
        }

        if(password.isEmpty()){
            passwordLogin.setError("Password cannot be empty.");
            passwordLogin.requestFocus();
            return;
        }
        if(password.length() < 6){
            passwordLogin.setError("Password length should be 6 characters atleast.");
            passwordLogin.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Intent intent_login = new Intent(MainLoginActivity.this, ProfileActivity.class);
                    startActivity(intent_login);

                } else {
                    Toast.makeText(MainLoginActivity.this, "Faied to login, please check your credentials!", Toast.LENGTH_LONG);
                }

            }
        });
    }
}