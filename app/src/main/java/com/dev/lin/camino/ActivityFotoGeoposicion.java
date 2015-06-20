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

import java.io.File;

/**
 * Captura de fotos
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class ActivityFotoGeoposicion extends ActionBarActivity {
    private String nombreFoto;
    private Usuario usuarioSeleccionado = null;
    private ImageView fotoCapturada = null;
    private File archivoFoto = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_geoposicion);
        usuarioSeleccionado = (Usuario) getIntent().getSerializableExtra("usuarioSeleccionado");
        fotoCapturada = (ImageView) this.findViewById(R.id.imageViewFoto);
    }

    public void sacarFoto(View view) {
        Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        File imageFolder = new File(Environment.getExternalStorageDirectory() + "/DCIM/Camino/");
        imageFolder.mkdirs();
        nombreFoto = usuarioSeleccionado.getNombre() + System.currentTimeMillis() + ".png";
        archivoFoto = new File(imageFolder, nombreFoto);
        Uri idImagen = Uri.fromFile(archivoFoto);
        i.putExtra(MediaStore.EXTRA_OUTPUT, idImagen);
        startActivityForResult(i, 1);
    }

    public void enviarFoto(View view) {
        Intent i = new Intent(ActivityFotoGeoposicion.this, ActivityMenuPrincipal.class);

        if (archivoFoto != null) {
            new ConexionFTP().execute(archivoFoto);
            startActivity(i);
        } else {
            Toast.makeText(this, "No se ha capturado ninguna foto.", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bitmap bMap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() +
                    "/DCIM/Camino/" + nombreFoto);
            fotoCapturada.setImageBitmap(bMap);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_foto_geoposicion, menu);
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
