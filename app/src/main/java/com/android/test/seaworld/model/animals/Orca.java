package com.android.test.seaworld.model.animals;


import com.android.test.seaworld.R;
import com.android.test.seaworld.model.SeaWorldModel;
import com.android.test.seaworld.utils.Settings;

public class Orca extends Animal {

    int timeWithOutFood;

    public Orca(SeaWorldModel seaWorldModel) {
        super(seaWorldModel);
        timeToReprodution = Settings.getTimeToReproductionOrca();
        lifeTime = 0;
        timeWithOutFood = Settings.getTimeWithoutFoodOrca();
    }

    @Override
    public int getDrawableResourceId() {
        return R.drawable.orca;
    }


    @Override
    public void move() {

        timeWithOutFood--;

        if (timeWithOutFood == 0) {
            seaWorldModel.removeAnimal(this);
        } else {

            int orcaX = positionX;
            int orcaY = positionY;

            Tux tux = findTux(positionX, positionY);

            if (tux != null) {
                lifeTime++;
                timeWithOutFood = Settings.getTimeWithoutFoodOrca();

                seaWorldModel.moveAnimal(this, tux.positionX, tux.positionY, true);
                tux.setPosition(orcaX, orcaY);
                tux.isAlive = false;
                seaWorldModel.removeAnimal(tux);

                if (lifeTime % timeToReprodution == 0) {
                    reproduction();
                }
            } else {
                super.move();
            }
        }
    }


    Tux findTux(int orcaX, int orcaY) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (seaWorldModel.overstep((orcaX + i) - 1, (orcaY + j) - 1)) {
                    if (seaWorldModel.getAnimal((orcaX + i) - 1, (orcaY + j) - 1) instanceof Tux) {
                        return (Tux) seaWorldModel.getAnimal((orcaX + i) - 1, (orcaY + j) - 1);
                    }
                }
            }
        }

        return null;
    }
}
