package com.example.small_gongjeon;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FriendRequestAdapter extends RecyclerView.Adapter<FriendRequestAdapter.CustomViewHolder_request> {


    private ArrayList<Friend> mList = null;
    private Activity context = null;


    public FriendRequestAdapter(Activity context, ArrayList<Friend> list) {
        this.context = context;
        this.mList = list;
    }

    class CustomViewHolder_request extends RecyclerView.ViewHolder {
        protected TextView name;
//        protected ImageView imageView;


        public CustomViewHolder_request(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.name_friend_request);
//            this.imageView = (ImageView) view.findViewById(R.id.image_friend_request);
        }
    }


    @Override
    public CustomViewHolder_request onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listviewitem_friend_request,null);
        CustomViewHolder_request viewHolder = new CustomViewHolder_request(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendRequestAdapter.CustomViewHolder_request viewholder, int position) {

        viewholder.name.setText(mList.get(position).getName());
//        viewholder.imageView.setImageDrawable(mList.get(position).getD());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
}
