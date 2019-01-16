package com.example.a2dam.jamp.dialogs;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.constraint.Constraints;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.a2dam.jamp.R;
import com.example.a2dam.jamp.dataClasses.Product;
import com.example.a2dam.jamp.fragments.ProductFragment;
import com.example.a2dam.jamp.model.PrincipalActivity;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class Dialog_Product extends Fragment implements View.OnClickListener {
    private Button btnPlus,btnMinus,btnOk,btnCancelar;
    protected EditText textCount;
    protected Integer count;
    protected TextView productName, productDescription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_product_dialog, container, false);

        ((PrincipalActivity) getActivity()).getSupportActionBar().setTitle("Productos");
        productName = view.findViewById(R.id.tfDialogTitle);
        productDescription = view.findViewById(R.id.tfDialogContent);

        productName.setText(this.getArguments().getString("nombre"));
        productDescription.setText(this.getArguments().getString("descripcion"));
        return view;
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {


        btnPlus=view.findViewById(R.id.btnPlus);
        btnPlus.setOnClickListener(this);

        btnMinus=view.findViewById(R.id.btnMinus);
        btnMinus.setOnClickListener(this);

        btnOk=view.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(this);

        btnCancelar=view.findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(this);

        textCount=view.findViewById(R.id.tfCount);
        count=1;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPlus:
                plus();
                break;
            case R.id.btnMinus:
                minus();
                break;
            case R.id.btnOk:
                String toasttext=this.getResources().getString(R.string.Dialogo_Productos_Toast_1) + " " + textCount.getText().toString() + " " + this.getResources().getString(R.string.Dialogo_Productos_Toast_2) + " " + productName.getText().toString() + " " + this.getResources().getString(R.string.Dialogo_Productos_Toast_3);
                Toast toast = Toast.makeText(getContext(),toasttext,Toast.LENGTH_LONG);
                toast.show();
                añadirProductos();
                break;
            case R.id.btnCancelar:
                getFragmentManager().beginTransaction().remove(this).commit();
                break;
        }
    }

    private void plus() {
        if(count<25) {
            count++;
            textCount.setText(count.toString());
        }else{
            Toast toast1 = Toast.makeText(getContext(),R.string.Dialogo_Productos_Max_Error, Toast.LENGTH_LONG);
            toast1.show();
        }

    }

    private void minus() {
        if(count>1) {
            count--;
            textCount.setText(count.toString());
        }else{
            Toast toast1 = Toast.makeText(getContext(),R.string.Dialogo_Productos_Min_Error, Toast.LENGTH_LONG);
            toast1.show();
        }
    }

    private void añadirProductos() {
        getFragmentManager().beginTransaction().remove(this).commit();
    }
}
