/**
 *
 * @author elena
 */

package práctica2_fr;

import java.util.ArrayList;
import java.util.Random;

public class Afiliado {
    private static int nAfiliado= 0;
    private String nTarjeta; // número de tarjeta del cliente 
    private String pin1; // pin usado para operaciones de extracción de dinero menores a 300 euros
    private String pin2;
    private ArrayList<String> nombre = new ArrayList<>();
    private Random random;
    private double dinero;
    
    // Mantenemos arrays con los pines1 y tarjetas asociadas a los clientes para evitar repeticiones
    private static ArrayList<String > pines1 = new ArrayList<>();
    private static ArrayList<String> tarjetas = new ArrayList<>();
    
    // Para crear un afiliado a partir de su número de tarjeta, pin1, pin2, nombre y apellidos
    public Afiliado(String n, String p1, String p2, ArrayList<String> s, double d){
        // Nos aseguramos de que las tarjetas tienen 8 dígitos, los pines 4 y se introducen nombre y dos apellidos
        assert(n.length() == 8 && p1.length() == 4 && p2.length() == 4 && s.size() == 3);
        // Obligamos también a que no se introduzca un pin y número de tarjeta que estén en uso, para mayor seguridad
        assert(!pines1.contains(p1) && !tarjetas.contains(n));
        assert(d >= 0);
        
        nTarjeta= n;
        pin1= p1;
        pin2= p2;
        
        nombre= new ArrayList<>();
        nombre.addAll(s);
        
        dinero= d;
        
        // Añadimos en la posición nCliente los pines y número de tarjeta 
        pines1.add(nAfiliado, pin1);
        tarjetas.add(nAfiliado, nTarjeta);
        
        nAfiliado++;
    }
    
    // Para crear un afiliado a partir de su nombre
    public Afiliado(ArrayList<String> s){
        nombre= new ArrayList<>();
        nombre.addAll(s);
        random= new Random();
        // Asignamos un pin1 y pin2
        do{
            pin1= null;
            pin2= null;
            for(Integer i= 0; i< 4; i++){
                pin1 += "";
                pin1 += random.nextInt(10);
                pin2 += "";
                pin2 += random.nextInt(10);
            }
        }while(pines1.contains(pin1));
        pines1.add(nAfiliado, pin1);
       
        // Asignamos número de tarjeta 
        do{
            for(Integer i= 0; i< 8; i++)
                nTarjeta = nTarjeta + Integer.toString(random.nextInt(10));      
        }while(tarjetas.contains(nTarjeta));
        tarjetas.add(nAfiliado, nTarjeta);  
        dinero= 0;
        
        nAfiliado++;
    }
    
    public void cambiarPin1(String newPin){
        pin1= null;
        pin1= newPin;
    }
    
    public void cambiarPin2(String newPin){
        pin2= null;
        pin2= newPin;
    }
    
    public void sacarDinero(float cantidad){
            dinero -= cantidad;
    }
    
    public void meterDinero(float cantidad){
        dinero += cantidad;
    }
    
    public void consultarDinero(){
        System.out.println("\nTiene " + dinero + " euros.");
    }
    
    public void getNombre(){
        System.out.println(nombre.get(0) + " " + nombre.get(1) + " " + nombre.get(2));
    }

    public String getnTarjeta() {
        return nTarjeta;
    }

    public double getDinero() {
        return dinero;
    }
    
    public String getPin1(){
        return pin1;
    }
    
    public String getPin2(){
        return pin2;
    }
}