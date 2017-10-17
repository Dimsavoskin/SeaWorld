package com.android.test.seaworld.model.animals;


import com.android.test.seaworld.model.SeaWorldModel;

public class Animal {

    int positionX;
    int positionY;

    int lifeTime;
    int timeToReprodution;

    public boolean isAlive;
    SeaWorldModel seaWorldModel;

    public Animal(SeaWorldModel seaWorldModel) {
        this.seaWorldModel = seaWorldModel;
        positionX = 0;
        positionY = 0;
        lifeTime = 0;
        isAlive = true;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPosition(int x, int y) {
        positionX = x;
        positionY = y;
    }

    public int getAnimalImageResource() {
        return 0;
    }

    public void move() {
        lifeTime++;
        if (lifeTime % timeToReprodution == 0) {
            reproduction();
        }
        // выбор случайной позиции для перемещения, не выходящей за границы
        int offsetX, offsetY;
        do {
            offsetX = (int) (Math.random() * 3) - 1;
            offsetY = (int) (Math.random() * 3) - 1;
        } while (!seaWorldModel.overstep(positionX + offsetX, positionY + offsetY)
                || (offsetX == 0 && offsetY == 0));
        seaWorldModel.moveAnimal(this, positionX + offsetX, positionY + offsetY, false);
    }

    void reproduction() {
        seaWorldModel.tryAddChild(this);
    }
}
