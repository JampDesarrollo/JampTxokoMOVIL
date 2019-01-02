package com.example.a2dam.jamp.fragments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.a2dam.jamp.R;

public class Product_Dialog_Fragment extends AppCompatActivity implements View.OnClickListener {
    protected Button btnPlus,btnMinus;
    protected EditText textCount;
    protected Integer count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_product_dialog);
        btnMinus=findViewById(R.id.btnMinus);
        btnMinus.setOnClickListener(this);

        btnPlus=findViewById(R.id.btnPlus);
        btnPlus.setOnClickListener(this);

        textCount=findViewById(R.id.tfCount);


        count=Integer.getInteger(textCount.getText().toString());
    }
    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.btnMinus:
                count++;
                textCount.setText(count);
                textCount.setText("4");
                break;
            case R.id.btnPlus:
                count--;
                textCount.setText(count);
                break;
        }
    }
}
