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
            
            Afiliado cliente1= new Afiliado(new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8)),
                               new ArrayList<Integer>(Arrays.asList(1,2,3,4)), 
                               new ArrayList<Integer>(Arrays.asList(1,3,5,7)), 
                               new ArrayList<String>(Arrays.asList("Elena", "Merelo", "Molina")),
                               23.23);

            Afiliado cliente2= new Afiliado(new ArrayList<String>(Arrays.asList("Elena", "Nito", "Del Bosque")));
            ArrayList<Afiliado> misClientes= new ArrayList<>();
            misClientes.add(cliente1);
            misClientes.add(cliente2);
                
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
                Banco elebank= new Banco(socketConexion, misClientes);
                elebank.start();
            } while (true);
            
        }catch(Exception e){
            System.err.println("Error al escuchar en el puerto " + port);
            e.printStackTrace();
        }
    }
}
