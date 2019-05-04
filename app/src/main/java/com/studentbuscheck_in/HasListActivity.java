package com.studentbuscheck_in;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class HasListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.have_list_view);
        setTitle(R.string.haveListTitleLabel);

    }
}
