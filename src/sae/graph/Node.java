package sae.graph;

import java.util.HashMap;
import java.util.Map;

import sae.dungeon.Coord;
import sae.dungeon.Direction;

public class Node {
	
	private String name;
	private Map<Node, Direction> neighbors;
	private Coord coord;
	
	// Pour l'algorithme A*
	// function = Fonction heuristique
	// cost = coût réel du chemin du point de départ au noeud de destination
	// heuristique = coût estimé du chemin du noeud actuel au de destination
	private double heuristique, function, cost;
	
	public boolean visited = false;
	
	public Node(String name, Coord coord) {
		/*
		 * 
		 * Définition de l'objet NOEUD
		 * Un noeud est un élément d'un graphe
		 * 
		 * */
		
		this.name = name;
		this.neighbors = new HashMap<Node, Direction>();
		this.coord = coord;
	}
	
	public Map<Node, Direction> getNeighbors() {
		// Renvoie les voisins du noeud
		return neighbors;
	}
	
	public void setHeuristique(double heuristique) {
		// Modifie la valeur de l'heuristique du noeud
		this.heuristique = heuristique;
	}
	
	public void setFunction(double function) {
		// Modifie la valeur de la fonction heuristique
		this.function = function;
	}
	
	public double getFunction() {
		// Renvoie la fonction heuristique
		return function;
	}
	
	public double getHeuristique() {
		// Renvoie la valeur de l'heuristique
		return heuristique;
	}
	
	public double getCost() {
		// Renvoie le coût du noeud
		return cost;
	}
	
	public void setCost(double newCost) {
		// Modifie la valeur du coût du noeud
		this.cost = newCost;
	}
	
	public void addNeighbour(Node node, Direction direction) {
		// Ajoute un nouveau voisin avec la direction vers celui-ci
		neighbors.put(node, direction);
	}
	
	public String getName() {
		// Renvoie le nom du noeud
		return name;
	}
	
	public Coord getCoord() {
		// Renvoie les coordonnées du noeud
		return coord;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
	
	@Override
	public boolean equals(Object obj) {
		Node otherNode = (Node) obj;
		return otherNode.getName() == this.getName() && otherNode.getCoord() == this.getCoord();
	}

}
