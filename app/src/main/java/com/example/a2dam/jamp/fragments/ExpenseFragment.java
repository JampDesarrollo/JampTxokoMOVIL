package com.example.a2dam.jamp.fragments;

import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a2dam.jamp.logic.ILogic;
import com.example.a2dam.jamp.logic.ILogicFactory;
import com.example.a2dam.jamp.model.PrincipalActivity;
import com.example.a2dam.jamp.R;

import java.util.List;


public class ExpenseFragment extends Fragment implements View.OnClickListener {

    TextView lblAmount;
    AnimationDrawable coinAnimation;
    ImageView imgCoins;
    FrameLayout fLayout;
    float x;
    float y;
    private ILogic ilogic;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_expense, container, false);
        ((PrincipalActivity) getActivity()).getSupportActionBar().setTitle("Gastos");

        lblAmount = view.findViewById(R.id.cantidad);
        imgCoins.setImageResource(R.drawable.coins);
        coinAnimation = (AnimationDrawable) imgCoins.getDrawable();
        fLayout = view.findViewById(R.id.expenseLayout);
        fLayout.setOnClickListener(this::onClick);
        ilogic = ILogicFactory.getILogic();

        imgCoins.post(new Runnable() {
            @Override
            public void run() {
                coinAnimation.start();
            }
        });

        //HACERLO en otro thread?
        //List<Expense> expenseList = ilogic.findExpensesMonth(null);

        return view;
    }


    @Override
    public void onClick(View v) {
        x = lblAmount.getTranslationX();
        y = lblAmount.getTranslationY();
        lblAmount.animate().translationXBy(10f).translationYBy(-600f).scaleXBy(2f).scaleYBy(2f).setDuration(1000);
        lblAmount.setTranslationX(x);
        lblAmount.setTranslationY(y);
    }
}
