package com.example.appfuncionandroid;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);

        //clic para el botón de Registrar Usuario
        Button btnRegistrarUsuario = findViewById(R.id.btnRegistrarUsuario);
        btnRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistroUsuarioActivity.class);
                startActivity(intent);
            }
        });

        // clic para el botón de Consultar Usuario
        Button btnConsultarUsuario = findViewById(R.id.btnConsultarUsuario);
        btnConsultarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConsultarUsuarioActivity.class);
                startActivity(intent);
            }
        });
    }
}
