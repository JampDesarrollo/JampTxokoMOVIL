package com.example.a2dam.jamp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import messageuserbean.UserBean;

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
     * Metodo que indica que se va a hacer dependiendo del boton al que se pulse.
     *
     * @param v Recibe el parametro v que es la vista.
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
     * Metodo de logIn. En el momento en el que el usuario pulse en el boton Iniciar Sesion
     * vendrá a este metodo y hará todas las comprobaciones necesarias para poder Iniciar Sesion.
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
     * Metodo para chekear que todos los campos estan llenos.
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
     * Metodo para chekear los caracteres metiodos en el campo usuario y contraseña.
     *
     * @return Devuelve un booleano indicando si se han pasado de 255 o no.
     */

    private Boolean maxCaracters() {
        Boolean maxCaracteres = false;

        if (tfUsuario.getText().toString().trim().length() < 255 && pfContraseña.getText().toString().trim().length() < 255) {
            maxCaracteres = true;
        }
        return maxCaracteres;
    }

    /**
     * Metodo que comprueba si el usuario existe y si dicha contraseña está con ese usuario.
     * En caso de que el usuario sea incorrecto salta la excepcion UserNotExistException.
     * Si la contraseña es incorrecta salta la excepcion PasswordNotOkException.
     * Si hay error al conectar con la base de datos salta Exception.
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
     * Metodo para hacer visible u ocultar la contraseña.
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