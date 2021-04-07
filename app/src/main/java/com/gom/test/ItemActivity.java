package com.gom.test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_item);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String grade =  intent.getStringExtra("grade");
        String score =  intent.getStringExtra("score");

        TextView nameText =  findViewById(R.id.name);
        TextView gradeText =  findViewById(R.id.grade);
        TextView scoreText =  findViewById(R.id.score);

        if(name != null) {

            nameText.setText(name);
            gradeText.setText(grade);
            scoreText.setText(score);
        }


    }
}
