package com.dw.gbcalculator.domain.states;

import com.dw.gbcalculator.InputSymbol;

import java.util.Collections;
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
            case DOT: {
                if (!input.contains(InputSymbol.OP_EQUALS))
                checkDot();
                return this;
            }
            case OP_EQUALS: {
                if (!input.contains(InputSymbol.OP_EQUALS))
                checkEquals(inputSymbol);
                return this;
            }
            case CLEAR:
                return clearInput();
            case STEP_BACK:
                return stepBackFloat();

            default:
                return this;
        }

    }


    private void checkDot() {
        if (!input.contains(InputSymbol.DOT)) {
            input.add(InputSymbol.DOT);
        } else if (
                operator != InputSymbol.UNDEFINE &&
                        Collections.frequency(input, InputSymbol.DOT) < 2 &&
                        input.get(input.size() - 1) != operator
        ) {
            input.add(InputSymbol.DOT);
        } else if (input.get(input.size() - 1) == operator) {
            input.add(InputSymbol.NUM_0);
            input.add(InputSymbol.DOT);
        }
    }

    private BaseState stepBackFloat() {
        if (operator == InputSymbol.UNDEFINE && input.get(input.size() - 1) == InputSymbol.DOT) {
            input.remove(input.size() - 1);
            if (input.get(input.size() - 1) == InputSymbol.NUM_0) {
                input.remove(input.size() - 1);
            }
            return new IntState(input);
        }
        if (input.get(input.size() - 1) == InputSymbol.DOT) {
            input.remove(input.size() - 1);
            if (input.get(input.size() - 1) == InputSymbol.NUM_0) {
                input.remove(input.size() - 1);
                return new InitState();
            }
            return this;
        }
        if (input.get(input.size() - 1) == operator) {
            input.remove(input.size() - 1);
            operator = InputSymbol.UNDEFINE;
            if (input.size() == 0) return new InitState();
            return this;
        } else input.remove(input.size() - 1);

        return this;
    }


}
