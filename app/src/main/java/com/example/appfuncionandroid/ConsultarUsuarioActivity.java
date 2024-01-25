package com.example.appfuncionandroid;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appfuncionandroid.utilidades.Utilidades;

public class ConsultarUsuarioActivity extends AppCompatActivity {

    EditText campoId, campoNombre, campoTelefono;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_usuario);

        // Inicializar la conexión a la base de datos
        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuarios", null, 1);

        // Enlazar los EditText con los elementos del layout
        campoId = findViewById(R.id.editTextId);
        campoNombre = findViewById(R.id.editTextNombre);
        campoTelefono = findViewById(R.id.editTextTelefono);
    }

    public void onClick(View view) {
        try {
            if (view.getId() == R.id.btnConsultar) {
                consultar();
            } else if (view.getId() == R.id.btnActualizar) {
                actualizarUsuario();
            } else if (view.getId() == R.id.btnEliminar) {
                eliminarUsuario();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
}

    private void eliminarUsuario() {
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros = {campoId.getText().toString()};
        db.delete(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID+"=?",parametros);
        Toast.makeText(getApplicationContext(), "Se elimino el usuario", Toast.LENGTH_LONG).show();

        campoId.setText("");

        limpiar();
        db.close();


    }

    private void actualizarUsuario() {

        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros = {campoId.getText().toString()};
        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE,campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO,campoTelefono.getText().toString());

        db.update(Utilidades.TABLA_USUARIO,values,Utilidades.CAMPO_ID + "=?",parametros);
        Toast.makeText(getApplicationContext(), "Ya se actualizo el usuario", Toast.LENGTH_LONG).show();
        db.close();
    }

    public void onBuscarClick(View view) {
        String idConsulta = campoId.getText().toString();

        if (!idConsulta.isEmpty()) {
            consultar();
        } else {
            Toast.makeText(getApplicationContext(), "Ingrese un ID para buscar", Toast.LENGTH_SHORT).show();
        }
    }


    private void consultar() {
        // Obtener una instancia de la base de datos
        SQLiteDatabase db = conn.getReadableDatabase();


        String idConsulta = campoId.getText().toString();


        String[] campos = {Utilidades.CAMPO_NOMBRE, Utilidades.CAMPO_TELEFONO};

        try {
            // consulta a la base de datos
            Cursor cursor = db.query(
                    Utilidades.TABLA_USUARIO,
                    campos,
                    Utilidades.CAMPO_ID + "=?",
                    new String[]{idConsulta},
                    null,
                    null,
                    null);


            if (cursor != null && cursor.moveToFirst()) {

                int columnIndexNombre = cursor.getColumnIndex(Utilidades.CAMPO_NOMBRE);
                int columnIndexTelefono = cursor.getColumnIndex(Utilidades.CAMPO_TELEFONO);

                String nombre = cursor.getString(columnIndexNombre);
                String telefono = cursor.getString(columnIndexTelefono);

                campoNombre.setText(nombre);
                campoTelefono.setText(telefono);
            } else {

                Toast.makeText(getApplicationContext(), "El usuario no existe", Toast.LENGTH_SHORT).show();
                limpiar();
            }

            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error al consultar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {

            db.close();
        }
    }

    private void limpiar() {
        // Limpiar los EditText
        campoNombre.setText("");
        campoTelefono.setText("");
    }
}