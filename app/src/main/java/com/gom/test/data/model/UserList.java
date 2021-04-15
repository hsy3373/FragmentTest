package com.gom.test.data.model;

import com.gom.test.data.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserList {
    private static List<User> userList = new ArrayList<>();

    public static List<User> getUserList() {
        if (userList.isEmpty()) {
            for(int i =0; i<1000; i++) {
                String[] randomGrade =  {"A", "B", "C", "D", "E", "F"};
                Random random = new Random();
                int randomNum = random.nextInt(randomGrade.length);
                User user = new User("user"+i, randomGrade[randomNum], random.nextInt(100));
                userList.add(user);
            }
        }
        return userList;
    }
}
