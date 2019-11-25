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
import com.source.studsimulator.model.entity.Food;
import com.source.studsimulator.model.entity.StudentActivity;
import com.source.studsimulator.model.entity.Work;
import com.source.studsimulator.ui.fragments.adapters.ActiveButtonsAdapter;
import com.source.studsimulator.ui.fragments.adapters.OneActiveButtonAdapter;

import java.util.ArrayList;
import java.util.List;

public class WorkFragment extends Fragment {


    public enum WORK_BUTTONS {
        FLYERS, LOADER, SECURITY, MUSICIAN, FREELANCER,
        MCDONALDS, ITRANSITION, YANDEX,
        FACEBOOK
    }

    public enum BUTTON_STATE {
        ACTIVE, ACCECIBLE, INACCESIBLE
    }

    private RecyclerView sideJobButtons;
    private List<StudentActivity> sideJobList;
    //private RecyclerView Job;

    private WorkFragment.OnWorkFragmentListener activityListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.work_fragment_activity, null);

        sideJobButtons = view.findViewById(R.id.side_job);
        sideJobButtons.setLayoutManager(new LinearLayoutManager(getContext()));
        sideJobButtons.setHasFixedSize(true);

        initializeSideJob();

        ActiveButtonsAdapter sideJobsAdapter = new ActiveButtonsAdapter(sideJobList);
        sideJobButtons.setAdapter(sideJobsAdapter);
        sideJobsAdapter.setAdapterListener(position -> {
            sideJobsAdapter.setButtonDisActivate(position);
            activityListener.clickOnWorkButton((Work) sideJobList.get(position));
        });

//        Job = view.findViewById(R.id.job);
//        sideJobButtons.setLayoutManager(new LinearLayoutManager(getContext()));
//        sideJobButtons.setHasFixedSize(true);

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

    private void initializeSideJob() {
        sideJobList = new ArrayList<>();
        sideJobList.add(new Work("Раздавать флаеры", -5, 5, 0, 0, 1, 1));
        sideJobList.add(new Work("Разгружать вагоны", -15, 10, 0, 0, 0, 2));
        sideJobList.add(new Work("Подменить друга в клубе", -5, 10, 0, 3, 0, 2));
        sideJobList.add(new Work("Играть в переходах", 0, 3, 0, 0, -1, 0));
        sideJobList.add(new Work("пофрилансить", -5, 25, 10, 10, 3, 3));

    }


}
