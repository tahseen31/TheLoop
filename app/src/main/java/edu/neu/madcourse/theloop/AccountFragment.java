package edu.neu.madcourse.theloop;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class AccountFragment extends Fragment {

    public AccountFragment() {
        // Required empty public constructor
    }

    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.account_fragment, container, false);

        ImageView changeGoalImg = rootView.findViewById(R.id.settingsIcon);
        TextView changeGoalsText = rootView.findViewById(R.id.settingsText);

        final OnChangeGoalListener mGoalListener;
        try {
            mGoalListener = (OnChangeGoalListener) getContext();
            Log.d("mContext is ", getContext().toString());
        } catch (ClassCastException ex) {
            throw new ClassCastException("The hosting activity of the fragment" +
                    "forgot to implement onFragmentInteractionListener");
        }

        changeGoalImg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mGoalListener.onChangeGoalClicked();
            }
        });

        changeGoalsText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mGoalListener.onChangeGoalClicked();
            }
        });


        return rootView;
    }

    public interface OnChangeGoalListener {
        public void onChangeGoalClicked();
    }

}
