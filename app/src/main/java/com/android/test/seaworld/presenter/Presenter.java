package com.android.test.seaworld.presenter;


import com.android.test.seaworld.model.IModel;
import com.android.test.seaworld.view.IView;

public class Presenter implements IPresenter{
    private IModel seaWorldModel;
    private IView seaWorldView;

    public Presenter(IModel seaWorld, IView seaWorldView ) {
        this.seaWorldModel = seaWorld;
        this.seaWorldView = seaWorldView;
        this.seaWorldView.setData(seaWorld.getAnimals());
    }

    @Override
    public void oneTact() {

        seaWorldView.refreshData();
    }
}
