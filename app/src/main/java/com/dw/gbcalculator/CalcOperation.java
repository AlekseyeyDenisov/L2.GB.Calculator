package com.dw.gbcalculator;

import java.io.Serializable;

public class CalcOperation implements Serializable {
    private String tempResult = "";

    public String getTempResult() {
        return tempResult;
    }


    public void setTempResult(String tempResult) {
        this.tempResult += tempResult;
    }

    public boolean checkButton(String button) {
        String dot = ".";
        String zero = "0";
        return !(button.equals(dot) & tempResult.contains(dot))
                && !(button.equals(dot) & tempResult.length() == 0)
                && !(button.equals(zero) & tempResult.length() == 1 & tempResult.contains(zero));

    }


    public String stepBack() {
        if (getTempResult().length() != 0) {
            tempResult = getTempResult().substring(0, getTempResult().length() - 1);
            return getTempResult();
        }
        return getTempResult();
    }

    public void clear() {
        this.tempResult = "";
    }
}
