package com.example.assignment_mad2019.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment_mad2019.R;
import com.example.assignment_mad2019.Structure;
import com.example.assignment_mad2019.StructureData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SelectorFragment extends Fragment {

    private Structure selectedStruc;
    private int selectedItem = RecyclerView.NO_POSITION;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle){
        View view = inflater.inflate(R.layout.fragment_selector,ui,false);
        StructureData sData = StructureData.getInstance();
        MapFragment.setSelector(this);

        RecyclerView rView = view.findViewById(R.id.selecterRecyclerView);
        //creating horizontal linear layout
        LinearLayoutManager lm = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        rView.setLayoutManager(lm);

        List<Structure> data = new ArrayList<>();

        Collections.addAll(data,sData.getRes());
        Collections.addAll(data,sData.getCommercial());
        Collections.addAll(data,sData.getRoads());

        selAdapter adapter = new selAdapter(data);

        rView.setAdapter(adapter);

        return view;
    }

    private class selAdapter extends RecyclerView.Adapter<selAdapter.selVHolder> {
        private List<Structure> data;

        /*PACKAGE PRIVATE*/ selAdapter(List<Structure> data) {
            this.data = data;
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        @Override
        public selVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater li = LayoutInflater.from(getActivity());
            return new selVHolder(li, parent);
        }

        @Override
        public void onBindViewHolder(selVHolder vh, int index) {
            vh.bind(data.get(index));
            if (selectedItem == index) {
                vh.itemView.setBackgroundColor(Color.GRAY);
            } else {
                vh.itemView.setBackgroundColor(Color.TRANSPARENT);
            }

        }

        /*
        VHolder class nested within adapter to allow access to the getAdaptorPostion()
        method. This is to allow highlighting of elements that ACTUALLY works. For more
        information refer to https://stackoverflow.com/a/35360331 where inspiration was drawn.
         */
        private class selVHolder extends RecyclerView.ViewHolder {
            private TextView strucName;
            private ImageView strucImg;
            private Structure data;

            /*PACKAGE PRIVATE*/ selVHolder(LayoutInflater li, ViewGroup parent) {
                super(li.inflate(R.layout.list_selection, parent, false));

                strucName = itemView.findViewById(R.id.strucName);
                strucImg = itemView.findViewById(R.id.strucImg);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //double check that an item is actually selected
                        if (selectedItem != RecyclerView.NO_POSITION) {
                            //if item already selected.
                            if(selectedItem == getAdapterPosition())
                            {
                                int prevSel = selectedItem;
                                setSelectedStruc(null); //clear structure data.

                                //set selected item to "nowhere"
                                selectedItem = RecyclerView.NO_POSITION;
                                //notify adapter of change to previous selection (make transparent).
                                notifyItemChanged(prevSel);
                            }
                            //If item not already selected
                            else
                            {
                                setSelectedStruc(data); //set structure data to selected item

                                //notify adapter to update background of old selected item
                                notifyItemChanged(selectedItem);
                                selectedItem = getAdapterPosition();
                                //update background of new selected item
                                notifyItemChanged(selectedItem);
                            }
                        }
                        else
                        {
                            //set new highlight.
                            selectedItem = getAdapterPosition();
                            //highlight position.
                            notifyItemChanged(selectedItem);

                            //set structure data.
                            setSelectedStruc(data);
                        }
                    }
                });
            }

            /*PACKAGE PRIVATE*/ void bind(Structure structure) {
                data = structure;
                strucName.setText(structure.getName());
                strucImg.setImageDrawable(getResources().getDrawable(structure.getImageId()));
            }
        }
    }

    private void setSelectedStruc(Structure structure)
    {
        selectedStruc = structure;
    }

    public Structure getSelectedStruc()
    {
        return selectedStruc;
    }
}
