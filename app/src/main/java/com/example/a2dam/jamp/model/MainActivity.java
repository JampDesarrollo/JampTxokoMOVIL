package com.example.a2dam.jamp.model;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.a2dam.jamp.R;
import com.example.a2dam.jamp.dialogs.Dialog_Request_New_Password;
import com.example.a2dam.jamp.exceptions.PasswordNotOkException;
import com.example.a2dam.jamp.exceptions.UserLoginExistException;
import com.example.a2dam.jamp.exceptions.UserNotExistException;
import com.example.a2dam.jamp.logic.ILogic;
import com.example.a2dam.jamp.logic.ILogicFactory;
import com.example.a2dam.jamp.logic.ThreadForSocketClient;

import messageuserbean.UserBean;

/**
 * Class that controller Login view
 *
 * @author Julen
 * @author Ander
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Thread.UncaughtExceptionHandler {

    Button btnInicio, btnRegistrarse;
    EditText pfContrasena, tfUsuario;
    TextView lblError;
    ImageButton btnShowPass,btnVideo;
    Boolean visible, bTextVisible,videoPlaying;
    VideoView video;
    private ILogic ilogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // relacionar las nuevas variables con las del xml de MainActivity
        //Los Botones
        btnInicio = findViewById(R.id.btnInicio);
        btnInicio.setOnClickListener(this);
        btnRegistrarse = findViewById(R.id.btnRegistrar);
        btnRegistrarse.setOnClickListener(this);
        btnShowPass = findViewById(R.id.btnOjo);
        btnShowPass.setOnClickListener(this);
        btnVideo=findViewById(R.id.btnVideo);
        btnVideo.setOnClickListener(this);

        //El TextView
        lblError = findViewById(R.id.lblError);

        //Los EditText
        tfUsuario = findViewById(R.id.tfUsuario);
        tfUsuario.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        pfContrasena = findViewById(R.id.pfContraseña);
        pfContrasena.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));

        //Los Boolean
        visible = false;
        bTextVisible = false;
        videoPlaying=false;

        //El Video
        video=findViewById(R.id.videoView);
        //
        ilogic = ILogicFactory.getILogic();

    }

    /**
     * Method that indicates that it is going to be done depending on the button that is pressed.
     *
     * @param v Receive the v parameter that is the view.
     */
    @Override
    public void onClick(View v) {
        //en el momento que pulsa
        switch (v.getId()) {
            case R.id.btnInicio: //cuando pulse en el metodo Inicio Sesion

                Intent iniciarSesion = new Intent(MainActivity.this, PrincipalActivity.class);
                startActivity(iniciarSesion);
                //logIn();
                break;
            case R.id.btnOjo: //cuando pulse en el ojo
                showPassword();
                break;
            case R.id.btnRegistrar:
                // que vaya a la ventana de registro
                Intent registrar = new Intent(getApplicationContext(), Registro.class);
                startActivity(registrar);
                tfUsuario.setText("");
                pfContrasena.setText("");
                lblError.setText("");
                break;
            case R.id.tfChangePass:
                changepass();
                break;
            case R.id.btnVideo:
                animacion();
                crearVideo();
               // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                /*Uri webpage = Uri.parse(this.getResources().getString(R.string.Video_Tutorial));
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);*/
        }
    }

    private void crearVideo() {
        video.setVisibility(View.VISIBLE);
        video.setMediaController(new MediaController(this));
        video.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video));
        video.start();
        videoPlaying=true;
    }

    public void onBackPressed(){
        if(videoPlaying){
            video.stopPlayback();
            video.setVisibility(View.INVISIBLE);
            videoPlaying=false;
            //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private void animacion() {

        AnimatorSet animador=new AnimatorSet();
        ObjectAnimator animacion=ObjectAnimator.ofFloat(btnVideo,"alpha",0f,1f);
        animacion.setDuration(3000);
        animador.play(animacion);
        animador.start();
    }

    private void changepass() {
        if(tfUsuario.getText().toString().trim().length() < 255){
            if(tfUsuario.getText().toString().trim().length()>0){
                comprobarUsuario();
            }else{
                tfUsuario.setError(this.getResources().getString(R.string.field_requiered_error));
                tfUsuario.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                lblError.setText(R.string.user_need_error);
            }
        }else{
            tfUsuario.setError(this.getResources().getString(R.string.max_lenght_error));
            tfUsuario.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            lblError.setText(R.string.user_need_error);

        }
    }

    private void comprobarUsuario() {
        DialogFragment dialogo =new Dialog_Request_New_Password();
        dialogo.show(getSupportFragmentManager(),"Dialog_Request_New_Password");
        lblError.setText("");
        tfUsuario.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));

        //conectar con la base de datos
        UserBean returnUser = null;

        try {
            UserBean usuario = new UserBean(tfUsuario.getText().toString(), pfContrasena.getText().toString());
            //crear hilo
            ThreadForSocketClient thread = new ThreadForSocketClient(usuario, ilogic, 2);
            thread.setUncaughtExceptionHandler(this::uncaughtException);
            //inicializar hilo
            thread.start();
            //esperar a que el hilo muera
            thread.join();
            //coger el user que he recibido
            returnUser = thread.getUser();
        } catch (InterruptedException e) {
            Toast.makeText(this, this.getResources().getString(R.string.conection_error), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * LogIn method. At the moment when the user clicks on the button Start Session
     * will come to this method and will do all the necessary checks to be able to start a session.
     */

    private void logIn() {

        Boolean filled = chkAllFieldsFilled(); //vamos  a mirar que todos los campos esten llenos
        if (filled) { //si estan escritos
            //si todos los campos estan llenos, el label de error se va a poner invisible y se le quita el color rojo a los campos

            pfContrasena.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
            tfUsuario.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
            //si todos los campos estan llenos miramos los caracteres
            Boolean max = maxCaracters();
            if (max) {
                UserBean userReturn = comprobarDatos();
                //si el usuario que devuelve no es null
                if (userReturn.getId() != 0) {
                    //que vaya a la ventana principal
                    Intent iniciarSesion = new Intent(MainActivity.this, PrincipalActivity.class);
                    iniciarSesion.putExtra("Usuario", userReturn);
                    startActivity(iniciarSesion);
                    tfUsuario.setText("");
                    pfContrasena.setText("");
                    lblError.setText("");

                }
            } else {
                //si los caracteres se pasan del rango
                if (pfContrasena.getText().toString().trim().length() > 255) {
                    pfContrasena.setError(this.getResources().getString(R.string.max_lenght_error));
                    pfContrasena.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                } else if (tfUsuario.getText().toString().trim().length() > 255) {
                    tfUsuario.setError(this.getResources().getString(R.string.max_lenght_error));
                    tfUsuario.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                } else {
                    pfContrasena.setError(this.getResources().getString(R.string.max_lenght_error));
                    tfUsuario.setError(this.getResources().getString(R.string.max_lenght_error));
                    pfContrasena.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                    tfUsuario.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                }
            }
        } else { //si no estan llenos los campos
            if (tfUsuario.getText().toString().trim().length() == 0) {
                tfUsuario.setError(this.getResources().getString(R.string.field_requiered_error));
                tfUsuario.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            }
            if (pfContrasena.getText().toString().trim().length() == 0) {
                pfContrasena.setError(this.getResources().getString(R.string.field_requiered_error));
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
            isFilled = true;
        }
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
            maxCaracteres = true;
        }
        return maxCaracteres;
    }

    /**
     * Method that checks if the user exists and if said password is with that user.
     * In case the user is incorrect, the UserNotExistException exception jumps.
     * If the password is incorrect, the PasswordNotOkException exception jumps.
     * If there is an error connecting to the database, Exception will be thrown.
     *
     * @return Devuelve el usuario entero.
     */

    private UserBean comprobarDatos() {

        //conectar con la base de datos
        UserBean returnUser = null;

        try {
            UserBean usuario = new UserBean(tfUsuario.getText().toString(), pfContrasena.getText().toString());
            //crear hilo
            ThreadForSocketClient thread = new ThreadForSocketClient(usuario, ilogic, 2);
            thread.setUncaughtExceptionHandler(this::uncaughtException);
            //inicializar hilo
            thread.start();
            //esperar al que el hilo muera
            thread.join();
            //coger el user que he recibido
            returnUser = thread.getUser();
        } catch (InterruptedException e) {
            Toast.makeText(this, this.getResources().getString(R.string.conection_error), Toast.LENGTH_LONG).show();
        }
        return returnUser;
    }

    /**
     * method that catches exceptions
     *
     * @param t Thread
     * @param e Throwable
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (e.getCause() instanceof UserNotExistException) {
            lblError.setText(this.getResources().getString(R.string.email_o_contrase_a_incorrecta));
        } else if (e.getCause() instanceof PasswordNotOkException) {
            lblError.setText(this.getResources().getString(R.string.email_o_contrase_a_incorrecta));
        } else if (e.getCause() instanceof UserLoginExistException){
            //Proceso de mandar email

            DialogFragment dialogo =new Dialog_Request_New_Password();
            dialogo.show(getSupportFragmentManager(),"Dialog_Request_New_Password");
            lblError.setText("");
            lblError.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        } else{
            lblError.setText(this.getResources().getString(R.string.no_hay_conexion));
        }
    }

    /**
     * Method that make visible or hide the password
     */
    private void showPassword() {
        if (!bTextVisible) { //si no esta visible la contraseña
            pfContrasena.setInputType(InputType.TYPE_CLASS_TEXT);
            bTextVisible = true;
        } else {
            pfContrasena.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            bTextVisible = false;
        }
    }
}