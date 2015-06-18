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

import java.io.File;
import java.io.Serializable;


public class ActivityFotoGeoposicionada extends ActionBarActivity {
    private ImageView img;
    private Usuario usuarioSeleccionado = null;
    private String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_geoposicionada);
        usuarioSeleccionado = (Usuario) getIntent().getSerializableExtra("usuarioSeleccionado");
        img = (ImageView) this.findViewById(R.id.imageViewFoto);
    }

    //nombre de la foto es nombre de usuario más tiempo
    public void sacarFoto(View view) {
        Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        File imageFolder = new File(Environment.getExternalStorageDirectory() + "/DCIM/Camino/");
        imageFolder.mkdirs();
        nombre = usuarioSeleccionado.getNombre() + System.currentTimeMillis() + ".png";
        File imagen = new File(imageFolder, nombre);
        System.out.println(nombre);
        System.out.println(getApplicationInfo().dataDir);
        Uri uriSavedImage = Uri.fromFile(imagen);
        i.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
        startActivityForResult(i, 1);
    }

    public void enviarFoto(View view) {
        img = null;
        Intent i = new Intent(ActivityFotoGeoposicionada.this, ActivityFotoGeoposicionada.class);
        i.putExtra("usuarioSeleccionado", (Serializable) usuarioSeleccionado);
        startActivity(i);
    }

    public void borrarFoto(View view) {
        img = null;
        Intent i = new Intent(ActivityFotoGeoposicionada.this, ActivityFotoGeoposicionada.class);
        i.putExtra("usuarioSeleccionado", (Serializable) usuarioSeleccionado);
        startActivity(i);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bitmap bMap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() +
                    "/DCIM/Camino/" + nombre);
            img.setImageBitmap(bMap);
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
