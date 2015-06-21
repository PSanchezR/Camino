package com.dev.lin.camino;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Selección de usuario
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class ActivityUsuarioSeleccionado extends ActionBarActivity {
    private static final String USUARIO_SELECCIONADO = "UsuarioSeleccionado";

    private Usuario usuarioSeleccionado = null;

    private String nombreUsuario = null;

    private ArrayList<String> nombresUsuarios = new ArrayList<String>();
    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_seleccionado);

        this.cargarUsuarios();
        this.seleccionarUsuario();
    }

    //Este método carga los usuarios leidos del fichero en la ListView
    public void cargarUsuarios() {
        this.usuarios = new ArrayList<Usuario>(GestionFicherosConfigs.leerUsuarios(getBaseContext()));

        Iterator<Usuario> itr = this.usuarios.iterator();

        while (itr.hasNext()) {
            Usuario usuario = itr.next();

            this.nombresUsuarios.add(usuario.getNombre());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                this.nombresUsuarios);
        ListView lista = (ListView) findViewById(R.id.listViewListaUsuarios);
        lista.setAdapter(adapter);
    }

    public void seleccionarUsuario() {
        ListView lista = (ListView) findViewById(R.id.listViewListaUsuarios);
        ArrayAdapter<String> adaptador;
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.nombresUsuarios);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                        nombreUsuario = (String) a.getItemAtPosition(position);
                        buscarUsuario();
                        Intent i = new Intent(ActivityUsuarioSeleccionado.this, ActivityMenuPrincipal.class);

                        i.putExtra("usuarioSeleccionado", (Serializable) usuarioSeleccionado);
                        startActivity(i);
                    }
                }
        );
    }

    public void nuevoUsuario(View view) {
        Intent i = new Intent(ActivityUsuarioSeleccionado.this, ActivityUsuarioNuevo.class);
        i.putExtra("usuarios", (Serializable) this.usuarios);
        startActivity(i);
    }

    private void buscarUsuario() {
        boolean comp = false;
        Iterator<Usuario> itr = this.usuarios.iterator();

        while (itr.hasNext() && !comp) {
            Usuario usuario = itr.next();

            comp = (usuario.getNombre()).equals(this.nombreUsuario);

            if (comp) {
                this.usuarioSeleccionado = usuario;
            }
        }
    }
}
