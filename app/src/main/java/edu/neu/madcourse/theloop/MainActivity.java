package edu.neu.madcourse.theloop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button getStartedButton;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getStartedButton = findViewById(R.id.button_get_started);
        getStartedButton.setOnClickListener(this);
        loginButton = findViewById(R.id.button_login);
        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_get_started:
                /*
                Intent intent_get_started = new Intent(MainActivity.this, GetStartedScrollActivity.class);
                startActivity(intent_get_started);
                break; */
                // Testing - Tahseen
                startActivity(new Intent(this, RegisterUser.class));
                break;
            case R.id.button_login:
                Intent intent_login = new Intent(MainActivity.this, MainLoginActivity.class);
                startActivity(intent_login);
                break;

        }
    }
}