package com.android.test.seaworld.model.animals;


import com.android.test.seaworld.model.SeaWorldModel;

import java.util.Random;

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

    public int getDrawableResourceId() {
        return 0;
    }


    public void move() {
        lifeTime++;

        if (lifeTime % timeToReprodution == 0) {
            reproduction();
        }

        int offsetX, offsetY;

    //    Random rand = new Random();

        do {
            offsetX = (int) (Math.random() * 3) - 1;
            offsetY = (int) (Math.random() * 3) - 1;
        } while ((offsetX == 0 && offsetY == 0)
                || !seaWorldModel.overstep(positionX + offsetX, positionY + offsetY));

        seaWorldModel.moveAnimal(this, positionX + offsetX, positionY + offsetY, false);




        System.out.println(lifeTime + " " + timeToReprodution);

    }

    public void reproduction() {
        seaWorldModel.tryAddChild(this);
    }


}
