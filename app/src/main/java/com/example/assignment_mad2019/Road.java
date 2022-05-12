package com.example.assignment_mad2019;

public class Road extends Structure {

    public Road(int imageId)
    {
        super(imageId);
    }

    @Override
    public String getName()
    {
        return "Road";
    }

    @Override
    public int getCost()
    {
        return GameData.getGameData().getSettings().getRoadCost();
    }
}
