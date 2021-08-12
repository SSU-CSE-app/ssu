package com.example.small_gongjeon;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SpinnerGroupInfoList extends BaseAdapter {
    private static final int ITEM_SPINNER = 0;
    private static final int ITEM_TITLE = 1;
    private static final int TYPE_COUNT = 2;

    ArrayList<SpinnerGroupInfo> arrayList = new ArrayList<>();

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int i) {
        return arrayList.get(i).getType();
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int viewType = getItemViewType(i) ;
        Context c = viewGroup.getContext();
        if(view == null){
            LayoutInflater li = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            SpinnerGroupInfo s = arrayList.get(i);

            switch (viewType){
                case ITEM_SPINNER:
                    view = li.inflate(R.layout.spinner_groupinfo, viewGroup, false);
                    ImageView iv = (ImageView)view.findViewById(R.id.image_spinner_group);
                    TextView tv1 = (TextView)view.findViewById(R.id.spinner_name_group);

                    iv.setImageDrawable(s.getD());
                    tv1.setText(s.getName());
                    break;
                case ITEM_TITLE:
                    view = li.inflate(R.layout.spinner_title, viewGroup, false);
                    TextView tv2 = (TextView)view.findViewById(R.id.spinner_name_group);
                    tv2.setText(s.getName());
                    break;
            }
        }

        return view;
    }

    public void addSpinnerGroup(Drawable d, String name){
        SpinnerGroupInfo s = new SpinnerGroupInfo();

        s.setType(ITEM_SPINNER);
        s.setD(d);
        s.setName(name);

        arrayList.add(s);
    }
    public void addSpinnerGroup(String name){
        SpinnerGroupInfo s = new SpinnerGroupInfo();

        s.setType(ITEM_TITLE);
        s.setName(name);

        arrayList.add(s);
    }
}
