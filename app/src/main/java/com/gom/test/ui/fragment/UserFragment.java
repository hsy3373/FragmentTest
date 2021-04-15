package com.gom.test.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gom.test.data.model.User;
import com.gom.test.data.qualifier.SortType;
import com.gom.test.databinding.FragmentUserBinding;
import com.gom.test.ui.UserSelectListener;
import com.gom.test.ui.activity.ItemActivity;
import com.gom.test.ui.adapter.UserAdapter;
import com.gom.test.ui.viewmodel.UserViewModel;

import java.util.List;


public class UserFragment extends Fragment {

    private UserViewModel model;
    private UserAdapter adapter;

    private FragmentUserBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        model = new ViewModelProvider(this).get(UserViewModel.class);
        model.getUserList().observe(getViewLifecycleOwner(), users -> {
            adapter.setUserList(users);
            adapter.sort(SortType.USER);
        });
    }

    @Override
    public void onPause() {
        super.onPause();
    }



    @Override
    public void onResume() {
        super.onResume();
        model.getDefaultUsers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUserBinding.inflate(inflater, container, false);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        this.adapter = new UserAdapter(getContext());
        binding.recyclerview.setAdapter(adapter);
        binding.buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = binding.edittextSearchKeyword.getText().toString();
                model.search(text);
            }
        });


        return binding.getRoot();
    }
}