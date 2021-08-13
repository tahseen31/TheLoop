package edu.neu.madcourse.theloop;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class InitialAppInfoActivity extends AppIntro{
    @Override
    public void init(Bundle savedInstanceState) {
        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
        addSlide(AppIntroFragment.newInstance("Make Fitness a group activity !", "", R.drawable.appintro1, getResources().getColor(R.color.design_default_color_primary)));
        addSlide(AppIntroFragment.newInstance("Set daily goals!", "Set daily goals and achieve them with your friends.", R.drawable.appintro3, getResources().getColor(R.color.design_default_color_primary)));
        addSlide(AppIntroFragment.newInstance("Motivate each other to fitness", "Chat with friends to stay connected in your fitness journey.", R.drawable.appintro4, getResources().getColor(R.color.design_default_color_primary)));
        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(Color.parseColor("#F44336"));
        setSeparatorColor(Color.parseColor("#2196F3"));
        // Hide Skip/Done button.
        showSkipButton(true);
        setProgressButtonEnabled(true);
        // Turn vibration on and set intensity.
        setVibrate(true);
        setVibrateIntensity(30);
    }
    @Override
    public void onSkipPressed() {
        // Do something when users tap on Skip button.
        Intent i = new Intent(InitialAppInfoActivity.this, EnterPersonalDetailsActivity.class);
        startActivity(i);
    }
    @Override
    public void onDonePressed() {
        // Do something when users tap on Done button.
        Intent i = new Intent(InitialAppInfoActivity.this, EnterPersonalDetailsActivity.class);
        startActivity(i);
        Toast.makeText(getApplicationContext(), "Finished", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onSlideChanged() {
        // Do something when the slide changes.
    }
    @Override
    public void onNextPressed() {
        // Do something when users tap on Next button.
        Toast.makeText(getApplicationContext(), "Cannot Skip", Toast.LENGTH_SHORT).show();
    }
}