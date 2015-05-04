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

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
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

    private ArrayList<Parada> listaParadas = new ArrayList<Parada>(Arrays.asList(
            new Parada(0, "SIN SELECCIONAR", 37.197003, -3.624251, 0, 0, false, false, false, false, false, false),
            new Parada(1, "Saint-Jean-Pied-de-Port", 43.163559, -1.235662, 0, 5.7, true, true, true, true, true, true),
            new Parada(2, "Huntto", 43.124447, -1.24476, 5.7, 2.7, true, true, true, true, true, false),
            new Parada(3, "Orisson", 43.10899321682584, -1.239052481080165, 2.7, 19.9, true, true, true, false, false, false),
            new Parada(4, "Orreaga/Roncesvalles", 43.00954730092566, -1.319763812529985, 19.9, 2.6, true, true, true, true, true, true),
            new Parada(5, "Auritz/Burguete", 42.990768, -1.33473, 2.6, 3.8, true, true, false, false, false, false),
            new Parada(6, "Aurizberri/Espinal", 42.981289, -1.37163, 3.8, 6.0, true, true, true, false, false, false),
            new Parada(7, "Bizkarreta-Gerendiain", 42.96769, -1.41792, 6.0, 2.32, true, true, false, false, false, false),
            new Parada(8, "Lintzoain", 42.96286, -1.4377, 2.32, 1.80, true, true, false, false, false, false),
            new Parada(9, "Zubiri", 42.93036118544159, -1.50466604700992, 1.80, 6.44, true, true, true, false, false, false),
            new Parada(10, "Larrasoaña", 42.900879, -1.5404, 6.44, 4.36, true, true, true, false, false, false),
            new Parada(11, "Zuriain", 42.878681, -1.56608, 4.36, 3.58, false, false, false, false, false, false),
            new Parada(12, "Zabaldika", 42.85474, -1.58113, 3.58, 3.86, false, false, true, false, false, false),
            new Parada(13, "Santísima Trinidad de Arre", 42.836189, -1.60428, 3.86, 1.0, false, false, true, false, false, false),
            new Parada(14, "Villava", 42.829681, -1.61037, 1.0, 0.987, true, true, true, false, false, false),
            new Parada(15, "Burlata", 42.824631, -1.61765, 0.987, 3.42, false, false, false, false, false, false),
            new Parada(16, "Pamplona", 42.818851, -1.64269, 3.42, 5.96, true, true, true, true, true, true),
            new Parada(17, "Cizur Menor", 42.786346, -1.677024, 5.96, 7.01, true, false, true, false, false, false),
            new Parada(18, "Zariquiegui", 42.74791, -1.723008, 7.01, 6.4, true, false, true, false, false, false),
            new Parada(19, "Uterga", 42.709103, -1.76101, 6.4, 2.55, true, true, true, false, false, false),
            new Parada(20, "Muruzábal", 42.68969, -1.771202, 2.55, 2.10, false, false, false, false, false, false),
            new Parada(21, "Obanos", 42.680275, -1.785729, 2.10, 3.09, true, true, true, false, false, false),
            new Parada(22, "Puente la Reina", 42.673096, -1.810405, 3.09, 6.64, true, true, true, true, true, true),
            new Parada(23, "Mañeru", 42.669022, -1.86209, 6.64, 2.7, false, false, true, false, false, false),
            new Parada(24, "Cirauqui", 42.675789, -1.89142, 2.7, 13.5, false, false, true, false, false, false),
            new Parada(25, "Villatuerta", 42.659447, -1.992731, 13.5, 4.69, true, true, true, false, false, false),
            new Parada(26, "Estella", 42.66983, -2.027471, 4.69, 1.02, true, true, true, true, true, true),
            new Parada(27, "Lorca", 42.663738, -2.032793, 1.02, 0.968, false, false, true, false, false, false),
            new Parada(28, "Ayegui", 42.657784, -2.038736, 0.968, 1.0, false, false, true, false, false, false),
            new Parada(29, "Monasterio de Irache", 42.650002, -2.044283, 1.0, 1.67, false, false, false, false, false, false),
            new Parada(30, "Irache", 42.646317, -2.056932, 1.67, 4.09, true, true, false, false, false, false),
            new Parada(31, "Azqueta", 42.635578, -2.087016, 4.09, 2.0, false, false, false, false, false, false),
            new Parada(32, "Villamayor de Monjardín", 42.629475, -2.105427, 2.0, 15.3, true, true, true, false, false, false),
            new Parada(33, "Los Arcos", 42.568855, -2.193596, 15.3, 8.77, true, true, true, true, true, true),
            new Parada(34, "Sansol", 42.553703, -2.265909, 8.77, 1.20, true, true, true, false, false, false),
            new Parada(35, "Torres del Río", 42.552006, -2.271295, 1.20, 13.1, true, true, true, false, false, false),
            new Parada(36, "Viana", 42.51554261904568, -2.37181831350982, 13.1, 11.3, true, true, true, false, false, false),
            new Parada(37, "Logroño", 42.46837095429641, -2.444684460353869, 11.3, 12.2, true, true, true, true, true, true),
            new Parada(38, "Navarrete", 42.429539, -2.56221, 12.2, 7.0, true, true, true, true, true, false),
            new Parada(39, "Ventosa", 42.411743, -2.627728, 7.0, 9.46, true, true, true, false, false, false),
            new Parada(40, "Nájera", 42.416634, -2.735177, 9.46, 7.58, true, true, true, true, true, true),
            new Parada(41, "Azofra", 42.424026, -2.800612, 7.58, 11.7, true, true, true, false, false, false),
            new Parada(42, "Cirueña", 42.412827, -2.896335, 11.7, 5.9, true, true, true, false, false, false),
            new Parada(43, "Santo Domingo de la Calzada", 42.440861, -2.952554, 5.9, 9.12, true, true, true, true, true, true),
            new Parada(44, "Grañón", 42.450569, -3.026948, 9.12, 4.63, true, true, true, false, false, false),
            new Parada(45, "Redecilla del Camino", 42.438267, -3.064091, 4.63, 2.24, true, true, true, false, false, false),
            new Parada(46, "Castildelgado", 42.436775, -3.083575000000001, 2.24, 2.52, true, true, false, false, false, false),
            new Parada(47, "Viloria de Rioja", 42.426086, -3.101664, 2.52, 4.22, true, true, true, false, false, false),
            new Parada(48, "Villamayor del Rio", 42.427448, -3.136146, 4.22, 6.25, false, false, true, false, false, false),
            new Parada(49, "Belorado", 42.420841, -3.188653, 6.25, 6.44, true, true, true, true, true, true),
            new Parada(50, "Tosantos", 42.413208, -3.243971, 6.44, 2.25, false, false, true, false, false, false),
            new Parada(51, "Villambistia", 42.40451, -3.261695, 2.25, 2.18, false, false, true, false, false, false),
            new Parada(52, "Espinosa del Camino", 42.405842, -3.280984999999999, 2.18, 4.06, false, false, true, false, false, false),
            new Parada(53, "Villafranca Montes de Oca", 42.390217, -3.308172, 4.06, 15.5, true, true, true, true, true, false),
            new Parada(54, "San Juan de Ortega", 42.375759, -3.436747, 15.5, 4.87, true, true, true, false, false, false),
            new Parada(55, "Agés", 42.369926, -3.478975, 4.87, 3.36, false, false, true, false, false, false),
            new Parada(56, "Atapuerca", 42.376682, -3.507922000000001, 3.36, 7.56, true, true, true, false, false, false),
            new Parada(57, "Cardeñuela Riopico", 42.367485, -3.571179, 7.56, 1.96, true, false, true, false, false, false),
            new Parada(58, "Orbaneja Riopico", 42.360191, -3.584225, 1.96, 3.99, true, true, false, false, false, false),
            new Parada(59, "Villafría de Burgos", 42.364311, -3.618218999999999, 3.99, 10.5, true, true, false, false, false, false),
            new Parada(60, "Burgos", 42.340927, -3.704876, 10.5, 8.0, true, true, false, false, false, false),
            new Parada(61, "Tardajos", 42.348377, -3.818028, 8.0, 2.49, true, true, true, false, false, false),
            new Parada(62, "Rabé de las Calzadas", 42.34026, -3.834314, 2.49, 10.4, false, false, true, false, false, false),
            new Parada(63, "Hornillos del Camino", 42.338593, -3.926411, 10.4, 7.47, true, true, true, false, false, false),
            new Parada(64, "San Bol", 42.326096, -3.990548, 7.47, 6.36, false, false, true, false, false, false),
            new Parada(65, "Hontanas", 42.312737, -4.045115, 6.36, 5.4, true, true, true, false, false, false),
            new Parada(66, "San Antón", 42.292816, -4.099081, 5.4, 4.52, false, false, true, false, false, false),
            new Parada(67, "Castrojeriz", 42.288216, -4.137597, 4.52, 8.94, true, true, true, true, true, true),
            new Parada(68, "Ermita de San Nicolás", 42.280941, -4.23919, 8.94, 2.76, false, false, true, false, false, false),
            new Parada(69, "Itero de la Vega", 42.287369, -4.25801, 2.76, 8.1, true, true, true, false, false, false),
            new Parada(70, "Boadilla del Camino", 42.260681, -4.34762, 8.1, 5.7, true, true, true, true, false, false),
            new Parada(71, "Frómista", 42.26604401852833, -4.406116165294307, 5.7, 4.48, true, true, true, true, true, false),
            new Parada(72, "Población de Campos", 42.26933083041678, -4.445625041295429, 4.48, 4.1, true, true, false, false, false, false),
            new Parada(73, "Revenga de Campos", 42.28823829512323, -4.475330946389946, 4.1, 3.25, false, false, false, false, false, false),
            new Parada(74, "Villarmentero de Campo", 42.29782534835481, -4.499479221331612, 3.25, 5.32, true, true, true, false, false, false),
            new Parada(75, "Villalcázar de Sirga", 42.31647637100176, -4.542616509294902, 5.32, 7.05, true, true, true, false, false, false),
            new Parada(76, "Carrión de los Condes", 42.33701793271186, -4.600910972351841, 7.05, 23.3, true, true, true, true, true, true),
            new Parada(77, "Calzadilla de la Cueza", 42.32928819482914, -4.805365948789728, 23.3, 7.45, true, true, true, false, false, false),
            new Parada(78, "Ledigos", 42.35346892266805, -4.865210560882744, 7.45, 3.63, false, false, true, false, false, false),
            new Parada(79, "Terradillos de los Templarios", 42.36243686904546, -4.891521292416499, 3.63, 4.10, true, false, true, false, false, false),
            new Parada(80, "Moratinos", 42.36065913738717, -4.926749189861326, 4.10, 3.24, true, true, true, false, false, false),
            new Parada(81, "San Nicolás del Real Camino", 42.36360024206139, -4.952028362233747, 3.24, 8.64, false, false, true, false, false, false),
            new Parada(82, "Sahagún", 42.3710019504643, -5.027328259769433, 8.64, 13.8, true, true, true, true, true, true),
            new Parada(83, "Bercianos del Real Camino", 42.38608608161385, -5.14566965408126, 13.8, 9.46, true, true, true, false, false, false),
            new Parada(84, "El Burgo Ranero", 42.4225217635454, -5.220259237441312, 9.46, 16.4, true, true, true, false, false, false),
            new Parada(85, "Reliegos", 42.47494326901281, -5.354935434308388, 16.4, 7.52, true, false, true, false, false, false),
            new Parada(86, "Mansilla de las Mulas", 42.49896383277923, -5.417510831775999, 7.52, 5.05, true, true, true, true, true, true),
            new Parada(87, "Villamoros de Mansilla", 42.53480476877282, -5.444472955200865, 5.05, 2.19, false, false, false, false, false, false),
            new Parada(88, "Puente de Villarente", 42.54574418415028, -5.459394421067614, 2.19, 4.80, true, true, true, false, false, false),
            new Parada(89, "Arcahueja", 42.56717952366034, -5.495987313777235, 4.80, 10.1, true, true, true, false, false, false),
            new Parada(90, "León", 42.59896683878336, -5.567367603479269, 10.1, 5.05, true, true, true, true, true, true),
            new Parada(91, "Trobajo del Camino", 42.59608795248055, -5.608595773586231, 5.05, 4.15, true, true, false, false, false, false),
            new Parada(92, "La Virgen del Camino", 42.5801565275495, -5.640897997782503, 4.15, 5.12, true, true, true, true, true, true),
            new Parada(93, "Valverde de la Virgen", 42.568834799466, -5.683178067759386, 5.12, 1.80, false, false, false, false, false, false),
            new Parada(94, "San Miguel del Camino", 42.56154138012829, -5.697586793589396, 1.80, 8.94, false, false, false, false, false, false),
            new Parada(95, "Villadangos del Páramo", 42.51908399190783, -5.765223328138703, 8.94, 5.65, true, true, true, false, false, false),
            new Parada(96, "San Martín del Camino", 42.494899739285, -5.80891904903929, 5.65, 9.13, true, false, true, false, false, false),
            new Parada(97, "Hospital de Órbigo", 42.46361619177748, -5.881946054204477, 9.13, 2.0, true, true, true, true, true, true),
            new Parada(98, "Villares de Órbigo", 42.45788317485298, -5.898403714172119, 2.0, 4.11, true, false, true, false, false, false),
            new Parada(99, "Santibáñez de Valdeiglesias", 42.45836315324036, -5.930283589071948, 4.11, 10.2, true, false, true, false, false, false),
            new Parada(100, "San Justo de la Vega", 42.45438775508052, -6.015809159902432, 10.2, 4.65, true, false, true, false, false, false),
            new Parada(101, "Astorga", 42.45547070692994, -6.053048477683262, 4.65, 3.33, true, true, true, true, true, true),
            new Parada(102, "Valdeviejas", 42.4593561692688, -6.081113398148332, 3.33, 2.98, false, false, true, false, false, false),
            new Parada(103, "Murias de Rechivaldo", 42.45925660109395, -6.107220848233881, 2.98, 5.94, true, true, true, false, false, false),
            new Parada(104, "Santa Catalina de Somoza", 42.4554552388274, -6.160104738033595, 5.94, 5.58, true, true, true, false, false, false),
            new Parada(105, "El Ganso", 42.46268946370218, -6.209066967202332, 5.58, 8.82, true, true, true, false, false, false),
            new Parada(106, "Rabanal del Camino", 42.48234063113036, -6.284265346272703, 8.82, 7.17, true, true, true, true, true, true),
            new Parada(107, "Foncebadón", 42.49143966226282, -6.342934205298832, 7.17, 2.47, true, false, true, false, false, false),
            new Parada(108, "Cruz de Ferro", 42.48875316775605, -6.361711286773868, 2.47, 2.97, false, false, false, false, false, false),
            new Parada(109, "Manjarín", 42.49018995221314, -6.386692402286143, 2.97, 8.72, false, false, true, false, false, false),
            new Parada(110, "El Acebo", 42.49887821380196, -6.457001077360444, 8.72, 3.92, true, true, true, false, false, false),
            new Parada(111, "Riego de Ambrós", 42.52172095711743, -6.479373488698768, 3.92, 5.95, true, true, true, false, false, false),
            new Parada(112, "Molinaseca", 42.53860829170707, -6.522440045703348, 5.95, 5.09, true, true, true, true, true, true),
            new Parada(113, "Campo", 42.53853309827439, -6.56431941344519, 5.09, 4.02, false, false, false, false, false, false),
            new Parada(114, "Ponferrada", 42.54344921381514, -6.593135292241726, 4.02, 6.01, true, true, true, true, true, true),
            new Parada(115, "Columbrianos", 42.57348935824068, -6.611247476744794, 6.01, 3.70, true, true, false, false, false, false),
            new Parada(116, "Fuentesnuevas", 42.57737168093492, -6.643172530797116, 3.70, 2.80, true, false, true, false, false, false),
            new Parada(117, "Camponaraya", 42.57896585884718, -6.667447841875158, 2.80, 6.79, false, false, false, false, false, false),
            new Parada(118, "Cacabelos", 42.5997817107809, -6.722804536771322, 6.79, 3.10, true, true, true, true, true, true),
            new Parada(119, "Pieros", 42.60567601165889, -6.749549276361094, 3.10, 1.78, true, false, true, false, false, false),
            new Parada(120, "Valtuille de Arriba", 42.60427497155767, -6.765248315962836, 1.78, 4.93, false, false, false, false, false, false),
            new Parada(121, "Villafranca del Bierzo", 42.60423226035614, -6.807388524279247, 4.93, 5.53, true, true, true, true, true, true),
            new Parada(122, "Pereje", 42.631796938234, -6.836967591340169, 5.53, 7.93, true, false, true, false, false, false),
            new Parada(123, "Trabadelo", 42.64956395617789, -6.881846368192599, 7.93, 5.08, true, true, true, true, true, true),
            new Parada(124, "La Portela de Valcarce", 42.66006915112168, -6.920651078795018, 5.08, 1.30, true, true, true, false, false, false),
            new Parada(125, "Ambasmestas", 42.66586330630172, -6.928323772229049, 1.30, 2.15, true, true, true, false, false, false),
            new Parada(126, "Vega de Valcarce", 42.6654104342722, -6.946317443830991, 2.15, 2.89, true, true, true, true, true, true),
            new Parada(127, "Ruitelán", 42.67183484377121, -6.968815215784345, 2.89, 1.71, true, false, true, false, false, false),
            new Parada(128, "Las Herrerías", 42.67095737774029, -6.982753547756147, 1.71, 4.86, true, true, true, false, false, false),
            new Parada(129, "La Faba", 42.68550511542193, -7.010181343582032, 4.86, 2.60, true, false, true, false, false, false),
            new Parada(130, "La Laguna", 42.70159526716245, -7.022385249871164, 2.60, 2.89, true, false, true, false, false, false),
            new Parada(131, "O Cebreiro", 42.70774872184296, -7.044702455821906, 2.89, 4.91, true, true, true, false, false, false),
            new Parada(132, "Liñares", 42.69833140250739, -7.077663552504576, 4.91, 2.62, true, true, false, false, false, false),
            new Parada(133, "Hospital da Condesa", 42.70461022667205, -7.099454108076372, 2.62, 3.55, false, false, true, false, false, false),
            new Parada(134, "Alto do Poio", 42.71278400049732, -7.125619692234523, 3.55, 4.33, true, true, true, false, false, false),
            new Parada(135, "Fonfría do Camiño", 42.73196564138166, -7.158311769221725, 4.33, 2.76, true, true, true, false, false, false),
            new Parada(136, "O Biduedo ", 42.74332267202118, -7.178037704809229, 2.76, 8.15, true, true, false, false, false, false),
            new Parada(137, "Triacastela", 42.75598351002881, -7.239805869607096, 8.15, 6.89, true, true, true, true, true, true),
            new Parada(138, "Renche", 42.73989052976592, -7.290904414783981, 6.89, 5.0, false, false, false, false, false, false),
            new Parada(139, "Samos", 42.73054712527786, -7.326522486809409, 5.0, 2.17, true, false, true, true, false, false),
            new Parada(140, "Teiguín", 42.72466772785302, -7.342060889947618, 2.17, 13.5, true, false, true, false, false, false),
            new Parada(141, "Sarria", 42.77713141278561, -7.413519715127549, 13.5, 4.03, true, true, true, true, true, true),
            new Parada(142, "Barbadelo", 42.76928465282261, -7.444279331404772, 4.03, 9.7, true, true, true, false, false, false),
            new Parada(143, "A Brea", 42.77962241404003, -7.515884043803027, 9.7, 1.95, false, false, false, false, false, false),
            new Parada(144, "Ferreiros", 42.78329278372696, -7.529116390630719, 1.95, 2.47, true, false, true, false, false, false),
            new Parada(145, "As Rozas", 42.78400404072978, -7.549803125409226, 2.47, 2.61, false, false, false, false, false, false),
            new Parada(146, "Mercadoiro", 42.78946837912826, -7.570525976926674, 2.61, 3.93, true, false, true, false, false, false),
            new Parada(147, "Vilachá", 42.79536377835125, -7.603606985671405, 3.93, 2.54, true, false, true, false, false, false),
            new Parada(148, "Portomarín", 42.80572413866636, -7.618897235307927, 2.54, 5.33, true, true, true, true, true, true),
            new Parada(149, "Toxibó", 42.81283566700174, -7.66403628372035, 5.33, 3.99, false, false, false, false, false, false),
            new Parada(150, "Gonzar", 42.82614870570648, -7.695667041793499, 3.99, 1.66, true, false, true, false, false, false),
            new Parada(151, "Castromaior", 42.83164641237603, -7.708906139951253, 1.66, 3.12, true, true, false, false, false, false),
            new Parada(152, "Hospital de la Cruz", 42.84111520107175, -7.734540537421708, 3.12, 1.91, true, true, true, false, false, false),
            new Parada(153, "Ventas de Narón", 42.84427066894147, -7.748648840437681, 1.91, 4.26, true, false, true, false, false, false),
            new Parada(154, "Ligonde", 42.86035480365461, -7.781590998536713, 4.26, 4.27, true, true, true, false, false, false),
            new Parada(155, "Lestedo", 42.87221882899041, -7.814220485892847, 4.27, 6.40, true, true, true, false, false, false),
            new Parada(156, "Palas de Rei", 42.87322485592507, -7.86899634128359, 6.40, 4.85, true, true, true, true, true, true),
            new Parada(157, "San Xulián do Camiño", 42.87598622479732, -7.909112191324297, 4.85, 0.680, true, false, true, false, false, false),
            new Parada(158, "A Ponte Campaña", 42.87838096185967, -7.914432776657094, 0.680, 1.61, true, false, true, false, false, false),
            new Parada(159, "Casanova", 42.87873650276701, -7.928288977983527, 1.61, 3.61, false, false, true, false, false, false),
            new Parada(160, "O Coto", 42.88474207061356, -7.958576889288352, 3.61, 0.820, true, true, false, false, false, false),
            new Parada(161, "O Leboreiro", 42.88743266351978, -7.965299264015186, 0.820, 6.83, true, true, false, false, false, false),
            new Parada(162, "Melide", 42.9141052447838, -8.014836771433863, 6.83, 7.47, true, true, true, true, true, true),
            new Parada(163, "Boente", 42.91616067784371, -8.077663408199825, 7.47, 2.71, true, false, true, false, false, false),
            new Parada(164, "A Castañeda", 42.92478002933106, -8.097418969451354, 2.71, 4.02, true, false, true, false, false, false),
            new Parada(165, "Ribadiso da Baixo", 42.93051260173026, -8.130716769961907, 4.02, 3.58, true, false, true, false, false, false),
            new Parada(166, "Arzúa", 42.92692155197133, -8.161119362391659, 3.58, 10.3, true, true, true, true, true, true),
            new Parada(167, "Calle", 42.91767583645103, -8.244318524374819, 10.3, 4.26, false, false, false, false, false, false),
            new Parada(168, "Salceda", 42.92648137773291, -8.280387396777883, 4.26, 3.0, true, true, true, false, false, false),
            new Parada(169, "A Brea", 42.9188424135804, -8.305409869796506, 3.0, 2.93, true, true, false, false, false, false),
            new Parada(170, "Santa Irene", 42.91731511475645, -8.329524574091849, 2.93, 2.4, true, false, true, false, false, false),
            new Parada(171, "A Rúa", 42.91458761701929, -8.350123163416683, 2.4, 1.56, true, true, false, false, false, false),
            new Parada(172, "Pedrouzo ", 42.90947528176598, -8.362972578946312, 1.56, 3.97, true, true, true, true, true, true),
            new Parada(173, "Amenal", 42.90594493197617, -8.394275770447933, 3.97, 4.83, true, false, true, false, false, false),
            new Parada(174, "San Paio", 42.90867641506432, -8.425675068216009, 4.83, 2.59, false, false, false, false, false, false),
            new Parada(175, "Lavacolla", 42.90018540145267, -8.446736905518236, 2.59, 6.41, true, true, false, false, false, false),
            new Parada(176, "San Marcos", 42.89135116830965, -8.488855354328081, 6.41, 1.14, true, true, false, false, false, false),
            new Parada(177, "Monte do Gozo", 42.8881420518836, -8.49860035847712, 1.14, 5.92, true, false, true, false, false, false),
            new Parada(178, "Santiago de Compostela", 42.88061530114828, -8.544359287974544, 5.92, 0, true, true, true, true, true, true)
    ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_camino);

        XmlPullParserFactory pullParserFactory;

        try {
            InputStream istr = getApplicationContext().getAssets().open("caminoFrances.xml");

            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();

            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(istr, null);

            parseXML(parser);
        } catch (XmlPullParserException e) {
            Log.e(AccionCaminoNuevo.DATOS_PARADA, "Error en el procesador del archivo XML: " + e.getMessage());
        } catch (IOException e) {
            Log.e(AccionCaminoNuevo.DATOS_PARADA, "Error de entrada/salida en el archivo XML: " + e.getMessage());
        }

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

    private void parseXML(XmlPullParser parser) throws XmlPullParserException, IOException {
        int eventType = parser.getEventType();
        ArrayList<Parada> paradas = null;
        Parada parada = new Parada();
        float latitud;
        float longitud;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String etiqueta = null;
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    paradas = new ArrayList();
                    break;
                case XmlPullParser.START_TAG:
                    etiqueta = parser.getName();

                    switch (etiqueta){
                        case "orden":
                            parada.setOrden(Integer.parseInt(parser.nextText()));
                            break;
                        case "nombre":
                            parada.setNombre(parser.nextText());
                            break;
                        case "coords":
                            String[] parts = parser.nextText().split(",");
                            latitud = Float.parseFloat(parts[0]);
                            longitud = Float.parseFloat(parts[1]);
                            parada.addCoords(latitud, longitud);
                            break;
                        case "distAnterior":
                            parada.setDistAnterior(Float.parseFloat(parser.nextText()));
                            break;
                        case "distSiguiente":
                            parada.setDistSiguiente(Float.parseFloat(parser.nextText()));
                            break;
                        case "comida":
                            parada.setComida(Boolean.parseBoolean(parser.nextText()));
                            break;
                        case "hotel":
                            parada.setHotel(Boolean.parseBoolean(parser.nextText()));
                            break;
                        case "albergue":
                            parada.setAlbergue(Boolean.parseBoolean(parser.nextText()));
                            break;
                        case "farmacia":
                            parada.setFarmacia(Boolean.parseBoolean(parser.nextText()));
                            break;
                        case "banco":
                            parada.setBanco(Boolean.parseBoolean(parser.nextText()));
                            break;
                        case "internet":
                            parada.setInternet(Boolean.parseBoolean(parser.nextText()));
                            break;
                    }

                    break;
                case XmlPullParser.END_TAG:
                    etiqueta = parser.getName();
                    if (etiqueta.equalsIgnoreCase("parada") && parada != null) {
                        Log.d(AccionCaminoNuevo.DATOS_PARADA, parada.toString());
                        paradas.add(parada);
                        parada.remCoords();
                    }
            }
            eventType = parser.next();
        }
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
            Toast.makeText(this, "ENTRO.", Toast.LENGTH_SHORT).show();
            // etapas = crearEtapasCaminoNuevo(dias, comienzoCamino, nombre, kmDia);
            etapas = pruebaETAPAS();
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
                    ordenParada++;
                } else {

                   /* //Si en la parada final no hay sitio donde dormir hacemos backtracking hasta la parada mas cercana que sí tenga alojamientos.
                    while(!paradasEtapa.get(paradasEtapa.size()-1).getHotel() && !paradasEtapa.get(paradasEtapa.size()-1).getAlbergue())
                    {
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

        listaEtapas.add(new Etapa(32, new ArrayList<Parada>(Arrays.asList(
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
