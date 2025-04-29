package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import template.GraphNode;

public class DiGraphImpl implements DiGraph {

	private List<GraphNode> nodeList = new ArrayList<>();

	@Override
	public Boolean addNode(GraphNode node) {
		if (nodeList.contains(node)) {
			return false;
		} else {
			nodeList.add(node);// fix later
			System.out.println("added" + node.getValue());// DELETE later
			return true;
		}

	}

	@Override
	public Boolean removeNode(GraphNode node) {

		if (nodeList.contains(node)) {
			nodeList.remove(node);
			System.out.println("removed" + node.getValue());// DELETE later
			for (GraphNode other : nodeList) {
				removeEdge(node, other);
			}

			return true;
		}
		return false;
	}

	@Override
	public Boolean setNodeValue(GraphNode node, String newNodeValue) {
		List<String> values = new ArrayList<String>();
		nodeList.forEach(n -> values.add(n.getValue()));
		if (!values.contains(newNodeValue)) {
			node.setValue(newNodeValue);// ??
			return true;
		}
		return false;

	}

	@Override
	public String getNodeValue(GraphNode node) {

		if (nodeList.contains(node)) {
			return node.getValue();
		}
		return null;
	}

	@Override
	public Boolean addEdge(GraphNode fromNode, GraphNode toNode, Integer weight) {
		if (nodeList.contains(fromNode) && nodeList.contains(toNode)) {
			fromNode.addNeighbor(toNode, weight);
			return true;
		}
		return false;
	}

	@Override
	public Boolean removeEdge(GraphNode fromNode, GraphNode toNode) {

		if (nodeList.contains(fromNode) && nodeList.contains(toNode)) {
			fromNode.removeNeighbor(toNode);
			System.out.println("Removed edge from " + fromNode.getValue() + " to " + toNode.getValue()); // DELETE later
			return true;
		}
		return false;
	}

	@Override
	public Boolean setEdgeValue(GraphNode fromNode, GraphNode toNode, Integer newWeight) {

		if (fromNode.getNeighbors().contains(toNode)) {
			fromNode.removeNeighbor(toNode);
			addEdge(fromNode, toNode, newWeight);
			return true;
		}
		return false;
	}

	@Override
	public Integer getEdgeValue(GraphNode fromNode, GraphNode toNode) {
		return fromNode.getDistanceToNeighbor(toNode);// ??

	}

	@Override
	public int fewestHops(GraphNode fromNode, GraphNode toNode) {
		GraphNode targetFromNode = getNode(fromNode.getValue());
		GraphNode targetToNode = getNode(toNode.getValue());

		Queue<GraphNode> queue = new LinkedList<>();
		queue.add(targetFromNode);

		Set<String> visitedNodes = new HashSet<>();
		visitedNodes.add(targetFromNode.getValue());

		Map<GraphNode, Integer> hops = new HashMap<>();
		hops.put(targetFromNode, 0);

		while (queue.peek() != null) {
			GraphNode current = queue.poll();

			if (current.getValue().equals(targetToNode.getValue())) {

				return hops.get(current);
			}

			for (GraphNode neighbor : current.getNeighbors()) {

				// manage visiting each node only once
				if (!visitedNodes.contains(neighbor.getValue())) {
					queue.add(neighbor);
					visitedNodes.add(neighbor.getValue());
					hops.put(neighbor, hops.get(current) + 1);
					// increment hops

				}

			}

		}

		return -1;


	}

	@Override
	public int shortestPath(GraphNode fromNode, GraphNode toNode) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<GraphNode> getNodes() {

		return nodeList;

	}

	public GraphNode getNode(String nodeValue) {
		for (GraphNode node : nodeList) {
			if (node.getValue().equals(nodeValue)) {
				return node;
			}
		}
		return null;
	}

	@Override
	public Boolean addEdge(String fromNode, String toNode, int weight) {
		List<String> values = new ArrayList<String>();
		nodeList.forEach(n -> values.add(n.getValue()));
		if (values.contains(fromNode) && values.contains(toNode)) {
			getNode(fromNode).addNeighbor(getNode(toNode), weight);
			return true;
		}
		return false;
	}

	@Override
	public List<GraphNode> getAdjacentNodes(GraphNode node) {
		return node.getNeighbors();
	}

	@Override
	public Boolean nodesAreAdjacent(GraphNode fromNode, GraphNode toNode) {
		for(GraphNode node:fromNode.getNeighbors()) {
			if(node.getValue().equals(toNode.getValue())) {return true;}
		}
		return false;
	}

	@Override
	public Boolean nodeIsReachable(GraphNode fromNode, GraphNode toNode) {
		return null;
		
	}

	@Override
	public Boolean hasCycles() {
		// TODO Auto-generated method stub
		return null;
	}
}
