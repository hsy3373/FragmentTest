package com.gom.test.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "userName")
    private final String userName;

    @ColumnInfo(name = "userGrade")
    private final String userGrade;

    @ColumnInfo(name = "userScore")
    private final int userScore;

    public int getUserScore() {
        return userScore;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserGrade() {
        return userGrade;
    }

    public User(String userName, String userGrade, int userScore) {
        this.userName = userName;
        this.userGrade = userGrade;
        this.userScore = userScore;
    }
}