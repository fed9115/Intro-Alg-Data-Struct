package lab05;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimplePriorityQ<E> implements Iterable<E> {

	private int size;
	E[] queue;

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new SimplePriorityQueueIterator();
	}

	private class SimplePriorityQueueIterator implements Iterator<E> {

		private int nextIndex;
		private boolean canRemove;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if (nextIndex < size) {
				return true;
			}
			return false;
		}

		@Override
		public E next() {
			// TODO Auto-generated method stub
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			nextIndex++;
			canRemove = true;
			return queue[nextIndex - 1];
		}

		@Override
		public void remove() {
			if (!canRemove) {
				throw new IllegalStateException();
			}
			canRemove = false;
			nextIndex--;
			for (int i = nextIndex; i < size - 1; i++) {
				queue[i] = queue[i + 1];
			}
			size--;
		}
	}
}
