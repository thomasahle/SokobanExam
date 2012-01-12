package uk.ac.ox.cs.sokobanexam.util;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An unmodifiable set representing points in a rectangular area.
 * The set does 'contains' queries in constant time.
 */
public class PointRangeSet extends AbstractSet<Point> {
	private int mWidth;
	private int mHeight;
	public PointRangeSet(int width, int height) {
		if (width < 0 || height < 0)
			throw new IllegalArgumentException("Illegal dimension "+width+"x"+height);
		mWidth = width;
		mHeight = height;
	}
	@Override
	public int size() {
		return mWidth*mHeight;
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
	
	class PointIterator implements Iterator<Point> {
		int curX = 0;
		int curY = 0;
		
		@Override
		public boolean hasNext() {
			return curY != mHeight;
		}

		@Override
		public Point next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Point res = Point.at(curX, curY);
			curX++;
			if (curX == mWidth) {
				curX = 0;
				curY++;
			}
			return res;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("The set is unmidifiable");
		}
	}
}
