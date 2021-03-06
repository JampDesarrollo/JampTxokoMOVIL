package com.jamp.reto2.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;

import com.jamp.reto2.R;
import com.jamp.reto2.fragments.ProductFragmentController;

import static android.content.DialogInterface.BUTTON_POSITIVE;

public class Dialog_Change_Password extends DialogFragment implements DialogInterface.OnClickListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Establecer en el activity el estilo Dialog_custom diseñado para los dialogos
        getActivity().setTheme(R.style.Dialog_custom);
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //establece un titulo
        builder.setTitle(R.string.Dialogo_Cambio_Contrasena_Title)
                //establece un mensaje
                .setMessage(R.string.Dialogo_Cambio_Contrasena_Content)
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
                //llama al metodo ok()
                ok();
                break;
        }
    }

    public void ok(){
        //carga el fragment de dialogos
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        fragmentManager= getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        ProductFragmentController productFragmentController = new ProductFragmentController();
        fragmentTransaction.replace(R.id.fragment, productFragmentController);
        fragmentTransaction.commit();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //volver a establecer en el activity el tema por defecto cuando se cierra el dialogo
        getActivity().setTheme(R.style.AppTheme);
    }
}
