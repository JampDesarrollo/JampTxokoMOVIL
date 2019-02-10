package com.jamp.reto2.fragments;

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

import com.jamp.reto2.R;
import com.jamp.reto2.models.PrincipalActivityController;

/**
 * Fragment used to diplay the user the amount to pay on the month.
 * @author Ander
 */
public class ExpenseFragmentController extends Fragment implements View.OnClickListener {

    /**
     * Amount textView.
     */
    private TextView lblAmount;
    /**
     * Animation Drawable.
     */
    private AnimationDrawable coinAnimation;
    /**
     * Imageview for the coin.
     */
    private ImageView imgCoins;
    /**
     * FrameLayout
     */
    private FrameLayout fLayout;
    /**
     * Boolean Max.
     */
    private Boolean max;
    /**
     * Expense Logic object.
     */
    //private ExpenseLogic ilogic;
    /**
     * View.
     */
    private View view ;
    /**
     * Float number of the amount.
     */
    public Float cant;

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
        if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){//si la orientacion es horizontal
            //infla el layout de expenses horizontal
            view= inflater.inflate(R.layout.fragment_expense_landscape, container, false);
        }else if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_PORTRAIT){//si la orientacion es vertical
            //carga el layout vertical
            view= inflater.inflate(R.layout.fragment_expense, container, false);
        }
        //mostrar la barra superior
        ((PrincipalActivityController) getActivity()).getSupportActionBar().setTitle(R.string.fragment_expense_title);

        //referenciar todas la variables
        lblAmount = view.findViewById(R.id.cantidad);
        lblAmount.setText(R.string.fragment_events_event_price);
        imgCoins=view.findViewById(R.id.coin);
        coinAnimation = (AnimationDrawable) imgCoins.getDrawable();
        //iniciar la animacion
        coinAnimation.run();

        //referenciar y escuchar el layout para hacer la otra animacion
        fLayout = view.findViewById(R.id.expenseLayout);
        fLayout.setOnClickListener(this);
      
        max=false;

        //CODE TO GET THE TOTAL AMOUNT OF THE MONTH
        /*
        try {
            cant = ilogic.findMonthExpensesSingleUser(1);
        } catch (BusinessLogicException e) {
            e.printStackTrace();
        }*/

        return view;
    }

    /**
     * On click method for the UI animation.
     * @param v view
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.expenseLayout://si clica en el layout
                //llama al metodo de la animacion
                animacion();
                break;
        }
    }

    /**
     * Method for the animation.
     */
    private void animacion() {
        if(!max){//si max es false
            //escala el tamaño de los gastos x2
            lblAmount.animate().scaleX(2).scaleY(2).setDuration(1000);
            //pone max a true para que la siguiente vez que clique escale para abajo
            max=true;
        }else{//si max en false
            //escala el tamaño de los gastos x1
            lblAmount.animate().scaleX(1).scaleY(1).setDuration(1000);
            //pone max a false para que la siguiente vez que clique escale para arriba
            max=false;
        }
    }
}
