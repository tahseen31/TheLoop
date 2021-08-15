package edu.neu.madcourse.theloop;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;

import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AccountActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, AccountFragment.OnChangeGoalListener {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);

        Toolbar toolbar3 = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar3);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new AccountFragment())
                .commit();

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        View mHeaderView = navigationView.getHeaderView(0);

        TextView nameId = mHeaderView.findViewById(R.id.txt1);
        nameId.setText(MainLoginActivity.USER_NAME);

        TextView emailId = mHeaderView.findViewById(R.id.txt2);
        emailId.setText(MainLoginActivity.USER_EMAIL);


        drawerLayout = findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle =
                new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer) {
                    @Override
                    public void onDrawerClosed(View drawerView) {
                        super.onDrawerClosed(drawerView);
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        super.onDrawerOpened(drawerView);
                    }
                };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.item1:
                Intent intent = new Intent(this, SetGoalActivity.class);
                startActivity(intent);
                break;
            case R.id.item2:
                intent = new Intent(this, chatActivity.class);
                startActivity(intent);
                break;
            case R.id.item3:
                intent = new Intent(this, AccountActivity.class);
                startActivity(intent);
                break;
            case R.id.item4:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(AccountActivity.this, MainLoginActivity.class));
                finish();
                return true;

            default:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onChangeGoalClicked() {
        Intent myIntent = new Intent(this, SetGoalActivity.class);
        startActivity(myIntent);
    }

}

