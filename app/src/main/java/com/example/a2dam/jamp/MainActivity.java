package com.example.a2dam.jamp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import messageuserbean.UserBean;

/**
 * Class that controller Login view
 *
 * @author Julen
 * @author Ander
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Thread.UncaughtExceptionHandler {

    Button btnInicio, btnRegistrarse;
    EditText pfContraseña, tfUsuario;
    TextView lblError;
    ImageView imLoading;
    ImageButton btnShowPass;
    Boolean visible, bTextVisible;
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

        //El TextView
        lblError = findViewById(R.id.lblError);

        //Los EditText
        tfUsuario = findViewById(R.id.tfUsuario);
        pfContraseña = findViewById(R.id.pfContraseña);

        //El ImageView
        imLoading = findViewById(R.id.imLoading);

        //Los Boolean
        visible = false;
        bTextVisible = false;

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
                logIn();
                break;
            case R.id.btnOjo: //cuando pulse en el ojo
                showPassword();
                break;
            case R.id.btnRegistrar:
                // que vaya a la ventana de registro
                Intent registrar = new Intent(getApplicationContext(), Registro.class);
                startActivity(registrar);
                tfUsuario.setText("");
                pfContraseña.setText("");
                lblError.setText("");
                break;
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

            pfContraseña.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
            tfUsuario.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
            //si todos los campos estan llenos miramos los caracteres
            Boolean max = maxCaracters();
            if (max) {
                //si los caracteres son los indicados
                pfContraseña.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
                tfUsuario.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));

                UserBean userReturn = comprobarDatos();
                //si el usuario que devuelve no es null
                if (userReturn.getId() != 0) {
                    //el gif se hara visible
                    imLoading.setVisibility(View.VISIBLE);
                    //que vaya a la ventana principal
                    Intent iniciarSesion = new Intent(MainActivity.this, PrincipalActivity.class);
                    iniciarSesion.putExtra("Usuario", userReturn);
                    startActivity(iniciarSesion);
                    tfUsuario.setText("");
                    pfContraseña.setText("");
                    lblError.setText("");
                    imLoading.setVisibility(View.INVISIBLE);
                }
            } else {
                //si los caracteres se pasan del rango
                if (pfContraseña.getText().toString().trim().length() > 255) {
                    pfContraseña.setError(this.getResources().getString(R.string.max_lenght_error));
                    pfContraseña.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                } else if (tfUsuario.getText().toString().trim().length() > 255) {
                    tfUsuario.setError(this.getResources().getString(R.string.max_lenght_error));
                    tfUsuario.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                } else {
                    pfContraseña.setError(this.getResources().getString(R.string.max_lenght_error));
                    tfUsuario.setError(this.getResources().getString(R.string.max_lenght_error));
                    pfContraseña.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                    tfUsuario.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                }
            }
        } else { //si no estan llenos los campos
            if (tfUsuario.getText().toString().trim().length() == 0) {
                tfUsuario.setError(this.getResources().getString(R.string.field_requiered_error));
                tfUsuario.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            }
            if (pfContraseña.getText().toString().trim().length() == 0) {
                pfContraseña.setError(this.getResources().getString(R.string.field_requiered_error));
                pfContraseña.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
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

        if (tfUsuario.getText().toString().trim().length() > 0 && pfContraseña.getText().toString().trim().length() > 0) {
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

        if (tfUsuario.getText().toString().trim().length() < 255 && pfContraseña.getText().toString().trim().length() < 255) {
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
            UserBean usuario = new UserBean(tfUsuario.getText().toString(), pfContraseña.getText().toString());
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
     * @param t
     * @param e
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (e.getCause() instanceof com.example.a2dam.jamp.UserNotExistException) {
            lblError.setText(this.getResources().getString(R.string.email_o_contrase_a_incorrecta));
        } else if (e.getCause() instanceof com.example.a2dam.jamp.PasswordNotOkException) {
            lblError.setText(this.getResources().getString(R.string.email_o_contrase_a_incorrecta));
        } else {
            Toast.makeText(this, this.getResources().getString(R.string.conection_error), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Method that make visible or hide the password
     */
    private void showPassword() {
        if (!bTextVisible) { //si no esta visible la contraseña
            pfContraseña.setInputType(InputType.TYPE_CLASS_TEXT);
            bTextVisible = true;
        } else {
            pfContraseña.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            bTextVisible = false;
        }
    }
}