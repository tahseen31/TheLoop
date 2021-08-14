package edu.neu.madcourse.theloop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterUser extends AppCompatActivity implements View.OnClickListener {


    private FirebaseAuth mAuth;
    private EditText fullName, age, userName, password, phone, height, weight ;
    private RadioGroup genderGroup;
    private RadioButton male, female, unclassified;
    String strGender;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_user);


        mAuth = FirebaseAuth.getInstance();
        fullName = findViewById(R.id.name_register);
        age = findViewById(R.id.age_register);
        userName = findViewById(R.id.username_register);
        password = findViewById(R.id.password_register);
        phone = findViewById(R.id.phoneInput);
        age=findViewById(R.id.age_register);
        height=findViewById(R.id.heightInput);
        weight=findViewById(R.id.weightInput);

        genderGroup = findViewById(R.id.genderGroup);
        male = findViewById(R.id.maleRadio);
        female = findViewById(R.id.femaleRadio);

        genderGroup.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == R.id.maleRadio) {
                strGender = "Male";

            } else  if (checkedId == R.id.femaleRadio) {
                strGender = "Female";
            }

        });

        registerBtn = findViewById(R.id.nextbutton);
        registerBtn.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.nextbutton:
                registerUser();
                break;
        }

    }

    private void registerUser() {
        String Email = userName.getText().toString();
        String Password = password.getText().toString();
        String Fullname = fullName.getText().toString();
        String Age = age.getText().toString();
        String Phone = phone.getText().toString();
        String Height = height.getText().toString();
        String Weight = weight.getText().toString();
        String Gender = strGender;


        if(Fullname.isEmpty()){
            fullName.setError("Name cannot be empty.");
            fullName.requestFocus();
            return;
        }
        if(Phone.isEmpty()){
            phone.setError("Name cannot be empty.");
            phone.requestFocus();
            return;
        }
        if(Weight.isEmpty()){
            weight.setError("Name cannot be empty.");
            weight.requestFocus();
            return;
        }
        if(Height.isEmpty()){
            height.setError("Name cannot be empty.");
            height.requestFocus();
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
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            String userid = firebaseUser.getUid();
                            User user = new User(Fullname, Email, userid,Phone, Gender, Height, Age, 0, Weight );
                            Steps step = new Steps(0);
                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        Toast.makeText(RegisterUser.this, "User has been successfully registered", Toast.LENGTH_LONG);

                                        // redirect user to log in
                                        Intent intent_login = new Intent(RegisterUser.this, SetGoalActivity.class);
                                        startActivity(intent_login);
                                    } else{
                                        Toast.makeText(RegisterUser.this, "Faied to register, try again!", Toast.LENGTH_LONG);
                                    }

                                }
                            });

                            FirebaseDatabase.getInstance().getReference("steps")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(step);

                        } else {
                            Toast.makeText(RegisterUser.this, "Faied to register, try again!", Toast.LENGTH_LONG);
                        }


                    }
                });
    }
}