package com.gom.test.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.gom.test.ui.fragment.GradeFragment;
import com.gom.test.ui.fragment.LocalFragment;
import com.gom.test.ui.fragment.ScoreFragment;
import com.gom.test.ui.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentStateAdapter {
    List<Fragment> fragmentList = new ArrayList<>();
    public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        fragmentList.add(new UserFragment());
        fragmentList.add(new GradeFragment());
        fragmentList.add(new ScoreFragment());
        fragmentList.add(new LocalFragment());
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}
