package tp5.ejercicio3;

import tp5.ejercicio1.*;
import tp5.ejercicio1.adjList.AdjListGraph;
import Queue.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Mapa {
	private Graph<String> mapaCiudades;

	public Mapa(Graph<String> mapa) {
		this.mapaCiudades = mapa;
	}

	// Retorna la lista de ciudades que se deben atravesar para ir de ciudad1 a
	// ciudad2 en caso de que se
	// pueda llegar, si no retorna la lista vacía. (Sin tener en cuenta el
	// combustible).

	public List<String> devolverCamino(String ciudad1, String ciudad2) {
		List<String> camino = new ArrayList<>();
		boolean[] visited = new boolean[mapaCiudades.getSize()]; // SE INICIALIZA EN FALSE POR DEFECTO
		boolean encontrado; // usamos un array para que se pueda modificar dentro de dfs
		for (Vertex<String> v : mapaCiudades.getVertices()) {
			if (v.getData().equals(ciudad1)) {
				encontrado = dfs(v, ciudad2, visited, mapaCiudades, camino);
				break;
			}

		}
		return camino;
	}

	private boolean dfs(Vertex<String> v, String ciudad2, boolean[] visited, Graph<String> mapa, List<String> camino) {

		visited[v.getPosition()] = true;
		camino.add(v.getData());
		System.out.println("Actual: " + v.getData());

		if (v.getData().equals(ciudad2)) {
			return true;
		}

		for (Edge<String> edge : mapaCiudades.getEdges(v)) {
			System.out.println("Adyacentes de " + v.getData() + ": " + edge.getTarget().getData());
			if (!visited[edge.getTarget().getPosition()]) { // si el adyacente no fue visitado
				if (dfs(edge.getTarget(), ciudad2, visited, mapa, camino)) {
					return true;
				}
			}
		}

		camino.remove(camino.size() - 1);
		return false;

	}

	/*
	 * Retorna la lista de ciudades que forman un camino desde ciudad1 a ciudad2,
	 * sin pasar por las ciudades que están contenidas en la lista ciudades pasada
	 * por parámetro, si no existe camino retorna la lista vacía. (Sin tener en
	 * cuenta el combustible).
	 */
	public List<String> devolverCaminoExceptuando(String ciudad1, String ciudad2, List<String> ciudades) {
		List<String> camino = new ArrayList<String>();
		boolean[] visited = new boolean[mapaCiudades.getSize()]; // se inicializa en FALSE por DEFECTO
		boolean[] encontrado = { false };
		for (Vertex<String> v : mapaCiudades.getVertices()) {
			if (v.getData() == ciudad1) {
				dfs(v, ciudad2, ciudades, visited, camino, encontrado);
				break;
			}
		}
		return camino;
	}

	private void dfs(Vertex<String> v, String ciudad2, List<String> ciudades, boolean[] visited, List<String> camino,
			boolean[] encontrado) {
		if (encontrado[0])
			return;

		visited[v.getPosition()] = true;
		if (ciudades.contains(v.getData())) {
			System.out.println(v.getData() + " esta en la lista de ciudades.");
			return;
		}
		camino.add(v.getData());
		System.out.println("Actual: " + v.getData());

		if (v.getData().equals(ciudad2)) {
			encontrado[0] = true;
		}
		for (Edge<String> edge : mapaCiudades.getEdges(v)) {
			System.out.println("Adyacente de " + v.getData() + ": " + edge.getTarget().getData());
			if (!visited[edge.getTarget().getPosition()]) {
				dfs(edge.getTarget(), ciudad2, ciudades, visited, camino, encontrado);
			}
		}
		if (!encontrado[0]) {
			camino.remove(camino.size() - 1);
		}

	}

	/*
	 * Retorna la lista de ciudades que forman el camino más corto para llegar de
	 * ciudad1 a ciudad2, si no existe camino retorna la lista vacía. (Las rutas
	 * poseen la distancia).
	 */
	public List<String> caminoMasCorto(String ciudad1, String ciudad2) {
		List<String> camino = new ArrayList<String>();
		boolean[] visited = new boolean[mapaCiudades.getSize()];
		int[] D = new int[mapaCiudades.getSize()]; // Distancias
		for (int i = 0; i < D.length; i++) {
			D[i] = Integer.MAX_VALUE; // Esto representa "infinito"
		}
		int[] P = new int[mapaCiudades.getSize()]; // Predecesores
		for (int i = 0; i < P.length; i++) {
			P[i] = -1; // -1 indica que no tiene predecesor asignado aún
		}

		Queue<Vertex<String>> cola = new Queue<Vertex<String>>();

		for (Vertex<String> v : mapaCiudades.getVertices()) {
			if (v.getData().equals(ciudad1)) {
				cola.enqueue(v);
				D[v.getPosition()] = 0; // Distancia del origen a sí mismo es 0
				break;
			}
		}

		while (!cola.isEmpty()) {
			Vertex<String> vertice = cola.dequeue();
			visited[vertice.getPosition()] = true;
			System.out.println("Vertice actual: " + vertice.getData());

			if (vertice.getData().equals(ciudad2)) {
				int pos = vertice.getPosition();
				LinkedList<String> caminoReverso = new LinkedList<>();
				while (pos != -1) {
					caminoReverso.addFirst(mapaCiudades.getVertex(pos).getData());
					pos = P[pos];
				}
				camino.addAll(caminoReverso);
				break;
			}

			for (Edge<String> edge : mapaCiudades.getEdges(vertice)) {
				System.out.println("Adyacente de " + vertice.getData() + ": " + edge.getTarget().getData());
				if (D[edge.getTarget().getPosition()] == Integer.MAX_VALUE) {
					D[edge.getTarget().getPosition()] = D[vertice.getPosition()] + 1;
					P[edge.getTarget().getPosition()] = vertice.getPosition();
					cola.enqueue(edge.getTarget());
				}
			}
		}
		return camino;
	}

	/*
	 * Retorna la lista de ciudades que forman un camino para llegar de ciudad1 a
	 * ciudad2. El auto no debe quedarse sin combustible y no puede cargar. Si no
	 * existe camino retorna la lista vacía.
	 */
	public List<String> caminoSinCargarCombustible(String ciudad1, String ciudad2, int tanqueAuto) {
		List<String> camino = new ArrayList<String>();
		boolean[] visited = new boolean[mapaCiudades.getSize()];

		int[] D = new int[mapaCiudades.getSize()];
		for (int i = 0; i < D.length; i++) {
			D[i] = Integer.MAX_VALUE;
		}

		int[] P = new int[mapaCiudades.getSize()];
		for (int i = 0; i < P.length; i++) {
			P[i] = -1;
		}
		Queue<Vertex<String>> cola = new Queue<Vertex<String>>();
		for (Vertex<String> v : mapaCiudades.getVertices()) {
			if (v.getData().equals(ciudad1)) {
				D[v.getPosition()] = 0;
				cola.enqueue(v);
				break;
			}
		}

		while (!cola.isEmpty()) {
			Vertex<String> actual = cola.dequeue();
			visited[actual.getPosition()] = true;
			System.out.println("Actual: " + actual.getData());
			if (actual.getData().equals(ciudad2) & (D[actual.getPosition()] < tanqueAuto)) {
				LinkedList<String> listaReversa = new LinkedList<String>();
				int pos = actual.getPosition();
				while (pos != -1) {
					System.out.println("Posicion: " + pos);
					listaReversa.addFirst(mapaCiudades.getVertex(pos).getData());
					pos = P[pos];
				}
				camino.addAll(listaReversa);
				break;
			}

			for (Edge<String> edge : mapaCiudades.getEdges(actual)) {

				if (D[edge.getTarget().getPosition()] == Integer.MAX_VALUE) {
					D[edge.getTarget().getPosition()] = D[actual.getPosition()] + 1; // le sumo lo que tiene su
																						// adyacente + 1
					P[edge.getTarget().getPosition()] = actual.getPosition();
					System.out.println("Adyacente de " + actual.getData() + ": " + edge.getTarget().getData() + " D "
							+ D[edge.getTarget().getPosition()] + " P " + P[edge.getTarget().getPosition()]);
					cola.enqueue(edge.getTarget());
				}

			}
		}
		return camino;
	}

	public static <T> void main(String[] args) {
		AdjListGraph mapa = new AdjListGraph<>();

		Vertex<String> v1 = mapa.createVertex("La Plata");
		Vertex<String> v2 = mapa.createVertex("Mar Del Plata");
		Vertex<String> v3 = mapa.createVertex("Buenos Aires");
		Vertex<String> v4 = mapa.createVertex("Puerto Madryn");
		Vertex<String> v5 = mapa.createVertex("Cordoba");
		Vertex<String> v6 = mapa.createVertex("Santa Fe");

		mapa.connect(v1, v2);
		mapa.connect(v1, v5);
		mapa.connect(v1, v3);
		mapa.connect(v2, v1);
		mapa.connect(v2, v5);
		mapa.connect(v2, v4);
		mapa.connect(v3, v1);
		mapa.connect(v3, v5);
		mapa.connect(v3, v6);
		mapa.connect(v4, v2);
		mapa.connect(v4, v5);
		mapa.connect(v5, v1);
		mapa.connect(v5, v2);
		mapa.connect(v5, v3);
		mapa.connect(v5, v4);
		mapa.connect(v5, v6);
		mapa.connect(v6, v5);
		mapa.connect(v6, v3);

		Mapa map = new Mapa(mapa);
		System.out.println(map.devolverCamino("La Plata", "Santa Fe"));
		List<String> ciudades = new ArrayList<String>();
		ciudades.add("Mar Del Plata");
		ciudades.add("Cordoba");

		System.out.println();
		String aux = "";
		for (int i = 0; i < ciudades.size(); i++) {
			aux += ciudades.get(i) + " ";
		}
		System.out.println("Camino sin pasar por las ciudades " + aux + " --> "
				+ map.devolverCaminoExceptuando("La Plata", "Santa Fe", ciudades));

		System.out.println();
		System.out.println("Camino mas corto --> " + map.caminoMasCorto("La Plata", "Santa Fe"));

		System.out.println();
		System.out.println(
				"Camino sin cargar combustible --> " + map.caminoSinCargarCombustible("La Plata", "Santa Fe", 4));

	}
}
