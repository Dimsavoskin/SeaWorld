package com.android.test.seaworld.model;


import com.android.test.seaworld.model.animals.Animal;
import com.android.test.seaworld.model.animals.Orca;
import com.android.test.seaworld.model.animals.Tux;
import com.android.test.seaworld.utils.Settings;

import java.util.ArrayList;

public class SeaWorldModel implements IModel {

    public Animal[][] animals;

    private int numOfColumns;
    private int numOfRows;

    private ArrayList<Animal> animalList;

    public SeaWorldModel(int numOfColumns, int numOfRows) {
        this.numOfColumns = numOfColumns;
        this.numOfRows = numOfRows;

        animals = new Animal[this.numOfColumns][this.numOfRows];

        setAnimals();
    }

    private void setAnimals() {

        int numberOfTux = (int) ((numOfColumns * numOfRows) * (Settings.getPercentOfTux()) / 100);
        int numberOfOrca = (int) ((numOfColumns * numOfRows) * (Settings.getPercentOfOrca()) / 100);

        animalList = new ArrayList<Animal>();

        //  System.out.println((numberOfOrca));

        for (int i=0;i<numberOfOrca;i++){
            int x = -1;
            int y = -1;
            do{
                x = (int) (Math.random()*numOfColumns);
                y = (int) (Math.random()*numOfRows);
            }while (animals[x][y] != null);

            animals[x][y] = new Orca(this);
            animalList.add(animals[x][y]);
            animals[x][y].setPosition(x, y);
        }


        for (int i=0;i<numberOfTux;i++){
            int x = -1;
            int y = -1;
            do{
                x = (int) (Math.random()*numOfColumns);
                y = (int) (Math.random()*numOfRows);
            }while (animals[x][y] != null);

            animals[x][y] = new Tux(this);
            animalList.add(animals[x][y]);
            animals[x][y].setPosition(x, y);
        }


//        for (int i = 0; i < numOfColumns; i++) {
//            for (int j = 0; j < numOfRows; j++) {
//                if (numOfRows * i + j < numberOfTux)
//                    animals[i][j] = new Tux(this);
//                else if (numOfRows * i + j < numberOfTux + numberOfOrca)
//                    animals[i][j] = new Orca(this);
//                else
//                    animals[i][j] = null;
//            }
//        }
//
//        int randX, randY;
//        Random random = new Random();
//        Animal tmp;
//
//
//
//        for (int i = 0; i < numOfColumns; i++) {
//            for (int j = 0; j < numOfRows; j++) {
//                randX = random.nextInt(numOfColumns);
//                randY = random.nextInt(numOfRows);
//                tmp = animals[i][j];
//                animals[i][j] = animals[randX][randY];
//                animals[randX][randY] = tmp;
//            }
//        }
//
//        for (int i = 0; i < numOfColumns; i++) {
//            for (int j = 0; j < numOfRows; j++) {
//                if (animals[i][j] != null) {
//                    animalList.add(animals[i][j]);
//                    animals[i][j].setPosition(i, j);
//                }
//            }
//        }

        //    return getAnimalList();
    }


    @Override
    public Animal[][] getAnimals() {
        return animals;
    }

    @Override
    public void oneDayOfWorld() {

        for (Animal animal : animalList) {
            animal.move();
        }

        animalList = new ArrayList<Animal>();

        for (int i = 0; i < numOfColumns; i++) {
            for (int j = 0; j < numOfRows; j++) {
                if (animals[i][j] != null && animals[i][j].isAlive) {
                    animals[i][j].setPosition(i, j);
                    animalList.add(animals[i][j]);
                }
                if (animals[i][j] != null && !animals[i][j].isAlive) {
                    animals[i][j] = null;
                }
            }
        }
        //   animalList = getAnimalList();

    }

    @Override
    public void refreshWorldData() {

        for (int i = 0; i < numOfColumns; i++) {
            for (int j = 0; j < numOfRows; j++) {
                animals[i][j] = null;
            }
        }

        setAnimals();
    }

    public boolean overstep(int x, int y) {
        return (x >= 0 && y >= 0 && x < numOfColumns && y < numOfRows);
    }


    public void moveAnimal(Animal animal, int x, int y, boolean eat) {
        int newX = x;
        int newY = y;
        int curX = animal.getPositionX();
        int curY = animal.getPositionY();

        //   System.out.println(newX + " " + newY + " " + eat);

        if ((animals[newX][newY] == null) || (eat)) {
            animals[newX][newY] = animal;
            animals[curX][curY] = null;
            // System.out.println(newX + " " + newY + " " + eat);
            animal.setPosition(newX, newY);
        }
    }

    public Animal getAnimal(int x, int y) {
        return animals[x][y];
    }

    public void addAnimal(Animal animal, int x, int y) {

        if (animals[x][y] == null) {
            animals[x][y] = animal;
            animals[x][y].setPosition(x, y);
        }
    }

    public Animal tryAddChild(Animal parent) {
        int parentX = parent.getPositionX();
        int parentY = parent.getPositionY();

        int childX = -1;
        int childY = -1;

        Animal child = new Animal(this);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ((i != 1 || j != 1) && parentX + i - 1 >= 0 && parentX + i - 1 < numOfColumns
                        && parentY + j - 1 >= 0 && parentY + j - 1 < numOfRows) {
                    if (animals[parentX + i - 1][parentY + j - 1] == null) {
                        childX = parentX + i - 1;
                        childY = parentY + j - 1;
                    }
                }
            }
        }

        if ((childX >= 0) && (childY >= 0)) {
            if (parent instanceof Tux) {
                child = new Tux(this);
            } else {
                child = new Orca(this);
            }
            addAnimal(child, childX, childY);
        }

        return child;
    }

    public void removeAnimal(Animal animal) {
        int curX = animal.getPositionX();
        int curY = animal.getPositionY();
        animals[curX][curY] = null;
    }

}
