package com.dw.gbcalculator.domain;

import android.util.Log;

import com.dw.gbcalculator.InputSymbol;

import java.util.List;

public class UtilCalculate {

    public static String resultOfMathematicalSolution(List<InputSymbol> inputSymbols) {
        Boolean firstNegativeNumber = false;
        Float num1;
        Float num2;
        inputSymbols.remove(inputSymbols.size()-1); //удаляем =
        if (inputSymbols.get(0) == InputSymbol.OP_MINUS) {
            firstNegativeNumber = true;
            inputSymbols.remove(0);
        }
        InputSymbol operator = searchOperator(inputSymbols);
        int indexOperator = inputSymbols.indexOf(operator);

        num1 = Float.parseFloat(convertInputSymbolsToString(
                inputSymbols,
                0,
                indexOperator));
        num2 = Float.parseFloat(convertInputSymbolsToString(
                inputSymbols,
                indexOperator + 1,
                inputSymbols.size()));

        Log.d("@@@","operator " + operator);
        Log.d("@@@","index operator " + indexOperator);
        Log.d("@@@","result " + inputSymbols);
        Log.d("@@@","num1 " + num1);
        Log.d("@@@","num2 " + num2);

        float result;
        if (!firstNegativeNumber)
            result = resultCalculate(operator,num1,num2);
        else result = resultCalculateNegative(operator,num1,num2);
        String resultString;

        if(result == (int) result)
            resultString = ""+(int) result;
         else
            resultString = ""+result;

        return resultString;
    }

    private static float resultCalculateNegative(InputSymbol operator, Float num1, Float num2) {
        float result = 0f;
        switch (operator){
            case OP_DIVISION:
                result = - num1 / num2;
                break;
            case OP_MULTIPLY:
                result = - num1 * num2;
                break;
            case OP_PLUS:
                result = - num1 + num2;
                break;
            case OP_MINUS:
                result = - num1 - num2;
                break;
            case OP_PRESENT:
                result = - num2*100/num1;
                break;
            default: break;
        }

        return result;
    }

    private static Float resultCalculate(InputSymbol operator, Float num1, Float num2) {
        float result = 0f;
        switch (operator){
            case OP_DIVISION:
                result = num1 / num2;
                break;
            case OP_MULTIPLY:
                result = num1 * num2;
                break;
            case OP_PLUS:
                result = num1 + num2;
                break;
            case OP_MINUS:
                result = num1 - num2;
                break;
            case OP_PRESENT:
                result =  num2*100/num1;
                break;
            default: break;
        }
        return result;
    }

    public static String convertInputSymbolsToString(List<InputSymbol> inputSymbols, int indexStart, int indexEnd) {
        StringBuilder sb = new StringBuilder();
        for (int i = indexStart; i < indexEnd; i++) {

            switch (inputSymbols.get(i)) {
                case NUM_0:
                    sb.append("0");
                    break;
                case NUM_1:
                    sb.append("1");
                    break;
                case NUM_2:
                    sb.append("2");
                    break;
                case NUM_3:
                    sb.append("3");
                    break;
                case NUM_4:
                    sb.append("4");
                    break;
                case NUM_5:
                    sb.append("5");
                    break;
                case NUM_6:
                    sb.append("6");
                    break;
                case NUM_7:
                    sb.append("7");
                    break;
                case NUM_8:
                    sb.append("8");
                    break;
                case NUM_9:
                    sb.append("9");
                    break;
                case DOT:
                    sb.append(".");
                    break;
                default:
                    sb.append("");
                    break;
            }
        }
        return sb.toString();
    }

    private static InputSymbol searchOperator(List<InputSymbol> inputSymbols) {
        InputSymbol resultOperator = null;
        for (InputSymbol inputSymbol : inputSymbols) {
            switch (inputSymbol) {
                case OP_DIVISION:
                case OP_MINUS:
                case OP_MULTIPLY:
                case OP_PLUS:
                case OP_PRESENT:
                    resultOperator = inputSymbol;
                    break;
            }
        }
        return resultOperator;
    }
}
