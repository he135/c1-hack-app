package com.example.yl.c1_hack;

/**
 * Created by Andrew on 1/11/18.
 */
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ChildActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        getSupportActionBar().setTitle("Overview"); //hi what's up
//
//        findViewById(R.id.task_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(ChildActivity.this, TaskSetupActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        findViewById(R.id.rewards_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ChildActivity.this, ChildActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}
