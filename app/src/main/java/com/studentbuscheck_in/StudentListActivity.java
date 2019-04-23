package com.studentbuscheck_in;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import static com.studentbuscheck_in.MainActivity.sampleList;

public class StudentListActivity extends Activity {
    private String[] myStringArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        myStringArray = new String[]{"bob", "chuck", "jenny"};
        ListView listView = findViewById(R.id.itemsListView);
        View itemView = findViewById(R.id.list_item);
//        ArrayAdapter<Student> adapter = new ArrayAdapter<>(this,
//                R.layout.list_item, sampleList);
//        listView.setAdapter(adapter);
        StudentsAdapter studentsAdapter = new StudentsAdapter(this, sampleList);
        listView.setAdapter(studentsAdapter);


    }


}
