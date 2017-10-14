package com.android.test.seaworld.model.animals;


import com.android.test.seaworld.model.SeaWorldModel;

public class Animal {

    int positionX;
    int positionY;

    boolean alive = true;


    SeaWorldModel seaWorldModel;

    public Animal() {

    }


    public int getDrawableResourceId() {
        return 0;
    }

    public boolean isAlive() {
        return alive;
    }
}
