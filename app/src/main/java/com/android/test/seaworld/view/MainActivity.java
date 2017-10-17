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
import com.android.test.seaworld.utils.Settings;
import com.android.test.seaworld.view.adapters.ImageAdapter;

public class MainActivity extends AppCompatActivity implements IView, AdapterView.OnItemClickListener, View.OnClickListener {

    private GridView mGridView;
    private ImageAdapter adapter;
    private IPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mRestartButton = (Button) findViewById(R.id.activity_main_button_restart);
        mRestartButton.setOnClickListener(this);

        int numOfColumns = Settings.getNumOfColumns();
        mGridView = (GridView) findViewById(R.id.activity_main_greed_view);
        mGridView.setNumColumns(numOfColumns);
        mGridView.setAdapter(adapter);
        mGridView.setOnItemClickListener(this);

        presenter = new Presenter(new SeaWorldModel(), this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        presenter.oneTact();
    }

    @Override
    public void setData(Animal[][] animals) {
        adapter = new ImageAdapter(this, mGridView, animals);
        mGridView.setAdapter(adapter);
    }

    @Override
    public void refreshData() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_main_button_restart:
                presenter.restartWorld();
                break;
        }
    }
}
