package com.example.a2dam.jamp;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Registro extends AppCompatActivity implements View.OnClickListener{
    TextView textLogin,textFullName,texteMail,showPass2;
    EditText pass1, pass2;
    Button btnRegistrarse,btnAtras;
    ImageButton btnShowPass;
    Boolean menor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        textLogin =findViewById(R.id.tfLogin);
        textFullName =findViewById(R.id.tfFullName);
        texteMail =findViewById(R.id.tfeMail);
        pass1=findViewById(R.id.pfPassword1);
        pass2=findViewById(R.id.pfPassword2);
        showPass2=findViewById(R.id.tfShowPass2);

        btnRegistrarse=findViewById(R.id.btnRegistrarse);
        btnRegistrarse.setOnClickListener(this);

        btnShowPass=findViewById(R.id.btnShowPass2);
        btnShowPass.setOnClickListener(this);

        btnAtras=findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(this);

        menor=false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegistrarse:
                //
                controlarTodosLosCampos();
                break;
            case R.id.btnAtras:
                //
                Intent inicio=new Intent(getApplicationContext(),Registro.class);
                startActivity(inicio);
                break;
            case R.id.btnShowPass2:
                //
                showPass2.setText(pass2.getText());
                pass2.setEnabled(false);
                showPass2.setEnabled(true);
                break;
        }
    }

    private void controlarTodosLosCampos() {
        textLogin.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        textFullName.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        texteMail.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        pass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        pass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));

        if(textLogin.getText().length()>0){
            menor=menor255(textLogin);
            if(!menor){
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
            menor=menor255(texteMail);
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
            Boolean mayor8=contrasenaMayor8(pass1);
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
            Boolean mayor8=contrasenaMayor8(pass2);
            menor=menor255(pass2);
            Boolean iguales=contrasenasIguales();
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

        comprobarDatos();

    }



    public void cambiarlblComprobante(String mensaje){
        TextView comprobante=findViewById(R.id.lblComprobante);
        comprobante.setText(mensaje);
        comprobante.setTextColor(getResources().getColor(R.color.rojo));
        //Cambiar color del campo de texto
    }

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
}