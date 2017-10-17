package com.android.test.seaworld.model;


import com.android.test.seaworld.model.animals.Animal;

public interface IModel {
    Animal[][] getAnimals();
    void oneDayOfWorld();
    void resetWorldData();
}
