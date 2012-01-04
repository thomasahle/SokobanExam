package uk.ac.ox.cs.sokobanexam.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class FilterIterator<T> implements Iterator<T> {
    private Iterator<T> mIterator;
    private T nextObject;
    
    public FilterIterator(Iterator<T> iterator) {
        mIterator = iterator;
    }
    
    public abstract boolean isGood(T elem);
    
    public boolean hasNext() {
        return nextObject != null || findNextElem();
    }
    
    public T next() {
        if (nextObject == null && !findNextElem())
            throw new NoSuchElementException();
        return nextObject;
    }
    
    public void remove() {
        if (nextObject == null)
            throw new IllegalStateException();
        mIterator.remove();
    }
    
    private boolean findNextElem() {
    	while (mIterator.hasNext()) {
            T elem = mIterator.next();
            if (isGood(elem)) {
                nextObject = elem;
                return true;
            }
        }
        return false;
    }
}