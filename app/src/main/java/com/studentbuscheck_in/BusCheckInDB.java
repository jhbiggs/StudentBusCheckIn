package com.studentbuscheck_in;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class BusCheckInDB {
    //set up the constants for a check-in list of students for SQLite
    public static final String DB_NAME = "bus_list.db";
    public static final int DB_VERSION = 1;

    //set up the lists table for multiple field trip lists
    public static final String LIST_TABLE = "lists";

    public static final String LIST_ID = "_id";
    public static final int LIST_ID_COLUMN = 0;

    public static final String LIST_NAME = "list_name";
    public static final int LIST_NAME_COLUMN = 1;

    //set up the students table
    public static final String STUDENT_TABLE = "students";

    public static final String STUDENT_ID = "_id";
    public static final int STUDENT_ID_COLUMN = 0;

    public static final String STUDENT_NAME_FIRST_LAST = "student_name_last_first";
    public static final int STUDENT_NAME_LAST_FIRST_COLUMN = 1;

    public static final String STUDENT_SCHOOL_ID = "student_school_id_number";
    public static final int STUDENT_SCHOOL_ID_COLUMN = 2;

    public static final String STUDENT_IS_PRESENT = "student_is_present";
    public static final int STUDENT_IS_PRESENT_COLUMN = 3;

    public static final String STUDENT_LIST_ID = "list_id";
    public static final int    STUDENT_LIST_ID_COL = 4;

    private static int currentVersion;

    //create students table
    public static final String CREATE_STUDENT_TABLE = "CREATE TABLE " + STUDENT_TABLE + " ("
            + STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + STUDENT_NAME_FIRST_LAST +
            " TEXT, " + STUDENT_SCHOOL_ID + " INTEGER, " + STUDENT_IS_PRESENT +
             " TEXT, " + STUDENT_LIST_ID + " INTEGER);";

    //create lists table
    public static final String CREATE_LIST_TABLE =
        "CREATE TABLE " + LIST_TABLE + " (" +
                LIST_ID   + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                LIST_NAME + " TEXT);";

    public static final String DROP_STUDENT_TABLE = "DROP TABLE IF EXISTS " + STUDENT_TABLE + ";";
    public static final String DROP_LIST_TABLE = "DROP TABLE IF EXISTS " + LIST_TABLE + ";";


    private static void setCurrentVersion(int version) {
        BusCheckInDB.currentVersion = version;
    }

    public int getCurrentVersion () {
        return currentVersion;
    }
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper (Context context,  String name,
                         SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            BusCheckInDB.setCurrentVersion(version);
        }



        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {


            //enter new data tables

            sqLiteDatabase.execSQL(CREATE_LIST_TABLE);

            sqLiteDatabase.execSQL(CREATE_STUDENT_TABLE);



        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

            Log.d("Student Table", "Upgrading db from version "
                    + oldVersion + " to " + newVersion);

            sqLiteDatabase.execSQL(DROP_LIST_TABLE);
            sqLiteDatabase.execSQL(DROP_STUDENT_TABLE);
            onCreate(sqLiteDatabase);


        }

    }
    public void upgradeDatabase (Context context, int oldVersion, int newVersion ){
        openWritableDatabase();

        dbHelper.onUpgrade(db, oldVersion, newVersion);

        closeDatabase();


    }
    public ArrayList<Student> getStudents() {

        ArrayList<Student> students = new ArrayList<>();
        this.openReadableDatabase();
        Cursor cursor = db.query(STUDENT_TABLE, null, null, null,
                null, null, null);

        while (cursor.moveToNext()) {
            students.add(getStudentFromCursor(cursor));
        }
        if (cursor != null) {
            cursor.close();
        }
        this.closeDatabase();

        return students;
    }
    public BusCheckInDB (Context context) {

        //sets the current version
        dbHelper = new DBHelper(context, DB_NAME, null, 1);

    }



    public List getList(String name) {
        String where = LIST_NAME + "= ?";
        String[] whereArgs = { name };

        openReadableDatabase();
        Cursor cursor = db.query(LIST_TABLE, null,
                where, whereArgs, null, null, null);
        cursor.moveToFirst();
        List list = new List(cursor.getInt(LIST_ID_COLUMN),
                cursor.getString(LIST_NAME_COLUMN));
        if (cursor != null)
            cursor.close();
        this.closeDatabase();

        return list;
    }

    private Student getStudentFromCursor (Cursor cursor) {
        //if there's no cursor, then don't bother with the rest of the method
        if (cursor == null) {
            return null;
        } else {
            try {
                return new Student (
                        cursor.getInt(STUDENT_ID_COLUMN),
                        cursor.getString(STUDENT_SCHOOL_ID_COLUMN),
                        cursor.getString(STUDENT_NAME_LAST_FIRST_COLUMN),
                        cursor.getString(STUDENT_IS_PRESENT_COLUMN),
                        cursor.getLong(STUDENT_LIST_ID_COL));


            }
            catch (Exception e) {
                return null;
            }
        }
    }

    private void openReadableDatabase () {
        db = dbHelper.getReadableDatabase();
    }

    private void openWritableDatabase () {
        db = dbHelper.getWritableDatabase();
    }

    private void closeDatabase () {
        if (db != null) {
            db.close();
        }
    }


    public long insertStudent(Student student) {
        ContentValues cv = new ContentValues();
//        cv.put(LIST_ID, student.getListId());
        cv.put(STUDENT_NAME_FIRST_LAST, student.getStudentFirstLast());
        cv.put(STUDENT_IS_PRESENT, student.getIsPresent());
        cv.put(STUDENT_SCHOOL_ID, student.getStudentSchoolId());
        cv.put(STUDENT_LIST_ID, student.getListId());

        this.openWritableDatabase();
        long rowID = db.insert(STUDENT_TABLE, null, cv);
        this.closeDatabase();

        return rowID;
    }

    public int updateStudent(Student student) {
        ContentValues cv = new ContentValues();
        cv.put(STUDENT_LIST_ID, student.getListId());
        cv.put(STUDENT_NAME_FIRST_LAST, student.getStudentFirstLast());
        cv.put(STUDENT_IS_PRESENT, student.getIsPresent());
        cv.put(STUDENT_SCHOOL_ID, student.getStudentSchoolId());

        String where = STUDENT_ID + "= ?";
        String[] whereArgs = { String.valueOf(student.getStudentId()) };

        this.openWritableDatabase();
        int rowCount = db.update(STUDENT_TABLE, cv, where, whereArgs);
        this.closeDatabase();

        return rowCount;
    }

    public int deleteStudent(long id) {
        String where = STUDENT_ID + "= ?";
        String[] whereArgs = { String.valueOf(id) };

        this.openWritableDatabase();
        int rowCount = db.delete(STUDENT_TABLE, where, whereArgs);
        this.closeDatabase();

        return rowCount;
    }

    public long insertList (ArrayList<Student> list, String listName) {


        ContentValues cv = new ContentValues();
        cv.put(LIST_NAME, listName);
        this.openWritableDatabase();
        long listId = db.insert(LIST_TABLE, null, cv);
        this.closeDatabase();

        for ( Student student : list) {

            ContentValues studentCV = new ContentValues();
            studentCV.put(STUDENT_LIST_ID, listId);
            studentCV.put(STUDENT_NAME_FIRST_LAST, student.getStudentFirstLast());
            studentCV.put(STUDENT_IS_PRESENT, student.getIsPresent());
            studentCV.put(STUDENT_SCHOOL_ID, student.getStudentSchoolId());
            this.openWritableDatabase();
            long rowId = db.insert(STUDENT_TABLE, null, studentCV);


        }

        return listId;
    }

   public long deleteList (long id) {
        String where = LIST_ID + " = ?";
        String[] whereArgs = { String.valueOf(id) } ;

        this.openWritableDatabase();
        int listCount = db.delete(LIST_TABLE, where, whereArgs);

        this.closeDatabase();
        return listCount;

   }

}
