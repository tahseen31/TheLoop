package edu.neu.madcourse.theloop;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityOptionsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.DecoDrawEffect;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.events.DecoEvent;

public class HomeScreen extends AppCompatActivity implements
         NavigationView.OnNavigationItemSelectedListener {

    public static float evsteps;
    public static int cont = 0;
    /**
     * Maximum value for each data series in the {@link DecoView}. This can be different for each
     * data series, in this example we are applying the same all data series
     */
    public static float mSeriesMax = 0f;
    boolean activityRunning;
    /**
     * DecoView animated arc based chart
     */
    private DecoView mDecoView;
    /**
     * Data series index used for controlling animation of {@link DecoView}. These are set when
     * the data series is created then used in {@link #createEvents} to specify what series to
     * apply a given event to
     */
    private int mBackIndex;
    private int mSeries1Index;
    private int mSeries2Index;
    private int mSeries3Index;
    // Sensor data
    private TextView textView;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private TextView textActivity1;
    private int numSteps;
    Button BtnStart,BtnStop;
    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_screen);
        textActivity1 = findViewById(R.id.textActivity1);
        textActivity1.setText( "steps: 0" );
        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(new StepListener() {
            @Override
            public void step(long timeNs) {
                numSteps++;
                textActivity1.setText( "steps: " + numSteps);
            }
        });
        BtnStart =  findViewById(R.id.start);
        BtnStop =  findViewById(R.id.stop);
        BtnStart.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View arg0) {



                numSteps = 0;
                sensorManager.registerListener(new SensorEventListener() {
                    @Override
                    public void onSensorChanged(SensorEvent sensorEvent) {
                        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                            simpleStepDetector.updateAccel(
                                    sensorEvent.timestamp, sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
                        }
                    }



                    @Override
                    public void onAccuracyChanged(Sensor sensor, int i) {



                    }
                }, accel, SensorManager.SENSOR_DELAY_FASTEST);



            }
        });




        BtnStop.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View arg0) {



                sensorManager.unregisterListener(new SensorEventListener() {
                    @Override
                    public void onSensorChanged(SensorEvent sensorEvent) {
                        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                            textActivity1.setText( "steps: 0" );
                            simpleStepDetector.updateAccel(
                                    sensorEvent.timestamp, sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
                            textActivity1.setText( "steps: 0" );
                            numSteps = 0;
                        }
                    }



                    @Override
                    public void onAccuracyChanged(Sensor sensor, int i) {



                    }
                });



            }
        });
        Toolbar toolbar2 = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);


        View mHeaderView = navigationView.getHeaderView(0);

        TextView nameId = mHeaderView.findViewById(R.id.txt1);
        nameId.setText(MainLoginActivity.USER_NAME);
        Toast.makeText(HomeScreen.this, MainLoginActivity.USER_NAME, Toast.LENGTH_LONG).show();

        PackageManager pm = getPackageManager();
        if (pm.hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_COUNTER)) {
            Toast.makeText(HomeScreen.this, "Step counter feature present", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(HomeScreen.this, "Step counter feature not present", Toast.LENGTH_LONG).show();
        }
        TextView emailId = mHeaderView.findViewById(R.id.txt2);
        emailId.setText(MainLoginActivity.USER_EMAIL);
        drawerLayout = findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle =
                new ActionBarDrawerToggle(this, drawerLayout,toolbar2, R.string.open_drawer, R.string.close_drawer) {
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

        mSeriesMax = SetGoalActivity.mSeries;
        Log.d("SetGoal mseries", String.valueOf(SetGoalActivity.mSeries));
        if (mSeriesMax == 0) {
            mSeriesMax = MainLoginActivity.mSeries1;
        }
        final String cap1;
        final float[] m = new float[1];
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Go to data image button
        final ImageView dn = findViewById(R.id.datanext);
        // Go to Chart Data page
        dn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(HomeScreen.this,Activity_ViewPager.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(HomeScreen.this, v, "testAnimation");
                    HomeScreen.this.startActivity(intent1, options.toBundle());
                } else {
                    startActivity(intent1);
                }
            }
        });
        mDecoView = findViewById(R.id.dynamicArcView);


        Log.d("mSeries out", (String.valueOf(mSeriesMax)));
        if (mSeriesMax > 0) {
            Log.d("mSeries out in", (String.valueOf(mSeriesMax)));
            // Create required data series on the DecoView
            createBackSeries();
            createDataSeries1();

            // Setup events to be fired on a schedule
            createEvents();
        }

    }

    // Step Counter






    private void createBackSeries() {
        SeriesItem seriesItem = new SeriesItem.Builder(Color.parseColor("#FFE2E2E2"))
                .setRange(0, mSeriesMax, 0)
                .setInitialVisibility(true)
                .build();

        mBackIndex = mDecoView.addSeries(seriesItem);
    }

    private void createDataSeries1() {
        final SeriesItem seriesItem = new SeriesItem.Builder(Color.parseColor("#FFFF8800"))
                .setRange(0, mSeriesMax, 0)
                .setInitialVisibility(false)
                .build();

        Log.d("mSeries Data1", (String.valueOf(mSeriesMax)));

        final TextView textPercentage = findViewById(R.id.textPercentage);
        seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                float percentFilled = ((currentPosition - seriesItem.getMinValue()) / (seriesItem.getMaxValue() - seriesItem.getMinValue()));
                textPercentage.setText(String.format("%.0f%%", percentFilled * 100f));
            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });


        final TextView textToGo = findViewById(R.id.textRemaining);
        seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                textToGo.setText(String.format("%d Steps to goal", (int) (seriesItem.getMaxValue() - currentPosition)));

            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });

       // final TextView textActivity1 = findViewById(R.id.textActivity1);
        seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
              //  Toast.makeText(HomeScreen.this, String.format("%.0f Steps", currentPosition), Toast.LENGTH_LONG).show();
               // textActivity1.setText(String.format("%.0f Steps", currentPosition));
            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });

        mSeries1Index = mDecoView.addSeries(seriesItem);
    }

    private void createEvents() {
        cont++;
        mDecoView.executeReset();

        if (cont == 1) {
            resetText();
            mDecoView.addEvent(new DecoEvent.Builder(DecoDrawEffect.EffectType.EFFECT_SPIRAL_EXPLODE)
                    .setIndex(mSeries1Index)
                    .setDelay(0)
                    .setDuration(1000)
                    .setDisplayText("")
                    .setListener(new DecoEvent.ExecuteEventListener() {
                        @Override
                        public void onEventStart(DecoEvent decoEvent) {

                        }

                        @Override
                        public void onEventEnd(DecoEvent decoEvent) {
                            createEvents();
                        }
                    })
                    .build());
        }
        mDecoView.addEvent(new DecoEvent.Builder(mSeriesMax)
                .setIndex(mBackIndex)
                .setDuration(3000)
                .setDelay(100)
                .build());

        mDecoView.addEvent(new DecoEvent.Builder(evsteps)
                .setIndex(mSeries1Index)
                .setDelay(3250)
                .build());

        mDecoView.addEvent(new DecoEvent.Builder(DecoDrawEffect.EffectType.EFFECT_SPIRAL_EXPLODE)
                .setIndex(mSeries1Index)
                .setDelay(20000)
                .setDuration(3000)
                .setDisplayText("")
                .setListener(new DecoEvent.ExecuteEventListener() {
                    @Override
                    public void onEventStart(DecoEvent decoEvent) {

                    }

                    @Override
                    public void onEventEnd(DecoEvent decoEvent) {
                        createEvents();
                    }
                })
                .build());

    }

    private void resetText() {
        ((TextView) findViewById(R.id.textPercentage)).setText("");
        ((TextView) findViewById(R.id.textRemaining)).setText("");
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            //Change Target
            case R.id.item1:
                Intent intent = new Intent(this, SetGoalActivity.class);
                startActivity(intent);
                break;
            //Chat with Friends
            case R.id.item2:
                intent = new Intent(this, chatActivity.class);
                startActivity(intent);
                break;
            //Settings
            case R.id.item3:
                intent = new Intent(this, AccountActivity.class);
                startActivity(intent);
                break;
            //Logout
            case R.id.item4:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomeScreen.this, MainLoginActivity.class));
                finish();
                return true;

            default:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
