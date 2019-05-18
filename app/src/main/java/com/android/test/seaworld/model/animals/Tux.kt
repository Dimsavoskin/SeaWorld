package com.android.test.seaworld.model.animals


import com.android.test.seaworld.R
import com.android.test.seaworld.model.SeaWorldModel
import com.android.test.seaworld.utils.Settings

class Tux(seaWorldModel: SeaWorldModel) : Animal(seaWorldModel) {

    override val animalImageResource: Int
        get() = R.drawable.tux

    init {
        timeToReprodution = Settings.timeToReproductionTux
    }
}
