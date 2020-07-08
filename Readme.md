
Servidor y cliente FTP, elaborado por Jordan Castillo Y José Quirós

Requerimentos para ejecutar correctamente este proyecto:

Java Development Kit (8 o superior):
     https://www.oracle.com/java/technologies/javase-downloads.html

Se puede utilizar cualquier ide de desarrollo que utilice java y en este caso, ejecute dicho lenguaje, nosotros para la utilizacion y elaboracion de este proyecto utilizamos

Netbeans 8.2: https://www.oracle.com/technetwork/es/java/javase/downloads/jdk-netbeans-jsp-3413139-esa.html

Ahora si, teniendo esto procederemos a descargar el proyecto, para ello es necesario darle en el boton COPY y luego DOWNLOAD ZIP 

![redesDescargar](https://user-images.githubusercontent.com/37676810/86641294-44e4c900-bf98-11ea-81c3-33b27b7f85ac.png)


Al tener el zip en nuestro almacenamiento correspondiente, procederemos a descomprimir y obtendremos los siguientes archivos

![Proyecto-Redes--master 6_7_2020 14_23_33](https://user-images.githubusercontent.com/37676810/86641404-575f0280-bf98-11ea-8cbd-e433c60aba62.png)


Luego de esto, abriremos Netbeans 8.2 y daremos en la sgte opcion para cargar los proyectos

![ServerFTP - NetBeans IDE 8 2 6_7_2020 14_34_03](https://user-images.githubusercontent.com/37676810/86641457-61810100-bf98-11ea-8a0d-49a89c8bcfa0.png)

Luego abriremos cada proyecto como lo indica la imagen

![ServerFTP - NetBeans IDE 8 2 6_7_2020 14_30_29](https://user-images.githubusercontent.com/37676810/86641508-6b0a6900-bf98-11ea-973c-83ea9e876faa.png)

Una vez cargados los proyectos los veremos de la sgte manera

![ServerFTP - NetBeans IDE 8 2 6_7_2020 14_37_48](https://user-images.githubusercontent.com/37676810/86641550-73fb3a80-bf98-11ea-9158-5757bcb2c2fc.png)

Como mencionamos en la descripcion el proposito de este proyecto es realizar un respaldo de archivos en el servidor, el cual recibe archivos desde un cliente que se encuentra registrado, Este cliente se encuentra almacenado en una base de datos Mysql creada por nosotros los desarrolladores, para su conexion desde java se necesita un Java Database Connectivity, más conocida por sus siglas JDBC, es una API que permite la ejecución de operaciones sobre bases de datos desde el lenguaje de programación Java.
Para facilidad nosotros suministramos las librerias en la misma carpeta del proyecto, para instalarlas es necesario :

![ServerFTP - NetBeans IDE 8 2 6_7_2020 14_44_51](https://user-images.githubusercontent.com/37676810/86641616-82e1ed00-bf98-11ea-8435-0e4bcda8d0a0.png)

Luego de esto procederemos a seleccionar los 2 archivos jar que se encuentran en la carpeta y darle a la opcion abrir

![ServerFTP - NetBeans IDE 8 2 6_7_2020 14_49_03](https://user-images.githubusercontent.com/37676810/86641662-8b3a2800-bf98-11ea-8363-15124321a601.png)

Para la utilizacion de esto es necesario configurar la conexion a base de datos de la siguiente manera

public class BaseDeDatos {
   String URL = "jdbc:mysql://163.178.107.10/"; // Ubicación de la BD.
    String BD = "PROYECTO1REDES"; // Nombre de la BD.
    String USER = "-----";
    String PASSWORD = "---- ";
    String a="?useSSL=false";
    public Connection conexion = null;

    @SuppressWarnings("finally")
    public Connection conectar() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(URL + BD+a, USER, PASSWORD);
            if (conexion != null) {
                System.out.println("¡Conexión Exitosa!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return conexion;
        }
    }
}

Concluido todo esto, se tiene lo necesario para ejecutar el proyecto, para saber sobre su utilizacion dirigirse al manual de usuario

