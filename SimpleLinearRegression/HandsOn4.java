package examples.SLR;

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
           double _a=1;
           double _b=1;
           double alpha=0.001;
           int iters=100000;
           
   
        System.out.println("Actividad de regresion lineal");


        int sum_xy = 0, sum_x = 0, sum_y = 0, sum_x2 = 0;  

        for(int actual = 0; actual < 9; actual++)
        {
            sum_xy += x[actual] * y[actual];
            sum_x  += x[actual];
            sum_y  += y[actual];
            sum_x2 += Math.pow(x[actual], 2);
        }
        
        System.out.println("SOLUCION ANALITICA");
        System.out.println("Calculando valor de a (Punto de Corte)");

         a = (double)(((sum_y * sum_x2) - (sum_x * sum_xy))/ ((9 * sum_x2) - Math.pow(sum_x, 2)));

        System.out.println("Listo! El valor de a es: " + a);
        
        System.out.println("Calculando valor de b (Pendiente)");

         b = (double)(((9 * sum_xy) - (sum_x * sum_y)) / ((9 * sum_x2) - Math.pow(sum_x,2)));   
        
        System.out.println("Listo! El valor de b es: " + b);
        
          	
         //Imprimiendo Todos los Valores 
	
	for(int j=0;j<x.length;j++)
	{
		//System.out.println("Cuando x es " +x[j]+" y es " + (a+(b*x[j])));
	}
	
	descenso_gradiente(x,y,_a,_b,alpha,iters);
	
	
	 myGui.showGui();
		}
	}
	
	public void CalculaRegresion()
	{
	
	System.out.println("Para el Valor " +myGui.numero+" Vale " + (a+(b*myGui.numero)));
	
	}
	
	
	public double coste(int _x[], int _y[], double _a, double _b)
	{
	double m=9;
	double error=0;
	double hipotesiss=0;

		for (int i=0; i<9; i++)
		{
			hipotesiss = _a+_b*_x[i];
			error += (_y[i] - hipotesiss)*(_y[i] - hipotesiss);
			error = error / (2*m);
		}
		
	return error / (2*m);
	}
	
	public void descenso_gradiente(int _x[], int _y[], double _a, double _b, double _alpha, int _epochs)
	{
	double m=9;
	double error=0;
	double hipotesis=0;
	double b_deriv;
	double a_deriv;

		
		for (int i=0; i<_epochs; i++)
		{
			b_deriv=0;
			a_deriv=0;
			
			for (int j=0; j<9; j++)
			{
				hipotesis = _a+_b*_x[j];
				a_deriv += hipotesis - _y[j];
				b_deriv += (hipotesis - _y[j]) * _x[j];
				error=0;
				error= coste(_x, _y, _a, _b);
				if(error<0.0)
				{break;}
			}
			_a -= (a_deriv / m) * _alpha;
			_b -= (b_deriv / m) * _alpha;
		}
	System.out.println("SOLUCION CON GRADIENTE DESCENDENTE: ");
	System.out.println("Listo! El valor de beta0 es: " + _a);
	System.out.println("Listo! El valor de beta1 es: " + _b);		
	System.out.println("Listo! El valor de error es: " + error);	
	}
	
	

}
