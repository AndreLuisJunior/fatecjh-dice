package edu.fatecjh.si.dice.engine;

import java.util.Iterator;
import java.util.List;

public class Result {

    private final String name;
    private final int modifier;
    private final List<Integer> results;

    public Result(String n, int k, List<Integer> rList) {
        this.name = n;
        this.modifier = k;
        this.results = rList;
    }

    public int getResult() {
        int intResult = 0;
        for (int i : results) {
            intResult += i;
        }
        intResult += modifier;
        return intResult;
    }

    public String getName() {
        return name;
    }

    public Iterator<Integer> iterator() {
        return results.iterator();
    }

    public int getModifier() {
        return this.modifier;
    }

}
