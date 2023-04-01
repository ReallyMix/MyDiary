package com.example.mydiary;


import static com.example.mydiary.DateAdapter.week;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class CalendarActivity extends AppCompatActivity {

    ListView ListOfDates;
    ImageButton toPast, toNext;
    public static String[] Dates = new String[] {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"};

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        getSupportActionBar().hide();


        ListOfDates = findViewById(R.id.ListOfDates);
        toPast = findViewById(R.id.toPast);
        toNext = findViewById(R.id.toFur);
        DateAdapter adapter = new DateAdapter(this, Dates);
        ListOfDates.setAdapter(adapter);

        ListOfDates.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Calendar today = new GregorianCalendar();

                if (today.get(Calendar.DAY_OF_WEEK)!=1) {

                    if (i+2!=today.get(Calendar.DAY_OF_WEEK)) {

                        today.add(Calendar.DAY_OF_MONTH, i+2-today.get(Calendar.DAY_OF_WEEK)+week*7);
                        MainActivity.thisDay = today;
                    }

                    else if(week!=0){

                        today.add(Calendar.DAY_OF_MONTH, week*7);
                        MainActivity.thisDay = today;
                    }

                    else {

                        MainActivity.thisDay = today;
                    }
                }

                else if (i+2>today.get(Calendar.DAY_OF_WEEK)) {

                    today.add(Calendar.DAY_OF_MONTH, i - 6 +week * 7);
                    MainActivity.thisDay = today;
                }

                else if (week!=0){

                    today.add(Calendar.DAY_OF_MONTH, week*7);
                    MainActivity.thisDay = today;
                }

                else {

                    MainActivity.thisDay = today;
                }


                Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
                startActivity(intent);


            }
        });

        toPast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                week -=1;
                adapter.notifyDataSetChanged();

            }
        });

        toNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                week +=1;
                adapter.notifyDataSetChanged();
            }
        });


    }
}