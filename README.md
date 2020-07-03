# Afip Manager Proyect

Este es el primer prototipo de un proyect personal que tiene como objetivo ayudar a los contadores
particulares en el uso de elementos de AFIP para no tener que realizar ciertas cosas de manera manual.



## Usage
Es una aplicación desarrollada en Java version 8, con una interfaz grafica en JavaFx.
Se utiliza internamente SQL de manera local para el manejo y el guardado de informacion con la intencion de posteriormente actualizarlo  a SQL Server.

La intension de la aplicacion es el uso de los duplicados electronicos generador por AFIP. El contador o persona responsable baja el duplicado electornico mensual
de las ventas de su cliente. Con la aplicacion carga ese duplicado electronico y la aplicacion lo almacena en una base de datos y genera un resumen mensual con el total
de las ventas generadas por el cliente en el mes, al mismo tiempo se puede ver las especificaciones de cada mes factura por factura.

El Proyecto esta dividido internamente en 3 carpetas:
  Una carpeta que contiene las planillas visuales de JavaFx con su respectivo controlador.
  Una carpeta model que contiene el modelado de clases para este proyecto y una clase datasource que contiene la interaccion con     SQL.
  Una carpeta que contiene los elementos de modelado con CSS.
  Por ultimo se encuentra la clase Main y una clase global llamada GlobalManagment que permite usar algunos controles globales


## Contributing
Cualquier contribucion a la misma, como mejor utilizacion del codigo, al igual que recomendaciones son mas que bienvenidas
es un primer desarrollo de la misma.
Muchas gracias.

## Next Patch
-Desarrollar un instalador del mismo.
-Modificarlo para trasladarlo a la version 9 de Java.
