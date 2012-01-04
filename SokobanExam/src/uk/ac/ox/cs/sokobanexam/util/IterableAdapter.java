package uk.ac.ox.cs.sokobanexam.util;

import java.util.Iterator;

public class IterableAdapter<T> implements Iterable<T> {
	Iterator<T> mIterator;
	public IterableAdapter(Iterator<T> iterator) {
		mIterator = iterator;
	}
	public Iterator<T> iterator() {
		return mIterator;
	}
}
