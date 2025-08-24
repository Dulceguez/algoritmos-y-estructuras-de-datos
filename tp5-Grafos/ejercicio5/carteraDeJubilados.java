package tp5.ejercicio5;

import tp5.ejercicio1.*;
import tp5.ejercicio1.adjList.AdjListGraph;
import Queue.Queue;
import java.util.ArrayList;
import java.util.List;

public class carteraDeJubilados {

	public carteraDeJubilados() {

	}

	public List<Persona> cartera(Graph<Persona> grafo, Persona empleado, int distancia) {

		List<Persona> jubilados = new ArrayList<Persona>(); // retorno lista de jubilados
		Queue<Vertex<Persona>> cola = new Queue<Vertex<Persona>>();

		for (Vertex<Persona> v : grafo.getVertices()) {
			if (v.getData().getNombre().equals(empleado.getNombre())) {
				cola.enqueue(v);
				System.out.println("Empleado: " + v.getData().getNombre());
				cola.enqueue(null); // separador de nivel
				break;
			}
		}

		int cont = 0;
		boolean[] visited = new boolean[grafo.getSize()];
		while (!cola.isEmpty()) {
			Vertex<Persona> actual = cola.dequeue();

			if (actual != null) {
				visited[actual.getPosition()] = true;
				// System.out.println("Actual --> "+actual.getData().getNombre());
				if (!actual.getData().getTipo().equals("Empleado") & cont <= distancia) {
					jubilados.add(actual.getData());
					// System.out.println("Guardando jubilado del nivel "+cont+": "+
					// actual.getData().getNombre());
				}

				for (Edge<Persona> edge : grafo.getEdges(actual)) {
					if (!visited[edge.getTarget().getPosition()])
						cola.enqueue(edge.getTarget());

				}
			} else {
				if (!cola.isEmpty()) {
					cola.enqueue(null);
					cont++;
				}
			}
		}
		return jubilados;
	}

	public static void main(String[] args) {
		AdjListGraph<Persona> grafo = new AdjListGraph<Persona>();

		Persona per, emp;
		// nivel 0
		emp = new Persona("Empleado", "Jorge", "3434");
		Vertex<Persona> v1 = grafo.createVertex(emp);

		// nivel 1
		per = new Persona("Jubilado", "Marta", "38");
		Vertex<Persona> v2 = grafo.createVertex(per);
		per = new Persona("Jubilado", "Mabel", "234");
		Vertex<Persona> v3 = grafo.createVertex(per);
		per = new Persona("Empleado", "Olga", "1344");
		Vertex<Persona> v4 = grafo.createVertex(per);
		per = new Persona("Jubilado", "Oscar", "4656");
		Vertex<Persona> v5 = grafo.createVertex(per);

		// nivel 2
		per = new Persona("Jubilado", "Hugo", "23435");
		Vertex<Persona> v6 = grafo.createVertex(per);

		grafo.connect(v1, v2);
		grafo.connect(v2, v1);
		grafo.connect(v1, v2);
		grafo.connect(v2, v1);
		grafo.connect(v1, v3);
		grafo.connect(v3, v1);
		grafo.connect(v1, v4);
		grafo.connect(v4, v1);
		grafo.connect(v1, v5);
		grafo.connect(v5, v1);

		grafo.connect(v5, v6);
		grafo.connect(v6, v5);

		carteraDeJubilados c = new carteraDeJubilados();
		System.out.println(c.cartera(grafo, emp, 1));
	}
}