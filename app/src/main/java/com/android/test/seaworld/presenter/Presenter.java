package com.android.test.seaworld.presenter;


import com.android.test.seaworld.model.IModel;
import com.android.test.seaworld.view.IView;

public class Presenter implements IPresenter {
    private IModel model;
    private IView view;

    public Presenter(IModel model, IView view) {
        this.model = model;
        this.view = view;
        this.view.setData(model.getAnimals());
    }

    @Override
    public void oneTact() {
        model.oneDayOfWorld();

        view.refreshData();
    }

    @Override
    public void restartWorld() {
        model.resetWorldData();
        view.refreshData();
    }
}
