/**
 *
 * @author elena
 */

package práctica2_fr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.ArrayList;

public class cliente {
    public static void main(String[] args){
        String mensaje, respuesta;
        String host= "localhost";
        int port= 8989;
        Socket socketServicio= null;
        
        try{
            // Creamos un socket que se conecte a host y port 
            try{
                socketServicio= new Socket(host, port);
            }catch( UnknownHostException e){
                System.err.println("Error: equipo desconocido");
            }catch(IOException e){
                System.err.println("Error: no se pudo establecer la conexión");
            }
            
            InputStream inputStream= socketServicio.getInputStream();
            OutputStream outputStream= socketServicio.getOutputStream();

            //Obtenemos los flujos de lectura y escritura del socket, en modo texto
            BufferedReader inReader= new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter outPrinter= new PrintWriter(outputStream, true);
            
            procesar();

            do{
                // Mandamos el mensaje por el outPrinter
                outPrinter.println(mensaje);
                outPrinter.flush();
                respuesta= inReader.readLine();

                //Mostramos lo recibido
                System.out.println("Recibido: ");
                System.out.println(respuesta);


                // Una vez terminado el servicio, cerramos el socket (automáticamente se cierran
                // el inpuStream  y el outputStream)
                //socketServicio.close();
            }while(true);
            
            
        }catch(UnknownHostException e){
            System.err.println("Error: nombre de host no encontrado");
        }catch(IOException e){
            System.err.println("Error de entrada/salida al abrir el socket");
        }       
    }
    
    public static void procesar(){
        Scanner s= new Scanner(System.in);
        String opcion;
        
        do{
            System.out.println("Introduzca 0 si quiere entrar en su cuenta o 1 si desea crear una.");
            opcion= s.nextLine();
        }while(!opcion.equals("0") && !opcion.equals("1"));
        
        if(opcion.equals("0")){
            // Hasta que no introduzca un entero de ocho dígitos
            do{
                System.out.println("Introduzca su número de tarjeta");
                int n= s.nextInt(), m= n, ndigits= 0;

                while( n > 0){
                    n /= 10;
                    ndigits++;
                }
                
            }while(ndigits != 8);
            
            ArrayList<Integer> numeroTarjeta= new ArrayList<>();
            for(int i= 0; i< 8; i++){
               numeroTarjeta.add(8-i, n % 10);
               n /= 10;
            }
                
        }
    }
}
