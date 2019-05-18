package com.android.test.seaworld.model.animals


import com.android.test.seaworld.model.SeaWorldModel

open class Animal(internal var seaWorldModel: SeaWorldModel) {

    var positionX: Int = 0
        internal set
    var positionY: Int = 0
        internal set

    internal var lifeTime: Int = 0
    internal var timeToReprodution: Int = 0

    var isAlive: Boolean = false

    open val animalImageResource: Int
        get() = 0

    init {
        positionX = 0
        positionY = 0
        lifeTime = 0
        isAlive = true
    }

    fun setPosition(x: Int, y: Int) {
        positionX = x
        positionY = y
    }

    open fun move() {
        lifeTime++
        if (lifeTime % timeToReprodution == 0) {
            reproduction()
        }
        // выбор случайной позиции для перемещения, не выходящей за границы
        var offsetX: Int
        var offsetY: Int
        do {
            offsetX = (Math.random() * 3).toInt() - 1
            offsetY = (Math.random() * 3).toInt() - 1
        } while (!seaWorldModel.overstep(positionX + offsetX, positionY + offsetY) || offsetX == 0 && offsetY == 0)
        seaWorldModel.moveAnimal(this, positionX + offsetX, positionY + offsetY, false)
    }

    internal fun reproduction() {
        seaWorldModel.tryAddChild(this)
    }
}
