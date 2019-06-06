package assign08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * The class represents a binary search tree by using nodes in the class called
 * BinaryNode.
 * 
 * @author Kyle Perry, Erdi Fan
 *
 */

public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {

	private BinaryNode<Type> root;
	private int size = 0;

	public BinarySearchTree() {
		root = null;
		size = 0;
	}

	@Override
	public boolean add(Type item) {
		if (root == null) {
			// Create a new node
			root = createNewNode(item);
		} else {
			// Locate the parent node
			BinaryNode<Type> parent = null;
			BinaryNode<Type> current = root;
			while (current != null) {
				if (item.compareTo(current.element) < 0) {
					parent = current;
					current = current.left;
				} else if (item.compareTo(current.element) > 0) {
					parent = current;
					current = current.right;
				} else
					return false; // Duplicate node not inserted
			}
			// Create new node and attach it to the parent node
			if (item.compareTo(parent.element) < 0) {
				parent.left = createNewNode(item);
			} else
				parent.right = createNewNode(item);
		}
		size++;
		return true; // Element inserted successfully
	}

	/**
	 * Helper method for creating a new node
	 * 
	 * @param item
	 * @return
	 */
	private BinaryNode<Type> createNewNode(Type item) {
		return new BinaryNode<>(item);
	}

	@Override
	public boolean addAll(Collection<? extends Type> items) {
		// Flag for checking if there are elements added
		boolean hasAdded = false;
		for (Type e : items) {
			if (add(e))
				hasAdded = true;
		}
		return hasAdded;
	}

	@Override
	public void clear() {
		root = null;
		size = 0;
	}

	@Override
	public boolean contains(Type item) {
		// Start from the root
		BinaryNode<Type> current = root;
		while (current != null) {
			if (item.compareTo(current.element) < 0) {
				current = current.left;
			} else if (item.compareTo(current.element) > 0) {
				current = current.right;
			} else
				return true; // item matches current.element, it is found
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<? extends Type> items) {
		for (Type e : items) {
			if (!contains(e))
				return false;
		}
		return true;
	}

	@Override
	public Type first() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException();
		return findMin(root).element;
	}

	/**
	 * Helper method for finding the smallest element in the tree
	 * 
	 * @param temp
	 * @return
	 */
	private BinaryNode<Type> findMin(BinaryNode<Type> temp) {
		if (temp.left != null) {
			return findMin(temp.left);
		}
		return temp;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Type last() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException();
		return findMax(root).element;
	}

	/**
	 * Helper method for finding the largest element in the tree
	 * 
	 * @param temp
	 * @return
	 */
	private BinaryNode<Type> findMax(BinaryNode<Type> temp) {
		if (temp.right != null) {
			return findMax(temp.right);
		}
		return temp;
	}

	@Override
	public boolean remove(Type item) {
		// Locate the node to be deleted and also locate its parent node
		BinaryNode<Type> parent = null;
		BinaryNode<Type> current = root;
		while (current != null) {
			if (item.compareTo(current.element) < 0) {
				parent = current;
				current = current.left;
			} else if (item.compareTo(current.element) > 0) {
				parent = current;
				current = current.right;
			} else
				break; // Element is in the tree pointed at by current
		}
		// Element is not in the tree
		if (current == null)
			return false;
		// Case 1: current has no left child
		if (current.left == null) {
			// Connect the parent with the right child of the current node
			if (parent == null) {
				root = current.right;
			} else {
				if (item.compareTo(parent.element) < 0) {
					parent.left = current.right;
				} else {
					parent.right = current.right;
				}
			}
		}
		// Case2: current has a left child
		else {
			// Locate the rightmost node in the left subtree of the current node and also
			// its parent
			BinaryNode<Type> parentOfRightMost = current;
			BinaryNode<Type> rightMost = current.left;
			while (rightMost.right != null) {
				// Keep going to the right
				parentOfRightMost = rightMost;
				rightMost = rightMost.right;
			}
			// Replace the element in current by the element in rightMost
			current.element = rightMost.element;
			// Eliminate rightmost node
			if (parentOfRightMost.right == rightMost) {
				parentOfRightMost.right = rightMost.left;
			} else {
				// Special case: parentOfRightMost is the same as current
				parentOfRightMost.left = rightMost.left;
			}
		}
		size--;
		return true;
	}

	@Override
	public boolean removeAll(Collection<? extends Type> items) {
		// Flag for checking if there are elements removed
		boolean hasRemoved = false;
		for (Type e : items) {
			if (remove(e))
				hasRemoved = true;
		}
		return hasRemoved;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public ArrayList<Type> toArrayList() {
		// Container ArrayList
		ArrayList<Type> container = new ArrayList<>();
		return inorder(root, container);
	}

	/**
	 * Helper method using inorder sequence to put the elements in the ArrayList
	 * 
	 * @param temp
	 * @param container
	 * @return
	 */
	private ArrayList<Type> inorder(BinaryNode<Type> temp, ArrayList<Type> container) {
		if (temp == null)
			return container;
		inorder(temp.left, container);
		container.add(temp.element);
		inorder(temp.right, container);
		return container;
	}
}
