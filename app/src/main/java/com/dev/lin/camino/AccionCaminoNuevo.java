package com.dev.lin.camino;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
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
 * Datos de un nuevo camino
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class AccionCaminoNuevo extends ActionBarActivity {
    private static final String DATOS_USUARIO = "DatosUsuario";
    private static final String DATOS_PARADA = "DatosParada";
    private GestionFicheros archivador = new GestionFicheros();
    private Usuario usuarioSeleccionado = null;

    private ArrayList<Parada> listaParadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_camino);

        this.listaParadas = archivador.parseadorXMLcaminos(getBaseContext());

        usuarioSeleccionado = (Usuario) getIntent().getSerializableExtra("usuarioSeleccionado");
        Log.d(AccionCaminoNuevo.DATOS_USUARIO, usuarioSeleccionado.toString());

        ArrayList<String> nombresListaParadas = new ArrayList<String>();
        Iterator<Parada> itr = this.listaParadas.iterator();

        while (itr.hasNext()) {
            Parada parada = itr.next();

            nombresListaParadas.add(parada.getNombre());
        }

        ArrayAdapter adaptador = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, nombresListaParadas);
        Spinner spinnerInicio = (Spinner) findViewById(R.id.spinnerParadaInicio);
        //Spinner spinnerFin = (Spinner) findViewById(R.id.spinnerParadaFin);
        spinnerInicio.setAdapter(adaptador);
        //spinnerFin.setAdapter(adaptador);
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_accion_camino_nuevo, menu);
        return true;
    }
    */

    public void crearCaminoFrances(View view) {
        Camino camino = new Camino("Camino franćes de " + this.usuarioSeleccionado.getNombre(), this.crearEtapasCaminoFrances());
        this.usuarioSeleccionado.addCamino(camino);
        Toast.makeText(this, "Añadido el camino francés.", Toast.LENGTH_SHORT).show();

        archivador.escribirUsuarios(usuarioSeleccionado, getBaseContext());
        Intent i = new Intent(AccionCaminoNuevo.this, AccionCaminoActual.class);
        i.putExtra("usuarioSeleccionado", this.usuarioSeleccionado);
        startActivity(i);
    }

    public void menuPrincipal(View view) {
        Intent i = new Intent(AccionCaminoNuevo.this, AccionMenuPrincipal.class);
        i.putExtra("usuarioSeleccionado", this.usuarioSeleccionado);
        startActivity(i);
    }

    public void crearCaminoNuevo(View view) {

        String nombre = "";
        String comienzoCamino = "";
        int dias = 0;
        int kmDia = 0;
        boolean correcto = true;
        ArrayList<Etapa> etapas = new ArrayList<Etapa>();
        Camino camino = null;

        if ((((EditText) findViewById(R.id.editTextNombreCamino)).getText()).toString().equals("SIN SELECCIONAR")) {
            Toast.makeText(this, "Introduzca un nombre para su nuevo camino.", Toast.LENGTH_SHORT).show();
            correcto = false;
        } else {
            nombre = (((EditText) findViewById(R.id.editTextNombreCamino)).getText()).toString();
        }

        if ((((Spinner) findViewById(R.id.spinnerParadaInicio)).getSelectedItem()).toString().equals("")) {
            Toast.makeText(this, "Seleccione una parada de inicio para su camino.", Toast.LENGTH_SHORT).show();
            correcto = false;
        } else {
            comienzoCamino = (((Spinner) findViewById(R.id.spinnerParadaInicio)).getSelectedItem()).toString();
        }

        if (((EditText) findViewById(R.id.editTextDias)).getText().toString().equals("") || Integer.parseInt(((EditText) findViewById(R.id.editTextDias)).getText().toString()) < 2) {
            Toast.makeText(this, "El número de días para hacer el camino tiene que ser mayor que 1.", Toast.LENGTH_SHORT).show();
            correcto = false;
        } else {
            dias = Integer.parseInt(((EditText) findViewById(R.id.editTextDias)).getText().toString());
        }

        if ((((EditText) findViewById(R.id.editTextKmMax)).getText()).toString().equals("")) {
            Toast.makeText(this, "Sin número de KMs diarios introducido. Distancia recomendada: " + this.usuarioSeleccionado.getKmMaximos() + " km.", Toast.LENGTH_SHORT).show();
            correcto = false;

        } else {
            kmDia = Integer.parseInt((((EditText) findViewById(R.id.editTextKmMax)).getText()).toString());

            if (kmDia > usuarioSeleccionado.getKmMaximos()) {
                Toast.makeText(this, "Distancia diaria mayor que la recomendada. Distancia recomendada: " + this.usuarioSeleccionado.getKmMaximos() + " km.", Toast.LENGTH_SHORT).show();
            }
        }


        if (correcto) {
            //Comprobar donde recibe usuario seleccionado y como construye etapas y caminos
            etapas = crearEtapasCaminoNuevo(dias, comienzoCamino, nombre, kmDia);
            //etapas = pruebaETAPAS();
            camino = new Camino(nombre, etapas);

            //Primera prueba estableciendo un único camino
            this.usuarioSeleccionado.addCamino(camino);
            archivador.escribirUsuarios(usuarioSeleccionado, getBaseContext());
            Intent i = new Intent(AccionCaminoNuevo.this, AccionCaminoActual.class);
            i.putExtra("usuarioSeleccionado", (Serializable) this.usuarioSeleccionado);
            startActivity(i);
        } else {
            Toast.makeText(this, "Introduzca todos los datos del formulario correctamente.", Toast.LENGTH_SHORT).show();
            //archivador.escribirUsuarios(usuarioSeleccionado, getBaseContext());

            Intent i = new Intent(AccionCaminoNuevo.this, AccionCaminoNuevo.class);
            i.putExtra("usuarioSeleccionado", (Serializable) this.usuarioSeleccionado);
            startActivity(i);
        }
    }

    ///////////////////Prueba para ver las distancias entre paradas////////////////
    public ArrayList<Etapa> pruebaETAPAS() {
        ArrayList<Etapa> etapas = new ArrayList<Etapa>();
        ArrayList<Parada> par;

        for (int i = 0; i < listaParadas.size() - 1; i++) {
            par = new ArrayList<Parada>();
            par.add(listaParadas.get(i));
            par.add(listaParadas.get(i + 1));

            etapas.add(new Etapa(i, par));
        }

        return etapas;
    }
    ////////////////////////////////////////////////////////////////////////////////

    public ArrayList<Etapa> crearEtapasCaminoNuevo(int dias, String comienzoCamino, String nombreCamino, int kmMax) {
        this.usuarioSeleccionado = (Usuario) getIntent().getSerializableExtra("usuarioSeleccionado");
        String inicioEtapa, finEtapa;
        ArrayList<Etapa> listaEtapas = new ArrayList<Etapa>();
        ArrayList<Parada> paradasEtapa = new ArrayList<Parada>();
        Double km = 0.0;
        boolean semaforo = true;
        int ordenParada = 1;
        int ordenEtapa = 1;
        boolean comp = false;
        Iterator<Parada> itr = this.listaParadas.iterator();

        // Llevamos el iterador de la lista de paradas hasta la inicial del camino
        while (itr.hasNext() && !comp) {
            Parada parada = itr.next();

            comp = (parada.getNombre()).equals(comienzoCamino);

            if (comp) {
                ordenParada = parada.getOrden();
            }
        }

        //Mientras queden dias o no se alcance la ciudad final
        while (dias > 0) {
            semaforo = true;
            paradasEtapa.clear();

            while (semaforo) {
                if (this.listaParadas.get(ordenParada).getDistSiguiente() + km <= kmMax) {
                    paradasEtapa.add(this.listaParadas.get(ordenParada));
                    km += this.listaParadas.get(ordenParada).getDistSiguiente();

                    if (this.listaParadas.get(ordenParada).equals("Santiago de Compostela")) {
                        Toast.makeText(this, "" + ordenParada, Toast.LENGTH_SHORT).show();
                        km = 999999.99;
                        dias = 0;
                    } else {
                        ordenParada++;
                    }

                } else {

                   /*/Si en la parada final no hay sitio donde dormir hacemos backtracking hasta la parada mas cercana que sí tenga alojamientos.
                    while(!paradasEtapa.get(paradasEtapa.size()-1).getHotel() && !paradasEtapa.get(paradasEtapa.size()-1).getAlbergue())
                    {
                        Toast.makeText(this, "quitando etapa: "+paradasEtapa.get(paradasEtapa.size()-1), Toast.LENGTH_SHORT).show();
                        km-= this.listaParadas.get(ordenParada).getDistAnterior();
                        paradasEtapa.remove(paradasEtapa.size()-1);
                        ordenParada--;
                    }
                    */
                    listaEtapas.add(new Etapa(ordenEtapa, paradasEtapa));
                    ordenEtapa++;
                    km = 0.0;
                    dias--;
                    semaforo = false;
                }


            }
        }

        return listaEtapas;
    }

    public ArrayList<Etapa> crearEtapasCaminoFrances() {
        ArrayList<Etapa> listaEtapas = new ArrayList<Etapa>();

        listaEtapas.add(new Etapa(1, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(1),
                this.listaParadas.get(2),
                this.listaParadas.get(3),
                this.listaParadas.get(4)
        ))));

        listaEtapas.add(new Etapa(2, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(4),
                this.listaParadas.get(5),
                this.listaParadas.get(6),
                this.listaParadas.get(7),
                this.listaParadas.get(8),
                this.listaParadas.get(9)
        ))));

        listaEtapas.add(new Etapa(3, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(9),
                this.listaParadas.get(10),
                this.listaParadas.get(11),
                this.listaParadas.get(12),
                this.listaParadas.get(13),
                this.listaParadas.get(14),
                this.listaParadas.get(15),
                this.listaParadas.get(16)
        ))));

        listaEtapas.add(new Etapa(4, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(16),
                this.listaParadas.get(17),
                this.listaParadas.get(18),
                this.listaParadas.get(19),
                this.listaParadas.get(20),
                this.listaParadas.get(21),
                this.listaParadas.get(22)
        ))));

        listaEtapas.add(new Etapa(5, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(22),
                this.listaParadas.get(23),
                this.listaParadas.get(24),
                this.listaParadas.get(25),
                this.listaParadas.get(26)
        ))));

        listaEtapas.add(new Etapa(6, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(26),
                this.listaParadas.get(28),
                this.listaParadas.get(29),
                this.listaParadas.get(30),
                this.listaParadas.get(31),
                this.listaParadas.get(32),
                this.listaParadas.get(33)
        ))));

        listaEtapas.add(new Etapa(7, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(33),
                this.listaParadas.get(34),
                this.listaParadas.get(35),
                this.listaParadas.get(36),
                this.listaParadas.get(37)
        ))));

        listaEtapas.add(new Etapa(8, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(37),
                this.listaParadas.get(38),
                this.listaParadas.get(39),
                this.listaParadas.get(40)
        ))));

        listaEtapas.add(new Etapa(9, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(40),
                this.listaParadas.get(41),
                this.listaParadas.get(42),
                this.listaParadas.get(43)
        ))));

        listaEtapas.add(new Etapa(10, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(43),
                this.listaParadas.get(44),
                this.listaParadas.get(45),
                this.listaParadas.get(46),
                this.listaParadas.get(47),
                this.listaParadas.get(48),
                this.listaParadas.get(49)
        ))));

        listaEtapas.add(new Etapa(11, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(49),
                this.listaParadas.get(50),
                this.listaParadas.get(51),
                this.listaParadas.get(52),
                this.listaParadas.get(53),
                this.listaParadas.get(54)
        ))));

        listaEtapas.add(new Etapa(12, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(54),
                this.listaParadas.get(56),
                this.listaParadas.get(57),
                this.listaParadas.get(58),
                this.listaParadas.get(59),
                this.listaParadas.get(60)
        ))));

        listaEtapas.add(new Etapa(13, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(60),
                this.listaParadas.get(61),
                this.listaParadas.get(62),
                this.listaParadas.get(63)
        ))));

        listaEtapas.add(new Etapa(14, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(63),
                this.listaParadas.get(64),
                this.listaParadas.get(65),
                this.listaParadas.get(66),
                this.listaParadas.get(67)
        ))));

        listaEtapas.add(new Etapa(15, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(67),
                this.listaParadas.get(68),
                this.listaParadas.get(69),
                this.listaParadas.get(70),
                this.listaParadas.get(71)
        ))));

        listaEtapas.add(new Etapa(16, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(71),
                this.listaParadas.get(72),
                this.listaParadas.get(73),
                this.listaParadas.get(74),
                this.listaParadas.get(75),
                this.listaParadas.get(76)
        ))));

        listaEtapas.add(new Etapa(17, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(76),
                this.listaParadas.get(77),
                this.listaParadas.get(78)
        ))));

        listaEtapas.add(new Etapa(18, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(78),
                this.listaParadas.get(79),
                this.listaParadas.get(80),
                this.listaParadas.get(81),
                this.listaParadas.get(82)
        ))));

        listaEtapas.add(new Etapa(19, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(82),
                this.listaParadas.get(83),
                this.listaParadas.get(84)
        ))));

        listaEtapas.add(new Etapa(20, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(84),
                this.listaParadas.get(85),
                this.listaParadas.get(86)
        ))));

        listaEtapas.add(new Etapa(21, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(86),
                this.listaParadas.get(87),
                this.listaParadas.get(88),
                this.listaParadas.get(89),
                this.listaParadas.get(90)
        ))));

        listaEtapas.add(new Etapa(22, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(90),
                this.listaParadas.get(91),
                this.listaParadas.get(92),
                this.listaParadas.get(93),
                this.listaParadas.get(94),
                this.listaParadas.get(95)
        ))));

        listaEtapas.add(new Etapa(23, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(95),
                this.listaParadas.get(96),
                this.listaParadas.get(97),
                this.listaParadas.get(98),
                this.listaParadas.get(99),
                this.listaParadas.get(100),
                this.listaParadas.get(101)
        ))));

        listaEtapas.add(new Etapa(24, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(101),
                this.listaParadas.get(102),
                this.listaParadas.get(103),
                this.listaParadas.get(104),
                this.listaParadas.get(105),
                this.listaParadas.get(106)
        ))));

        listaEtapas.add(new Etapa(25, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(106),
                this.listaParadas.get(107),
                this.listaParadas.get(108),
                this.listaParadas.get(109),
                this.listaParadas.get(110),
                this.listaParadas.get(111),
                this.listaParadas.get(112),
                this.listaParadas.get(113),
                this.listaParadas.get(114)
        ))));

        listaEtapas.add(new Etapa(26, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(114),
                this.listaParadas.get(115),
                this.listaParadas.get(116),
                this.listaParadas.get(117),
                this.listaParadas.get(118),
                this.listaParadas.get(119),
                this.listaParadas.get(120),
                this.listaParadas.get(121)
        ))));

        listaEtapas.add(new Etapa(27, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(121),
                this.listaParadas.get(122),
                this.listaParadas.get(123),
                this.listaParadas.get(124),
                this.listaParadas.get(125),
                this.listaParadas.get(126),
                this.listaParadas.get(127),
                this.listaParadas.get(128),
                this.listaParadas.get(129),
                this.listaParadas.get(130),
                this.listaParadas.get(131)
        ))));

        listaEtapas.add(new Etapa(28, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(131),
                this.listaParadas.get(132),
                this.listaParadas.get(133),
                this.listaParadas.get(134),
                this.listaParadas.get(135),
                this.listaParadas.get(136),
                this.listaParadas.get(137),
                this.listaParadas.get(138),
                this.listaParadas.get(139)
        ))));

        listaEtapas.add(new Etapa(29, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(139),
                this.listaParadas.get(140),
                this.listaParadas.get(141),
                this.listaParadas.get(142),
                this.listaParadas.get(143),
                this.listaParadas.get(144),
                this.listaParadas.get(145),
                this.listaParadas.get(146),
                this.listaParadas.get(147),
                this.listaParadas.get(148)
        ))));

        listaEtapas.add(new Etapa(30, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(148),
                this.listaParadas.get(149),
                this.listaParadas.get(150),
                this.listaParadas.get(151),
                this.listaParadas.get(152),
                this.listaParadas.get(153),
                this.listaParadas.get(154),
                this.listaParadas.get(155),
                this.listaParadas.get(156)
        ))));

        listaEtapas.add(new Etapa(31, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(156),
                this.listaParadas.get(157),
                this.listaParadas.get(158),
                this.listaParadas.get(159),
                this.listaParadas.get(160),
                this.listaParadas.get(161),
                this.listaParadas.get(162),
                this.listaParadas.get(163),
                this.listaParadas.get(164),
                this.listaParadas.get(165),
                this.listaParadas.get(166)
        ))));

        listaEtapas.add(new Etapa(32, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(166),
                this.listaParadas.get(167),
                this.listaParadas.get(168),
                this.listaParadas.get(169),
                this.listaParadas.get(170),
                this.listaParadas.get(171),
                this.listaParadas.get(172),
                this.listaParadas.get(173),
                this.listaParadas.get(174),
                this.listaParadas.get(175),
                this.listaParadas.get(176),
                this.listaParadas.get(177)
        ))));

        listaEtapas.add(new Etapa(33, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(177),
                this.listaParadas.get(178)
        ))));

        return listaEtapas;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify caminoFrances.xml parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
