package com.example.a2dam.jamp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import messageuserbean.UserBean;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnInicio, btnRegistrarse;
    EditText pfContraseña;
    TextView lblError,  tfUsuario;
    ImageView imLoading;
    ImageButton btnShowPass;
    Boolean visible = false;
    Boolean bTextVisible    = false;
    private ILogic ilogic;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pfContraseña = findViewById(R.id.pfContraseña);
        btnInicio = (Button) findViewById(R.id.btnInicio);
        lblError= findViewById(R.id.lblError);
        //textContraseña=findViewById(R.id.tfContraseña);
        tfUsuario=findViewById(R.id.tfUsuario);
        btnShowPass=findViewById(R.id.btnOjo);
        imLoading=findViewById(R.id.imLoading);
        btnRegistrarse=findViewById(R.id.btnRegistrar);
        //Que el foco este siempre en el boton de Inicio
        btnInicio.setOnClickListener(this);
        btnRegistrarse.setOnClickListener(this);
        btnShowPass.setOnClickListener(this);
        ilogic = ILogicFactory.getILogic();

    /*
        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent iniciarSesion = new Intent(MainActivity.this, PrincipalActivity.class);
                startActivity(iniciarSesion);
            }
        });
        */
    }

    /**
     * Metodo que indica que se va a hacer dependiendo del boton al que se pulse.
     * @param v Recibe el parametro v que es la vista.
     */
    @Override
    public void onClick(View v) {
        //en el momento que pulsa
      switch(v.getId()){

          case R.id.btnInicio: //cuando pulse en el metodo Inicio Sesion
              logIn();
              break;
          case R.id.btnOjo: //cuando pulse en el ojo
              showPassword();
              break;
          case R.id.btnRegistrar:
              // que vaya a la ventana de registro
              Intent registrar =new Intent(getApplicationContext(),Registro.class);
              startActivity(registrar);
              break;
      }

    }

    /**
     * Metodo de logIn. En el momento en el que el usuario pulse en el boton Iniciar Sesion
     * vendrá a este metodo y hará todas las comprobaciones necesarias para poder Iniciar Sesion.
     */

    private void logIn() {

      Boolean filled = chkAllFieldsFilled(); //vamos  a mirar que todos los campos esten llenos

      if(filled){ //si estan escritos
          //si todos los campos estan llenos, el label de error se va a poner invisible y se le quita el color rojo a los campos
          lblError.setVisibility(View.INVISIBLE);
          pfContraseña.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
          tfUsuario.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
          //si todos los campos estan llenos miramos los caracteres
          Boolean max = maxCaracters();

                if(max){
                    //si los caracteres son los indicados
                    lblError.setVisibility(View.INVISIBLE);
                    pfContraseña.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
                    tfUsuario.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));

                    UserBean userReturn =comprobarDatos();
                    //si el usuario que devuelve no es null
                    if(userReturn!=null) {
                        //el gif se hara visible
                        imLoading.setVisibility(View.VISIBLE);
                        //que vaya a la ventana principal
                        Intent iniciarSesion = new Intent(MainActivity.this, PrincipalActivity.class);
                        startActivity(iniciarSesion);
                    }
                }else{
                    //si los caracteres se pasan del rango
                    if(pfContraseña.getText().toString().trim().length()>255){
                        pfContraseña.setError("Demasiados caracteres");
                        pfContraseña.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));

                    }else if (tfUsuario.getText().toString().trim().length()>255){
                        tfUsuario.setError("Demasiados caracteres");
                        tfUsuario.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));

                    }else{
                        pfContraseña.setError("Demasiados caracteres");
                        tfUsuario.setError("Demasiados caracteres");
                        pfContraseña.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                        tfUsuario.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));

                    }

                }

  }else{ //si no estan llenos los campos
          if(pfContraseña.getText().toString().trim().length()==0){
              pfContraseña.setError("Campo requerido");
              pfContraseña.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));

          }else if (tfUsuario.getText().toString().trim().length()==0){
              tfUsuario.setError("Campo requerido");
              tfUsuario.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));

          }else{
              pfContraseña.setError("Campo requerido");
              tfUsuario.setError("Campo requerido");
              tfUsuario.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
              pfContraseña.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));

          }
      }

    }

    /**
     * Metodo para chekear que todos los campos estan llenos.
     * @return devuelve un boolean indicando si estan llenos o no.
     */

    private Boolean chkAllFieldsFilled() {
        //mirar si todos los campos estan llenos
        Boolean isFilled = false;

        if(tfUsuario.getText().toString().trim().length()>0 && pfContraseña.getText().toString().trim().length()>0){

            isFilled = true;
        }
        return isFilled;
    }

    /**
     * Metodo para chekear los caracteres metiodos en el campo usuario y contraseña.
     * @return Devuelve un booleano indicando si se han pasado de 255 o no.
     */

    private Boolean maxCaracters() {
        Boolean maxCaracteres = false;

        if(tfUsuario.getText().toString().trim().length()<255 && pfContraseña.getText().toString().trim().length()<255){

            maxCaracteres=true;
        }
        return maxCaracteres;
    }
/**
 * Metodo que comprueba si el usuario existe y si dicha contraseña está con ese usuario.
 * En caso de que el usuario sea incorrecto salta la excepcion UserNotExistException.
 * Si la contraseña es incorrecta salta la excepcion PasswordNotOkException.
 * Si hay error al conectar con la base de datos salta Exception.
 * @return Devuelve el usuario entero.
 */

    private UserBean comprobarDatos() {

        //conectar con la base de datos
        UserBean returnUser = null;
        try {
            UserBean usuario = new UserBean(tfUsuario.getText().toString(), pfContraseña.getText().toString());
            returnUser = ilogic.userLogin(usuario);
        }catch(UserNotExistException e){

            tfUsuario.setError("Usuario o contraseña incorrecta");
            pfContraseña.setError("Usuario o contraseña incorrecta");
            tfUsuario.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            pfContraseña.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));

        }catch(PasswordNotOkException e){

            tfUsuario.setError("Usuario o contraseña incorrecta");
            pfContraseña.setError("Usuario o contraseña incorrecta");
            tfUsuario.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            pfContraseña.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
        }catch(Exception e){
            //ERRROR AL CONECTAR CON LA BASE DE DATOS
            Toast.makeText(this,"Error al conectar con la base de datos", Toast.LENGTH_LONG).show();
        }
        return returnUser;

    }



    /**
     * Metodo para hacer visible u ocultar la contraseña.
     */
    private void showPassword() {
       if(bTextVisible == false){ //si no esta visible la contraseña
           pfContraseña.setInputType(InputType.TYPE_CLASS_TEXT);
           bTextVisible = true;
          }else  {
           pfContraseña.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
           //Toast.makeText(this,"HHHHHHHHHHHHHHHHHH", Toast.LENGTH_LONG).show();
           bTextVisible = false;
    }
    }

}
