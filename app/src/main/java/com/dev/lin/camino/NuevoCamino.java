package com.dev.lin.camino;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Datos de un nuevo camino
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class NuevoCamino extends ActionBarActivity {
    private Usuario usuarioSeleccionado = null;

    private Pueblo[] pueblosCaminoFrances = {
            new Pueblo("Saint Jean Pied de Port", 0, 5, true, true, true, true, true, true),
            new Pueblo("Honto", 5, 2.6, true, true, true, true, true, false),
            new Pueblo("Orison", 2.6, 16.6, true, true, true, false, false, false),
            new Pueblo("Roncesvalles", 16.6, 3, true, true, true, true, true, true),
            new Pueblo("Burguete", 3, 3.6, true, true, false, false, false, false),
            new Pueblo("Espinal", 3.6, 4.8, true, true, true, false, false, false),
            new Pueblo("Bizkarreta", 4.8, 2, true, true, false, false, false, false),
            new Pueblo("Lintzoain", 2, 8.4, true, true, false, false, false, false),
            new Pueblo("Zubiri", 8.4, 5.4, true, true, true, false, false, false),
            new Pueblo("Larrasoaña", 5.4, 4, true, true, true, false, false, false),
            new Pueblo("Zuriaín", 4, 3.2, false, false, false, false, false, false),
            new Pueblo("Zabaldika", 3.2, 3.6, false, false, true, false, false, false),
            new Pueblo("Arre", 3.6, 0.1, false, false, true, false, false, false),
            new Pueblo("Villava", 0.1, 1.2, true, true, true, false, false, false),
            new Pueblo("Burlada", 1.2, 3.4, false, false, false, false, false, false),
            new Pueblo("Pamplona", 3.4, 4.8, true, true, true, true, true, true),
            new Pueblo("Cizur Menor", 4.8, 6.4, true, false, true, false, false, false),
            new Pueblo("Zariquiegui", 6.4, 6, true, false, true, false, false, false),
            new Pueblo("Uterga", 6, 2.8, true, true, true, false, false, false),
            new Pueblo("Muruzábal", 2.8, 1.8, false, false, false, false, false, false),
            new Pueblo("Óbanos", 1.8, 2.4, true, true, true, false, false, false),
            new Pueblo("Puente la Reina", 2.4, 5.4, true, true, true, true, true, true),
            new Pueblo("Mañeru", 5.4, 2.6, false, false, true, false, false, false),
            new Pueblo("Cirauqui", 2.6, 5.8, false, false, true, false, false, false),
            new Pueblo("Lorca", 5.8, 4.8, false, false, true, false, false, false),
            new Pueblo("Villatuerta", 4.8, 4, true, true, true, false, false, false),// VARIANTE?
            new Pueblo("Estella", 4, 2, true, true, true, true, true, true),
            new Pueblo("Ayegui", 2, 0.8, false, false, true, false, false, false),
            new Pueblo("Monasterio de Irache", 0.8, 1.5, false, false, false, false, false, false),
            new Pueblo("Irache", 1.5, 3.5, true, true, false, false, false, false),
            new Pueblo("Azqueta", 3.5, 2, false, false, false, false, false, false),
            new Pueblo("Villamayor de Monjardín", 2, 12.6, true, true, true, false, false, false),
            new Pueblo("Los Arcos", 2, 12.6, true, true, true, true, true, true),
            new Pueblo("Sansol", 12.6, 7, true, true, true, false, false, false),
            new Pueblo("Torres del Rio", 7, 0.8, true, true, true, false, false, false),
            new Pueblo("Viana", 0.8, 11, true, true, true, false, false, false),
            new Pueblo("Logroño", 11, 9.6, true, true, true, true, true, true),
            new Pueblo("Navarrete", 9.6, 13, true, true, true, true, true, false),
            new Pueblo("Ventosa", 13, 7.8, true, true, true, false, false, false),
            new Pueblo("Nájera", 7.8, 9.6, true, true, true, true, true, true),
            new Pueblo("Azofra", 9.6, 5.8, true, true, true, false, false, false),
            new Pueblo("Cirueña", 5.8, 9.6, true, true, true, false, false, false),
            new Pueblo("Santo Domingo de la Calzada", 5.8, 9.6, true, true, true, true, true, true),
            new Pueblo("Grañón", 6.2, 4, true, true, true, false, false, false),
            new Pueblo("Redecilla del Camino", 4, 2, true, true, true, false, false, false),
            new Pueblo("Castildelgado", 2, 2, true, true, false, false, false, false),
            new Pueblo("Viloria de Rioja", 2, 3.4, true, true, true, false, false, false),
            new Pueblo("Villamayor del Rio", 3.4, 2, false, false, true, false, false, false),
            new Pueblo("Belorado", 2, 4.8, true, true, true, true, true, true),
            new Pueblo("Tosantos", 4.8, 1.8, false, false, true, false, false, false),
            new Pueblo("Villambistia", 1.8, 1.8, false, false, true, false, false, false),
            new Pueblo("Espinosa del Camino", 1.8, 3.6, false, false, true, false, false, false),
            new Pueblo("Villafranca Montes de Oca", 3.6, 12.4, true, true, true, true, true, false),
            new Pueblo("San Juan de Ortega", 12.4, 3.8, true, true, true, false, false, false),
            new Pueblo("Agés", 3.8, 2.6, false, false, true, false, false, false),
            new Pueblo("Atapuerca", 2.6, 6.6, true, true, true, false, false, false),
            new Pueblo("Cardeñuela Riopico", 6.6, 2, true, false, true, false, false, false),
            new Pueblo("Orbaneja Riopico", 2, 3.6, true, true, false, false, false, false),
            new Pueblo("Villafría", 3.6, 7.7, true, true, false, false, false, false),
            new Pueblo("Burgos", 7.7, 9.8, true, true, false, false, false, false),
            new Pueblo("Tardajos", 9.8, 2, true, true, true, false, false, false),
            new Pueblo("Rabé de las Calzadas", 2, 2, false, false, true, false, false, false),
            new Pueblo("Hornillos del Camino", 2, 5.8, true, true, true, false, false, false),
            new Pueblo("San Bol", 5.8, 5, false, false, true, false, false, false),
            new Pueblo("Hontanas", 5, 6, true, true, true, false, false, false),
            new Pueblo("Convento de San Antón", 6, 3.8, false, false, true, false, false, false),
            new Pueblo("Castrojeríz", 3.8, 9.8, true, true, true, true, true, true),
            new Pueblo("Ermita de San Nicolás", 9.8, 1.8, false, false, true, false, false, false),
            new Pueblo("Itero de la Vega", 1.8, 8, true, true, true, false, false, false),
            new Pueblo("Boadilla del Camino", 8, 5.8, true, true, true, true, false, false),
            new Pueblo("Frómista", 5.8, 3.6, true, true, true, true, true, false),
            new Pueblo("Población de Campos", 3.6, 3.8, true, true, false, false, false, false),
            new Pueblo("Revenga de Campos", 3.8, 2.2, false, false, false, false, false, false),
            new Pueblo("Villarmentero de Campo", 2.2, 4.2, true, true, true, false, false, false),
            new Pueblo("Villalcázar de Sirga", 4.2, 5.8, true, true, true, false, false, false),
            new Pueblo("Carrión de los Condes", 5.8, 17.2, true, true, true, true, true, true),
            new Pueblo("Calzadilla de la Cueza", 17.2, 6.2, true, true, true, false, false, false),
            new Pueblo("Ledigos", 6.2, 3.2, false, false, true, false, false, false),
            new Pueblo("Terradillos de los Templarios", 3.2, 3.4, true, false, true, false, false, false),
            new Pueblo("Moratinos", 3.4, 2.8, true, true, true, false, false, false),
            new Pueblo("San Nicolás del Real Camino", 2.8, 6.8, false, false, true, false, false, false),
            new Pueblo("Sahagún", 6.8, 10.4, true, true, true, true, true, true),
            new Pueblo("Bercianos del Real Camino", 10.4, 7.4, true, true, true, false, false, false),
            new Pueblo("El Burgo Ranero", 7.4, 12.6, true, true, true, false, false, false),
            new Pueblo("Reliegos", 12.6, 6.8, true, false, true, false, false, false),
            new Pueblo("Mansilla de las Mulas", 6.8, 4.4, true, true, true, true, true, true),
            new Pueblo("Villamoros de Mansilla", 4.4, 1.6, false, false, false, false, false, false),
            new Pueblo("Puente de Villarente", 1.6, 4.6, true, true, true, false, false, false),
            new Pueblo("Arcahueja", 4.6, 6.8, true, true, true, false, false, false),
            new Pueblo("Leon", 6.8, 4.2, true, true, true, true, true, true),
            new Pueblo("Trobajo del Camino", 4.2, 3.4, true, true, false, false, false, false),
            new Pueblo("La Virgen del Camino", 3.4, 4.2, true, true, true, true, true, true),
            new Pueblo("Valverde de la Virgen", 4.2, 1.2, false, false, false, false, false, false),
            new Pueblo("San Miguel del Camino", 1.2, 7.2, false, false, false, false, false, false),
            new Pueblo("Villadangos del P'aramo", 7.2, 4.6, true, true, true, false, false, false),
            new Pueblo("San Mart'in del Camino", 4.6, 7.6, true, false, true, false, false, false),
            new Pueblo("Hospital de 'Orbigo", 7.6, 2, true, true, true, true, true, true),
            new Pueblo("Villares de 'Orbigo", 2, 2.6, true, false, true, false, false, false),
            new Pueblo("Santibañez de Valdeiglesias", 2.6, 8.2, true, false, true, false, false, false),
            new Pueblo("San Justo de la Vega", 8.2, 3.6, true, false, true, false, false, false),
            new Pueblo("Astorga", 3.6, 2.2, true, true, true, true, true, true),
            new Pueblo("Valdeviejas", 2.2, 2.4, false, false, true, false, false, false),
            new Pueblo("Murias de Rechivaldo", 2.4, 4.6, true, true, true, false, false, false),
            new Pueblo("Santa Catalina de Somoza", 4.6, 4.2, true, true, true, false, false, false),
            new Pueblo("El Ganso", 4.2, 7, true, true, true, false, false, false),
            new Pueblo("Rabanal del Camino", 7, 5.6, true, true, true, true, true, true),
            new Pueblo("Foncebad'on", 5.6, 2, true, false, true, false, false, false),
            new Pueblo("Cruz de Ferro", 2, 2.2, false, false, false, false, false, false),
            new Pueblo("Manjar'in", 2.2, 7, false, false, true, false, false, false),
            new Pueblo("El Acebo", 7, 3.6, true, true, true, false, false, false),
            new Pueblo("Riego de Ambr'os", 3.6, 4.4, true, true, true, false, false, false),
            new Pueblo("Molinaseca", 4.4, 4, true, true, true,true, true, true),
            new Pueblo("Campo", 4, 3.8, false, false, false,false, false, false),
            new Pueblo("Ponferrada", 3.8, 5, true, true, true,true, true, true),
            new Pueblo("Columbrianos", 5, 2.8, true, true, false,false, false, false),
            new Pueblo("Fuentesnuevas", 2.8, 2, true, false, true,false, false, false),
            new Pueblo("Camponaraya", 2, 6, false, false, false,false, false, false),
            new Pueblo("Cacabelos", 6, 2, true, true,true, true,true, true),
            new Pueblo("Pieros", 2, 2.2, true, false,true, false,false, false),
            new Pueblo("Valtuille de Arriba", 2.2, 4.6, false, false,false, false,false, false),
            new Pueblo("Villafranca del Bierzo", 4.6, 5.4, true, true,true, true,true, true),
            new Pueblo("Pereje", 5.4, 4.4, true, false,true, false,false, false),
            new Pueblo("Trabadelo", 4.4, 3.6, true, true,true, true,true, true),
            new Pueblo("La Portela de Valcarce", 3.6, 1.2, true, true,true, false,false, false),
            new Pueblo("Ambasmestas", 1.2, 1.8, true, true,true, false,false, false),
            new Pueblo("Vega de Valcarce", 1.8, 2.2, true, true,true, true, true,true),
            new Pueblo("Ruitelán", 2.2, 1.2, true, false,true, false,false, false),
            new Pueblo("Las Herrerías", 1.2, 3.6, true, true,true, false,false, false),
            new Pueblo("La Faba", 3.6, 2.4, true, false,true, false,false, false),
            new Pueblo("Laguna de Castilla", 2.4, 2.6, true, false,true, false,false, false),
            new Pueblo("O Cebreiro", 2.6, 3.2, true, true,true, false,false, false),
            new Pueblo("Liñares", 3.2, 2.4, true, true,false, false,false, false),
            new Pueblo("Hospital da Condesa", 2.4, 3, false, false,true, false,false, false),
            new Pueblo("Alto do Pollo", 3, 3.4, true, true,true, false,false, false),
            new Pueblo("Fonfría", 3.4, 2.4, true, true,true, false,false, false),
            new Pueblo("O Biduedo", 2.4, 3, true, true,false, false,false, false),
            new Pueblo("Fillobal", 3, 3.6, false, false,true, false,false, false),
            new Pueblo("Triacastela", 3.6, 1.3, true, true,true, true,true, true),
            new Pueblo("A Balsa", 1.3, 2.5, true, false,true, false,false, false),
            new Pueblo("San Xil", 2.5, 6.8, false, false,false, false,false, false),
            new Pueblo("Furela", 6.8, 1.4, false, false,false, false,false, false),
            new Pueblo("Pintín", 1.4, 1.2, true, true,false, false,false, false),
            new Pueblo("Calvor", 1.2, 1, false, false,true, false,false, false),
            new Pueblo("San Mamede do Camiño", 1, 3.6, true, false,true, false,false, false),
            new Pueblo("Triacastela", 3.6, 3.8, true, true,true, true,true, true),

           //PREGUNTAR!?!?!?!

            new Pueblo("Sarria", 3.8, 4.2, true, true,true, true,true, true),
            new Pueblo("Barbadelo", 4.2, 2.8, true, true,true, false,false, false),
            new Pueblo("Molino de Marzán", 2.8, 4.6, false, false,true, false,false, false),
            new Pueblo("Morgade", 4.6, 1, true, true,true, false,false, false),
            new Pueblo("Ferreiros", 1, 1, true, false,true, false,false, false),
            new Pueblo("A Pena", 1, 3.2, true, false,true, false,false, false),
            new Pueblo("Mercadoiro", 3.2, 3, true, false,true, false,false, false),
            new Pueblo("Vilachá", 3, 2.2, true, false,true, false,false, false),
            new Pueblo("Portomarín", 2.2, 4.8, true, true,true, true,true, true),
            new Pueblo("Toxibó", 4.8, 3.2, false, false,false, false,false, false),
            new Pueblo("Gonzar", 3.2, 1.4, true, false,true, false,false, false),
            new Pueblo("Castromaior", 1.4, 2.4, true, true,false, false,false, false),
            new Pueblo("Hospital da Cruz", 2.4, 1.2, true, true,true, false,false, false),
            new Pueblo("Ventas de Narón", 1.2, 3.4, true, false,true, false,false, false),
            new Pueblo("Ligonde", 3.4, 3.6, true, true,true, false,false, false),
            new Pueblo("Lestedo", 3.6, 5, true, true,true, false,false, false),
            new Pueblo("Palas de Rei", 5, 3.2, true, true,true,true, true,true),
            new Pueblo("San Xulián do Camiño", 3.2, 1.2, true, false,true,false, false,false),
            new Pueblo("Ponte Campaña", 1.2, 1.2, true, false,true,false, false,false),
            new Pueblo("Casanova", 1.2, 3, false, false,true,false, false,false),
            new Pueblo("Coto", 3, 0.6, true, true,false,false, false,false),
            new Pueblo("Leboreiro", 0.6, 6, true, true,false,false, false,false),
            new Pueblo("Melide", 6, 5.8, true, true,true, true, true, true),
            new Pueblo("Boente", 5.8, 2.2, true, false,true, false, false, false),
            new Pueblo("Castañeda", 2.2, 3.2, true, false,true, false, false, false),
            new Pueblo("Ribadixo da Baixo", 3.2, 2.8, true, false,true, false, false, false),
            new Pueblo("Arzúa", 2.8,8, true, true,true, true, true, true),
            new Pueblo("Calle", 8,3.2,false, false, false,false, false, false),
            new Pueblo("Salceda", 3.2,2.2,true, true,true,false, false, false),
            new Pueblo("A Brea", 2.2,3.2,true, true,false,false, false, false),
            new Pueblo("Santa Irene", 3.2,1.4,true, false,true,false, false, false),
            new Pueblo("A Rúa (O Pino)", 1.4,1.4,true, true,false,false, false, false),
            new Pueblo("O Pedrouzo (O Pino)", 1.4,3.2,true, true,true,true, true, true),
            new Pueblo("Amenal", 3.2,4.2,true, false,true,false, false, false),
            new Pueblo("San Paio", 4.2,2.4,false, false,false,false, false, false),
            new Pueblo("Lavacolla", 2.4 ,4.6,true, true,false,false, false, false),
            new Pueblo("San Marcos", 4.6 ,0.6,true, true,false,false, false, false),
            new Pueblo("Monte do Gozo", 0.6 ,4.8,true, false,true,false, false, false),
            new Pueblo("Santiago de Compostela", 4.8 ,0,true, true,true,true, true, true)

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] nombresPueblos = new String[pueblosCaminoFrances.length+1];
        nombresPueblos[0]="Sin seleccionar";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_camino);
        calculaNombres(nombresPueblos);
        ArrayAdapter adaptador = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, nombresPueblos);
        Spinner spinnerInicio = (Spinner) findViewById(R.id.spinnerCiudadInicio);
        Spinner spinnerFin = (Spinner) findViewById(R.id.spinnerCiudadFin);
        spinnerInicio.setAdapter(adaptador);
        spinnerFin.setAdapter(adaptador);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nuevo_camino, menu);
        return true;
    }

    public void caminoClasico(View view) {
        Intent i = new Intent(NuevoCamino.this, CaminoActual.class);
        startActivity(i);
    }

    public void calculaNombres(String[] nombresPueblos)
    {
        for (int i = 0; i< pueblosCaminoFrances.length;i++)
        {
            nombresPueblos[i+1]=(pueblosCaminoFrances[i].getNombre());
        }
    }


    public void miCamino(View view)
    {
         /* int dias = 0;// Integer.parseInt(""+((EditText) findViewById(R.id.editTextDias)).getText());
        String nombre = "";//(""+((EditText) findViewById(R.id.editTextNombreCamino)).getText());

        String comienzo="";// = (""+((Spinner) findViewById(R.id.spinnerCiudadInicio)).getSelectedItem());
        String fin ="" ;//= (""+((Spinner) findViewById(R.id.spinnerCiudadFin)).getSelectedItem());

       if(comienzo.compareTo("Sin seleccionar")==1 && fin.compareTo("Sin seleccionar")==1)
        {
            Toast.makeText(this, "Debe seleccionar sólo una de dos, o ciudad inicio o ciudad final",
                    Toast.LENGTH_SHORT).show();
        }
        if(dias < 1)
        {
            Toast.makeText(this, "Debe introducir un número de dias mayor a 1",
                    Toast.LENGTH_SHORT).show();
        }
        if(nombre.compareTo("")==0)
        {
            Toast.makeText(this, "Debe introducir un nombre para el nuevo camino",
                    Toast.LENGTH_SHORT).show();
        }
        */
        //calculaEtapas(dias,comienzo, fin, nombre);
        Intent i = new Intent(NuevoCamino.this, CaminoActual.class);
        startActivity(i);

    }
    public void calculaEtapas(int dias,String comienzo, String fin, String nombre)
    {
        usuarioSeleccionado = (Usuario) getIntent().getSerializableExtra("usuarioSeleccionado");
        Double kmMax = usuarioSeleccionado.getKmMaximos();
        Pueblo aux;
        ArrayList<Etapa> etapas = new ArrayList<Etapa>();
        String iEtapa, fEtapa;
        Double auxKm=0.0;
        boolean semaforo=true;
        int i = 0;
        if(dias == 0){dias = 999999999;}//Cuando el usuario elige ciudad inicio y ciudad final no se calcula en función de los dias


        while(pueblosCaminoFrances[i].getNombre().compareTo(comienzo)==1)
        {
            i++;
        }


        //Mientras queden dias o no se alcance la ciudad final
        while(dias > 0 || pueblosCaminoFrances[i].getNombre().compareTo(fin)==1 )
        {
            iEtapa=pueblosCaminoFrances [i].getNombre();
            semaforo = true;
            while(semaforo)
            {
                if(pueblosCaminoFrances[i].getDist_siguiente()+auxKm<=kmMax)
                {
                    auxKm+=pueblosCaminoFrances[i].getDist_siguiente();
                    i++;
                }else
                {
                    semaforo=false;
                }

            }
            fEtapa = pueblosCaminoFrances[i].getNombre();
            etapas.add(new Etapa(auxKm,iEtapa,fEtapa));
            auxKm=0.0;
            dias--;

        }



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
