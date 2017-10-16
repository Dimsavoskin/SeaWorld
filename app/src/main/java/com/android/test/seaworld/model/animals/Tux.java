package com.android.test.seaworld.model.animals;


import com.android.test.seaworld.R;
import com.android.test.seaworld.model.SeaWorldModel;
import com.android.test.seaworld.utils.Settings;

public class Tux extends Animal{



    public Tux(SeaWorldModel seaWorldModel){
        super(seaWorldModel);
        timeToReprodution = Settings.getTimeToReproductionTux();
    }

    @Override
    public int getDrawableResourceId() {
        return R.drawable.tux;
    }
}
