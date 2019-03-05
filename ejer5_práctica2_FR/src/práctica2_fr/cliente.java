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

public class cliente {

    static public PrintWriter outPrinter;
    static public BufferedReader inReader;
    
    static Socket socketServicio;
    
    public static void main(String[] args){
        String host= "localhost";
        int port= 8989;
        
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
            inReader= new BufferedReader(new InputStreamReader(inputStream));
            outPrinter= new PrintWriter(outputStream, true);
            
           
            do{
                procesar();
            }while(true);
            
            
        }catch(UnknownHostException e){
            System.err.println("Error: nombre de host no encontrado");
        }catch(IOException e){
            System.err.println("Error de entrada/salida al abrir el socket");
        }       
    }
    
    public static String leerNumeroCuenta(){
            String numCuenta;
            Scanner s = new Scanner(System.in);
            // Hasta que no introduzca un entero de ocho dígitos
            do{
                System.out.println("Introduzca su número de tarjeta");
                numCuenta =  s.nextLine();
                
                
                if(numCuenta.length()!=8) System.out.println("Error: el número de tarjeta debe tener 8 dígitos.");
      
            } while(numCuenta.length() != 8);
            return numCuenta;
    }
    
    //funcion que lee 4 digitos seguidos
    public static String leerPin(){
        String pin;
        Scanner s = new Scanner(System.in);
        do{
            pin= s.nextLine();
        }while(pin.length() != 4);
        return pin;
    }
    
    public static void procesar(){
        Scanner s= new Scanner(System.in);
        String opcion;
        
        do{
            System.out.println("Introduzca 0 si quiere entrar en su cuenta o 1 si desea crear una.");
            opcion= s.nextLine();
        }while(!opcion.equals("0") && !opcion.equals("1"));
        
        String[] respuesta;
        
        if(opcion.equals("1")){
            // Quiere registrar una cuenta nueva
            String numCuenta = leerNumeroCuenta();
            String p1, p2, n;
            System.out.println( "Introduzca el pin 1 que quiera poner: " );
            p1 = leerPin();
            System.out.println( "Introduzca el pin 2 que quiera poner: " );
            p2 = leerPin();
            System.out.println( "Introduzca su nombre: " );
            n =  s.nextLine();
            //Ya sabemos el codigo, y lo que tenemos que mandar.
            protocolo.emitPacket(outPrinter, protocolo.NUEVACUENTA, numCuenta + "//" + p1 + "//" + p2 + "//" + n );
            System.out.println("dd");
            respuesta = protocolo.receivePacket(inReader);
            if( Integer.parseInt(respuesta[0]) == protocolo.OK ) {
                System.out.println( "Cuenta creada correctamente." );
            } else {
                System.out.println("Error: " + respuesta[1]);
            }
        } else {
            //Entonces quiere acceder a su cuenta
            String numCuenta = leerNumeroCuenta();
            System.out.println( "Emitimos num cuenta..." );
            protocolo.emitPacket(outPrinter, protocolo.LOGINCUENTA, numCuenta);
            respuesta = protocolo.receivePacket(inReader);
            if( Integer.parseInt( respuesta[0] ) == protocolo.OK ) {
                System.out.println( "Logeado correctamente." );                
                do{
                    System.out.println("Introduzca 0 (sacar dinero) 1 (ingresar dinero)");
                    opcion= s.nextLine();
                }while(!opcion.equals("0") && !opcion.equals("1"));

                System.out.println( "Introduzca la cantidad que desee ingresar o sacar:" );
                String cantidad = s.nextLine();


                if( opcion.equals("0") ){
                    protocolo.emitPacket(outPrinter, protocolo.SACAR, cantidad);
                    respuesta = protocolo.receivePacket( inReader );
                    if( Integer.parseInt(respuesta[0]) == protocolo.OK ) 
                        System.out.println( "Extracto realizado correctamente." );
                    else if( Integer.parseInt(respuesta[0]) == protocolo.ERRORFONDOS ) 
                            System.out.println( "Error: " + respuesta[1] );
                    
                    if( Integer.parseInt(respuesta[0]) == protocolo.PEDIRPIN1){
                        System.out.println("Pidiendo pin1...");
                        String pin1;
                        do{
                            System.out.println("Introduzca su pin 1: ");
                            pin1= leerPin();
                            protocolo.emitPacket(outPrinter, protocolo.SACARCONPIN1, pin1);  
                            respuesta = protocolo.receivePacket( inReader );
                            System.out.println( "Estado pin: " + respuesta[1] );
                        } while( Integer.parseInt( respuesta[0] ) == protocolo.PIN1INCORRECTO  );
                        
                        if( Integer.parseInt( respuesta[0] ) == protocolo.DISCONNECT ) {
                            System.out.println( "Agotado número de intentos, abortando..." );
                            try {socketServicio.close();}catch(Exception e){};
                            System.exit(-1);
                        }
                        if( Integer.parseInt( respuesta[0] ) == protocolo.OK ) {
                            System.out.println( "OK:" + respuesta[1] );
                        }
                    }
                    
                    if( Integer.parseInt(respuesta[0]) == protocolo.PEDIRPIN2){
                        System.out.println("Pidiendo pin1...");
                        String pin2;
                        do{
                            pin2= leerPin();
                            protocolo.emitPacket(outPrinter, protocolo.SACARCONPIN2, pin2);  
                            respuesta = protocolo.receivePacket( inReader );
                            System.out.println( "Estado pin: " + respuesta[1] );
                        } while( Integer.parseInt( respuesta[0] ) == protocolo.PIN2INCORRECTO  );
                        
                        if( Integer.parseInt( respuesta[0] ) == protocolo.DISCONNECT ) {
                            System.out.println( "Agotado número de intentos, abortando..." );
                            try {socketServicio.close();}catch(Exception e){};
                            System.exit(-1);
                        }
                        if( Integer.parseInt( respuesta[0] ) == protocolo.OK ) {
                            System.out.println( "OK:" + respuesta[1] );
                        }
                    }
                    
                    
                } else {
                    protocolo.emitPacket(outPrinter, protocolo.INGRESAR, cantidad);
                    respuesta = protocolo.receivePacket( inReader );
                    if( Integer.parseInt( respuesta[0] ) == protocolo.OK ) {
                        System.out.println( "Ingreso realizado correctamente." + respuesta[1] );
                    } else {
                        System.out.println( "Error: " + respuesta[1] );
                    }
                }            
            } else {
                System.out.println( "Error: " + respuesta[1] );
            }
        }
    }
}