package com.example.iot_lab4_20206331.data.model;

import com.google.gson.annotations.SerializedName;

public class Day {
    @SerializedName("maxtemp_c")
    private float maxtempC;

    @SerializedName("mintemp_c")
    private float mintempC;

    @SerializedName("condition")
    private Condition condition;

    public float getMaxtempC() {
        return maxtempC;
    }

    public void setMaxtempC(float maxtempC) {
        this.maxtempC = maxtempC;
    }

    public float getMintempC() {
        return mintempC;
    }

    public void setMintempC(float mintempC) {
        this.mintempC = mintempC;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
