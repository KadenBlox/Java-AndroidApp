package com.example.assignment_mad2019.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment_mad2019.GameData;
import com.example.assignment_mad2019.MapElement;
import com.example.assignment_mad2019.R;
import com.example.assignment_mad2019.Structure;

import java.util.ArrayList;
import java.util.List;


public class MapFragment extends Fragment {

    private static SelectorFragment selector;
    private final int PADDING_MOD = 40;
    private GameData gData;
    private List<MapElement> data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle)
    {
        gData = GameData.getGameData();
        View view = inflater.inflate(R.layout.fragment_map,ui,false);

        RecyclerView rView = view.findViewById(R.id.mapRecyclerView);

        rView.setLayoutManager(new GridLayoutManager(getContext(),
                gData.getMapHeight(),
                GridLayoutManager.HORIZONTAL,false));

        /*
        Load map from game data and create bitmaps for any preexisting/loaded
        structures. Add to data to be passed to recycler adapter.
         */
        for(int ii = 0; ii < gData.getMapWidth(); ii++)
        {
            for(int jj = 0; jj < gData.getMapHeight(); jj++)
            {
                MapElement e = new MapElement(gData.getMap()[ii][jj]);
                if(e.getStructure() != null && e.getImage() == null)
                {
                    e.setImage(createBitmap(e.getStructure().getImageId()));
                }
                else if(e.getStructure() == null)
                {
                    e.setImage(null);
                }

                data.add(e);
            }
        }

        mapAdapter adapter = new mapAdapter(data);

        rView.setAdapter(adapter);

        Button addTime = view.findViewById(R.id.btnTime);

        addTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gData.incTime(1);
            }
        });

        return view;
    }

    /*
    When the view is unloaded (eg going back to main menu or details activity)
    this saves the structures on the map back to the GameData class.

    TODO: implement saving to database as well.
     */
    public void onStop() {
        super.onStop();

        for(int ii = 0; ii < gData.getMapWidth(); ii++)
        {
            for(int jj = 0; jj < gData.getMapHeight(); jj++)
            {
                gData.getMap()[ii][jj] = data.get(coordToColumnMajor(ii,jj,
                        gData.getMapHeight()));
            }
        }
    }

    private class mapAdapter extends RecyclerView.Adapter<mapAdapter.mapVHolder>
    {
        private List<MapElement> data;

        public mapAdapter(List<MapElement> data)
        {
            this.data = data;
        }

        @Override
        public int getItemCount()
        {
            return data.size();
        }

        @Override
        public mapVHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater li = LayoutInflater.from(getActivity());
            return new mapVHolder(li,parent);
        }

        @Override
        public void onBindViewHolder(mapVHolder vh, int index)
        {
            vh.bind(data.get(index),index);
        }


        private class mapVHolder extends RecyclerView.ViewHolder
        {
            private ImageView structure;
            private ImageView terrain;
            private TextView temp;
            private MapElement data;

            /*PACKAGE PRIVATE*/ mapVHolder(LayoutInflater li, ViewGroup parent)
            {
                super(li.inflate(R.layout.grid_cells,parent,false));

                terrain = itemView.findViewById(R.id.terrainImg);
                structure = itemView.findViewById(R.id.structureImg);
                temp = itemView.findViewById(R.id.txtTemp);

                /*
                Scale grid elements to be square via padding instead of rounding the map
                size +1 as in prac 3. Looks better and has more consistent border sizing.
                 */
                int size = parent.getMeasuredHeight() / (GameData.getGameData().getMapHeight());
                int pad = size / PADDING_MOD;

                ViewGroup.LayoutParams lp = itemView.getLayoutParams();
                lp.width = size;
                lp.height = size;

                /*
                terrain ImageView adds borders between elements but does not require
                an image within.
                 */
                terrain.setBackgroundColor(getResources().getColor(R.color.terrain));
                itemView.setPadding(pad,pad,pad,pad);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //get selected structure from selector.
                        Structure structure = selector.getSelectedStruc();

                        //if no structure currently there and structure selected
                        if(structure != null && data.getStructure() == null)
                        {
                            if(buyStructure(structure))
                            {
                                data.setStructure(structure);
                                data.setImage(createBitmap(structure.getImageId()));
                                notifyItemChanged(getAdapterPosition());
                            }
                            else
                            {
                                Toast.makeText(getContext(), "Insufficient Funds",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                        else if(data.getStructure() != null)
                        {
                            Toast.makeText(getContext(), "Must destroy structure first!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        //bring up details activity/fragment. return true to stop further processing.
                        return false;
                    }
                });
            }

            /*PACKAGE PRIVATE*/ void bind(MapElement element, int index)
            {
                data = element;
                structure.setImageBitmap(element.getImage());
                temp.setText(Integer.toString(index));
            }

            private boolean buyStructure(Structure inStruct)
            {
                int currMoney = gData.getMoney();
                if(inStruct.getCost() <= currMoney)
                {
                    currMoney -= inStruct.getCost();
                    gData.setMoney(currMoney);

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
    }

    /*PACKAGE PRIVATE*/ static void setSelector(SelectorFragment inSel)
    {
        selector = inSel;
    }

    //creates a drawable from a drawable resource.
    private Bitmap createBitmap(int resource)
    {
        Drawable drawable = getResources().getDrawable(resource);
        Bitmap out = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(out);
        drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
        drawable.draw(canvas);

        return out;
    }

    /*Converts grid coordinates to column major order integer.
    * Assumes input is an Array index eg top left = 0,0
    * and height is the length/size of the outer Array.*/
    private int coordToColumnMajor(int x, int y, int height)
    {
        int position;

        position = (x * height) + (y);

        return position;
    }

}
