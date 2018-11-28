/**
 *
 * @author elena
 */

package práctica2_fr;

import java.util.ArrayList;
import java.util.Random;

public class Afiliado {
    private static int nAfiliado= 0;
    private ArrayList<Integer> nTarjeta; // número de tarjeta del cliente 
    private ArrayList<Integer> pin1; // pin usado para operaciones de extracción de dinero menores a 300 euros
    private ArrayList<Integer> pin2;
    private ArrayList<String> nombre;
    private Random random;
    private double dinero;
    
    // Mantenemos arrays con los pines1 y tarjetas asociadas a los clientes para evitar repeticiones
    private static ArrayList<ArrayList<Integer> > pines1;
    private static ArrayList<ArrayList<Integer> > tarjetas;
    
    // Para crear un afiliado a partir de su número de tarjeta, pin1, pin2, nombre y apellidos
    public Afiliado(ArrayList<Integer> n, ArrayList<Integer> p1, ArrayList<Integer> p2, ArrayList<String> s, double d){
        // Nos aseguramos de que las tarjetas tienen 8 dígitos, los pines 4 y se introducen nombre y dos apellidos
        assert(n.size() == 8 && p1.size() == 4 && p2.size() == 4 && s.size() == 3);
        // Obligamos también a que no se introduzca un pin y número de tarjeta que estén en uso, para mayor seguridad
        assert(!pines1.contains(p1) && !tarjetas.contains(n));
        assert(d >= 0);
        
        nTarjeta= new ArrayList<>();
        nTarjeta.addAll(n);
        
        pin1= new ArrayList<>();
        pin1.addAll(p1);
        
        pin2= new ArrayList<>();
        pin2.addAll(p2);
        
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
            pin1.clear();
            pin2.clear();
            for(Integer i= 0; i< 4; i++){
                pin1.add(random.nextInt(10));
                pin2.add(random.nextInt(10));
            }
        }while(pines1.contains(pin1));
        pines1.add(nAfiliado, pin1);
       
        // Asignamos número de tarjeta 
        do{
            nTarjeta.clear();
            for(Integer i= 0; i< 8; i++)
                nTarjeta.add(random.nextInt(10));      
        }while(tarjetas.contains(nTarjeta));
        tarjetas.add(nAfiliado, nTarjeta);  
        dinero= 0;
        
        nAfiliado++;
    }
    
    public void cambiarPin1(ArrayList<Integer> newPin){
        pin1.clear();
        pin1.addAll(newPin);
    }
    
    public void cambiarPin2(ArrayList<Integer> newPin){
        pin2.clear();
        pin2.addAll(newPin);
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
}
