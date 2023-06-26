package javaproject.game.Credits;

public class Credits {
    private  int credits;
    private static int minCredits = 0;
    private static int maxCredits = 2000;
    private final int DEFAULT_CREDITS = 100;
    private boolean hasEnoughCredits = false;

    public Credits(int credits) {
        this.credits = credits;
        Credits.setMinCredits(Credits.getMinCredits());
        Credits.setMaxCredits(Credits.getMaxCredits());
    }

    public Credits() {
        this.setCredits(DEFAULT_CREDITS);
        Credits.setMinCredits(Credits.getMinCredits());
        Credits.setMaxCredits(Credits.getMaxCredits());
    }

    public Credits(int credits, int max, int min) {
        this.addCredits(credits);
        Credits.setMaxCredits(max);
        Credits.setMinCredits(min);
    }


    public void addCredits(int amount){
        this.setCredits(this.getCredits() + amount);
    }

    public void withdrawCredits(int amount) {
        if(amount > 0 && !(this.getCredits() - amount < Credits.getMinCredits())) {
            this.setHasEnoughCredits(true);
        }

        if(this.hasEnoughCredits) {
            this.setCredits(this.getCredits() - amount);
        }else {
            System.out.println("You don't have enough coins!");
        }
    }

    public  int getCredits() {
        return credits;
    }

    public  void setCredits(int credits) {
        if(this.getCredits() + credits > Credits.getMaxCredits()){
            this.setCredits(Credits.getMaxCredits()-1);
        }

        if(this.getCredits() - credits < Credits.getMinCredits()) {
            this.setCredits(0);
        }

        this.credits = credits;
    }

    public static int getMinCredits() {
        return minCredits;
    }

    public static void setMinCredits(int minCredits) {
        Credits.minCredits = minCredits;
    }

    public static int getMaxCredits() {
        return maxCredits;
    }

    public static void setMaxCredits(int maxCredits) {
        Credits.maxCredits = maxCredits;
    }

    public boolean isHasEnoughCredits() {
        return hasEnoughCredits;
    }

    public void setHasEnoughCredits(boolean hasEnoughCredits) {
        this.hasEnoughCredits = hasEnoughCredits;
    }
}
