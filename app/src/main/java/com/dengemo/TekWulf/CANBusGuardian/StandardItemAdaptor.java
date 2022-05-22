package com.dengemo.TekWulf.CANBusGuardian;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

public class StandardItemAdaptor extends BaseAdapter {
    private LinkedList<StandardItem> data;
    private Context context;

    public StandardItemAdaptor(LinkedList<StandardItem> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_standard, parent, false);
        //选项的标签名
        TextView name = convertView.findViewById(R.id.item_standard_label);
        name.setText(data.get(position).getName());
        //选项的图标
        ImageView icon = convertView.findViewById(R.id.item_standard_icon);
        icon.setBackgroundResource(StandardItem.RIGHT_ICON);
        //选项的摘要，空则删掉
        TextView brief = convertView.findViewById(R.id.item_standard_brief);
        if (null == data.get(position).getBrief()) {
            ViewGroup group = (ViewGroup) brief.getParent();
            group.removeView(brief);
        } else brief.setText(data.get(position).getBrief());
        //选项的详情描述
        TextView desc = convertView.findViewById(R.id.item_standard_desc);
        if (null == data.get(position).getDesc()) {
            ViewGroup group = (ViewGroup) desc.getParent();
            group.removeView(desc);
        } else desc.setText(data.get(position).getDesc());

        return convertView;
    }

    public class ViewHolder{
        TextView tv1;
        TextView tv2;
        TextView tv3;
        ImageView iv1;
    }

}
