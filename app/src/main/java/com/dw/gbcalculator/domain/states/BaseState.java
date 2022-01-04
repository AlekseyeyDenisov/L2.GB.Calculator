package com.dw.gbcalculator.domain.states;

import com.dw.gbcalculator.InputSymbol;

import java.util.ArrayList;
import java.util.List;

abstract public class BaseState {
    protected final List<InputSymbol> input = new ArrayList<>();
    static InputSymbol operator = InputSymbol.UNDEFINE;

    static Boolean firstNegativeNumber = false;

    public static Boolean getFirstNegativeNumber() {
        return firstNegativeNumber;
    }

    public static void setFirstNegativeNumber(Boolean firstNegativeNumber) {
        BaseState.firstNegativeNumber = firstNegativeNumber;
    }

    public abstract BaseState onClickButton(InputSymbol inputSymbol);

    public List<InputSymbol> getInput() {
        return new ArrayList<>(input);
    }

    public void checkEquals(InputSymbol inputSymbol) {
        if (operator != InputSymbol.UNDEFINE && input.get(input.size() - 1) != operator) {
            input.add(inputSymbol);
        }
    }

    public void checkOperator(InputSymbol inputSymbol) {
        if (InputSymbol.DOT == input.get(input.size() - 1))  return;

        if (operator == InputSymbol.UNDEFINE) {
            operator = inputSymbol;
            input.add(inputSymbol);
            return;
        }
        if (input.get(input.size() - 1) == operator) {
            operator = inputSymbol;
            input.remove(input.size() - 1);
            input.add(inputSymbol);
        }


    }

    public BaseState clearInput() {
        firstNegativeNumber = false;
        input.clear();
        operator = InputSymbol.UNDEFINE;
        return new InitState();
    }


}
