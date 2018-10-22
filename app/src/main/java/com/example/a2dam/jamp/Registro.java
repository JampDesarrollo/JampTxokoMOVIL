package com.example.a2dam.jamp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Registro extends AppCompatActivity implements View.OnClickListener,{

    boolean correcto=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    @Override
    public void onClick(View v) {
        controlarTodosLosCampos();
    }

    private void controlarTodosLosCampos() {
        if(R.id.tfLogin>0){
            if(R.id.tfFullName>0){
                if(R.id.tfeMail>0){
                    if(R.id.pfPassword1>0){
                        if(R.id.pfPassword2>0){
                            contraseñasIguales();
                        }else{dialogoError("La Segunda Contrasaeña Esta Vaacia")}
                    }else{dialogoError("La Primera Contraseña Esta Vacia")}
                }else{dialogoError("El eMail Esta Vacio")}
            }else{dialogoError("El Nombre Esta Vacio")}
        comprobarDatos();
        }else{dialogoError("El Login Esta Vacio")}
    }

    public void cambiarlblComprobante(String mensaje){
        TextView comprobante=findViewById(R.id.lblComprobante);
        comprobante.setText(mensaje);
        comprobante.setTextColor(@color/rojo);
    }

    private void contraseñasIguales() {
        if(R.id.pfPassword2!=R.id.pfPassword1){
            cambiarlblComprobante("Las Contraseñas No Coinciden");
        }
    }

    private void comprobarDatos(){
        comprobarLogin();
        comprobarNombre();
        comprobarEmail();
        comprobarContraseñas();
    }

    private void comprobarContraseñas() {

    }
    private void comprobarEmail() {

    }
    private void comprobarNombre() {

    }
    private void comprobarLogin() {

    }
}