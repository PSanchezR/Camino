package com.dev.lin.camino;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Selección de usuario
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class SeleccionUsuario extends ActionBarActivity {
    static ArrayList<Usuario> usuarios;//Lista de objetos usuario que se leerán desde fichero.

    private ArrayAdapter<String> adapter; //Adaptador para pasar los nombres a un listview
    private String seleccionado = null;
    private Usuario usuarioSeleccionado = null;
    private ArrayList<String> nombresUsuarios = new ArrayList<String>();// Lista de nombres de usuario

    private GestionFicheros archivador = new GestionFicheros();

    //Este método carga los usuarios leidos del fichero en la ListView
    public void cargarUsuarios(){
        this.usuarios = new ArrayList<Usuario>(archivador.recuperarUsuarios(getBaseContext()));

        Iterator<Usuario> itr = usuarios.iterator();

        while (itr.hasNext()) {
            Usuario usuario = itr.next();

            nombresUsuarios.add(usuario.getNombre());
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombresUsuarios);
        ListView lista = (ListView) findViewById(R.id.listUsuarios);
        lista.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_usuario);

        cargarUsuarios();
        usuarioSeleccionado();
    }

    public void nuevoUsuario(View view) {
        Intent i = new Intent(SeleccionUsuario.this, FormularioNuevoUsuario.class);
        i.putExtra("usuarios", (Serializable) usuarios);
        startActivity(i);
    }

    public void buscarUsuario() {
        boolean comp = false;
        Iterator<Usuario> itr = usuarios.iterator();

        while (itr.hasNext() && !comp) {
            Usuario usuario = itr.next();

            comp = (usuario.getNombre()).equals(seleccionado);

            if (comp) {
                usuarioSeleccionado = usuario;
            }
        }
    }

    public void usuarioSeleccionado() {
        ListView lista = (ListView) findViewById(R.id.listUsuarios);
        ArrayAdapter<String> adaptador;
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombresUsuarios);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                        seleccionado = (String) a.getItemAtPosition(position);
                        buscarUsuario();
                        Intent i = new Intent(SeleccionUsuario.this, MenuPrincipal.class);

                        i.putExtra("usuarioSeleccionado", (Serializable) usuarioSeleccionado);
                        startActivity(i);
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_seleccion_usuario, menu);
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
