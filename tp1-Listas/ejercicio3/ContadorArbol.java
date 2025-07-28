package tp2.ejercicio3;

import tp2.ejercicio1.BinaryTree;
import java.util.List;
import java.util.ArrayList;

public class ContadorArbol {
	 private BinaryTree<Integer> a;
	 
	 public ContadorArbol(BinaryTree<Integer> arbol) {
		 this.a = arbol;
	 }
	 
	 public List<Integer> numerosPares(){
		 List<Integer> lista = new ArrayList<Integer>();
		 if(!this.a.isEmpty()) {
			 buscarParesInOrden(this.a, lista);
		 }
		 return lista;
	 }
	 
	 private void buscarParesInOrden(BinaryTree<Integer> nodo, List<Integer> lista) {
		 if(nodo == null) {	 return; }
		 
		 buscarParesInOrden(nodo.getLeftChild(), lista); // voy hasta el hijo mas izquierdo ultimo
		 
		 if(nodo.getData() % 2 == 0) {
			 lista.add(nodo.getData());
		 }
		 
		 buscarParesInOrden(nodo.getRightChild(), lista);
		 
	 }
	 
	 private void buscarParesPostOrden(BinaryTree<Integer> nodo, List<Integer> lista){
		 if(nodo == null) { return ; }
		 
		 buscarParesPostOrden(nodo.getLeftChild(), lista);
		 buscarParesPostOrden(nodo.getRightChild(), lista);
		 
		 if(nodo.getData() % 2 == 0) {
			 lista.add(nodo.getData());
		 }
	 }
}
