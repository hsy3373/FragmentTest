package com.gom.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static com.gom.test.MainActivity.userList;

public class ScoreFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment, container, false);


        ArrayList<User> userArrayList =  (ArrayList<User>) userList.clone();

        Collections.sort(userArrayList, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {

                if(o1.getUserScoreInt() < o2.getUserScoreInt()) return -1;
                if(o1.getUserScoreInt() > o2.getUserScoreInt()) return 1;

                if(o1.getUserGrade().compareTo(o2.getUserGrade()) > 0) return -1;
                if(o1.getUserGrade().compareTo(o2.getUserGrade()) < 0) return 1;


                return 0;
            }
        });

        Collections.reverse(userArrayList);


        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.scrollToPosition(0);
        UserAdapter adapter =  new UserAdapter(userArrayList, getActivity());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;


    }
}
