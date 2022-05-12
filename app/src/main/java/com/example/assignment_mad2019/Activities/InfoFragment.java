package com.example.assignment_mad2019.Activities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assignment_mad2019.GameData;
import com.example.assignment_mad2019.R;

public class InfoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup ui,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info,ui,false);
        final GameData gData = GameData.getGameData();

        final TextView time = view.findViewById(R.id.txtTime);
        final TextView money = view.findViewById(R.id.txtMoney);
        TextView income = view.findViewById(R.id.txtIncome);
        TextView pop = view.findViewById(R.id.txtPop);
        TextView emp = view.findViewById(R.id.txtEmp);

        time.setText(Integer.toString(gData.getGTime()));
        money.setText(Integer.toString(gData.getMoney()));
        income.setText("$0");
        pop.setText("0");
        emp.setText("0");

        gData.setMListner(new GameData.MoneyListner() {
            @Override
            public void onChange() {
                money.setText(Integer.toString(gData.getMoney()));
            }
        });

        gData.setTListner(new GameData.TimeListner() {
            @Override
            public void onChange() {
                time.setText(Integer.toString(gData.getGTime()));
            }
        });

        return view;
    }

}
