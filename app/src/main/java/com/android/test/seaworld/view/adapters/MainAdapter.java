package com.android.test.seaworld.view.adapters;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.android.test.seaworld.model.animals.Animal;
import com.android.test.seaworld.settings.Settings;

public class MainAdapter extends BaseAdapter {
    private Context context;

    private GridView gridView;
    private Animal[][] animals;

    private int numOfColumns;
    private int numOfLines;

    public MainAdapter(Context context, GridView gridView, Animal[][] animals) {
        this.context = context;
        this.gridView = gridView;
        this.animals = animals;

        numOfColumns = Settings.getNumOfColumns();
        numOfLines = Settings.getNumOfRows();
    }


    @Override
    public int getCount() {
        return numOfColumns * numOfLines;
    }

    @Override
    public Object getItem(int i) {
        return animals[i % numOfColumns][(int) (i / numOfColumns)];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        Animal animal;
        animal = (Animal) (getItem(i));

        imageView = new ImageView(context);

        imageView.setImageResource(animal.getDrawableResourceId());

        ViewGroup.LayoutParams param = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                gridView.getHeight() / numOfLines);
        imageView.setLayoutParams(param);

        return imageView;
    }
}
