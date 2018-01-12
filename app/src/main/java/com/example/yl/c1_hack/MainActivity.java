package com.example.yl.c1_hack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    //private static final String TAG = "MainActivity";
    //FirebaseDatabase db = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Parent Overview"); //hi what's up

        findViewById(R.id.task_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, TaskSetupActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.rewards_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RewardsActivity.class);
                startActivity(intent);
            }
        });

        /*DatabaseReference ref = db.getReference("tasks");
        final List<Task> data = new ArrayList<>();

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap: dataSnapshot.getChildren()) {
                    Task tsk = snap.getValue(Task.class);
                    data.add(tsk);
                }
                Data.changeTasks(data);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap: dataSnapshot.getChildren()) {
                    Task tsk = snap.getValue(Task.class);
                    data.add(tsk);
                }
                Data.changeTasks(data);
            }

            @Override
            public void onCancelled(DatabaseError error) { }
        });
          ref.addChildEventListener(new ChildEventListener() {
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());
                Task temp = dataSnapshot.getValue(Task.class);
                data.add(temp);
                Data.changeTasks(data);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    }
}
