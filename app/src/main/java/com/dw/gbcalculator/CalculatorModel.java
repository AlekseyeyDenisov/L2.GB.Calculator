package com.dw.gbcalculator;

import java.io.Serializable;

public class CalculatorModel implements Serializable {
    private String inputArithmeticExpression = "";

    public String getInputArithmeticExpression() {
        return inputArithmeticExpression;
    }


    public void setInputArithmeticExpression(String inputArithmeticExpression) {
        this.inputArithmeticExpression += inputArithmeticExpression;
    }

    public boolean checkEnteredData(String button) {
        String dot = ".";
        String zero = "0";
        return !(button.equals(dot) & inputArithmeticExpression.contains(dot))
                && !(button.equals(dot) & inputArithmeticExpression.length() == 0)
                && !(button.equals(zero) & inputArithmeticExpression.length() == 1 & inputArithmeticExpression.contains(zero));

    }


    public String backSpace() {
        if (getInputArithmeticExpression().length() != 0)
            inputArithmeticExpression = getInputArithmeticExpression().substring(0, getInputArithmeticExpression().length() - 1);
        return getInputArithmeticExpression();
    }

    public void clear() {
        this.inputArithmeticExpression = "";
    }
}
