package com.example.small_gongjeon;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GroupAddInviteAdapter extends RecyclerView.Adapter<GroupAddInviteAdapter.CustomViewHolder_group> {


    private ArrayList<Friend> mList = null;
    private Activity context = null;


    public GroupAddInviteAdapter(Activity context, ArrayList<Friend> list) {
        this.context = context;
        this.mList = list;
    }

    class CustomViewHolder_group extends RecyclerView.ViewHolder {
        protected TextView name;
        protected CheckBox checkBox;
        protected ImageView imageView;


        public CustomViewHolder_group(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.name_invite_friend);
            this.checkBox = (CheckBox) view.findViewById(R.id.checkbox_invite_friend);
            this.imageView = (ImageView) view.findViewById(R.id.image_invite_friend);
        }
    }


    @Override
    public CustomViewHolder_group onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listviewitem_group_add,viewGroup,false);
        GroupAddInviteAdapter.CustomViewHolder_group viewHolder = new GroupAddInviteAdapter.CustomViewHolder_group(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupAddInviteAdapter.CustomViewHolder_group viewholder, int position) {

        viewholder.name.setText(mList.get(position).getName());
        viewholder.checkBox.setChecked(false);
//        viewholder.message.setText(mList.get(position).getMessage());
        viewholder.imageView.setImageResource(mList.get(position).getPhotoID());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
}
