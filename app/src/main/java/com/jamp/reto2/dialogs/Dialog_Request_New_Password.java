package com.jamp.reto2.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import com.jamp.reto2.R;

import static android.content.DialogInterface.BUTTON_POSITIVE;

public class Dialog_Request_New_Password extends DialogFragment implements DialogInterface.OnClickListener{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Establecer en el activity el estilo Dialog_custom diseñado para los dialogos
        getActivity().setTheme(R.style.Dialog_custom);
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //establece un titulo
        builder.setTitle(R.string.Dialogo_Solicitud_Nueva_Contrasena_Title)
                //establece un mensaje
                .setMessage(R.string.Dialogo_Solicitud_Nueva_Contrasena_Content)
                //establece un boton positivo
                .setPositiveButton(R.string.Dialogos_Ok, this);
        // Create the AlertDialog object and return it
        return builder.create();
    }


    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case BUTTON_POSITIVE://si clica en el boton positivo
                //cierra el dialogo
                dialog.dismiss();
                break;
        }
    }
}
