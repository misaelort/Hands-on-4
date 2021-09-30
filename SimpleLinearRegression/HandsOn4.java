package examples.SimpleLinearRegression;

import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.*;


public class HandsOn4 extends Agent {


	private Interfaz myGui;  //Objeto de mi interfaz
	double a,b;		  //Valores globales de b0 y b1
	
	
	protected void setup() {
	
	myGui = new Interfaz(this);
		
		// Add the behaviour serving queries from buyer agents
		//comportamiento
		addBehaviour(new RegresionLineal());
		
	}
	
	private class RegresionLineal extends OneShotBehaviour{
	
	public void action(){
	
	   int[] x = {23,26,30,34,43,48,52,57,58};
           int[] y = {651,762,856,1063,1190,1298,1421,1440,1518};
   
        System.out.println("Actividad de regresion lineal");


        int sum_xy = 0, sum_x = 0, sum_y = 0, sum_x2 = 0;  

        for(int actual = 0; actual < 9; actual++)
        {
            sum_xy += x[actual] * y[actual];
            sum_x  += x[actual];
            sum_y  += y[actual];
            sum_x2 += Math.pow(x[actual], 2);
        }
        
        System.out.println("Calculando valor de a (Punto de Corte)");

         a = (double)(((sum_y * sum_x2) - (sum_x * sum_xy))/ ((9 * sum_x2) - Math.pow(sum_x, 2)));

        System.out.println("Listo! El valor de a es: " + a);
        
        System.out.println("Calculando valor de b (Pendiente)");

         b = (double)(((9 * sum_xy) - (sum_x * sum_y)) / ((9 * sum_x2) - Math.pow(sum_x,2)));   
        
        System.out.println("Listo! El valor de b es: " + b);
        
          	
         //Imprimiendo Todos los Valores 
	int ciclo = 10;
	for(int j=0;j<x.length;j++){
	System.out.println("Cuando x es " +x[j]+" y es " + (a+(b*x[j])));
	}
	
	 myGui.showGui();
		}
	}
	
	public void CalculaRegresion()
	{
	
	System.out.println("Para el Valor " +myGui.numero+" y Vale " + (a+(b*myGui.numero)));
	
	}
	
	

}

