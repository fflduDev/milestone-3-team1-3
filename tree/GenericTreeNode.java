package tree;  

import java.util.ArrayList;  

public class GenericTreeNode<E> {  
    E data;  
    ArrayList<GenericTreeNode<E>> children;  

    public GenericTreeNode(E theItem) {  
        data = theItem;  
        children = new ArrayList<>(); // Initialize the children list  
    }  

    public void addChild(GenericTreeNode<E> theItem) {  
        children.add(theItem);  
    }  

    public void removeChild(E theItem) {  
        children.removeIf(child -> child.data.equals(theItem));  
    }  
    
    // Optionally, you can add a method to get the data  
    public E getData() {  
        return data;  
    }  
}  
