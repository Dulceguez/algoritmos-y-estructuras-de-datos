package tp3.ejercicio4;

import tp3.ejercicio1.GeneralTree;
import Queue.Queue;

public class AnalizadorArbol {
	
	public double devolverMaximoPromedio (GeneralTree<AreaEmpresa>arbol) {
		Queue<GeneralTree<AreaEmpresa>> queue = new Queue<>();
		queue.enqueue(arbol);
		queue.enqueue(null);
		int nivel = 0;
		double promedioActual, maxPromedio = -1, transmisionTotal = 0,cant = 0;
		while(!queue.isEmpty()) {
			GeneralTree<AreaEmpresa> nodo = queue.dequeue();
			if(nodo != null) {
				cant++;
				transmisionTotal += nodo.getData().getTardanza();
				System.out.println(nodo.getData().getId()+" "+nodo.getData().getTardanza());
				for(GeneralTree<AreaEmpresa> child : nodo.getChildren()) {
					queue.enqueue(child);
				}
			} else {
				promedioActual = transmisionTotal / cant;
				if(maxPromedio < promedioActual) {
					maxPromedio = promedioActual;
				}
				System.out.println("Nivel "+nivel+" promedio "+promedioActual);	
				cant = 0; // inicializo nuevamente porque cambio de nivel
				transmisionTotal = 0; // " 	"	"
				nivel ++;
				if(!queue.isEmpty()) { // termino el nivel
					queue.enqueue(null);
				}
			}
		}
		return maxPromedio;
	}
	
	public static void main(String[] args) {
		GeneralTree<AreaEmpresa> childJ = new GeneralTree<AreaEmpresa>(new AreaEmpresa("J",13));
		childJ.addChild(new GeneralTree<AreaEmpresa>(new AreaEmpresa("A",4)));
		childJ.addChild(new GeneralTree<AreaEmpresa>(new AreaEmpresa("B",7)));
		childJ.addChild(new GeneralTree<AreaEmpresa>(new AreaEmpresa("C",5)));
		
		GeneralTree<AreaEmpresa> childK = new GeneralTree<AreaEmpresa>(new AreaEmpresa("K",25));
		childK.addChild(new GeneralTree<AreaEmpresa>(new AreaEmpresa("D",6)));
		childK.addChild(new GeneralTree<AreaEmpresa>(new AreaEmpresa("E",10)));
		childK.addChild(new GeneralTree<AreaEmpresa>(new AreaEmpresa("F",18)));
		
		GeneralTree<AreaEmpresa> childL = new GeneralTree<AreaEmpresa>(new AreaEmpresa("L",10));
		childL.addChild(new GeneralTree<AreaEmpresa>(new AreaEmpresa("G",9)));
		childL.addChild(new GeneralTree<AreaEmpresa>(new AreaEmpresa("H",12)));
		childL.addChild(new GeneralTree<AreaEmpresa>(new AreaEmpresa("I",19)));
	
		GeneralTree<AreaEmpresa> raiz = new GeneralTree<AreaEmpresa>(new AreaEmpresa("M",14));
		raiz.addChild(childJ);raiz.addChild(childK);raiz.addChild(childL);

		AnalizadorArbol a = new AnalizadorArbol();
		System.out.println("Mayor promedio -> "+ a.devolverMaximoPromedio(raiz));
		
	}
}