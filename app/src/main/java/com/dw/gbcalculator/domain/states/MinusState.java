package com.dw.gbcalculator.domain.states;

import com.dw.gbcalculator.InputSymbol;

import java.util.List;

public class MinusState extends BaseState {

    public MinusState(List<InputSymbol> input) {
        this.input.addAll(input);
    }

    @Override
    public BaseState onClickButton(InputSymbol inputSymbol) {
        switch (inputSymbol) {
            case NUM_0:
            case DOT:
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
                return new InitState();
            default:
                return this;
        }


    }
}
