package com.example.yl.c1_hack;

//import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;


public class RewardsActivity extends AppCompatActivity {
    private static double end_reward;
    private static double begin_reward = 0;
    private boolean saved = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);
        getSupportActionBar().setTitle("Set Reward");
        final TextView amount = (TextView) findViewById(R.id.amount);
        amount.setText(Double.toString(end_reward));
        begin_reward = end_reward;
        final EditText e = findViewById(R.id.amount);
        findViewById(R.id.plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = e.getText().toString();
                end_reward = Double.parseDouble(content);
                end_reward++;
                amount.setText(Double.toString(end_reward));
            }
        });
        findViewById(R.id.amount).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    String content = e.getText().toString();
                    end_reward = Double.parseDouble(content);
                    e.setImeOptions(EditorInfo.IME_ACTION_DONE);
                }
            }
        });
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saved = true;
            }
        });
        findViewById(R.id.minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (end_reward > 0) {
                    String content = e.getText().toString();
                    end_reward = Double.parseDouble(content);
                    end_reward--;
                    amount.setText(Double.toString(end_reward));
                }
            }
        });
    }
    @Override
    protected void onStop(){
        super.onStop();
        if(!saved){
            end_reward = begin_reward;
        }
    }
}
