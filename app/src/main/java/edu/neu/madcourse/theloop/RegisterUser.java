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
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterUser extends AppCompatActivity implements View.OnClickListener {


    private FirebaseAuth mAuth;
    private EditText fullName, age, userName, password;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);


        mAuth = FirebaseAuth.getInstance();
        fullName = findViewById(R.id.name_register);
        age = findViewById(R.id.age_register);
        userName = findViewById(R.id.username_register);
        password = findViewById(R.id.password_register);

        registerBtn = findViewById(R.id.button_register);
        registerBtn.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button_register:
                registerUser();
                break;
        }

    }

    private void registerUser() {
        String Email = userName.getText().toString();
        String Password = password.getText().toString();
        String Fullname = fullName.getText().toString();
        String Age = age.getText().toString();

        if(Fullname.isEmpty()){
            fullName.setError("Name cannot be empty.");
            fullName.requestFocus();
            return;
        }
        if(Age.isEmpty()){
            age.setError("Age cannot be empty.");
            age.requestFocus();
            return;
        }
        if(Email.isEmpty()){
            userName.setError("Email cannot be empty.");
            userName.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            userName.setError("Please provide a valid Email Address.");
            userName.requestFocus();
            return;
        }
        if(Password.isEmpty()){
            password.setError("Password cannot be empty.");
            password.requestFocus();
            return;
        }
        if(Password.length() < 6){
            password.setError("Password length should be 6 characters atleast.");
            password.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull  Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(Fullname, Age, Email);
                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        Toast.makeText(RegisterUser.this, "User has been successfully registered", Toast.LENGTH_LONG);

                                        // redirect user to log in
                                        Intent intent_login = new Intent(RegisterUser.this, MainLoginActivity.class);
                                        startActivity(intent_login);
                                    } else{
                                        Toast.makeText(RegisterUser.this, "Faied to register, try again!", Toast.LENGTH_LONG);
                                    }

                                }
                            });

                        } else {
                            Toast.makeText(RegisterUser.this, "Faied to register, try again!", Toast.LENGTH_LONG);
                        }

                    }
                });
    }
}