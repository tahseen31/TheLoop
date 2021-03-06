package edu.neu.madcourse.theloop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

import edu.neu.madcourse.theloop.Fragments.ChatsFragment;
import edu.neu.madcourse.theloop.Fragments.FriendsFragment;

public class chatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_item);

        // Tab Layout instantiation
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager2 = findViewById(R.id.viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPagerAdapter.addFragment(new ChatsFragment(), "Chats");
        viewPagerAdapter.addFragment(new FriendsFragment(), "Friends");

        viewPager2.setAdapter(viewPagerAdapter);
       //  tabLayout.setupWithViewPager((ViewPager) viewPager);
        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setText(fragment[position + 1])
        ).attach();

    }


    // class view pager adapter
    class ViewPagerAdapter extends FragmentStateAdapter {
        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;


        public ViewPagerAdapter(FragmentActivity fm) {
            super(fm);

            this.fragments = new ArrayList<>();
            this.titles = new ArrayList<>();
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments.get(position);
        }

        @Override
        public int getItemCount() {
            return fragments.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
            titles.add(title);

        }

        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }


    }

    private String[] fragment = { "NULL", "CHATS", "FRIENDS"};

    }
