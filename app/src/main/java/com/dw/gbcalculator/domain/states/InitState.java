package com.dw.gbcalculator.domain.states;

import com.dw.gbcalculator.InputSymbol;

public class InitState extends BaseState {

    public InitState() {
        setFirstNegativeNumber(false);
    }

    @Override
    public BaseState onClickButton(InputSymbol inputSymbol) {
        switch (inputSymbol) {
            case OP_MINUS:
                if (input.size() == 0){
                    firstNegativeNumber = true;
                    input.add(inputSymbol);
                }
                return this;
            case OP_PLUS:
                input.clear();
                firstNegativeNumber = false;
                return this;
            case DOT:
            case NUM_0:
                input.add(InputSymbol.NUM_0);
                input.add(InputSymbol.DOT);
                return new FloatState(input);

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
                return new IntState(input);
            case CLEAR:
            case STEP_BACK:
                return clearInput();
            default:
                return this;

        }
    }
}
