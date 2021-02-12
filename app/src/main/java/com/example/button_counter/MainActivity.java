package com.example.button_counter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "MainActivity";    // used as an identifier for android.util.Log

    private Button buttonInc;
    private Button buttonDec;
    private Button buttonRes;
    private int counter = 0;
    private TextView counterView;

    private int doge_chance;
    private TextView doge;
    private Random rng;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        rng = new Random();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterView = findViewById(R.id.counterView);
        counterView.setText(String.valueOf(counter));
        buttonInc = findViewById(R.id.buttonInc);
        buttonInc.setOnClickListener(this);
        buttonDec = findViewById(R.id.buttonDec);
        buttonDec.setOnClickListener(this);
        buttonRes = findViewById(R.id.buttonRes);
        buttonRes.setOnClickListener(this);

        doge_chance = 10 + rng.nextInt(7);
        doge = findViewById(R.id.doge);
        constraintLayout = findViewById(R.id.parent_layout);

    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "onClick() triggered");
        int id = v.getId();

        if(id == R.id.buttonInc) {
            counter++;
            doge_chance--;
        }
        else if(id == R.id.buttonDec) {
            if (counter > 0) counter--;
        }
        else if(id == R.id.buttonRes) {
            counter = 0;
        }

        counterView.setText(String.valueOf(counter));
        if(doge_chance <= 0) {
            triggerDoge();
        }
        else {
            doge.setText("");
        }
    }

    private void triggerDoge(){
        String[] dogeArray = getResources().getStringArray(R.array.doge);
        doge.setRotation(rng.nextInt(100) - 50);

        doge.setText(dogeArray[rng.nextInt(dogeArray.length)]);
        ConstraintSet newSet = new ConstraintSet();
        newSet.clone(constraintLayout);
        newSet.setHorizontalBias(doge.getId(), (float) (0.1 + rng.nextFloat() * 0.8));
        newSet.setVerticalBias(doge.getId(), (float) (0.6 + rng.nextFloat() * 0.3));
        newSet.applyTo(constraintLayout);
        doge_chance = 10 + rng.nextInt(7);
    }




    /* Below are all of the Android lifecycles.
     * They get triggered on certain Android OS events, such as switching
     *   back and forth between different apps, or closing the app.
     */

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart() triggered");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume() triggered");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause() triggered");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop() triggered");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart triggered");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy() triggered");
    }

    /* end lifecycle code */


}