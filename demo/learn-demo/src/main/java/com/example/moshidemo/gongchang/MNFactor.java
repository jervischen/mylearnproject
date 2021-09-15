package com.example.moshidemo.gongchang;

public class MNFactor implements Factor {
    public Milk getMilk() {
        return new MnMilk();
    }
}
