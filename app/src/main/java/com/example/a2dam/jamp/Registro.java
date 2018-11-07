package com.example.a2dam.jamp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.IOException;
import java.sql.Timestamp;
import messageuserbean.UserBean;

/**
 * @author Markel Oñate
 * @author Paula Lopez
 * @since 2018
 * @version 1.0
 */

public class Registro extends AppCompatActivity implements View.OnClickListener{
    /*
     * @param pass1 User Password EditText
     * @param pass2 Repetition Of The User Password
     * @param textLogin User Login EditText
     * @param textFullName User Full Name EditText
     * @param texteMail User eMail EditText
     */
    EditText pass1, pass2,textLogin,textFullName,texteMail;

    TextView lblMessage;

    /*
     * @param btnRegistrarse User SignUp Button
     * @param btnAtras Go Back To Login View Button
     */
    Button btnRegistrarse,btnAtras;

    /*
     * @param btnShowPass Show Written Password Button
     */
    ImageButton btnShowPass;
    /*
     * @param correcto User Data Correct Boolean
     * @param format User email Correct format Boolean
     */
    Boolean correcto,formatEmail,bTextVisible;

    ImageView imLoading;
    private ILogic ilogic;

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

        lblMessage=findViewById(R.id.lblComprobante);

        btnRegistrarse=findViewById(R.id.btnRegistrarse);
        btnRegistrarse.setOnClickListener(this);

        btnShowPass=findViewById(R.id.btnShowPass2);
        btnShowPass.setOnClickListener(this);

        btnAtras=findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(this);

        correcto=true;
        bTextVisible=false;

        //el progress bar es invisible desde un principio
        imLoading=findViewById(R.id.imLoading);
        formatEmail=false;
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

                controlarTodosLosCampos();
                // si todos los campos estan llenos, el length es el que deberia y las contraseñas concuerdan haces el progress bar
                break;
            case R.id.btnAtras:
                //si pulso en el boton atras que vaya a la ventana de inicio
                /*Intent inicio=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(inicio);*/
                finish();
                break;
            case R.id.btnShowPass2:
                //llamar al metodo show password para mostrar la contraseña
                showPassword();
                break;
        }
    }

    private void controlarTodosLosCampos() {
        //Restablecer Los Colores Por Defecto De Los Campos De Texto
        textLogin.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        textFullName.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        texteMail.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        pass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        pass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));

        //vaciar mensaje de error
        lblMessage.setText("");

        //establecer correcto en true
        correcto=true;

        if(textLogin.getText().length()>0){// si el campo esta lleno
            if(textLogin.getText().length()>255){//controlar que el campo sea menor de 255
                //Cambiar color del campo de texto y establecer mensaje de error
                textLogin.setError(this.getResources().getString(R.string.max_lenght_error));
                textLogin.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                correcto=false;
            }
        }else{
            //Cambiar color del campo de texto y establecer mensaje de error
            textLogin.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            textLogin.setError(this.getResources().getString(R.string.field_requiered));
            correcto=false;
        }
        if(textFullName.getText().length()>0){
            if(textFullName.getText().length()>255){
                //Cambiar color del campo de texto y establecer mensaje de error
                textFullName.setError(this.getResources().getString(R.string.max_lenght_error));
                textFullName.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                correcto=false;
            }
        }else{
            //Cambiar color del campo de texto y establecer mensaje de error
            textFullName.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            textFullName.setError(this.getResources().getString(R.string.field_requiered));
            correcto=false;
        }
        if(texteMail.getText().length()>0) {
            if(texteMail.getText().length()<255){
                formatEmail = emailFormat(texteMail); // comprobar que tiene formato email
     
                if(!formatEmail) {
                    //Cambiar color del campo de texto y establecer mensaje de error
                    texteMail.setError(this.getResources().getString(R.string.format_error));
                    texteMail.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                }
            }else{
                //Cambiar color del campo de texto y establecer mensaje de error
                texteMail.setError(this.getResources().getString(R.string.max_lenght_error));
                texteMail.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                correcto=false;
            }
        }else{
            //Cambiar color del campo de texto y establecer mensaje de error
            texteMail.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            texteMail.setError(this.getResources().getString(R.string.field_requiered));
            correcto=false;
        }
        if(pass1.getText().length()>0){
            if(pass1.getText().length()<8){
                //Cambiar color del campo de texto y establecer mensaje de error
                pass1.setError(this.getResources().getString(R.string.pass_min_lenght_error));
                pass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                correcto=false;
            }else if(pass1.getText().length()>255){
                //Cambiar color del campo de texto y establecer mensaje de error
                pass1.setError(this.getResources().getString(R.string.max_lenght_error));
                pass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                correcto=false;
            }
        }else{
            //Cambiar color del campo de texto y establecer mensaje de error
            pass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            pass1.setError(this.getResources().getString(R.string.field_requiered));
            correcto=false;
        }
        if(pass2.getText().length()>0){
            if(pass2.getText().length()<8){
                //Cambiar color del campo de texto y establecer mensaje de error
                pass2.setError(this.getResources().getString(R.string.pass_min_lenght_error));
                pass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                correcto=false;
            }else if(pass2.getText().length()>255){
                //Cambiar color del campo de texto y establecer mensaje de error
                pass2.setError(this.getResources().getString(R.string.max_lenght_error));
                pass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                correcto=false;
            }else if(pass1.getText().equals(pass2.getText())){
                //Cambiar color del campo de texto y establecer mensaje de error
                pass2.setError(this.getResources().getString(R.string.pass_equals_error));
                pass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                correcto=false;
            }
        }else{
            //Cambiar color del campo de texto y establecer mensaje de error
            pass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            pass2.setError(this.getResources().getString(R.string.field_requiered));
            correcto=false;
        }
        if(correcto)
            comprobarDatos();
    }

    /**
     *
     * @param texto manda el email
     * @return devuelve true si el formato del email es correcto
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

    private void showPassword() {
        if(!bTextVisible){
            pass2.setInputType(InputType.TYPE_CLASS_TEXT);
            bTextVisible = true;
        }else  {
            pass2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            bTextVisible = false;
        }
    }

    private void comprobarDatos(){
        try{
            Timestamp now = new Timestamp(System.currentTimeMillis());
            UserBean user = new UserBean(textLogin.getText().toString(), texteMail.getText().toString(), textFullName.getText().toString(),
                    pass1.getText().toString(), now, now);
            ilogic.userSignUp(user);
        }catch(UserLoginExistException e){
            lblMessage.setText(R.string.user_login_exist_error);
            lblMessage.setTextColor(this.getResources().getColorStateList(R.color.rojo));
        }catch (IOException e){
            lblMessage.setText(R.string.conection_error);
            lblMessage.setTextColor(this.getResources().getColorStateList(R.color.rojo));
        }catch (Exception e){
            lblMessage.setText(R.string.other_error);
            lblMessage.setTextColor(this.getResources().getColorStateList(R.color.rojo));
        }
    }
}