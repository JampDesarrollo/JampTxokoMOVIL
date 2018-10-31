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

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnInicio, btnRegistrarse;
    EditText pfContraseña;
    TextView lblError,  tfUsuario;
    ImageView imLoading;
    ImageButton btnShowPass;




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


                   /* UserBean userReturn =comprobarDatos();
                    //si el usuario que devuelve no es null
                    if(userReturn!=null){
                        //el gif se hara visible
                        imLoading.setVisibility(View.VISIBLE);
                        //que vaya a la ventana principal
                        Intent iniciarSesion = new Intent(MainActivity.this, PrincipalActivity.class);
                        startActivity(iniciarSesion);
                    }*/
                    imLoading.setVisibility(View.VISIBLE);
                    //que vaya a la ventana principal
                    Intent iniciarSesion = new Intent(MainActivity.this, PrincipalActivity.class);
                    startActivity(iniciarSesion);
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

    private Boolean chkAllFieldsFilled() {
        //mirar si todos los campos estan llenos
        Boolean isFilled = false;

        if(tfUsuario.getText().length()>0 && pfContraseña.getText().toString().trim().length()>0){

            isFilled = true;
        }
        return isFilled;
    }

    private Boolean maxCaracters() {
        Boolean maxCaracteres = false;

        if(tfUsuario.getText().toString().trim().length()<255 && pfContraseña.getText().toString().trim().length()<255){

            maxCaracteres=true;
        }
        return maxCaracteres;
    }
/*
    private UserBean comprobarDatos() {

        //conectar con la base de datos

        return null;

    }

*/
    private void showPassword() {

        if(pfContraseña.getInputType()==InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD){

            //el texto que hay en el pfContraseña  se pasa al textfield
            /*
            textContraseña.setText(pfContraseña.getText());
            pfContraseña.setEnabled(false);
            textContraseña.setEnabled(true);

            */
            pfContraseña.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);


        }else{
    /*
            pfContraseña.setText(textContraseña.getText());
            textContraseña.setEnabled(false);
            pfContraseña.setEnabled(true);

           */
            pfContraseña.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);



        }
    }

}
