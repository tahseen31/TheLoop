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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainLoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText usernameLogin, passwordLogin;
    Button loginNow;
    public static float mSeries1 = 0f;
    public static String USER_ID = "";
    public static String USER_EMAIL = "";
    public static String USER_NAME = "";

    // Firebase
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference mDatabase =  FirebaseDatabase.getInstance().getReference();


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
    private DatabaseReference getUsersRef(String ref) {
        FirebaseUser user = mAuth.getCurrentUser();
        String userId = user.getUid();
        return mDatabase.child("users").child(userId).child(ref);
    }
    private void getUserInfo() {
        getUsersRef("fullName").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                USER_NAME = (String.valueOf(dataSnapshot.getValue()));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        getUsersRef("stepGoal").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mSeries1 = Float.parseFloat(String.valueOf(dataSnapshot.getValue()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        getUsersRef("email").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                USER_EMAIL = (String.valueOf(dataSnapshot.getValue()));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
                    // m series
                    getUserInfo();
                    Intent intent_login = new Intent(MainLoginActivity.this, HomeScreen.class);
                    startActivity(intent_login);

                } else {
                    Toast.makeText(MainLoginActivity.this, "Faied to login, please check your credentials!", Toast.LENGTH_LONG);
                }

            }
        });
    }
}