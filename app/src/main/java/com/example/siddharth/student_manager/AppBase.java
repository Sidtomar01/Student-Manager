package com.example.siddharth.student_manager;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class AppBase extends AppCompatActivity {

    ArrayList<String> basicFields;
    gridAdapter adapter;
    public static ArrayList<String> divisions ;
    GridView gridView;
    public static databaseHandler handler;
    TextView txtview;
    public static Activity activity;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mai_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base1_layout);
        basicFields = new ArrayList<>();
        handler = new databaseHandler(this);
        activity = this;


        divisions = new ArrayList();
        divisions.add(" COMPUTER SCIENCE");
        divisions.add(" INFORMATION TECHNOLOGY");
        divisions.add(" ELECTRICAL ENGINEERING");
        divisions.add(" ELECTRONICS AND COMMUNICATION ENGINEERING");
        divisions.add(" MECHANICAL ENGINEERING");
        divisions.add(" CIVIL ENGINEERING");

        gridView = (GridView) findViewById(R.id.grid);
        txtview= (TextView) findViewById(R.id.textgrid);
        basicFields.add("ATTENDANCE");
        basicFields.add("SCHEDULER");
        basicFields.add("NOTES");
        basicFields.add("PROFILE");
      //  basicFields.add("CGPA CALCULATOR");
        adapter = new gridAdapter(this,basicFields);
        gridView.setAdapter(adapter);
    }

    public void loadSettings(MenuItem item) {
        Intent launchIntent = new Intent(this,SettingsActivity.class);
        startActivity(launchIntent);
    }

    public void loadAbout(MenuItem item) {
        Intent launchIntent = new Intent(this,About.class);
        startActivity(launchIntent);
    }
}
