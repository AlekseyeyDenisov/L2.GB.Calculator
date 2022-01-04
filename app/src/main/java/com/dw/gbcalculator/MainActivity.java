package com.dw.gbcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.dw.gbcalculator.domain.CalculatorModel;


public class MainActivity extends AppCompatActivity {
    private final CalculatorModel calculatorModel = new CalculatorModel();

    TextView calculationResultTextView,
            operationMinus,
            operationPlus,
            operationDivision,
            operationEquals,
            operationMultiply,
            operationPercent,

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

    ImageButton stepBack;
    private static final String SAVE_RESULT = "save_result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        clickButtonNumber();

    }

    private void initViews() {
        calculationResultTextView = findViewById(R.id.calculation_result_text_view);
        clearButton = findViewById(R.id.clear_button);
        stepBack = findViewById(R.id.back_space_button);
        digitDotButton = findViewById(R.id.digit_dot_button);

        operationMinus = findViewById(R.id.operation_minus_button);
        operationPlus = findViewById(R.id.operation_plus_button);
        operationDivision = findViewById(R.id.operation_division_button);
        operationEquals = findViewById(R.id.operation_equals_button);
        operationMultiply = findViewById(R.id.operation_multiply_button);
        operationPercent = findViewById(R.id.operation_percent_button);


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
        digitDotButton.setOnClickListener(v -> updateInput(InputSymbol.DOT));
        digitZeroButton.setOnClickListener(v -> updateInput(InputSymbol.NUM_0));
        digitOneButton.setOnClickListener(v -> updateInput(InputSymbol.NUM_1));
        digitTwoButton.setOnClickListener(v -> updateInput(InputSymbol.NUM_2));
        digitThreeButton.setOnClickListener(v -> updateInput(InputSymbol.NUM_3));
        digitFourButton.setOnClickListener(v -> updateInput(InputSymbol.NUM_4));
        digitFiveButton.setOnClickListener(v -> updateInput(InputSymbol.NUM_5));
        digitSixButton.setOnClickListener(v -> updateInput(InputSymbol.NUM_6));
        digitSevenButton.setOnClickListener(v -> updateInput(InputSymbol.NUM_7));
        digitEightButton.setOnClickListener(v -> updateInput(InputSymbol.NUM_8));
        digitNineButton.setOnClickListener(v -> updateInput(InputSymbol.NUM_9));

        operationMinus.setOnClickListener(v -> updateInput(InputSymbol.OP_MINUS));
        operationPlus.setOnClickListener(v -> updateInput(InputSymbol.OP_PLUS));
        operationDivision.setOnClickListener(v -> updateInput(InputSymbol.OP_DIVISION));
        operationMultiply.setOnClickListener(v -> updateInput(InputSymbol.OP_MULTIPLY));
        operationPercent.setOnClickListener(v -> updateInput(InputSymbol.OP_PRESENT));

        operationEquals.setOnClickListener(v -> updateInput(InputSymbol.OP_EQUALS));

        clearButton.setOnClickListener(v -> updateInput(InputSymbol.CLEAR));
        stepBack.setOnClickListener(v -> updateInput(InputSymbol.STEP_BACK));

        goTuNewResultButton.setOnClickListener(v -> goToActivityResult());

    }

    private void updateInput(InputSymbol inputSymbol) {
        calculatorModel.onClickButton(inputSymbol);
        calculationResultTextView.setText(
                calculatorModel.convertInputSymbolsToString(calculatorModel.getInput(), this)
        );
    }

    private void goToActivityResult() {
        String resultText = calculationResultTextView.getText().toString();
        Log.d("@@@","TextView "+ resultText);
        Intent intent = new Intent(this, ResultInfoActivity.class);
        intent.putExtra(
                ResultInfoActivity.CONSTANT_INTENT_RESULT_ACTIVITY, resultText
        );
        startActivity(intent);
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVE_RESULT, calculationResultTextView.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculationResultTextView.setText(savedInstanceState.getString(SAVE_RESULT));
    }
}