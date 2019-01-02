package com.example.a2dam.jamp.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.Button;

import com.example.a2dam.jamp.R;

public class Dialog_Productos_Fragment extends DialogFragment{
    protected Button btnPlus,btnMinus;
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(R.layout.fragment_product_dialog)
                .setPositiveButton(R.string.Dialogos_Ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                });
        return builder.create();
    }
}
