package edu.neu.madcourse.theloop;

import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;

public class GetStartedScrollActivity extends AppCompatActivity  {
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started_scroll);

        // initializing view pager
        ViewPager viewPager = findViewById(R.id.viewPager);

      //  registerBtn = findViewById(R.id.register_button);
      //  registerBtn.setOnClickListener(this);

        // setting the Adapter
        viewPager.setAdapter(new CustomPagerAdapter(this));
    }

    /*
    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.register_button:
                startActivity(new Intent(this, RegisterUser.class));
                break;
        }
    } */

}