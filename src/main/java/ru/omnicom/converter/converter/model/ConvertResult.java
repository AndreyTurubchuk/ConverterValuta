//Конвертер валют

package ru.omnicom.converter.converter.model;

import java.util.Locale;

public class ConvertResult {
    private Double sum = 0.00;
    private String status;

    public ConvertResult(Double sum, String status) {
        this.sum = sum;
        this.status = status;
    }

    public ConvertResult() {
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getSum() {
        return sum;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "{sum: " + String.format(Locale.ENGLISH, "%.2f", sum) + ", " + "status: " + status + "}";
    }


}
