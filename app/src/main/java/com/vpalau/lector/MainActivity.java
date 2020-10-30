package com.vpalau.lector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtResultado;
    private Button   btnEscanear;
    private Button   btnSalir;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //enlazar controles
        txtResultado = (TextView)findViewById(R.id.txtResultado);
        btnEscanear  = (Button)findViewById(R.id.btnEscanear);
        btnSalir     = (Button)findViewById(R.id.btnSalir);
        btnEscanear.setOnClickListener(this);
        btnSalir.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnEscanear:
                new IntentIntegrator(this).initiateScan(); //iniciar el escaneo
                break;
            case R.id.btnSalir:
                finish();
                break;
        }
    }
    @Override
    protected final void onActivityResult(final int requestCode, final int resultCode, final Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null){
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            }else{
                txtResultado.setText(result.getContents());
            }
        } else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}