package edu.neu.madcourse.theloop;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;

public class Activity_ViewPager extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    private ActionBar myActionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_viewpager);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myActionBar = getSupportActionBar();
        myActionBar.setDisplayHomeAsUpEnabled(true);

        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("RUN - Running Video"));
        tabLayout.addTab(tabLayout.newTab().setText("Inspirational Running Video"));
        tabLayout.addTab(tabLayout.newTab().setText("Nike:Last"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        customizeViewPager();
        //Using 3rd party library here, toxicbakery. List of transformers in
        //https://github.com/ToxicBakery/ViewPagerTransforms/tree/master/library/src/main/java/com/ToxicBakery/viewpager/transforms
        viewPager.setPageTransformer(true, new CubeOutTransformer());


        TabLayout.Tab tab = tabLayout.getTabAt(1);
        tab.select();

        viewPager.setCurrentItem(1);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }

        });

    }

    private void customizeViewPager() {
        viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            public void transformPage(View view, float position) {
                final float np = Math.abs(Math.abs(position) - 1);
                view.setScaleX(np / 2 + 0.5f);
                view.setScaleY(np / 2 + 0.5f);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }


}
