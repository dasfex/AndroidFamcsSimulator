package com.source.studsimulator.ui.fragments.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.source.studsimulator.R;
import com.source.studsimulator.model.entity.Friend;

import java.util.ArrayList;

public class FriendAdapter extends ArrayAdapter<Friend> {
    private Activity context;
    private ArrayList<Friend> data;

    public FriendAdapter(Activity context, int resource, ArrayList<Friend> data) {
        super(context, resource, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            row = inflater.inflate(R.layout.friend_template, parent, false);
        }

        Friend friend = data.get(position);

        if (friend != null) {
            ImageView friendPhotoIV = row.findViewById(R.id.friendPhoto);
            TextView friendNameTV = row.findViewById(R.id.friendName);
            if (friendPhotoIV != null) {
                friendPhotoIV.setBackground(context.getResources().getDrawable(friend.getPhotoId(), context.getTheme()));
            }
            if (friendNameTV != null) {
                friendNameTV.setText(friend.getName());
            }
        }

        return row;
    }
}
