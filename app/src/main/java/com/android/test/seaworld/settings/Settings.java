package com.android.test.seaworld.settings;


public class Settings {

    private static int NUM_OF_COLUMNS = 10;
    private static int NUM_OF_ROWS = 15;
    private static int PERCENT_OF_TUX = 50;
    private static int PERCENT_OF_ORCA = 5;
    private static int TIME_TO_REPRODUCTION_TUX = 3;
    private static int TIME_TO_REPRODUCTION_ORCA = 8;
    private static int TIME_WITHOUT_FOOD_ORCA = 3;


    public static int getNumOfColumns() {
        return NUM_OF_COLUMNS;
    }

    public static int getNumOfRows() {
        return NUM_OF_ROWS;
    }

    public static int getPercentOfTux() {
        return PERCENT_OF_TUX;
    }

    public static int getPercentOfOrca() {
        return PERCENT_OF_ORCA;
    }

    public static int getTimeToReproductionTux() {
        return TIME_TO_REPRODUCTION_TUX;
    }

    public static int getTimeToReproductionOrca() {
        return TIME_TO_REPRODUCTION_ORCA;
    }

    public static int getTimeWithoutFoodOrca() {
        return TIME_WITHOUT_FOOD_ORCA;
    }
}
