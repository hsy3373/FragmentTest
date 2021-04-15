package com.gom.test.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gom.test.data.model.User;
import com.gom.test.data.qualifier.SortType;
import com.gom.test.databinding.LocalFragmentBinding;
import com.gom.test.ui.activity.ItemActivity;
import com.gom.test.ui.adapter.UserAdapter;
import com.gom.test.ui.viewmodel.UserViewModel;

import java.util.List;

public class LocalFragment extends Fragment {

    private LocalFragmentBinding binding;
    private UserViewModel model;
    private UserAdapter adapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        model = new ViewModelProvider(this).get(UserViewModel.class);

        final Observer<List<User>> userObserver = users -> adapter.setUserList(users);
        model.getUserList().observe(getViewLifecycleOwner(), userObserver);
    }

    @Override
    public void onResume() {
        super.onResume();
        model.getHistory(getContext());
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        model.clearHistory(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = LocalFragmentBinding.inflate(inflater, container, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.localRecyclerview.setLayoutManager(linearLayoutManager);
        this.adapter = new UserAdapter(getContext());
        binding.localRecyclerview.setAdapter(this.adapter);

        return binding.getRoot();

    }
}
