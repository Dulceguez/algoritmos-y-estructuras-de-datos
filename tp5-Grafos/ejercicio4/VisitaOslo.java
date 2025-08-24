package tp5.ejerciocio4;

import java.util.ArrayList;
import java.util.List;

import tp5.ejercicio1.*;
import tp5.ejercicio1.adjList.AdjListGraph;

public class VisitaOslo {

	/*
	 * realizar un paseo en bicicleta por lugares emblemáticos de Oslo. Partiendo
	 * desde el “Ayuntamiento” hasta un lugar destino en menos de X minutos, sin
	 * pasar por un conjunto de lugares que están restringidos.
	 */
	public List<String> paseoEnBici(Graph<String> lugares, String destino, int maxTiempo,
			List<String> lugaresRestringidos) {
		List<String> camino = new ArrayList<String>();
		boolean[] visited = new boolean[lugares.getSize()];
		Vertex<String> origen = null;
		for (Vertex<String> v : lugares.getVertices()) {
			if (v.getData().equals("Ayuntamiento")) {
				origen = v;
			}
			for (String restringido : lugaresRestringidos) {
				if (v.getData().equals(restringido)) {
					visited[v.getPosition()] = true;
				}
			}
		}
		int tiempo = 0;
		dfs(lugares, origen, tiempo, destino, maxTiempo, visited, camino);

		return camino;
	}

	private boolean dfs(Graph<String> lugares, Vertex<String> v, int tiempo, String destino, int maxTiempo,
			boolean[] visited, List<String> camino) {
		visited[v.getPosition()] = true;
		camino.add(v.getData());

		System.out.println("Actual: " + v.getData());
		if (v.getData().equals(destino)) {
			return true;
		}

		for (Edge<String> edge : lugares.getEdges(v)) {
			int nuevoTiempo = tiempo + edge.getWeight();
			System.out.println("Tiempo de " + v.getData() + " con " + edge.getTarget().getData() + " " + nuevoTiempo);

			if (!visited[edge.getTarget().getPosition()] & (nuevoTiempo < maxTiempo)) {
				boolean encontrado = dfs(lugares, edge.getTarget(), nuevoTiempo, destino, maxTiempo, visited, camino);
				if (encontrado)
					return true;
			}
		}

		System.out.println("Eliminar " + camino.get(camino.size() - 1));
		camino.remove(camino.size() - 1);
		return false;

	}

	public static void main(String[] args) {
		AdjListGraph grafo = new AdjListGraph();
		Vertex<String> v0 = grafo.createVertex("Ayuntamiento");

		Vertex<String> v1 = grafo.createVertex("Parque Botanico");
		Vertex<String> v2 = grafo.createVertex("El Tigre");
		Vertex<String> v3 = grafo.createVertex("Akker Brigge");
		Vertex<String> v4 = grafo.createVertex("Palacio Real");
		grafo.connect(v0, v1, 10);
		grafo.connect(v1, v0, 10);
		grafo.connect(v0, v2, 15);
		grafo.connect(v2, v0, 15);
		grafo.connect(v0, v3, 20);
		grafo.connect(v3, v0, 20);
		grafo.connect(v0, v4, 5);
		grafo.connect(v4, v0, 5);

		Vertex<String> v5 = grafo.createVertex("Museo Munch");
		grafo.connect(v5, v1, 1);
		grafo.connect(v5, v2, 15);
		grafo.connect(v2, v5, 15);
		grafo.connect(v1, v5, 1);

		Vertex<String> v6 = grafo.createVertex("Galeria Nacional");
		grafo.connect(v6, v1, 15);
		grafo.connect(v1, v6, 15);

		Vertex<String> v7 = grafo.createVertex("Parque Vigeland");
		grafo.connect(v7, v6, 10);
		grafo.connect(v6, v7, 10);

		Vertex<String> v8 = grafo.createVertex("Folk Museum");
		grafo.connect(v8, v7, 20);
		grafo.connect(v7, v8, 20);
		grafo.connect(v8, v4, 5);
		grafo.connect(v4, v8, 5);
		grafo.connect(v8, v3, 30);
		grafo.connect(v3, v8, 30);

		Vertex<String> v9 = grafo.createVertex("Museo Fram");
		grafo.connect(v9, v8, 5);
		grafo.connect(v8, v9, 5);

		Vertex<String> v10 = grafo.createVertex("Museo Vikingo");
		grafo.connect(v10, v9, 10);
		grafo.connect(v9, v10, 10);
		grafo.connect(v10, v3, 30);
		grafo.connect(v3, v10, 30);

		List<String> lugaresRestringidos = new ArrayList<String>();
		lugaresRestringidos.add("Palacio Real");
		lugaresRestringidos.add("Akker Brigge");
		VisitaOslo visita = new VisitaOslo();
		System.out.println("Paseo en Bici --> " + visita.paseoEnBici(grafo, "Museo Vikingo", 120, lugaresRestringidos));
	}

}
