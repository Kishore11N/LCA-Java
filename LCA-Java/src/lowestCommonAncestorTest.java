import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class lowestCommonAncestorTest {

    public static void main(String[] args) {

    }

    @Test

    public void testFindLCA() {
        lowestCommonAncestor tree = new lowestCommonAncestor();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(7);

        assertEquals(2, tree.findLCA(4, 5));
        assertEquals(1, tree.findLCA(4, 6));
        assertEquals(1, tree.findLCA(3, 4));
        assertEquals(2, tree.findLCA(2, 4));

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

        assertEquals(true, tree.findPath(tree.root, 1, path));
        assertEquals(true,tree.findPath(tree.root.left,5,path));
        assertEquals(true,tree.findPath(tree.root.right,7,path));    
        assertEquals(false,tree.findPath(tree.root.right,8,path));
    }
}