package sae.graph;

import java.util.ArrayList;
import java.util.List;

import sae.dungeon.Direction;

public class Graph {
	
	private List<Node> nodes;
	
	public Graph() {
		nodes = new ArrayList<Node>();
	}
	
	public void addNode(Node node) {
		// Ajoute un noeud dans le graphe
		nodes.add(node);
	}
	
	public void addEdge(Node node1, Node node2, Direction directionN1ToN2, Direction directionN2ToN1) {
		node1.addNeighbour(node2, directionN1ToN2);
		node2.addNeighbour(node1, directionN2ToN1);
	}
	
	public List<Node> getNodes() {
		return nodes;
	}
	
	@Override
	public String toString() {
		String result = "";
		for (Node node : nodes) {
			result += node.toString() + " ";
		}
		return result;
	}
	
	public int getNumberOfNodes() {
		return nodes.size();
	}

}
