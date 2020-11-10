import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class lowestCommonAncestorTest {

    public static void main(String[] args) {

    }


    //Tests for LCA of Binary Tree

    @Test

    public void testFindLCA() {
        //Creating a new binary tree
        lowestCommonAncestor tree = new lowestCommonAncestor();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(7);

        //Testing to see if correct outputs are produced
        assertEquals(2, tree.findLCA(4, 5));
        assertEquals(1, tree.findLCA(4, 6));
        assertEquals(1, tree.findLCA(3, 4));
        assertEquals(2, tree.findLCA(2, 4));

        //Testing outputs when passing in invalid inputs
        assertEquals(-1,tree.findLCA(7,8));
        assertEquals(-1,tree.findLCA(-1,-2));


        //Creating a new binary tree with a null root
        lowestCommonAncestor tree1 = new lowestCommonAncestor();
        tree1.root = null;
        tree1.root.left = new Node(2);
        tree1.root.right = new Node(3);
        tree1.root.left.left = new Node(4);
        
        assertEquals(-1,tree1.findLCA(3,4));

    }

    @Test

    public void testFindPath() {
        lowestCommonAncestor tree = new lowestCommonAncestor();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(7);

        List<Integer> path = new ArrayList<>();

        //Testing for paths
        assertEquals(true, tree.findPath(tree.root, 1, path));
        assertEquals(true,tree.findPath(tree.root.left,5,path));
        assertEquals(true,tree.findPath(tree.root.right,7,path));    
        
        //Testing any error scenarios
        assertEquals(false,tree.findPath(null,7,path));
        assertEquals(false,tree.findPath(tree.root.right,8,path));
    }


   
    //Tests for LCA of a Directed Acyclic Graph

    @Test

    public void testConstructor(){
        //Creating a new Directed Acyclic Graph
        DAG Graph1 = new DAG(10);
        Graph1.addEdge(1,2);
        Graph1.addEdge(1,3);
        Graph1.addEdge(2,4);
        Graph1.addEdge(4, 6);
        Graph1.addEdge(3, 5);
        Graph1.addEdge(5, 7);
        Graph1.addEdge(5, 8);
        Graph1.addEdge(5, 9);

        //Testing getVertex
        assertEquals(10,Graph1.getVertex());

        //Testing getEdge
        assertEquals(8, Graph1.getEdge());

        //Testing for correct indegrees
        assertEquals(0,Graph1.indegree(-1));
        assertEquals(0,Graph1.indegree(1));
        assertEquals(1,Graph1.indegree(5));
        assertEquals(1,Graph1.indegree(7));

        //Testing for correct outdegrees
        assertEquals(0,Graph1.outdegree(-1));
        assertEquals(0,Graph1.outdegree(8));
        assertEquals(1,Graph1.outdegree(2));
        assertEquals(3,Graph1.outdegree(5));

        //Testing for correct adjacents
        assertEquals("[2, 3]",Graph1.adj(1).toString());
        assertEquals("[5]",Graph1.adj(3).toString());
        assertEquals("[7, 8, 9]",Graph1.adj(5).toString());


    }


    @Test

    public void testAddEdge(){
        DAG Graph2 = new DAG(5);
        Graph2.addEdge(1, 2);
        Graph2.addEdge(2, -3);
        Graph2.addEdge(-3, -4);
        
        //Testing for edges between non-valid vertices
        assertEquals(1,Graph2.getEdge());
        
        //Testing for edges between valid-vertices
        Graph2.addEdge(2, 3);
        Graph2.addEdge(3, 4);
        assertEquals(3,Graph2.getEdge());

    }
    @Test

    public void testCyclic(){
        
        //Creating a directed cyclic graph
        DAG Graph3 = new DAG(5);
        Graph3.addEdge(1, 2);
        Graph3.addEdge(2, 3);
        Graph3.addEdge(3, 4);
        Graph3.addEdge(4, 1);

        Graph3.findCycle(1);

        assertEquals(true, Graph3.hasCycle());

        //Creating a directed acyclic graph
        DAG Graph4 = new DAG(6);
        Graph4.addEdge(1, 2);
        Graph4.addEdge(2, 3);
        Graph4.addEdge(3, 4);
        Graph4.addEdge(4, 5);

        Graph4.findCycle(1);

        assertEquals(false, Graph4.hasCycle());

    }

    @Test

    public void testLCA(){
        //Creating a directed acyclic graph
        DAG Graph5 = new DAG (20);
        Graph5.addEdge(1, 2);
        Graph5.addEdge(1, 3);
        Graph5.addEdge(2, 4);
        Graph5.addEdge(4, 6);
        Graph5.addEdge(3, 5);
        Graph5.addEdge(5, 7);
        Graph5.addEdge(5, 8);
        Graph5.addEdge(7, 10);
        Graph5.addEdge(10, 9);
        Graph5.addEdge(10, 13);
        Graph5.addEdge(10, 11);
        Graph5.addEdge(11, 12);

        //Testing LCA of 2 vertices within DAG
        assertEquals(1,Graph5.findLCA(2, 3));
        assertEquals(2,Graph5.findLCA(2, 2));
        assertEquals(10,Graph5.findLCA(9, 10));
        assertEquals(10,Graph5.findLCA(11, 13));

        //Testing LCA of invalid vertices
        assertEquals(-1,Graph5.findLCA(0, 3));
        assertEquals(-1,Graph5.findLCA(-1, -3));

        //Creating a directed cyclic graph
        DAG Graph6 = new DAG (20);
        Graph6.addEdge(0, 1);
        Graph6.addEdge(0, 2);
        Graph6.addEdge(2, 3);
        Graph6.addEdge(3, 0);
        Graph6.addEdge(3, 4);
    

        //Testing to find LCA 
        assertEquals(-1,Graph6.findLCA(0, 1));
        assertEquals(-1,Graph6.findLCA(0, 2));
        assertEquals(-1,Graph6.findLCA(2, 3));
        assertEquals(-1,Graph6.findLCA(3, 4));


        //Creating an empty graph
        DAG Graph7 = new DAG(20);
        
        //Testing for LCA in empty DAG
        assertEquals(-1,Graph7.findLCA(1, 2));


       




    }


}