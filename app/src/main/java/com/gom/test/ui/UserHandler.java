package com.gom.test.ui;

import com.gom.test.data.model.User;

import java.util.List;

public interface UserHandler {
    void onResult(List<User> users);
}
