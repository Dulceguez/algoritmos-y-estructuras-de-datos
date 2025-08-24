package tp3.ejercicio9;

import java.util.Iterator;
import java.util.List;

import tp3.ejercicio1.GeneralTree;

public class ParcialArboles {
	
	public static boolean esDeSeleccion (GeneralTree<Integer> arbol) {
		System.out.println(arbol.getData());
		List<GeneralTree<Integer>> children = arbol.getChildren();
		
		if(children.isEmpty()) { // es hoja
			return true;
		}
		
		int min = Integer.MAX_VALUE;
		
		for(GeneralTree<Integer> child : children) {
			
			if(child.getData() < min) {
				min = child.getData();
			}

			if(!esDeSeleccion(child)) {
				return false;
			}
		}
		
		if(min == arbol.getData()) {
			return true;
		}
		return false;
	}
	
	
	public static void main(String[]args) {
		GeneralTree<Integer> child35 = new GeneralTree<Integer>(35);
		child35.addChild(new GeneralTree<Integer>(35));
		
		GeneralTree<Integer> child33 = new GeneralTree<Integer>(33);
		child33.addChild(new GeneralTree<Integer>(35)); child33.addChild(new GeneralTree<Integer>(83));
		child33.addChild(new GeneralTree<Integer>(90)); child33.addChild(new GeneralTree<Integer>(33));
		
		GeneralTree<Integer> child12 = new GeneralTree<Integer>(12);
		child12.addChild(new GeneralTree<Integer>(14)); child12.addChild(new GeneralTree<Integer>(12)); child12.addChild(child35);
		
		GeneralTree<Integer> child355 = new GeneralTree<Integer>(35);
		child355.addChild(child35);
		
		GeneralTree<Integer> child122 = new GeneralTree<Integer>(12);
		child122.addChild(child355); child122.addChild(child12);
		
		GeneralTree<Integer> child25 = new GeneralTree<Integer>(25);
		child25.addChild(new GeneralTree<Integer>(25)); 
		
		GeneralTree<Integer> raiz = new GeneralTree<Integer>(12);
		raiz.addChild(child122); raiz.addChild(child25);
		
		System.out.println("Es de seleccion --> "+esDeSeleccion(raiz));
	}
}
