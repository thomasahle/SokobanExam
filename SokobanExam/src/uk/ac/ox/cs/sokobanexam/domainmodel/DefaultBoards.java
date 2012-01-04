package uk.ac.ox.cs.sokobanexam.domainmodel;

import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Arrow;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Crate;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Floor;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Human;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Sprite;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Target;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Wall;
import uk.ac.ox.cs.sokobanexam.util.Color;
import uk.ac.ox.cs.sokobanexam.util.Dir;

public class DefaultBoards {
	public static Board board1() {
		Board board = new DefaultBoard(20, 10);
		board.insertSpriteAt(Point.at(0,0), new Wall())
			 .insertSpriteAt(Point.at(1,0), new Wall())
			 .insertSpriteAt(Point.at(2,0), new Wall())
			 .insertSpriteAt(Point.at(3,0), new Wall())
			 .insertSpriteAt(Point.at(0,1), new Wall())
			 .insertSpriteAt(Point.at(1,1), new Wall())
			 .insertSpriteAt(Point.at(2,1), new Wall())
			 .insertSpriteAt(Point.at(3,1), new Wall());
		
		board.insertSpriteAt(Point.at(0,0), new Wall())
		 	 .insertSpriteAt(Point.at(1,0), new Wall())
		 	 .insertSpriteAt(Point.at(2,0), new Wall())
		 	 .insertSpriteAt(Point.at(3,0), new Wall())
		 	 .insertSpriteAt(Point.at(0,1), new Wall())
		 	 .insertSpriteAt(Point.at(1,1), new Wall())
		 	 .insertSpriteAt(Point.at(2,1), new Wall())
		 	 .insertSpriteAt(Point.at(3,1), new Wall());
		
		return board;
	}
	
	private static Board fromStrings(String... rows) {
		Board board = new DefaultBoard(rows[0].length(), rows.length);
		for (int y = 0; y < rows.length; y++)
			for (int x = 0; x < rows[0].length(); x++) {
				Sprite sprite;
				switch (rows[y].charAt(x)) {
				case 'W':
					sprite = new Wall();
					break;
				case 'x':
					sprite = new Target();
					break;
				case 'C':
					sprite = new Crate(Color.BLUE);
					break;
				case '↑':
					sprite = new Arrow(Dir.NORTH);
					break;
				case '→':
					sprite = new Arrow(Dir.EAST);
					break;
				case '↓':
					sprite = new Arrow(Dir.SOUTH);
					break;
				case '←':
					sprite = new Arrow(Dir.WEST);
					break;
				case 'H':
					sprite = new Human();
					break;
				default:
					sprite = new Floor();
				}
				board.insertSpriteAt(Point.at(x,y), sprite);
			}
		return board;
	}
	
	public static Board board2() {
		return fromStrings(
				"WWW xC ",
				"WWWxCxH",
				"WWW W↓W",
				"  Wx←xW",
				"   CWCW",
				"   C  x",
				"WW    W"
			);
	}
}
