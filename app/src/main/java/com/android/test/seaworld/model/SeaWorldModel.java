package com.android.test.seaworld.model;


import com.android.test.seaworld.model.animals.Animal;
import com.android.test.seaworld.model.animals.Orca;
import com.android.test.seaworld.model.animals.Tux;
import com.android.test.seaworld.settings.Settings;

import java.util.Random;

public class SeaWorldModel implements IModel {

    Animal[][] animals;

    private int numOfColumns;
    private int numOfRows;

    public SeaWorldModel() {
        numOfColumns = Settings.getNumOfColumns();
        numOfRows = Settings.getNumOfRows();

        animals = new Animal[this.numOfColumns][this.numOfRows];

        setAnimals();
    }

    private void setAnimals() {

        int numberOfTux = (int) ((numOfColumns * numOfRows) * (Settings.getPercentOfTux()) / 100);
        int numberOfOrca = (int) ((numOfColumns * numOfRows) * (Settings.getPercentOfOrca()) / 100);

        //  System.out.println((numberOfOrca));

        for (int i = 0; i < numOfColumns; i++) {
            for (int j = 0; j < numOfRows; j++) {
                if (numOfRows * i + j < numberOfTux)
                    animals[i][j] = new Tux();
                else if (numOfRows * i + j < numberOfTux + numberOfOrca)
                    animals[i][j] = new Orca();
                else
                    animals[i][j] = new Animal();
            }
        }

        int randX, randY;
        Random random = new Random();
        Animal tmp;

        for (int i = 0; i < numOfColumns; i++) {
            for (int j = 0; j < numOfRows; j++) {
                randX = random.nextInt(numOfColumns);
                randY = random.nextInt(numOfRows);
                tmp = animals[i][j];
                animals[i][j] = animals[randX][randY];
                animals[randX][randY] = tmp;// Random
            }
        }
    }


    @Override
    public Animal[][] getAnimals() {
        return animals;
    }

    @Override
    public void oneDayOfWorld() {
        for (int i = 0; i < numOfColumns; i++) {
            for (int j = 0; j < numOfRows; j++) {

                if (animals[i][j] != null && animals[i][j].isAlive()) {


                }
                if (animals[i][j] != null && !animals[i][j].isAlive()) {
                    animals[i][j] = null;
                }
            }
        }
    }
}
