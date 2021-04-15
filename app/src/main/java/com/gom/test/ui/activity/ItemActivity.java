package com.gom.test.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.gom.test.R;
import com.gom.test.data.model.User;
import com.gom.test.ui.viewmodel.UserViewModel;

public class ItemActivity extends AppCompatActivity {
    private UserViewModel model;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String grade = intent.getStringExtra("grade");
        int score = intent.getIntExtra("score", -1);

        TextView nameText = findViewById(R.id.name);
        TextView gradeText = findViewById(R.id.grade);
        TextView scoreText = findViewById(R.id.score);
        model = new ViewModelProvider(this).get(UserViewModel.class);
        User user = new User(name, grade, score);
        model.insertUser(this, user);

        if(name != null) {
            nameText.setText(name);
            gradeText.setText(grade);
            scoreText.setText(String.valueOf(score));
        }


    }
}
