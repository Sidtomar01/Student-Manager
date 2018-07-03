package com.example.siddharth.student_manager;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;

import info.hoang8f.widget.FButton;

public class make_schedule extends AppCompatActivity {

   Spinner  classSelect,daySelect;
    ArrayAdapter adapterSpinner, days;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sheduler_create);

        classSelect = (Spinner) findViewById(R.id.classSelector);
        daySelect = (Spinner) findViewById(R.id.daySelector);

        adapterSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, AppBase.divisions);
        assert classSelect != null;
        classSelect.setAdapter(adapterSpinner);

        ArrayList<String> weekdays = new ArrayList<>();
        weekdays.add("MONDAY");
        weekdays.add("TUESDAY");
        weekdays.add("WEDNESDAY");
        weekdays.add("THURSDAY");
        weekdays.add("FRIDAY");
        weekdays.add("SATURDAY");
        weekdays.add("SUNDAY");
        days = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, weekdays);
        assert classSelect != null;
        daySelect.setAdapter(days);

        FButton btn = (FButton) findViewById(R.id.saveBUTTON_SCHEDULE);
        assert btn != null;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSchedule(v);
            }
        });

    }


    private void saveSchedule(View v) {
        String daySelected = daySelect.getSelectedItem().toString();
        String classSelected = classSelect.getSelectedItem().toString();
        MaterialEditText editText = (MaterialEditText) findViewById(R.id.idclassSelector);
        String subject = editText.getText().toString();
        if(subject.length()<2)
        {
            Toast.makeText(getBaseContext(),"Enter Valid Subject Name",Toast.LENGTH_SHORT).show();
            return;
        }
        TimePicker timePicker = (TimePicker)findViewById(R.id.timePicker);
        int hour = timePicker.getCurrentHour();
        int min = timePicker.getCurrentMinute();

        String sql = "INSERT INTO SCHEDULE VALUES('" + classSelected +"'," +
                "'" + subject + "'," +
                "'" + hour +":"+min +"'," +
                "'" + daySelected + "');";
        Log.d("Schedule", sql);
        if(AppBase.handler.execAction(sql))
        {
            Toast.makeText(getBaseContext(),"Scheduling Done", Toast.LENGTH_LONG).show();
            this.finish();
        }else
            Toast.makeText(getBaseContext(),"Failed To Schedule", Toast.LENGTH_LONG).show();

    }
}
