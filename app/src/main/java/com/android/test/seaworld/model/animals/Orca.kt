package com.android.test.seaworld.model.animals


import com.android.test.seaworld.R
import com.android.test.seaworld.model.SeaWorldModel
import com.android.test.seaworld.utils.Settings

class Orca(seaWorldModel: SeaWorldModel) : Animal(seaWorldModel) {

    private var timeWithOutFood: Int = 0

    override val animalImageResource: Int
        get() = R.drawable.orca

    init {
        timeToReprodution = Settings.timeToReproductionOrca
        lifeTime = 0
        timeWithOutFood = Settings.timeWithoutFoodOrca
    }

    override fun move() {
        // если время без еды закончилось, касатка умирает
        if (timeWithOutFood == 0) {
            seaWorldModel.removeAnimal(this)
        } else {
            timeWithOutFood--
            // иначе ищем пингвина
            val orcaX = positionX
            val orcaY = positionY
            val tux = findTux(positionX, positionY)

            if (tux != null) {
                // если пингвин найден, касатка ест пингвина
                lifeTime++
                timeWithOutFood = Settings.timeWithoutFoodOrca

                seaWorldModel.moveAnimal(this, tux.positionX, tux.positionY, true)
                tux.setPosition(orcaX, orcaY)
                tux.isAlive = false
                seaWorldModel.removeAnimal(tux)

                // проверяем, не пришло ли время для размножения
                if (lifeTime % timeToReprodution == 0) {
                    reproduction()
                }
            } else {
                // если пингвин не найден, двигаемся как пингвин
                super.move()
            }
        }
    }

    private fun findTux(orcaX: Int, orcaY: Int): Tux? {
        for (i in 0..2) {
            for (j in 0..2) {
                if (seaWorldModel.overstep(orcaX + i - 1, orcaY + j - 1)) {
                    if (seaWorldModel.getAnimal(orcaX + i - 1, orcaY + j - 1) is Tux) {
                        return seaWorldModel.getAnimal(orcaX + i - 1, orcaY + j - 1) as Tux
                    }
                }
            }
        }
        return null
    }
}
