package com.jdhcreations.imcaplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ib.custom.toast.CustomToastView;

public class calculadoraImc extends AppCompatActivity implements View.OnClickListener {

    private TextView Info;
    private EditText Altura;
    private EditText Peso;
    private Button Calcular;
    private ImageView Estado;
    private TextView Resultado;
    private float IMC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String name = intent.getStringExtra("nameCalculator");
        String surname = intent.getStringExtra("surnameCalculator");
        String email = intent.getStringExtra("emailCalculator");
        String message = "Hola " + name + " " + surname + " es un gusto tenerlo aca, Su correo para el informe es: " + email;
        setContentView(R.layout.activity_calculadora_imc);

        Info = findViewById(R.id.tvInfo);
        Altura = findViewById(R.id.txtAltura);
        Peso = findViewById(R.id.txtPeso);
        Calcular = findViewById(R.id.btnCalcular);
        Estado = findViewById(R.id.imEstado);
        Resultado = findViewById(R.id.tvResultado);
        Info.setText(message);
        Calcular.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnCalcular){
            String peso = Peso.getText().toString();
            String altura = Altura.getText().toString();
            float ipeso = Float.valueOf(peso);
            float ialtura = Float.valueOf(altura);
            IMC = ipeso / (ialtura * ialtura);

            if(peso.isEmpty()){
                CustomToastView.makeWarningToast(this, "¡Ingrese su peso antes!", R.layout.custom_toast).show();
                return;
            }

            if(altura.isEmpty()){
                CustomToastView.makeWarningToast(this, "¡Ingrese su altura antes!", R.layout.custom_toast).show();
                return;
            }

            if(IMC < 18.5){
                Resultado.setText("Su indice de masa corporal es: " + IMC + " y tiene un bajo peso.");
                Estado.setImageResource(R.drawable.bajopeso);
            }
            if(IMC > 18.5 || IMC < 24.9){
                Resultado.setText("Su indice de masa corporal es: " + IMC + " y tiene un peso normal.");
                Estado.setImageResource(R.drawable.pesoideal);
            }
            if(IMC > 24.9 || IMC < 29.9){
                Resultado.setText("Su indice de masa corporal es: " + IMC + " y tiene un sobrepeso.");
                Estado.setImageResource(R.drawable.sobrepeso);
            }
            if(IMC > 30){
                Resultado.setText("Su indice de masa corporal es: " + IMC + " y tiene obesidad.");
                Estado.setImageResource(R.drawable.obesidad);
            }
        }
    }
}