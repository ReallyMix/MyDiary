package com.example.mydiary;


import static com.example.mydiary.MainActivity.Day;
import static com.example.mydiary.MainActivity.dbManager;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class AddAffairActivity extends AppCompatActivity {


    public EditText EditTitle, EditDisc;
    public Button save, delete;
    public static String TText = "";
    public static String DText = "";
    public static int idOfItem;
    ImageButton backToAffairs;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_affair);
        getSupportActionBar().hide();
        backToAffairs = findViewById(R.id.backToAffairs);
        EditTitle = findViewById(R.id.title);
        EditDisc = findViewById(R.id.subtitle);
        save = findViewById(R.id.saveButton);
        delete = findViewById(R.id.deleteButton);
        EditTitle.setText(TText);
        EditDisc.setText(DText);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!TText.equals("") && !EditTitle.getText().toString().equals("")) {

                    dbManager.update(EditTitle.getText().toString(), EditDisc.getText().toString(), idOfItem);

                    TText = "";
                    DText = "";


                    Intent intent = new Intent(AddAffairActivity.this, MainActivity.class);
                    startActivity(intent);

                }

                else if (EditTitle.getText().toString().equals("")) {


                    Toast.makeText(getApplicationContext(), "Введите название",Toast.LENGTH_SHORT).show();


                }

                else {

                    dbManager.insert(EditTitle.getText().toString(), EditDisc.getText().toString(), Day);



                    Intent intent = new Intent(AddAffairActivity.this, MainActivity.class);
                    startActivity(intent);

                }

            }
        });


        backToAffairs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AddAffairActivity.this, MainActivity.class);
                startActivity(intent);

                TText = "";
                DText = "";
            }
        });



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!TText.equals("")) {

                    dbManager.delete(idOfItem);

                    TText = "";
                    DText = "";

                    Intent intent = new Intent(AddAffairActivity.this, MainActivity.class);
                    startActivity(intent);

                }

                else if (EditTitle.getText().toString().equals("") && EditDisc.getText().toString().equals("")) {

                    Toast.makeText(getApplicationContext(), "Нет данных для удаления",Toast.LENGTH_SHORT).show();
                }

                else {

                    Intent intent = new Intent(AddAffairActivity.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        });

    }
}