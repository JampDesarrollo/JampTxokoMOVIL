package com.example.a2dam.jamp.models;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.a2dam.jamp.R;
import com.example.a2dam.jamp.dataClasses.UserBean;
import com.example.a2dam.jamp.dialogs.Dialog_Request_New_Password;
import com.example.a2dam.jamp.sinUsar.exceptions.BusinessLogicException;
import com.example.a2dam.jamp.sinUsar.logic.UserLogic;
import com.example.a2dam.jamp.sinUsar.others.ILogicFactory;


/**
 * Class that controller Login view
 *
 * @author Julen
 * @author Ander
 */

public class MainActivityController extends AppCompatActivity implements View.OnClickListener {

    private Button btnInicio, btnRegistrarse;
    private EditText pfContrasena, tfUsuario;
    private TextView lblError;
    private ImageButton btnShowPass,btnVideo;
    private Boolean bTextVisible,videoPlaying,allOK;
    private VideoView video;
    private ScrollView resto;
    private UserLogic ilogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //establecer el tema por defecto, esto hace falta porque la aplicacion al iniciar tiene un tema diferente para la splash image
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){//si la orientacion es horizontal
            //quita la barra de arriba
            getSupportActionBar().hide();
            //ejecuta el metodo crearvideo.
            crearVideo();
        }else {
            //establece el layout de la ventana
            setContentView(R.layout.activity_main);

            //referenciar las variables de botones y botones de imagen declaradas arriba con los campos del diseño grafico y establecer el action listener de dichos botones
            //el boton de iniciar sesio
            btnInicio = findViewById(R.id.btnInicio);
            btnInicio.setOnClickListener(this);
            //el boton de registrarse
            btnRegistrarse = findViewById(R.id.btnRegistrar);
            btnRegistrarse.setOnClickListener(this);
            //el boton de mostrar la contraseña
            btnShowPass = findViewById(R.id.btnOjo);
            btnShowPass.setOnClickListener(this);
            //el boton del video
            btnVideo = findViewById(R.id.btnVideo);
            btnVideo.setOnClickListener(this);

            //referenciar las variables de texto declaradas arriba con los campos de texto del diseño grafico y establecer el color blanco en la rayas inferiores de los campos de texto
            tfUsuario = findViewById(R.id.tfUsuario);
            tfUsuario.setBackgroundTintList(this.getResources().getColorStateList(R.color.blanco));
            pfContrasena = findViewById(R.id.pfContraseña);
            pfContrasena.setBackgroundTintList(this.getResources().getColorStateList(R.color.blanco));
            lblError = findViewById(R.id.lblError);

            //referenciar el videoview declarado arriba con el videoview del inicio de sesion que esta oculto
            video = findViewById(R.id.videoView);

            //referenciar el scrollbar que contiene todos los lementos excepto el videoview
            resto = findViewById(R.id.scrollInicio);

            //Inicializar todos los Booleans a false;
            //este boolean es para saber la visibilidad del campo de la contraseña
            bTextVisible = false;
            //este boolean sirve para saber el estado del video
            videoPlaying = false;
            //este bollean sirve para saber si han saltado excepciones al conectar con el servidor
            allOK = true;
        }


    }

    /**
     * Method that indicates that it is going to be done depending on the button that is pressed.
     *
     * @param v Receive the v parameter that is the view.
     */
    @Override
    public void onClick(View v) {
        //en el momento que pulsa un boton se ejecuta este metodo y comprueba que boton se a mostrado
        switch (v.getId()) {
            case R.id.btnInicio: //cuando pulse en el metodo Inicio Sesion
                //este intent se borrara cuando este operativo
                Intent iniciarSesion = new Intent(MainActivityController.this, PrincipalActivityController.class);
                startActivity(iniciarSesion);
                //ejecuta el metodo logIn
                //logIn();
                break;
            case R.id.btnOjo: //cuando pulse el boton del ojo
                //ejecuta el metodo animacion
                animacion();
                //ejecuta el metodo animacion
                showPassword();
                break;
            case R.id.btnRegistrar://cuando pulsa el boton de RegistroController
                //Crea un nuevo intent que lleva a la ventana de registro
                Intent registrar = new Intent(getApplicationContext(), RegistroController.class);
                startActivity(registrar);
                //despues de cargar la ventana de registro pone los siguientes campos de la ventana de login vacios
                //campo del usuario
                tfUsuario.setText("");
                //campo de la contraseña
                pfContrasena.setText("");
                //campo de los errores del servidor
                lblError.setText("");
                break;
            case R.id.tfChangePass://cuando clica en el texto de he olvidado la contraseña
                //ejecuta el metodo changepass
                changepass();
                break;
            case R.id.btnVideo://cuando le da al boton del video
                //no llama al metodo crear video como seria logico sino que cambia la orientacion de la pantalla a horizontal lo que hace que directamente vaya al metodo onSavedIntanceState el cual guarda un boolean
                //en el outState para cuando recarge el activity carge e video en la ventana horizontal
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    private void crearVideo() {
        //pone el videoview donde se va a mostrar el video visible
        video.setVisibility(View.VISIBLE);
        //pone el scrollbar que contiene el resto de los elementos invisibles para que el usuario al tocar la pantalla no pueda interactuar con dichos campos
        resto.setVisibility(View.INVISIBLE);
        //crea un mediaController para controlar el video que vamos a reproducir(los botones de pausa,start,avanzar...)
        video.setMediaController(new MediaController(this));
        //seleciona el video que vamos a mostrar, en este caso lo tenemos en una carpeta raw en res
        video.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video));
        //ejecuta el video
        video.start();
        //pone el boolean de video playing a true, para indicarnos que el video se esta ejecutado
        videoPlaying=true;
    }

    public void onBackPressed(){
        if(videoPlaying){//si el video se esta ejecutando
            //para el video
            video.stopPlayback();
            //pone el resto de los elementos del login visibles
            resto.setVisibility(View.VISIBLE);
            //pone el videoview invisible para ocultarlo
            video.setVisibility(View.INVISIBLE);
            //pone el boolean videoplaying a false para informarnos que el video se a detenido
            videoPlaying=false;
            //vuelve a poner la orientacion de pantalla en vertical
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }else{//si el video no se esta ejecutando cierra el activity y al ser el primero en la cola cierra la aplicaion entera
            finish();
        }
    }

    private void animacion() {
        //crear e inicializar una variable AnimatorSet
        AnimatorSet animador=new AnimatorSet();
        //crear e inicializar una variable ObjectAnimator con el boton al que le vamos a aplicar la animacion, el tipo de animacion en este caso alpha para jugar con la transparencia y los valores
        ObjectAnimator animacion=ObjectAnimator.ofFloat(btnShowPass,"alpha",0f,1f);
        //establecemos el tiempo de duracion de la animacion
        animacion.setDuration(3000);
        //le indicamoa al animador que use la animacion
        animador.play(animacion);
        //ejecutamos el animador
        animador.start();
    }

    private void changepass() {
        if(tfUsuario.getText().toString().trim().length() < 255){//si el campo de usuario es menor de 255 (sin contar los blancos) continua probando
            if(tfUsuario.getText().toString().trim().length()>0){//si el campo de usuario en mayor de 0 (sin contar los blancos) continua a probar

                if(conectarCambiarPass()){//llama al metodo que se conecta con el servidor si duelve true
                    //carga el dialogo
                    Dialog_Request_New_Password dialog= new Dialog_Request_New_Password();
                    dialog.show(getSupportFragmentManager(),"Dialog_Request_New_Password");
                }
            }else{//si el campo esta vacio
                //mostrar mensaje de error
                tfUsuario.setError(this.getResources().getString(R.string.field_requiered_error));
                //pintar la linea inferior del campo de rojo
                tfUsuario.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                //mostrar en el campo de texto destinado a los error con el servidor el siguiente error:
                lblError.setText(R.string.user_need_error);
            }
        }else{//si el usuario es mayor de 255 caracteres (sin contar los blancos)
            //se muestra el mensaje de error
            tfUsuario.setError(this.getResources().getString(R.string.max_lenght_error));
            //se pinta la linea inferior del campo de rojo
            tfUsuario.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            //mostrar en el campo de texto destinado a los error con el servidor el siguiente error:
            lblError.setText(R.string.user_need_error);

        }
    }

    /**
     * LogIn method. At the moment when the user clicks on the button Start Session
     * will come to this method and will do all the necessary checks to be able to start a session.
     */

    private void logIn() {
        if (chkAllFieldsFilled()) { //si todos los campos estan llenos:
            //se pintan las lineas inferiores de los campos de texto en azul
            pfContrasena.setBackgroundTintList(this.getResources().getColorStateList(R.color.blanco));
            tfUsuario.setBackgroundTintList(this.getResources().getColorStateList(R.color.blanco));
            //si todos los campos estan llenos miramos el maximo de caracteres
            if (maxCaracters()) {//si los campos no son superiores a 255
                //llama al metodo ConectarIniciarSesion para conectar con el servidor
                UserBean userReturn = conectarIniciarSesion();
                if (userReturn.getIdUser() != 0) {//si el usuario que devuelve no es null
                    //que vaya a la ventana principal
                    Intent iniciarSesion = new Intent(MainActivityController.this, PrincipalActivityController.class);
                    //manda el usuario completo al activity principal
                    iniciarSesion.putExtra("Usuario", userReturn);
                    //inicia el activity principal
                    startActivity(iniciarSesion);
                    //vacia los campos de la ventana de login
                    tfUsuario.setText("");
                    pfContrasena.setText("");
                    lblError.setText("");
                }
            } else {//si los caracteres se pasan del rango
                if (pfContrasena.getText().toString().trim().length() > 255) {//si la contraseña es menor de 255
                    //muestra el error
                    pfContrasena.setError(this.getResources().getString(R.string.max_lenght_error));
                    //pinta la linea del campo de rojo
                    pfContrasena.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                }
                if (tfUsuario.getText().toString().trim().length() > 255) {//si el usuario es menor de 255
                    //muestra el error
                    tfUsuario.setError(this.getResources().getString(R.string.max_lenght_error));
                    //pinta la linea del campo de rojo
                    tfUsuario.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                }
            }
        } else { //si no estan llenos los campos
            if (tfUsuario.getText().toString().trim().length() == 0) {//si el usuario es igual a cero
                //muestra el error
                tfUsuario.setError(this.getResources().getString(R.string.field_requiered_error));
                //pinta el campo de rojo
                tfUsuario.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            }
            if (pfContrasena.getText().toString().trim().length() == 0) {//si la contraseña esta vacia
                //muestra el error del campo
                pfContrasena.setError(this.getResources().getString(R.string.field_requiered_error));
                //pinta el error de rojo
                pfContrasena.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            }
        }
    }



    /**
     * Method to check that all fields are full.
     *
     * @return devuelve un boolean indicando si estan llenos o no.
     */
    private Boolean chkAllFieldsFilled() {
        //mirar si todos los campos estan llenos
        Boolean isFilled = false;
        if (tfUsuario.getText().toString().trim().length() > 0 && pfContrasena.getText().toString().trim().length() > 0) {
            //si todos los campos estan llenos poner el boolean que vamos a devolver a true
            isFilled = true;
        }
        //devolvemos el boolean
        return isFilled;
    }

    /**
     *Method to check the characters entered in the user and password field.
     *
     * @return Returns a Boolean indicating whether it has been passed 255 or not.
     */

    private Boolean maxCaracters() {
        Boolean maxCaracteres = false;

        if (tfUsuario.getText().toString().trim().length() < 255 && pfContrasena.getText().toString().trim().length() < 255) {
            //si todos los campos son menor de 255 caracteres(sin contar blancos) poner el boolean que vamos a devolver a true
            maxCaracteres = true;
        }
        //devolvemos el boolean
        return maxCaracteres;
    }
    private UserBean conectarIniciarSesion() {
        UserLogic ilogic=ILogicFactory.getUserLogic();

        //conectar con la base de datos
        UserBean returnUser = null;
        try {
            //crear una variable userbean con todos los campos que ha metido el usuario para mandar al servidor
            returnUser=ilogic.findUserByLoginPasswMov(tfUsuario.getText().toString(),pfContrasena.getText().toString());
        } catch (BusinessLogicException e) {
            e.printStackTrace();
        }
        return returnUser;
    }


    private Boolean conectarCambiarPass() {
        UserLogic ilogic=ILogicFactory.getUserLogic();

        //conectar con la base de datos
        Boolean ok = false;
        try {
            //llamar al metodo forgotpassword de la logica y mandamos el usuario al servidor
            ok=ilogic.findUserForgotPassw(tfUsuario.getText().toString());
        } catch (BusinessLogicException e) {//si casca pilla la excepcion
            //mostrar un error en el campo de error
            lblError.setText(R.string.request_new_password_error);
        }
        return ok;
    }

    /**
     * Method that make visible or hide the password
     */
    private void showPassword() {
        if (bTextVisible) {// si el boolean de comprobar la contraseña es true
            //convierte los campos de las contraseñas en campos de texto de contraseña para ocultar el contenido
            pfContrasena.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            //pone a variable bTextVisible a false porque se estan ocultando las contraseñas
            bTextVisible = false;
        } else {//si el boolean de comprobar la contraseña es false
            //convierte los campos de las contraseñas en campos de texto normales para mostrar el contenido
            pfContrasena.setInputType(InputType.TYPE_CLASS_TEXT);
            //pone a variable bTextVisible a true porque se estan mostrando las contraseñas
            bTextVisible = true;
        }
    }
}