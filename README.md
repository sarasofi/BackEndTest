# BackEndTest
Esta es la solucion propuesta a la prueba tecnica, Almacen para realizar pagos en linea, creado en Spring boot usando JAVA

Este proyecto fue creado con la herramienta Spring Tool, presenta una api para la solución de 3 problemas planteados.
Historias de usuario:
2.1 Como dueño del almacén quiero que mis clientes puedan hacer pedidos en línea
Solución: Se implementó una api de tipo REST, en donde por medio de los métodos HTTP, se puede:
1. Ingresar usuario, es lo primero que se debe hacer.
2. Ingresar productos, cuando se terminan de ingresar los productos se puede continuar con el siguiente paso.
3. Generar factura.
2.2 Editar el pedido si fue hecho antes de 5 horas.
Solución: Basado en la solución anterior se verifica a que hora se realizó el pedido y por medio de una función se determina si es posible editar la solicitud, para esto
se usaron los siguientes métodos:
1. verificar que se haya generado la factura no antes de 5 horas en comparación a la hora que se presenta la solicitud.
2. Generar el cambio.
3. Si se desea ver el nuevo valor total solo se debe solicitar usando el método implementado en la solución anterior (Generar factura).

2.2 Eliminar el pedido si fue hecho antes de 12 horas, sino aplicar multa.
Solución: Utilizando los datos obtenidos por la solución uno, cuando se llama a este método se eliminan los productos, y solo se verifica si la solicitud es hecha dentro del
rango de 12 horas establecidas, de lo contrario el nuevo valor a pagar es el 10% del valor total que fue cancelado.

Siempre para poder ver la factura del cliente, se debe llamar al método obtener factura.
Para probar el codigo se debe crear una base de datos sin tablas que se llame 'products', se crea con este comando en mysql "create database products;", y en el archivo src/main/resources/aplication.yml se debe configurar los valores para establecer conexión con la base de datos, la url, usuario y contraseña.
