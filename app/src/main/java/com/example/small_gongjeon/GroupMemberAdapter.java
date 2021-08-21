package com.example.small_gongjeon;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GroupMemberAdapter extends RecyclerView.Adapter<GroupMemberAdapter.CustomViewHolder> {
    private static String IP_ADDRESS = "27.96.134.147";
    private static String TAG = "small_gongjeon";

    private ArrayList<GroupMember> mList = null;
    private Activity context = null;

    public GroupMemberAdapter(Activity context, ArrayList<GroupMember> list){
        this.context = context;
        this.mList = list;
    }

    class CustomViewHolder extends  RecyclerView.ViewHolder{
        protected TextView name;
        protected ImageView img;

        public CustomViewHolder(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.name_group_member);
            this.img = (ImageView) view.findViewById(R.id.image_group_member);

        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listviewitem_group_member,null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.name.setText(mList.get(position).getName());
        //viewholder.img.setImageDrawable(mList.get(position).getD());
    }
    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
}
