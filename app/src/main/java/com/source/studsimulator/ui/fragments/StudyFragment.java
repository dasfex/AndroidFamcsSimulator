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
import com.source.studsimulator.model.entity.StudentActivity;
import com.source.studsimulator.model.entity.Study;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.study_fragment_activity, null);

        initializeLists();

        initializeRv(view);

        return view;
    }

    private void changeButtonActivity(int position) {
        activeButtonIndex = activeButtonIndex == position ? -1 : position;
    }

    private void changeAccessForSideButton(int pos) {
        isCourseActive.set(pos, !isCourseActive.get(pos));
    }

    public interface OnStudyFragmentListener {
        void clickOnStudyButton(Study study);
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
        OneActiveButtonAdapter universityRvAdapter = new OneActiveButtonAdapter(university);
        universityRv.setAdapter(universityRvAdapter);
        universityRvAdapter.setIndexOfActivatedButton(activeButtonIndex);

        universityRvAdapter.setAdapterListener(position -> {
            int currentPosition = universityRvAdapter.getIndexOfActivatedButton();
            int currentEnergy = activityListener.getEnergy();
            if (currentPosition != -1) {
                currentEnergy += university.get(currentPosition).getEnergyNeeded();
            }
            StudentActivity newStudy = university.get(position);
            if (currentEnergy >= newStudy.getEnergyNeeded()) {
                if (currentPosition != -1) {
                    activityListener.unclickOnStudyButton((Study) university.get(currentPosition));
                }
                universityRvAdapter.setIndexOfActivatedButton(position);
                changeButtonActivity(position);
                universityRvAdapter.notifyDataSetChanged();
                if (currentPosition != position) {
                    activityListener.clickOnStudyButton((Study) university.get(position));
                }
            } else {
                Toast.makeText(getContext(),
                        StudSimulatorApplication.getContext().getString(R.string.notEnoughEnergy),
                        Toast.LENGTH_SHORT).show();
            }
        });

        extraActivityRv = view.findViewById(R.id.extraActivityRV);
        extraActivityRv.setLayoutManager(new LinearLayoutManager(getContext()));
        extraActivityRv.setHasFixedSize(true);
        ActiveButtonsAdapter extraActivityRvAdapter = new ActiveButtonsAdapter(extraActivity);
        extraActivityRv.setAdapter(extraActivityRvAdapter);

        extraActivityRvAdapter.setAdapterListener(position -> {
            List<Integer> currentIndices = extraActivityRvAdapter.getActiveButtonsIndices();
            if (currentIndices.contains(position)) {
                activityListener.unclickOnStudyButton((Study) extraActivity.get(position));
                extraActivityRvAdapter.setButtonDisActivate(position);
                changeAccessForSideButton(position);
                extraActivityRvAdapter.notifyDataSetChanged();
            } else {
                int currentEnergy = activityListener.getEnergy();
                StudentActivity newStudy = extraActivity.get(position);
                if (currentEnergy >= newStudy.getEnergyNeeded()) {
                    activityListener.clickOnStudyButton((Study) extraActivity.get(position));
                    extraActivityRvAdapter.setButtonDisActivate(position);
                    changeAccessForSideButton(position);
                    extraActivityRvAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(),
                            StudSimulatorApplication.getContext().getString(R.string.notEnoughEnergy),
                            Toast.LENGTH_SHORT).show();
                }
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
