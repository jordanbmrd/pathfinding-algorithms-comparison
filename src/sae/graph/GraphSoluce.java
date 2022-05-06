package sae.graph;

import java.util.LinkedList;
import java.util.List;

public class GraphSoluce {
	
	private List<Node> soluce;
	
	public GraphSoluce() {
		soluce = new LinkedList<Node>();
	}
	
	public void add(Node node) {
		soluce.add(node);
	}
	
	public List<Node> getSoluce() {
		return soluce;
	}
	
	@Override
	public String toString() {
		String result = "";
		for (Node node : soluce) {
			result += node.toString() + " ";
		}
		return result;
	}

}
