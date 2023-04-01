package com.example.mydiary;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydiary.db.DBManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    public TextView textViewDate, withoutAffairs;
    public static TextView affairs;
    public static ListView workList;
    FloatingActionButton add;
    public static DBManager dbManager;
    ImageView schedule;
    ImageButton BackToPage;
    public static Calendar thisDay = new GregorianCalendar();
    public static String Day;
    String[] shorterDates = new String[] {"Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс",};

    @Override
    public void onBackPressed () {

        Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
        startActivity(intent);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        textViewDate = findViewById(R.id.textViewDate);
        affairs = findViewById(R.id.textView);
        add = findViewById(R.id.add);
        workList = findViewById(R.id.workList);
        BackToPage = findViewById(R.id.BackToPage);
        withoutAffairs = findViewById(R.id.withoutAffairs);
        schedule = findViewById(R.id.schedule);


        if (thisDay.get(Calendar.DAY_OF_WEEK)!=1) {

            String date = shorterDates[thisDay.get(Calendar.DAY_OF_WEEK)-2] + ", " + thisDay.get(Calendar.DAY_OF_MONTH) + " " + DateAdapter.months[thisDay.get(Calendar.MONTH)];
            textViewDate.setText(date);
        }

        else {

            String date = shorterDates[6] + ", " + thisDay.get(Calendar.DAY_OF_MONTH) + " " + DateAdapter.months[thisDay.get(Calendar.MONTH)];
            textViewDate.setText(date);
        }




        dbManager = new DBManager(this);

        BackToPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MainActivity.this, AddAffairActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();


        dbManager.openDB();


        Day = String.valueOf(thisDay.get(Calendar.DAY_OF_MONTH)) + String.valueOf(thisDay.get(Calendar.MONTH)) + String.valueOf(thisDay.get(Calendar.YEAR));

        if (dbManager.getTitleFromDB(Day).size()!=0) {

            schedule.setVisibility(View.GONE);
            withoutAffairs.setVisibility(View.GONE);
        }

        MainAdapter adapter = new MainAdapter(this, dbManager.getTitleFromDB(Day));
        workList.setAdapter(adapter);



        workList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                AddAffairActivity.TText = dbManager.getTitleFromDB(Day).get(i);
                AddAffairActivity.DText = dbManager.getDiscFromDB(Day).get(i);
                AddAffairActivity.idOfItem = dbManager.getListOfId(Day).get(i);


                Intent intent = new Intent(MainActivity.this, AddAffairActivity.class);
                startActivity(intent);


            }
        });



    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        dbManager.closeDB();
    }


}