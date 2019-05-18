package com.android.test.seaworld.view


import com.android.test.seaworld.model.animals.Animal

interface IView {

    fun setData(animals: Array<Array<Animal>>)

    fun refreshData()
}
