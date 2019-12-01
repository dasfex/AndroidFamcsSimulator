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
import com.source.studsimulator.ui.fragments.adapters.ActiveButtonsAdapter;
import com.source.studsimulator.ui.fragments.adapters.FriendAdapter;

import java.util.ArrayList;
import java.util.List;

public class HobbyFragment extends Fragment {

    private RecyclerView lonelyHobbyRv;
    private RecyclerView friendHobbyRv;
    private Spinner friendSpinner;

    private List<Friend> friendList;
    private List<StudentActivity> hobbies;
    private ArrayList<Boolean> isHobbyActive = new ArrayList<>();

    private HobbyFragment.OnHobbyFragmentListener activityListener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hobby_fragment_activity, null);

        initializeLists();

        initializeWidgets(view);

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

    private void initializeLists() {
        hobbies = ActionObjects.getHobbyList();
        friendList = ActionObjects.getFriendList();
    }

    private void initializeWidgets(View view) {
        lonelyHobbyRv = view.findViewById(R.id.lonelyHobbyRv);
        lonelyHobbyRv.setLayoutManager(new LinearLayoutManager(getContext()));
        lonelyHobbyRv.setHasFixedSize(true);

        ActiveButtonsAdapter hobbyRvAdapter = new ActiveButtonsAdapter(hobbies);
        lonelyHobbyRv.setAdapter(hobbyRvAdapter);

        hobbyRvAdapter.setAdapterListener(position -> {
            hobbyRvAdapter.setButtonDisActivate(position);
            changeAccessForSideButton(position);
            hobbyRvAdapter.notifyDataSetChanged();
            activityListener.clickOnHobbyButton((Hobby) hobbies.get(position));
        });

        if (isHobbyActive.size() == 0) {
            for (int i = 0; i < hobbies.size(); ++i) {
                isHobbyActive.add(false);
            }
        }

        for (int i = 0; i < hobbies.size(); ++i) {
            if (isHobbyActive.get(i)) {
                hobbyRvAdapter.setButtonDisActivate(i);
            }
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                friendSpinner.setSelection(0);
            }
        });
    }
}
