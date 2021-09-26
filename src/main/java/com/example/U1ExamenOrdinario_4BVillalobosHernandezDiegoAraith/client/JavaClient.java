package com.example.U1ExamenOrdinario_4BVillalobosHernandezDiegoAraith.client;

import com.example.U1ExamenOrdinario_4BVillalobosHernandezDiegoAraith.database.User;
import com.example.U1ExamenOrdinario_4BVillalobosHernandezDiegoAraith.server.Handler;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class JavaClient {
    public static void main( String[] args )
            throws MalformedURLException, XmlRpcException {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        int opc=0,status;
        boolean result;
        String name,lastname,email,password;
        Handler handler = new Handler();

        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL( new URL( "http://localhost:1200" ) );
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig( config );//se le pasa la configuración de donde se encuentra el servidor con el servicio a consumir

        //Programa
        System.out.println("Bienvenido al CRUD de usuarios!");
        while (opc!=5){
            System.out.println("Menú:\n1-Insertar nuevo usuario\n2-Listar usuarios\n3-Modificar usuario\n4-Eliminar usuario\n5-Salir");
            System.out.print("opción: ");
            opc = sc.nextInt();

            switch (opc){
                case 1://insertar
                    System.out.println("Nuevo usuario\nIntroduce nombre(s): ");
                    name = sc.next();
                    System.out.println("Introduce apellidos");
                    lastname = sc.next();
                    System.out.println("Introduce email(será tu clave para la opción 3 y 4 del menú): ");
                    email = sc.next();
                    System.out.println("Introduce tu contraseña: ");
                    password = sc.next();
                    System.out.println("Introduce el status (0-inactivo,1-activo): ");
                    status=sc.nextInt();

                    result = (boolean) client.execute( "Handler.createUser", new Object[]{ (Object) name, (Object)lastname,(Object)email, (Object) password,(Object) status});
                    System.out.println(result?"Inserción correcta":"Algo salió mal en la inserción intentalo mas tarde");
                    break;
                case 2://listar
                    System.out.println("\nListado-------------------------------");
                    for (User user: handler.findAll()){
                        System.out.print("Nombre:"+user.getName()+" Apellido:"+user.getLastname());
                        System.out.println("\nEmail:"+user.getEmail()+" Fecha_registro:"+user.getDateRegistered()+" status:"+((user.getStatus()==1)?"activo":"inactivo"));
                        System.out.println("---------");
                    }
                    System.out.println("Listado completado");
                    break;
                case 3://modificar
                    System.out.println("Actualización\nIntroduce email(no modificable, a quien: ");
                    email = sc.next();
                    System.out.println("Introduce nombre(s): ");
                    name = sc.next();
                    System.out.println("Introduce apellidos");
                    lastname = sc.next();
                    System.out.println("Introduce el status (0-inactivo,1-activo): ");
                    status = sc.nextInt();
                    result = (boolean) client.execute( "Handler.updateUser", new Object[]{ (Object) email, (Object) name, (Object) lastname, (Object) status});
                    System.out.println(result?"Actualización correcta":"Algo salió mal en la actualización, intentalo mas tarde");
                    break;
                case 4://eliminar
                    System.out.println("Eliminación\nIntroduce email de la persona a eliminar: ");
                    email = sc.next();
                    result = (boolean) client.execute( "Handler.deleteUser", new Object[]{ (Object) email});
                    System.out.println(result?"Eliminación correcta":"Algo salió mal en la eliminación, intentalo mas tarde");
                    break;
                case 5:
                    System.out.println("Nos vemos! ");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }

        }

    }
}