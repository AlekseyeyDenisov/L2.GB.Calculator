package com.dw.gbcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private final CalcOperation calcOperation = new CalcOperation();

    TextView resultOutput,
            clearButton,
            dotButton,
            zeroButton,
            oneButton,
            twoButton,
            threeButton,
            fourButton,
            fiveButton,
            sixButton,
            sevenButton,
            eightButton,
            nineButton,
            goTuResultButton;
    ImageButton stepBackButton;
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
        clearButton = findViewById(R.id.clear_output_button);
        stepBackButton = findViewById(R.id.step_back_button);
        dotButton = findViewById(R.id.digit_dot_button);
        zeroButton = findViewById(R.id.digit_zero_button);
        oneButton = findViewById(R.id.digit_one_button);
        twoButton = findViewById(R.id.digit_two_button);
        threeButton = findViewById(R.id.digit_three_button);
        fourButton = findViewById(R.id.digit_four_button);
        fiveButton = findViewById(R.id.digit_five_button);
        sixButton = findViewById(R.id.digit_six_button);
        sevenButton = findViewById(R.id.digit_seven_button);
        eightButton = findViewById(R.id.digit_eight_button);
        nineButton = findViewById(R.id.digit_nine_button);
        goTuResultButton = findViewById(R.id.go_to_new_activity_button);
    }

    private void clickButtonNumber() {
        dotButton.setOnClickListener(v -> resultOutput(dotButton));
        zeroButton.setOnClickListener(v -> resultOutput(zeroButton));
        oneButton.setOnClickListener(v -> resultOutput(oneButton));
        twoButton.setOnClickListener(v -> resultOutput(twoButton));
        threeButton.setOnClickListener(v -> resultOutput(threeButton));
        fourButton.setOnClickListener(v -> resultOutput(fourButton));
        fiveButton.setOnClickListener(v -> resultOutput(fiveButton));
        sixButton.setOnClickListener(v -> resultOutput(sixButton));
        sevenButton.setOnClickListener(v -> resultOutput(sevenButton));
        eightButton.setOnClickListener(v -> resultOutput(eightButton));
        nineButton.setOnClickListener(v -> resultOutput(nineButton));
        goTuResultButton.setOnClickListener(v -> goToActivityResult());
    }

    private void goToActivityResult() {
        Intent intent = new Intent(this,ResultInfoActivity.class);
        intent.putExtra(
                ResultInfoActivity.CONSTANT_INTENT_RESULT_ACTIVITY,calcOperation.getTempResult()
        );
        startActivity(intent);
    }


    private void resultOutput(TextView button) {
        String btnValue = (String) button.getText();
        if (calcOperation.checkButton(btnValue)) {
            calcOperation.setTempResult(btnValue);
            resultOutput.setText(calcOperation.getTempResult());
        }

    }

    private void operationsCalc() {
        clearButton.setOnClickListener(v -> {
            calcOperation.clear();
            resetInput();
        });
        stepBackButton.setOnClickListener(v -> {
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
        resultOutput.setText(savedInstanceState.getString(SAVE_RESULT));
    }
}