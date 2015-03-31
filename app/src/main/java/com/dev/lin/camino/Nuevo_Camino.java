package com.dev.lin.camino;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Nuevo_Camino extends ActionBarActivity {


    private Pueblo[] pueblos_camino_frances={
            new Pueblo("Saint Jean Pied de Port",0,5,true,true,true,true,true,true),
            new Pueblo("Honto",5,2.6,true,true,true,true,true,false),
            new Pueblo("Orison",2.6,16.6,true,true,true,false,false,false),
            new Pueblo("Roncesvalles",16.6,3,true,true,true,true,true,true),
            new Pueblo("Burguete",3,3.6,true,true,false,false,false,false),
            new Pueblo("Espinal",3.6,4.8,true,true,true,false,false,false),
            new Pueblo("Bizkarreta",4.8,2,true,true,false,false,false,false),
            new Pueblo("Lintzoain",2,8.4,true,true,false,false,false,false),
            new Pueblo("Zubiri",8.4,5.4,true,true,true,false,false,false),
            new Pueblo("Larrasoaña",5.4,4,true,true,true,false,false,false),
            new Pueblo("Zuriaín",4,3.2,false,false,false,false,false,false),
            new Pueblo("Zabaldika",3.2,3.6,false,false,true,false,false,false),
            new Pueblo("Arre",3.6,0.1,false,false,true,false,false,false),
            new Pueblo("Villava",0.1,1.2,true,true,true,false,false,false),
            new Pueblo("Burlada",1.2,3.4,false,false,false,false,false,false),
            new Pueblo("Pamplona",3.4,4.8,true,true,true,true,true,true),
            new Pueblo("Cizur Menor",4.8,6.4,true,false,true,false,false,false),
            new Pueblo("Zariquiegui",6.4,6,true,false,true,false,false,false),
            new Pueblo("Uterga",6,2.8,true,true,true,false,false,false),
            new Pueblo("Muruzábal",2.8,1.8,false,false,false,false,false,false),
            new Pueblo("Óbanos",1.8,2.4,true,true,true,false,false,false),
            new Pueblo("Puente la Reina",2.4,5.4,true,true,true,true,true,true),
            new Pueblo("Mañeru",5.4,2.6,false,false,true,false,false,false),
            new Pueblo("Cirauqui",2.6,5.8,false,false,true,false,false,false),
            new Pueblo("Lorca",5.8,4.8,false,false,true,false,false,false),
            new Pueblo("Villatuerta",4.8,4,true,true,true,false,false,false),// VARIANTE?
            new Pueblo("Estella",4,2,true,true,true,true,true,true),
            new Pueblo("Ayegui",2,0.8,false,false,true,false,false,false),
            new Pueblo("Monasterio de Irache",0.8,1.5,false,false,false,false,false,false),
            new Pueblo("Irache",1.5,3.5,true,true,false,false,false,false),
            new Pueblo("Azqueta",3.5,2,false,false,false,false,false,false),
            new Pueblo("Villamayor de Monjardín",2,12.6,true,true,true,false,false,false),
            new Pueblo("Los Arcos",2,12.6,true,true,true,true,true,true),
            new Pueblo("Sansol",12.6,7,true,true,true,false,false,false),
            new Pueblo("Torres del Rio",7,0.8,true,true,true,false,false,false),
            new Pueblo("Viana",0.8,11,true,true,true,false,false,false),
            new Pueblo("Logroño",11,9.6,true,true,true,true,true,true),
            new Pueblo("Navarrete",9.6,13,true,true,true,true,true,false),
            new Pueblo("Ventosa",13,7.8,true,true,true,false,false,false),
            new Pueblo("Nájera",7.8,9.6,true,true,true,true,true,true),
            new Pueblo("Azofra",9.6,5.8,true,true,true,false,false,false),
            new Pueblo("Cirueña",5.8,9.6,true,true,true,false,false,false),
            new Pueblo("Santo Domingo de la Calzada",5.8,9.6,true,true,true,true,true,true),
            new Pueblo("Grañón",6.2,4,true,true,true,false,false,false),
            new Pueblo("Redecilla del Camino",4,2,true,true,true,false,false,false),
            new Pueblo("Castildelgado",2,2,true,true,false,false,false,false),
            new Pueblo("Viloria de Rioja",2,3.4,true,true,true,false,false,false),
            new Pueblo("Villamayor del Rio",3.4,2,false,false,true,false,false,false),
            new Pueblo("Belorado",2,4.8,true,true,true,true,true,true),
            new Pueblo("Tosantos",4.8,1.8,false,false,true,false,false,false),
            new Pueblo("Villambistia",1.8,1.8,false,false,true,false,false,false),
            new Pueblo("Espinosa del Camino",1.8,3.6,false,false,true,false,false,false),
            new Pueblo("Villafranca Montes de Oca",3.6,12.4,true,true,true,true,true,false),
            new Pueblo("San Juan de Ortega",12.4,3.8,true,true,true,false,false,false),
            new Pueblo("Agés",3.8,2.6,false,false,true,false,false,false),
            new Pueblo("Atapuerca",2.6,6.6,true,true,true,false,false,false),
            new Pueblo("Cardeñuela Riopico",6.6,2,true,false,true,false,false,false),
            new Pueblo("Orbaneja Riopico",2,3.6,true,true,false,false,false,false),
            new Pueblo("Villafría",3.6,7.7,true,true,false,false,false,false),
            new Pueblo("Burgos",7.7,9.8,true,true,false,false,false,false),
            new Pueblo("Tardajos",9.8,2,true,true,true,false,false,false),
            new Pueblo("Rabé de las Calzadas",2,2,false,false,true,false,false,false),
            new Pueblo("Hornillos del Camino",2,5.8,true,true,true,false,false,false),
            new Pueblo("San Bol",5.8,5,false,false,true,false,false,false),
            new Pueblo("Hontanas",5,6,true,true,true,false,false,false),
            new Pueblo("Convento de San Antón",6,3.8,false,false,true,false,false,false),
            new Pueblo("Castrojeríz",3.8,9.8,true,true,true,true,true,true),
            new Pueblo("Ermita de San Nicolás",9.8,1.8,false,false,true,false,false,false),
            new Pueblo("Itero de la Vega",1.8,8,true,true,true,false,false,false),
            new Pueblo("Boadilla del Camino",8,5.8,true,true,true,true,false,false),
            new Pueblo("Frómista",5.8,3.6,true,true,true,true,true,false),
            new Pueblo("Población de Campos",3.6,3.8,true,true,false,false,false,false),
            new Pueblo("Revenga de Campos",3.8,2.2,false,false,false,false,false,false),
            new Pueblo("Villarmentero de Campo",2.2,4.2,true,true,true,false,false,false),
            new Pueblo("Villalcázar de Sirga",4.2,5.8,true,true,true,false,false,false),
            new Pueblo("Calzadilla de la Cueza",5.8,17.2,true,true,true,false,false,false),
            new Pueblo("Ledigos",17.2,6.2,false,false,true,false,false,false),
            new Pueblo("Terradillos de los Templarios",6.2,3.2,true,false,true,false,false,false),
            new Pueblo("Moratinos",6.2,3.2,true,false,true,false,false,false),
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_camino);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nuevo_camino, menu);
        return true;
    }

    public void caminoClasico(View view)
    {
        Intent i = new Intent(Nuevo_Camino.this,Camino_Actual.class);
        startActivity(i);

    }

    public void crearMiCamino(View view)
    {
        Intent i = new Intent(Nuevo_Camino.this,Camino_Actual.class);
        startActivity(i);

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
