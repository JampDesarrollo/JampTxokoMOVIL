package com.example.a2dam.jamp.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.a2dam.jamp.R;
import com.example.a2dam.jamp.dialogs.Dialogo_Cambio_Contrasena;
import com.example.a2dam.jamp.model.PrincipalActivity;

public class ChangePasswordFragment extends Fragment implements View.OnClickListener {
    private ChangePasswordFragment.OnFragmentInteractionListener mListener;
    private View vista;
    protected EditText actualpass, newpass1,newpass2;
    protected ImageButton btnShowActualpass,btnShowNewPass1,btnShowNewPass2;
    protected Boolean actualpassboolean=false,newpass1boolean=false,newpass2boolean=false;
    protected Button btnCambiar;


    public ChangePasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_changepass, container, false);


        actualpass=vista.findViewById(R.id.pfActualPassword);
        btnShowActualpass=vista.findViewById(R.id.btnShowActualPass);
        btnShowActualpass.setOnClickListener(this);

        newpass1 =vista.findViewById(R.id.pfNewPassword1);
        btnShowNewPass1=vista.findViewById(R.id.btnShowNewPass1);
        btnShowNewPass1.setOnClickListener(this);

        newpass2=vista.findViewById(R.id.pfNewPassword2);
        btnShowNewPass2=vista.findViewById(R.id.btnShowNewPass2);
        btnShowNewPass2.setOnClickListener(this);

        btnCambiar=vista.findViewById(R.id.btnCambiar);
        btnCambiar.setOnClickListener(this);

        ((PrincipalActivity) getActivity()).getSupportActionBar().setTitle(R.string.nav_change_pass);
        return vista;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnShowActualPass:
                actualpassboolean = showPassword(actualpass, actualpassboolean);
                break;
            case R.id.btnShowNewPass1:
                newpass1boolean=showPassword(newpass1,newpass1boolean);
                break;
            case R.id.btnShowNewPass2:
                newpass2boolean=showPassword(newpass2,newpass2boolean);
                break;
            case R.id.btnCambiar:
                cambiarPass();
                break;
        }
    }

    private void cambiarPass() {
        /*if(checkPasswords()){
            Dialogo_Cambio_Contrasena dialog =new Dialogo_Cambio_Contrasena();
            dialog.show(getFragmentManager(),"Dialogo_Cambio_Contrasena");
        }*/
        Dialogo_Cambio_Contrasena dialog =new Dialogo_Cambio_Contrasena();
        dialog.show(getFragmentManager(),"Dialogo_Cambio_Contrasena");


        //Cargar el fragment de productos

        /*FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        fragmentManager = FragmentActivity.getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        ProductFragment productFragment = new ProductFragment();
        fragmentTransaction.replace(R.id.fragment, productFragment);
        fragmentTransaction.commit();*/
    }

    private Boolean checkPasswords() {
        Boolean correcto=true;
        actualpass.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        newpass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));
        newpass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorJAMP));

        if(actualpass.getText().toString().trim().length()>0){
            if(actualpass.getText().toString().trim().length()<8){
                actualpass.setError(this.getResources().getString(R.string.pass_min_lenght_error));
                actualpass.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                correcto=false;
            }else if(actualpass.getText().toString().trim().length()>255){
                actualpass.setError(this.getResources().getString(R.string.max_lenght_error));
                actualpass.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                correcto=false;
            }
        }else{
            //Cambiar color del campo de texto
            actualpass.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            actualpass.setError(this.getResources().getString(R.string.field_requiered_error));
            correcto=false;
        }

        if(newpass1.getText().toString().trim().length()>0){
            if(newpass1.getText().toString().trim().length()<8){
                newpass1.setError(this.getResources().getString(R.string.pass_min_lenght_error));
                newpass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                correcto=false;
            }else if(newpass1.getText().toString().trim().length()>255){
                newpass1.setError(this.getResources().getString(R.string.max_lenght_error));
                newpass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                correcto=false;
            }
        }else{
            //Cambiar color del campo de texto
            newpass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            newpass1.setError(this.getResources().getString(R.string.field_requiered_error));
            correcto=false;
        }

        if(newpass2.getText().toString().trim().length()>0){
            if(newpass2.getText().toString().trim().length()<8){
                newpass2.setError(this.getResources().getString(R.string.pass_min_lenght_error));
                newpass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                correcto=false;
            }else if(newpass2.getText().toString().trim().length()>255){
                newpass2.setError(this.getResources().getString(R.string.max_lenght_error));
                newpass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                correcto=false;
            }else if(!(newpass1.getText().toString().trim().equals(newpass2.getText().toString().trim()))){
                newpass1.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                newpass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                newpass2.setError(this.getResources().getString(R.string.new_pass_equals_error));
                correcto=false;
            }else if(!(actualpass.getText().toString().trim().equals(newpass2.getText().toString().trim()))){
                newpass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                newpass2.setError(this.getResources().getString(R.string.actual_pass_and_new_pass_equals_error));
                correcto=false;

            }
        }else{
            //Cambiar color del campo de texto
            newpass2.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
            newpass2.setError(this.getResources().getString(R.string.field_requiered_error));
            correcto=false;
        }
        return correcto;
    }

    private Boolean showPassword(EditText field, Boolean type) {
        if(!type){
            field.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            type=true;
        }else {
            field.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
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
