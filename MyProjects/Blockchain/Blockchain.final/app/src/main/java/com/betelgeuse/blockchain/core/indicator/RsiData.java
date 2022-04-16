package com.betelgeuse.blockchain.core.indicator;

import com.betelgeuse.blockchain.core.indicator.Data;

public class RsiData {


    public RsiData (Data data) {
        double change = data.price - data.open;
        if (change > 0) {
            this.changeUpward = Math.abs(change);
            this.changeDownward = 0;
        }
        if (change < 0) {
            this.changeDownward = Math.abs(change);
            this.changeUpward = 0;
        }
        if (change == 0) {
            this.changeUpward = 0;
            this.changeDownward = 0;
        }
    }

    double changeUpward;
    double changeDownward;
    double averageUpward;
    double averageDownward;
    double averagePrice;
    double relativeStrength;
    double relativeStrengthIndex;
}
