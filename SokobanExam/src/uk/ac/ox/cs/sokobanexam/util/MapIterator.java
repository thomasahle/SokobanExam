package uk.ac.ox.cs.sokobanexam.util;

import java.util.Iterator;

/**
 * A simple map iterator allowing you to map objects
 * using a template method.
 */
public abstract class MapIterator<A,B> implements Iterator<B> {
    private Iterator<A> mIterator;
    
    public MapIterator(Iterator<A> iterator) {
        mIterator = iterator;
    }
    
    public abstract B applyMap(A elem);
    
    public boolean hasNext() {
        return mIterator.hasNext();
    }
    
    public B next() {
        return applyMap(mIterator.next());
    }
    
    public void remove() {
        mIterator.remove();
    }
}