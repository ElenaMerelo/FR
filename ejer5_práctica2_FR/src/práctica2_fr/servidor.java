/**
 *
 * @author elena
 */

package práctica2_fr;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;


public class servidor {
    
    public static void main(String [] args){
        System.out.println("\nBienvenido a elebank.");
        int port= 8989;
        
        //Abrimos el socket en modo pasivo, escuchando en el puerto indicado 
        ServerSocket socketServidor;
        
        try{
            socketServidor= new ServerSocket(port);
            Socket socketConexion= null;
            
            
            //=====Inicializamos la clase banco=====.
            Afiliado cliente1= new Afiliado("12345678","1234", "1357", 
                               new ArrayList<>(Arrays.asList("Elena", "Merelo", "Molina")), 23.23);

            Afiliado cliente2= new Afiliado(new ArrayList<>(Arrays.asList("Elena", "Nito", "Del Bosque")));
            
            Banco.clientes= new ArrayList<>();
            Banco.clientes.add(cliente1);
            Banco.clientes.add(cliente2);
                
            //====================================
            // Logica del servidor
            do{
                try{
                    // Aceptamos nueva conexión
                    socketConexion= socketServidor.accept();   
                } catch(Exception e){
                    System.out.println("\nError: no se pudo aceptar la conexión");
                } finally{
                    Thread.sleep(1000);
                }

                // Creamos un objeto de la clase banco, pasándole como argumento el nuevo socket, 
                // para que realice el procesamiento de los clientes que lleguen. 
                Banco elebank= new Banco(socketConexion);
                elebank.start();
                System.out.println("Nuevo cliente conectado...");
            } while (true);
            
        }catch(Exception e){
            System.err.println("Error al escuchar en el puerto " + port);
            e.printStackTrace();
        }
    }
}