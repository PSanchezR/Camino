package com.dev.lin.camino;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Datos del usuario de la aplicación
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class ActivityUsuarioDatos extends ActionBarActivity {
    private static final String USUARIO_DATOS = "UsuarioDatos";

    private Usuario usuarioSeleccionado = null;

    protected String[] valoresComplexion = {"Nada deportista", "Poco deportista", "Deportista Amateur",
            "Deportista profesional"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_datos);

        this.usuarioSeleccionado = (Usuario) getIntent().getSerializableExtra("usuarioSeleccionado");

        ((TextView) findViewById(R.id.textViewNombre)).setText("Usuario: " + this.usuarioSeleccionado.
                getNombre());
        ((EditText) findViewById(R.id.editTextAltura)).setText("" + this.usuarioSeleccionado.getAltura());
        ((EditText) findViewById(R.id.editTextPeso)).setText("" + this.usuarioSeleccionado.getPeso());
        ((TextView) findViewById(R.id.textViewDistanciaMax)).setText("   " + this.usuarioSeleccionado.
                getKmMaximos() + " Kms");

        ArrayAdapter adaptadorComplexion = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, this.valoresComplexion);
        Spinner spinnerComplexion = (Spinner) findViewById(R.id.spinnerComplexion);
        spinnerComplexion.setAdapter(adaptadorComplexion);
        spinnerComplexion.setSelection(this.usuarioSeleccionado.getComplexion());

        ArrayAdapter<String> adaptadorCaminos = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, this.cargarCaminos());
        ListView lista = (ListView) findViewById(R.id.listViewMisCaminos);
        lista.setAdapter(adaptadorCaminos);
        lista.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                        // Cuando se seleccione un camino pasa a ser el actual.
                        String seleccionado = (String) a.getItemAtPosition(position);
                        usuarioSeleccionado.setCaminoActual(buscarCamino(seleccionado));

                        GestionFicherosConfigs.escribirUsuarios(usuarioSeleccionado, getBaseContext());

                        Intent i = new Intent(ActivityUsuarioDatos.this, ActivityCaminoActual.class);
                        i.putExtra("usuarioSeleccionado", (Serializable) usuarioSeleccionado);
                        startActivity(i);
                    }
                }
        );
    }

    public Camino buscarCamino(String nombre) {
        Camino camino;

        Iterator<Camino> itr = this.usuarioSeleccionado.getMisCaminos().iterator();
        while (itr.hasNext()) {
            camino = itr.next();
            if (camino.getNombre().equals(nombre)) {
                return camino;
            }
        }
        return null;
    }

    public ArrayList<String> cargarCaminos() {
        ArrayList<String> nombresCaminos = new ArrayList<String>();
        ArrayList<Camino> caminos = this.usuarioSeleccionado.getMisCaminos();

        for (int i = 0; i < caminos.size(); i++) {
            nombresCaminos.add(caminos.get(i).getNombre());
        }

        return nombresCaminos;
    }

    //Método que edita los cambios del usuario seleccionado y guarda los mismos en el fichero de usuarios.
    public void aplicarCambios(View view) {
        this.usuarioSeleccionado.setAltura(Integer.parseInt(((EditText) findViewById(R.id.editTextAltura)).
                getText() + ""));
        this.usuarioSeleccionado.setPeso(Integer.parseInt(((EditText) findViewById(R.id.editTextPeso)).
                getText() + ""));
        this.usuarioSeleccionado.setComplexion(((Spinner) findViewById(R.id.spinnerComplexion)).
                getSelectedItemPosition());
        this.usuarioSeleccionado.calcularKmMaximos();
        GestionFicherosConfigs.escribirUsuarios(this.usuarioSeleccionado, getBaseContext());

        Intent i = new Intent(ActivityUsuarioDatos.this, ActivityMenuPrincipal.class);
        i.putExtra("usuarioSeleccionado", this.usuarioSeleccionado);
        startActivity(i);
    }
}
