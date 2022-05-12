package com.example.assignment_mad2019;

public class Settings {

    private int mapWidth;
    private int mapHeight;
    private int initialMoney;
    private int familySize;
    private int shopSize;
    private int salary;
    private double taxRate;
    private int serviceCost;
    private int houseCost;
    private int commCost;
    private int roadCost;

    public Settings(){

        mapWidth = 50;
        mapHeight = 10;
        initialMoney = 1000;
        familySize = 4;
        shopSize = 6;
        salary = 10;
        taxRate = 0.3;
        serviceCost = 2;
        houseCost = 100;
        commCost = 500;
        roadCost = 20;

    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getInitialMoney() {
        return initialMoney;
    }

    public int getFamilySize() {
        return familySize;
    }

    public int getShopSize() {
        return shopSize;
    }

    public int getSalary() {
        return salary;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public int getServiceCost() {
        return serviceCost;
    }

    public int getHouseCost() {
        return houseCost;
    }

    public int getCommCost() {
        return commCost;
    }

    public int getRoadCost() {
        return roadCost;
    }

    public void setMapWidth(int mapWidth) {
        //Error handling done in settings activity
        this.mapWidth = mapWidth;
    }

    public void setMapHeight(int mapHeight) {
        //Error handling done in settings activity
        this.mapHeight = mapHeight;
    }

    public void setInitialMoney(int initialMoney) {
        //Error handling done in settings activity
        this.initialMoney = initialMoney;
    }
}
