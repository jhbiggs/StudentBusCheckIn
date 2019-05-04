package com.studentbuscheck_in;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class ListItemActivity extends Activity implements EditText.OnEditorActionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new ListItemFragment()).commit();


    }



    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

        return false;
    }
}
