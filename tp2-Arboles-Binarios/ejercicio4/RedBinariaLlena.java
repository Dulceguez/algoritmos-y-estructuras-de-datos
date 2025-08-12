package tp2.ejercicio4;

import Parciales.Maximo;
import tp2.ejercicio1.BinaryTree;

public class RedBinariaLlena {
	private BinaryTree<Integer> red;
	
	public RedBinariaLlena(BinaryTree<Integer> a) {
		this.red = a;
	}
	
	public int retardoReenvio() {
		int retardo = 0;
		if(!this.red.isEmpty()) {
			retardo = calcularRetardo(this.red);
		}
		return retardo;
	}
	
	private int calcularRetardo(BinaryTree<Integer> red) {
		if(red == null) { return 0; }
		
		int maxIzq = calcularRetardo(red.getLeftChild()) + red.getData();
		int maxDer = calcularRetardo(red.getRightChild()) + red.getData();
		
		return Math.max(maxIzq, maxDer);
	}
}
