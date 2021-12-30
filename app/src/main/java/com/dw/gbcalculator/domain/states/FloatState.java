package com.dw.gbcalculator.domain.states;

import com.dw.gbcalculator.InputSymbol;

import java.util.List;

public class FloatState extends BaseState {

    public FloatState(List<InputSymbol> input) {
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
                input.add(inputSymbol);
                return this;
            case CLEAR:
                return new InitState();
            case STEP_BACK:
                return stepBackElement();
            default:
                return this;
        }

    }


    private BaseState stepBackElement() {
        if (input.contains(InputSymbol.OP_MINUS)) {
            if (input.size() > 3) {
                input.remove(input.size() - 1);
                return checkDot(input);
            } else {
                return checkZero();
            }

        } else {
            if (input.size() > 2) {
                input.remove(input.size() - 1);
                return checkDot(input);
            } else {
                return checkZero();
            }
        }
    }

    private BaseState checkZero() {
        if (input.contains(InputSymbol.NUM_0))
            return new InitState();
        else {
            input.remove(input.size() - 1);
            return new IntState(input);
        }
    }

    private BaseState checkDot(List<InputSymbol> input) {
        if (input.contains(InputSymbol.DOT)) {
            return this;
        } else return new IntState(input);
    }
}
