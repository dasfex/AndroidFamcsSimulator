package com.source.studsimulator.ui.fragments.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.source.studsimulator.R;
import com.source.studsimulator.model.entity.StudentActivity;

import java.util.List;

public class BlockUnactiveButtonsAdapter
        extends RecyclerView.Adapter<BlockUnactiveButtonsAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.text_view);
        }
    }

    private List<StudentActivity> studentActivities;
    private int indexOfActivatedButton = -1;
    private AdapterListener adapterListener;

    public void setIndexOfActivatedButton(int indexOfActivatedButton) {
        if (indexOfActivatedButton == this.indexOfActivatedButton) {
            this.indexOfActivatedButton = -1;
        } else if (this.indexOfActivatedButton == -1) {
            this.indexOfActivatedButton = indexOfActivatedButton;
        }
    }

    public BlockUnactiveButtonsAdapter(List<StudentActivity> activities) {
        this.studentActivities = activities;
    }

    public AdapterListener getAdapterListener() {
        return adapterListener;
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
        viewHolder.textView.setText(studentActivities.get(ind).getTitle());
        if (indexOfActivatedButton == ind) {
            viewHolder.textView.setBackgroundColor(Color.GREEN);
            viewHolder.textView.setClickable(true);
        } else {
            if (indexOfActivatedButton != -1) {
                viewHolder.textView.setBackgroundColor(Color.GRAY);
                viewHolder.textView.setClickable(false);
            } else {
                viewHolder.textView.setBackgroundColor(Color.WHITE);
                viewHolder.textView.setClickable(true);
            }
        }
        viewHolder.textView.setOnClickListener(v -> adapterListener.onClick(ind));
    }

    public interface AdapterListener {
        void onClick(int position);
    }
}
