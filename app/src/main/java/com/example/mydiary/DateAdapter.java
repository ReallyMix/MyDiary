package com.example.mydiary;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.Calendar;
import java.util.GregorianCalendar;



import static com.example.mydiary.CalendarActivity.Dates;
import static com.example.mydiary.MainActivity.dbManager;

public class DateAdapter extends ArrayAdapter<String> {

    public static String[] months = new String[] {"января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря"};
    public static int week = 0;


    public DateAdapter(Context context, String[] AmountOfDays) {
        super(context, R.layout.datelist_adapter_item, AmountOfDays);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.datelist_adapter_item, parent, false);
        TextView MyDate = view.findViewById(R.id.MainDate);
        TextView Task = view.findViewById(R.id.AmountOfTasks);
        TextView CTasks = view.findViewById(R.id.AmountOfCTasks);

        Calendar today = new GregorianCalendar();

        if (today.get(Calendar.DAY_OF_WEEK)!=1) {

            if (position+2!=today.get(Calendar.DAY_OF_WEEK)) {
                today.add(Calendar.DAY_OF_MONTH, position+2-today.get(Calendar.DAY_OF_WEEK)+week*7);

                MyDate.setText(Dates[position] + "\n" + today.get(Calendar.DAY_OF_MONTH) + " " + months[today.get(Calendar.MONTH)] + ", " + today.get(Calendar.YEAR));

            }

            else if(week!=0){
                today.add(Calendar.DAY_OF_MONTH, week*7);
                MyDate.setText(Dates[position] + "\n" + today.get(Calendar.DAY_OF_MONTH) + " " + months[today.get(Calendar.MONTH)] + ", " + today.get(Calendar.YEAR));
            }

            else {

                MyDate.setText("Сегодня, " + Dates[position] + "\n" + today.get(Calendar.DAY_OF_MONTH) + " " + months[today.get(Calendar.MONTH)] + ", " + today.get(Calendar.YEAR));
            }
        }

        else if (position+2>=today.get(Calendar.DAY_OF_WEEK) & position!=6) {

            today.add(Calendar.DAY_OF_MONTH, position - 6 + week*7);

            MyDate.setText(Dates[position] + "\n" + today.get(Calendar.DAY_OF_MONTH) + " " + months[today.get(Calendar.MONTH)] + ", " + today.get(Calendar.YEAR));

        }

        else if(week!=0){
            today.add(Calendar.DAY_OF_MONTH, week*7);
            MyDate.setText(Dates[position] + "\n" + today.get(Calendar.DAY_OF_MONTH) + " " + months[today.get(Calendar.MONTH)] + ", " + today.get(Calendar.YEAR));
        }

        else {

            MyDate.setText("Сегодня, " + Dates[position] + "\n" + today.get(Calendar.DAY_OF_MONTH) + " " + months[today.get(Calendar.MONTH)] + ", " + today.get(Calendar.YEAR));
            }

        String dayToString = String.valueOf(today.get(Calendar.DAY_OF_MONTH)) + String.valueOf(today.get(Calendar.MONTH)) + String.valueOf(today.get(Calendar.YEAR));

        int completed = 0;
        for (int i = 0; i<dbManager.getIsCompletedFromDB(dayToString).size(); i++) {

            if (dbManager.getIsCompletedFromDB(dayToString).get(i)==1)   {
                completed+=1;
            }
        }

        if (dbManager.getIsCompletedFromDB(dayToString).size()==0) {
            Task.setText("Нет задач");
            CTasks.setVisibility(View.GONE);
        }

        else if (completed==dbManager.getIsCompletedFromDB(dayToString).size()) {

            Task.append(String.valueOf(dbManager.getTitleFromDB(dayToString).size()));
            CTasks.setVisibility(View.VISIBLE);
            CTasks.setText("Все задачи выполнены!");
        }

        else {
            Task.append(String.valueOf(dbManager.getTitleFromDB(dayToString).size()));
            CTasks.setVisibility(View.VISIBLE);
            CTasks.append(String.valueOf(completed));
        }

        return view;

    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

}
