package com.example.yl.c1_hack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        getSupportActionBar().setTitle("Add/Edit Task");

        //limit value to 2 decimals
        EditText val = findViewById(R.id.taskValue);
        val.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(5,2)});

        findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ((EditText) findViewById(R.id.taskName)).getText().toString();
                String descript = ((EditText) findViewById(R.id.taskDescription)).getText().toString();
                String value = ((EditText) findViewById(R.id.taskValue)).getText().toString();

                //require fields to be filled out
                if (name.matches("") || descript.matches("") || value.matches("")) {
                    Toast.makeText(TaskActivity.this, "Please fill out all fields.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Task item = new Task(name, descript, Double.parseDouble(value));

                //adding to Firebase
                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference ref = db.getReference("tasks").child("" + item.getId());
                ref.setValue(item);

                Data.tasks.add(item);

                //return Task
                Intent intent = new Intent(TaskActivity.this, TaskSetupActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaskActivity.this, TaskSetupActivity.class);
                startActivity(intent);
            }
        });
    }


    //Limit value to 2 decimal places https://stackoverflow.com/questions/5357455/limit-decimal-places-in-android-edittext

    public class DecimalDigitsInputFilter implements InputFilter {
        Pattern mPattern;
        public DecimalDigitsInputFilter(int digitsBeforeZero,int digitsAfterZero) {
            mPattern= Pattern.compile("[0-9]{0," + (digitsBeforeZero-1) + "}+((\\.[0-9]{0," + (digitsAfterZero-1) + "})?)||(\\.)?");
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Matcher matcher=mPattern.matcher(dest);
            if(!matcher.matches())
                return "";
            return null;
        }
    }


}
