package com.example.a2dam.jamp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import static android.view.View.VISIBLE;

public class Registro extends AppCompatActivity implements View.OnClickListener{
    TextView textLogin,textFullName,texteMail,showPass2, showPass1;
    EditText pass1, pass2;
    Button btnRegistrarse,btnAtras;
    ImageButton btnShowPass;
    Boolean menor;
    Boolean formatEmail;
    Boolean iguales;
    Boolean mayor8;
    ImageView imLoading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        textLogin =findViewById(R.id.tfLogin);
        textFullName =findViewById(R.id.tfFullName);
        texteMail =findViewById(R.id.tfeMail);
        pass1=findViewById(R.id.pfPassword1);
        pass2=findViewById(R.id.pfPassword2);
        showPass1=findViewById(R.id.tfPassword1);
        showPass2=findViewById(R.id.tfShowPass2);

        btnRegistrarse=findViewById(R.id.btnRegistrarse);
        btnRegistrarse.setOnClickListener(this);

        btnShowPass=findViewById(R.id.btnShowPass2);
        btnShowPass.setOnClickListener(this);

        btnAtras=findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(this);

        imLoading=findViewById(R.id.imLoading);

        //el progress bar es invisible desde un principio
        menor=false;
        formatEmail=false;
        mayor8=false;
        iguales = false;
    }

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
                Intent inicio=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(inicio);
                break;
            case R.id.btnShowPass2:
                //que vaya al metodo showpassword
                showPassword();
                break;
        }
    }


    private void controlarTodosLosCampos() {
        textLogin.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        textFullName.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        texteMail.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        pass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        pass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));

        if(textLogin.getText().length()>0){ // si el campo esta lleno
            menor=menor255(textLogin);
            if(!menor){ //controlar que el campo sea menor de 255
                textLogin.setError("El Login De Ser Menor De 255");
            }
        }else{
            //Cambiar color del campo de texto
            textLogin.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            //cambiarlblComprobante("El Login Esta Vacio");
            textLogin.setError("Campo Requerido");
        }

        if(textFullName.getText().length()>0){
            menor=menor255(textFullName);
            if(!menor){
                textFullName.setError("El Nombre De Ser Menor De 255");
                textFullName.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            }
        }else{
            //Cambiar color del campo de texto
            textFullName.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            //cambiarlblComprobante("El Nombre Esta Vacio");
            textFullName.setError("Campo Requerido");
        }

        if(texteMail.getText().length()>0) {
            formatEmail = emailFormat(texteMail); // comprobar que tiene formato email
            menor=menor255(texteMail);
            if(!formatEmail){
                texteMail.setError("El formato no es el correcto");
                texteMail.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            }
            if(!menor){
                texteMail.setError("El Nombre De Ser Menor De 255");
                texteMail.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            }
        }else{
            //Cambiar color del campo de texto
            texteMail.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            //cambiarlblComprobante("El eMail Esta Vacio");
            texteMail.setError("Campo Requerido");
        }

        if(pass1.getText().length()>0){
            mayor8=contrasenaMayor8(pass1);
            menor=menor255(pass1);
            if(!menor){
                pass1.setError("La Contraseña Debe Ser Menor De 255");
                pass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            }else if(!mayor8){
                pass1.setError("La Contraseña Debe Ser Mayor De 8");
                pass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            }
        }else{
            //Cambiar color del campo de texto
            pass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            //cambiarlblComprobante("La Primera Contraseña Esta Vacia");
            pass1.setError("Campo Requerido");
        }

        if(pass2.getText().length()>0){
            mayor8=contrasenaMayor8(pass2);
            menor=menor255(pass2);
            iguales=contrasenasIguales();
            if(!menor){
                pass1.setError("La Contraseña Debe Ser Menor De 255");
                pass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            }else if(!mayor8){
                pass1.setError("La Contraseña Debe Ser Mayor De 8");
                pass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            }else if(!iguales){
                pass1.setError("Las Contraseña Deben Ser Iguales");
                pass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            }
        }else{
            //Cambiar color del campo de texto
            pass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            //cambiarlblComprobante("La Segunda Contraseña Esta Vacia");
            pass2.setError("Campo Requerido");
        }
        //una vez que compruebe que los campos estan llenos, las contraseñas estan iguales

         if(textLogin.getText().length()>0 && textFullName.getText().length()>0 && texteMail.getText().length() >0 && pass1.getText().length()>0
                 && pass2.getText().length()>0 && menor && mayor8 && iguales && formatEmail){
            //en el momento que vaya a comprobar con la base de datos, que se ponga la imagen
            imLoading.setVisibility(View.VISIBLE);
             comprobarDatos();

    }}

    private Boolean contrasenaMayor8(EditText texto) {
        Boolean mayor=false;
        if(texto.getText().length()>=8){
            mayor=true;
        }
        return mayor;
    }

    private Boolean menor255(EditText texto) {
        Boolean menor=false;
        if(texto.getText().length()<=255){
            menor=true;
        }
        return menor;
    }

    private Boolean menor255(TextView texto) {
        Boolean menor=false;
        if(texto.getText().length()<=255){
            menor=true;
        }
        return menor;
    }
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

    private Boolean contrasenasIguales() {
        Boolean iguales=false;
        if(pass1!=pass2){
            iguales=true;
        }
        return iguales;
    }

    private void comprobarDatos(){
        comprobarLogin();
        comprobarEmail();
        comprobarContrasenas();
    }

    private void comprobarContrasenas() {
        try {
            //conectar con la base de datos y comprobar la contraseña
        }catch(Exception e){
            e.getMessage();
        }
    }
    private void comprobarEmail() {
        try{
            //conectar con la base de datos y comprobar el email
        }catch(Exception e){
            e.getMessage();
        }
    }
    private void comprobarLogin() {
        try{
            //conectar con la base de datos y comprobar el Login
        }catch(Exception e){
            e.getMessage();
        }
    }

    private void showPassword(){
        // si la contraseña no esta visible, que se haga visible
        //el showpass es el tf
        //pass2 es el pf

        if(pass1.getVisibility() == (View.VISIBLE)) {
            //que el showpass sea visible
            //va a aparecer el textfield
            showPass2.setText(pass2.getText()); //paso el texto del pass2 al textfield
            showPass2.setEnabled(true); // el text field se active
            pass2.setEnabled(false); //el password field pasa a false
            showPass1.setText(pass1.getText());
            showPass1.setEnabled(true);
            pass1.setEnabled(false);

        }else{ //si pass2 no es visible desde el inicio
            //que el password field sea visible
            pass2.setText(showPass2.getText()); //lo que esta en el textfield le paso a passwordfield
            showPass2.setEnabled(false); // el textfield lo inhabilito
            pass2.setEnabled(true); //habilito el password field
            pass1.setText(showPass1.getText());
            showPass1.setEnabled(false);
            pass1.setEnabled(true);
        }

    }
}