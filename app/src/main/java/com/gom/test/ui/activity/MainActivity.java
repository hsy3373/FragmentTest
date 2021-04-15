package com.gom.test.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.gom.test.R;
import com.gom.test.data.model.User;
import com.gom.test.ui.adapter.PagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.viewPager);
        pagerAdapter = new PagerAdapter(this);

        viewPager2.setAdapter(pagerAdapter);

        BottomNavigationView navi = findViewById(R.id.bottomNavigationView);
        navi.setOnNavigationItemSelectedListener(item -> {
            int num = 0;
            switch (item.getItemId()){
                case R.id.menu_user:
                    num = 0;
                    break;
                case R.id.menu_grade:
                    num = 1;
                    break;

                case R.id.menu_score:
                    num = 2;
                    break;

                case R.id.menu_local:
                    num = 3;
                    break;
                default:
                    num = 0;
                    break;
            }
            if (num != viewPager2.getCurrentItem()) {
                viewPager2.setCurrentItem(num);
            }
            return true;
        });
        navi.setSelectedItemId(0);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                int navigation = 0;
                switch (position) {
                    case 1:
                        navigation = R.id.menu_grade;
                        break;
                    case 2:
                        navigation = R.id.menu_score;
                        break;
                    case 3:
                        navigation = R.id.menu_local;
                        break;
                    default:
                        navigation = R.id.menu_user;
                        break;
                }
                if (navi.getSelectedItemId() != navigation) {
                    navi.setSelectedItemId(navigation);
                }
            }
        });
    }
}

