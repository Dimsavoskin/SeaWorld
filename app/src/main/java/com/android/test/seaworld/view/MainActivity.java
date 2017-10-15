package com.android.test.seaworld.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.android.test.seaworld.R;
import com.android.test.seaworld.model.SeaWorldModel;
import com.android.test.seaworld.model.animals.Animal;
import com.android.test.seaworld.presenter.IPresenter;
import com.android.test.seaworld.presenter.Presenter;
import com.android.test.seaworld.settings.Settings;
import com.android.test.seaworld.view.adapters.MainAdapter;

public class MainActivity extends AppCompatActivity implements IView, AdapterView.OnItemClickListener, View.OnClickListener {

    private GridView seaWorldGrid;

    private Button restartButton;

    private int numOfColumns;
    private int numOfRows;

    private MainAdapter adapter;

    private IPresenter presenter;


    //  Animal[][] animals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restartButton = (Button) findViewById(R.id.restartBtn);
        restartButton.setOnClickListener(this);

        numOfColumns = Settings.getNumOfColumns();
        numOfRows = Settings.getNumOfRows();

        seaWorldGrid = (GridView) findViewById(R.id.seaWorldGrid);
        seaWorldGrid.setNumColumns(numOfColumns);
        // seaWorldGrid.setColumnWidth(seaWorldGrid.getWidth() / numOfColumns);


        seaWorldGrid.setAdapter(adapter);

        seaWorldGrid.setOnItemClickListener(this);

        presenter = new Presenter(new SeaWorldModel(numOfColumns, numOfRows), this);


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
        System.out.println("refreshData");

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.restartBtn:
                presenter.restartWorld();
                break;
        }
    }
}
