package com.android.test.seaworld.view;


import com.android.test.seaworld.model.animals.Animal;

public interface IView {
    void setData(Animal[][] animals);
    void refreshData();
}
