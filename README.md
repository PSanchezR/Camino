Camino
======


### Autores

Germán Martínez Maldonado  
Pablo Sánchez Robles

### Breve descripción

Aplicación para viajeros del Camino de Santiago que permite generar rutas personalizadas, dando información sobre las distintas paradas en el camino. Además, ofrece la posibilidad de realizar fotos que pueden ser visionadas y situadas en un mapa desde cualquier dispositivo Android.

### Requerimentos

La aplicación usa el servicio web de Google para obtener los mapas de Google Maps, usando estos mapas para mostrar las rutas generadas y posicionar las fotos hechas en la misma aplicación.  

También se conecta a un servidor propio por FTP mediante la librería Apache Commons Net para subir y descargar las fotos que se hacen con la propia aplicación, esta librería ya está incluida en la aplicación dado que su licencia librería Apache License v2 es compatible con la licencia GNU GPL v3 de nuestra aplicación, permitiendo ambas la libre distribución.  

Para usar ambas funcionalidades lo único necesario es de disponer de conexión a Internet desde el dispositivo móvil.

