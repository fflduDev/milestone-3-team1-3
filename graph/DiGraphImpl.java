package graph;

import java.util.ArrayList;
import java.util.List;
 

public class DiGraphImpl implements DiGraph{

	private List<GraphNode> nodeList = new ArrayList<>();

	@Override
	public Boolean addNode(GraphNode node) {
		if(nodeList.contains(node)) {return false;}
		else {
		nodeList.add(node);//fix later
		System.out.println("added"+node.getValue());//DELETE later
		return true;
		}
		
	}

	@Override
	public Boolean removeNode(GraphNode node) {
		
		if(nodeList.contains(node)) {
			nodeList.remove(node);
			System.out.println("removed"+node.getValue());//DELETE later
			return true;
		}
		return false;
	}

	@Override
	public Boolean setNodeValue(GraphNode node, String newNodeValue) {
		// TODO Auto-generated method stub
		if(nodeList.contains(node)) {
			node.setValue(newNodeValue);
			return true;
		}
		return false;
	}

	@Override
	public String getNodeValue(GraphNode node) {
		// TODO Auto-generated method stub
		if(nodeList.contains(node)) {
			return node.getValue();
		}
		return null;
	}

	@Override
	public Boolean addEdge(GraphNode fromNode, GraphNode toNode, Integer weight) {
		// TODO Auto-generated method stub
		if(!nodeList.contains(fromNode)) {addNode(fromNode);}
		if(!nodeList.contains(toNode)) {addNode(toNode);}
		fromNode.addNeighbor(toNode, weight);
		return fromNode.addNeighbor(toNode, weight);//??
	}

	@Override
	public Boolean removeEdge(GraphNode fromNode, GraphNode toNode) {
		// TODO Auto-generated method stub
		if(nodeList.contains(fromNode)&&nodeList.contains(toNode)) {
			fromNode.removeNeighbor(toNode);
			System.out.println("Removed edge from " + fromNode.getValue() + " to " + toNode.getValue()); // DELETE later
			return true;
		}
		return false;
	}

	@Override
	public Boolean setEdgeValue(GraphNode fromNode, GraphNode toNode, Integer newWeight) {
		// TODO Auto-generated method stub
		
			
		
		return null;
	}

	@Override
	public Integer getEdgeValue(GraphNode fromNode, GraphNode toNode) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
}
