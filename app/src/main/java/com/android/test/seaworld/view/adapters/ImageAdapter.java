package com.android.test.seaworld.view.adapters;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.android.test.seaworld.model.animals.Animal;
import com.android.test.seaworld.utils.Settings;

public class ImageAdapter extends BaseAdapter {
    private Context context;

    private int numOfColumns;
    private int numOfRows;

    private GridView gridView;
    private Animal[][] animals;

    public ImageAdapter(Context context, GridView gridView, Animal[][] animals) {
        this.context = context;
        numOfColumns = Settings.numOfColumns;
        numOfRows = Settings.numOfRows;
        this.gridView = gridView;
        this.animals = animals;
    }

    @Override
    public int getCount() {
        return numOfColumns * numOfRows;
    }

    @Override
    public Object getItem(int i) {
        return animals[i % numOfColumns][(i / numOfColumns)];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new GridView.LayoutParams(gridView.getHeight() / numOfRows,
                gridView.getHeight() / numOfRows));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setPadding(1, 1, 1, 1);

        Animal animal = (Animal) (getItem(i));
        if (animal != null) {
            imageView.setImageResource(animal.getAnimalImageResource());
        } else {
            imageView.setImageResource(0);
        }
        return imageView;
    }
}
