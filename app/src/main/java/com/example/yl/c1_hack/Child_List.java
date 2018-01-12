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

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class Child_List extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    TaskSetupActivity.TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_tasklists);

        getSupportActionBar().setTitle("Task List");

        //weird bug, doesn't show data on first startup
        /*DatabaseReference ref = db.getReference("tasks");
        final List<Task> data = new ArrayList<>();

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    Task tsk = snap.getValue(Task.class);
                    data.add(tsk);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
        List<Task> data = new ArrayList<>();
        for (Task t : Data.tasks) {
            if (t.getStatus() == 1) {
                data.add(t);
            }
        }

        ListView listView = (ListView) findViewById(R.id.list_task);
        TaskAdapter adapter = new TaskAdapter(this, data);
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
