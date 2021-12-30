package com.dw.gbcalculator.domain.states;

import com.dw.gbcalculator.InputSymbol;

import java.util.List;

public class IntState extends BaseState {


    public IntState(List<InputSymbol> input) {
        this.input.addAll(input);
    }

    @Override
    public BaseState onClickButton(InputSymbol inputSymbol) {
        switch (inputSymbol){
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
            case STEP_BACK:
                if (input.size() > 1){
                    input.remove(input.size() - 1);
                    return this;
                }
                else return new InitState();
            case CLEAR:
                return new InitState();
            case DOT:
                input.add(inputSymbol);
                return new FloatState(input);
            default:
                return this;
        }


    }
}
