package tp3.ejercicio6;

import java.util.Iterator;

import tp3.ejercicio1.GeneralTree;

public class RedDeAguaPotable {
	private GeneralTree<Character> red;
	
	public RedDeAguaPotable(GeneralTree<Character> unaRed) {
		this.red = unaRed;
	}
	
	public double minimoCaudal(double caudal) {
		Minimo min = new Minimo(999);
		if(!red.isEmpty()) {
			
			calcularMinimoCaudal(red,caudal,min);
		}
		return min.getMinimo();
	}
	
	private void calcularMinimoCaudal(GeneralTree<Character> nodo, double caudal, Minimo min) {
		if (nodo.isLeaf()) {
			if(caudal < min.getMinimo())
				min.setMinimo(caudal);
			return ;
		}
		if(nodo.hasChildren()) {
			Iterator<GeneralTree<Character>> it = nodo.getChildren().iterator();		
			while (it.hasNext()) {
				calcularMinimoCaudal(it.next(),caudal/nodo.getChildren().size(),min);
			}
		}
	}
	
	public static void main(String [] args) {
		GeneralTree<Character> childG = new GeneralTree<Character>('G');
		childG.addChild(new GeneralTree<Character>('L'));
		
		GeneralTree<Character> childC = new GeneralTree<Character>('C');
		childC.addChild(new GeneralTree<Character>('F')); childC.addChild(childG);
		
		GeneralTree<Character> childJ = new GeneralTree<Character>('J');
		childJ.addChild(new GeneralTree<Character>('M')); childJ.addChild(new GeneralTree<Character>('N'));
		
		GeneralTree<Character> childD = new GeneralTree<Character>('D');
		childD.addChild(new GeneralTree<Character>('H')); childD.addChild(new GeneralTree<Character>('I')); childD.addChild(childJ);
		childD.addChild(new GeneralTree<Character>('K')); childD.addChild(new GeneralTree<Character>('P'));
		
		GeneralTree<Character> raiz = new GeneralTree<Character>('A');
		raiz.addChild(new GeneralTree<Character>('B')); raiz.addChild(childC); raiz.addChild(childD);
		raiz.addChild(new GeneralTree<Character>('E'));
	
		RedDeAguaPotable red = new RedDeAguaPotable(raiz);
		System.out.println("Menor caudal -> "+red.minimoCaudal(1000));
	
	
	}
}
	
		
