package com.example.moshidemo.gongchang;

public class YLFactor implements Factor {
    public Milk getMilk() {
        return new YiliMilk();
    }
}
