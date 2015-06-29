Camino
======


### Autores

Germán Martínez Maldonado  
Pablo Sánchez Robles

### Breve descripción

Aplicación para viajeros del **Camino de Santiago** que permite generar rutas personalizadas, dando información sobre las distintas paradas en el camino. Además, ofrece la posibilidad de realizar fotos que pueden ser visionadas y situadas en un mapa desde cualquier dispositivo **Android**.

### Requerimientos

La aplicación usa el **servicio web de Google** para obtener los mapas de **Google Maps**, usando estos mapas para mostrar las rutas generadas y posicionar las fotos hechas en la misma aplicación.  

También se conecta a un servidor propio por **FTP** mediante la librería **Apache Commons Net** para subir y descargar las fotos que se hacen con la propia aplicación, esta librería ya está incluida en la aplicación dado que su licencia librería **Apache License v2** es compatible con la licencia **GNU GPL v3** de nuestra aplicación, permitiendo ambas la libre distribución.  

Si disponemos de un smartwatch podremos usar la función de notificación cuando seleccionamos una etapa dentro del menú **"Camino actual"**, enviándose una notificación al smartwatch indicando la distancia desde nuestra posición inicial hasta nuestro destino.

Para poder usar todas las funcionalidades lo único necesario es de disponer de **conexión a Internet** desde el dispositivo móvil y tener instaladas las aplicaciones **Google Play Services** y **Android Wear**.

