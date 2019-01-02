package com.example.a2dam.jamp.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import com.example.a2dam.jamp.fragments.ChangePasswordFragment;
import com.example.a2dam.jamp.fragments.EventFragment;
import com.example.a2dam.jamp.fragments.ExpenseFragment;
import com.example.a2dam.jamp.fragments.ProductFragment;
import com.example.a2dam.jamp.fragments.TelephonFragment;

import com.example.a2dam.jamp.R;
import com.example.a2dam.jamp.fragments.ProductFragment;

public class Dialogo_Solicitud_Nueva_Contrasena extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.Dialogo_Solicitud_Nueva_Contrasena_Title)
                .setMessage(R.string.Dialogo_Solicitud_Nueva_Contrasena_Content)
                .setPositiveButton(R.string.Dialogos_Ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //ok();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
/*
    public void ok(){
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        fragmentManager= getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        ProductFragment productFragment = new ProductFragment();
        fragmentTransaction.replace(R.id.fragment, productFragment);
        fragmentTransaction.commit();
    }
*/

}
