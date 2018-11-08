package com.example.a2dam.jamp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
     *  pass1 User Password EditText
     *  pass2 Repetition Of The User Password
     *  textLogin User Login EditText
     *  textFullName User Full Name EditText
     *  texteMail User eMail EditText
     */
    EditText pass1, pass2,textLogin,textFullName,texteMail;

    //lblMesassage Exception error messages
    TextView lblMessage;

    /*
     * btnRegistrarse User SignUp Button
     * btnAtras Go Back To Login View Button
     */
    Button btnRegistrarse,btnAtras;

    // btnShowPass Show Written Password Button
    ImageButton btnShowPass;

    /*
     * correcto User Data Correct Boolean
     * format User email Correct format Boolean
     */
    Boolean correcto,formatEmail,bTextVisible;

    //declarar la variable ilogic
    private ILogic ilogic;

    /**
     * Method that create the Registro Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // relacionar las nuevas variables con las del xml de registro

        //Los EditText
        textLogin =findViewById(R.id.tfLogin);
        textFullName =findViewById(R.id.tfFullName);
        texteMail =findViewById(R.id.tfeMail);
        pass1=findViewById(R.id.pfPassword1);
        pass2=findViewById(R.id.pfPassword2);

        //El TextView
        lblMessage=findViewById(R.id.lblComprobante);

        //Los Botones
        btnRegistrarse=findViewById(R.id.btnRegistrarse);
        btnRegistrarse.setOnClickListener(this);

        btnShowPass=findViewById(R.id.btnShowPass2);
        btnShowPass.setOnClickListener(this);

        btnAtras=findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(this);

        //Inicializar los boolean
        correcto=true;
        bTextVisible=false;
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

            case R.id.btnRegistrarse:
                // si le da al boton de registro, vaya al metodo para comprobar todos los campos
                controlarTodosLosCampos();
                break;
            case R.id.btnAtras:
                //si pulso en el boton atras que vaya a la ventana anterior cerrando la presente
                finish();
                break;
            case R.id.btnShowPass2:
                //llamar al metodo show password para mostrar la contraseña
                showPassword();
                break;
        }
    }

    /**
     * method that control all fields
     */
    private void controlarTodosLosCampos() {
        //Establecer los valores por defecto de los campos de la ventana de registro
        textLogin.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        textFullName.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        texteMail.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        pass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        pass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        lblMessage.setText("");
        correcto=true;

        //Empieza a comprobar todos los campos de uno en uno
        if(textLogin.getText().length()>0){//controlar que el campo sea mayor de 0
            if(textLogin.getText().length()>255){//controlar que el campo sea mayor de 255

                textLogin.setError(this.getResources().getString(R.string.max_lenght_error));//establecer el campo de error
                textLogin.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));//pintar de rojo la linea de debajo del texto
                correcto=false;//inicializa el boolean correcto a falso por fallar
            }
        }else{
            //Cambiar color del campo de texto
            textLogin.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));//pintar de rojo la linea de debajo del texto
            textLogin.setError(this.getResources().getString(R.string.field_requiered));//establecer el campo de error
            correcto=false;//inicializa el boolean correcto a falso por fallar
        }

        if(textFullName.getText().length()>0){//controlar que el campo sea mayor de 0
            if(textFullName.getText().length()>255){//controlar que el campo sea mayor de 255
                textFullName.setError(this.getResources().getString(R.string.max_lenght_error));//establecer el campo de error
                textFullName.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));//pintar de rojo la linea de debajo del texto
                correcto=false;//inicializa el boolean correcto a falso por fallar
            }
        }else{
            //Cambiar color del campo de texto
            textFullName.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));//pintar de rojo la linea de debajo del texto
            textFullName.setError(this.getResources().getString(R.string.field_requiered));//establecer el campo de error
            correcto=false;//inicializa el boolean correcto a falso por fallar
        }

        if(texteMail.getText().length()>0) {//controlar que el campo sea mayor de 0
            if(texteMail.getText().length()<255){//controlar que el campo sea menor de 255
                formatEmail = emailFormat(texteMail); // comprobar que tiene formato email
     
                if(!formatEmail) {
                    texteMail.setError(this.getResources().getString(R.string.format_error));//establecer el campo de error
                    texteMail.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));//pintar de rojo la linea de debajo del texto
                    correcto=false;//inicializa el boolean correcto a falso por fallar
                }
            }else{
                texteMail.setError(this.getResources().getString(R.string.max_lenght_error));//establecer el campo de error
                texteMail.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));//pintar de rojo la linea de debajo del texto
                correcto=false;//inicializa el boolean correcto a falso por fallar
            }
        }else{
            //Cambiar color del campo de texto
            texteMail.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));//pintar de rojo la linea de debajo del texto
            texteMail.setError(this.getResources().getString(R.string.field_requiered));//establecer el campo de error
            correcto=false;//inicializa el boolean correcto a falso por fallar
        }

        if(pass1.getText().length()>0){//controlar que el campo sea mayor de 0
            if(pass1.getText().length()<8){//controlar que el campo sea menor de 8
                pass1.setError(this.getResources().getString(R.string.pass_min_lenght_error));//establecer el campo de error
                pass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));//pintar de rojo la linea de debajo del texto
                correcto=false;//inicializa el boolean correcto a falso por fallar
            }else if(pass1.getText().length()>255){//controlar que el campo sea mayor de 255
                pass1.setError(this.getResources().getString(R.string.max_lenght_error));//establecer el campo de error
                pass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));//pintar de rojo la linea de debajo del texto
                correcto=false;//inicializa el boolean correcto a falso por fallar
            }
        }else{
            //Cambiar color del campo de texto
            pass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));//pintar de rojo la linea de debajo del texto
            pass1.setError(this.getResources().getString(R.string.field_requiered));//establecer el campo de error
            correcto=false;//inicializa el boolean correcto a falso por fallar
        }

        if(pass2.getText().length()>0){//controlar que el campo sea mayor de 0
            if(pass2.getText().length()<8){//controlar que el campo sea menor de 8
                pass2.setError(this.getResources().getString(R.string.pass_min_lenght_error));//establecer el campo de error
                pass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));//pintar de rojo la linea de debajo del texto
                correcto=false;//inicializa el boolean correcto a falso por fallar
            }else if(pass2.getText().length()>255){//controlar que el campo sea mayor de 255
                pass2.setError(this.getResources().getString(R.string.max_lenght_error));//establecer el campo de error
                pass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));//pintar de rojo la linea de debajo del texto
                correcto=false;//inicializa el boolean correcto a falso por fallar
            }else if(pass1.getText().equals(pass2.getText())){
                pass2.setError(this.getResources().getString(R.string.pass_equals_error));//establecer el campo de error
                pass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));//pintar de rojo la linea de debajo del texto
                correcto=false;//inicializa el boolean correcto a falso por fallar
            }
        }else{
            //Cambiar color del campo de texto
            pass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));//pintar de rojo la linea de debajo del texto
            pass2.setError(this.getResources().getString(R.string.field_requiered));//establecer el campo de error
            correcto=false;//inicializa el boolean correcto a falso por fallar
        }

        // si todos los campos estan llenos, el length es el que deberia y las contraseñas concuerdan haces la llamada al metodo que hace la conexion a la base de datos
        if(correcto)
            comprobarDatos();
    }

    /**
     * @param texto manda el email
     * @return formato devuelve true si el formato del email es correcto
     */
    private Boolean emailFormat(TextView texto){
        boolean formato = false;
        String emailPattern= "[A-Za-z0-9._]*+@[A-Za-z]*+.[A-Za-z]{2,3}";//crear un email pattern
        if(texto.getText().toString().trim().matches(emailPattern)){//comparar el emailpattern con el email introducido para comprobar que tenga un buen formato
            //el asterisco es que pueden aparecer las letras y los numeros 0 o mas veces
            //{2-3} tiene que haber dos o tres caracteres despues del punto
            formato = true;//si el formato es correcto se inicializa a true y se devuelve al metodo que lo ha llamado
        }
        return formato;
    }

    /**
     * method that show the password fields
     */
    private void showPassword() {
        if(!bTextVisible){//si la contraseña no se visualiza como texto plano cambiar el tipo de dato a textview
            pass2.setInputType(InputType.TYPE_CLASS_TEXT);
            bTextVisible = true;
        }else  {//si la contraseña se visualiza como texto plano cambiar el tipo de dato a passwordfield
            pass2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            bTextVisible = false;
        }
    }


    /**
     * method that connect to the ilogic class to connect with the database
     */
    private void comprobarDatos(){
        try{
            Timestamp now = new Timestamp(System.currentTimeMillis());//crear una variable de tipo timestap con la fecha del momento para mandarla a la base de datos
            UserBean user = new UserBean(textLogin.getText().toString(), texteMail.getText().toString(), textFullName.getText().toString(),
                    pass1.getText().toString(), now, now); //crear un userbean con los datos introducidos anteriormente y la hora del momento
            ilogic.userSignUp(user);//mandar el usuario con todos los datos al metodo userSignUp de la clase iLogic
        }catch(UserLoginExistException e){//si salta la excepcion establecer un mensaje de error en el textview y pinta de rojo el contenido del campo de texto
            lblMessage.setText(R.string.user_login_exist_error);
            lblMessage.setTextColor(this.getResources().getColorStateList(R.color.rojo));
        }catch (IOException e){//si salta la excepcion establecer un mensaje de error en el textview y pinta de rojo el contenido del campo de texto
            lblMessage.setText(R.string.conection_error);
            lblMessage.setTextColor(this.getResources().getColorStateList(R.color.rojo));
        }catch (Exception e){//si salta la excepcion establecer un mensaje de error en el textview y pinta de rojo el contenido del campo de texto
            lblMessage.setText(R.string.other_error);
            lblMessage.setTextColor(this.getResources().getColorStateList(R.color.rojo));
        }
    }
}