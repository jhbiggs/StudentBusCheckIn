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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity  {

    private BusCheckInDB activityDB;
    protected static ArrayList<Student> sampleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button noButton = findViewById(R.id.noButton);
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(noButton.getContext(), StudentListActivity.class);
                startActivity(intent);
            }
        });

        final Button yesButton = findViewById(R.id.yesButton);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(yesButton.getContext(), HasListActivity.class);
                startActivity(intent);

            }
        });
// long studentSchoolId, String studentFirstLast,
//                    String isPresent)

        activityDB = new BusCheckInDB(this);
//        activityDB.upgradeDatabase(this, 1, 2);

        sampleList = new ArrayList<>();
        Student student1 = new Student("3456", "Joe Bloggs", "0");
        Student student2 = new Student("4567", "Joe Bloggers", "1");
        Student student3 = new Student("5678", "Joe Bloggitts", "1");
        Student student4 = new Student("6789", "Josephine Bloggs", "1");
        Student student5 = new Student("7890", "JoeRai Bloggington", "1");
        sampleList.add(student1);
        sampleList.add(student2);
        sampleList.add(student3);
        sampleList.add(student4);
        sampleList.add(student5);
//        long sampleDBList = activityDB.insertList(sampleList, "Sample_List");
//        Log.d("Table added:", "ID: " + sampleDBList);

        // set the default values for the preferences
        PreferenceManager.setDefaultValues(this, R.xml.pref_general, false);



        // get default SharedPreferences object

    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Student> students;
        students = activityDB.getStudents();

        for (Student s: students) {

            StringBuilder sb = new StringBuilder();
            sb.append("The student name is: " + s.getStudentFirstLast() + "\n");
            sb.append("hash code is: " + s.hashCode() + ".  The school ID was "
                    + s.getStudentSchoolId()+".");


        }
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
                Toast.makeText(this,"About",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




}
