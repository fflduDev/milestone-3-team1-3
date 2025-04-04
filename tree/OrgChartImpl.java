package tree;  

import java.util.ArrayList;  
import java.util.LinkedList;  
import java.util.List;  
import java.util.Queue;  

public class OrgChartImpl implements OrgChart {  
    private GenericTreeNode<Employee> root;  

    @Override  
    public void addRoot(Employee e) {  
        if (root == null) {  
            root = new GenericTreeNode<>(e);  
        } else {  
            throw new IllegalStateException("Root already exists");  
        }  
    }  

    @Override  
    public void clear() {  
        root = null;  
    }  

    @Override  
    public void addDirectReport(Employee manager, Employee newPerson) {  
        GenericTreeNode<Employee> managerNode = findNode(root, manager);  
        if (managerNode != null) {  
            managerNode.addChild(new GenericTreeNode<>(newPerson));
        } else {  
            throw new IllegalArgumentException("Manager not found");  
        }  
    }  

    @Override  
    public void removeEmployee(Employee firedPerson) {  
        removeNode(root, firedPerson); 
    }  

    @Override  
    public void showOrgChartDepthFirst() {  
        System.out.println("Org Chart (DFS) of the company is:");  
        depthFirstTraversal(root, "– "); 
        System.out.println(); 
    }  

    @Override  
    public void showOrgChartBreadthFirst() {  
        System.out.println("Org Chart (BFS) of the company is:");  
        breadthFirstTraversal(root); 
        System.out.println(); 
    }  

    // Find a specific node
    private GenericTreeNode<Employee> findNode(GenericTreeNode<Employee> node, Employee e) {  
        if (node == null) return null;  
        
        // compare node  
        if (node.data.equals(e)) return node;  

        //Traverse the child node
        if (node.children != null) {  
            for (GenericTreeNode<Employee> child : node.children) {  
                GenericTreeNode<Employee> found = findNode(child, e);  
                if (found != null) {  
                    return found;  
                }  
            }  
        }  
        return null;  
    }  

    // Remove a specific node 
    private void removeNode(GenericTreeNode<Employee> node, Employee e) {  
        if (node == null) return;  
        
        // Traverse the child node
        if (node.children != null) {  
            for (int i = 0; i < node.children.size(); i++) {  
                GenericTreeNode<Employee> child = node.children.get(i);  
                if (child.data.equals(e)) {  
                    // Move the child node to the parent node  
                    node.children.remove(i);  
                    node.children.addAll(i, child.children);  
                    return;  
                }  
                removeNode(child, e);  
            }  
        }  
    }  

  
    private void depthFirstTraversal(GenericTreeNode<Employee> node, String prefix) {  
        if (node == null) return; 
        System.out.println(prefix + node.data); 
        if (node.children != null) {  
            for (GenericTreeNode<Employee> child : node.children) {  
            	// Traverses the child node
            	depthFirstTraversal(child, prefix + "– "); 
            }  
        }  
    }  
 
    
    private void breadthFirstTraversal(GenericTreeNode<Employee> root) {  
        if (root == null) return; 
        Queue<GenericTreeNode<Employee>> queue = new LinkedList<>();  
        queue.add(root);  

        while (!queue.isEmpty()) {  
            GenericTreeNode<Employee> current = queue.poll();  
            System.out.print(current.data + " ");  
            if (current.children != null) {  
            	//Add a child node
            	queue.addAll(current.children); 
            }  
        }  
        System.out.println();   
    }  
}  