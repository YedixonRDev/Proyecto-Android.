package com.example.appfuncionandroid;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import com.example.appfuncionandroid.utilidades.Utilidades;

public class RegistroUsuarioActivity extends AppCompatActivity {

    private EditText campoID;
    private EditText campoNombre;
    private EditText campoTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);


        campoID = findViewById(R.id.editTextId);
        campoNombre = findViewById(R.id.editTextNombre);
        campoTelefono = findViewById(R.id.editTextTelefono);

        Button btnRegistrar = findViewById(R.id.btnRegistrarUsuario);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarUsuario();
            }

            private void registrarUsuario() {
                ConexionSQLiteHelper conn = new ConexionSQLiteHelper(RegistroUsuarioActivity.this, "bd_usuarios", null, 1);
                SQLiteDatabase db = conn.getWritableDatabase();

                try {
                    ContentValues values = new ContentValues();
                    values.put(Utilidades.CAMPO_ID, campoID.getText().toString());
                    values.put(Utilidades.CAMPO_NOMBRE, campoNombre.getText().toString());
                    values.put(Utilidades.CAMPO_TELEFONO, campoTelefono.getText().toString());

                    long idResultante = db.insert(Utilidades.TABLA_USUARIO, null, values);

                    Toast.makeText(getApplicationContext(), "Id Registro: " + idResultante, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error al registrar usuario: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                } finally {
                    // Cerrar la conexión de la base de datos
                    db.close();
                }
            }
        });
    }
}
