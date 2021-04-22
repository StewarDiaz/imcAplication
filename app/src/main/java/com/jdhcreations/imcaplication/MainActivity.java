package com.jdhcreations.imcaplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ib.custom.toast.CustomToastView;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText Name;
    private EditText Surname;
    private EditText Email;
    private Button Next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = findViewById(R.id.txtNombre);
        Surname = findViewById(R.id.txtApellido);
        Email = findViewById(R.id.txtCorreo);
        Next = findViewById(R.id.btnNext);
        Next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String name = Name.getText().toString();
        String surname = Surname.getText().toString();
        String email = Email.getText().toString();

        if(name.isEmpty()){
            CustomToastView.makeErrorToast(this, "Error al validar el nombre", R.layout.custom_toast).show();
            return;
        }
        if(surname.isEmpty()){
            CustomToastView.makeErrorToast(this, "Error al validar el apellido", R.layout.custom_toast).show();
            return;
        }
        if(!isValidEmail(email)){
                CustomToastView.makeErrorToast(this, "Error al validar el email", R.layout.custom_toast).show();
                return;
        }

        Intent intent = new Intent(this, calculadoraImc.class);
        intent.putExtra("nameCalculator",name);
        intent.putExtra("surnameCalculator",surname);
        intent.putExtra("emailCalculator",email);
        startActivity(intent);
        }


    private Boolean isValidEmail(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}