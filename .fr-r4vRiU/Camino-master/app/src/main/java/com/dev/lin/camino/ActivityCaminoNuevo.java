package com.dev.lin.camino;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Nuevo camino de la Ruta de Santiago a realizar
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class ActivityCaminoNuevo extends ActionBarActivity {
    private static final String CAMINO_NUEVO = "CaminoNuevo";

    private Usuario usuarioSeleccionado = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camino_nuevo);

        this.usuarioSeleccionado = (Usuario) getIntent().getSerializableExtra("usuarioSeleccionado");
        Log.d(ActivityCaminoNuevo.CAMINO_NUEVO, this.usuarioSeleccionado.toString());

        ArrayList<String> nombresListaParadas = new ArrayList<String>();
        Iterator<Parada> itr = GestionFicherosConfigs.listaParadasCaminoFrances.iterator();

        while (itr.hasNext()) {
            Parada parada = itr.next();

            nombresListaParadas.add(parada.getNombre());
        }

        ArrayAdapter adaptador = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,
                nombresListaParadas);
        Spinner spinnerInicio = (Spinner) findViewById(R.id.spinnerParadaInicio);
        spinnerInicio.setAdapter(adaptador);
    }

    public void menuPrincipal(View view) {
        Intent i = new Intent(ActivityCaminoNuevo.this, ActivityMenuPrincipal.class);
        i.putExtra("usuarioSeleccionado", this.usuarioSeleccionado);
        startActivity(i);
    }

    public void crearCaminoNuevo(View view) {
        String paradaComienzo = "";
        String nombreCamino = "";
        int numDias = 0;
        int distanciaDiaria = 0;
        boolean correcto = true;
        ArrayList<Etapa> listaEtapas = new ArrayList<Etapa>();
        Camino camino = null;

        if (((EditText) findViewById(R.id.editTextNumDias)).getText().toString().isEmpty() ||
                Integer.parseInt(((EditText) findViewById(R.id.editTextNumDias)).getText().toString()) < 2) {
            Toast.makeText(this, "El número de días para hacer el camino tiene que ser mayor que 1.",
                    Toast.LENGTH_SHORT).show();
            correcto = false;
        } else {
            numDias = Integer.parseInt(((EditText) findViewById(R.id.editTextNumDias)).getText().toString());
        }

        if ((((EditText) findViewById(R.id.editTextNombre)).getText()).toString().isEmpty()) {
            Toast.makeText(this, "Introduzca un nombre para su nuevo camino.", Toast.LENGTH_SHORT).show();
            correcto = false;
        } else {
            nombreCamino = (((EditText) findViewById(R.id.editTextNombre)).getText()).toString();
        }

        if ((((EditText) findViewById(R.id.editTextDistanciaMax)).getText()).toString().equals("")) {
            Toast.makeText(this, "Sin número de KMs diarios introducido. Distancia recomendada: "
                    + this.usuarioSeleccionado.getKmMaximos() + " km.", Toast.LENGTH_SHORT).show();
            correcto = false;

        } else {
            distanciaDiaria = Integer.parseInt((((EditText) findViewById(R.id.editTextDistanciaMax)).
                    getText()).toString());

            if (distanciaDiaria > this.usuarioSeleccionado.getKmMaximos()) {
                Toast.makeText(this, "Distancia diaria mayor que la recomendada. Distancia recomendada: "
                        + this.usuarioSeleccionado.getKmMaximos() + " km.", Toast.LENGTH_SHORT).show();
            }
        }

        if (correcto) {
            //Comprobar donde recibe usuario seleccionado y como construye etapas y caminos
            listaEtapas = crearEtapasCaminoNuevo(numDias, paradaComienzo, nombreCamino, distanciaDiaria);
            camino = new Camino(nombreCamino, listaEtapas);

            //Primera prueba estableciendo un único camino
            this.usuarioSeleccionado.addCamino(camino);
            GestionFicherosConfigs.escribirUsuarios(this.usuarioSeleccionado, getBaseContext());

            Intent i = new Intent(ActivityCaminoNuevo.this, ActivityMenuPrincipal.class);
            i.putExtra("usuarioSeleccionado", (Serializable) this.usuarioSeleccionado);
            startActivity(i);
        } else {
            Toast.makeText(this, "Introduzca todos los datos del formulario correctamente.",
                    Toast.LENGTH_SHORT).show();

            Intent i = new Intent(ActivityCaminoNuevo.this, ActivityCaminoNuevo.class);
            i.putExtra("usuarioSeleccionado", (Serializable) this.usuarioSeleccionado);
            startActivity(i);
        }
    }

    public void crearCaminoFrances(View view) {
        Camino camino = new Camino("Camino franćes de " + this.usuarioSeleccionado.getNombre(),
                this.crearEtapasCaminoFrances());
        this.usuarioSeleccionado.addCamino(camino);
        Toast.makeText(this, "Añadido el camino francés.", Toast.LENGTH_SHORT).show();

        GestionFicherosConfigs.escribirUsuarios(this.usuarioSeleccionado, getBaseContext());

        Intent i = new Intent(ActivityCaminoNuevo.this, ActivityMenuPrincipal.class);
        i.putExtra("usuarioSeleccionado", this.usuarioSeleccionado);
        startActivity(i);
    }

    public ArrayList<Etapa> crearEtapasCaminoNuevo(int dias, String paradaComienzo, String nombreCamino,
                                                   int kmMax) {
        ArrayList<Etapa> listaEtapas = new ArrayList<Etapa>();
        ArrayList<Integer> listaParadasEtapa = new ArrayList<Integer>();
        Double distancia = 0.0;
        int ordenParada = 1;
        int ordenEtapa = 1;
        boolean semaforo = true;
        boolean inicio = false;

        Iterator<Parada> itr = GestionFicherosConfigs.listaParadasCaminoFrances.iterator();
        Parada parada = null;

        // Nos situamos en el inicio del camino
        while (itr.hasNext() && !inicio) {
            parada = itr.next();

            if (parada.getNombre().equals(paradaComienzo)) {
                ordenParada = parada.getOrden();
                inicio = true;
            }
        }
        // Mientras queden dias o no se alcance la ciudad final
        while (dias > 0) {
            semaforo = true;
            while (semaforo) {
                if (GestionFicherosConfigs.listaParadasCaminoFrances.get(ordenParada).getDistSiguiente()
                        + distancia <= kmMax) {

                    listaParadasEtapa.add(ordenParada);
                    distancia += GestionFicherosConfigs.listaParadasCaminoFrances.get(ordenParada).
                            getDistSiguiente();

                    if (ordenParada == GestionFicherosConfigs.listaParadasCaminoFrances.size() - 1) {
                        dias = 0;
                        distancia = 9999.0;
                        ordenParada--;
                    } else {
                        ordenParada++;
                    }
                } else {
                    //Si en la parada final no hay sitio donde dormir hacemos backtracking hasta la
                    // parada mas cercana que sí tenga alojamientos.
                    while (!GestionFicherosConfigs.listaParadasCaminoFrances.get(listaParadasEtapa.
                            get(listaParadasEtapa.size() - 1)).getHotel() &&
                            !GestionFicherosConfigs.listaParadasCaminoFrances.get(listaParadasEtapa.
                                    get(listaParadasEtapa.size() - 1)).getAlbergue()) {
                        distancia -= GestionFicherosConfigs.listaParadasCaminoFrances.get(ordenParada).
                                getDistAnterior();
                        listaParadasEtapa.remove(listaParadasEtapa.size() - 1);
                        ordenParada--;
                    }
                    listaEtapas.add(new Etapa(ordenEtapa, listaParadasEtapa));
                    listaParadasEtapa.clear();
                    ordenEtapa++;
                    ordenParada--;
                    distancia = 0.0;
                    dias--;
                    semaforo = false;
                }
            }
        }
        return listaEtapas;
    }

    public ArrayList<Etapa> crearEtapasCaminoFrances() {
        ArrayList<Etapa> listaEtapas = new ArrayList<Etapa>();

        ArrayList<Integer> indiceParadasEtapa01 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4));
        Etapa etapa_01 = new Etapa(1, indiceParadasEtapa01);
        listaEtapas.add(etapa_01);

        ArrayList<Integer> indiceParadasEtapa02 = new ArrayList<Integer>(Arrays.asList(4, 5, 6, 7, 8, 9));
        Etapa etapa_02 = new Etapa(2, indiceParadasEtapa02);
        listaEtapas.add(etapa_02);

        ArrayList<Integer> indiceParadasEtapa03 = new ArrayList<Integer>(Arrays.asList(9, 10, 11, 12, 13, 14, 15,
                16));
        Etapa etapa_03 = new Etapa(3, indiceParadasEtapa03);
        listaEtapas.add(etapa_03);

        ArrayList<Integer> indiceParadasEtapa04 = new ArrayList<Integer>(Arrays.asList(16, 17, 18, 19, 20, 21, 22));
        Etapa etapa_04 = new Etapa(4, indiceParadasEtapa04);
        listaEtapas.add(etapa_04);

        ArrayList<Integer> indiceParadasEtapa05 = new ArrayList<Integer>(Arrays.asList(22, 23, 24, 25, 26));
        Etapa etapa_05 = new Etapa(5, indiceParadasEtapa05);
        listaEtapas.add(etapa_05);

        ArrayList<Integer> indiceParadasEtapa06 = new ArrayList<Integer>(Arrays.asList(26, 27, 28, 29, 30, 31, 32,
                33));
        Etapa etapa_06 = new Etapa(6, indiceParadasEtapa06);
        listaEtapas.add(etapa_06);

        ArrayList<Integer> indiceParadasEtapa07 = new ArrayList<Integer>(Arrays.asList(33, 34, 35, 36, 37));
        Etapa etapa_07 = new Etapa(7, indiceParadasEtapa07);
        listaEtapas.add(etapa_07);

        ArrayList<Integer> indiceParadasEtapa08 = new ArrayList<Integer>(Arrays.asList(37, 38, 39, 40));
        Etapa etapa_08 = new Etapa(8, indiceParadasEtapa08);
        listaEtapas.add(etapa_08);

        ArrayList<Integer> indiceParadasEtapa09 = new ArrayList<Integer>(Arrays.asList(40, 41, 42, 43));
        Etapa etapa_09 = new Etapa(9, indiceParadasEtapa09);
        listaEtapas.add(etapa_09);

        ArrayList<Integer> indiceParadasEtapa10 = new ArrayList<Integer>(Arrays.asList(43, 44, 45, 46, 47, 48, 49));
        Etapa etapa_10 = new Etapa(10, indiceParadasEtapa10);
        listaEtapas.add(etapa_10);

        ArrayList<Integer> indiceParadasEtapa11 = new ArrayList<Integer>(Arrays.asList(49, 50, 51, 52, 53, 54));
        Etapa etapa_11 = new Etapa(11, indiceParadasEtapa11);
        listaEtapas.add(etapa_11);

        ArrayList<Integer> indiceParadasEtapa12 = new ArrayList<Integer>(Arrays.asList(54, 55, 56, 57, 58, 59, 60));
        Etapa etapa_12 = new Etapa(12, indiceParadasEtapa12);
        listaEtapas.add(etapa_12);

        ArrayList<Integer> indiceParadasEtapa13 = new ArrayList<Integer>(Arrays.asList(60, 61, 62, 63));
        Etapa etapa_13 = new Etapa(13, indiceParadasEtapa13);
        listaEtapas.add(etapa_13);

        ArrayList<Integer> indiceParadasEtapa14 = new ArrayList<Integer>(Arrays.asList(63, 64, 65, 66, 67));
        Etapa etapa_14 = new Etapa(14, indiceParadasEtapa14);
        listaEtapas.add(etapa_14);

        ArrayList<Integer> indiceParadasEtapa15 = new ArrayList<Integer>(Arrays.asList(67, 68, 69, 70, 71));
        Etapa etapa_15 = new Etapa(15, indiceParadasEtapa15);
        listaEtapas.add(etapa_15);

        ArrayList<Integer> indiceParadasEtapa16 = new ArrayList<Integer>(Arrays.asList(71, 72, 73, 74, 75, 76));
        Etapa etapa_16 = new Etapa(16, indiceParadasEtapa16);
        listaEtapas.add(etapa_16);

        ArrayList<Integer> indiceParadasEtapa17 = new ArrayList<Integer>(Arrays.asList(76, 77, 78));
        Etapa etapa_17 = new Etapa(17, indiceParadasEtapa17);
        listaEtapas.add(etapa_17);

        ArrayList<Integer> indiceParadasEtapa18 = new ArrayList<Integer>(Arrays.asList(78, 79, 80, 81, 82));
        Etapa etapa_18 = new Etapa(18, indiceParadasEtapa18);
        listaEtapas.add(etapa_18);

        ArrayList<Integer> indiceParadasEtapa19 = new ArrayList<Integer>(Arrays.asList(82, 83, 84));
        Etapa etapa_19 = new Etapa(19, indiceParadasEtapa19);
        listaEtapas.add(etapa_19);

        ArrayList<Integer> indiceParadasEtapa20 = new ArrayList<Integer>(Arrays.asList(84, 85, 86));
        Etapa etapa_20 = new Etapa(20, indiceParadasEtapa20);
        listaEtapas.add(etapa_20);

        ArrayList<Integer> indiceParadasEtapa21 = new ArrayList<Integer>(Arrays.asList(86, 87, 88, 89, 90));
        Etapa etapa_21 = new Etapa(21, indiceParadasEtapa21);
        listaEtapas.add(etapa_21);

        ArrayList<Integer> indiceParadasEtapa22 = new ArrayList<Integer>(Arrays.asList(90, 91, 92, 93, 94, 95));
        Etapa etapa_22 = new Etapa(22, indiceParadasEtapa22);
        listaEtapas.add(etapa_22);

        ArrayList<Integer> indiceParadasEtapa23 = new ArrayList<Integer>(Arrays.asList(95, 96, 97, 98, 99, 100, 101));
        Etapa etapa_23 = new Etapa(23, indiceParadasEtapa23);
        listaEtapas.add(etapa_23);

        ArrayList<Integer> indiceParadasEtapa24 = new ArrayList<Integer>(Arrays.asList(101, 102, 103, 104, 105, 106));
        Etapa etapa_24 = new Etapa(24, indiceParadasEtapa24);
        listaEtapas.add(etapa_24);

        ArrayList<Integer> indiceParadasEtapa25 = new ArrayList<Integer>(Arrays.asList(106, 107, 108, 109, 110, 111,
                112, 113, 114));
        Etapa etapa_25 = new Etapa(25, indiceParadasEtapa25);
        listaEtapas.add(etapa_25);

        ArrayList<Integer> indiceParadasEtapa26 = new ArrayList<Integer>(Arrays.asList(114, 115, 116, 117, 118, 119,
                120, 121));
        Etapa etapa_26 = new Etapa(26, indiceParadasEtapa26);
        listaEtapas.add(etapa_26);

        ArrayList<Integer> indiceParadasEtapa27 = new ArrayList<Integer>(Arrays.asList(121, 122, 123, 124, 125, 126,
                127, 128, 129, 130, 131));
        Etapa etapa_27 = new Etapa(27, indiceParadasEtapa27);
        listaEtapas.add(etapa_27);

        ArrayList<Integer> indiceParadasEtapa28 = new ArrayList<Integer>(Arrays.asList(131, 132, 133, 134, 135, 136,
                137, 138, 139));
        Etapa etapa_28 = new Etapa(28, indiceParadasEtapa28);
        listaEtapas.add(etapa_28);

        ArrayList<Integer> indiceParadasEtapa29 = new ArrayList<Integer>(Arrays.asList(139, 140, 141, 142, 143, 144,
                145, 146, 147, 148));
        Etapa etapa_29 = new Etapa(29, indiceParadasEtapa29);
        listaEtapas.add(etapa_29);

        ArrayList<Integer> indiceParadasEtapa30 = new ArrayList<Integer>(Arrays.asList(148, 149, 150, 151, 152, 153,
                154, 155, 156));
        Etapa etapa_30 = new Etapa(30, indiceParadasEtapa30);
        listaEtapas.add(etapa_30);

        ArrayList<Integer> indiceParadasEtapa31 = new ArrayList<Integer>(Arrays.asList(156, 157, 158, 159, 160, 161,
                162, 163, 164, 165, 166));
        Etapa etapa_31 = new Etapa(31, indiceParadasEtapa31);
        listaEtapas.add(etapa_31);

        ArrayList<Integer> indiceParadasEtapa32 = new ArrayList<Integer>(Arrays.asList(166, 167, 168, 169, 170, 171,
                172, 173, 174, 175, 176, 177));
        Etapa etapa_32 = new Etapa(32, indiceParadasEtapa32);
        listaEtapas.add(etapa_32);

        ArrayList<Integer> indiceParadasEtapa33 = new ArrayList<Integer>(Arrays.asList(177, 178));
        Etapa etapa_33 = new Etapa(33, indiceParadasEtapa33);
        listaEtapas.add(etapa_33);

        return listaEtapas;
    }
}
