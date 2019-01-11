package com.example.a2dam.jamp.model;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a2dam.jamp.R;
import com.example.a2dam.jamp.dialogs.Dialog_SingUp;
import com.example.a2dam.jamp.exceptions.UserLoginExistException;
import com.example.a2dam.jamp.logic.EncryptPassword;
import com.example.a2dam.jamp.logic.ILogic;
import com.example.a2dam.jamp.logic.ILogicFactory;
import com.example.a2dam.jamp.logic.ThreadForSocketClient;

import java.sql.Timestamp;

import messageuserbean.UserBean;

/**
 * Class that controller register view
 * @author Markel Oñate
 * @author Paula Lopez
 * @since 2018
 * @version 1.0
 */

public class Registro extends AppCompatActivity implements View.OnClickListener, Thread.UncaughtExceptionHandler{
    /**
     *  pass1 User Password EditText
     *  pass2 Repetition Of The User Password
     *  textLogin User Login EditText
     *  textFullName User Full Name EditText
     *  texteMail User eMail EditText
     */
    EditText pass1, pass2,textLogin,textFullName,texteMail,textTxoko;

    /**
     *  btnRegistrarse User SignUp Button
     *  btnAtras Go Back To Login View Button
     */
    Button btnRegistrarse,btnAtras;

    /**
     *  btnShowPass Show Written Password Button
     */
    ImageButton btnShowPass;
    /**
     *  correcto User Data Correct Boolean
     *  format User email Correct format Boolean
     */
    Boolean correcto,formatEmail,bTextVisible, allOK;

    TextView lblError;
    private ILogic ilogic;

    /**
     * Method that create the Registro View
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        textLogin =findViewById(R.id.tfLogin);
        textLogin.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        textFullName =findViewById(R.id.tfFullName);
        textFullName.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        texteMail =findViewById(R.id.tfeMail);
        texteMail.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        textTxoko = findViewById(R.id.tfTxoko);
        textTxoko.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        pass1=findViewById(R.id.pfPassword1);
        pass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        pass2=findViewById(R.id.pfNewPassword1);
        pass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));

        btnRegistrarse=findViewById(R.id.btnRegistrarse);
        btnRegistrarse.setOnClickListener(this);

        btnShowPass=findViewById(R.id.btnShowNewPass1);
        btnShowPass.setOnClickListener(this);

        btnAtras=findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(this);

        correcto=true;
        bTextVisible=false;


        formatEmail=false;
        lblError=findViewById(R.id.lblComprobante);
        ilogic = ILogicFactory.getILogic();
    }

    /**
     * Method That Checks Clicked Button
     * @param v Clicked Button
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            // si le da al boton de registro, vaya al metodo para comprobar todos los campos
            case R.id.btnRegistrarse:
                DialogFragment dialogo =new Dialog_SingUp();
                dialogo.show(getSupportFragmentManager(),"Dialog_SingUp");
                //controlarTodosLosCampos();
                // si todos los campos estan llenos, el length es el que deberia y las contraseñas concuerdan haces el progress bar
                break;
            case R.id.btnAtras:
                //si pulso en el boton atras que vaya a la ventana de inicio
                finish();
                break;
            case R.id.btnShowNewPass1:
                //Boton Mostrar Contraseña
                showPassword();
                break;
        }
    }

    private void controlarTodosLosCampos() {
        textLogin.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        textFullName.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        texteMail.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        textTxoko.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        pass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        pass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        correcto=true;

        if(textLogin.getText().toString().trim().length()>0){// si el campo esta lleno
            if(textLogin.getText().toString().trim().length()>255){//controlar que el campo sea menor de 255

                textLogin.setError(this.getResources().getString(R.string.max_lenght_error));
                textLogin.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                correcto=false;
            }
        }else{
            //Cambiar color del campo de texto
            textLogin.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            textLogin.setError(this.getResources().getString(R.string.field_requiered_error));
            correcto=false;
        }

        if(textFullName.getText().toString().trim().length()>0){
            if(textFullName.getText().toString().trim().length()>255){
                textFullName.setError(this.getResources().getString(R.string.max_lenght_error));
                textFullName.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                correcto=false;
            }
        }else{
            //Cambiar color del campo de texto
            textFullName.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            textFullName.setError(this.getResources().getString(R.string.field_requiered_error));
            correcto=false;
        }

        if(texteMail.getText().toString().trim().length()>0) {
            if(texteMail.getText().toString().trim().length()<255){
                formatEmail = emailFormat(texteMail); // comprobar que tiene formato email

                if(!formatEmail) {
                    texteMail.setError(this.getResources().getString(R.string.format_error));
                    texteMail.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                }
            }else{
                texteMail.setError(this.getResources().getString(R.string.max_lenght_error));
                texteMail.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                correcto=false;
            }
        }else{
            //Cambiar color del campo de texto
            texteMail.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            texteMail.setError(this.getResources().getString(R.string.field_requiered_error));
            correcto=false;
        }

        if(textTxoko.getText().toString().trim().length()>0){
            if(textTxoko.getText().toString().trim().length()>255){
                textTxoko.setError(this.getResources().getString(R.string.max_lenght_error));
                textTxoko.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                correcto=false;
            }
        }else{
            //Cambiar color del campo de texto
            textTxoko.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            textTxoko.setError(this.getResources().getString(R.string.field_requiered_error));
            correcto=false;
        }

        if(pass1.getText().toString().trim().length()>0){
            if(pass1.getText().toString().trim().length()<8){
                pass1.setError(this.getResources().getString(R.string.pass_min_lenght_error));
                pass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                correcto=false;
            }else if(pass1.getText().toString().trim().length()>255){
                pass1.setError(this.getResources().getString(R.string.max_lenght_error));
                pass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                correcto=false;
            }
        }else{
            //Cambiar color del campo de texto
            pass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            pass1.setError(this.getResources().getString(R.string.field_requiered_error));
            correcto=false;
        }

        if(pass2.getText().toString().trim().length()>0){
            if(pass2.getText().toString().trim().length()<8){
                pass2.setError(this.getResources().getString(R.string.pass_min_lenght_error));
                pass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                correcto=false;
            }else if(pass2.getText().toString().trim().length()>255){
                pass2.setError(this.getResources().getString(R.string.max_lenght_error));
                pass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                correcto=false;
            }else if(pass1.getText().toString().trim().equals(pass2.getText().toString().trim())){
                pass2.setError(this.getResources().getString(R.string.pass_equals_error));
                pass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                correcto=false;
            }
        }else{
            //Cambiar color del campo de texto
            pass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            pass2.setError(this.getResources().getString(R.string.field_requiered_error));
            correcto=false;
        }
        if(correcto) {
            comprobarDatos();
        }

    }
    /**
     * Method that verify email format
     * @param texto manda el email
     * @return formato devuelve true si el formato del email es correcto
     */

