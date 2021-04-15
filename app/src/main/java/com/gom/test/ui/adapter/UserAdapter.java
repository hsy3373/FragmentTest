package com.gom.test.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gom.test.R;
import com.gom.test.data.qualifier.SortType;
import com.gom.test.data.model.User;
import com.gom.test.ui.UserSelectListener;
import com.gom.test.ui.activity.ItemActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

    private List<User> userList;
    private UserSelectListener listener;
    private Context context;
    public UserAdapter(Context context) {
        this.userList = new ArrayList<>();
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
        holder.itemView.setOnClickListener(v -> {
            User user = userList.get(position);
            Intent intent = new Intent(context, ItemActivity.class);
            intent.putExtra("name", user.getUserName());
            intent.putExtra("grade", user.getUserGrade());
            intent.putExtra("score", user.getUserScore());
            context.startActivity(intent);
        });
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    public void sort(SortType sortType) {
        switch (sortType) {
            case SCORE:
                sortByScore();
                break;
            case GRADE:
                sortByGrade();
                break;
            default:
                sortByUsers();
                break;
        }
    }

    private void sortByUsers() {
        Collections.sort(this.userList, (u1, u2) -> Integer.parseInt(u1.getUserName().substring(4)) - Integer.parseInt(u2.getUserName().substring(4)));
        notifyDataSetChanged();
    }

    private void sortByScore() {
        Collections.sort(this.userList, (o1, o2) -> {
            if (o1.getUserScore() == o2.getUserScore()) {
                if(o2.getUserGrade().compareTo(o1.getUserGrade()) == 0) {
                    return Integer.parseInt(o1.getUserName().substring(4)) - Integer.parseInt(o2.getUserName().substring(4));
                } else {
                    return o2.getUserGrade().compareTo(o1.getUserGrade());
                }
            } else {
                return o1.getUserScore() - o2.getUserScore();
            }
        });
        Collections.reverse(this.userList);
        notifyDataSetChanged();
    }

    private void sortByGrade() {
        Collections.sort(this.userList, (o1, o2) -> {
            if (o1.getUserGrade().equals(o2.getUserGrade())) {
                if (o1.getUserScore() == o2.getUserScore()) {
                    return Integer.parseInt(o1.getUserName().substring(4)) - Integer.parseInt(o2.getUserName().substring(4));
                } else {
                    return o2.getUserScore() - o1.getUserScore();
                }
            } else {
                return o1.getUserGrade().compareTo(o2.getUserGrade());
            }
        });
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView nameText;
        private final TextView gradeText;
        private final TextView scoreText;

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
