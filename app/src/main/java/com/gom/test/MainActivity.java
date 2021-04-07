package com.gom.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<User> userList =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment userFragment = new UserFragment();
        Fragment gradeFragment = new GradeFragment();
        Fragment scoreFragment = new ScoreFragment();

        for(int i=0; i<1000; i++) {
            String[] randomGrade =  {"A", "B", "C", "D", "E", "F"};
            Random random = new Random();
            int randomNum = random.nextInt(randomGrade.length);
            User user = new User("user"+i, randomGrade[randomNum], random.nextInt(100));
            userList.add(user);
        }

        BottomNavigationView navi = findViewById(R.id.bottomNavigationView);
        navi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_user:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,userFragment).commit();
                        return true;

                    case R.id.menu_grade:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,gradeFragment).commit();
                        return true;

                    case R.id.menu_score:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,scoreFragment).commit();
                        return true;
                }
                return false;
            }
        });


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, userFragment).commit();

    }
}