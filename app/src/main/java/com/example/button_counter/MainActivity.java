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
    private ConstraintLayout constraintLayout;

    private Random rng;

    private TextView doge;
    private int doge_chance;
    private int doge_rot;
    private float doge_off_hz;
    private float doge_off_vt;

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
        ConstraintSet newSet = new ConstraintSet();
        String[] dogeArray = getResources().getStringArray(R.array.doge);
        doge.setText(dogeArray[rng.nextInt(dogeArray.length)]);

        doge_rot = rng.nextInt(100) - 50;
        doge.setRotation(doge_rot);

        newSet.clone(constraintLayout);
        doge_off_hz = (float) (0.1 + rng.nextFloat() * 0.8);
        doge_off_vt = (float) (0.6 + rng.nextFloat() * 0.3);
        newSet.setHorizontalBias(doge.getId(), doge_off_hz);
        newSet.setVerticalBias(doge.getId(), doge_off_vt);
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count", counter);

        outState.putInt("doge_chance", doge_chance);
        outState.putInt("doge_rot", doge_rot);
        outState.putFloat("doge_off_hz", doge_off_hz);
        outState.putFloat("doge_off_vt", doge_off_vt);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        counter = savedInstanceState.getInt("count");

        doge_chance = savedInstanceState.getInt("doge_chance");
        doge_rot = savedInstanceState.getInt("doge_rot");
        doge_off_hz = savedInstanceState.getFloat("doge_off_hz");
        doge_off_vt = savedInstanceState.getFloat("doge_off_vt");

        ConstraintSet newSet = new ConstraintSet();
        doge.setRotation(doge_rot);
        newSet.clone(constraintLayout);
        newSet.setHorizontalBias(doge.getId(), doge_off_hz);
        newSet.setVerticalBias(doge.getId(), doge_off_vt);
        newSet.applyTo(constraintLayout);

    }
    /* end lifecycle code */


}