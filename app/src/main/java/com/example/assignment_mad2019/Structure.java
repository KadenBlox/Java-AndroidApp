package com.example.assignment_mad2019;

public abstract class Structure {
    private int imageId;

    public Structure(int imageId)
    {
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public abstract String getName();

    public abstract int getCost();
}
