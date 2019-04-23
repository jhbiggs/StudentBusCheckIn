package com.studentbuscheck_in;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class listAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
//        if (convertView == null) {
//            convertView = getLayoutInflater().inflate(R.layout.list_item, viewGroup, false);
//        }
//
//        ((TextView) convertView.findViewById(R.id.student_name_list_item))
//                .setText(getItem(position));
        return null;
    }
}
