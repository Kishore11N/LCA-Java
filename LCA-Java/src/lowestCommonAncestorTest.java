import static org.junit.Assert.*;

import org.junit.Test;

public class lowestCommonAncestorTest {

    public static void main(String[] args) 
    {
    
    }

    @Test

    public  void testFindLCA() {
        lowestCommonAncestor tree = new lowestCommonAncestor(); 
        tree.root = new Node(1); 
        tree.root.left = new Node(2); 
        tree.root.right = new Node(3); 
        tree.root.left.left = new Node(4); 
        tree.root.left.right = new Node(5); 
        tree.root.right.left = new Node(6); 
        tree.root.right.right = new Node(7); 
        
       assertEquals(2, tree.findLCA(4,5));
       assertEquals(1, tree.findLCA(4,6));
       assertEquals(1, tree.findLCA(3,4));
       assertEquals(2, tree.findLCA(2,4));

       

    }
}
