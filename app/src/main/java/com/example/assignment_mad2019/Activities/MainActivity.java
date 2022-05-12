package com.example.assignment_mad2019.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.assignment_mad2019.GameData;
import com.example.assignment_mad2019.MapElement;
import com.example.assignment_mad2019.R;
import com.example.assignment_mad2019.Structure;
import com.example.assignment_mad2019.StructureData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = findViewById(R.id.btnStart);
        Button settings = findViewById(R.id.btnSettings);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startSettings = new Intent(MainActivity.this, SettingsActivity.class);

                startActivity(startSettings);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent startMap = new Intent(MainActivity.this, MapActivity.class);

                startActivity(startMap);
            }
        });
    }
}
