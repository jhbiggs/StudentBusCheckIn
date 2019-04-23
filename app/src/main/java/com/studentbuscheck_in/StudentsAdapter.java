package com.studentbuscheck_in;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;


public class StudentsAdapter extends ArrayAdapter<Student> {
    public StudentsAdapter(Context context, ArrayList<Student> students) {
        super(context, 0, students);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the data for this position
        Student student = getItem(position);

        //check if the view is being used, otherwise inflate it.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        //look up view for data population
        TextView studentName = (TextView) convertView.findViewById(R.id.student_name_list_item);
        TextView studentSchoolId = (TextView) convertView.findViewById(R.id.student_school_id_list_item);
        CheckBox studentIsPresent = (CheckBox) convertView.findViewById(R.id.is_present_checkbox_list_item);

        studentName.setText(student.getStudentFirstLast());
        studentSchoolId.setText(student.getStudentSchoolId());
        studentIsPresent.setChecked(true);
        return convertView;
    }
}
