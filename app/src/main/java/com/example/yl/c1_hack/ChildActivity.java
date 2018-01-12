package com.example.yl.c1_hack;

/**
 * Created by Andrew on 1/11/18.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ChildActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_overview);
        getSupportActionBar().setTitle("Child Overview"); //hi what's up
        findViewById(R.id.Child_AR).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Change to Child_AR page
                Intent intent = new Intent(ChildActivity.this, TaskSetupActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.Child_Account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Change to Child_Account page
                Intent intent = new Intent(ChildActivity.this, ChildAccountActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.Child_Task).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChildActivity.this, Child_List.class);
                startActivity(intent);
            }
        });
    }
}
