package com.example.a2dam.jamp.dialogs;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.a2dam.jamp.R;

@SuppressLint("ValidFragment")
public class Dialog_Product extends Fragment implements View.OnClickListener {
    private Button btnPlus,btnMinus,btnOk,btnCancelar;
    protected EditText textCount;
    protected Integer count;
    protected TextView productName, productDescription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //infla la vista con el layout personalizado para los productos
        final View view = inflater.inflate(R.layout.fragment_product_dialog, container, false);

        //referencia los textos
        productName = view.findViewById(R.id.tfDialogTitle);
        productDescription = view.findViewById(R.id.tfDialogContent);

        //llena los textos con los datos que le pasamos
        productName.setText(this.getArguments().getString("nombre"));
        productDescription.setText(this.getArguments().getString("descripcion"));
        return view;
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //referenciamos y escuchamos el boton
        btnPlus=view.findViewById(R.id.btnPlus);
        btnPlus.setOnClickListener(this);

        //referenciamos y escuchamos el boton
        btnMinus=view.findViewById(R.id.btnMinus);
        btnMinus.setOnClickListener(this);

        //referenciamos y escuchamos el boton
        btnOk=view.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(this);

        //referenciamos y escuchamos el boton
        btnCancelar=view.findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(this);
        //referenciamos el campo de texto
        textCount=view.findViewById(R.id.tfCount);
        //establecemos el contador a 1
        count=1;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPlus://si clica en el plus
                //llama al metodo plus
                plus();
                break;
            case R.id.btnMinus://si clicamos en el minus
                //llama al metodo minus
                minus();
                break;
            case R.id.btnOk://si clicamos en ok
                //creamos un string con el texto que vamos a mostrar en el toast
                String toasttext=this.getResources().getString(R.string.Dialogo_Productos_Toast_1) + " " + textCount.getText().toString() + " " + this.getResources().getString(R.string.Dialogo_Productos_Toast_2) + " " + productName.getText().toString() + " " + this.getResources().getString(R.string.Dialogo_Productos_Toast_3);
                //creamos el toast con el texto
                Toast toast = Toast.makeText(getContext(),toasttext,Toast.LENGTH_LONG);
                //muestra el toast
                toast.show();
                //llama al metodo añadir productos
                añadirProductos();
                break;
            case R.id.btnCancelar://si pulsamos en cancelar
                //cierra el "dialogo"
                getFragmentManager().beginTransaction().remove(this).commit();
                break;
        }
    }

    private void plus() {
        if(count<25) {//si el contador es menor de 25
            //suma 1 en el contador
            count++;
            //muestra el contador en el campo de texto
            textCount.setText(count.toString());
        }else{//si el contador es 25
            //muestra un toast de error
            Toast toast1 = Toast.makeText(getContext(),R.string.Dialogo_Productos_Max_Error, Toast.LENGTH_LONG);
            toast1.show();
        }

    }

    private void minus() {
        if(count>1) {//si el contador es mayor de 1
            // resta 1 al contador
            count--;
            //muestra el contador en el campo
            textCount.setText(count.toString());
        }else{//si el contador es igual a 1
            //muestra el error
            Toast toast1 = Toast.makeText(getContext(),R.string.Dialogo_Productos_Min_Error, Toast.LENGTH_LONG);
            toast1.show();
        }
    }

    private void añadirProductos() {
        //aqui tenemos que hace la llamada al servidor para añadir productos
        getFragmentManager().beginTransaction().remove(this).commit();
    }
}
