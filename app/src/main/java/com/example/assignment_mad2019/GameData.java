package com.example.assignment_mad2019;

public class GameData {
    private static GameData instance = null;

    private static Settings settings;

    private static MapElement[][] map;

    private static int money;
    private MoneyListner mListner;

    private static int gameTime;
    private TimeListner tListner;

    private GameData()
    {
        settings = new Settings();
        map = initialiseMap();
        money = settings.getInitialMoney();
        gameTime = 0;
    }

    public static GameData getGameData()
    {
        if(instance == null)
        {
            instance = new GameData();
            return instance;
        }
        else
        {
            return instance;
        }
    }

    /*
    GAME LOGIC METHODS
    make the game go go gadget pls and thank you.
     */

    /*
    TIME METHODS:
    - getGTime() : int
    - incTime(int) : void
    - INTERFACE TimeListner
        - ABSTRACT onChange() : void
    - getTListner() : TimeListner
    - setTListner(TimeListner) : void
     */

    public int getGTime()
    {
        return gameTime;
    }

    public void incTime(int increment)
    {
        gameTime += increment;
        if(tListner != null)
        {
            tListner.onChange();
        }
    }

    public interface TimeListner
    {
        public void onChange();
    }

    public TimeListner getTListner()
    {
        return tListner;
    }

    public void setTListner(TimeListner inTL)
    {
        tListner = inTL;
    }

    /***************************************************************************
    MONEY METHODS:
     - getMoney() : int
     - setMoney(int) : void
     - INTERFACE MoneyListner
        - ABSTRACT onChange() : void
     - setMListner(MoneyListner) : void
     - getmLister() : MoneyLister
     **************************************************************************/

    public int getMoney()
    {
        return money;
    }

    public void setMoney(int inMoney)
    {
        money = inMoney;
        if(mListner != null)
        {
            mListner.onChange();
        }
    }

    public interface MoneyListner
    {
        void onChange();
    }

    public void setMListner(MoneyListner in)
    {
        mListner = in;
    }

    public MoneyListner getMListner()
    {
        return mListner;
    }

    /*
    END MONEY METHODS.
     */

    /***************************************************************************
    MAP METHODS:
     - getMap() : MapElement[][]
     - getMapWidth() : int
     - getMapHeight() : int
     - isEmpty(int, int) : boolean
     - validCoord(int, int) : boolean
     - initialiseMap() : MapElement[][]
     **************************************************************************/

    public MapElement[][] getMap()
    {
        return map;
    }

    public int getMapWidth()
    {
        return map.length;
    }

    public int getMapHeight()
    {
        return map[0].length;
    }

    //Checks map if coordinates already contain an element
    public boolean isEmpty(int x, int y) //throws coordinateException
    {
        boolean ret = false;
        //try {
            //validate input coords.
            if (validCoord(x, y)) {
                if (map[x][y] == null) //check if coords empty
                {
                    ret = true;
                } else {
                    ret = false;
                }
            }
        /*}catch(coordinateException e) //catch and rethrow coordinateException
        {
            throw new coordinateException(e.getMessage());
        }*/

        return ret;
    }

    public boolean validCoord(int x, int y) //throws coordinateException
    {
        //validate input coords.
        if(x < settings.getMapWidth() && y < settings.getMapHeight()) {
            return true;
        }
        else
        {
            return false;
            //throw new coordinateException("Coords X: " + x + " Y: " + y + "out of bounds");
        }
    }

    private MapElement[][] initialiseMap()
    {
        MapElement[][] out = new MapElement[settings.getMapWidth()][settings.getMapHeight()];

        for(int ii = 0; ii < getSettings().getMapWidth(); ii++)
        {
            for(int jj = 0; jj < getSettings().getMapHeight(); jj++)
            {
                out[ii][jj] = new MapElement();
                        //StructureData.getInstance().getResI(1));
            }
        }

        return out;
    }

    /*
    END MAP METHODS
     */

    /***************************************************************************
    SETTINGS METHODS:
     - getSettings : settings
     - updateSettings() : void
     **************************************************************************/

    public Settings getSettings()
    {
        return settings;
    }


    public void updateSettings()
    {
        map = initialiseMap();
        money = settings.getInitialMoney();
    }

    /*
    END SETTINGS METHODS
     */

}
