package com.example.a2dam.jamp.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.a2dam.jamp.R;

import static android.content.DialogInterface.BUTTON_POSITIVE;

public class Dialog_Change_Password extends DialogFragment implements DialogInterface.OnClickListener {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.Dialogo_Cambio_Contraseña_Title)
                .setMessage(R.string.Dialogo_Cambio_Contraseña_Content)
                .setPositiveButton(R.string.Dialogos_Ok, this);
        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case BUTTON_POSITIVE:
                dialog.dismiss();
                break;
        }
    }
}
