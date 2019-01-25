package com.example.a2dam.jamp.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.a2dam.jamp.R;

import static android.content.DialogInterface.BUTTON_NEGATIVE;
import static android.content.DialogInterface.BUTTON_POSITIVE;

public class Dialog_LogOut extends DialogFragment implements DialogInterface.OnClickListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Establecer en el activity el estilo Dialog_custom diseñado para los dialogos
        getActivity().setTheme(R.style.Dialog_custom);
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.Dialogo_LogOut_Title)
                .setNegativeButton(R.string.Dialogo_LogOut_no,this)
                .setPositiveButton(R.string.Dialogo_LogOut_yes, this);

        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case BUTTON_POSITIVE:
                getActivity().finish();
                break;
            case BUTTON_NEGATIVE:
                this.dismiss();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //volver a establecer en el activity el tema por defecto
        getActivity().setTheme(R.style.AppTheme);
    }
}
