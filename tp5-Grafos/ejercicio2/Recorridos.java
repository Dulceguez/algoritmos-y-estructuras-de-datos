package tp5.ejercicio2;

import java.util.List;
import Queue.Queue;
import tp5.ejercicio1.*;
import tp5.ejercicio1.adjList.AdjListGraph;

import java.util.ArrayList;

public class Recorridos {

	// Retorna una lista con los datos de los vértices,
	// con el recorrido en profundidad del grafo recibido como parámetro.

	public <T> List<T> dfs(Graph<T> grafo) {
		boolean[] visited = new boolean[grafo.getSize()];

		List<T> lista = new ArrayList<>();

		for (Vertex<T> v : grafo.getVertices()) {
			if (!visited[v.getPosition()]) {
				dfsRecursivo(v, lista, visited, grafo);
			}
		}
		return lista;
	}

	private <T> void dfsRecursivo(Vertex<T> v, List<T> lista, boolean[] visited, Graph<T> grafo) {
		visited[v.getPosition()] = true;
		lista.add(v.getData());

		for (Edge<T> adyacent : grafo.getEdges(v)) {
			if (!visited[adyacent.getTarget().getPosition()]) {
				dfsRecursivo(adyacent.getTarget(), lista, visited, grafo);
			}
		}
	}

	// Retorna una lista con los datos de vértices,
	// con el recorrido en amplitud del grafo recibido como parámetro
	public <T> List<T> bfs(Graph<T> grafo) {
		boolean[] visited = new boolean[grafo.getSize()];
		Queue<Vertex<T>> cola = new Queue<Vertex<T>>();
		List<T> lista = new ArrayList<>();

		for (Vertex<T> v : grafo.getVertices()) {

			if (!visited[v.getPosition()]) {
				visited[v.getPosition()] = true;
				cola.enqueue(v);

				while (!cola.isEmpty()) {
					Vertex<T> actual = cola.dequeue(); // desencolo vertice
					lista.add(actual.getData());

					for (Edge<T> edge : grafo.getEdges(actual)) {
						Vertex<T> vecino = edge.getTarget();
						if (!visited[vecino.getPosition()]) {
							visited[vecino.getPosition()] = true;
							cola.enqueue(vecino);
						}
					}
				}
			}
		}
		return lista;
	}

	public static <T> void main(String[] args) {
		AdjListGraph grafo = new AdjListGraph<>(); // creo lista

		// agrego los vertices
		Vertex<T> v1 = grafo.createVertex(1);
		Vertex<T> v2 = grafo.createVertex(2);
		Vertex<T> v3 = grafo.createVertex(3);
		Vertex<T> v4 = grafo.createVertex(4);
		Vertex<T> v5 = grafo.createVertex(5);
		Vertex<T> v6 = grafo.createVertex(6);

		// conecto los vertices
		grafo.connect(v1, v2);
		grafo.connect(v1, v4);
		grafo.connect(v4, v2);
		grafo.connect(v2, v5);
		grafo.connect(v5, v4);
		grafo.connect(v3, v6);
		grafo.connect(v3, v5);
		grafo.connect(v6, v6);

		Recorridos recorrido = new Recorridos();
		System.out.println("Dfs --> " + recorrido.dfs(grafo));
		System.out.println("Bfs --> " + recorrido.bfs(grafo));
	}

}
