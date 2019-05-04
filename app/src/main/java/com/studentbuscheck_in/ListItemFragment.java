package com.studentbuscheck_in;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.MainThread;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;

import static com.studentbuscheck_in.MainActivity.myDatabase;

public class ListItemFragment extends Fragment implements View.OnClickListener {

    private Button nextButton;
    private Button doneButton;
    private EditText studentNameEditText;
    private EditText schoolIdEditText;
    private CheckBox isPresentCheckBox;
    private String firstLast;
    private String schoolId;
    private String isPresent;
    private ArrayList<Student> students;
//    private int studentId;



    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.student_name_entry_fragment, container, false);

        //get the views
        nextButton = v.findViewById(R.id.next_button_list_item);
        doneButton = v.findViewById(R.id.done_button_list_item);
        studentNameEditText = v.findViewById(R.id.student_name_list_item);
        schoolIdEditText = v.findViewById(R.id.student_school_id_list_item);
        isPresentCheckBox = v.findViewById(R.id.is_present_checkbox_list_item);

        //set the listeners
        doneButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        //array list for list database
        students = new ArrayList<>();


        return v;

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.next_button_list_item:

                Log.d("NextButtton", "clicked");

                //get the student info
                firstLast = studentNameEditText.getText().toString();
                schoolId = schoolIdEditText.getText().toString();
                if (isPresentCheckBox.isChecked()) {
                    isPresent = "1";
                } else {
                    isPresent = "0";
                }

                //create a Student for the database
                Student s = new Student(schoolId, firstLast, isPresent);

                students.add(s);
                Log.d("Student entered", firstLast + schoolId + isPresent);
//                db.insertStudent(s);



                studentNameEditText.setText("");
                schoolIdEditText.setText("");
                isPresentCheckBox.setChecked(false);


                break;
            case R.id.done_button_list_item:
//                Log.d("DoneButton", "clicked");
//                Log.d("ArrayList", students.get(0).getStudentFirstLast());
                myDatabase.insertList(students, "newList");
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                break;
        }

    }
}
