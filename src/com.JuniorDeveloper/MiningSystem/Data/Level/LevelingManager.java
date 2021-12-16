package Data.Level;

public class LevelingManager {

    /**
     * TODO: For usage => Comment will be deleted
     *  - Level will be saved into Database or Config,
     *  - Multiplier will be setted in the CONFIG
     *  - Amount of xp needed, can be discussed.
     *  - Maximum Level will be with Config
     */
    static int level = 0;
    static int multiplier = 0; // Multiplier has to be changed to config.
    static int maxmimum = 150; // Set this in a config.
    XpAmount xpAmount = new XpAmount();

    public static int getMultiplier() {
        return multiplier;
    }

    public static int getLevel() {
        return level;
    }

    public static int getMaxmimum() {
        return maxmimum;
    }
}
