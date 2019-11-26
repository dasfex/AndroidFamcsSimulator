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
import com.source.studsimulator.ui.fragments.adapters.OneActiveButtonAdapter;

import java.util.ArrayList;
import java.util.List;

public class WorkFragment extends Fragment {


    public enum WORK_BUTTONS {
        FLYERS, LOADER, SECURITY, MUSICIAN, FREELANCER,
        MCDONALDS, ITRANSITION, YANDEX,
        FACEBOOK
    }

    private RecyclerView sideJobRV;
    private RecyclerView workRV;
    private RecyclerView summerWorkRV;

    private List<StudentActivity> sideJobList;
    private List<StudentActivity> workList;
    private List<StudentActivity> summerWorkList;

    private WorkFragment.OnWorkFragmentListener activityListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.work_fragment_activity, null);

        initializeLists();

        sideJobRV = view.findViewById(R.id.sideJobRV);
        sideJobRV.setLayoutManager(new LinearLayoutManager(getContext()));
        sideJobRV.setHasFixedSize(true);
        ActiveButtonsAdapter sideJobsAdapter = new ActiveButtonsAdapter(sideJobList);
        sideJobRV.setAdapter(sideJobsAdapter);
        sideJobsAdapter.setAdapterListener(position -> {
            sideJobsAdapter.setButtonDisActivate(position);
            sideJobsAdapter.notifyDataSetChanged();
            activityListener.clickOnWorkButton((Work) sideJobList.get(position));
        });

        workRV = view.findViewById(R.id.workRV);
        workRV.setLayoutManager(new LinearLayoutManager(getContext()));
        workRV.setHasFixedSize(true);
        BlockUnactiveButtonsAdapter workAdapter = new BlockUnactiveButtonsAdapter(workList);
        workRV.setAdapter(workAdapter);
        workAdapter.setAdapterListener(position -> {
            workAdapter.setIndexOfActivatedButton(position);
            workAdapter.notifyDataSetChanged();
            activityListener.clickOnWorkButton((Work) workList.get(position));
        });

        summerWorkRV = view.findViewById(R.id.summerWorkRV);
        summerWorkRV.setLayoutManager(new LinearLayoutManager(getContext()));
        summerWorkRV.setHasFixedSize(true);
        BlockUnactiveButtonsAdapter summerWorkAdapter = new BlockUnactiveButtonsAdapter(summerWorkList);
        summerWorkRV.setAdapter(summerWorkAdapter);
        summerWorkAdapter.setAdapterListener(position -> {
            summerWorkAdapter.setIndexOfActivatedButton(position);
            summerWorkAdapter.notifyDataSetChanged();
            activityListener.clickOnWorkButton((Work) summerWorkList.get(position));
        });

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
        sideJobList.add(new Work(getString(R.string.flyers), -5, -5, 5, 0, 0, 0, 1));
        sideJobList.add(new Work(getString(R.string.loader), -15, -15, 10, 0, 0, 0, 0));
        sideJobList.add(new Work(getString(R.string.security), -5, - 10, 10, 0, 3, 0, 1));
        sideJobList.add(new Work(getString(R.string.musician), -4, -8,  3, 0, 0, 0, 1));
        sideJobList.add(new Work(getString(R.string.freelancer), -2, -5,  25, 10, 10, 3, 1));

        workList = new ArrayList<>();
        workList.add(new Work(getString(R.string.mcdonalds), -10, -10, 50, 20, 30, 10, 6));
        workList.add(new Work(getString(R.string.itra), -6, -7, 40, 5, 2, 5, 4));
        workList.add(new Work(getString(R.string.yandex), -20, -20, 100, 50, 3, 20, 1));

        summerWorkList = new ArrayList<>();
        summerWorkList.add(new Work(getString(R.string.facebook), -20, -20, 150, 30, 50, 20, 20));
    }

}
