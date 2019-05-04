package com.studentbuscheck_in;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import static com.studentbuscheck_in.MainActivity.myDatabase;

public class PopulateListAsyncTask extends AsyncTask {
    private BusCheckInDB activityDB;
    private Context mainContext;
    private ArrayList<Student> students;

    public PopulateListAsyncTask(Context context) {
        this.mainContext = context;
    }
    public PopulateListAsyncTask(Context context, ArrayList<Student> students) {
        this.mainContext = context;
        this.students = students;
    }
    @Override
    protected Object doInBackground(Object[] objects) {
        /* TODO: grab a list from somewhere and populate it into the sqlite database */
        Log.d("PopulateListAsyncTask", "doing in Background");

        myDatabase = new BusCheckInDB(mainContext);

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Log.d("onPostExecute", "I'M DONE");
        int currentVersion = myDatabase.getCurrentVersion();
        myDatabase.upgradeDatabase(mainContext, currentVersion, currentVersion + 1 );

    }
}
