package com.example.yl.c1_hack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ChildAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_account);

        getSupportActionBar().setTitle("Account Balance");

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference ref = db.getReference("accounts");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MyAccount child = null;
                for (DataSnapshot snap: dataSnapshot.getChildren()) {
                    MyAccount temp = snap.getValue(MyAccount.class);
                    if (temp.getId() == 321) {
                        child = temp;
                    }
                }
                NumberFormat formatter = new DecimalFormat("#0.00");
                double balance = child.getBalance();
                final TextView bal = (TextView) findViewById(R.id.balance);
                bal.setText("$" + formatter.format(balance));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });
    }
}
