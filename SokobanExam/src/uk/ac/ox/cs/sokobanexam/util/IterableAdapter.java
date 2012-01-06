package uk.ac.ox.cs.sokobanexam.util;

import java.util.Iterator;

/**
 * There are good reasons for the  Iterator interface to not extend Iterable,
 * however working with filters and maps i can come in as a pain.
 * This adapter will lessen the pain somewhat.
 */
public class IterableAdapter<T> implements Iterable<T> {
	Iterator<T> mIterator;
	public IterableAdapter(Iterator<T> iterator) {
		mIterator = iterator;
	}
	/**
	 * Returns the iterator wrapped by the adapter.
	 * Notice: Iterators returned from this class are NOT independent.
	 */
	public Iterator<T> iterator() {
		return mIterator;
	}
}
