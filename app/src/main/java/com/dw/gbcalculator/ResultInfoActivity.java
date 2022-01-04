package com.dw.gbcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultInfoActivity extends AppCompatActivity {
    public static final String CONSTANT_INTENT_RESULT_ACTIVITY = "constant_intent_result_activity";
    TextView resultCalcInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_info);

        resultCalcInfo = findViewById(R.id.result_calc_info);


        final Intent intent = getIntent();
        if (intent != null && intent.hasExtra(CONSTANT_INTENT_RESULT_ACTIVITY)) {
            String text = intent.getExtras().getString(CONSTANT_INTENT_RESULT_ACTIVITY);
            resultCalcInfo.setText(text);
        }





    }
}