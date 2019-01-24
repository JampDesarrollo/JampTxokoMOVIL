package com.example.a2dam.jamp.models;

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
import com.example.a2dam.jamp.others.EncryptPassword;
import com.example.a2dam.jamp.logics.UserLogic;
import com.example.a2dam.jamp.others.ILogicFactory;
import com.example.a2dam.jamp.antes_PARA_BORRAR.ThreadForSocketClient;

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
    private UserLogic ilogic;

    /**
     * Method that create the Registro View
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //referenciar las variables de texto declaradas arriba con los campos de texto del diseño grafico y establecer el color jamp (azul oscuro) en la rayas inferiores de los campos de texto
        //texto de login de usuario
        textLogin =findViewById(R.id.tfLogin);
        textLogin.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));

        //texto del nombre completo de usuario
        textFullName =findViewById(R.id.tfFullName);
        textFullName.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));

        //texto del email del usuario
        texteMail =findViewById(R.id.tfeMail);
        texteMail.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));

        //texto del id del txoko del usuario
        textTxoko = findViewById(R.id.tfTxoko);
        textTxoko.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));

        //texto de la primera contraseña del usuario
        pass1=findViewById(R.id.pfPassword1);
        pass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));

        //texto de la segunda contraseña del usuario
        pass2=findViewById(R.id.pfNewPassword1);
        pass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));

        //referenciar las variables de botones y botones de imagen declaradas arriba con los campos del diseño grafico y establecer el action listener de dichos botones
        //boton de registrarse
        btnRegistrarse=findViewById(R.id.btnRegistrarse);
        btnRegistrarse.setOnClickListener(this);

        //boton de mostrar la contraseña
        btnShowPass=findViewById(R.id.btnShowNewPass1);
        btnShowPass.setOnClickListener(this);

        //boton de ir a atras
        btnAtras=findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(this);

        //inicializar los boolean declarados arriba
        //el boolean que se encarga de comprobar que todos los campos esten correctos
        correcto=true;
        //el boolean que controla la visibilidad de la contraseña
        bTextVisible=false;
        //el boolean que comprueba la validez del patron de email
        formatEmail=false;

        //referencia la variable del campo donde se muestran los erorres de la conexion con el servidor
        lblError=findViewById(R.id.lblComprobante);

        //inicializar la logica de la factoria
        ilogic = ILogicFactory.getILogic();
    }

    /**
     * Method That Checks Clicked Button
     * @param v Clicked Button view
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegistrarse:
                //este dialog se borrara cuando la aplicacion este terminada
                DialogFragment dialogo =new Dialog_SingUp();
                dialogo.show(getSupportFragmentManager(),"Dialog_SingUp");
                // si le da al boton de registro, vaya al metodo para comprobar todos los campos
                //controlarTodosLosCampos();
                break;
            case R.id.btnAtras:
                //si se pulsa el boton de atras cierra el activity registro y vuelve al que este en la cola, en este caso el mainActivity tambien conocido como Login Page
                finish();
                break;
            case R.id.btnShowNewPass1:
                //si se pulsa el boton de mostrar la contraseña va al metodo showPassword que muestra los campos de las contraseñas
                showPassword();
                break;
        }
    }

    private void controlarTodosLosCampos() {
        //pinta las lineas inferiores de todos los campos de texto
        textLogin.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        textFullName.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        texteMail.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        textTxoko.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        pass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        pass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        //inicializa la variable otra vez para poder comprobar varias veces que los campos esten correctos
        correcto=true;

        if(textLogin.getText().toString().trim().length()>0){//si el campo de texto esta lleno (sin contar los espacios en blanco) continua a la siguiente comprobacion
            if(textLogin.getText().toString().trim().length()>255){//si el campo de texto es menor de 255 caracteres (sin contar los espacios en blanco) muestra el error
                //escribe el texto de error en un bocata negro con una exclamacion
                textLogin.setError(this.getResources().getString(R.string.max_lenght_error));
                //pinta la linea inferior del campo de texto en rojo
                textLogin.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                //inicializa la variable correcto a false para que al final no conecte con el servidor
                correcto=false;
            }
        }else{//si el campo de texto del login esta vacio muestra el error
            //escribe el texto de error en un bocata negro con una exclamacion
            textLogin.setError(this.getResources().getString(R.string.field_requiered_error));
            //pinta la linea inferior del campo de texto en rojo
            textLogin.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            //inicializa la variable correcto a false para que al final no conecte con el servidor
            correcto=false;
        }

        if(textFullName.getText().toString().trim().length()>0){//si el campo de texto esta lleno (sin contar los espacios en blanco) continua a la siguiente comprobacion
            if(textFullName.getText().toString().trim().length()>255){//si el campo de texto es menor de 255 caracteres (sin contar los espacios en blanco) muestra el error
                //escribe el texto de error en un bocata negro con una exclamacion
                textFullName.setError(this.getResources().getString(R.string.max_lenght_error));
                //pinta la linea inferior del campo de texto en rojo
                textFullName.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                //inicializa la variable correcto a false para que al final no conecte con el servidor
                correcto=false;
            }
        }else{//si el campo de texto del nombre completo esta vacio muestra el error
            //pinta la linea inferior del campo de texto en rojo
            textFullName.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            //escribe el texto de error en un bocata negro con una exclamacion
            textFullName.setError(this.getResources().getString(R.string.field_requiered_error));
            //inicializa la variable correcto a false para que al final no conecte con el servidor
            correcto=false;
        }

        if(texteMail.getText().toString().trim().length()>0) {//si el campo de texto esta lleno (sin contar los espacios en blanco) continua a la siguiente comprobacion
            if(texteMail.getText().toString().trim().length()<255){//si el campo de texto es menor de 255 caracteres (sin contar los espacios en blanco) comprueba el email
                formatEmail = emailFormat(texteMail); // comprobar que tiene el formato de email
                if(!formatEmail) {//si no tiene el formato de email muestra el error
                    //escribe el texto de error en un bocata negro con una exclamacion
                    texteMail.setError(this.getResources().getString(R.string.format_error));
                    //pinta la linea inferior del campo de texto en rojo
                    texteMail.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                    //inicializa la variable correcto a false para que al final no conecte con el servidor
                    correcto=false;
                }
            }else{//si el campo de email es mayor de 255 caracteres (sin contar los blancos) muestra el error
                //escribe el texto de error en un bocata negro con una exclamacion
                texteMail.setError(this.getResources().getString(R.string.max_lenght_error));
                //pinta la linea inferior del campo de texto en rojo
                texteMail.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                //inicializa la variable correcto a false para que al final no conecte con el servidor
                correcto=false;
            }
        }else{//si el campo de texto del email esta vacio muestra el error
            //pinta la linea inferior del campo de texto en rojo
            texteMail.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            //escribe el texto de error en un bocata negro con una exclamacion
            texteMail.setError(this.getResources().getString(R.string.field_requiered_error));
            //inicializa la variable correcto a false para que al final no conecte con el servidor
            correcto=false;
        }

        if(textTxoko.getText().toString().trim().length()>0){//si el campo de texto esta lleno (sin contar los espacios en blanco) continua a la siguiente comprobacion
            if(textTxoko.getText().toString().trim().length()>255){//si el campo de texto es menor de 255 caracteres (sin contar los espacios en blanco) muestra el error
                //escribe el texto de error en un bocata negro con una exclamacion
                textTxoko.setError(this.getResources().getString(R.string.max_lenght_error));
                //pinta la linea inferior del campo de texto en rojo
                textTxoko.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                //inicializa la variable correcto a false para que al final no conecte con el servidor
                correcto=false;
            }
        }else{//si el campo de texto del id de txoko esta vacio muestra el error
            //pinta la linea inferior del campo de texto en rojo
            textTxoko.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            //escribe el texto de error en un bocata negro con una exclamacion
            textTxoko.setError(this.getResources().getString(R.string.field_requiered_error));
            //inicializa la variable correcto a false para que al final no conecte con el servidor
            correcto=false;
        }

        if(pass1.getText().toString().trim().length()>0){//si el campo de texto esta lleno (sin contar los espacios en blanco) continua a la siguiente comprobacion
            if(pass1.getText().toString().trim().length()<8){//si el campo de texto es menor de 8 caracteres (sin contar blancos) muestra el error
                //escribe el texto de error en un bocata negro con una exclamacion
                pass1.setError(this.getResources().getString(R.string.pass_min_lenght_error));
                //pinta la linea inferior del campo de texto en rojo
                pass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                //inicializa la variable correcto a false para que al final no conecte con el servidor
                correcto=false;
            }else if(pass1.getText().toString().trim().length()>255){//si el campo de texto es menor de 255 caracteres (sin contar los espacios en blanco) muestra el error
                //escribe el texto de error en un bocata negro con una exclamacion
                pass1.setError(this.getResources().getString(R.string.max_lenght_error));
                //pinta la linea inferior del campo de texto en rojo
                pass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                //inicializa la variable correcto a false para que al final no conecte con el servidor
                correcto=false;
            }
        }else{//si el campo de texto de la primera contraseña esta vacio muestra el error
            //pinta la linea inferior del campo de texto en rojo
            pass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            //escribe el texto de error en un bocata negro con una exclamacion
            pass1.setError(this.getResources().getString(R.string.field_requiered_error));
            //inicializa la variable correcto a false para que al final no conecte con el servidor
            correcto=false;
        }

        if(pass2.getText().toString().trim().length()>0){//si el campo de texto esta lleno (sin contar los espacios en blanco) continua a la siguiente comprobacion
            if(pass2.getText().toString().trim().length()<8){//si el campo de texto es menor de 8 caracteres (sin contar blancos) muestra el error
                //escribe el texto de error en un bocata negro con una exclamacion
                pass2.setError(this.getResources().getString(R.string.pass_min_lenght_error));
                //pinta la linea inferior del campo de texto en rojo
                pass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                //inicializa la variable correcto a false para que al final no conecte con el servidor
                correcto=false;
            }else if(pass2.getText().toString().trim().length()>255){//si el campo de texto es menor de 255 caracteres (sin contar los espacios en blanco) muestra el error
                //escribe el texto de error en un bocata negro con una exclamacion
                pass2.setError(this.getResources().getString(R.string.max_lenght_error));
                //pinta la linea inferior del campo de texto en rojo
                pass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                //inicializa la variable correcto a false para que al final no conecte con el servidor
                correcto=false;
            }else if(pass1.getText().toString().trim().equals(pass2.getText().toString().trim())){
                //escribe el texto de error en un bocata negro con una exclamacion
                pass2.setError(this.getResources().getString(R.string.pass_equals_error));
                //pinta la linea inferior del campo de texto en rojo
                pass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                //inicializa la variable correcto a false para que al final no conecte con el servidor
                correcto=false;
            }
        }else{//si el campo de texto de la segunda contraseña esta vacio muestra el error
            //pinta la linea inferior del campo de texto en rojo
            pass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            //escribe el texto de error en un bocata negro con una exclamacion
            pass2.setError(this.getResources().getString(R.string.field_requiered_error));
            //inicializa la variable correcto a false para que al final no conecte con el servidor
            correcto=false;
        }
        if(correcto) {//si todas las comprobaciones anteriores estan bien vaya al metodo de conectar
            //va al metodo conectar que se encarga de conectar con el servidor y manda el usuario con todos los campos que hemos comprobado
            conectar();
        }

    }
    /**
     * Method that verify email format
     * @param texto manda el email
     * @return formato devuelve true si el formato del email es correcto
     */

    private Boolean emailFormat(TextView texto){
        //declara y inicializa una variable que es la que se encarga de devolver al metodo comprobar todos los campos que el formato de la contraseña esta bien
        boolean formato = false;
        //declara un string en el que guarda el patron que debe tener un correo valido que son los siguientes:
        //debe tener caractes mayusculas y minusculas y numeros, seguidos por una @, y a continuacion debe tener solo caracteres en mayuscula y minuscula, seguido debe haber un punto,
        // //y para terminar debe haber 2 o 3 caracteres matuscula o minusculas
        String emailPattern= "[A-Za-z0-9._]*+@[A-Za-z]*+.[A-Za-z]{2,3}";
        if(emailPattern.matches(texto.getText().toString().trim())){//si el campo de texto que le hemos pasado concuerda con el patron que hemos creado antes pone el boolean formato a true
            formato = true;
        }
        //devuelve el boolean formato al metodo comprobar todos los datos
        return formato;
    }

    /**
     * Method that connect to the ilogic class to connect with the database
     */

    private void conectar(){
        try{
            //boolean que comprueba que la conexion con el servidor a ido ok
            allOK = true;
            //almacenar la hora del sistema en una variable long
            Long tsLong = System.currentTimeMillis();
            //guadar la variable tsLong con la hora del sistema en un  time stamp
            Timestamp now = new Timestamp(tsLong);

            //crear una variable userbean con todos los campos que ha metido el usuario para mandar al servidor, 5º campo llama a un metodo de la clase Encrypt password y se le manda la contraseña que ha introducido
            //y devuelve la contraseña encriptada con la clave publica.
            //este userbean es el correcto, el que se va a usar cuando cambiemos el userbean, porque este tiene el campo del txoko que el otro no tiene
            //UserBean user = new UserBean(textLogin.getText().toString(), texteMail.getText().toString(), textFullName.getText().toString(),textTxoko.getText().toString(), EncryptPassword.encrypt(pass1.getText().toString()), now, now);
            //este user bean se tiene que borrar cuando metamos el nuevo userbean
            UserBean user = new UserBean(textLogin.getText().toString(), texteMail.getText().toString(), textFullName.getText().toString(),EncryptPassword.encrypt(pass1.getText().toString()), now, now);
            //crear un hilo para la implementacion de la logica
            ThreadForSocketClient thread = new ThreadForSocketClient(user, ilogic, 1);
            //recibe la excepciones que no se han controlado del hilo y las manda al metodo uncaughtException
            thread.setUncaughtExceptionHandler(this::uncaughtException);
            //inicia el hilo
            thread.start();
            //esperar al que el hilo muera
            thread.join();
            if (allOK) {//si no han saltado excepciones muestra un cuadro de dialogo Dialog_SignUp
                Dialog_SingUp dial =new Dialog_SingUp();
                dial.show(getSupportFragmentManager(),"Dialog_SingUp");
            }
        }catch(InterruptedException e){//captura la exception de interrupcion
            //muestra un toaste diciendo que ha habido un problema con la conexion al servidor
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
        if (e.getCause() instanceof UserLoginExistException) {//si la excepcion atrapada es igual que la excepcion UserLoginExistException
            //muestra un mensaje de error en el campo destinado a los errores con la conxion
            lblError.setText(this.getResources().getString(R.string.user_login_exist_error));
            //devuelve la variable a false porque ha saltado una excepcion
            allOK=false;
        }else {//si el error es distinto a la excepcion UserLoginExistException
            //muestra un toast diciendo que ha habido un error con la conexion al servidor
            Toast.makeText(this,this.getResources().getString(R.string.conection_error), Toast.LENGTH_LONG).show();
            //devuelve la variable a false porque ha saltado una excepcion
            allOK=false;
        }
    }

    /**
     * Method that show the password fields
     */
    private void showPassword() {
        if(!bTextVisible){//si el boolean de comprobar la contraseña es false
            //convierte los campos de las contraseñas en campos de texto normales para mostrar el contenido
            pass1.setInputType(InputType.TYPE_CLASS_TEXT);
            pass2.setInputType(InputType.TYPE_CLASS_TEXT);
            //pone a variable bTextVisible a true porque se estan mostrando las contraseñas
            bTextVisible = true;
        }else  {// si el boolean es true
            //convierte los campos de las contraseñas en campos de texto de contraseña para ocultar el contenido
            pass1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            pass2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            //pone a variable bTextVisible a false porque se estan ocultando las contraseñas
            bTextVisible = false;
        }
    }
}