package com.android.test.seaworld.view.adapters;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.android.test.seaworld.model.animals.Animal;
import com.android.test.seaworld.utils.Settings;

public class MainAdapter extends BaseAdapter {
    private Context context;

    private int numOfColumns;
    private int numOfRows;

    private GridView gridView;
    private Animal[][] animals;

    public MainAdapter(Context context, GridView gridView, Animal[][] animals) {
        this.context = context;
        numOfColumns = Settings.getNumOfColumns();
        numOfRows = Settings.getNumOfRows();
        this.gridView = gridView;
        this.animals = animals;
    }

    @Override
    public int getCount() {
        return numOfColumns * numOfRows;
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
        ImageView imageView = new ImageView(context);

        Animal animal = (Animal) (getItem(i));

        if (animal != null)
            imageView.setImageResource(animal.getDrawableResourceId());
        else
            imageView.setImageResource(0);

        ViewGroup.LayoutParams param = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, gridView.getHeight() / numOfRows);
        imageView.setLayoutParams(param);

        imageView.requestLayout();

        return imageView;
    }
}
