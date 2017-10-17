package com.android.test.seaworld.model;


import com.android.test.seaworld.model.animals.Animal;
import com.android.test.seaworld.model.animals.Orca;
import com.android.test.seaworld.model.animals.Tux;
import com.android.test.seaworld.utils.Settings;

import java.util.ArrayList;

public class SeaWorldModel implements IModel {
    private Animal[][] animals;
    private ArrayList<Animal> stillAliveAnimalList;

    private int numOfColumns;
    private int numOfRows;

    public SeaWorldModel() {
        numOfColumns = Settings.getNumOfColumns();
        numOfRows = Settings.getNumOfRows();

        animals = new Animal[this.numOfColumns][this.numOfRows];
        setAnimals();
    }

    // расстановка животных на поле
    private void setAnimals() {
        int numberOfTux = (int) ((numOfColumns * numOfRows) * (Settings.getPercentOfTux()) / 100);
        int numberOfOrca = (int) ((numOfColumns * numOfRows) * (Settings.getPercentOfOrca()) / 100);
        stillAliveAnimalList = new ArrayList<Animal>();

        for (int i = 0; i < numberOfOrca; i++) {
            int x = -1;
            int y = -1;
            do {
                x = (int) (Math.random() * numOfColumns);
                y = (int) (Math.random() * numOfRows);
            } while (animals[x][y] != null);

            animals[x][y] = new Orca(this);
            stillAliveAnimalList.add(animals[x][y]);
            animals[x][y].setPosition(x, y);
        }

        for (int i = 0; i < numberOfTux; i++) {
            int x = -1;
            int y = -1;
            do {
                x = (int) (Math.random() * numOfColumns);
                y = (int) (Math.random() * numOfRows);
            } while (animals[x][y] != null);

            animals[x][y] = new Tux(this);
            stillAliveAnimalList.add(animals[x][y]);
            animals[x][y].setPosition(x, y);
        }
    }

    @Override
    public Animal[][] getAnimals() {
        return animals;
    }

    @Override
    public void oneDayOfWorld() {
        // оставшиеся в живых животные двигаем
        for (Animal animal : stillAliveAnimalList) {
            animal.move();
        }
        // обновляем список живых животных
        stillAliveAnimalList = new ArrayList<Animal>();
        for (int i = 0; i < numOfColumns; i++) {
            for (int j = 0; j < numOfRows; j++) {
                if (animals[i][j] != null && animals[i][j].isAlive) {
                    animals[i][j].setPosition(i, j);
                    stillAliveAnimalList.add(animals[i][j]);
                }
                if (animals[i][j] != null && !animals[i][j].isAlive) {
                    animals[i][j] = null;
                }
            }
        }
    }

    @Override
    public void resetWorldData() {
        for (int i = 0; i < numOfColumns; i++) {
            for (int j = 0; j < numOfRows; j++) {
                animals[i][j] = null;
            }
        }
        setAnimals();
    }

    // проверка выхода за границы поля
    public boolean overstep(int x, int y) {
        return (x >= 0 && y >= 0 && x < numOfColumns && y < numOfRows);
    }


    public void moveAnimal(Animal animal, int x, int y, boolean replace) {
        int curX = animal.getPositionX();
        int curY = animal.getPositionY();

        if ((animals[x][y] == null) || (replace)) {
            animals[x][y] = animal;
            animals[curX][curY] = null;
            animal.setPosition(x, y);
        }
    }

    public Animal getAnimal(int x, int y) {
        return animals[x][y];
    }

    private void addAnimal(Animal animal, int x, int y) {
        if (animals[x][y] == null) {
            animals[x][y] = animal;
            animals[x][y].setPosition(x, y);
        }
    }

    // поиск места для нового животного
    public void tryAddChild(Animal parent) {
        int parentX = parent.getPositionX();
        int parentY = parent.getPositionY();
        int childX = -1;
        int childY = -1;
        Animal child;
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
            // если свободное место найдено - заполняем его животным родительского типа
            if (parent instanceof Tux) {
                child = new Tux(this);
            } else {
                child = new Orca(this);
            }
            addAnimal(child, childX, childY);
        }
    }

    public void removeAnimal(Animal animal) {
        int x = animal.getPositionX();
        int y = animal.getPositionY();
        animals[x][y] = null;
    }
}
