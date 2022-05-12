package com.example.assignment_mad2019;

public class StructureData {
    private static StructureData instance = null;
    private Residential[] residential;
    private Commercial[] commercial;
    private Road[] roads;

    public StructureData()
    {
        //residential building images
        residential = new Residential[4];

        residential[0] = new Residential(R.drawable.ic_building1);
        residential[1] = new Residential(R.drawable.ic_building2);
        residential[2] = new Residential(R.drawable.ic_building3);
        residential[3] = new Residential(R.drawable.ic_building4);

        //commercial building images
        commercial = new Commercial[4];

        commercial[0] = new Commercial(R.drawable.ic_building5);
        commercial[1] = new Commercial(R.drawable.ic_building6);
        commercial[2] = new Commercial(R.drawable.ic_building7);
        commercial[3] = new Commercial(R.drawable.ic_building8);

        //road images
        roads = new Road[15];

        //index 0-3 N,E,S,W
        roads[0] = new Road(R.drawable.ic_road_n);
        roads[1] = new Road(R.drawable.ic_road_e);
        roads[2] = new Road(R.drawable.ic_road_s);
        roads[3] = new Road(R.drawable.ic_road_w);

        //index 4 EW
        roads[4] = new Road(R.drawable.ic_road_ew);

        //index 5-7 N*
        roads[5] = new Road(R.drawable.ic_road_ne);
        roads[6] = new Road(R.drawable.ic_road_ns);
        roads[7] = new Road(R.drawable.ic_road_nw);

        //index 8-10 N**
        roads[8] = new Road(R.drawable.ic_road_new);
        roads[9] = new Road(R.drawable.ic_road_nse);
        roads[10] = new Road(R.drawable.ic_road_nsw);

        //index 11-12 S*
        roads[11] = new Road(R.drawable.ic_road_se);
        roads[12] = new Road(R.drawable.ic_road_sw);

        //index 13 SEW
        roads[13] = new Road(R.drawable.ic_road_sew);

        //index 14 NSEW
        roads[14] = new Road(R.drawable.ic_road_nsew);
    }

    public static StructureData getInstance()
    {
        if(instance == null)
        {
            instance = new StructureData();
            return instance;
        }
        else
        {
            return instance;
        }
    }

    public Residential[] getRes()
    {
        return residential.clone();
    }

    public Residential getResI(int i)
    {
        Residential ret = null;

        if(i <= (residential.length-1))
        {
            ret = residential[i];
        }

        return ret;
    }

    public Commercial[] getCommercial()
    {
        return commercial.clone();
    }

    public Commercial getCommI(int i)
    {
        Commercial ret = null;

        if(i <= (commercial.length-1))
        {
            ret = commercial[i];
        }

        return ret;
    }

    public Road[] getRoads()
    {
        return roads.clone();
    }

    public Road getRoadsI(int i)
    {
        Road ret = null;

        if(i <= (roads.length - 1))
        {
            ret = roads[i];
        }

        return ret;
    }

}
