package com.example.prescribed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.prescribed.R;
import com.example.prescribed.chat.ChatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this, R.id.fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

    }


        public void onClick(View view){

            switch(view.getId()) {
                case R.id.button_chat:
                    Intent intent = new Intent();
                    intent.setClassName("com.example.prescribed", "com.example.prescribed.chat.ChatActivity");
                    startActivity(intent);
                    break;
                case R.id.add_button:
                    Intent intent1 = new Intent();
                    intent1.setClassName("com.example.prescribed", "com.example.prescribed.addMed.AddMedActivity");
                    startActivity(intent1);
                    break;
                case R.id.morning_button:
                    Intent intent2 = new Intent();
                    intent2.setClassName("com.example.prescribed", "com.example.prescribed.MedicationActivity");
                    intent2.putExtra("timeOfDay", "morning");
                    startActivity(intent2);
                    break;
                case R.id.day_button:
                    Intent intent3 = new Intent();
                    intent3.setClassName("com.example.prescribed", "com.example.prescribed.MedicationActivity");
                    intent3.putExtra("timeOfDay", "day");
                    startActivity(intent3);
                    break;
                case R.id.evening_button:
                    Intent intent4 = new Intent();
                    intent4.setClassName("com.example.prescribed", "com.example.prescribed.MedicationActivity");
                    intent4.putExtra("timeOfDay", "evening");
                    startActivity(intent4);
                    break;
                case R.id.night_button:
                    Intent intent5 = new Intent();
                    intent5.setClassName("com.example.prescribed", "com.example.prescribed.MedicationActivity");
                    intent5.putExtra("timeOfDay", "night");
                    startActivity(intent5);
                    break;
            }
        }



}