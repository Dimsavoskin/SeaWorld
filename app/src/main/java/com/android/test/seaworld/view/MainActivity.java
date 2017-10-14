package com.android.test.seaworld.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.test.seaworld.R;
import com.android.test.seaworld.model.SeaWorldModel;
import com.android.test.seaworld.model.animals.Animal;
import com.android.test.seaworld.presenter.Presenter;
import com.android.test.seaworld.settings.Settings;
import com.android.test.seaworld.view.adapters.MainAdapter;

public class MainActivity extends AppCompatActivity implements IView, AdapterView.OnItemClickListener {

    private GridView seaWorldGrid;

    private int numOfColumns;
    private int numOfRows;

    private MainAdapter adapter;

    private Presenter presenter;


    Animal[][] animals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numOfColumns = Settings.getNumOfColumns();
        numOfRows = Settings.getNumOfRows();

        seaWorldGrid = (GridView) findViewById(R.id.seaWorldGrid);
        seaWorldGrid.setNumColumns(numOfColumns);
       // seaWorldGrid.setColumnWidth(seaWorldGrid.getWidth() / numOfColumns);


        animals = new Animal[this.numOfColumns][this.numOfRows];

//        for (int i = 0; i < numOfColumns; i++) {
//            for (int j = 0; j < numOfRows; j++) {
//
//                if(i%2 == 0) {
//                    animals[i][j] = new Tux();
//                }else{
//                    animals[i][j] = new Orca();
//                }
//
//            }
//        }


//        adapter = new MainAdapter(this, seaWorldGrid, animals);
//
//        seaWorldGrid.setAdapter(adapter);

        seaWorldGrid.setOnItemClickListener(this);

        presenter = new Presenter(new SeaWorldModel(), this);

    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        System.out.println("Click");
        presenter.oneTact();
    }

    @Override
    public void setData(Animal[][] animals) {
        adapter = new MainAdapter(this, seaWorldGrid, animals);
        seaWorldGrid.setAdapter(adapter);
    }

    @Override
    public void refreshData() {
        adapter.notifyDataSetChanged();
    }
}
