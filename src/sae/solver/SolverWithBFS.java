package sae.solver;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import sae.graph.GraphSoluce;
import sae.graph.Node;

public class SolverWithBFS implements Solver {

	private GraphSoluce soluce;
	private Queue<Node> queue;
	private Node start, end;
	private Map<Node, Node> pi;
	private int steps;
	
	public SolverWithBFS(Node nodeA, Node nodeB) {
		this.soluce = new GraphSoluce();
		this.start = nodeA;
		this.end = nodeB;
		this.pi = new LinkedHashMap<Node, Node>();
		this.steps = 0;
	}

	@Override
	public void resolve() {
		/*
		 * 
		 * Cette fonction résout un graphe en utilisant l'algorithme BFS (Breadth-First-Search)
		 * 
		 * */
		
		queue = new ArrayDeque<Node>();
		
		start.visited = true;
		queue.add(start);
		
		while (!queue.isEmpty()) {			
			Node actuel = queue.poll();
			
			actuel.getNeighbors().forEach((voisin, direction) -> {
				if (!voisin.visited) {
					queue.add(voisin);
					pi.put(voisin, actuel);
					steps++;
					voisin.visited = true;
				}
			});
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
		
		List<Node> nodes = new ArrayList<Node>();
		List<Node> voisins = new ArrayList<Node>();
		List<Node> solution = new ArrayList<Node>();
		
		soluce = new GraphSoluce();
	
		pi.forEach((v, n) -> {
			nodes.add(n);
			voisins.add(v);
		});
		
		solution.add(end);

		Node actuel = end;
		for (int i = nodes.size() - 1; i >= 0; i--) {
			if (voisins.get(i) == actuel) {
				actuel = nodes.get(i);
				solution.add(actuel);
			}
		}
		
		Collections.reverse(solution);
		
		for(Node node : solution) {
			soluce.add(node);
		}
		
		return soluce;
	}

	@Override
	public int getSteps() {
		return this.steps;
	}

}
