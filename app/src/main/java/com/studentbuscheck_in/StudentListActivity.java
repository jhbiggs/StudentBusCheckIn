package com.studentbuscheck_in;

import android.app.Activity;
import android.content.Intent;
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

//import static com.studentbuscheck_in.MainActivity.sampleList;

public class StudentListActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get the main view
        setContentView(R.layout.activity_student_list);
        setTitle("Student List:");
        //get the list view for the student list
       ListView listView = findViewById(R.id.itemsListView);
        //start a StudentsAdapter to populate the view with data.  SampleList is an array
        // of students
//        StudentsAdapter studentsAdapter = new StudentsAdapter(this, sampleList);
//        listView.setAdapter(studentsAdapter);


    }


    @Override
    public void onClick(View view) {

        Intent intent = new Intent(this, ListItemActivity.class);
        startActivity(intent);
    }
}
