package com.gom.test.ui.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.gom.test.data.model.AppDatabase;
import com.gom.test.data.model.DatabaseWrapper;
import com.gom.test.data.model.User;
import com.gom.test.data.model.UserList;
import com.gom.test.ui.UserHandler;

import java.util.ArrayList;
import java.util.List;

public class UserViewModel extends androidx.lifecycle.ViewModel {

    private final List<User> defaultUsers = UserList.getUserList();
    private final MutableLiveData<List<User>> userList = new MutableLiveData<>();

    public MutableLiveData<List<User>> getUserList() {
        return userList;
    }

    public void search(String input) {
        List<User> searchUserList = new ArrayList<>();
        for(User user : defaultUsers) {
            if(user.getUserName().contains(input)){
                searchUserList.add(user);
            }
        }
        userList.setValue(searchUserList);
    }
    public void getDefaultUsers() {
        userList.postValue(defaultUsers);
    }
    public void getHistory(Context context) {
        DatabaseWrapper.getInstance(context).getAllUsers(new UserHandler() {
            @Override
            public void onResult(List<User> users) {
                userList.postValue(users);
            }
        });
    }

    public void insertUser(Context context, User user) {
        DatabaseWrapper.getInstance(context).insertUser(user);
    }

    public void clearHistory(Context context) {
        DatabaseWrapper.getInstance(context).clearTable();
    }
}
