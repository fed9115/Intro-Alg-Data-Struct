package assign08;

/**
 * Create a class for nodes in the BST.
 * 
 * @author Kyle Perry, Erdi Fan
 *
 */

public class BinaryNode<Type> {
	// Variables needed in a node
	public Type element;
	public BinaryNode<Type> left;
	public BinaryNode<Type> right;

	public BinaryNode(Type e) {
		element = e;
	}
}
