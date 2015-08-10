package edu.fatecjh.si.dice.engine;

import java.util.ArrayList;
import java.util.List;

public class Dice {

    private final String name;
    private final int modifier;
    private final ArrayList<Die> diceList;

    Dice(String n, int k, Die... dice) {
        name = n;
        modifier = k;
        diceList = new ArrayList<Die>(dice.length);
        for (Die d : dice) {
            diceList.add(d);
        }
    }

    public String getName() {
        return name;
    }

    public int getModifier() {
        return modifier;
    }

    public List<Die> getDiceList() {
        return diceList;
    }

}
