package tp2.ejercicio1;

import Queue.Queue;

public class BinaryTree<T> {

	private T data;
	private BinaryTree<T> leftChild;
	private BinaryTree<T> rightChild;

	public BinaryTree() {
		super();
	}

	public BinaryTree(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	/**
	 * Preguntar antes de invocar si hasLeftChild()
	 * 
	 * @return
	 */
	public BinaryTree<T> getLeftChild() {
		return leftChild;
	}

	/**
	 * Preguntar antes de invocar si hasRightChild()
	 * 
	 * @return
	 */
	public BinaryTree<T> getRightChild() {
		return this.rightChild;
	}

	public void addLeftChild(BinaryTree<T> child) {
		this.leftChild = child;
	}

	public void addRightChild(BinaryTree<T> child) {
		this.rightChild = child;
	}

	public void removeLeftChild() {
		this.leftChild = null;
	}

	public void removeRightChild() {
		this.rightChild = null;
	}

	public boolean isEmpty() {
		return (this.isLeaf() && this.getData() == null);
	}

	public boolean isLeaf() {
		return (!this.hasLeftChild() && !this.hasRightChild());

	}

	public boolean hasLeftChild() {
		return this.leftChild != null;
	}

	public boolean hasRightChild() {
		return this.rightChild != null;
	}

	@Override
	public String toString() {
		return this.getData().toString();
	}
	
	/*
	 *   Devuelve la cantidad de árbol/subárbol hojas del árbol receptor.
	 */
	public int contarHojas() {
		int cont = 0;
		if(!this.isEmpty()) {
			cont = contar(this);
		}
		return cont;
	}
	
	private int contar(BinaryTree<T> a) {
		if(a== null) return 0;
		
		if(a.isLeaf()) {
			return 1;
		}
		
		return contar(a.getLeftChild()) + contar(a.getRightChild()) + 0;
	}

	public BinaryTree<T> espejo() {
		if(this.isEmpty()) return new BinaryTree<T>();
		
		return espejoRecursivo(this);
	}
	
	private BinaryTree<T> espejoRecursivo(BinaryTree<T> a) {
		
		if(a == null) { return null; }
		
		BinaryTree<T> nuevoNodo = new BinaryTree<T>(this.getData());
		
		nuevoNodo.addLeftChild(espejoRecursivo(a.getRightChild()));
		nuevoNodo.addRightChild(espejoRecursivo(a.getLeftChild()));
		
		return nuevoNodo;
	}

	// 0<=n<=m
	public void entreNiveles(int n, int m) {
		Queue<BinaryTree<T>> queue = new Queue<BinaryTree<T>>();
		queue.enqueue(this);
		queue.enqueue(null);
		
		int nivel = 0;
		while(!queue.isEmpty()) {
			BinaryTree<T> nodo = queue.dequeue();
			
			if(nodo != null) {
				if(n <= nivel && m >= nivel) {
					System.out.println(nodo.getData());
				}
				if(nodo.hasLeftChild()) { queue.enqueue(nodo.getLeftChild()); }
				if(nodo.hasRightChild()) { queue.enqueue(nodo.getRightChild()); }
			}else {
				if(!queue.isEmpty()) {
					nivel ++;
					queue.enqueue(null);
				}
			}
		}
	}	
}