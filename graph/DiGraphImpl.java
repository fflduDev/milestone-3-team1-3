package graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class DiGraphImpl implements DiGraph {

	private List<GraphNode> nodeList = new ArrayList<>();

	@Override
	public Boolean addNode(GraphNode node) {
		if (!nodeList.contains(node) && !getValuesList().contains(node.getValue())) {
			nodeList.add(node);
			//System.out.println("added" + node.getValue());
			return true;
		}
		return false;
	}

	@Override
	public Boolean removeNode(GraphNode node) {

		if (nodeList.contains(node)) {
			nodeList.remove(node);
			System.out.println("removed" + node.getValue());
			for (GraphNode other : nodeList) {
				removeEdge(node, other);
				removeEdge(other, node);
			}

			return true;
		}
		return false;
	}

	@Override
	public Boolean setNodeValue(GraphNode node, String newNodeValue) {

		if (!getValuesList().contains(newNodeValue)) {
			GraphNode targetNode = getNode(node.getValue());
			targetNode.setValue(newNodeValue);
			//System.out.println("Set node value to" + targetNode.getValue());
			return true;
		}
		return false;
	}

	@Override
	public String getNodeValue(GraphNode node) {

		if (getValuesList().contains(node.getValue())) {
			//System.out.println("node: " + getNode(node.getValue()) + " value: " + node.getValue());
			return node.getValue();
		}
		return null;
	}

	@Override
	public Boolean addEdge(GraphNode fromNode, GraphNode toNode, Integer weight) {
		GraphNode targetFromNode = getNode(fromNode.getValue());
		GraphNode targetToNode = getNode(toNode.getValue());

		if (targetFromNode != null && targetToNode != null) {
			targetFromNode.addNeighbor(targetToNode, weight);
			//System.out.println("Added edge from " + targetFromNode.getValue() + " to " + targetToNode.getValue());
			return true;
		}
		return false;

	}

	@Override
	public Boolean addEdge(String fromNode, String toNode, int weight) {
		GraphNode targetFromNode = getNode(fromNode);
		GraphNode targetToNode = getNode(toNode);

		if (targetFromNode != null && targetToNode != null) {
			targetFromNode.addNeighbor(targetToNode, weight);
			//System.out.println("Added edge from " + targetFromNode.getValue() + " to " + targetToNode.getValue());

			return true;
		}
		return false;
	}

	@Override
	public Boolean removeEdge(GraphNode fromNode, GraphNode toNode) {
		GraphNode targetFromNode = getNode(fromNode.getValue());
		GraphNode targetToNode = getNode(toNode.getValue());

		if (targetFromNode != null && targetToNode != null) {
			targetFromNode.removeNeighbor(targetToNode);
			//System.out.println("Removed edge from " + targetFromNode.getValue() + " to " + targetToNode.getValue());
			return true;
		}
		return false;
	}

	@Override
	public Boolean setEdgeValue(GraphNode fromNode, GraphNode toNode, Integer newWeight) {
		GraphNode targetFromNode = getNode(fromNode.getValue());
		GraphNode targetToNode = getNode(toNode.getValue());

		if (targetFromNode.getNeighbors().contains(targetToNode)) {
			targetFromNode.removeNeighbor(targetToNode);
			addEdge(targetFromNode, targetToNode, newWeight);
			//System.out.println("set edge value from " + targetFromNode.getValue() + " to " + targetToNode.getValue()+ " is: " + targetFromNode.getDistanceToNeighbor(targetToNode));
			return true;
		}
		return false;
	}

	@Override
	public Integer getEdgeValue(GraphNode fromNode, GraphNode toNode) {
		//System.out.println("edge value from " + fromNode.getValue() + " to " + toNode.getValue() + " is: "+ fromNode.getDistanceToNeighbor(toNode));
		return fromNode.getDistanceToNeighbor(toNode);

	}

	@Override
	public List<GraphNode> getAdjacentNodes(GraphNode node) {
		GraphNode targetNode = getNode(node.getValue());
		//System.out.println(targetNode.getValue() + "'s adjacent nodes are: ");
		targetNode.printNeighbors();
		return targetNode.getNeighbors();
	}

	@Override
	public Boolean nodesAreAdjacent(GraphNode fromNode, GraphNode toNode) {
		GraphNode targetFromNode = getNode(fromNode.getValue());
		GraphNode targetToNode = getNode(toNode.getValue());
		//System.out.println("nodes " + targetFromNode.getValue() + " & " + targetToNode.getValue() + "are adjacent?"	+ targetFromNode.getNeighbors().contains(targetToNode));
		return targetFromNode.getNeighbors().contains(targetToNode);

	}

	@Override
	public Boolean nodeIsReachable(GraphNode fromNode, GraphNode toNode) {
		GraphNode targetFromNode = getNode(fromNode.getValue());// fix
		GraphNode targetToNode = getNode(toNode.getValue());

		if (targetFromNode == null || targetToNode == null) {
			return false;
		}
		if (targetFromNode.getValue().equals(targetToNode.getValue())) {
			return true;
		}

		Queue<GraphNode> queue = new LinkedList<GraphNode>();
		List<GraphNode> visited = new ArrayList<>();
		queue.add(targetFromNode);
		visited.add(targetFromNode);
		while (!queue.isEmpty()) {

			GraphNode element = queue.remove();
			
			List<GraphNode> neighbours = element.getNeighbors();
			
			for (int i = 0; i < neighbours.size(); i++) {
				GraphNode n = neighbours.get(i);
				if (n != null && !visited.contains(n)) {
					queue.add(n);
					visited.add(n);
				}
				if (n.getValue().equals(targetToNode.getValue())) {
					//System.out.println(targetToNode.getValue() + "is reachable from " + targetFromNode.getValue());
					return true;
				}

			}
		}
		
		return false;

	}

	@Override
	public Boolean hasCycles() {
		for (GraphNode node : nodeList) {
			if (nodeIsReachable(node, node)) {
				//System.out.println("Graph has cycles");
				return true;
			}
		}
		return false;
	}

	@Override
	public Boolean hasCycles(GraphNode node) {
		GraphNode targetNode = getNode(node.getValue());
		if (nodeIsReachable(targetNode, targetNode)) {
			//System.out.println(targetNode.getValue() + "has cycles");
			return true;
		}
		
		return false;
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

				if (!visitedNodes.contains(neighbor.getValue())) {
					queue.add(neighbor);
					visitedNodes.add(neighbor.getValue());
					hops.put(neighbor, hops.get(current) + 1);

				}

			}

		}

		return -1;

	}

	@Override
	public int shortestPath(GraphNode fromNode, GraphNode toNode) {
		GraphNode start = getNode(fromNode.getValue());
		GraphNode target = getNode(toNode.getValue());

		if (start == null || target == null) {return -1;}
		Map<GraphNode, Integer> paths = new HashMap<>();
		paths.put(start, 0);
		
		PriorityQueue<GraphNode> queue = new PriorityQueue<>(Comparator.comparingInt(paths::get));//https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/
		queue.add(start);

		while (!queue.isEmpty()) {
			GraphNode current = queue.poll();
			int currentDist = paths.get(current);

			if (current.equals(target)) {
				return currentDist;
			}
			
			
			for (GraphNode neighbor : current.getNeighbors()) {
				int weight = current.getDistanceToNeighbor(neighbor);
				int newDist = currentDist + weight;

				if (!paths.containsKey(neighbor) || newDist < paths.get(neighbor)) {
					paths.put(neighbor, newDist);
					queue.add(neighbor);
				}
			}
		}

		return -1;
	}

	public List<String> getValuesList() {
		List<String> values = new ArrayList<>();
		nodeList.forEach(n -> values.add(n.getValue()));
		return values;
	}

}
