package com.example.prescribed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.prescribed.addMed.Medication;
import com.example.prescribed.addMed.medRecord;

import java.util.ArrayList;
import java.util.List;

public class MedicationActivity extends AppCompatActivity {

    private static final String TAG = "MedicationActivity";
    
    private List<String> images;
    private List<String> medNames;
    private List<String> timeText;
    private List<String> notesText;
    private medRecord medRec = new medRecord();
    private Intent intent;
    private String timeOfDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medications);

        images = new ArrayList<>();
        medNames = new ArrayList<>();
        timeText = new ArrayList<>();
        notesText = new ArrayList<>();

        intent = getIntent();
        timeOfDay = intent.getExtras().getString("timeOfDay");

        if (timeOfDay.equals("morning")) {
            TextView tv = findViewById(R.id.med_title);
            tv.setText("MORNING PILLS");
        }
        else if (timeOfDay.equals("day")) {
            TextView tv1 = findViewById(R.id.med_title);
            tv1.setText("DAY PILLS");
        }
        else if (timeOfDay.equals("evening")){
            TextView tv2 = findViewById(R.id.med_title);
            tv2.setText("EVENING PILLS");
        }
        else {
            TextView tv3 = findViewById(R.id.med_title);
            tv3.setText("NIGHT PILLS");
        }


        initMedBitmap();
    }

    private List<Medication> getList(){
        if (timeOfDay.equals("morning"))
            return medRec.getMorningMeds();
        else if (timeOfDay.equals("day"))
            return medRec.getDayMeds();
        else if (timeOfDay.equals("evening"))
            return medRec.getEveningMeds();
        return medRec.getNightMeds();
    }

    private void initMedBitmap(){
        List<Medication> medList = getList();

        medNames.clear();
        timeText.clear();
        notesText.clear();

        for(Medication med : medList){
            medNames.add(med.getName());

            int min = med.getDate().getMinutes();
            String minutes = min < 10? "0"+ min : ""+min;
            timeText.add("" + med.getDate().getHours() + ":" + minutes);

            notesText.add(med.getNotes());
        }

        initRecyclerView();
    }

    private void initRecyclerView(){

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(images, medNames, timeText, notesText,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}