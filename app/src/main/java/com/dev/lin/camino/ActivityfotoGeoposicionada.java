package com.dev.lin.camino;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.Serializable;


public class ActivityfotoGeoposicionada extends ActionBarActivity {

    private ImageView img;
    private Usuario usuarioSeleccionado = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_geoposicionada);
        usuarioSeleccionado = (Usuario) getIntent().getSerializableExtra("usuarioSeleccionado");
        img = (ImageView)this.findViewById(R.id.imageViewFoto);

    }

    public void sacarFoto(View view) {
        Intent i = new Intent( android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        File imageFolder = new File(Environment.getExternalStorageDirectory(),"Fotos_Geoposicionadas");
        imageFolder.mkdirs();
        File imagen = new File(imageFolder,"foto.jpg");
        Uri uriSavedImage = Uri.fromFile(imagen);
        i.putExtra(MediaStore.EXTRA_OUTPUT,uriSavedImage);
        startActivityForResult(i,1);
    }

    public void borrarFoto(View view) {
        img = null;
        Intent i = new Intent(ActivityfotoGeoposicionada.this, ActivityfotoGeoposicionada.class);
        i.putExtra("usuarioSeleccionado", (Serializable) usuarioSeleccionado);
        startActivity(i);
    }
    // AQUI HAY QUE AÑADIR LA POSICION GPS COMO NOMBRE DE LA FOTO EN LUGAR DE "FOTO.JPG"
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bitmap bMap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() +
                    "/Fotos_Geoposicionadas/" + "foto.jpg");
            img.setImageBitmap(bMap);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activityfoto_geoposicionada, menu);
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
