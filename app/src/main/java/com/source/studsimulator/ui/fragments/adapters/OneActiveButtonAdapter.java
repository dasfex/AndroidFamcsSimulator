package com.source.studsimulator.ui.fragments.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.source.studsimulator.R;
import com.source.studsimulator.model.entity.StudentActivity;

import java.util.List;

public class OneActiveButtonAdapter
        extends RecyclerView.Adapter<OneActiveButtonAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        private Button button;

        ViewHolder(View view) {
            super(view);
            button = view.findViewById(R.id.button);
        }
    }

    private List<StudentActivity> studentActivities;
    private int indexOfActivatedButton = -1;
    AdapterListener adapterListener;

    public void setIndexOfActivatedButton(int indexOfActivatedButton) {
        if (indexOfActivatedButton == this.indexOfActivatedButton) {
            this.indexOfActivatedButton = -1;
        } else {
            this.indexOfActivatedButton = indexOfActivatedButton;
        }
    }

    public int getIndexOfActivatedButton() {
        return this.indexOfActivatedButton;
    }

    public OneActiveButtonAdapter(List<StudentActivity> studentActivities) {
        this.studentActivities = studentActivities;
    }

    public void setAdapterListener(AdapterListener adapterListener) {
        this.adapterListener = adapterListener;
    }

    @Override
    public int getItemCount() {
        return studentActivities.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int ind) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buttons_linear_layout,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int ind) {
        viewHolder.button.setText(studentActivities.get(ind).getTitle());
        viewHolder.button.setBackgroundColor(ind == indexOfActivatedButton ? Color.GREEN : Color.WHITE);
        viewHolder.button.setOnClickListener(v -> adapterListener.onClick(ind));
    }

    public interface AdapterListener {
        void onClick(int position);
    }
}
