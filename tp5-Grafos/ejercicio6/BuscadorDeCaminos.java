package tp5.ejercicio6;

import java.util.ArrayList;
import java.util.List;

import tp5.ejercicio1.Edge;
import tp5.ejercicio1.Graph;
import tp5.ejercicio1.Vertex;
import tp5.ejercicio1.adjList.AdjListGraph;

public class BuscadorDeCaminos {
	private Graph<String> bosque;

	public List<String> BuscadorDelPrimerCamino() {
		List<String> camino = new ArrayList<String>();
		boolean[] visited = new boolean[bosque.getSize()];

		Vertex<String> origen = null;
		for (Vertex<String> v : bosque.getVertices()) {
			if (v.getData().equals("Casa Caperucita")) {
				origen = v;
				break;
			}
		}

		dfsParteDos(origen, visited, camino);
		return camino;

	}

	private boolean dfsParteDos(Vertex<String> v, boolean[] visited, List<String> camino) {
		visited[v.getPosition()] = true;
		camino.add(v.getData());

		if (v.getData().equals("Casa Abuelita")) {
			return true;
		}

		for (Edge<String> edge : bosque.getEdges(v)) {
			if (!visited[edge.getTarget().getPosition()] && (edge.getWeight() <= 5)) {
				if (dfsParteDos(edge.getTarget(), visited, camino)) {
					return true;
				}
			}
		}
		camino.remove(camino.size() - 1);
		visited[v.getPosition()] = false;

		return false;
	}

	public BuscadorDeCaminos(Graph<String> b) {
		this.bosque = b;
	}

	/*
	 * encontrar todos los caminos que no pasen por los senderos con cantidad de
	 * frutales >= 5 (edges) y lleguen a la casa de la abuelita. devuelve un listado
	 * con TODOS los caminos que cumplen con las condiciones
	 */
	public List<List<String>> recorridosMasSeguro() {
		List<List<String>> caminos = new ArrayList<List<String>>(); // lista de los caminos.
		boolean[] visited = new boolean[bosque.getSize()]; // se inicializa por defecto en false

		Vertex<String> origen = null;
		for (Vertex<String> v : bosque.getVertices()) { // busco casa de caperucita, donde empieza mi recorrido
			if (v.getData().equals("Casa Caperucita")) {
				origen = v;
				break;
			}
		}

		List<String> lista = new ArrayList<String>();
		dfs(origen, caminos, visited, lista);
		return caminos;

	}

	private void dfs(Vertex<String> v, List<List<String>> caminos, boolean[] visited, List<String> lista) {
		visited[v.getPosition()] = true;
		lista.add(v.getData());
		System.out.println(v.getData());
		if (v.getData().equals("Casa Abuelita")) {
			System.out.println(lista);
			caminos.add(new ArrayList<>(lista));
		} else {

			for (Edge<String> edge : bosque.getEdges(v)) {
				System.out.println("Edge --> " + edge.getTarget().getData());
				if (!visited[edge.getTarget().getPosition()]) {
					if (edge.getWeight() <= 5) {
						System.out.println(edge.getWeight());
						dfs(edge.getTarget(), caminos, visited, lista);
					}
				}
			}

		}
		lista.remove(lista.size() - 1);
		visited[v.getPosition()] = false;
		System.out.println("Elimino " + v.getData());
	}

	public static void main(String[] args) {
		AdjListGraph grafo = new AdjListGraph<String>();

		Vertex<String> cc = grafo.createVertex("Casa Caperucita");
		Vertex<String> claro3 = grafo.createVertex("Claro 3");
		Vertex<String> claro1 = grafo.createVertex("Claro 1");
		Vertex<String> claro2 = grafo.createVertex("Claro 2");
		Vertex<String> claro5 = grafo.createVertex("Claro 5");
		Vertex<String> claro4 = grafo.createVertex("Claro 4");
		Vertex<String> ca = grafo.createVertex("Casa Abuelita");

		// cc
		grafo.connect(cc, claro3, 4);
		grafo.connect(cc, claro1, 3);
		grafo.connect(cc, claro2, 4);

		// claro 3
		grafo.connect(claro3, claro5, 15);
		grafo.connect(claro3, cc, 4);

		// claro 1
		grafo.connect(claro1, claro5, 3);
		grafo.connect(claro1, cc, 3);
		grafo.connect(claro1, claro2, 4);

		// claro 2
		grafo.connect(claro2, cc, 4);
		grafo.connect(claro2, claro1, 4);
		grafo.connect(claro2, claro5, 11);
		grafo.connect(claro2, claro4, 10);

		// claro 5
		grafo.connect(claro5, claro3, 15);
		grafo.connect(claro5, claro1, 3);
		grafo.connect(claro5, claro2, 11);
		grafo.connect(claro5, ca, 4);

		// claro 4
		grafo.connect(claro4, claro2, 10);
		grafo.connect(claro4, ca, 9);

		// ca
		grafo.connect(ca, claro5, 4);
		grafo.connect(ca, claro4, 9);

		BuscadorDeCaminos caminos = new BuscadorDeCaminos(grafo);

		System.out.println(caminos.BuscadorDelPrimerCamino());

	}
}
