package com.example.a2dam.jamp.fragments;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a2dam.jamp.R;
import com.example.a2dam.jamp.logic.ILogic;
import com.example.a2dam.jamp.logic.ILogicFactory;
import com.example.a2dam.jamp.model.PrincipalActivity;


public class ExpenseFragment extends Fragment implements View.OnClickListener {

    private TextView lblAmount;
    private AnimationDrawable coinAnimation;
    private ImageView imgCoins;
    private FrameLayout fLayout;
    protected Boolean max;
    private ILogic ilogic;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_expense, container, false);
        ((PrincipalActivity) getActivity()).getSupportActionBar().setTitle("Gastos");

        lblAmount = view.findViewById(R.id.cantidad);
        imgCoins=view.findViewById(R.id.coin);
        coinAnimation = (AnimationDrawable) imgCoins.getDrawable();
        coinAnimation.run();


        fLayout = view.findViewById(R.id.expenseLayout);
        fLayout.setOnClickListener(this);
        ilogic = ILogicFactory.getILogic();
      
        max=false;

        //HACERLO en otro thread?
        //List<Expense> expenseList = ilogic.findExpensesMonth(null);

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.expenseLayout:
                animacion();
                break;
        }

    }

    private void animacion() {
        if(!max){
            lblAmount.animate().scaleX(2).scaleY(2).setDuration(1000);
            max=true;
        }else{
            lblAmount.animate().scaleX(1).scaleY(1).setDuration(1000);
            max=false;
        }
    }
}
