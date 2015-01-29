package set;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class MaxSet<E extends Comparable<E>> extends ArraySet<E> {
	private E maxElement;

	/**
	 * Constructs a new empty set.
	 */
	public MaxSet() {
		super();
	}

	/**
	 * Returns the currently largest element in this set. pre: the set is not
	 * empty post: the set is unchanged
	 * 
	 * @return the currently largest element in this set
	 * @throws NoSuchElementException
	 *             if this set is empty
	 */
	public E getMax() {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		return maxElement;
	}

	/**
	 * Adds the specified element to this set, if it is not already present.
	 * post: x is added to the set if it is not already present
	 * 
	 * @param x
	 *            the element to be added
	 * @return true if the specified element was added
	 */
	public boolean add(E x) {
		if (super.data.contains(x)) {
			return false;
		}
		if (super.data.isEmpty()) {
			maxElement = x;
		} else if (maxElement.compareTo(x) < 0) {
			maxElement = x;
		}
		super.data.add(x);
		return true;
	}

	/**
	 * Removes the specified element from this set if it is present. post: x is
	 * removed if it was present
	 * 
	 * @param x
	 *            the element to remove - if present
	 * @return true if the set contained the specified element
	 */
	public boolean remove(Object x) {
		E a;
		if (data.contains(x)) {
			if (x.equals(maxElement)) {
				data.remove(x);
				if (!data.isEmpty()) {
					maxElement = super.data.get(0);
					int b = super.data.size();
					for (int i = 1; i < b; i++) {
						a = super.data.get(i);
						if (maxElement.compareTo(a) < 0) {
							maxElement = a;
						}
					}
				}
			}else {
				data.remove(x);
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Adds all of the elements in the specified set, for which it is possible,
	 * to this set. post: all elements, for which it is possible, in the
	 * specified set are added to this set.
	 * 
	 * @return true if this set changed as a result of the call
	 */
	public boolean addAll(SimpleSet<? extends E> c) {
		Iterator<? extends E> itr = c.iterator();
		boolean changed = false;
		while (itr.hasNext()) {
			E a = itr.next();
			if (add(a)) {
				changed = true;
				if (maxElement.compareTo(a) > 0) {
					maxElement = a;
				}
			}
		}
		return changed;
	}
}
