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

	
	
	
	
}
