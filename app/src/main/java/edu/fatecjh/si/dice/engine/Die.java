package edu.fatecjh.si.dice.engine;

public enum Die {

    D4(4),
    D6(6),
    D10(10),
    D12(12),
    D20(20),
    D100(100),
    D1000(1000);

    public final int sides;

    Die(int sides) {
        this.sides = sides;
    }

    public int sides() {
        return this.sides;
    }

}
