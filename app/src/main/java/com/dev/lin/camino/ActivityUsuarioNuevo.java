package com.dev.lin.camino;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Formulario de registro de un nuevo usuario
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class ActivityUsuarioNuevo extends ActionBarActivity {
    private static final String USUARIO_NUEVO = "UsuarioNuevo";

    private Usuario usuario = null;

    private Spinner listaComplexion = null;
    private String[] valoresComplexion = {"Nada deportista", "Poco deportista", "Deportista Amateur",
            "Deportista profesional"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_nuevo);

        ArrayAdapter adaptador = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,
                this.valoresComplexion);

        this.listaComplexion = (Spinner) findViewById(R.id.spinnerComplexion);
        this.listaComplexion.setAdapter(adaptador);
    }

    public void crearUsuario(View view) {
        String nombre = ((EditText) findViewById(R.id.editTextNombre)).getText().toString();
        int altura = Integer.parseInt(((EditText) findViewById(R.id.editTextAltura)).getText().toString());
        int peso = Integer.parseInt(((EditText) findViewById(R.id.editTextPeso)).getText().toString());
        int complexion = ((Spinner) findViewById(R.id.spinnerComplexion)).getSelectedItemPosition();
        int anioDeNacimiento = Integer.parseInt(((EditText) findViewById(R.id.editTextAnioNacimiento)).
                getText().toString());
        double kmMax = 0;

        if (existeUsuario(nombre)) {
            Toast.makeText(this, "Ya existe un usuario con ese nombre. Pruebe con otro.",
                    Toast.LENGTH_SHORT).show();
        } else {

            this.usuario = new Usuario(nombre, altura, peso, complexion, anioDeNacimiento);

            if (GestionFicherosConfigs.escribirUsuarios(this.usuario, getBaseContext()) == 0) {
                Toast.makeText(this, "Usuario guardado correctamente.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El usuario no ha sido creado.", Toast.LENGTH_SHORT).show();
            }
        }

        GestionFicherosConfigs.leerUsuarios(getBaseContext());
        menuUsuarios();
    }


    public boolean existeUsuario(String nombre) {
        boolean comp = false;
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>(GestionFicherosConfigs.leerUsuarios(getBaseContext()));
        Iterator<Usuario> itr = usuarios.iterator();

        while (itr.hasNext() && !comp) {
            Usuario usu = itr.next();

            comp = (usu.getNombre()).equals(nombre);
        }

        return comp;
    }

    public void menuUsuarios() {
        Intent intent = new Intent(ActivityUsuarioNuevo.this, ActivityUsuarioSeleccionado.class);
        startActivity(intent);
    }
}
