package com.example.assignment_mad2019.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.assignment_mad2019.R;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFrag = (MapFragment) fm.findFragmentById(R.id.fragMap);
        SelectorFragment selFrag = (SelectorFragment) fm.findFragmentById(R.id.fragSelector);
        InfoFragment infoFrag = (InfoFragment) fm.findFragmentById(R.id.fragInfo);

        if(mapFrag == null)
        {
            mapFrag = new MapFragment();
            fm.beginTransaction().add(R.id.fragMap, mapFrag).commit();
        }

        if(selFrag == null)
        {
            selFrag = new SelectorFragment();
            fm.beginTransaction().add(R.id.fragSelector, selFrag).commit();
        }

        if(infoFrag == null)
        {
            infoFrag = new InfoFragment();
            fm.beginTransaction().add(R.id.fragInfo, infoFrag).commit();
        }
    }
}
