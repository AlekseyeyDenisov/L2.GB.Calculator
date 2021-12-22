package com.dw.gbcalculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    TextView resultInput;
    Button btnC, dot, zero, one, two, three, four, five, six, seven, eight, nine;
    ImageButton stepBack;
    String tempResult = "";
    private static final String SAVE_RESULT = "save_result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        clickButtonNumber();
        operationsCalc();


    }

    private void operationsCalc() {
        btnC.setOnClickListener(v -> {
            tempResult = "";
            resetInput();
        });
        stepBack.setOnClickListener(v -> {
            if (tempResult.length() != 0) {
                tempResult = tempResult.substring(0, tempResult.length() - 1);
                resultInput.setText(tempResult);
            }
            resetInput();
        });
    }

    private void resetInput() {
        if (tempResult.length() == 0)
            resultInput.setText(getString(R.string.initial_text));
    }

    private void clickButtonNumber() {
        dot.setOnClickListener(v -> inputView(dot));
        zero.setOnClickListener(v -> inputView(zero));
        one.setOnClickListener(v -> inputView(one));
        two.setOnClickListener(v -> inputView(two));
        three.setOnClickListener(v -> inputView(three));
        four.setOnClickListener(v -> inputView(four));
        five.setOnClickListener(v -> inputView(five));
        six.setOnClickListener(v -> inputView(six));
        seven.setOnClickListener(v -> inputView(seven));
        eight.setOnClickListener(v -> inputView(eight));
        nine.setOnClickListener(v -> inputView(nine));
    }

    private void inputView(Button currentBtn) {
        String btnValue = (String) currentBtn.getText();
        if (!tempResult.equals(btnValue) & dotCheck(btnValue)) {
            tempResult += currentBtn.getText();
            resultInput.setText(tempResult);
        }

    }

    private boolean dotCheck(String btnValue) {
        String dotValue = (String) dot.getText();
        return !(btnValue.equals(dotValue) & tempResult.contains(dotValue)) &&
                !(btnValue.equals(dotValue) & tempResult.length() == 0);
    }

    private void initView() {
        resultInput = findViewById(R.id.result_input);
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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVE_RESULT, tempResult);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tempResult = savedInstanceState.getString(SAVE_RESULT);
    }
}