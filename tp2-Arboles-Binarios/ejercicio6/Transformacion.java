package tp2.ejercicio6;

import tp2.ejercicio1.BinaryTree;

public class Transformacion {
	private BinaryTree<Integer> arbol;
	
	public Transformacion(BinaryTree<Integer> unArbol) {
		this.arbol = unArbol;
	}
	
	public BinaryTree<Integer> suma() {
		BinaryTree<Integer> arbolTransformado = new BinaryTree<Integer>();
		if(!this.arbol.isEmpty()) {
			transformarArbol(this.arbol,arbolTransformado);
		}
		return arbolTransformado;
	}
	
	private int transformarArbol(BinaryTree<Integer> a, BinaryTree<Integer> arbolTransformado) {
		if(a == null) { return 0; }
		
		int sumaIzq = 0, sumaDer = 0;
		
		if(a.hasLeftChild()) {
			BinaryTree<Integer> izq = new BinaryTree<Integer>();
			arbolTransformado.addLeftChild(izq);
			sumaIzq = transformarArbol(a.getLeftChild(),izq);
		}
		if(a.hasRightChild()) {
			BinaryTree<Integer> der = new BinaryTree<Integer>();
			arbolTransformado.addRightChild(der);
			sumaDer = transformarArbol(a.getRightChild(),der);
		}
		arbolTransformado.setData(sumaIzq + sumaDer);
		
		return a.getData() + arbolTransformado.getData();
	}
	
	public static void main(String[] args) {
		BinaryTree<Integer> child2 = new BinaryTree<Integer>(2);
		child2.addRightChild(new BinaryTree<Integer>(4));
		
		BinaryTree<Integer> child5 = new BinaryTree<Integer>(5);
		child5.addLeftChild(new BinaryTree<Integer>(7)); child5.addRightChild(new BinaryTree<Integer>(8));
		
		BinaryTree<Integer> child3 = new BinaryTree<Integer>(3);
		child3.addLeftChild(child5); child3.addRightChild(new BinaryTree<Integer>(6));
		
		BinaryTree<Integer> raiz = new BinaryTree<Integer>(1);
		raiz.addLeftChild(child2); raiz.addRightChild(child3);
		
		Transformacion t = new Transformacion(raiz);
		System.out.println("Arbol Transformado -> "+t.suma());
	}
}
