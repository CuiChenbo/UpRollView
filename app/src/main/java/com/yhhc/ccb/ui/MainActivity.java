package com.yhhc.ccb.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yhhc.ccb.uprollview.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl1 , new UpRollAnimFragment()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl2 , new UpRollFragment()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl3 , new UpRollRecyclerFragment()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl4 , new UpRollViewPagerFragment()).commit();
    }

}
