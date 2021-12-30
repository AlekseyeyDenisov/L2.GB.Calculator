package com.dw.gbcalculator.domain.states;

import com.dw.gbcalculator.InputSymbol;

import java.util.ArrayList;
import java.util.List;

abstract public class BaseState {
    protected final List<InputSymbol> input = new ArrayList<>();

    public abstract BaseState onClickButton(InputSymbol inputSymbol);

    public List<InputSymbol> getInput() {
        return new ArrayList<>(input);
    }
}
