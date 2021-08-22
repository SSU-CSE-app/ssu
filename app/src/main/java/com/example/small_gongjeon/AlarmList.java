//package com.example.small_gongjeon;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.Switch;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//public class AlarmList extends BaseAdapter {
//    ArrayList<Alarm> alarms = new ArrayList<>();
//    private static final int ITEM_GROUP = 0;
//    private static final int ITEM_INDIVIDUAL = 1;
//    private static final int ITEM_GROUPINFO = 2;
//    private static final int TYPE_COUNT = 3;
//    ImageView img;
//    ImageButton btn;
//    Switch s;
//
//
//    @Override
//    public int getCount() {
//        return alarms.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return alarms.get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return i;
//    }
//
//    @Override
//    public int getViewTypeCount() {
//        return TYPE_COUNT;
//    }
//
//    @Override
//    public int getItemViewType(int i) {
//        return alarms.get(i).getType();
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        int viewType = getItemViewType(i) ;
//        Context c = viewGroup.getContext();
//        if(view == null){
//            LayoutInflater li = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            Alarm g = alarms.get(i);
//            switch (viewType){
//                case ITEM_GROUP:
//                    view = li.inflate(R.layout.listviewitem_alarm_group, viewGroup, false);
//                    img = (ImageView)view.findViewById(R.id.image_alarm_group);
//                    TextView tv1 = (TextView)view.findViewById(R.id.alarm_time);
//                    TextView tv2 = (TextView)view.findViewById(R.id.alarm_group_name);
//
//                    tv1.setText(g.getTime());
//                    tv2.setText(g.getName());
//                    break;
//                case ITEM_INDIVIDUAL:
//                    view = li.inflate(R.layout.listviewitem_alarm_individual, viewGroup, false);
//                    img = (ImageView)view.findViewById(R.id.image_alarm_individual);
//                    TextView tv3 = (TextView)view.findViewById(R.id.alarm_time);
//
//                    tv3.setText(g.getTime());
//                    btn = (ImageButton)view.findViewById(R.id.btn_delete_alarm);
//                    s = (Switch)view.findViewById(R.id.switch_alarm);
//                    break;
//                case ITEM_GROUPINFO:
//                    view = li.inflate(R.layout.listviewitem_alarm_group_info, viewGroup, false);
//                    img = (ImageView)view.findViewById(R.id.image_alarm_group);
//                    TextView tv4 = (TextView)view.findViewById(R.id.alarm_time);
////                    TextView tv5 = (TextView)view.findViewById(R.id.number_groupinfo_member);
//                    btn = (ImageButton)view.findViewById(R.id.btn_delete_alarm);
//                    s = (Switch)view.findViewById(R.id.switch_alarm);
//
//                    tv4.setText(g.getTime());
////                    tv5.setText(""+g.getMember());
//                    break;
//            }
//        }
//        return view;
//    }
//
//
//    public void addAlarm(String time, String name){
//        Alarm g = new Alarm();
//
//        g.setType(ITEM_GROUP);
//        g.setTime(time);
//        g.setName(name);
//
//        alarms.add(g);
//    }
//
//    public void addAlarm(String time){
//        Alarm g = new Alarm();
//
//        g.setType(ITEM_INDIVIDUAL);
//        g.setTime(time);
//
//        alarms.add(g);
//    }
//    public void addAlarm(int member,String time){
//        Alarm g = new Alarm();
//
//        g.setType(ITEM_GROUPINFO);
////        g.setMember(member);
//        g.setTime(time);
//
//        alarms.add(g);
//    }
//}