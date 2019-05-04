package com.studentbuscheck_in;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity  {
    //set up class variables
    protected static BusCheckInDB myDatabase;
    private ArrayList<Student> students;
    private EditText teacherNameEditText;
    private EditText busDriverNameEditText;
    private EditText tripNameEditText;
    private String teacherName = "x";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up database on async thread
        new PopulateListAsyncTask(this).execute();

        //find the view elements
        final Button noButton = findViewById(R.id.noButton);
        final Button yesButton = findViewById(R.id.yesButton);
        teacherNameEditText = findViewById(R.id.teacher_name_edit_text);
        busDriverNameEditText = findViewById(R.id.bus_driver_edit_text);

        teacherName = teacherNameEditText.getText().toString();
        /* Todo: make persistent the main activity information */



        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(noButton.getContext(), ListItemActivity.class);
                startActivity(intent);
            }
        });

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(yesButton.getContext(), HasListActivity.class);
                startActivity(intent);

            }
        });

        PreferenceManager.setDefaultValues(this, R.xml.pref_general, false);

    }

    @Override
    protected void onResume() {
        super.onResume();
        teacherNameEditText.setText(teacherName);
        Log.d("onResume", teacherName);
//        ArrayList<Student> students;
//        students = activityDB.getStudents();

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.bus_check_in, menu);
        return true;

    }



    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_settings:
                startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
                return true;
            case R.id.menu_about:
                Toast.makeText(this,"copyright mindframe 2019",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




}
