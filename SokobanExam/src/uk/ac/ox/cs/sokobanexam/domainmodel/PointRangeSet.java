package uk.ac.ox.cs.sokobanexam.domainmodel;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.NoSuchElementException;

public class PointRangeSet implements Set<Point> {
	private int mWidth;
	private int mHeight;
	public PointRangeSet(int width, int height) {
		assert width >= 0;
		assert height >= 0;
		mWidth = width;
		mHeight = height;
	}
	@Override
	public int size() {
		return mWidth*mHeight;
	}
	@Override
	public boolean isEmpty() {
		return mWidth == 0 || mHeight == 0;
	}
	@Override
	public boolean contains(Object o) {
		if (!(o instanceof Point))
			return false;
		Point p = (Point)o;
		return 0 <= p.x && p.x < mWidth
				&& 0 <= p.y && p.y < mHeight;
	}
	@Override
	public Iterator<Point> iterator() {
		return new PointIterator();
	}
	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}
	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}
	@Override
	public boolean add(Point e) {
		throw new UnsupportedOperationException();
	}
	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}
	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean addAll(Collection<? extends Point> c) {
		throw new UnsupportedOperationException();
	}
	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}
	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}
	
	class PointIterator implements Iterator<Point> {
		int curX = 0;
		int curY = 0;
		
		@Override
		public boolean hasNext() {
			return curX + 1 + mWidth*curY < mWidth*mHeight;
		}

		@Override
		public Point next() {
			curX++;
			if (curX == mWidth) {
				curX = 0;
				curY++;
				if (curY == mHeight)
					throw new NoSuchElementException();
			}
			return Point.at(curX, curY);
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
