package com.android.test.seaworld.model;


import com.android.test.seaworld.model.animals.Animal;

public interface IModel {
    public Animal[][] getAnimals();
    public void oneDayOfWorld();
    public void refreshWorldData();
}
