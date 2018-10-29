package com.example.a2dam.jamp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * @author Markel Oñate
 * @author Paula Lopez
 * @since 2018
 * @version 1.0
 */

public class Registro extends AppCompatActivity implements View.OnClickListener{
    /**
     * @param textLogin Password 2 Show Text
     */
    TextView showPass2;

    /**
     * @param pass1 User Password EditText
     * @param pass2 Repetition Of The User Password
     * @param textLogin User Login EditText
     * @param textFullName User Full Name EditText
     * @param texteMail User eMail EditText
     */
    EditText pass1, pass2,textLogin,textFullName,texteMail;

    /**
     * @param btnRegistrarse User SignUp Button
     * @param btnAtras Go Back To Login View Button
     */
    Button btnRegistrarse,btnAtras;

    /**
     * @param btnShowPass Show Written Password Button
     */
    ImageButton btnShowPass;
    /**
     * @param correcto User Data Correct Boolean
     */
    Boolean correcto;

    /**
     * Method that create the Registro View
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        textLogin =findViewById(R.id.tfLogin);
        textFullName =findViewById(R.id.tfFullName);
        texteMail =findViewById(R.id.tfeMail);
        pass1=findViewById(R.id.pfPassword1);
        pass2=findViewById(R.id.pfPassword2);
        showPass2=findViewById(R.id.tfShowPass2);

        btnRegistrarse=findViewById(R.id.btnRegistrarse);
        btnRegistrarse.setOnClickListener(this);

        btnShowPass=findViewById(R.id.btnShowPass2);
        btnShowPass.setOnClickListener(this);

        btnAtras=findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(this);

        correcto=true;
    }

    /**
     * Method That Checks Clicked Button
     * @param v Clicked Button
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegistrarse:
                //
                controlarTodosLosCampos();
                break;
            case R.id.btnAtras:
                //
                Intent inicio=new Intent(getApplicationContext(),Registro.class);
                startActivity(inicio);
                break;
            case R.id.btnShowPass2:
                //
                if(pass2.getVisibility()==View.VISIBLE){
                    showPass2.setText(pass2.getText());
                    pass2.setVisibility(View.INVISIBLE);
                    showPass2.setVisibility(View.VISIBLE);

                }else{
                    pass2.setVisibility(View.VISIBLE);
                    showPass2.setVisibility(View.INVISIBLE);
                }

                break;
        }
    }

    /**
     *
     */
    private void controlarTodosLosCampos() {
        textLogin.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        textFullName.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        texteMail.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        pass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        pass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        correcto=true;

        if(textLogin.getText().length()>0){
            if(textLogin.getText().length()>255){
                textLogin.setError("El Login De Ser Menor De 255");
                textLogin.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                correcto=false;
            }
        }else{
            //Cambiar color del campo de texto
            textLogin.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            textLogin.setError("Campo Requerido");
            correcto=false;
        }

        if(textFullName.getText().length()>0){
            if(textFullName.getText().length()>255){
                textFullName.setError("El Nombre De Ser Menor De 255");
                textFullName.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                correcto=false;
            }
        }else{
            //Cambiar color del campo de texto
            textFullName.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            textFullName.setError("Campo Requerido");
            correcto=false;
        }

        if(texteMail.getText().length()>0) {
            if(texteMail.getText().length()>255){
                texteMail.setError("El Nombre De Ser Menor De 255");
                texteMail.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                correcto=false;
            }
        }else{
            //Cambiar color del campo de texto
            texteMail.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            texteMail.setError("Campo Requerido");
            correcto=false;
        }

        if(pass1.getText().length()>0){
            if(pass1.getText().length()>8){
                pass1.setError("La Contraseña Debe Ser Mayor De 8");
                pass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                correcto=false;
            }else if(pass1.getText().length()>255){
                pass1.setError("La Contraseña Debe Ser Menor De 255");
                pass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                correcto=false;
            }
        }else{
            //Cambiar color del campo de texto
            pass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            pass1.setError("Campo Requerido");
            correcto=false;
        }

        if(pass2.getText().length()>0){
            if(pass2.getText().length()>8){
                pass2.setError("La Contraseña Debe Ser Mayor De 8");
                pass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                correcto=false;
            }else if(pass2.getText().length()>255){
                pass2.setError("La Contraseña Debe Ser Menor De 255");
                pass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                correcto=false;
            }else if(pass1.getText().equals(pass2.getText())){
                pass2.setError("Las Contraseña Deben Ser Iguales");
                pass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                correcto=false;
            }
        }else{
            //Cambiar color del campo de texto
            pass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            pass2.setError("Campo Requerido");
            correcto=false;
        }
        if(correcto)
            comprobarDatos();

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