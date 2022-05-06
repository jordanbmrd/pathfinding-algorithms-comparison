package sae.solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import sae.graph.GraphSoluce;
import sae.graph.Node;

public class SolverWithAstar implements Solver {

	private GraphSoluce soluce;
	private Map<Node, Node> pi;
	
	List<Node> closedList;
	List<Node> openList;
	
	private Node start, end;
	private int steps;

	public SolverWithAstar(Node nodeA, Node nodeB) {
		this.soluce = new GraphSoluce();
		this.start = nodeA;
		this.end = nodeB;
		
		this.closedList = new ArrayList<>();
		this.openList = new ArrayList<>();
		
		this.pi = new LinkedHashMap<Node, Node>();
		this.steps = 0;
	}

	@Override
	public void resolve() {
		/*
		 * 
		 * Cette fonction résout un graphe en utilisant l'algorithme A*
		 * 
		 * */
		
		int xB = end.getCoord().getX();
		int yB = end.getCoord().getY();
		
		openList.add(start);
		
		while (!openList.isEmpty()) {
			Node current = getLowestFunctionNode();
			
			// Si on est arrivé au point d'arrivée, on arrête la fonction
			if (current.equals(end)) return;
			
			openList.remove(current);
			closedList.add(current);
			
			for (Node n : current.getNeighbors().keySet()) {	// Pour chaque Node voisin de current
				if (closedList.contains(n)) continue;
				
				double tempScore = current.getCost() + Math.sqrt((xB - n.getCoord().getX())^2 + (yB - n.getCoord().getY())^2);
				
				if (openList.contains(n)) {
					if (tempScore < n.getCost()) {
						n.setCost(tempScore);
						pi.put(n, current);
					}
				}
				else {
					n.setCost(tempScore);
					openList.add(n);
					pi.put(n, current);
				}
				steps++;
				
				n.setHeuristique(Math.sqrt((xB - n.getCoord().getX())^2 + (yB - n.getCoord().getY())^2));
				n.setFunction(n.getCost() + n.getHeuristique());
			}
		}
	}

	@Override
	public GraphSoluce getSoluce() {
		/*
		 * 
		 * Cette fonction renvoie la solution du graphe après avoir été résolu
		 * avec l'algorithme A*
		 * 
		 * */
		
		soluce = new GraphSoluce();
		List<Node> solution = new ArrayList<Node>();
		
		Node temp = end;
		
		solution.add(end);
		
		for (int i = 0; i < pi.size(); i++) {
			for (Map.Entry<Node, Node> lien : pi.entrySet()) {
				if (lien.getKey().getName() == temp.getName()) {
					solution.add(lien.getValue());
					temp = lien.getValue();
				}
			}
		}
		
		Collections.reverse(solution);
		solution.forEach((node) -> soluce.add(node));
		
		return soluce;
	}

	@Override
	public int getSteps() {
		return steps;
	}
	
	private Node getLowestFunctionNode() {
		/*
		 * 
		 * Cette fonction renvoie le noeud avec le 
		 * 
		 * */
		Node lowest = openList.get(0);
		
		for (Node n : openList) {
			if (n.getFunction() < lowest.getFunction()) {
				lowest = n;
			}
		}
		
		return lowest;
	}

}
