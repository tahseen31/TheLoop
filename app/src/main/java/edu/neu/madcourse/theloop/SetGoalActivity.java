package edu.neu.madcourse.theloop;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SetGoalActivity extends AppCompatActivity {
    public static float mSeries = 0f;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goal);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        final String userId = user.getUid();
        final EditText stepGoal = findViewById(R.id.et1);
        Button setgoal = findViewById(R.id.button);
        setgoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stepGoal.getText().toString().length() == 0) {
                    stepGoal.setError("Set Steps Goal");
                    return;
                }
                mDatabase.child("users").child(userId).child("stepGoal").setValue(stepGoal.getText().toString());
                mDatabase.child("users").child(userId).child("stepGoal").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        mSeries = Float.parseFloat(String.valueOf(dataSnapshot.getValue()));
                        Log.d("mSeries", (String.valueOf(mSeries)));
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
                Intent myIntent = new Intent(SetGoalActivity.this, MainLoginActivity.class);
                startActivity(myIntent);
            }
        });
    }
}


