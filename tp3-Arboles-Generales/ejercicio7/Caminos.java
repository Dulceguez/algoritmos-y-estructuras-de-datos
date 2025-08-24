package tp3.ejercicio7;

import tp3.ejercicio1.GeneralTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Caminos {
	private GeneralTree<Integer> arbol;
	
	public Caminos(GeneralTree<Integer> a) {
		this.arbol = a;
	}
	/*
	 *  retorna el camino a la hoja más lejana. En el caso de haber
 		más de un camino máximo retorne el primero que encuentre
	 */
	
	public List<Integer> caminoAHojaMasLejana () {
		List<Integer> camino = new ArrayList<Integer>();
		List<Integer> lista = new ArrayList<Integer>();
		if(!this.arbol.isEmpty()) {
			dfsContadorDelCaminoMaximo(this.arbol,camino,lista);
		}
		return camino;
	}
	
	private void dfsContadorDelCaminoMaximo(GeneralTree<Integer> a, List<Integer> caminoMasLargo, List<Integer> caminoActual) {
		caminoActual.add(a.getData());
		System.out.println("Camino actual --> "+caminoActual);
		if (a.isLeaf()) {
			if(caminoMasLargo.size() < caminoActual.size()) {
				caminoMasLargo.clear();
				caminoMasLargo.addAll(caminoActual);
			}
		}
		
		
		if(a.hasChildren()) {
			Iterator<GeneralTree<Integer>> it = a.getChildren().iterator();
			while(it.hasNext()) {
				dfsContadorDelCaminoMaximo(it.next(), caminoMasLargo, caminoActual);
				
			}
		}
		System.out.println(a.getData());
		caminoActual.remove(caminoActual.size()-1);
	}
	
	public static void main(String[] args) {
		GeneralTree<Integer> child6 = new GeneralTree<Integer>(6);
		child6.addChild(new GeneralTree<Integer>(1));
		
		GeneralTree<Integer> child17 = new GeneralTree<Integer>(17);
		child17.addChild(new GeneralTree<Integer>(10)); child17.addChild(child6);
		
		GeneralTree<Integer> child9 = new GeneralTree<Integer>(9);
		child9.addChild(new GeneralTree<Integer>(8));
		
		GeneralTree<Integer> child14 = new GeneralTree<Integer>(14);
		child14.addChild(new GeneralTree<Integer>(16)); child14.addChild(new GeneralTree<Integer>(7));
		
		GeneralTree<Integer> child15 = new GeneralTree<Integer>(15);
		child15.addChild(child14); child15.addChild(new GeneralTree<Integer>(18));
		
		GeneralTree<Integer> raiz = new GeneralTree<Integer>(12);
		raiz.addChild(child17); raiz.addChild(child9); raiz.addChild(child15);
		
		Caminos c = new Caminos(raiz);
		System.out.println("Camino mas largo --> "+c.caminoAHojaMasLejana());
	}
}
