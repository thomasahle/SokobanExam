package uk.ac.ox.cs.sokobanexam.domainmodel;

import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Arrow;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Crate;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Floor;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Human;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Nothing;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Room;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Sprite;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Target;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Wall;
import uk.ac.ox.cs.sokobanexam.util.Color;
import uk.ac.ox.cs.sokobanexam.util.Dir;
import uk.ac.ox.cs.sokobanexam.util.Point;

// Tag flere fra https://github.com/scrooloose/sokoban/tree/master/data

public class DefaultBoards {
	private static Board fromStrings(String... rows) {
		Board board = new DefaultBoard(rows[0].length(), rows.length);
		for (int y = 0; y < rows.length; y++)
			for (int x = 0; x < rows[0].length(); x++) {
				Room room;
				switch (rows[y].charAt(x)) {
				case '#':
				case 'W':
					room = new Wall(Point.at(x,y), new Nothing(Point.at(x,y)));
					break;
				case '.':
				case 'x':
					room = new Target(Point.at(x,y), new Nothing(Point.at(x,y)));
					break;
				case 'o':
				case 'C':
					room = new Floor(Point.at(x,y), new Crate(Point.at(x,y), Color.BLUE));
					break;
				case '↑':
					room = new Arrow(Point.at(x,y), new Nothing(Point.at(x,y)), Dir.NORTH);
					break;
				case '→':
					room = new Arrow(Point.at(x,y), new Nothing(Point.at(x,y)), Dir.EAST);
					break;
				case '↓':
					room = new Arrow(Point.at(x,y), new Nothing(Point.at(x,y)), Dir.SOUTH);
					break;
				case '←':
					room = new Arrow(Point.at(x,y), new Nothing(Point.at(x,y)), Dir.WEST);
					break;
				case '@':
				case 'H':
					room = new Floor(Point.at(x,y), new Human(Point.at(x,y), Dir.WEST));
					break;
				default:
					room = new Floor(Point.at(x,y), new Nothing(Point.at(x,y)));
				}
				board.setRoom(Point.at(x,y), room);
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
	public static Board board3() {
		return fromStrings(
				"#########",
				"# @ o . #",
				"#########"
			);
	}
}
