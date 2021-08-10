package edu.neu.madcourse.theloop;

import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Adapter;

public class GetStartedScrollActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started_scroll);

        // initializing view pager
        ViewPager viewPager = findViewById(R.id.viewPager);

        // setting the Adapter
        viewPager.setAdapter(new CustomPagerAdapter(this));
    }
}