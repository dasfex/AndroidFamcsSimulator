package com.source.studsimulator.ui.fragments;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.source.studsimulator.R;
import com.source.studsimulator.model.ActionObjects;
import com.source.studsimulator.model.Student;
import com.source.studsimulator.model.entity.StudentActivity;
import com.source.studsimulator.model.entity.Study;
import com.source.studsimulator.model.entity.Work;
import com.source.studsimulator.ui.StudSimulatorApplication;
import com.source.studsimulator.ui.fragments.adapters.ActiveButtonsAdapter;
import com.source.studsimulator.ui.fragments.adapters.OneActiveButtonAdapter;

import java.util.ArrayList;
import java.util.List;

public class StudyFragment extends Fragment {

    private RecyclerView universityRv;
    private RecyclerView extraActivityRv;
    private List<StudentActivity> university;
    private List<StudentActivity> extraActivity;
    private ArrayList<Boolean> isCourseActive = new ArrayList<>();

    private StudyFragment.OnStudyFragmentListener activityListener;
    private int activeButtonIndex = -1;

    OneActiveButtonAdapter universityRvAdapter;
    ActiveButtonsAdapter extraActivityRvAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.study_fragment_activity, null);

        initializeLists();

        initializeRv(view);

        return view;
    }

    public void activateButton(int position, Study.TYPE_OF_STUDY type) {
        switch (type) {
            case UNIVERSITY:
                universityRvAdapter.setIndexOfActivatedButton(position);
                changeButtonActivity(position);
                universityRvAdapter.notifyDataSetChanged();
                break;
            case EXTRA:
                extraActivityRvAdapter.setButtonDisActivate(position);
                    changeAccessForSideButton(position);
                    extraActivityRvAdapter.notifyDataSetChanged();
                break;
        }
    }

    private void changeButtonActivity(int position) {
        activeButtonIndex = activeButtonIndex == position ? -1 : position;
    }

    private void changeAccessForSideButton(int pos) {
        isCourseActive.set(pos, !isCourseActive.get(pos));
    }

    public interface OnStudyFragmentListener {
        void clickOnStudyButton(int index, Study.TYPE_OF_STUDY type);
        void unclickOnStudyButton(Study study);
        int getEnergy();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof StudyFragment.OnStudyFragmentListener) {
            activityListener = (StudyFragment.OnStudyFragmentListener) context;
        }
    }

    private void initializeLists() {
        university = ActionObjects.getUniversityList();
        extraActivity = ActionObjects.getExtraActivityList();
    }

    private void initializeRv(View view) {
        universityRv = view.findViewById(R.id.universityRV);
        universityRv.setLayoutManager(new LinearLayoutManager(getContext()));
        universityRv.setHasFixedSize(true);
        universityRvAdapter = new OneActiveButtonAdapter(university);
        universityRv.setAdapter(universityRvAdapter);
        universityRvAdapter.setIndexOfActivatedButton(activeButtonIndex);

        universityRvAdapter.setAdapterListener(position -> {
            int currentPosition = universityRvAdapter.getIndexOfActivatedButton();
            if (currentPosition == position) {
                activityListener.unclickOnStudyButton((Study) university.get(currentPosition));
                universityRvAdapter.setIndexOfActivatedButton(position);
                changeButtonActivity(position);
                universityRvAdapter.notifyDataSetChanged();
            } else {
                activityListener.clickOnStudyButton(position, Study.TYPE_OF_STUDY.UNIVERSITY);
            }
        });

        extraActivityRv = view.findViewById(R.id.extraActivityRV);
        extraActivityRv.setLayoutManager(new LinearLayoutManager(getContext()));
        extraActivityRv.setHasFixedSize(true);
        extraActivityRvAdapter = new ActiveButtonsAdapter(extraActivity);
        extraActivityRv.setAdapter(extraActivityRvAdapter);

        extraActivityRvAdapter.setAdapterListener(position -> {
            List<Integer> currentIndices = extraActivityRvAdapter.getActiveButtonsIndices();
            if (currentIndices.contains(position)) {
                activityListener.unclickOnStudyButton((Study) extraActivity.get(position));
                extraActivityRvAdapter.setButtonDisActivate(position);
                changeAccessForSideButton(position);
                extraActivityRvAdapter.notifyDataSetChanged();
            } else {
                activityListener.clickOnStudyButton(position, Study.TYPE_OF_STUDY.EXTRA);
            }
        });

        if (isCourseActive.size() == 0) {
            for (int i = 0; i < extraActivity.size(); ++i) {
                isCourseActive.add(false);
            }
        }

        for (int i = 0; i < isCourseActive.size(); ++i) {
            if (isCourseActive.get(i)) {
                extraActivityRvAdapter.setButtonDisActivate(i);
            }
        }
    }
}
