package com.example.a2dam.jamp.fragments;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
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
import com.example.a2dam.jamp.logics.UserLogic;
import com.example.a2dam.jamp.others.ILogicFactory;
import com.example.a2dam.jamp.models.PrincipalActivity;


public class ExpenseFragment extends Fragment implements View.OnClickListener {

    protected TextView lblAmount;
    protected AnimationDrawable coinAnimation;
    protected ImageView imgCoins;
    protected FrameLayout fLayout;
    protected Boolean max;
    protected UserLogic ilogic;
    protected View view ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        /*if (savedInstanceState != null) {
            view= inflater.inflate(R.layout.fragment_expense_landscape, container, false);
        }else{
            view= inflater.inflate(R.layout.fragment_expense, container, false);
        }*/

        if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){
            view= inflater.inflate(R.layout.fragment_expense_landscape, container, false);
        }else if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_PORTRAIT){
            view= inflater.inflate(R.layout.fragment_expense, container, false);
        }
        ((PrincipalActivity) getActivity()).getSupportActionBar().setTitle(R.string.fragment_expense_title);

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

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(getActivity().getRequestedOrientation()==ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            outState.putBoolean("state", true);
        }else if(getActivity().getRequestedOrientation()==ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            outState.putBoolean("state", false);
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
