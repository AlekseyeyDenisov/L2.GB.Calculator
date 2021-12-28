package com.dw.gbcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private final CalculatorModel calculatorModel = new CalculatorModel();

    TextView calculationResultTextView,
            clearButton,
            digitDotButton,
            digitZeroButton,
            digitOneButton,
            digitTwoButton,
            digitThreeButton,
            digitFourButton,
            digitFiveButton,
            digitSixButton,
            digitSevenButton,
            digitEightButton,
            digitNineButton,
            goTuNewResultButton;
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
        calculationResultTextView = findViewById(R.id.calculation_result_text_view);
        clearButton = findViewById(R.id.clear_button);
        stepBackButton = findViewById(R.id.step_back_button);
        digitDotButton = findViewById(R.id.digit_dot_button);
        digitZeroButton = findViewById(R.id.digit_zero_button);
        digitOneButton = findViewById(R.id.digit_one_button);
        digitTwoButton = findViewById(R.id.digit_two_button);
        digitThreeButton = findViewById(R.id.digit_three_button);
        digitFourButton = findViewById(R.id.digit_four_button);
        digitFiveButton = findViewById(R.id.digit_five_button);
        digitSixButton = findViewById(R.id.digit_six_button);
        digitSevenButton = findViewById(R.id.digit_seven_button);
        digitEightButton = findViewById(R.id.digit_eight_button);
        digitNineButton = findViewById(R.id.digit_nine_button);
        goTuNewResultButton = findViewById(R.id.go_to_new_activity_button);
    }

    private void clickButtonNumber() {
        digitDotButton.setOnClickListener(v -> resultOutput(digitDotButton));
        digitZeroButton.setOnClickListener(v -> resultOutput(digitZeroButton));
        digitOneButton.setOnClickListener(v -> resultOutput(digitOneButton));
        digitTwoButton.setOnClickListener(v -> resultOutput(digitTwoButton));
        digitThreeButton.setOnClickListener(v -> resultOutput(digitThreeButton));
        digitFourButton.setOnClickListener(v -> resultOutput(digitFourButton));
        digitFiveButton.setOnClickListener(v -> resultOutput(digitFiveButton));
        digitSixButton.setOnClickListener(v -> resultOutput(digitSixButton));
        digitSevenButton.setOnClickListener(v -> resultOutput(digitSevenButton));
        digitEightButton.setOnClickListener(v -> resultOutput(digitEightButton));
        digitNineButton.setOnClickListener(v -> resultOutput(digitNineButton));
        goTuNewResultButton.setOnClickListener(v -> goToActivityResult());
    }

    private void goToActivityResult() {
        Intent intent = new Intent(this,ResultInfoActivity.class);
        intent.putExtra(
                ResultInfoActivity.CONSTANT_INTENT_RESULT_ACTIVITY, calculatorModel.getInputArithmeticExpression()
        );
        startActivity(intent);
    }


    private void resultOutput(TextView button) {
        String btnValue = (String) button.getText();
        if (calculatorModel.checkEnteredData(btnValue)) {
            calculatorModel.setInputArithmeticExpression(btnValue);
            calculationResultTextView.setText(calculatorModel.getInputArithmeticExpression());
        }

    }

    private void operationsCalc() {
        clearButton.setOnClickListener(v -> {
            calculatorModel.clear();
            resetInput();
        });
        stepBackButton.setOnClickListener(v -> {
            calculationResultTextView.setText(calculatorModel.backSpace());
            resetInput();
        });
    }

    private void resetInput() {
        if (calculatorModel.getInputArithmeticExpression().length() == 0)
            calculationResultTextView.setText(getString(R.string.initial_text));
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVE_RESULT, calculatorModel.getInputArithmeticExpression());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculatorModel.setInputArithmeticExpression(savedInstanceState.getString(SAVE_RESULT));
        calculationResultTextView.setText(savedInstanceState.getString(SAVE_RESULT));
    }
}