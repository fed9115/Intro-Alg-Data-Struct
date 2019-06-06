package lab09;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class represents a node of a binary tree.
 * 
 * Since the data structure is recursively defined, a single binary node
 * designated as the root of a tree represents a binary tree.
 * 
 * @author Erin Parker & Tina Ziemek
 * @version March 8, 2019
 */
public class BinaryNode<T> {

	private T element;

	private BinaryNode<T> left; // NOTICE the recursion

	private BinaryNode<T> right;

	/**
	 * Creates a binary node with left and right nodes as children.
	 * 
	 * @param element - data stored at this node
	 * @param left    - left child node
	 * @param right   - right child node
	 */
	public BinaryNode(T element, BinaryNode<T> left, BinaryNode<T> right) {
		this.element = element;
		this.left = left;
		this.right = right;
	}

	/**
	 * Creates a binary node with no children.
	 * 
	 * @param element
	 */
	public BinaryNode(T element) {
		this(element, null, null);
	}

	/**
	 * @return the data stored at this node
	 */
	public T element() {
		return element;
	}

	/**
	 * Set the data stored at this node.
	 * 
	 * @param element - data
	 */
	public void resetElement(T element) {
		this.element = element;
	}

	/**
	 * @return the left child of this node
	 */
	public BinaryNode<T> left() {
		return left;
	}

	/**
	 * @return the right child of this node
	 */
	public BinaryNode<T> right() {
		return right;
	}

	/**
	 * @return number of nodes in the tree rooted at "this" node
	 * 
	 *         IS THIS EFFICIENT? How else can we implement size()?
	 */
	public int size() {
		int size = 1; // count this node

		if (left != null)
			size += left.size(); // count all of the nodes in the left subtree

		if (right != null)
			size += right.size(); // count all of the nodes in the right subtree

		return size;
	}

	/**
	 * @return copy of the tree rooted at "this" node
	 */
	public BinaryNode<T> duplicate() {
		BinaryNode<T> copyLeft = null;
		if (left != null)
			copyLeft = left.duplicate(); // get copy of left subtree

		BinaryNode<T> copyRight = null;
		if (right != null)
			copyRight = right.duplicate(); // get copy of right subtree

		// combine left and right in a new node w/ element
		return new BinaryNode<T>(this.element, copyLeft, copyRight);
	}

	/**
	 * Prints the elements of the tree rooted at "this" node, using preorder
	 * (element followed by left subtree followed by right subtree) traversal.
	 */
	public void printPreorder() {
		System.out.println(this.element.toString());
		if (this.left != null)
			this.left.printPreorder();
		if (this.right != null)
			this.right.printPreorder();
	}

	/**
	 * Prints the elements of the tree rooted at "this" node, using inorder (left
	 * subtree followed by element followed by right subtree) traversal.
	 */
	public void printInorder() {
		if (this.left != null)
			this.left.printInorder();
		System.out.println(this.element.toString());
		if (this.right != null)
			this.right.printInorder();
	}

	/**
	 * Prints the elements of the tree rooted at "this" node, using postorder (left
	 * subtree followed by right subtree followed by element) traversal.
	 */
	public void printPostorder() {
		if (this.left != null)
			this.left.printInorder();

		if (this.right != null)
			this.right.printInorder();
		System.out.println(this.element.toString());
	}

	/**
	 * Prints the elements of the tree rooted at "this" node, using level-order
	 * (breadth-first) traversal.
	 */
	public void printLevelorder() {
		Queue<BinaryNode<T>> q = new LinkedList<BinaryNode<T>>();

		q.offer(this);

		while (!q.isEmpty()) {
			BinaryNode<T> c = q.poll();
			System.out.println(c.element.toString());
			if (c.left != null)
				q.offer(c.left);
			if (c.right != null)
				q.offer(c.right);
		}
	}

	/**
	 * @return a string containing all of the edges in the tree rooted at "this"
	 *         node, in DOT format
	 */
	private String generateDot() {
		String ret = "\tnode" + element + " [label = \"<f0> |<f1> " + element + "|<f2> \"]\n";

		if (left != null)
			ret += "\tnode" + element + ":f0 -> node" + left.element() + ":f1\n" + left.generateDot();

		if (right != null)
			ret += "\tnode" + element + ":f2 -> node" + right.element() + ":f1\n" + right.generateDot();

		return ret;
	}

	/**
	 * Public driver method for generating DOT file for a binary tree.
	 * 
	 * @param filename - name of DOT file to be generated
	 * @param root     - binary node that is the root of the binary tree
	 */
	public static <T> void generateDotFile(String filename, BinaryNode<T> root) {
		try {
			PrintWriter out = new PrintWriter(filename);
			out.println("digraph Tree {\n\tnode [shape=record]\n");

			if (root == null)
				out.println("");
			else
				out.print(root.generateDot());

			out.println("}");
			out.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
