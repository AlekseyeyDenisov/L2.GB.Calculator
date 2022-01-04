package com.dw.gbcalculator.domain.states;

import android.os.Parcelable;
import android.util.Log;

import com.dw.gbcalculator.InputSymbol;

import java.util.ArrayList;
import java.util.List;

abstract public class BaseState  {
    protected final List<InputSymbol> input = new ArrayList<>();
    static InputSymbol operator = InputSymbol.UNDEFINE;
    static float num1 = 0;
    static float num2 = 0;


    public abstract BaseState onClickButton(InputSymbol inputSymbol);

    public List<InputSymbol> getInput() {
        return new ArrayList<>(input);
    }

    public InputSymbol getOperator() {
        return operator;
    }

    public void checkEquals(InputSymbol inputSymbol) {
        if (operator != InputSymbol.UNDEFINE && input.get(input.size() - 1) != operator) {
            input.add(inputSymbol);
        }
    }

    public void checkOperator(InputSymbol inputSymbol) {
        if (InputSymbol.DOT == input.get(input.size() - 1)){
            return;
        }
        if (operator == InputSymbol.UNDEFINE) {
            operator = inputSymbol;
            input.add(inputSymbol);
            return;
        }
        if(input.get(input.size() - 1) == operator){
            operator = inputSymbol;
            input.remove(input.size() - 1);
            input.add(inputSymbol);
            return;
        }



    }

    public BaseState clearInput() {
        input.clear();
        operator = InputSymbol.UNDEFINE;
        return new InitState();
    }




}
