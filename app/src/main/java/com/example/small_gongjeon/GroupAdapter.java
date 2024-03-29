package com.example.small_gongjeon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.CustomViewHolder>{
    private ArrayList<Group> mList = null;
    private Activity context = null;
    private OnItemClickListener mListener = null;

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

    public GroupAdapter(Activity context, ArrayList<Group> list){
        this.context = context;
        this.mList = list;
    }

    class CustomViewHolder extends  RecyclerView.ViewHolder{
        protected TextView name;
        protected TextView number;
        protected ImageView img;
        protected ImageView img2;
        protected  ImageView groupInfoImage;

        public CustomViewHolder(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.name_group);
            this.number = (TextView) view.findViewById(R.id.number_group_member);
            this.img = (ImageView) view.findViewById(R.id.image_group); // 그룹 이미지
            this.img2 = (ImageView) view.findViewById(R.id.image_number_group_member);
            this.groupInfoImage = (ImageView) view.findViewById(R.id.add_group_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView groupname_temp = (TextView) view.findViewById(R.id.name_group);
                    Main.currGroup = (String) groupname_temp.getText();
                    Group3_GroupInfo.Photo = img.getDrawable();
                    int position = getAbsoluteAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        if(mListener != null){
                            mListener.onItemClick(view,position);
                        }
                    }
                }
            });

        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listviewitem_group,viewGroup,false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.name.setText(mList.get(position).getName());
        viewholder.number.setText("" + mList.get(position).getNumber());
        viewholder.img.setImageResource(mList.get(position).getPhoto());
        System.out.println("photo : "+mList.get(position).getPhoto());
    }
    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
}
