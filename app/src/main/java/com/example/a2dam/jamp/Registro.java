package com.example.a2dam.jamp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Registro extends AppCompatActivity implements View.OnClickListener{

    //boolean correcto=false;
    TextView textLogin =findViewById(R.id.tfLogin);
    TextView textFullName =findViewById(R.id.tfFullName);
    TextView texteMail =findViewById(R.id.tfeMail);
    EditText pass1=findViewById(R.id.pfPassword1);
    EditText pass2=findViewById(R.id.pfPassword2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegistrarse:
                controlarTodosLosCampos();
                break;
            case R.id.btnAtras:
                Intent inicio=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(inicio);
                break;
            case R.id.btnShowPass1:
                //mostrar contraseña
                break;
        }

    }

    private void controlarTodosLosCampos() {
        if(textLogin.getText().length()>0){
            if(textFullName.getText().length()>0){
                if(texteMail.getText().length()>0){
                    if(pass1.getText().length()>0){
                        if(pass2.getText().length()>0){
                            contrasenasIguales();
                        }else{
                            //Cambiar color del campo de texto
                            cambiarlblComprobante("La Segunda Contraseña Esta Vacia");
                        }
                    }else{
                        //Cambiar color del campo de texto
                        cambiarlblComprobante("La Primera Contraseña Esta Vacia");
                    }
                }else{
                    //Cambiar color del campo de texto
                    cambiarlblComprobante("El eMail Esta Vacio");
                }
            }else{
                //Cambiar color del campo de texto
                cambiarlblComprobante("El Nombre Esta Vacio");
            }
        comprobarDatos();
        }else{
            //Cambiar color del campo de texto
            cambiarlblComprobante("El Login Esta Vacio");
        }
    }

    public void cambiarlblComprobante(String mensaje){
        TextView comprobante=findViewById(R.id.lblComprobante);
        comprobante.setText(mensaje);
        comprobante.setTextColor(getResources().getColor(R.color.rojo));
        //Cambiar color del campo de texto
    }

    private void contrasenasIguales() {
        if(pass1!=pass2){
            cambiarlblComprobante("Las Contraseñas No Coinciden");
        }
    }

    private void comprobarDatos(){
        comprobarLogin();
        comprobarEmail();
        comprobarContrasenas();
    }

    private void comprobarContrasenas() {
        try {
            //conectar con la base de datos y comprobar la contraseña
        }catch(Exception e){
            e.getMessage();
        }
    }
    private void comprobarEmail() {
         try{
             //conectar con la base de datos y comprobar el email
        }catch(Exception e){
            e.getMessage();
        }
    }
    private void comprobarLogin() {
        try{
            //conectar con la base de datos y comprobar el Login
        }catch(Exception e){
            e.getMessage();
        }
    }
}