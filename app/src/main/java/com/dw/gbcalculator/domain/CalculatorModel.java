package com.dw.gbcalculator.domain;


import android.util.Log;

import com.dw.gbcalculator.InputSymbol;
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
        //Log.d("@@@","operator " + currentState.getOperator());
        Log.d("@@@","----------------");

        currentState = newState;

    }


    public List<InputSymbol> getInput() {
        return new ArrayList<>(currentState.getInput());
    }




}
