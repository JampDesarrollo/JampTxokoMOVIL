package com.example.a2dam.jamp.fragments;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.a2dam.jamp.R;
import com.example.a2dam.jamp.dataClasses.UserBean;
import com.example.a2dam.jamp.dialogs.Dialog_Change_Password;
import com.example.a2dam.jamp.exceptions.BusinessLogicException;
import com.example.a2dam.jamp.logic.UserLogic;
import com.example.a2dam.jamp.models.PrincipalActivity;
import com.example.a2dam.jamp.others.ILogicFactory;

public class ChangePasswordFragment extends Fragment implements View.OnClickListener {
    private ChangePasswordFragment.OnFragmentInteractionListener mListener;
    private View vista;
    private EditText actualpass, newpass1,newpass2;
    private ImageButton btnShowActualpass,btnShowNewPass1,btnShowNewPass2;
    private Boolean actualpassboolean=false,newpass1boolean=false,newpass2boolean=false;
    private Button btnCambiar;
    private UserBean user;

    public ChangePasswordFragment() {

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){//si la orientacion es horizontal
            //infla la vista con el layout fragment_changepass_landscape
            vista= inflater.inflate(R.layout.fragment_changepass_landscape, container, false);
        }else if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_PORTRAIT){//si la orientacion es vertical
            //infla la vista con el layout fragment_changepass
            vista= inflater.inflate(R.layout.fragment_changepass, container, false);
        }

        //Establece el titulo de la barra superior
        ((PrincipalActivity) getActivity()).getSupportActionBar().setTitle(R.string.nav_change_pass);

        //referenciar el campo de la contraseña actual
        actualpass=vista.findViewById(R.id.pfActualPassword);
        //referenciar y escuchar el boton de la contraseña actual
        btnShowActualpass=vista.findViewById(R.id.btnShowActualPass);
        btnShowActualpass.setOnClickListener(this);

        //referenciar el campo de la nueva contraseña 1
        newpass1 =vista.findViewById(R.id.pfNewPassword1);
        //referenciar y escuchar el boton de la nueva contraseña 1
        btnShowNewPass1=vista.findViewById(R.id.btnShowNewPass1);
        btnShowNewPass1.setOnClickListener(this);

        //referenciar el campo de la nueva contraseña 2
        newpass2=vista.findViewById(R.id.pfNewPassword2);
        //referenciar y escuchar el boton de la nueva contraseña 2
        btnShowNewPass2=vista.findViewById(R.id.btnShowNewPass2);
        btnShowNewPass2.setOnClickListener(this);

        //referenciar y escuchar el boton de cambiar contraseña
        btnCambiar=vista.findViewById(R.id.btnCambiar);
        btnCambiar.setOnClickListener(this);

        //referenciar el userBean al publicuser del PrincipalActivity para poder mandarlo a la logica con las contraseñas
        user= PrincipalActivity.getPublicUser();
        return vista;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnShowActualPass: //si clica en el boton de mostrar la contraseña actual
                actualpassboolean = showPassword(actualpass, actualpassboolean);
                break;
            case R.id.btnShowNewPass1://si clica en el boton de mostrar la nueva contraseña 1
                newpass1boolean=showPassword(newpass1,newpass1boolean);
                break;
            case R.id.btnShowNewPass2://si clica en el boton de mostrar la nueva contraseña 2
                newpass2boolean=showPassword(newpass2,newpass2boolean);
                break;
            case R.id.btnCambiar://si pulsa en el boton de cambiar la contraseña
                cambiarPass();
                break;
        }
    }
    /*public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(getActivity().getRequestedOrientation()==ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){ //si la orientacion solicitada es vertical
            outState.putBoolean("state", true);//ponemos un boolean state a true en el Bundle
        }else if(getActivity().getRequestedOrientation()==ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {//si la orientacion solicitada es horizontal
            outState.putBoolean("state", false);//ponemos un boolean state a false en el Bundle
        }
        //con esto podemos controlar arriba que layout cargara dependiendo de la orientacion de la pantalla
    }*/

    private void cambiarPass() {
        if(checkPasswords()){//si el metodo checkpaddwords devuelve true
            //declaramos un booblean allok que vamos a usar para comprobar que la parte del servidor ha ido bien
            Boolean allOk=false;
            try {
                //Declarar un UserLogic llamando al metodo getUserLogic del iLogicFactory
                UserLogic iLogic=ILogicFactory.getUserLogic();
                //mandamos los datos a la logica para que se comunique con el servidor y nos devuelve un boolean a true si all ha ido bien
                allOk=iLogic.findUserChangePasswMov(user.getIdUser(),actualpass.getText().toString(),newpass1.getText().toString());
            } catch (BusinessLogicException e) {//si salta una excepcion la pilla
                //muestra un toast con el mensaje de error en la conexion
                Toast.makeText(getContext(),R.string.conection_error,Toast.LENGTH_LONG).show();
            }
            if(allOk) {//si all ha ido bien
                //Mostrar un dialogo informativo diciendo que ha cambiado la contraseña
                Dialog_Change_Password dialog = new Dialog_Change_Password();
                dialog.show(getFragmentManager(), "Dialog_Change_Password");

                //Cargar el fragment de productos
                FragmentManager fragmentManager;
                FragmentTransaction fragmentTransaction;
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                ProductFragment productFragment = new ProductFragment();
                fragmentTransaction.replace(R.id.fragment, productFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        }
    }

    private Boolean checkPasswords() {
        //declarar el Boolean que vamos a devolver si all ha ido bien
        Boolean correcto=true;
        //pintamos los campos de las contraseñas de blanco
        actualpass.setBackgroundTintList(this.getResources().getColorStateList(R.color.blanco));
        newpass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.blanco));
        newpass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.blanco));

        if(actualpass.getText().toString().trim().length()>0){//si la contraseña actual es mayor de 0
            if(actualpass.getText().toString().trim().length()<8){//si la contraseña actual es menor de 8
                //muestra un mensaje de error
                actualpass.setError(this.getResources().getString(R.string.pass_min_lenght_error));
                //pinta el campo de rojo
                actualpass.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                //inicializa la variable correcto a false para para informar que algo ha ido mal
                correcto=false;
            }else if(actualpass.getText().toString().trim().length()>255){
                //muestra un mensaje de error
                actualpass.setError(this.getResources().getString(R.string.max_lenght_error));
                //pinta el campo de rojo
                actualpass.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                //inicializa la variable correcto a false para para informar que algo ha ido mal
                correcto=false;
            }
        }else{//si el campo esta vacio
            //Cambiar color del campo de texto
            actualpass.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            //muestra un mensaje de error
            actualpass.setError(this.getResources().getString(R.string.field_requiered_error));
            //inicializa la variable correcto a false para para informar que algo ha ido mal
            correcto=false;
        }

        if(newpass1.getText().toString().trim().length()>0){//si la contraseña actual es mayor de 0
            if(newpass1.getText().toString().trim().length()<8){//si la contraseña actual es menor de 8
                //muestra un mensaje de error
                newpass1.setError(this.getResources().getString(R.string.pass_min_lenght_error));
                //pinta el campo de rojo
                newpass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                //inicializa la variable correcto a false para para informar que algo ha ido mal
                correcto=false;
            }else if(newpass1.getText().toString().trim().length()>255){//si el campo es mayor de 255 caracteres
                //muestra un mensaje de error
                newpass1.setError(this.getResources().getString(R.string.max_lenght_error));
                //pinta el campo de rojo
                newpass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                //inicializa la variable correcto a false para para informar que algo ha ido mal
                correcto=false;
            }
        }else{//si el campo esta vacio
            //Cambiar color del campo de texto
            newpass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            //muestra un mensaje de error
            newpass1.setError(this.getResources().getString(R.string.field_requiered_error));
            //inicializa la variable correcto a false para para informar que algo ha ido mal
            correcto=false;
        }

        if(newpass2.getText().toString().trim().length()>0){//si la contraseña actual es mayor de 0
            if(newpass2.getText().toString().trim().length()<8){//si la contraseña actual es menor de 8
                //muestra un mensaje de error
                newpass2.setError(this.getResources().getString(R.string.pass_min_lenght_error));
                //pinta el campo de rojo
                newpass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                //inicializa la variable correcto a false para para informar que algo ha ido mal
                correcto=false;
            }else if(newpass2.getText().toString().trim().length()>255){//si el campo es mayor de 255 caracteres
                //muestra un mensaje de error
                newpass2.setError(this.getResources().getString(R.string.max_lenght_error));
                //pinta el campo de rojo
                newpass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                //inicializa la variable correcto a false para para informar que algo ha ido mal
                correcto=false;
            }else if(!(newpass1.getText().toString().trim().equals(newpass2.getText().toString().trim()))){//si las contraseñas nuevos son diferentes
                //pinta el campo de rojo
                newpass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                //pinta el campo de rojo
                newpass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                //muestra un mensaje de error
                newpass2.setError(this.getResources().getString(R.string.new_pass_equals_error));
                //muestra un mensaje de error
                newpass1.setError(this.getResources().getString(R.string.new_pass_equals_error));
                //inicializa la variable correcto a false para para informar que algo ha ido mal
                correcto=false;
            }else if(actualpass.getText().toString().trim().equals(newpass2.getText().toString().trim())){//si las contraseñas no son iguales
                //pinta el campo de rojo
                newpass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                //muestra un mensaje de error
                newpass2.setError(this.getResources().getString(R.string.actual_pass_and_new_pass_equals_error));
                //inicializa la variable correcto a false para para informar que algo ha ido mal
                correcto=false;

            }
        }else{//si el campo esta vacio
            //Cambiar color del campo de texto
            newpass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            //muestra un mensaje de error
            newpass2.setError(this.getResources().getString(R.string.field_requiered_error));
            //inicializa la variable correcto a false para para informar que algo ha ido mal
            correcto=false;
        }
        return correcto;
    }

    private Boolean showPassword(EditText field, Boolean type) {
        if(!type){//si el boolean que le mandamos esta a false
            //cambiamos el input type del password field que le mandamos a password
            field.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            //inicializamos la variable a true para que la proxima vez que llamemos a este metodo muestre la contraseña
            type=true;
        }else {//si el boolean es false
            //cammbiamoe el iput type de la contraseña a texto plano
            field.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            //inicializamos la variable a false para que la proxima vez que llamemos a este metodo oculte la contraseña
            type=false;
        }
        return type;
    }
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }


}
