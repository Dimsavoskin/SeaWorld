package com.android.test.seaworld.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView

import com.android.test.seaworld.R
import com.android.test.seaworld.model.SeaWorldModel
import com.android.test.seaworld.model.animals.Animal
import com.android.test.seaworld.presenter.Presenter
import com.android.test.seaworld.utils.Settings
import com.android.test.seaworld.view.adapters.ImageAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IView, AdapterView.OnItemClickListener, View.OnClickListener {

    private var adapter: ImageAdapter? = null
    private lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity_main_button_restart.setOnClickListener(this)

        val numOfColumns = Settings.numOfColumns
        activity_main_greed_view.numColumns = numOfColumns
        activity_main_greed_view.adapter = adapter
        activity_main_greed_view.onItemClickListener = this

        presenter = Presenter(SeaWorldModel(), this)
    }


    override fun onItemClick(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
        presenter.oneTact()
    }

    override fun setData(animals: Array<Array<Animal>>) {
        adapter = ImageAdapter(this, activity_main_greed_view, animals)
        activity_main_greed_view.adapter = adapter
    }

    override fun refreshData() {
        adapter!!.notifyDataSetChanged()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.activity_main_button_restart -> presenter.restartWorld()
        }
    }
}
