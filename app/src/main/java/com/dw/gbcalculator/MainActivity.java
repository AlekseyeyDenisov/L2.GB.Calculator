package com.dw.gbcalculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private final CalcOperation calcOperation = new CalcOperation();
    TextView resultOutput;
    Button btnC, dot, zero, one, two, three, four, five, six, seven, eight, nine;
    ImageButton stepBack;
    private static final String SAVE_RESULT = "save_result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        clickButtonNumber();
        operationsCalc();


    }

    private void initView() {
        resultOutput = findViewById(R.id.result_input);
        btnC = findViewById(R.id.clear_output_button);
        stepBack = findViewById(R.id.step_back_button);
        dot = findViewById(R.id.digit_dot_button);
        zero = findViewById(R.id.digit_zero_button);
        one = findViewById(R.id.digit_one_button);
        two = findViewById(R.id.digit_two_button);
        three = findViewById(R.id.digit_three_button);
        four = findViewById(R.id.digit_four_button);
        five = findViewById(R.id.digit_five_button);
        six = findViewById(R.id.digit_six_button);
        seven = findViewById(R.id.digit_seven_button);
        eight = findViewById(R.id.digit_eight_button);
        nine = findViewById(R.id.digit_nine_button);
    }

    private void clickButtonNumber() {
        dot.setOnClickListener(v -> resultOutput(dot));
        zero.setOnClickListener(v -> resultOutput(zero));
        one.setOnClickListener(v -> resultOutput(one));
        two.setOnClickListener(v -> resultOutput(two));
        three.setOnClickListener(v -> resultOutput(three));
        four.setOnClickListener(v -> resultOutput(four));
        five.setOnClickListener(v -> resultOutput(five));
        six.setOnClickListener(v -> resultOutput(six));
        seven.setOnClickListener(v -> resultOutput(seven));
        eight.setOnClickListener(v -> resultOutput(eight));
        nine.setOnClickListener(v -> resultOutput(nine));
    }


    private void resultOutput(Button button) {
        String btnValue = (String) button.getText();
        if (calcOperation.checkButton(btnValue)) {
            calcOperation.setTempResult(btnValue);
            resultOutput.setText(calcOperation.getTempResult());
        }

    }

    private void operationsCalc() {
        btnC.setOnClickListener(v -> {
            calcOperation.clear();
            resetInput();
        });
        stepBack.setOnClickListener(v -> {
            resultOutput.setText(calcOperation.stepBack());
            resetInput();
        });
    }

    private void resetInput() {
        if (calcOperation.getTempResult().length() == 0)
            resultOutput.setText(getString(R.string.initial_text));
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVE_RESULT, calcOperation.getTempResult());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calcOperation.setTempResult(savedInstanceState.getString(SAVE_RESULT));
    }
}