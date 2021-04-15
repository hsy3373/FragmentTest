package com.gom.test.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gom.test.data.qualifier.SortType;
import com.gom.test.data.model.User;
import com.gom.test.databinding.FragmentGradeBinding;
import com.gom.test.ui.activity.ItemActivity;
import com.gom.test.ui.adapter.UserAdapter;
import com.gom.test.ui.viewmodel.UserViewModel;

import java.util.List;

public class GradeFragment extends Fragment {

    private UserViewModel model;
    private UserAdapter adapter;
    private FragmentGradeBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        model = new ViewModelProvider(this).get(UserViewModel.class);
        final Observer<List<User>> userObserver = users -> {
            adapter.setUserList(users);
            adapter.sort(SortType.GRADE);
        };
        model.getUserList().observe(getViewLifecycleOwner(), userObserver);
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
        binding = FragmentGradeBinding.inflate(inflater, container, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.recyclerview.setLayoutManager(linearLayoutManager);
        this.adapter = new UserAdapter(getContext());
        binding.recyclerview.setAdapter(this.adapter);

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