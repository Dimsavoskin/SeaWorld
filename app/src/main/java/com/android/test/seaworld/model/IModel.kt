package com.android.test.seaworld.model


import com.android.test.seaworld.model.animals.Animal

interface IModel {

    val animals: Array<Array<Animal>>

    fun oneDayOfWorld()

    fun resetWorldData()
}
