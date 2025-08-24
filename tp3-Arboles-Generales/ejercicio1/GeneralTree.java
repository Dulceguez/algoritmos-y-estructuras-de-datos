package tp3.ejercicio1;

import java.util.LinkedList;
import java.util.List;
import Queue.Queue;

public class GeneralTree<T> {

	private T data;
	private List<GeneralTree<T>> children = new LinkedList<GeneralTree<T>>();

	public GeneralTree() {

	}

	public GeneralTree(T data) {
		this.data = data;
	}

	public GeneralTree(T data, List<GeneralTree<T>> children) {
		this(data);
		this.children = children;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<GeneralTree<T>> getChildren() {
		return this.children;
	}

	public void setChildren(List<GeneralTree<T>> children) {
		if (children != null)
			this.children = children;
	}

	public void addChild(GeneralTree<T> child) {
		this.getChildren().add(child);
	}

	public boolean isLeaf() {
		return !this.hasChildren();
	}

	public boolean hasChildren() {
		return !this.children.isEmpty();
	}

	public boolean isEmpty() {
		return this.data == null && !this.hasChildren();
	}

	public void removeChild(GeneralTree<T> child) {
		if (this.hasChildren())
			children.remove(child);
	}
	
	/*
	 * 	 devuelve la altura del árbol, es decir, la longitud del camino más largo
 			desde el nodo raíz hasta una hoja.
	 */
	public int altura() {
		if(this.isLeaf()) return 0;
		
		int maxAltura = 0;
		System.out.println(this.getData());
		for(GeneralTree<T> child : this.getChildren()) {
			
			int childAltura = child.altura();
			if(childAltura > maxAltura) {
				maxAltura = childAltura;
			}
			
		}
	
		return maxAltura + 1;
	}
	/*
	 * 	devuelve la profundidad o nivel del dato en el árbol. El nivel de un nodo
 		es la longitud del único camino de la raíz al nodo.
	 */
	public int nivel(T dato) {
		Queue<GeneralTree<T>> queue = new Queue<>();
		queue.enqueue(this);
		queue.enqueue(null);
		int nivel = 0;
		while(!queue.isEmpty()) {
			GeneralTree<T> nodo = queue.dequeue();
			
			if(nodo != null) {
				System.out.println(nodo.getData());
				if (nodo.getData().equals(dato)) {
		            return nivel;
		        }
		        for (GeneralTree<T> child : nodo.getChildren()) {
		            queue.enqueue(child);
		        }
			}  else {
				if (!queue.isEmpty()) {
		            nivel++;
		            queue.enqueue(null);
		        }
			}
		}
		return -1;
	}
	/*
	 * 	ancho -->  cantidad de nodos que se encuentran en el nivel que
	 *  posee la mayor cantidad de nodos.
	 */
	public int ancho() {
		Queue<GeneralTree<T>> queue = new Queue<>();
		queue.enqueue(this);
		queue.enqueue(null);
		int nivel = 0, cant = 0, maxCant = 0, maxNivel = -1;
		while(!queue.isEmpty()) {
			GeneralTree<T> nodo = queue.dequeue();
			if(nodo != null) {
				System.out.println(nodo.getData());
				cant++; // si no es null -> estoy en el mismo nivel. Cuento 1 nodo mas.
				for(GeneralTree<T> child : nodo.getChildren()) {
					queue.enqueue(child);
				}
			} else {
				System.out.println("Nivel: "+nivel+" tiene "+cant+" nodos.");
				if(!queue.isEmpty()) {
					
					queue.enqueue(null);
					if(cant > maxCant) {
						maxCant = cant;
						maxNivel = nivel;
					}
					nivel++; cant = 0;
				}
			}
		}
				
		return maxNivel;
	}
	/*
	 * 	 n es ancestro de un nodo m si existe un camino desde n a m.
	 * 
	 * 	devuelve true si el valor “a” es ancestro del valor “b”.
	 */
	 public boolean esAncestro(T a, T b) {
		 GeneralTree<T> nodoA = buscar(a);
		 boolean encontrado = false;
		 if(nodoA != null) {
			 System.out.println("Buscanding "+nodoA.getData());
			 encontrado = nodoA.buscarB(b);
		 }
		 return encontrado; 
	 }
	 
	 private boolean buscarB(T b) {
		 System.out.println(this.getData());
		 if(this.getData().equals(b)) { return true; }
		 
		 boolean encontrado = false;
		 for(GeneralTree<T> child : this.getChildren()) {
			 encontrado = encontrado || child.buscarB(b);
		 }
		 
		 return encontrado; 
	 }
	 
	 
	 private GeneralTree<T> buscar(T dato){
		 System.out.println(this.getData());
		 if (this.getData().equals(dato)) {
			 return this;
		 } else {
		 
			for(GeneralTree<T> child : this.getChildren()) {
				GeneralTree<T> encontrado = child.buscar(dato);
				if(encontrado != null) {
					return encontrado;
				}
			 }
		 }
		 return null;
	 
	 }
}
