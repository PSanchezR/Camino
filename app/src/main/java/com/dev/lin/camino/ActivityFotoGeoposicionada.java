package com.dev.lin.camino;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.Serializable;

/**
 * Captura de fotos
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class ActivityFotoGeoposicionada extends ActionBarActivity {
    private String nombre;
    private Usuario usuarioSeleccionado = null;
    private ImageView imagenCapturada = null;
    private File foto = null;
    FTPClient cliente = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_geoposicionada);
        usuarioSeleccionado = (Usuario) getIntent().getSerializableExtra("usuarioSeleccionado");
        imagenCapturada = (ImageView) this.findViewById(R.id.imageViewFoto);
    }

    public void sacarFoto(View view) {
        Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        File imageFolder = new File(Environment.getExternalStorageDirectory() + "/DCIM/Camino/");
        imageFolder.mkdirs();
        nombre = usuarioSeleccionado.getNombre() + System.currentTimeMillis() + ".png";
        foto = new File(imageFolder, nombre);
        Uri idImagen = Uri.fromFile(foto);
        i.putExtra(MediaStore.EXTRA_OUTPUT, idImagen);
        i.putExtra("foto", (Serializable) foto);
        startActivityForResult(i, 1);
    }

    public void enviarFoto(View view) {
        Intent i = new Intent(ActivityFotoGeoposicionada.this, ActivityFotoGeoposicionada.class);
        i.putExtra("usuarioSeleccionado", (Serializable) usuarioSeleccionado);

        if (foto != null) {
            new ConexionFTP().execute(foto);
        } else {
            Toast.makeText(this, "No se ha capturado ninguna foto.", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bitmap bMap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() +
                    "/DCIM/Camino/" + nombre);
            imagenCapturada.setImageBitmap(bMap);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_foto_geoposicionada, menu);
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
