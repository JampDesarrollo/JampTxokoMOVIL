package com.example.a2dam.jamp.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a2dam.jamp.R;

import static android.content.DialogInterface.BUTTON_POSITIVE;

public class Dialog_Productos_Fragment extends DialogFragment implements DialogInterface.OnClickListener {
    protected Button btnPlus,btnMinus;
    protected EditText textCount;
    //protected Integer count;

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //textCount.findViewById(R.id.tfCount);
        //btnPlus.findViewById(R.id.btnPlus);
        //btnPlus.setOnClickListener(this);
        //btnMinus.findViewById(R.id.btnMinus);
       //btnMinus.setOnClickListener(this);
        //count = 0;
        builder.setView(R.layout.fragment_product_dialog)
                .setPositiveButton(R.string.Dialogos_Ok,this);
        return builder.create();
    }
/*
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPlus:
                //count++;
                //textCount.setText(count);
                break;
            case R.id.btnMinus:
                //count--;
               // textCount.setText(count);
                break;
        }
    }*/

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case BUTTON_POSITIVE:
                dialog.dismiss();
                break;
        }
    }
}
