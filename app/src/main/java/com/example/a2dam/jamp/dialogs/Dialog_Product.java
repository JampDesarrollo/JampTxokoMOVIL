package com.example.a2dam.jamp.dialogs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.a2dam.jamp.R;
import com.example.a2dam.jamp.model.PrincipalActivity;

public class Dialog_Product extends Fragment implements View.OnClickListener {
    private Button btnPlus,btnMinus,btnOk;
    protected EditText textCount;
    protected Integer count;
    public Dialog_Product() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_product_dialog, container, false);

        ((PrincipalActivity) getActivity()).getSupportActionBar().setTitle("Productos");

        return view;
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        btnPlus=view.findViewById(R.id.btnPlus);
        btnPlus.setOnClickListener(this);

        btnMinus=view.findViewById(R.id.btnMinus);
        btnMinus.setOnClickListener(this);

        btnOk=view.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(this);

        textCount=view.findViewById(R.id.tfCount);
        count=1;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPlus:
                count++;
                textCount.setText(count.toString());
                break;
            case R.id.btnMinus:
                if(count>1) {
                    count--;
                    textCount.setText(count.toString());
                }else{
                    Toast toast1 = Toast.makeText(getContext(),R.string.Dialogo_Productos_Min_Error, Toast.LENGTH_LONG);
                    toast1.show();
                }
                break;
            case R.id.btnOk:
                añadirProductos();
                break;
        }
    }

    private void añadirProductos() {
        getFragmentManager().beginTransaction().remove(this).commit();
    }
}
