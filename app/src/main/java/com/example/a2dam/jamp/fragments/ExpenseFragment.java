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
import com.example.a2dam.jamp.exceptions.BusinessLogicException;
import com.example.a2dam.jamp.logic.ExpenseLogic;
import com.example.a2dam.jamp.others.ILogicFactory;
import com.example.a2dam.jamp.models.PrincipalActivity;

/**
 * Fragment used to diplay the user the amount to pay on the month.
 * @author Ander
 */
public class ExpenseFragment extends Fragment implements View.OnClickListener {
    /**
     * Amount textView.
     */
    protected TextView lblAmount;
    /**
     * Animation Drawable.
     */
    protected AnimationDrawable coinAnimation;
    /**
     * Imageview for the coin.
     */
    protected ImageView imgCoins;
    /**
     * FrameLayout
     */
    protected FrameLayout fLayout;
    /**
     * Boolean Max.
     */
    protected Boolean max;
    /**
     * Expense Logic object.
     */
    protected ExpenseLogic ilogic;
    /**
     * View.
     */
    protected View view ;
    /**
     * Float number of the amount.
     */
    protected Float cant;

    /**
     * On create view method for the fragment.
     * @param inflater inf
     * @param container cont
     * @param savedInstanceState instance
     * @return View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        ilogic = ILogicFactory.getExpenseLogic();
      
        max=false;

        //CODE TO GET THE TOTAL AMOUNT OF THE MONTH
        /*try {
            cant = ilogic.findMonthExpensesSingleUser(1);
        } catch (BusinessLogicException e) {
            e.printStackTrace();
        }
        lblAmount.setText(Float.toString(cant) + "€");*/
        return view;
    }

    /**
     * On click method for the UI animation.
     * @param v view
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.expenseLayout:
                animacion();
                break;
        }
    }

    /**
     * Method for the animation.
     */
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
