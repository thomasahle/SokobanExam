package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

import uk.ac.ox.cs.sokobanexam.util.Point;

/* Anden måde:
  data InnerCell = Player
                 | Box
                 | Empty
                 deriving (Eq)
  
  data CellType = Wall
                | Path   { inner :: InnerCell }
                | Target { inner :: InnerCell }
                deriving (Eq)

 */

/*
 * moveable interface
 * flyt i retninger
 * se om blokkeret
 */



/*
 * Room
 * getType(): Path
 * getContent(): Player
 * isType(class)
 */

public interface Sprite {
	public Point point();
	public Sprite move(Point point);
	public void accept(SpriteVisitor visitor);
}
