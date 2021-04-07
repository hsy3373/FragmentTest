package com.gom.test;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

    private final Context context;
    private ArrayList<User> userList = new ArrayList<>();

    public UserAdapter(ArrayList<User> userList, Context context) {
        this.userList = userList;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(userList.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ItemActivity.class);
                intent.putExtra("name", userList.get(position).getUserName());
                intent.putExtra("grade", userList.get(position).getUserGrade());
                intent.putExtra("score", userList.get(position).getUserScore());
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameText;
        private TextView gradeText;
        private TextView scoreText;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.userName);
            gradeText = itemView.findViewById(R.id.userGrade);
            scoreText = itemView.findViewById(R.id.userScore);

        }

        void onBind(User user) {
            nameText.setText(user.getUserName());
            gradeText.setText(user.getUserGrade());
            scoreText.setText(String.valueOf(user.getUserScore()));
        }

    }


}
