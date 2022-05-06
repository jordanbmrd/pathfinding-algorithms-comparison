package sae.transform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sae.dungeon.Direction;
import sae.dungeon.Dungeon;
import sae.dungeon.DungeonSoluce;
import sae.dungeon.Room;
import sae.graph.Graph;
import sae.graph.GraphSoluce;
import sae.graph.Node;

public class Dungeon2Graph {
	
	public Graph graph;
	
	private Map<Room, Node> associations;

	public Dungeon2Graph(Dungeon dungeon) {
		/*
		 * 
		 * OBJET Dungeon2Graph
		 * Convertit un donjon en graphe.
		 * 
		 * - Ajoute les noeuds à partir des chambres
		 * - Établit les liens entre chaque noeud
		 * 
		 * */
		
		associations = new HashMap<Room, Node>();
		graph = new Graph();
		
		// Pour chaque chambre, on enregistre un noeud dans le graphe et on enregistre l'association Room - Node
		for(Room room : dungeon.getRooms()) {
			Node nodeFromRoom = new Node(room.getName(), room.getCoords());
			graph.addNode(nodeFromRoom);
			associations.put(room, nodeFromRoom);
		}
		
		// Pour chaque association, on ajoute un lien entre le noeud et son voisin (en veillant à enregistrer la direction vers celui-ci)
		for(Map.Entry<Room, Node> association : associations.entrySet()) {
			Room room = association.getKey();
			room.getNextRooms().forEach((direction, neighbour) -> {
				graph.addEdge(association.getValue(), mappedNode(neighbour), room.getDirection(neighbour), neighbour.getDirection(room));
			});
		}
		
	}

	public Node mappedNode(Room room) {
		/*
		 * Cette fonction renvoie le noeud du graphe correspondant à la chambre passée en paramètre.
		 * Si le graphe ne contient pas le noeud recherché, elle renvoie null.
		 * 
		 * PARAMÈTRE : Room à rechercher
		 * */
		return associations.get(room);
	}

	public DungeonSoluce transform(GraphSoluce graphSoluce) {
		/*
		 * 
		 * Cette fonction transforme la solution d'un graph en donjon.
		 * La fonction parcourt les noeuds de la solutions et vérifie leur association avec le noeud suivant
		 * pour ajouter la direction dans le nouveau donjon.
		 * 
		 * PARAMÈTRE : Solution du graphe
		 * 
		 * */
		DungeonSoluce dungeonSoluce = new DungeonSoluce();
		
		List<Node> graphSoluces = graphSoluce.getSoluce();
		
		for (int i = 1; i < graphSoluces.size(); i++) {
			for (Map.Entry<Node, Direction> association : graphSoluces.get(i-1).getNeighbors().entrySet()) {
				if (graphSoluces.get(i) == association.getKey())
					dungeonSoluce.addDirection(association.getValue());
			}
		}
		
		return dungeonSoluce;
	}
	
	@Override
	public String toString() {
		return graph.toString();
	}

}
