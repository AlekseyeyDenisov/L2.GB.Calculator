package com.dw.gbcalculator.domain.states;

import com.dw.gbcalculator.InputSymbol;

import java.util.List;

public class IntState extends BaseState {


    public IntState(List<InputSymbol> input) {
        this.input.addAll(input);
    }

    @Override
    public BaseState onClickButton(InputSymbol inputSymbol) {
        switch (inputSymbol) {
            case NUM_0:
            case NUM_1:
            case NUM_2:
            case NUM_3:
            case NUM_4:
            case NUM_5:
            case NUM_6:
            case NUM_7:
            case NUM_8:
            case NUM_9:
                if (!input.contains(InputSymbol.OP_EQUALS))
                input.add(inputSymbol);
                return this;
            case OP_MINUS:
            case OP_DIVISION:
            case OP_MULTIPLY:
            case OP_PLUS:
            case OP_PRESENT:
                if (!input.contains(InputSymbol.OP_EQUALS))
                checkOperator(inputSymbol);
                return this;
            case OP_EQUALS:
                if (!input.contains(InputSymbol.OP_EQUALS))
                checkEquals(inputSymbol);
                return this;
            case STEP_BACK:
                return stepBackInt();
            case CLEAR:
                return clearInput();
            case DOT:
                if (!input.contains(InputSymbol.OP_EQUALS))
                input.add(inputSymbol);
                return new FloatState(input);
            default:
                return this;
        }


    }

    private BaseState stepBackInt() {
        if (input.get(input.size() - 1) == operator) {
            input.remove(input.size() - 1);
            operator = InputSymbol.UNDEFINE;
            if (input.size() == 0) return new InitState();
            return this;
        } else input.remove(input.size() - 1);
        if (input.size() == 0) return new InitState();
        return this;
    }
}
