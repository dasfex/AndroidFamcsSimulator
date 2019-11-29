package com.source.studsimulator.ui.fragments;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.source.studsimulator.R;
import com.source.studsimulator.model.entity.StudentActivity;
import com.source.studsimulator.model.entity.Work;
import com.source.studsimulator.ui.fragments.adapters.ActiveButtonsAdapter;
import com.source.studsimulator.ui.fragments.adapters.BlockUnactiveButtonsAdapter;

import java.util.ArrayList;
import java.util.List;

public class WorkFragment extends Fragment {


    public enum WORK_BUTTONS {
        FLYERS, LOADER, SECURITY, MUSICIAN, FREELANCER,
        MCDONALDS, ITRANSITION, YANDEX,
        FACEBOOK
    }

    private RecyclerView sideJobRv;
    private RecyclerView workRv;
    private RecyclerView summerWorkRv;

    private List<StudentActivity> sideJobList;
    private List<StudentActivity> workList;
    private List<StudentActivity> summerWorkList;

    private ArrayList<Boolean> isSideJobActive = new ArrayList<>();
    private int activeWorkIndex = -1;
    private int activeSummerWorkIndex = -1;

    private WorkFragment.OnWorkFragmentListener activityListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.work_fragment_activity, null);

        initializeLists();

        initializeRecyclerView(view);

        if (isSideJobActive.size() == 0) {
            for (int i = 0; i < sideJobList.size(); ++i) {
                isSideJobActive.add(false);
            }
        }

        return view;
    }

    public interface OnWorkFragmentListener {
        void clickOnWorkButton(Work work);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof WorkFragment.OnWorkFragmentListener) {
            activityListener = (WorkFragment.OnWorkFragmentListener) context;
        }
    }

    private void initializeLists() {
        sideJobList = new ArrayList<>();
        sideJobList.add(new Work(getString(R.string.flyers), -5, -5, 5, 0, 0, 0, 1, 2));
        sideJobList.add(new Work(getString(R.string.loader), -15, -15, 10, 0, 0, 0, 0,3));
        sideJobList.add(new Work(getString(R.string.security), -5, - 10, 10, 0, 3, 0, 1, 1));
        sideJobList.add(new Work(getString(R.string.musician), -4, -8,  3, 0, 0, 0, 1, 1));
        sideJobList.add(new Work(getString(R.string.freelancer), -2, -5,  25, 10, 10, 3, 1, 2));

        workList = new ArrayList<>();
        workList.add(new Work(getString(R.string.mcdonalds), -10, -10, 50, 20, 30, 10, 6, 2));
        workList.add(new Work(getString(R.string.itra), -6, -7, 40, 5, 2, 5, 4,3));
        workList.add(new Work(getString(R.string.yandex), -20, -20, 100, 50, 3, 20, 1,3));

        summerWorkList = new ArrayList<>();
        summerWorkList.add(new Work(getString(R.string.facebook), -20, -20, 150, 30, 50, 20, 20,4));
    }

    private void changeAccessForSideButton(int pos) {
        isSideJobActive.set(pos, !isSideJobActive.get(pos));
    }

    private void changeWorkButtonActivity(int pos) {
        activeWorkIndex = activeWorkIndex == pos ? -1 : pos;
    }

    private void changeSummerWorkButtonActivity(int pos) {
        activeSummerWorkIndex = activeSummerWorkIndex == pos ? -1 : pos;
    }

    private void initializeRecyclerView(View view) {
        sideJobRv = view.findViewById(R.id.sideJobRV);
        sideJobRv.setLayoutManager(new LinearLayoutManager(getContext()));
        sideJobRv.setHasFixedSize(true);
        ActiveButtonsAdapter sideJobsAdapter = new ActiveButtonsAdapter(sideJobList);
        sideJobRv.setAdapter(sideJobsAdapter);
        sideJobsAdapter.setAdapterListener(position -> {
            sideJobsAdapter.setButtonDisActivate(position);
            changeAccessForSideButton(position);
            sideJobsAdapter.notifyDataSetChanged();
            activityListener.clickOnWorkButton((Work) sideJobList.get(position));
        });

        for (int i = 0; i < isSideJobActive.size(); ++i) {
            if (isSideJobActive.get(i)) {
                sideJobsAdapter.setButtonDisActivate(i);
            }
        }

        workRv = view.findViewById(R.id.workRV);
        workRv.setLayoutManager(new LinearLayoutManager(getContext()));
        workRv.setHasFixedSize(true);
        BlockUnactiveButtonsAdapter workAdapter = new BlockUnactiveButtonsAdapter(workList);
        workRv.setAdapter(workAdapter);
        workAdapter.setIndexOfActivatedButton(activeWorkIndex);
        workAdapter.setAdapterListener(position -> {
            workAdapter.setIndexOfActivatedButton(position);
            changeWorkButtonActivity(position);
            workAdapter.notifyDataSetChanged();
            activityListener.clickOnWorkButton((Work) workList.get(position));
        });

        summerWorkRv = view.findViewById(R.id.summerWorkRV);
        summerWorkRv.setLayoutManager(new LinearLayoutManager(getContext()));
        summerWorkRv.setHasFixedSize(true);
        BlockUnactiveButtonsAdapter summerWorkAdapter = new BlockUnactiveButtonsAdapter(summerWorkList);
        summerWorkRv.setAdapter(summerWorkAdapter);
        summerWorkAdapter.setIndexOfActivatedButton(activeSummerWorkIndex);
        summerWorkAdapter.setAdapterListener(position -> {
            summerWorkAdapter.setIndexOfActivatedButton(position);
            changeSummerWorkButtonActivity(position);
            summerWorkAdapter.notifyDataSetChanged();
            activityListener.clickOnWorkButton((Work) summerWorkList.get(position));
        });
    }

}
