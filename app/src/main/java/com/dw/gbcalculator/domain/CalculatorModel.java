package com.dw.gbcalculator.domain;


import android.content.Context;
import android.util.Log;

import com.dw.gbcalculator.InputSymbol;
import com.dw.gbcalculator.R;
import com.dw.gbcalculator.domain.states.BaseState;
import com.dw.gbcalculator.domain.states.InitState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CalculatorModel implements Serializable {
    private BaseState currentState = new InitState();


    public void onClickButton(InputSymbol inputSymbol) {
        BaseState newState = currentState.onClickButton(inputSymbol);
        Log.d("@@@","Old State " + currentState.getClass().getSimpleName());
        Log.d("@@@","Input symbol " + inputSymbol.name());
        Log.d("@@@","NewState " + newState.getClass().getSimpleName());
        Log.d("@@@","result " + currentState.getInput());
        Log.d("@@@","FirstNegativeNumber " + BaseState.getFirstNegativeNumber());
        Log.d("@@@","----------------");

        currentState = newState;

    }


    public List<InputSymbol> getInput() {
        return new ArrayList<>(currentState.getInput());
    }

    public String convertInputSymbolsToString(List<InputSymbol> inputSymbols, Context context) {
        StringBuilder sb = new StringBuilder();
        for (InputSymbol inputSymbol : inputSymbols) {
            switch (inputSymbol) {
                case NUM_0:
                    sb.append(context.getString(R.string.zero));
                    break;
                case NUM_1:
                    sb.append(context.getString(R.string.one));
                    break;
                case NUM_2:
                    sb.append(context.getString(R.string.two));
                    break;
                case NUM_3:
                    sb.append(context.getString(R.string.three));
                    break;
                case NUM_4:
                    sb.append(context.getString(R.string.four));
                    break;
                case NUM_5:
                    sb.append(context.getString(R.string.five));
                    break;
                case NUM_6:
                    sb.append(context.getString(R.string.six));
                    break;
                case NUM_7:
                    sb.append(context.getString(R.string.seven));
                    break;
                case NUM_8:
                    sb.append(context.getString(R.string.eight));
                    break;
                case NUM_9:
                    sb.append(context.getString(R.string.nine));
                    break;
                case DOT:
                    sb.append(context.getString(R.string.dot));
                    break;
                case CLEAR:
                    sb.append(context.getString(R.string.clear));
                    break;
                case OP_DIVISION:
                    sb.append(context.getString(R.string.divide));
                    break;
                case OP_MINUS:
                    sb.append(context.getString(R.string.minus));
                    break;
                case OP_MULTIPLY:
                    sb.append(context.getString(R.string.multiply));
                    break;
                case OP_PLUS:
                    sb.append(context.getString(R.string.plus));
                    break;
                case OP_PRESENT:
                    sb.append(context.getString(R.string.percent));
                    break;
                case OP_EQUALS: {
                    sb.append(context.getString(R.string.equals));
                    sb.append(UtilCalculate.resultOfMathematicalSolution(inputSymbols));
                    break;
                }
                default:
                    sb.append("@");
                    break;
            }
        }
        return sb.toString();
    }

}
