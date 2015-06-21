package com.dev.lin.camino;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

/**
 * Menú principal de la aplicación
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class ActivityMenuPrincipal extends ActionBarActivity {
    public static final String SERVIDOR = "caminoapp.bugs3.com";
    public static final String USUARIO = "u223647139";
    public static final String PASS = "caminoapp2015";
    public static final int PUERTO = 21;
    public static final String DIRECTORIO = "fotos";

    private static final String MENU_PRINCIPAL = "MenuPrincipal";

    private Usuario usuarioSeleccionado = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        this.usuarioSeleccionado = (Usuario) getIntent().getSerializableExtra("usuarioSeleccionado");

        ((TextView) findViewById(R.id.textViewNombre)).setText("Usuario: " + " " +
                this.usuarioSeleccionado.getNombre());
    }

    public void datosUsuario(View view) {
        Intent i = new Intent(ActivityMenuPrincipal.this, ActivityUsuarioDatos.class);
        i.putExtra("usuarioSeleccionado", (Serializable) this.usuarioSeleccionado);
        startActivity(i);
    }

    public void caminoNuevo(View view) {
        Intent i = new Intent(ActivityMenuPrincipal.this, ActivityCaminoNuevo.class);
        i.putExtra("usuarioSeleccionado", (Serializable) this.usuarioSeleccionado);
        startActivity(i);
    }

    public void caminoActual(View view) {
        if (usuarioSeleccionado.getCaminoActual() != null) {
            Intent i = new Intent(ActivityMenuPrincipal.this, ActivityCaminoActual.class);
            i.putExtra("usuarioSeleccionado", (Serializable) this.usuarioSeleccionado);
            startActivity(i);
        } else {
            Toast.makeText(this, "No tiene asignado un camino actual", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(ActivityMenuPrincipal.this, ActivityMenuPrincipal.class);
            i.putExtra("usuarioSeleccionado", (Serializable) this.usuarioSeleccionado);
            startActivity(i);
        }
    }

    public void probarGPS(View view) {
        Intent i = new Intent(ActivityMenuPrincipal.this, ActivityPruebaPosicionamiento.class);
        i.putExtra("usuarioSeleccionado", (Serializable) this.usuarioSeleccionado);
        startActivity(i);
    }

    public void capturarFoto(View view) {
        Intent i = new Intent(ActivityMenuPrincipal.this, ActivityFotoCapturada.class);
        i.putExtra("usuarioSeleccionado", (Serializable) this.usuarioSeleccionado);
        startActivity(i);
    }

    public void listarFotos(View view) {
        Intent i = new Intent(ActivityMenuPrincipal.this, ActivityFotoListado.class);
        i.putExtra("usuarioSeleccionado", (Serializable) this.usuarioSeleccionado);
        startActivity(i);
    }
}
