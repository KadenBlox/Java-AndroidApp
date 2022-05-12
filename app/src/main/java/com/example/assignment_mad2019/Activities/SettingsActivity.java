package com.example.assignment_mad2019.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment_mad2019.GameData;
import com.example.assignment_mad2019.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //ANDROID VARS
        //Buttons
        Button back = findViewById(R.id.btnSetBack);
        Button apply = findViewById(R.id.btnSetApply);

        //TextViews
        //No need to modify or access the text boxes on this page.

        //Edit Texts
        final EditText mapW = findViewById(R.id.inSetMW);
        final EditText mapH = findViewById(R.id.inSetMH);
        final EditText initMoney = findViewById(R.id.inSetMoney);

        //Handling saving user defined settings to GameData
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] temp = new int[3];
                boolean invalid = false;

                //Getting value of map width and detecting a lack of change in value
                if(mapW.getText().toString().equals("Default"))
                {
                    temp[0] = -1; //-1 reserved for default values.
                }
                else if(Integer.parseInt(mapW.getText().toString()) > 0)
                {
                    temp[0] = Integer.parseInt(mapW.getText().toString());
                }
                else
                {
                    //Displaying error to user for out of bounds size
                    String eMsg = "Map width must be greater than 0!";
                    Toast.makeText(getApplicationContext(),eMsg,Toast.LENGTH_SHORT).show();
                    invalid = true;
                }

                //Getting value of map Height and detecting a lack of change in value
                if(mapH.getText().toString().equals("Default"))
                {
                    temp[1] = -1; //-1 reserved for default values.
                }
                else if(Integer.parseInt(mapH.getText().toString()) > 0)
                {
                    temp[1] = Integer.parseInt(mapH.getText().toString());
                }
                else
                {
                    //Displaying error to user for out of bounds size
                    String eMsg = "Map height must be greater than 0!";
                    Toast.makeText(getApplicationContext(),eMsg,Toast.LENGTH_SHORT).show();
                    invalid = true;
                }

                //Getting value of money and detecting a lack of change in value
                if(initMoney.getText().toString().equals("Default"))
                {
                    temp[2] = -1; //-1 reserved for default values.
                }
                else if(Integer.parseInt(initMoney.getText().toString()) > 0)
                {
                    temp[2] = Integer.parseInt(initMoney.getText().toString());
                }
                else
                {
                    //Displaying error to user for out of bounds size
                    String eMsg = "Initial Money must be greater than 0!";
                    Toast.makeText(getApplicationContext(),eMsg,Toast.LENGTH_SHORT).show();
                    invalid = true;
                }

                if(!invalid)
                {
                    applySettings(temp);
                }
            }
        });

        //Handling returning to previous activity.
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private void applySettings(int[] settings)
    {
        //[0] Width, [1] Height, [2] Initial Money.
        GameData data = GameData.getGameData();

        //setting the user adjustable settings via the gamedata singleton.
        //if -1 the default is already set by the default settings constructor.
        if(settings[0] != -1)
        {
            data.getSettings().setMapWidth(settings[0]);
        }

        if(settings[1] != -1) {
            data.getSettings().setMapHeight(settings[1]);
        }

        if(settings[2] != -1) {
            data.getSettings().setInitialMoney(settings[2]);
        }

        data.updateSettings();
    }

}
