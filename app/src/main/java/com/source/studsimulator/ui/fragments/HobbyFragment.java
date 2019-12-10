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
import com.source.studsimulator.model.ActionObjects;
import com.source.studsimulator.model.entity.Friend;
import com.source.studsimulator.model.entity.Hobby;
import com.source.studsimulator.model.entity.StudentActivity;
import com.source.studsimulator.ui.fragments.adapters.FriendAdapter;
import com.source.studsimulator.ui.fragments.adapters.OneActiveButtonWithBlockCharacteristics;

import java.util.ArrayList;
import java.util.List;

public class HobbyFragment extends Fragment {

    private RecyclerView hobbyRv;
    private Spinner friendSpinner;

    private List<Friend> friendList;
    private List<StudentActivity> hobbies;
    private int indexOfActivatedButton = -1;

    private HobbyFragment.OnHobbyFragmentListener activityListener;

    OneActiveButtonWithBlockCharacteristics hobbyRvAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hobby_fragment_activity, null);

        initializeLists();

        initializeWidgets(view);

        return view;
    }


    public void activateButton(int position) {
        hobbyRvAdapter.setIndexOfActivatedButton(position);
        changeAccessForHobby(position);
        hobbyRvAdapter.notifyDataSetChanged();
    }

    private void changeAccessForHobby(int pos) {
        indexOfActivatedButton = indexOfActivatedButton == pos ? -1 : pos;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HobbyFragment.OnHobbyFragmentListener) {
            activityListener = (HobbyFragment.OnHobbyFragmentListener) context;
        }
    }

    public interface OnHobbyFragmentListener {
        void clickOnHobbyButton(int index, Friend friend);
        void unclickOnHobbyButton(Hobby hobby, Friend friend);
    }

    private void initializeLists() {
        hobbies = ActionObjects.getHobbyList();
        friendList = ActionObjects.getFriendList();
    }

    private void initializeWidgets(View view) {
        hobbyRv = view.findViewById(R.id.lonelyHobbyRv);
        hobbyRv.setLayoutManager(new LinearLayoutManager(getContext()));
        hobbyRv.setHasFixedSize(true);

        hobbyRvAdapter =
                new OneActiveButtonWithBlockCharacteristics(hobbies);
        hobbyRv.setAdapter(hobbyRvAdapter);

        hobbyRvAdapter.setAdapterListener(position -> {
            int currentPosition = hobbyRvAdapter.getIndexOfActivatedButton();
            if (currentPosition == position) {
                activityListener.unclickOnHobbyButton((Hobby) hobbies.get(currentPosition),
                                                     (Friend) friendSpinner.getSelectedItem());
                hobbyRvAdapter.setIndexOfActivatedButton(position);
                changeAccessForHobby(position);
                hobbyRvAdapter.notifyDataSetChanged();
            } else {
                activityListener.clickOnHobbyButton(position, (Friend) friendSpinner.getSelectedItem());
            }
        });

        if (indexOfActivatedButton != -1) {
            hobbyRvAdapter.setIndexOfActivatedButton(indexOfActivatedButton);
        }

        friendSpinner = view.findViewById(R.id.friendSpinner);

        FriendAdapter friendAdapter =
                new FriendAdapter((Activity) getContext(), android.R.layout.simple_spinner_dropdown_item,
                        (ArrayList<Friend>) friendList);

        friendSpinner.setAdapter(friendAdapter);
        friendSpinner.setSelection(0);
        friendSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                friendSpinner.setSelection(position);
                Friend friend = (Friend) friendSpinner.getSelectedItem();
                hobbyRvAdapter.setCharacteristicForBlock(friend.getFriendshipLevel());
                hobbyRvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                friendSpinner.setSelection(0);
                Friend friend = (Friend) friendSpinner.getSelectedItem();
                hobbyRvAdapter.setCharacteristicForBlock(friend.getFriendshipLevel());
                hobbyRvAdapter.notifyDataSetChanged();
            }
        });
    }
}
