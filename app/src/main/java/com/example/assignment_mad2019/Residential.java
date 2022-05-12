package com.example.assignment_mad2019;

public class Residential extends Structure {

    public Residential(int imageId)
    {
        super(imageId);
    }

    @Override
    public String getName()
    {
        return "Residential";
    }

    @Override
    public int getCost()
    {
        return GameData.getGameData().getSettings().getHouseCost();
    }
}
