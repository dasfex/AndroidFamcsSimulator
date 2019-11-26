package com.source.studsimulator.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.source.studsimulator.R;
import com.source.studsimulator.model.entity.Friend;
import com.source.studsimulator.model.entity.Hobby;
import com.source.studsimulator.model.entity.StudentActivity;
import com.source.studsimulator.ui.fragments.adapters.ActiveButtonsAdapter;
import com.source.studsimulator.ui.fragments.adapters.FriendAdapter;

import java.util.ArrayList;

public class HobbyFragment extends Fragment {

    public enum HOBBY_BUTTONS {
        READ, DANCE, BEER, FILM, VOTE
    }

    private RecyclerView hobbyRV;
    private Spinner friendSpinner;

    private ArrayList<Friend> friendList;
    private ArrayList<StudentActivity> hobbies;
    private ArrayList<Boolean> isHobbyActive = new ArrayList<>();

    private HobbyFragment.OnHobbyFragmentListener activityListener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hobby_fragment_activity, null);

        initializeFriends();
        initializeHobbies();

        hobbyRV = view.findViewById(R.id.hobbyRV);
        hobbyRV.setLayoutManager(new LinearLayoutManager(getContext()));
        hobbyRV.setHasFixedSize(true);

        ActiveButtonsAdapter hobbyRVAdapter = new ActiveButtonsAdapter(hobbies);
        hobbyRV.setAdapter(hobbyRVAdapter);

        hobbyRVAdapter.setAdapterListener(position -> {
            hobbyRVAdapter.setButtonDisActivate(position);
            changeAccessForSideButton(position);
            hobbyRVAdapter.notifyDataSetChanged();
            activityListener.clickOnHobbyButton((Hobby) hobbies.get(position));
        });

        if (isHobbyActive.size() == 0) {
            for (int i = 0; i < hobbies.size(); ++i) {
                isHobbyActive.add(false);
            }
        }

        for (int i = 0; i < hobbies.size(); ++i) {
            if (isHobbyActive.get(i)) {
                hobbyRVAdapter.setButtonDisActivate(i);
            }
        }

        friendSpinner = view.findViewById(R.id.friendSpinner);

        FriendAdapter friendAdapter =
                new FriendAdapter((Activity) getContext(), android.R.layout.simple_spinner_dropdown_item, friendList);

        friendSpinner.setAdapter(friendAdapter);

        friendSpinner.setSelection(0);

        friendSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                friendSpinner.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                friendSpinner.setSelection(0);
            }
        });


        return view;
    }

    private void changeAccessForSideButton(int pos) {
        isHobbyActive.set(pos, !isHobbyActive.get(pos));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HobbyFragment.OnHobbyFragmentListener) {
            activityListener = (HobbyFragment.OnHobbyFragmentListener) context;
        }
    }

    public interface OnHobbyFragmentListener {
        void clickOnHobbyButton(Hobby hobby);
    }

    private void initializeHobbies() {
        hobbies = new ArrayList<>();
        hobbies.add(new Hobby(getString(R.string.read), 0, 1, 1));
        hobbies.add(new Hobby(getString(R.string.dance), 0, 1, 1));
        hobbies.add(new Hobby(getString(R.string.beer), 0, 1, 1));
        hobbies.add(new Hobby(getString(R.string.film), 0, 1, 1));
        hobbies.add(new Hobby(getString(R.string.vote), 0, 1, 1));
    }

    private void initializeFriends() {
        friendList = new ArrayList<>();
        friendList.add(new Friend(50, 0, 0.0, "NoOne", R.drawable.hobby));
        friendList.add(new Friend(50, 0, 0.0, "Vitya", R.drawable.food));
        friendList.add(new Friend(50, 0, 0.0, "Zhenya", R.drawable.info));
    }
}
