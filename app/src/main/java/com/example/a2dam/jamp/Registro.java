package com.example.a2dam.jamp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Registro extends AppCompatActivity implements View.OnClickListener{

    boolean correcto=false;
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
        controlarTodosLosCampos();
    }

    private void controlarTodosLosCampos() {
        if(textLogin.getText().length()>0){
            if(textFullName.getText().length()>0){
                if(texteMail.getText().length()>0){
                    if(pass1.getText().length()>0){
                        if(pass2.getText().length()>0){
                            contrasenasIguales();
                        }else{cambiarlblComprobante("La Segunda Contrasaeña Esta Vaacia");}
                    }else{cambiarlblComprobante("La Primera Contraseña Esta Vacia");}
                }else{cambiarlblComprobante("El eMail Esta Vacio");}
            }else{cambiarlblComprobante("El Nombre Esta Vacio");}
        comprobarDatos();
        }else{cambiarlblComprobante("El Login Esta Vacio");}
    }

    public void cambiarlblComprobante(String mensaje){
        TextView comprobante=findViewById(R.id.lblComprobante);
        comprobante.setText(mensaje);
        comprobante.setTextColor(getResources().getColor(R.color.rojo));
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

    //Los if(correcto) hay que cambiarlos, solo estan para que no den fallos
    private void comprobarContrasenas() {
        if(correcto){//conectar con la base de datos y comprobar la contraseña
            correcto=true;
        }else{
            correcto=false;
        }
    }
    private void comprobarEmail() {
        if(correcto){//conectar con la base de datos y comprobar el email
            correcto=true;
        }else{
            correcto=false;
        }
    }
    private void comprobarLogin() {
        if(correcto){//conectar con la base de datos y comprobar el email
            correcto=true;
        }else{
            correcto=false;
        }
    }
}