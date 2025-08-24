package tp3.ejercicio2;

import java.util.ArrayList;
import java.util.List;
import Queue.Queue;

import tp3.ejercicio1.GeneralTree;

public class RecorridosAG {
	
	public RecorridosAG() {
		
	}
	/*
	 *   retorna una lista con los elementos impares del árbol “a” que sean mayores al valor “n”
	 *    recorrido en preorden
	 */
	public List<Integer> numerosImparesMayoresQuePreOrden (GeneralTree <Integer> a, Integer n) {
		 List<Integer> lista = new ArrayList<Integer>();
		 if (!a.isEmpty()) {
			 imparesPreOrden(a, n, lista);
		 }
		 return lista;
	 }
	
	private void imparesPreOrden(GeneralTree<Integer> a, Integer n, List<Integer> lista) {
		if(a.getData() % 2 == 1 && a.getData() > n) {
			lista.add(a.getData());
		}
		System.out.println(a.getData());
		List<GeneralTree<Integer>> children = a.getChildren();
		for(GeneralTree<Integer> child : children) {
			imparesPreOrden(child, n, lista);
		}
	}
	/*
	 *   retorna una lista con los elementos impares del árbol “a” que sean mayores al valor “n”
	 *    recorrido en inorden
	 */
	 public List<Integer> numerosImparesMayoresQueInOrden (GeneralTree <Integer> a, Integer n){
		 List<Integer> lista = new ArrayList<Integer>();
		 if(!a.isEmpty()) {
			 imparesInOrden(a,n,lista);
		 }
		 return lista;
	 }
	
	 private void imparesInOrden(GeneralTree<Integer> a, Integer n, List<Integer> lista) {
		 if (a == null) return;
		 
		 List<GeneralTree<Integer>> children = a.getChildren();
		 
		 if(!children.isEmpty()) {
			 imparesInOrden(a.getChildren().get(0),n,lista);
		 }
		 if(a.getData() % 2 == 1 && a.getData() > n) {
			 lista.add(a.getData());
		 }
		 System.out.println(a.getData());
		 for(int i=1; i < children.size(); i++) {
			 
			 imparesInOrden(children.get(i), n, lista);
		 }
	 }
	 /*
		 *   retorna una lista con los elementos impares del árbol “a” que sean mayores al valor “n”
		 *    recorrido en postorden
		 */
	 public List<Integer> numerosImparesMayoresQuePostOrden (GeneralTree <Integer> a, Integer n){
		 List<Integer> lista = new ArrayList<Integer>();
		 if(!a.isEmpty()) {
			 imparesPostOrden(a,n,lista);
		 }
		 return lista;
	 }
	
	 private void imparesPostOrden(GeneralTree<Integer> a, Integer n, List<Integer> lista) {
		 List<GeneralTree<Integer>> children = a.getChildren();
		 for(GeneralTree<Integer> child : children) {
			 imparesPostOrden(child, n, lista);
			 System.out.println(child.getData());
			 if(child.getData() % 2 == 1 && child.getData() > n) {
				 lista.add(child.getData());
			 }
		 }
	 }
	 /*
		 *   retorna una lista con los elementos impares del árbol “a” que sean mayores al valor “n”
		 *    recorrido por niveles
		 */   
	 public List<Integer> numerosImparesMayoresQuePorNiveles(GeneralTree <Integer> a, Integer n){
		 List<Integer> lista = new ArrayList<Integer>();
		 Queue<GeneralTree<Integer>> queue = new Queue<>();	
		 queue.enqueue(a);
		 while(!queue.isEmpty()) {
			 GeneralTree<Integer> nodo = queue.dequeue();
			 System.out.println(nodo.getData());
			 if(nodo.getData() % 2 == 1 && nodo.getData() > n) {
				 lista.add(nodo.getData());
			 }
			 for(GeneralTree<Integer> child : nodo.getChildren()) {
				 queue.enqueue(child);
			 }
		 }
		 
		 return lista;
	 }
	 
	 
	public static void main(String[] args) {
		GeneralTree<Integer> raiz = new GeneralTree<Integer>(10);
		
		GeneralTree<Integer> child21 = new GeneralTree<Integer>(21);
		child21.addChild(new GeneralTree<Integer>(3)); child21.addChild(new GeneralTree<Integer>(20)); child21.addChild(new GeneralTree<Integer>(90));
		
		GeneralTree<Integer> child1 = new GeneralTree<Integer>(1);
		child1.addChild(new GeneralTree<Integer>(8)); child1.addChild(new GeneralTree<Integer>(9));
	
		GeneralTree<Integer> child18 = new GeneralTree<Integer>(18);
		GeneralTree<Integer> child15 = new GeneralTree<Integer>(15);
		child18.addChild(child15);
		child15.addChild(new GeneralTree<Integer>(66));
		raiz.addChild(child21); raiz.addChild(child1); raiz.addChild(child18);
		
		//RecorridosAG recorrido = new RecorridosAG();
		//System.out.println(recorrido.numerosImparesMayoresQuePorNiveles(raiz, 11));
		//System.out.println("Nivel de 18 --> "+raiz.nivel(18));
		//System.out.println("Altura -> "+raiz.altura());
		//System.out.println("Ancho del arbol -> "+raiz.ancho());
		
		System.out.println(raiz.esAncestro(21, 90));
	}
}