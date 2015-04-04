package com.dev.lin.camino;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Formulario de registro de un nuevo usuario
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class FormularioNuevoUsuario extends ActionBarActivity {
    protected String archivo = "usuarios.dat";
    protected Usuario usuario;
    protected Spinner listaComplexion;
    protected String[] valoresComplexion = {"Nada deportista", "Poco deportista", "Deportista Amateur", "Deportista profesional"};

    private GestionFicheros archivador = new GestionFicheros();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_nuevo_usuario);
        ArrayAdapter adaptador = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, valoresComplexion);
        listaComplexion = (Spinner) findViewById(R.id.spinnerComplexion);
        listaComplexion.setAdapter(adaptador);
    }

    public void crearUsuario(View view){
        String nombre = ((EditText) findViewById(R.id.editTextNombre)).getText().toString();
        int altura = Integer.parseInt(((EditText) findViewById(R.id.editTextAltura)).getText().toString());
        int peso = Integer.parseInt(((EditText) findViewById(R.id.editTextPeso)).getText().toString());
        int complexion = ((Spinner) findViewById(R.id.spinnerComplexion)).getSelectedItemPosition();
        int anioDeNacimiento = Integer.parseInt(((EditText) findViewById(R.id.editTextFecha)).getText().toString());

        if (existeUsuario(nombre)) {
            Toast.makeText(this, "Ya existe un usuario con ese nombre. Pruebe con otro.", Toast.LENGTH_SHORT).show();
        } else {
            Usuario usuario = new Usuario(nombre, altura, peso, complexion, anioDeNacimiento);

            if (archivador.guardarUsuario(usuario, getBaseContext()) == 0) {
                Toast.makeText(this, "Usuario guardado correctamente.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El usuario no ha sido creado.", Toast.LENGTH_SHORT).show();
            }
        }

        archivador.recuperarUsuarios(getBaseContext());

        menuUsuarios();
    }

    public boolean existeUsuario(String nombre){
        boolean comp = false;
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>(archivador.recuperarUsuarios(getBaseContext()));
        Iterator<Usuario> itr = usuarios.iterator();

        while (itr.hasNext() && !comp) {
            Usuario usuario = itr.next();

            comp = (usuario.getNombre()).equals(nombre);
        }

        return comp;
    }

    public void menuUsuarios() {
        Intent intent = new Intent(FormularioNuevoUsuario.this, SeleccionUsuario.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_formulario_nuevo_usuario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
