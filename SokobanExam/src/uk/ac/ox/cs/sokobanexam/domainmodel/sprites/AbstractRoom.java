package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

import uk.ac.ox.cs.sokobanexam.util.Point;

public abstract class AbstractRoom implements Room {
	private Sprite mInner;
	public AbstractRoom(Sprite inner) {
		mInner = inner;
	}
	@Override
	public Sprite inner() {
		return mInner;
	}
	@Override
	public Point point() {
		return mInner.point();
	}
}
