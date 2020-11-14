package com.cookandroid.fragment6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    MainFragment mainFragment;
    AnnounceFragment announceFragment;
    SurveyFragment surveyFragment;
    CommunityFragment communityFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.mainFragment);
        announceFragment = new AnnounceFragment();
        surveyFragment = new SurveyFragment();
        communityFragment = new CommunityFragment();
    }

    public void onFragmentChanged(int index) {
        if (index == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, announceFragment).commit();
        } else if (index == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, mainFragment).commit();
        } else if (index == 2) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, surveyFragment).commit();
        } else if (index == 3) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, communityFragment).commit();
        }
    }

}
