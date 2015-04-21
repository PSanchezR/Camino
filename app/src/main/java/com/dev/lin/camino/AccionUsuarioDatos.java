package com.dev.lin.camino;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Datos del usuario de la aplicación
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class AccionUsuarioDatos extends ActionBarActivity {
    protected String[] valoresComplexion = {"Nada deportista", "Poco deportista", "Deportista Amateur", "Deportista profesional"};
    private Usuario usuarioSeleccionado = null;
    private GestionFicheros archivador = new GestionFicheros();
    private static final String DATOS_USUARIO = "DatosUsuario";

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_usuario);

        usuarioSeleccionado = (Usuario) getIntent().getSerializableExtra("usuarioSeleccionado");
 //       Log.d(AccionUsuarioDatos.DATOS_USUARIO, usuarioSeleccionado.toString());
//        Log.d(AccionUsuarioDatos.DATOS_USUARIO, usuarioSeleccionado.getNombreCaminoActual());

        ((TextView) findViewById(R.id.textViewNombre)).setText("Usuario: " + usuarioSeleccionado.getNombre());
        ((EditText) findViewById(R.id.editTextAltura)).setText("" + usuarioSeleccionado.getAltura());
        ((EditText) findViewById(R.id.editTextPeso)).setText("" + usuarioSeleccionado.getPeso());
        ((TextView) findViewById(R.id.textViewDistMax)).setText("   " + usuarioSeleccionado.getKmMaximos() + " Kms");

        ArrayAdapter adaptador = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, valoresComplexion);
        Spinner spinnerComplexion = (Spinner) findViewById(R.id.spinnerComplexion);
        spinnerComplexion.setAdapter(adaptador);
        spinnerComplexion.setSelection(usuarioSeleccionado.getComplexion());

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cargarCaminos());
        ListView lista = (ListView) findViewById(R.id.listViewMisCaminos);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                       /* Cuando se seleccione un camino pasa a ser el actual. */
                        String seleccionado = (String) a.getItemAtPosition(position);
                        usuarioSeleccionado.setCaminoActual(buscarCamino(seleccionado));
                        archivador.escribirUsuarios(usuarioSeleccionado, getBaseContext());
                        Intent i = new Intent(AccionUsuarioDatos.this, AccionCaminoActual.class);
                        i.putExtra("usuarioSeleccionado", (Serializable) usuarioSeleccionado);
                        startActivity(i);
                    }
                }
        );
    }
    public Camino buscarCamino(String nombre)
    {
        Camino c;

        Iterator<Camino> itr = this.usuarioSeleccionado.getMisCaminos().iterator();
        while(itr.hasNext())
        {
            c = itr.next();
            if(c.getNombre().equals(nombre))
            {
                return c;
            }
        }
        return null;
    }
    public ArrayList<String> cargarCaminos()
    {
        ArrayList<String> nombresCaminos = new ArrayList<String>();
        ArrayList<Camino> caminos = usuarioSeleccionado.getMisCaminos();
        /*Iterator<Camino> itr = caminos.iterator();
        while (itr.hasNext()) {
            Camino camino = itr.next();
            nombresCaminos.add(camino.getNombre());
        }*/

        for(int i = 0; i<caminos.size();i++)
        {
            nombresCaminos.add(caminos.get(i).getNombre());
        }


        return nombresCaminos;
    }

    //Método que edita los cambios del usuario seleccionado y guarda los mismos en el fichero de usuarios.
    public void aplicarCambios(View view) {
        usuarioSeleccionado.setAltura(Integer.parseInt(((EditText) findViewById(R.id.editTextAltura)).getText() + ""));
        usuarioSeleccionado.setPeso(Integer.parseInt(((EditText) findViewById(R.id.editTextPeso)).getText() + ""));
        usuarioSeleccionado.setComplexion(((Spinner) findViewById(R.id.spinnerComplexion)).getSelectedItemPosition());
        usuarioSeleccionado.calcularKmMaximos();
        archivador.escribirUsuarios(usuarioSeleccionado, getBaseContext());

        Intent i = new Intent(AccionUsuarioDatos.this, AccionMenuPrincipal.class);
        i.putExtra("usuarioSeleccionado", usuarioSeleccionado);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_accion_usuario_datos, menu);
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
