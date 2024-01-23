package com.example.appfuncionandroid;

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
                // Implementa la lógica para btnActualizar
            } else if (view.getId() == R.id.btnEliminar) {
                // Implementa la lógica para btnEliminar
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
}

    public void onBuscarClick(View view) {
        // Obtener el ID del campoId EditText
        String idConsulta = campoId.getText().toString();

        // Verificar que el ID no esté vacío
        if (!idConsulta.isEmpty()) {
            // Realizar la consulta y llenar los campos
            consultar();
        } else {
            // Mostrar un mensaje si el campo ID está vacío
            Toast.makeText(getApplicationContext(), "Ingrese un ID para buscar", Toast.LENGTH_SHORT).show();
        }
    }


    private void consultar() {
        // Obtener una instancia de la base de datos en modo lectura
        SQLiteDatabase db = conn.getReadableDatabase();

        // Obtener el ID del campoId EditText
        String idConsulta = campoId.getText().toString();

        // Definir las columnas que deseas obtener de la consulta
        String[] campos = {Utilidades.CAMPO_NOMBRE, Utilidades.CAMPO_TELEFONO};

        try {
            // Realizar la consulta a la base de datos
            Cursor cursor = db.query(
                    Utilidades.TABLA_USUARIO,
                    campos,
                    Utilidades.CAMPO_ID + "=?",
                    new String[]{idConsulta},
                    null,
                    null,
                    null);

            // Verificar si hay resultados en el cursor
            if (cursor != null && cursor.moveToFirst()) {
                // Obtener los índices de las columnas que deseas mostrar
                int columnIndexNombre = cursor.getColumnIndex(Utilidades.CAMPO_NOMBRE);
                int columnIndexTelefono = cursor.getColumnIndex(Utilidades.CAMPO_TELEFONO);

                // Obtener los valores de las columnas
                String nombre = cursor.getString(columnIndexNombre);
                String telefono = cursor.getString(columnIndexTelefono);

                // Mostrar los resultados en los EditText correspondientes
                campoNombre.setText(nombre);
                campoTelefono.setText(telefono);
            } else {
                // No se encontraron resultados
                Toast.makeText(getApplicationContext(), "El usuario no existe", Toast.LENGTH_SHORT).show();
                limpiar();
            }

            // Cerrar el cursor
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error al consultar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            // Cerrar la base de datos
            db.close();
        }
    }

    private void limpiar() {
        // Limpiar los EditText
        campoNombre.setText("");
        campoTelefono.setText("");
    }
}