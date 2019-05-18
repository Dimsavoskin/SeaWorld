package com.android.test.seaworld.presenter


import com.android.test.seaworld.model.IModel
import com.android.test.seaworld.view.IView

class Presenter(private val model: IModel, private val view: IView) {

    init {
        this.view.setData(model.animals)
    }

    fun oneTact() {
        model.oneDayOfWorld()

        view.refreshData()
    }

    fun restartWorld() {
        model.resetWorldData()
        view.refreshData()
    }
}