    private Boolean emailFormat(TextView texto){
        boolean formato = false;
        String email = texto.getText().toString().trim();
        String emailPattern= "[A-Za-z0-9._]*+@[A-Za-z]*+.[A-Za-z]{2,3}";
        if(email.matches(emailPattern)){
            //el asterisco es que pueden aparecer las letras y los numeros 0 o mas veces
            //{2-3} tiene que haber dos o tres caracteres despues del punto
            formato = true;
        }

        return formato;
    }

    /**
     * Method that connect to the ilogic class to connect with the database
     */

    private void comprobarDatos(){
        try{
            allOK = true;
            Long tsLong = System.currentTimeMillis();
            Timestamp now = new Timestamp(tsLong);
            //UserBean user = new UserBean(textLogin.getText().toString(), texteMail.getText().toString(), textFullName.getText().toString(),textTxoko.getText().toString(), pass1.getText().toString(), now, now);
            UserBean user = new UserBean(textLogin.getText().toString(), texteMail.getText().toString(), textFullName.getText().toString(),EncryptPassword.encrypt(pass1.getText().toString()), now, now);
            //crear hilo
            ThreadForSocketClient thread = new ThreadForSocketClient(user, ilogic, 1);
            thread.setUncaughtExceptionHandler(this::uncaughtException);
            //inicializar hilo
            thread.start();
            //esperar al que el hilo muera
            thread.join();
            if (allOK) {
                Dialog_SingUp dial =new Dialog_SingUp();
                dial.show(getSupportFragmentManager(),"Dialog_SingUp");
            }
        }catch(InterruptedException e){
            Toast.makeText(this,this.getResources().getString(R.string.conection_error), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Method that returns uncaught Exceptions
     * @param t Thread
     * @param e Throwable
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (e.getCause() instanceof UserLoginExistException) {
            lblError.setText(this.getResources().getString(R.string.user_login_exist_error));
            allOK=false;
        }else {
            Toast.makeText(this,this.getResources().getString(R.string.conection_error), Toast.LENGTH_LONG).show();
            allOK=false;
        }
    }

    /**
     * Method that show the password fields
     */

    private void showPassword() {
        if(!bTextVisible){
            pass1.setInputType(InputType.TYPE_CLASS_TEXT);
            pass2.setInputType(InputType.TYPE_CLASS_TEXT);
            bTextVisible = true;
        }else  {
            pass1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            pass2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            bTextVisible = false;
        }
    }
/*
    @Override
    public void FinalizaCuadroDialogo(String texto) {

    }*/
}