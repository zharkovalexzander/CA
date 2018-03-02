package com.ds.caesar;

import java.text.DecimalFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Statistics {
    private List<Map.Entry<Character, Integer>> rate;
    private long fullCount;

    public Statistics() {
        this.rate = new ArrayList<>();
        this.fullCount = 0;
    }

    public void put(char character) {
        if (isPresent(character)) {
            Map.Entry<Character, Integer> entry = getPair(character);
            entry.setValue(entry.getValue() + 1);
        } else {
            this.rate.add(new AbstractMap.SimpleEntry<>(character, 1));
        }
        fullCount++;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("======= Getting Statistics =======\n");
        for(Map.Entry<Character, Integer> entry : rate) {
            stringBuilder.append("`" + entry.getKey() + "` : " +
                    new DecimalFormat("#0.00").format((entry.getValue() / (double) fullCount) * 100)
                    + "%\n");
        }
        stringBuilder.append("===================================\n");
        return stringBuilder.toString();
    }

    private boolean isPresent(char character) {
        for(Map.Entry<Character, Integer> entry : rate) {
            if(entry.getKey() == character) {
                return true;
            }
        }
        return false;
    }

    private Map.Entry<Character, Integer> getPair(char character) {
        for(Map.Entry<Character, Integer> entry : rate) {
            if(entry.getKey() == character) {
                return entry;
            }
        }
        return null;
    }
}
