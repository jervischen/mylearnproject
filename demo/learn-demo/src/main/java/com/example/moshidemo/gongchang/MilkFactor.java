package com.example.moshidemo.gongchang;

public class MilkFactor extends AbstractMilk {
    @Override
    Milk getMN() {
        return new YLFactor().getMilk();
    }

    @Override
    Milk getYL() {
        return new MNFactor().getMilk();
    }
}
