package com.example.assignment_mad2019;

public class Commercial extends Structure {

    public Commercial(int imageId)
    {
        super(imageId);
    }

    @Override
    public String getName()
    {
        return "Commercial";
    }

    @Override
    public int getCost()
    {
        return GameData.getGameData().getSettings().getCommCost();
    }
}
