package com.example.mydiary;

import static com.example.mydiary.MainActivity.Day;
import static com.example.mydiary.MainActivity.dbManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.List;

public class MainAdapter extends ArrayAdapter<String> {

    public MainAdapter(Context context, List<String> title) {
        super(context, R.layout.adapter_item, title);
    }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.adapter_item, parent, false);
            TextView affair = view.findViewById(R.id.affair);
            TextView disc = view.findViewById(R.id.disc);
            CheckBox checkBox = view.findViewById(R.id.checkBox);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (checkBox.isChecked()) {
                        dbManager.complete(dbManager.getListOfId(Day).get(position));
                    }
                    else {
                        dbManager.decomplete(dbManager.getListOfId(Day).get(position));
                    }
                }
            });

                affair.setText(dbManager.getTitleFromDB(Day).get(position));
                disc.setText(dbManager.getDiscFromDB(Day).get(position));

                if (dbManager.getIsCompletedFromDB(Day).get(position) == 1){
                    checkBox.setChecked(true);
                }
            return view;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }
}
