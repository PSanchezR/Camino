package com.dev.lin.camino;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
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
    private Usuario usuarioSeleccionado = null;

    private ArrayList<Parada> listaParadasCaminoFrances = new ArrayList<Parada>(Arrays.asList(
            new Parada(1, "Saint Jean Pied de Port", 43.1569766, -1.2337874, 0, 5, true, true, true, true, true, true),
            new Parada(2, "Honto", 43.1243525, -1.244748, 5, 2.5, true, true, true, true, true, false),
            new Parada(3, "Orisson", 43.1078991, -1.239412, 2.5, 27.9, true, true, true, false, false, false),
            new Parada(4, "Roncesvalles", 43.0092949, -1.319693, 27.9, 2.8, true, true, true, true, true, true),
            new Parada(5, "Burguete", 42.9891494, -1.3349746, 2.8, 3.7, true, true, false, false, false, false),
            new Parada(6, "Espinal", 42.9784853, -1.3689033, 3.7, 6.2, true, true, true, false, false, false),
            new Parada(7, "Bizkarreta", 42.967821, -1.41761, 6.2, 1.9, true, true, false, false, false, false),
            new Parada(8, "Lintzoain", 42.9622941, -1.4374914, 1.9, 8.4, true, true, false, false, false, false),
            new Parada(9, "Zubiri", 42.9311744, -1.5031652, 8.4, 4.9, true, true, true, false, false, false),
            new Parada(10, "Larrasoaña", 42.901110, -1.542937, 4.9, 3.2, true, true, true, false, false, false),
            new Parada(11, "Zuriaín", 42.8794971, -1.5658047, 3.2, 3.2, false, false, false, false, false, false),
            new Parada(12, "Zabaldika", 42.8560356, -1.5821676, 3.2, 4.4, false, false, true, false, false, false),
            new Parada(13, "Arre", 42.8422961, -1.6115911, 4.4, 1.9, false, false, true, false, false, false),
            new Parada(14, "Villava/Atarrabia", 42.8317741, -1.610218, 1.9, 0.8, true, true, true, false, false, false),
            new Parada(15, "Burlada", 42.826559, -1.6149977, 0.8, 3.8, false, false, false, false, false, false),
            new Parada(16, "Pamplona", 42.815766, -1.6500216, 3.8, 4.4, true, true, true, true, true, true),
            new Parada(17, "Cizur Menor", 42.7876245, -1.6788998, 4.4, 6.4, true, false, true, false, false, false),
            new Parada(18, "Zariquiegui", 42.7478004, -1.7229047, 6.4, 8.1, true, false, true, false, false, false),
            new Parada(19, "Uterga", 42.7088268, -1.759921, 8.1, 2.6, true, true, true, false, false, false),
            new Parada(20, "Muruzábal", 42.6894469, -1.7700646, 2.6, 1.8, false, false, false, false, false, false),
            new Parada(21, "Óbanos", 42.6813332, -1.7869176, 1.8, 2.7, true, true, true, false, false, false),
            new Parada(22, "Puente la Reina", 42.6719605, -1.8136042, 2.7, 4.9, true, true, true, true, true, true),
            new Parada(23, "Mañeru", 42.669889, -1.8626504, 4.9, 2.5, false, false, true, false, false, false),
            new Parada(24, "Cirauqui", 42.6755693, -1.8896709, 2.5, 5.5, false, false, true, false, false, false),
            new Parada(25, "Lorca", 42.6720822, -1.945259, 5.5, 4.6, false, false, true, false, false, false),
            new Parada(26, "Villatuerta", 42.6607176, -1.9931451, 4.6, 4.7, true, true, true, false, false, false),
            new Parada(27, "Estella", 42.6713975, -2.0372037, 4.7, 2.2, true, true, true, true, true, true),
            new Parada(28, "Ayegui", 42.6575097, -2.0383751, 2.2, 1, false, false, true, false, false, false),
            new Parada(29, "Monasterio de Irache", 42.650065, -2.043214, 1, 0.05, false, false, false, false, false, false),
            new Parada(30, "Irache", 42.650119, -2.044350, 0.05, 4.3, true, true, false, false, false, false),
            new Parada(31, "Azqueta", 42.6347917, -2.0876089, 4.3, 1.8, false, false, false, false, false, false),
            new Parada(32, "Villamayor de Monjardín", 42.629572, -2.104846, 1.8, 11.7, true, true, true, false, false, false),
            new Parada(33, "Los Arcos", 42.5699556, -2.1924701, 11.7, 6.5, true, true, true, true, true, true),
            new Parada(34, "Sansol", 42.5536319, -2.2669663, 6.5, 0.9, true, true, true, false, false, false),
            new Parada(35, "Torres del Río", 42.5519077, -2.2731861, 0.9, 9.3, true, true, true, false, false, false),
            new Parada(36, "Viana", 42.5210758, -2.3536715, 9.3, 10.2, true, true, true, false, false, false),
            new Parada(37, "Logroño", 42.4744512, -2.4416829, 10.2, 13.1, true, true, true, true, true, true),
            new Parada(38, "Navarrete", 42.4290518, -2.5614262, 13.1, 6.6, true, true, true, true, true, false),
            new Parada(39, "Ventosa", 42.4039922, -2.6252376, 6.6, 10, true, true, true, false, false, false),
            new Parada(40, "Nájera", 42.415757, -2.7303652, 10, 6.1, true, true, true, true, true, true),
            new Parada(41, "Azofra", 42.4235785, -2.8004563, 6.1, 9.2, true, true, true, false, false, false),
            new Parada(42, "Cirueña", 42.4117011, -2.8954536, 9.2, 5.5, true, true, true, false, false, false),
            new Parada(43, "Santo Domingo de la Calzada", 42.4396185, -2.9461281, 5.5, 7.4, true, true, true, true, true, true),
            new Parada(44, "Grañón", 42.449244, -3.0269819, 7.4, 3.9, true, true, true, false, false, false),
            new Parada(45, "Redecilla del Camino", 42.4376438, -3.065428, 3.9, 1.6, true, true, true, false, false, false),
            new Parada(46, "Castildelgado", 42.4369635, -3.08408362, 1.6, 2.1, true, true, false, false, false, false),
            new Parada(47, "Viloria de Rioja", 42.4258371, -3.1012088, 2.1, 3.4, true, true, true, false, false, false),
            new Parada(48, "Villamayor del Rio", 42.427197, -3.1370531, 3.4, 4.8, false, false, true, false, false, false),
            new Parada(49, "Belorado", 42.4207075, -3.1898083, 4.8, 4.7, true, true, true, true, true, true),
            new Parada(50, "Tosantos", 42.4135369, -3.2434293, 4.7, 2.1, false, false, true, false, false, false),
            new Parada(51, "Villambistia", 42.4052679, -3.261524, 2.1, 1.7, false, false, true, false, false, false),
            new Parada(52, "Espinosa del Camino", 42.4060392, -3.2805126, 1.7, 3.5, false, false, true, false, false, false),
            new Parada(53, "Villafranca Montes de Oca", 42.3869408, -3.3091994, 3.5, 12.4, true, true, true, true, true, false),
            new Parada(54, "San Juan de Ortega", 42.377565, -3.437642, 12.4, 3.6, true, true, true, false, false, false),
            new Parada(55, "Agés", 42.3695982, -3.4794578, 3.6, 2.5, false, false, true, false, false, false),
            new Parada(56, "Atapuerca", 42.3764085, -3.5080555, 2.5, 6, true, true, true, false, false, false),
            new Parada(57, "Cardeñuela Riopico", 42.359341, -3.5596227, 6, 2.2, true, false, true, false, false, false),
            new Parada(58, "Orbaneja Riopico", 42.3603006, -3.5853513, 2.2, 3.8, true, true, false, false, false, false),
            new Parada(59, "Villafría", 42.3654699, -3.616125, 3.8, 7.1, true, true, false, false, false, false),
            new Parada(60, "Burgos", 42.344126, -3.694693, 7.1, 11.2, true, true, false, false, false, false),
            new Parada(61, "Tardajos", 42.3489175, -3.8176709, 11.2, 2.1, true, true, true, false, false, false),
            new Parada(62, "Rabé de las Calzadas", 42.3403421, -3.8355013, 2.1, 7.7, false, false, true, false, false, false),
            new Parada(63, "Hornillos del Camino", 42.3388769, -3.9263121, 7.7, 10.1, true, true, true, false, false, false),
            new Parada(64, "San Bol", 42.2866317, -3.9889174, 10.1, 7.9, false, false, true, false, false, false),
            new Parada(65, "Hontanas", 42.3123792, -4.0448338, 7.9, 5.4, true, true, true, false, false, false),
            new Parada(66, "Convento de San Antón", 42.2925216, -4.0991801, 5.4, 3.3, false, false, true, false, false, false),
            new Parada(67, "Castrojeriz", 42.2897829, -4.1366422, 3.3, 9.1, true, true, true, true, true, true),
            new Parada(68, "Ermita de San Nicolás", 42.281539, -4.239780, 9.1, 2.4, false, false, true, false, false, false),
            new Parada(69, "Itero de la Vega", 42.2874443, -4.2584075, 2.4, 8.1, true, true, true, false, false, false),
            new Parada(70, "Boadilla del Camino", 42.2589581, -4.3466966, 8.1, 5.9, true, true, true, true, false, false),
            new Parada(71, "Frómista", 42.2670503, -4.405248, 5.9, 3.7, true, true, true, true, true, false),
            new Parada(72, "Población de Campos", 42.2699296, -4.4464623, 3.7, 3.7, true, true, false, false, false, false),
            new Parada(73, "Revenga de Campos", 42.284053, -4.4825627, 3.7, 2.2, false, false, false, false, false, false),
            new Parada(74, "Villarmentero de Campo", 42.2975476, -4.499834, 2.2, 4.5, true, true, true, false, false, false),
            new Parada(75, "Villalcázar de Sirga", 42.3173136, -4.5426964, 4.5, 5.6, true, true, true, false, false, false),
            new Parada(76, "Carrión de los Condes", 42.3380198, -4.6009934, 5.6, 17.2, true, true, true, true, true, true),
            new Parada(77, "Calzadilla de la Cueza", 42.3285717, -4.804747, 17.2, 6.1, true, true, true, false, false, false),
            new Parada(78, "Ledigos", 42.3543517, -4.8641561, 6.1, 3.2, false, false, true, false, false, false),
            new Parada(79, "Terradillos de los Templarios", 42.3630525, -4.8900628, 3.2, 3.4, true, false, true, false, false, false),
            new Parada(80, "Moratinos", 42.3610805, -4.9270468, 3.4, 2.2, true, true, true, false, false, false),
            new Parada(81, "San Nicolás del Real Camino", 42.3640638, -4.9521732, 2.2, 6.6, false, false, true, false, false, false),
            new Parada(82, "Sahagún", 42.3708177, -5.0284189, 6.6, 10.2, true, true, true, true, true, true),
            new Parada(83, "Bercianos del Real Camino", 42.3874235, -5.1436161, 10.2, 7.5, true, true, true, false, false, false),
            new Parada(84, "El Burgo Ranero", 42.4224281, -5.2190493, 7.5, 13.1, true, true, true, false, false, false),
            new Parada(85, "Reliegos", 42.4740366, -5.355214, 13.1, 5.8, true, false, true, false, false, false),
            new Parada(86, "Mansilla de las Mulas", 42.4946856, -5.4161018, 5.8, 5.1, true, true, true, true, true, true),
            new Parada(87, "Villamoros de Mansilla", 42.5338814, -5.4447415, 5.1, 2.2, false, false, false, false, false, false),
            new Parada(88, "Puente de Villarente", 42.5339578, -5.4493341, 2.2, 3.9, true, true, true, false, false, false),
            new Parada(89, "Arcahueja", 42.5661715, -5.4985595, 3.9, 8.3, true, true, true, false, false, false),
            new Parada(90, "León", 42.5936353, -5.582447, 8.3, 2.7, true, true, true, true, true, true),
            new Parada(91, "Trobajo del Camino", 42.5960901, -5.6080414, 2.7, 4.3, true, true, false, false, false, false),
            new Parada(92, "La Virgen del Camino", 42.5844873, -5.6495205, 4.3, 4.8, true, true, true, true, true, true),
            new Parada(93, "Valverde de la Virgen", 42.568799, -5.6836894, 4.8, 1.4, false, false, false, false, false, false),
            new Parada(94, "San Miguel del Camino", 42.5612296, -5.6974907, 1.4, 7.6, false, false, false, false, false, false),
            new Parada(95, "Villadangos del Páramo", 42.5173456, -5.7663305, 7.6, 4.4, true, true, true, false, false, false),
            new Parada(96, "San Martín del Camino", 42.4944302, -5.8096058, 4.4, 7.1, true, false, true, false, false, false),
            new Parada(97, "Hospital de Órbigo", 42.4633871, -5.8832593, 7.1, 2.5, true, true, true, true, true, true),
            new Parada(98, "Villares de Órbigo", 42.4702179, -5.9098477, 2.5, 2.6, true, false, true, false, false, false),
            new Parada(99, "Santibáñez de Valdeiglesias", 42.4584874, -5.9310897, 2.6, 7.7, true, false, true, false, false, false),
            new Parada(100, "San Justo de la Vega", 42.4536873, -6.0139633, 7.7, 3.8, true, false, true, false, false, false),
            new Parada(101, "Astorga", 42.4582707, -6.054475, 3.8, 2.2, true, true, true, true, true, true),
            new Parada(102, "Valdeviejas", 42.4617926, -6.0793798, 2.2, 2.4, false, false, true, false, false, false),
            new Parada(103, "Murias de Rechivaldo", 42.460609, -6.1056527, 2.4, 4.7, true, true, true, false, false, false),
            new Parada(104, "Santa Catalina de Somoza", 42.4555203, -6.160416, 4.7, 4, true, true, true, false, false, false),
            new Parada(105, "El Ganso", 42.4624001, -6.2074661, 4, 6.8, true, true, true, false, false, false),
            new Parada(106, "Rabanal del Camino", 42.481728, -6.2834592, 6.8, 5.8, true, true, true, true, true, true),
            new Parada(107, "Foncebadón", 42.4915183, -6.3433458, 5.8, 1.9, true, false, true, false, false, false),
            new Parada(108, "Cruz de Ferro", 42.488864, -6.361473, 1.9, 2.3, false, false, false, false, false, false),
            new Parada(109, "Manjarín", 42.4892466, -6.3864987, 2.3, 7.1, false, false, true, false, false, false),
            new Parada(110, "El Acebo de San Miguel", 42.4994104, -6.4578164, 7.1, 3.3, true, true, true, false, false, false),
            new Parada(111, "Riego de Ambrós", 42.5212215, -6.4801022, 3.3, 5.1, true, true, true, false, false, false),
            new Parada(112, "Molinaseca", 42.5375323, -6.5201928, 5.1, 4.6, true, true, true, true, true, true),
            new Parada(113, "Campo", 42.535215, -6.564385, 4.6, 4.9, false, false, false, false, false, false),
            new Parada(114, "Ponferrada", 42.5539048, -6.609852, 4.9, 2.6, true, true, true, true, true, true),
            new Parada(115, "Columbrianos", 42.5721574, -6.611061, 2.6, 3.4, true, true, false, false, false, false),
            new Parada(116, "Fuentesnuevas", 42.569332, -6.645738, 3.4, 2, true, false, true, false, false, false),
            new Parada(117, "Camponaraya", 42.5768273, -6.6655271, 2, 5.9, false, false, false, false, false, false),
            new Parada(118, "Cacabelos", 42.5986821, -6.7248626, 5.9, 2.5, true, true, true, true, true, true),
            new Parada(119, "Pieros", 42.6068853, -6.7502712, 2.5, 2.1, true, false, true, false, false, false),
            new Parada(120, "Valtuille de Arriba", 42.6162255, -6.7653204, 2.1, 4.8, false, false, false, false, false, false),
            new Parada(121, "Villafranca del Bierzo", 42.6079546, -6.8093414, 4.8, 5, true, true, true, true, true, true),
            new Parada(122, "Pereje", 42.6261219, -6.8440464, 5, 4.5, true, false, true, false, false, false),
            new Parada(123, "Trabadelo", 42.6492995, -6.8822539, 4.5, 3.9, true, true, true, true, true, true),
            new Parada(124, "La Portela de Valcarce", 42.6599818, -6.9200342, 3.9, 1.2, true, true, true, false, false, false),
            new Parada(125, "Ambasmestas", 42.665594, -6.928687, 1.2, 1.6, true, true, true, false, false, false),
            new Parada(126, "Vega de Valcarce", 42.6643369, -6.945522, 1.6, 2.3, true, true, true, true, true, true),
            new Parada(127, "Ruitelán", 42.6724333, -6.9669001, 2.3, 1.6, true, false, true, false, false, false),
            new Parada(128, "Las Herrerías", 42.6710632, -6.9830272, 1.6, 2.9, true, true, true, false, false, false),
            new Parada(129, "La Faba", 42.6844202, -7.0078303, 2.9, 2.4, true, false, true, false, false, false),
            new Parada(130, "La Laguna de Castilla", 42.701093, -7.021165, 2.4, 2.4, true, false, true, false, false, false),
            new Parada(131, "O Cebreiro", 42.7078939, -7.0439408, 2.4, 3.4, true, true, true, false, false, false),
            new Parada(132, "Liñares", 42.698174, -7.077549, 3.4, 2.2, true, true, false, false, false, false),
            new Parada(133, "Hospital da Condesa", 42.704799, -7.101073, 2.2, 2.7, false, false, true, false, false, false),
            new Parada(134, "Alto do Poio", 42.71247, -7.126114, 2.7, 3.5, true, true, true, false, false, false),
            new Parada(135, "Fonfría", 42.732035, -7.158598, 3.5, 2.2, true, true, true, false, false, false),
            new Parada(136, "O Biduedo", 42.743216, -7.178115, 2.2, 3, true, true, false, false, false, false),
            new Parada(137, "Fillobal", 42.7441127, -7.2048258, 3, 4, false, false, true, false, false, false),
            new Parada(138, "Triacastela", 42.7570443, -7.2371989, 4, 2.7, true, true, true, true, true, true),
            new Parada(139, "A Balsa", 42.767956, -7.255376, 2.7, 1.9, true, false, true, false, false, false),
            new Parada(140, "San Xil", 42.767635, -7.270404, 1.9, 6.5, false, false, false, false, false, false),
            new Parada(141, "Furela", 42.773423, -7.3269519, 6.5, 1.1, false, false, false, false, false, false),
            new Parada(142, "Pintín", 42.7730477, -7.3387262, 1.1, 1.1, true, true, false, false, false, false),
            new Parada(143, "Calvor", 42.7725409, -7.3510243, 1.1, 2.4, false, false, true, false, false, false),
            new Parada(144, "San Mamede do Camiño", 42.7774161, -7.3739255, 2.4, 3.6, true, false, true, false, false, false),
            new Parada(145, "Sarria", 42.7789661, -7.4114961, 3.6, 4.3, true, true, true, true, true, true),
            new Parada(146, "Barbadelo", 42.769223, -7.444562, 4.3, 3.8, true, true, true, false, false, false),
            new Parada(147, "Molino de Marzán", 42.7722531, -7.4811267, 3.8, 4.7, false, false, true, false, false, false),
            new Parada(148, "Morgade", 42.7821895, -7.521359, 4.7, 1.1, true, true, true, false, false, false),
            new Parada(149, "Ferreiros", 42.7834223, -7.5297497, 1.1, 1.2, true, false, true, false, false, false),
            new Parada(150, "A Pena", 42.785741, -7.543056, 1.2, 3.7, true, false, true, false, false, false),
            new Parada(151, "Mercadoiro", 42.789149, -7.570567, 3.7, 3, true, false, true, false, false, false),
            new Parada(152, "Vilachá", 42.795592, -7.6036956, 3, 2.4, true, false, true, false, false, false),
            new Parada(153, "Portomarín", 42.8081693, -7.6164439, 2.4, 4.5, true, true, true, true, true, true),
            new Parada(154, "Toxibó", 42.8123223, -7.6646652, 4.5, 3.4, false, false, false, false, false, false),
            new Parada(155, "Gonzar", 42.8255094, -7.6958314, 3.4, 1.4, true, false, true, false, false, false),
            new Parada(156, "Castromaior", 42.8316628, -7.7088723, 1.4, 2.5, true, true, false, false, false, false),
            new Parada(157, "Hospital da Cruz", 42.840882, -7.734957, 2.5, 1.6, true, true, true, false, false, false),
            new Parada(158, "Ventas de Narón", 42.8442472, -7.7485157, 1.6, 3.2, true, false, true, false, false, false),
            new Parada(159, "Ligonde", 42.8591726, -7.7801634, 3.2, 3.6, true, true, true, false, false, false),
            new Parada(160, "Lestedo", 42.872181, -7.814297, 3.6, 4.8, true, true, true, false, false, false),
            new Parada(161, "Palas de Rei", 42.8744952, -7.8684764, 4.8, 4, true, true, true, true, true, true),
            new Parada(162, "San Xulián do Camiño", 42.8736762, -7.9079574, 4, 4.8, true, false, true, false, false, false),
            new Parada(163, "Ponte Campaña", 42.875969, -7.918395, 4.8, 1.7, true, false, true, false, false, false),
            new Parada(164, "Casanova", 42.878672, -7.9283491, 1.7, 3.6, false, false, true, false, false, false),
            new Parada(165, "Coto", 42.884764, -7.958450, 3.6, 0.65, true, true, false, false, false, false),
            new Parada(166, "Leboreiro", 42.8873691, -7.9653408, 0.65, 5.3, true, true, false, false, false, false),
            new Parada(167, "Melide", 42.9133679, -8.0151186, 5.3, 5.8, true, true, true, true, true, true),
            new Parada(168, "Boente", 42.9158262, -8.0778573, 5.8, 2.4, true, false, true, false, false, false),
            new Parada(169, "Castañeda", 42.924771, -8.097432, 2.4, 3.1, true, false, true, false, false, false),
            new Parada(170, "Ribadixo da Baixo", 42.930787, -8.130614, 3.1, 3, true, false, true, false, false, false),
            new Parada(171, "Arzúa", 42.926797, -8.163419, 3, 8.6, true, true, true, true, true, true),
            new Parada(172, "Calle", 42.918035, -8.244280, 8.6, 3.3, false, false, false, false, false, false),
            new Parada(173, "Salceda", 42.926659, -8.280127, 3.3, 2.4, true, true, true, false, false, false),
            new Parada(174, "A Brea", 42.918805, -8.305324, 2.4, 2.4, true, true, false, false, false, false),
            new Parada(175, "Santa Irene", 42.9169295, -8.3315079, 2.4, 1.7, true, false, true, false, false, false),
            new Parada(176, "A Rúa (O Pino)", 42.916269, -8.351004, 1.7, 1.6, true, true, false, false, false, false),
            new Parada(177, "O Pedrouzo (O Pino)", 42.905261, -8.361492, 1.6, 3.2, true, true, true, true, true, true),
            new Parada(178, "Amenal", 42.905628, -8.394497, 3.2, 4.1, true, false, true, false, false, false),
            new Parada(179, "San Paio", 42.9090735, -8.4266986, 4.1, 2.4, false, false, false, false, false, false),
            new Parada(180, "Lavacolla", 42.9019635, -8.4501248, 2.4, 4, true, true, false, false, false, false),
            new Parada(181, "San Marcos", 42.892497, -8.488293, 4, 0.7, true, true, false, false, false, false),
            new Parada(182, "Monte do Gozo", 42.889317, -8.493716, 0.7, 4.9, true, false, true, false, false, false),
            new Parada(183, "Santiago de Compostela", 42.8802049, -8.5447697, 4.9, 0, true, true, true, true, true, true)
    ));

    private ArrayList<Parada> listaParadas = new ArrayList<Parada>(this.listaParadasCaminoFrances);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<String> nombresListaParadas = new ArrayList<String>();
        //nombresListaParadas.add("Sin seleccionar");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_camino);

        Iterator<Parada> itr = listaParadas.iterator();

        while (itr.hasNext()) {
            Parada parada = itr.next();

            nombresListaParadas.add(parada.getNombre());
        }

        ArrayAdapter adaptador = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, nombresListaParadas);
        Spinner spinnerInicio = (Spinner) findViewById(R.id.spinnerParadaInicio);
        Spinner spinnerFin = (Spinner) findViewById(R.id.spinnerParadaFin);
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
        Intent i = new Intent(AccionCaminoNuevo.this, AccionCaminoActual.class);
        startActivity(i);
    }


    public void miCamino(View view) {
        String nombre = (((EditText) findViewById(R.id.editTextNombreCamino)).getText()).toString();
        String comienzo = (((Spinner) findViewById(R.id.spinnerParadaInicio)).getSelectedItem()).toString();
        String fin = (((Spinner) findViewById(R.id.spinnerParadaFin)).getSelectedItem()).toString();
        int dias = 0;
        int kmDia = Integer.parseInt((((EditText) findViewById(R.id.editTextKmMax)).getText()).toString());
        boolean correcto = true;
        ArrayList<Etapa> etapas;
        Camino camino;

        if (((EditText) findViewById(R.id.editTextDias)).getText().toString().compareTo("") == 0) {
            correcto = false;
            Toast.makeText(this, "Introduzca un número de días mayor a 1.", Toast.LENGTH_SHORT).show();
        } else {
            dias = Integer.parseInt(((EditText) findViewById(R.id.editTextDias)).getText().toString());
        }

        if (comienzo.compareTo("Sin seleccionar") == 1 && fin.compareTo("Sin seleccionar") == 1 && dias > 0) {
            Toast.makeText(this, "Si selecciona ciudad inicio y ciudad fin, no introduzca número de días.", Toast.LENGTH_SHORT).show();
            correcto = false;
        }


        if (nombre.compareTo("") == 0) {
            Toast.makeText(this, "Introduzca un nombre para su nuevo camino.", Toast.LENGTH_SHORT).show();
            correcto = false;
        }
        if ((((EditText) findViewById(R.id.editTextKmMax)).getText()).toString().compareTo("") == 0) {
            Toast.makeText(this, "No ha introducido número de km diarios. Asignada su distancia recomendada de " + usuarioSeleccionado.getKmMaximos() + " km.", Toast.LENGTH_SHORT).show();
        }

        if (correcto) {
            //Comprobar donde recibe usuario seleccionado y como construye etapas y caminos
            etapas = calculaEtapas(dias, comienzo, fin, nombre, kmDia);
            camino = new Camino(nombre, etapas, kmDia);
            usuarioSeleccionado.addCamino(camino);
            Intent i = new Intent(AccionCaminoNuevo.this, AccionCaminoActual.class);
            i.putExtra("usuarioSeleccionado", (Serializable) usuarioSeleccionado);
            startActivity(i);
        } else {
            Toast.makeText(this, "Introduzca todos los datos del formulario.", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(AccionCaminoNuevo.this, AccionCaminoNuevo.class);
            i.putExtra("usuarioSeleccionado", (Serializable) usuarioSeleccionado);
            startActivity(i);
        }
    }

    public ArrayList<Etapa> calculaEtapas(int dias, String comienzoCamino, String finCamino, String nombreCamino, int kmMax) {
        usuarioSeleccionado = (Usuario) getIntent().getSerializableExtra("usuarioSeleccionado");
        String inicioEtapa, finEtapa;
        ArrayList<Etapa> listaEtapas = new ArrayList<Etapa>();
        Double km = 0.0;
        boolean semaforo = true;
        int ordenParada = 1;

        //Cuando el usuario elige ciudad inicio y ciudad final no se calcula en función de los dias
        if (dias == 0) {
            dias = 999999999;
        }

        boolean comp = false;
        Iterator<Parada> itr = this.listaParadas.iterator();

        while (itr.hasNext() && !comp) {
            Parada parada = itr.next();

            comp = (parada.getNombre()).equals(comienzoCamino);

            if (comp) {
                ordenParada = parada.getOrden();
            }
        }

        //Mientras queden dias o no se alcance la ciudad final
        while (dias > 0 || this.listaParadas.get(ordenParada).getNombre().equals(finCamino)) {
            inicioEtapa = this.listaParadas.get(ordenParada).getNombre();
            semaforo = true;

            while (semaforo) {
                if (this.listaParadas.get(ordenParada).getDistSiguiente() + km <= kmMax) {
                    km += this.listaParadas.get(ordenParada).getDistSiguiente();
                    ordenParada++;
                } else {
                    semaforo = false;
                }
            }

            finEtapa = this.listaParadas.get(ordenParada).getNombre();
            listaEtapas.add(new Etapa(km, inicioEtapa, finEtapa));
            km = 0.0;
            dias--;
        }

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