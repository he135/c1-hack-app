package com.example.yl.c1_hack;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Child_List extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
//    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_tasklists);

        getSupportActionBar().setTitle("Task List");

        //weird bug, doesn't show data on first startup
        DatabaseReference ref = db.getReference("tasks");
        final List<Task> data = new ArrayList<>();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    Task tsk = snap.getValue(Task.class);
                    if (tsk.getStatus() < 2) {
                        data.add(tsk);
                    }
                }
                Data.changeTasks(data);
            }

            @Override
            public void onCancelled(DatabaseError error) {
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

//    public void completeTask(View view) {
//        View parent = (View) view.getParent();
//        TextView title = (TextView) parent.findViewById(R.id.task_title);
//        TextView tview = (TextView) parent.findViewById(R.id.task_complete);
//        Button btn = (Button) parent.findViewById(R.id.task_complete);
//
//        //no undo button
//        if (btn.getText().toString().equals("Mark as Complete")) {
//            //ideally have an 'are you sure? you cannot undo this action and money will be transferred to your child_overview's account'
//            btn.setText("Completed");
//            btn.setEnabled(false);
//            ListView listView = (ListView) findViewById(R.id.list_task);
//            title.setPaintFlags(tview.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//
//            //update status
//            final int position = listView.getPositionForView(parent);
//            Task tsk = Data.tasks.get(position);
//            tsk.updateStatus(2);
//            try {
//                db.getReference("tasks").child("" + tsk.getId()).child("status").setValue(tsk.getStatus());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            //title.setPaintFlags(tview.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
//        } else {
//            //don't do anything, can't undo completed
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_add_task:
//                Log.d(TAG, "Add a new task");
//                Intent intent = new Intent(Child_List.this, ChildActivity.class);
//                startActivity(intent);
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

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
