package com.gom.test;

import androidx.appcompat.app.AppCompatActivity;

public class User extends AppCompatActivity {

    private String userName;
    private String userGrade;
    private int userScore;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(String userGrade) {
        this.userGrade = userGrade;
    }

    public String getUserScore() {
        return String.valueOf(userScore);
    }

    public int getUserScoreInt() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    public User(String userName, String userGrade, int userScore) {
        this.userName = userName;
        this.userGrade = userGrade;
        this.userScore = userScore;
    }
}
//
//class Comparator implements Comparable<User>{
//    @Override
//    public int compareTo(User o) {
//        return 0;
//    }
//    public int compare(User box1, User box2) {
//
//        int ret = 0;
//        if (box1.point < box2.point) {
//            ret = 1;
//        }
//        if (box1.point == box2.point) {
//            if (box1.date < box2.date) {
//                ret = 1;
//            }
//        }
//        return ret;
//    }
//
//
//
//}
