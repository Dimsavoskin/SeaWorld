package com.android.test.seaworld.view;


import com.android.test.seaworld.model.animals.Animal;

public interface IView {
    public void setData(Animal[][] animals);
    public void refreshData();
}
