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
    private GestionFicheros archivador = new GestionFicheros();
    private Usuario usuarioSeleccionado = null;

    private ArrayList<Parada> listaParadas = new ArrayList<Parada>(Arrays.asList(
            new Parada(1,"Saint-Jean-Pied-de-Port",43.163559,-1.235662,0,5, true, true, true, true, true, true),
            new Parada(2,"Huntto",43.124447,-1.24476,5, 2.5, true, true, true, true, true, false),
            new Parada(3,"Orisson",43.10899321682584,-1.239052481080165, 2.5, 27.9, true, true, true, false, false, false),
            new Parada(4,"Orreaga/Roncesvalles",43.00954730092566,-1.319763812529985, 27.9, 2.8, true, true, true, true, true, true),
            new Parada(5,"Auritz/Burguete",42.990768,-1.33473, 2.8, 3.7, true, true, false, false, false, false),
            new Parada(6,"Aurizberri/Espinal",42.981289,-1.37163, 3.7, 6.2, true, true, true, false, false, false),
            new Parada(7,"Bizkarreta-Gerendiain",42.96769,-1.41792, 6.2, 1.9, true, true, false, false, false, false),
            new Parada(8,"Lintzoain",42.96286,-1.4377, 1.9, 8.4, true, true, false, false, false, false),
            new Parada(9,"Zubiri",42.93036118544159,-1.50466604700992, 8.4, 4.9, true, true, true, false, false, false),
            new Parada(10,"Larrasoaña",42.900879,-1.5404, 4.9, 3.2, true, true, true, false, false, false),
            new Parada(11,"Zuriain",42.878681,-1.56608, 3.2, 3.2, false, false, false, false, false, false),
            new Parada(12,"Zabaldika",42.85474,-1.58113, 3.2, 4.4, false, false, true, false, false, false),
            new Parada(13,"Santísima Trinidad de Arre",42.836189,-1.60428, 4.4, 1.9, false, false, true, false, false, false),
            new Parada(14,"Villava",42.829681,-1.61037, 1.9, 0.8, true, true, true, false, false, false),
            new Parada(15,"Burlata",42.824631,-1.61765, 0.8, 3.8, false, false, false, false, false, false),
            new Parada(16,"Pamplona",42.818851,-1.64269, 3.8, 4.4, true, true, true, true, true, true),
            new Parada(17,"Cizur Menor",42.786346,-1.677024, 4.4, 6.4, true, false, true, false, false, false),
            new Parada(18,"Zariquiegui",42.74791,-1.723008, 6.4, 8.1, true, false, true, false, false, false),
            new Parada(19,"Uterga",42.709103,-1.76101, 8.1, 2.6, true, true, true, false, false, false),
            new Parada(20,"Muruzábal",42.68969,-1.771202, 2.6, 1.8, false, false, false, false, false, false),
            new Parada(21,"Obanos",42.680275,-1.785729, 1.8, 2.7, true, true, true, false, false, false),
            new Parada(22,"Puente la Reina",42.673096,-1.810405, 2.7, 4.9, true, true, true, true, true, true),
            new Parada(23,"Mañeru",42.669022,-1.86209, 4.9, 2.5, false, false, true, false, false, false),
            new Parada(24,"Cirauqui",42.675789,-1.89142, 2.5, 5.5, false, false, true, false, false, false),
            new Parada(25,"Villatuerta",42.659447,-1.992731, 4.6, 4.7, true, true, true, false, false, false),
            new Parada(26,"Estella",42.66983,-2.027471, 4.7, 2.2, true, true, true, true, true, true),
            new Parada(27,"Lorca",42.663738,-2.032793, 5.5, 4.6, false, false, true, false, false, false),
            new Parada(28,"Ayegui",42.657784,-2.038736, 2.2,1, false, false, true, false, false, false),
            new Parada(29,"Monasterio de Irache",42.650002,-2.044283,1, 0.05, false, false, false, false, false, false),
            new Parada(30,"Irache",42.646317,-2.056932, 0.05, 4.3, true, true, false, false, false, false),
            new Parada(31,"Azqueta",42.635578,-2.087016, 4.3, 1.8, false, false, false, false, false, false),
            new Parada(32,"Villamayor de Monjardín",42.629475,-2.105427, 1.8, 11.7, true, true, true, false, false, false),
            new Parada(33,"Los Arcos",42.568855,-2.193596, 11.7, 6.5, true, true, true, true, true, true),
            new Parada(34,"Sansol",42.553703,-2.265909, 6.5, 0.9, true, true, true, false, false, false),
            new Parada(35,"Torres del Río",42.552006,-2.271295, 0.9, 9.3, true, true, true, false, false, false),
            new Parada(36,"Viana",42.51554261904568,-2.37181831350982, 9.3, 10.2, true, true, true, false, false, false),
            new Parada(37,"Logroño",42.46837095429641,-2.444684460353869, 10.2, 13.1, true, true, true, true, true, true),
            new Parada(38,"Navarrete",42.429539,-2.56221, 13.1, 6.6, true, true, true, true, true, false),
            new Parada(39,"Ventosa",42.411743,-2.627728, 6.6,10, true, true, true, false, false, false),
            new Parada(40,"Nájera",42.416634,-2.735177,10, 6.1, true, true, true, true, true, true),
            new Parada(41,"Azofra",42.424026,-2.800612, 6.1, 9.2, true, true, true, false, false, false),
            new Parada(42,"Cirueña",42.412827,-2.896335, 9.2, 5.5, true, true, true, false, false, false),
            new Parada(43,"Santo Domingo de la Calzada",42.440861,-2.952554, 5.5, 7.4, true, true, true, true, true, true),
            new Parada(44,"Grañón",42.450569,-3.026948, 7.4, 3.9, true, true, true, false, false, false),
            new Parada(45,"Redecilla del Camino",42.438267,-3.064091, 3.9, 1.6, true, true, true, false, false, false),
            new Parada(46,"Castildelgado",42.436775,-3.083575000000001, 1.6, 2.1, true, true, false, false, false, false),
            new Parada(47,"Viloria de Rioja",42.426086,-3.101664, 2.1, 3.4, true, true, true, false, false, false),
            new Parada(48,"Villamayor del Rio",42.427448,-3.136146, 3.4, 4.8, false, false, true, false, false, false),
            new Parada(49,"Belorado",42.420841,-3.188653,4.8, 4.7, true, true, true, true, true, true),
            new Parada(50,"Tosantos",42.413208,-3.243971, 4.7, 2.1, false, false, true, false, false, false),
            new Parada(51,"Villambistia",42.40451,-3.261695, 2.1, 1.7, false, false, true, false, false, false),
            new Parada(52,"Espinosa del Camino",42.405842,-3.280984999999999, 1.7, 3.5, false, false, true, false, false, false),
            new Parada(53,"Villafranca Montes de Oca",42.390217,-3.308172, 3.5, 12.4, true, true, true, true, true, false),
            new Parada(54,"San Juan de Ortega",42.375759,-3.436747, 12.4, 3.6, true, true, true, false, false, false),
            new Parada(55,"Agés",42.369926,-3.478975, 3.6, 2.5, false, false, true, false, false, false),
            new Parada(56,"Atapuerca",42.376682,-3.507922000000001, 2.5,6, true, true, true, false, false, false),
            new Parada(57,"Cardeñuela Riopico",42.367485,-3.571179,6, 2.2, true, false, true, false, false, false),
            new Parada(58,"Orbaneja Riopico",42.360191,-3.584225, 2.2, 3.8, true, true, false, false, false, false),
            new Parada(59,"Villafría de Burgos",42.364311,-3.618218999999999, 3.8, 7.1, true, true, false, false, false, false),
            new Parada(60,"Burgos",42.340927,-3.704876, 7.1, 11.2, true, true, false, false, false, false),
            new Parada(61,"Tardajos",42.348377,-3.818028, 11.2, 2.1, true, true, true, false, false, false),
            new Parada(62,"Rabé de las Calzadas",42.34026,-3.834314, 2.1, 7.7, false, false, true, false, false, false),
            new Parada(63,"Hornillos del Camino",42.338593,-3.926411, 7.7, 10.1, true, true, true, false, false, false),
            new Parada(64,"San Bol",42.326096,-3.990548, 10.1, 7.9, false, false, true, false, false, false),
            new Parada(65,"Hontanas",42.312737,-4.045115, 7.9, 5.4, true, true, true, false, false, false),
            new Parada(66,"San Antón",42.292816,-4.099081, 5.4, 3.3, false, false, true, false, false, false),
            new Parada(67,"Castrojeriz",42.288216,-4.137597, 3.3, 9.1, true, true, true, true, true, true),
            new Parada(68,"Ermita de San Nicolás",42.280941,-4.23919, 9.1, 2.4, false, false, true, false, false, false),
            new Parada(69,"Itero de la Vega",42.287369,-4.25801, 2.4, 8.1, true, true, true, false, false, false),
            new Parada(70,"Boadilla del Camino",42.260681,-4.34762, 8.1, 5.9, true, true, true, true, false, false),
            new Parada(71,"Frómista",42.26604401852833,-4.406116165294307, 5.9, 3.7, true, true, true, true, true, false),
            new Parada(72,"Población de Campos",42.26933083041678,-4.445625041295429, 3.7, 3.7, true, true, false, false, false, false),
            new Parada(73,"Revenga de Campos",42.28823829512323,-4.475330946389946, 3.7, 2.2, false, false, false, false, false, false),
            new Parada(74,"Villarmentero de Campo",42.29782534835481,-4.499479221331612, 2.2, 4.5, true, true, true, false, false, false),
            new Parada(75,"Villalcázar de Sirga",42.31647637100176,-4.542616509294902, 4.5, 5.6, true, true, true, false, false, false),
            new Parada(76,"Carrión de los Condes",42.33701793271186,-4.600910972351841, 5.6, 17.2, true, true, true, true, true, true),
            new Parada(77,"Calzadilla de la Cueza",42.32928819482914,-4.805365948789728, 17.2, 6.1, true, true, true, false, false, false),
            new Parada(78,"Ledigos",42.35346892266805,-4.865210560882744, 6.1, 3.2, false, false, true, false, false, false),
            new Parada(79,"Terradillos de los Templarios",42.36243686904546,-4.891521292416499, 3.2, 3.4, true, false, true, false, false, false),
            new Parada(80,"Moratinos",42.36065913738717,-4.926749189861326, 3.4, 2.2, true, true, true, false, false, false),
            new Parada(81,"San Nicolás del Real Camino",42.36360024206139,-4.952028362233747, 2.2, 6.6, false, false, true, false, false, false),
            new Parada(82,"Sahagún",42.3710019504643,-5.027328259769433, 6.6, 10.2, true, true, true, true, true, true),
            new Parada(83,"Bercianos del Real Camino",42.38608608161385,-5.14566965408126, 10.2, 7.5, true, true, true, false, false, false),
            new Parada(84,"El Burgo Ranero",42.4225217635454,-5.220259237441312, 7.5, 13.1, true, true, true, false, false, false),
            new Parada(85,"Reliegos",42.47494326901281,-5.354935434308388, 13.1, 5.8, true, false, true, false, false, false),
            new Parada(86,"Mansilla de las Mulas",42.49896383277923,-5.417510831775999, 5.8, 5.1, true, true, true, true, true, true),
            new Parada(87,"Villamoros de Mansilla",42.53480476877282,-5.444472955200865, 5.1, 2.2, false, false, false, false, false, false),
            new Parada(88,"Puente de Villarente",42.54574418415028,-5.459394421067614, 2.2, 3.9, true, true, true, false, false, false),
            new Parada(89,"Arcahueja",42.56717952366034,-5.495987313777235, 3.9, 8.3, true, true, true, false, false, false),
            new Parada(90,"León",42.59896683878336,-5.567367603479269, 8.3, 2.7, true, true, true, true, true, true),
            new Parada(91,"Trobajo del Camino",42.59608795248055,-5.608595773586231, 2.7, 4.3, true, true, false, false, false, false),
            new Parada(92,"La Virgen del Camino",42.5801565275495,-5.640897997782503, 4.3, 4.8, true, true, true, true, true, true),
            new Parada(93,"Valverde de la Virgen",42.568834799466,-5.683178067759386, 4.8, 1.4, false, false, false, false, false, false),
            new Parada(94,"San Miguel del Camino",42.56154138012829,-5.697586793589396, 1.4, 7.6, false, false, false, false, false, false),
            new Parada(95,"Villadangos del Páramo",42.51908399190783,-5.765223328138703, 7.6, 4.4, true, true, true, false, false, false),
            new Parada(96,"San Martín del Camino",42.494899739285,-5.80891904903929, 4.4, 7.1, true, false, true, false, false, false),
            new Parada(97,"Hospital de Órbigo",42.46361619177748,-5.881946054204477, 7.1, 2.5, true, true, true, true, true, true),
            new Parada(98,"Villares de Órbigo",42.45788317485298,-5.898403714172119, 2.5, 2.6, true, false, true, false, false, false),
            new Parada(99,"Santibáñez de Valdeiglesias",42.45836315324036,-5.930283589071948, 2.6, 7.7, true, false, true, false, false, false),
            new Parada(100,"San Justo de la Vega",42.45438775508052,-6.015809159902432, 7.7, 3.8, true, false, true, false, false, false),
            new Parada(101,"Astorga",42.45547070692994,-6.053048477683262, 3.8, 2.2, true, true, true, true, true, true),
            new Parada(102,"Valdeviejas",42.4593561692688,-6.081113398148332, 2.2, 2.4, false, false, true, false, false, false),
            new Parada(103,"Murias de Rechivaldo",42.45925660109395,-6.107220848233881, 2.4, 4.7, true, true, true, false, false, false),
            new Parada(104,"Santa Catalina de Somoza",42.4554552388274,-6.160104738033595, 4.7,4, true, true, true, false, false, false),
            new Parada(105,"El Ganso",42.46268946370218,-6.209066967202332,4, 6.8, true, true, true, false, false, false),
            new Parada(106,"Rabanal del Camino",42.48234063113036,-6.284265346272703, 6.8, 5.8, true, true, true, true, true, true),
            new Parada(107,"Foncebadón",42.49143966226282,-6.342934205298832, 5.8, 1.9, true, false, true, false, false, false),
            new Parada(108,"Cruz de Ferro",42.48875316775605,-6.361711286773868, 1.9, 2.3, false, false, false, false, false, false),
            new Parada(109,"Manjarín",42.49018995221314,-6.386692402286143, 2.3, 7.1, false, false, true, false, false, false),
            new Parada(110,"El Acebo",42.49887821380196,-6.457001077360444, 7.1, 3.3, true, true, true, false, false, false),
            new Parada(111,"Riego de Ambrós",42.52172095711743,-6.479373488698768, 3.3, 5.1, true, true, true, false, false, false),
            new Parada(112,"Molinaseca",42.53860829170707,-6.522440045703348, 5.1, 4.6, true, true, true, true, true, true),
            new Parada(113,"Campo",42.53853309827439,-6.56431941344519, 4.6, 4.9, false, false, false, false, false, false),
            new Parada(114,"Ponferrada",42.54344921381514,-6.593135292241726, 4.9, 2.6, true, true, true, true, true, true),
            new Parada(115,"Columbrianos",42.57348935824068,-6.611247476744794, 2.6, 3.4, true, true, false, false, false, false),
            new Parada(116,"Fuentesnuevas",42.57737168093492,-6.643172530797116, 3.4,2, true, false, true, false, false, false),
            new Parada(117,"Camponaraya",42.57896585884718,-6.667447841875158,2, 5.9, false, false, false, false, false, false),
            new Parada(118,"Cacabelos",42.5997817107809,-6.722804536771322, 5.9, 2.5, true, true, true, true, true, true),
            new Parada(119,"Pieros",42.60567601165889,-6.749549276361094, 2.5, 2.1, true, false, true, false, false, false),
            new Parada(120,"Valtuille de Arriba",42.60427497155767,-6.765248315962836, 2.1, 4.8, false, false, false, false, false, false),
            new Parada(121,"Villafranca del Bierzo",42.60423226035614,-6.807388524279247, 4.8,5, true, true, true, true, true, true),
            new Parada(122,"Pereje",42.631796938234,-6.836967591340169,5, 4.5, true, false, true, false, false, false),
            new Parada(123,"Trabadelo",42.64956395617789,-6.881846368192599, 4.5, 3.9, true, true, true, true, true, true),
            new Parada(124,"La Portela de Valcarce",42.66006915112168,-6.920651078795018, 3.9, 1.2, true, true, true, false, false, false),
            new Parada(125,"Ambasmestas",42.66586330630172,-6.928323772229049, 1.2, 1.6, true, true, true, false, false, false),
            new Parada(126,"Vega de Valcarce",42.6654104342722,-6.946317443830991, 1.6, 2.3, true, true, true, true, true, true),
            new Parada(127,"Ruitelán",42.67183484377121,-6.968815215784345, 2.3, 1.6, true, false, true, false, false, false),
            new Parada(128,"Las Herrerías",42.67095737774029,-6.982753547756147, 1.6, 2.9, true, true, true, false, false, false),
            new Parada(129,"La Faba",42.68550511542193,-7.010181343582032, 2.9, 2.4, true, false, true, false, false, false),
            new Parada(130,"La Laguna",42.70159526716245,-7.022385249871164, 2.4, 2.4, true, false, true, false, false, false),
            new Parada(131,"O Cebreiro",42.70774872184296,-7.044702455821906, 2.4, 3.4, true, true, true, false, false, false),
            new Parada(132,"Liñares",42.69833140250739,-7.077663552504576, 3.4, 2.2, true, true, false, false, false, false),
            new Parada(133,"Hospital da Condesa",42.70461022667205,-7.099454108076372, 2.2, 2.7, false, false, true, false, false, false),
            new Parada(134,"Alto do Poio",42.71278400049732,-7.125619692234523, 2.7, 3.5, true, true, true, false, false, false),
            new Parada(135,"Fonfría do Camiño",42.73196564138166,-7.158311769221725, 3.5, 2.2, true, true, true, false, false, false),
            new Parada(136,"O Biduedo ",42.74332267202118,-7.178037704809229, 2.2,3, true, true, false, false, false, false),
            new Parada(137,"Triacastela",42.75598351002881,-7.239805869607096,4, 5.7, true, true, true, true, true, true),
            new Parada(138,"Renche",42.73989052976592,-7.290904414783981,5.7,4.3, false, false, false, false, false, false),
            new Parada(139,"Samos",42.73054712527786,-7.326522486809409,4.3,1.8, true, false, true, true, false, false),
            new Parada(140,"Teiguín",42.72466772785302,-7.342060889947618,1.8,9.8, true, false, true, false, false, false),
            new Parada(141,"Sarria",42.77713141278561,-7.413519715127549,9.8, 4.3, true, true, true, true, true, true),
            new Parada(142,"Barbadelo",42.76928465282261,-7.444279331404772, 4.3,7.7, true, true, true, false, false, false),
            new Parada(143,"A Brea",42.77962241404003,-7.515884043803027,7.7,1.7, false, false, false, false, false, false),
            new Parada(144,"Ferreiros",42.78329278372696,-7.529116390630719,1.7,1.9, true, false, true, false, false, false),
            new Parada(145,"As Rozas",42.78400404072978,-7.549803125409226,1.9,3, false, false, false, false, false, false),
            new Parada(146,"Mercadoiro",42.78946837912826,-7.570525976926674,3,3, true, false, true, false, false, false),
            new Parada(147,"Vilachá",42.79536377835125,-7.603606985671405,3, 2.4, true, false, true, false, false, false),
            new Parada(148,"Portomarín",42.80572413866636,-7.618897235307927, 2.4, 4.5, true, true, true, true, true, true),
            new Parada(149,"Toxibó",42.81283566700174,-7.66403628372035, 4.5, 3.4, false, false, false, false, false, false),
            new Parada(150,"Gonzar",42.82614870570648,-7.695667041793499, 3.4, 1.4, true, false, true, false, false, false),
            new Parada(151,"Castromaior",42.83164641237603,-7.708906139951253, 1.4, 2.5, true, true, false, false, false, false),
            new Parada(152,"Hospital de la Cruz",42.84111520107175,-7.734540537421708, 2.5, 1.6, true, true, true, false, false, false),
            new Parada(153,"Ventas de Narón",42.84427066894147,-7.748648840437681, 1.6, 3.2, true, false, true, false, false, false),
            new Parada(154,"Ligonde",42.86035480365461,-7.781590998536713, 3.2, 3.6, true, true, true, false, false, false),
            new Parada(155,"Lestedo",42.87221882899041,-7.814220485892847, 3.6, 4.8, true, true, true, false, false, false),
            new Parada(156,"Palas de Rei",42.87322485592507,-7.86899634128359, 4.8,4, true, true, true, true, true, true),
            new Parada(157,"San Xulián do Camiño",42.87598622479732,-7.909112191324297,4, 4.8, true, false, true, false, false, false),
            new Parada(158,"A Ponte Campaña",42.87838096185967,-7.914432776657094, 4.8, 1.7, true, false, true, false, false, false),
            new Parada(159,"Casanova",42.87873650276701,-7.928288977983527, 1.7, 3.6, false, false, true, false, false, false),
            new Parada(160,"O Coto",42.88474207061356,-7.958576889288352, 3.6, 0.65, true, true, false, false, false, false),
            new Parada(161,"O Leboreiro",42.88743266351978,-7.965299264015186, 0.65, 5.3, true, true, false, false, false, false),
            new Parada(162,"Melide",42.9141052447838,-8.014836771433863, 5.3, 5.8, true, true, true, true, true, true),
            new Parada(163,"Boente",42.91616067784371,-8.077663408199825, 5.8, 2.4, true, false, true, false, false, false),
            new Parada(164,"A Castañeda",42.92478002933106,-8.097418969451354, 2.4, 3.1, true, false, true, false, false, false),
            new Parada(165,"Ribadiso da Baixo",42.93051260173026,-8.130716769961907, 3.1,3, true, false, true, false, false, false),
            new Parada(166,"Arzúa",42.92692155197133,-8.161119362391659,3, 8.6, true, true, true, true, true, true),
            new Parada(167,"Calle",42.91767583645103,-8.244318524374819, 8.6, 3.3, false, false, false, false, false, false),
            new Parada(168,"Salceda",42.92648137773291,-8.280387396777883, 3.3, 2.4, true, true, true, false, false, false),
            new Parada(169,"A Brea",42.9188424135804,-8.305409869796506, 2.4, 2.4, true, true, false, false, false, false),
            new Parada(170,"Santa Irene",42.91731511475645,-8.329524574091849, 2.4, 1.7, true, false, true, false, false, false),
            new Parada(171,"A Rúa",42.91458761701929,-8.350123163416683, 1.7, 1.6, true, true, false, false, false, false),
            new Parada(172,"Pedrouzo ",42.90947528176598,-8.362972578946312, 1.6, 3.2, true, true, true, true, true, true),
            new Parada(173,"Amenal",42.90594493197617,-8.394275770447933, 3.2, 4.1, true, false, true, false, false, false),
            new Parada(174,"San Paio",42.90867641506432,-8.425675068216009, 4.1, 2.4, false, false, false, false, false, false),
            new Parada(175,"Lavacolla",42.90018540145267,-8.446736905518236, 2.4,4, true, true, false, false, false, false),
            new Parada(176,"San Marcos",42.89135116830965,-8.488855354328081,4, 0.7, true, true, false, false, false, false),
            new Parada(177,"Monte do Gozo",42.8881420518836,-8.49860035847712, 0.7, 4.9, true, false, true, false, false, false),
            new Parada(178,"Santiago de Compostela",42.88061530114828,-8.544359287974544, 4.9,0, true, true, true, true, true, true)
    ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_camino);

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
                this.listaParadas.get(26),
                this.listaParadas.get(27)
        ))));

        listaEtapas.add(new Etapa(6, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(27),
                this.listaParadas.get(28),
                this.listaParadas.get(29),
                this.listaParadas.get(30),
                this.listaParadas.get(31),
                this.listaParadas.get(32),
                this.listaParadas.get(33),
                this.listaParadas.get(34),
                this.listaParadas.get(35)
        ))));

        listaEtapas.add(new Etapa(7, new ArrayList<Parada>(Arrays.asList(
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
                this.listaParadas.get(54),
                this.listaParadas.get(55)
        ))));

        listaEtapas.add(new Etapa(12, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(55),
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
                this.listaParadas.get(63),
                this.listaParadas.get(64),
                this.listaParadas.get(65)
        ))));

        listaEtapas.add(new Etapa(14, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(65),
                this.listaParadas.get(66),
                this.listaParadas.get(67),
                this.listaParadas.get(68),
                this.listaParadas.get(69),
                this.listaParadas.get(70)
        ))));

        listaEtapas.add(new Etapa(15, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(70),
                this.listaParadas.get(71),
                this.listaParadas.get(72),
                this.listaParadas.get(73),
                this.listaParadas.get(74),
                this.listaParadas.get(75),
                this.listaParadas.get(76)
        ))));

        listaEtapas.add(new Etapa(16, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(76),
                this.listaParadas.get(77),
                this.listaParadas.get(78),
                this.listaParadas.get(79)
        ))));

        listaEtapas.add(new Etapa(17, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(79),
                this.listaParadas.get(80),
                this.listaParadas.get(81),
                this.listaParadas.get(82),
                this.listaParadas.get(83),
                this.listaParadas.get(84)
        ))));

        listaEtapas.add(new Etapa(18, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(84),
                this.listaParadas.get(85),
                this.listaParadas.get(86),
                this.listaParadas.get(87),
                this.listaParadas.get(88),
                this.listaParadas.get(89),
                this.listaParadas.get(90)
        ))));

        listaEtapas.add(new Etapa(19, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(90),
                this.listaParadas.get(91),
                this.listaParadas.get(92),
                this.listaParadas.get(93),
                this.listaParadas.get(94),
                this.listaParadas.get(95),
                this.listaParadas.get(96)
        ))));

        listaEtapas.add(new Etapa(20, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(96),
                this.listaParadas.get(97),
                this.listaParadas.get(98),
                this.listaParadas.get(99),
                this.listaParadas.get(100),
                this.listaParadas.get(101)
        ))));

        listaEtapas.add(new Etapa(21, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(101),
                this.listaParadas.get(102),
                this.listaParadas.get(103),
                this.listaParadas.get(104),
                this.listaParadas.get(105),
                this.listaParadas.get(106),
                this.listaParadas.get(107)
        ))));

        listaEtapas.add(new Etapa(22, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(107),
                this.listaParadas.get(108),
                this.listaParadas.get(109),
                this.listaParadas.get(110),
                this.listaParadas.get(111),
                this.listaParadas.get(112),
                this.listaParadas.get(113),
                this.listaParadas.get(114)
        ))));

        listaEtapas.add(new Etapa(23, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(114),
                this.listaParadas.get(115),
                this.listaParadas.get(116),
                this.listaParadas.get(117),
                this.listaParadas.get(118),
                this.listaParadas.get(119),
                this.listaParadas.get(120),
                this.listaParadas.get(121)
        ))));

        listaEtapas.add(new Etapa(24, new ArrayList<Parada>(Arrays.asList(
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

        listaEtapas.add(new Etapa(25, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(131),
                this.listaParadas.get(132),
                this.listaParadas.get(133),
                this.listaParadas.get(134),
                this.listaParadas.get(135),
                this.listaParadas.get(136),
                this.listaParadas.get(137),
                this.listaParadas.get(138)
        ))));

        listaEtapas.add(new Etapa(26, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(138),
                this.listaParadas.get(139),
                this.listaParadas.get(140),
                this.listaParadas.get(141),
                this.listaParadas.get(142),
                this.listaParadas.get(143),
                this.listaParadas.get(144),
                this.listaParadas.get(145)
        ))));

        listaEtapas.add(new Etapa(27, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(145),
                this.listaParadas.get(146),
                this.listaParadas.get(147),
                this.listaParadas.get(148),
                this.listaParadas.get(149),
                this.listaParadas.get(150),
                this.listaParadas.get(151),
                this.listaParadas.get(152),
                this.listaParadas.get(153)
        ))));

        listaEtapas.add(new Etapa(28, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(153),
                this.listaParadas.get(154),
                this.listaParadas.get(155),
                this.listaParadas.get(156),
                this.listaParadas.get(157),
                this.listaParadas.get(158),
                this.listaParadas.get(159),
                this.listaParadas.get(160),
                this.listaParadas.get(161)
        ))));

        listaEtapas.add(new Etapa(29, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(161),
                this.listaParadas.get(162),
                this.listaParadas.get(163),
                this.listaParadas.get(164),
                this.listaParadas.get(165),
                this.listaParadas.get(166),
                this.listaParadas.get(167),
                this.listaParadas.get(168),
                this.listaParadas.get(169),
                this.listaParadas.get(170),
                this.listaParadas.get(171)
        ))));

        listaEtapas.add(new Etapa(30, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(171),
                this.listaParadas.get(172),
                this.listaParadas.get(173),
                this.listaParadas.get(174),
                this.listaParadas.get(175),
                this.listaParadas.get(176),
                this.listaParadas.get(177)
        ))));

        listaEtapas.add(new Etapa(31, new ArrayList<Parada>(Arrays.asList(
                this.listaParadas.get(177),
                this.listaParadas.get(178),
                this.listaParadas.get(179),
                this.listaParadas.get(180),
                this.listaParadas.get(181),
                this.listaParadas.get(182),
                this.listaParadas.get(183)
        ))));

        return listaEtapas;
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
