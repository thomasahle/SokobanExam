package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

import uk.ac.ox.cs.sokobanexam.util.Point;

public abstract class AbstractSprite implements Sprite {
	protected Point mPoint;
	@Override
	public Point point() {
		return mPoint;
	}
	@Override
	public int hashCode() {
		return getClass().hashCode() ^ mPoint.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
        if (obj instanceof AbstractSprite) {
        	AbstractSprite sprite = (AbstractSprite)obj;
            return mPoint.equals(sprite.point());
        }
        return super.equals(obj);
    }
}
