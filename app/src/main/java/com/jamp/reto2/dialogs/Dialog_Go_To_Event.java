package com.jamp.reto2.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.jamp.reto2.R;

import static android.content.DialogInterface.BUTTON_NEGATIVE;
import static android.content.DialogInterface.BUTTON_POSITIVE;

public class Dialog_Go_To_Event extends DialogFragment implements DialogInterface.OnClickListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Establecer en el activity el estilo Dialog_custom diseñado para los dialogos
        getActivity().setTheme(R.style.Dialog_custom);
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //establece un titulo
        builder.setTitle(R.string.Dialogo_Asistir_Al_Evento_Title)
                //establece un mensaje
                .setMessage(R.string.Dialogo_Asistir_Al_Evento_Content)
                //establece un boton positivo
                .setPositiveButton(R.string.Dialogos_Ok, this)
                //establece un boton negativo
                .setNegativeButton(R.string.Dialogos_Cancelar,this);

        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case BUTTON_POSITIVE://si clica en el boton positivo
                anadirGasto();
                break;
            case BUTTON_NEGATIVE://si clica en el boton negativo
                //cierra el dialogo
                this.dismiss();
                break;
        }
    }

    private void anadirGasto() {
        //cierra el dialogo
        this.dismiss();
        //crea un toast con un mensaje
        Toast toast = Toast.makeText(getContext(),R.string.Dialogo_Asistir_Al_Evento_Toast,Toast.LENGTH_LONG);
        toast.show();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        //volver a establecer en el activity el tema por defecto cuando se cierra el dialogo
        getActivity().setTheme(R.style.AppTheme);
    }
}
