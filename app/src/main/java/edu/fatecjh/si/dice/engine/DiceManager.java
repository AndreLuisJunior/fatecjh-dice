package edu.fatecjh.si.dice.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class DiceManager {

    /**
     * Create the internal storage
     */
    private static final Map<String, Dice> storage = new HashMap<String, Dice>();
    static {
        storage.put("1 D6", new Dice("1 D6", 0, Die.D6));
        storage.put("2 D6", new Dice("2 D6", 0, Die.D6, Die.D6));
        storage.put("3 D6", new Dice("3 D6", 0, Die.D6, Die.D6, Die.D6));
        storage.put("3 D6 + 3", new Dice("3 D6 + 3", 3, Die.D6, Die.D6, Die.D6));
        storage.put("1 D10", new Dice("1 D10", 0, Die.D10));
        storage.put("2 D10 + 4", new Dice("2 D10 + 4", 4, Die.D10, Die.D10));
        storage.put("1 D20", new Dice("1 D20", 0, Die.D20));
        storage.put("1 D100", new Dice("1 D100", 0, Die.D100));
    }

    private static final ArrayList<Result> history = new ArrayList<Result>();

    /**
     * Retrieve a set containing the names of all Dice available in the system
     * 
     * @return a set of Strings
     */
    public static Set<String> getDiceNames() {
        return storage.keySet();
    }

    /**
     * Retrieve a Dice by its name
     * 
     * @param name
     *            the name os the Dice
     * @return a Dice corresponding to the name.
     *         <code>null<code> if none found in the database
     */
    public static Dice getDice(String name) {
        return storage.get(name);
    }

    /**
     * Roll the Dice (a set of die plus a (possibly positive or negative)
     * modifier
     * 
     * @param dice
     *            the Dice
     * @param saveHistory
     *            save (or not) history
     * 
     * @return the Result of the operation
     */
    public static Result rollTheDice(Dice dice, boolean saveHistory) {
        List<Integer> rList = new ArrayList<Integer>();
        for (Die d : dice.getDiceList()) {
            int r = randomize(1, d.sides());
            rList.add(r);
        }
        Result result = new Result(dice.getName(), dice.getModifier(), rList);
        
        if (saveHistory) {
            history.add(result);
        }
        
        return result;
    }

    /**
     * 
     * @return
     */
    public static List<Result> getHistory() {
        return history;
    }

    /**
     * 
     */
    public static void clearHistory() {
        history.clear();
    }

    /**
     * Randomizes a Integer between min (inclusive) and max (inclusive)
     * 
     * @param min
     *            the minimum integer value of the randomization (inclusive)
     * @param max
     *            the maximum integer value of the randomization (inclusive)
     * @return the randomized integer
     */
    private final static int randomize(int min, int max) {
        Random r = new Random();
        return r.nextInt(max - min) + min;
    }

}
