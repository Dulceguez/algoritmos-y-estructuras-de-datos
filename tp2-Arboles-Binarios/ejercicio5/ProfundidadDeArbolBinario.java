package tp2.ejercicio5;

import tp2.ejercicio1.BinaryTree;

public class ProfundidadDeArbolBinario {
	private BinaryTree<Integer> arbol;
	
	public ProfundidadDeArbolBinario(BinaryTree<Integer> a) {
		this.arbol = a;
	}
	
	 public int sumaElementosProfundidad (int p) {
		 if(arbol.isEmpty()) {	return 0;	 }
		 int profActual = 0;
		 return  sumaElementosProfundidadRecursivo(this.arbol,p,profActual);
	 }

	private int sumaElementosProfundidadRecursivo(BinaryTree<Integer> a, int p, int profActual) {
		if(a == null) { return 0 ; }
		
		if(p == profActual) { return a.getData(); }
		
		int profIzq = sumaElementosProfundidadRecursivo(a.getLeftChild(), p, profActual + 1);
		int profDer = sumaElementosProfundidadRecursivo(a.getRightChild(), p, profActual + 1);
		
		return profIzq + profDer;
	}
}
