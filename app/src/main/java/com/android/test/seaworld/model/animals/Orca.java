package com.android.test.seaworld.model.animals;


import com.android.test.seaworld.R;
import com.android.test.seaworld.model.SeaWorldModel;
import com.android.test.seaworld.utils.Settings;

public class Orca extends Animal {

    private int timeWithOutFood;

    public Orca(SeaWorldModel seaWorldModel) {
        super(seaWorldModel);
        timeToReprodution = Settings.getTimeToReproductionOrca();
        lifeTime = 0;
        timeWithOutFood = Settings.getTimeWithoutFoodOrca();
    }

    @Override
    public int getAnimalImageResource() {
        return R.drawable.orca;
    }

    @Override
    public void move() {
        // если время без еды закончилось, касатка умирает
        if (timeWithOutFood == 0) {
            seaWorldModel.removeAnimal(this);
        } else {
            timeWithOutFood--;
            // иначе ищем пингвина
            int orcaX = positionX;
            int orcaY = positionY;
            Tux tux = findTux(positionX, positionY);

            if (tux != null) {
                // если пингвин найден, касатка на ест пингвина
                lifeTime++;
                timeWithOutFood = Settings.getTimeWithoutFoodOrca();

                seaWorldModel.moveAnimal(this, tux.positionX, tux.positionY, true);
                tux.setPosition(orcaX, orcaY);
                tux.isAlive = false;
                seaWorldModel.removeAnimal(tux);

                // проверяем, не пришло ли время для размножения
                if (lifeTime % timeToReprodution == 0) {
                    reproduction();
                }
            } else {
                // если пингвин не найден, двигаемся как пингвин
                super.move();
            }
        }
    }

    private Tux findTux(int orcaX, int orcaY) {
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
