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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


public class TaskSetupActivity extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    private static final String TAG = "MainActivity";
    TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_setup);

        getSupportActionBar().setTitle("Task List");

        findViewById(R.id.home_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaskSetupActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });

        //weird bug, doesn't show data on first startup
        ListView listView = (ListView) findViewById(R.id.list_task);
        adapter = new TaskAdapter(this, Data.tasks);
        //TaskAdapter adapter = new TaskAdapter(this, Data.tasks);
        listView.setAdapter(adapter);
        /*findViewById(R.id.create_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaskSetupActivity.this, TaskActivity.class);
                startActivity(intent);
            }
        });*/

        /*findViewById(R.id.task_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < Data.tasks.length; i++) {
                    Task curr = Data.tasks.get(i);
                    if (curr.getName().equals())
                }

            }
        });*/
    }

    @Override
    protected void onResume() {
        super.onResume();

        //TODO: update data
        adapter.notifyDataSetChanged();

    }

    public void completeTask(View view) {
        View parent = (View) view.getParent();
        TextView title = (TextView) parent.findViewById(R.id.task_title);
        TextView tview = (TextView) parent.findViewById(R.id.task_complete);
        Button btn = (Button) parent.findViewById(R.id.task_complete);

        //no undo button
        if (btn.getText().toString().equals("Mark as Complete")) {
            //ideally have an 'are you sure? you cannot undo this action and money will be transferred to your child_overview's account'
            btn.setText("Completed");
            btn.setEnabled(false);
            ListView listView = (ListView) findViewById(R.id.list_task);
            title.setPaintFlags(tview.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            //update status
            final int position = listView.getPositionForView(parent);
            final Task tsk = Data.tasks.get(position);
            tsk.updateStatus(2);
            try {
                db.getReference("tasks").child("" + tsk.getId()).child("status").setValue(tsk.getStatus());
            } catch (Exception e) {
                e.printStackTrace();
            }

            db.getReference("accounts").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    MyAccount child = null;
                    MyAccount parent = null;
                    for (DataSnapshot snap: dataSnapshot.getChildren()) {
                        MyAccount temp = snap.getValue(MyAccount.class);
                        if (temp.getId() == 123) {
                            parent = temp;
                        } else if (temp.getId() == 321) {
                            child = temp;
                        }
                    }
                    if (parent == null || child == null) {
                        System.out.println("Error");
                    } else {
                        parent.transferTo(child, tsk.getValue());
                        db.getReference("accounts").child("child").setValue(child);
                        db.getReference("accounts").child("parent").setValue(parent);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // ...
                }
            });
            //title.setPaintFlags(tview.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        } else {
            //don't do anything, can't undo completed
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                Log.d(TAG, "Add a new task");
                Intent intent = new Intent(TaskSetupActivity.this, TaskActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
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
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_item, parent, false);
            }
            // Lookup view for data population
            TextView title = (TextView) convertView.findViewById(R.id.task_title);
            TextView tview = (TextView) convertView.findViewById(R.id.task_complete);
            Button complete = (Button) convertView.findViewById(R.id.task_complete);

            // Populate the data into the template view using the data object
            title.setText(tsk.getName());

            if (tsk.getStatus() != 1) {
                System.out.println("This has status not one " + tsk.getName() + tsk.getStatus());
                complete.setText("Completed");
                complete.setEnabled(false);
                title.setPaintFlags(tview.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                complete.setText("Mark as Complete");
                complete.setEnabled(true);
                title.setPaintFlags(tview.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                System.out.println("This has status of one " + tsk.getName() + tsk.getStatus());
            }
            // Return the completed view to render on screen
            return convertView;
        }


    }
}
