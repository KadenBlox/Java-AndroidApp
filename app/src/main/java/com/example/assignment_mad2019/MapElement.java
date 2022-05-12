package com.example.assignment_mad2019;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;


public class MapElement {
    private Structure structure;
    private Bitmap image;
    private String ownerName;

    public MapElement()
    {
        structure = null;
        image = null;
        ownerName = "Default";
    }

    public MapElement(Structure s)
    {
        structure = s;
        image = null;
        ownerName = "Default";
    }

    public MapElement(MapElement e)
    {
        structure = e.structure;
        image = e.image;
        ownerName = e.ownerName;
    }

    public Structure getStructure()
    {
        return structure;
    }

    public void setStructure(Structure s)
    {
        structure = s;
    }

    public void setImage(Bitmap img)
    {
        image = img;
    }

    public Bitmap getImage()
    {
        return image;
    }
}
