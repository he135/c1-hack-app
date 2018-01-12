package com.example.yl.c1_hack;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Child_List extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    TaskSetupActivity.TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_tasklists);

        getSupportActionBar().setTitle("Task List");

        ListView listView = (ListView) findViewById(R.id.list_task);
        TaskAdapter adapter = new TaskAdapter(this, Data.tasks);
        listView.setAdapter(adapter);
        //weird bug, doesn't show data on first startup
        DatabaseReference ref = db.getReference("tasks");
        final List<Task> data = new ArrayList<>();
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {
                Task temp = snapshot.getValue(Task.class);
                if(temp.getStatus()<2){
                    data.add(temp);
                }
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
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        TaskAdapter adapter = new TaskAdapter(this, Data.tasks);
        ListView listView = (ListView) findViewById(R.id.list_task);
        listView.setAdapter(adapter);
    }

    public class TaskAdapter extends ArrayAdapter<Task> {
        public TaskAdapter(Context context, List<Task> tasks) {
            super(context, 0, tasks);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Task tsk = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.child_task_item, parent, false);
            }
            // Lookup view for data population
            TextView title = (TextView) convertView.findViewById(R.id.task_title);
            //TextView val = (TextView) convertView.findViewById(R.id.task_value);
            // Populate the data into the template view using the data object
            title.setText(tsk.getName());
            //val.setText("" + tsk.getValue());
            // Return the completed view to render on screen
            return convertView;
        }


    }
}
