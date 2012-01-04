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
		for (Point point : board.getPoints()) {
			Room room;
			switch (rows[point.y].charAt(point.x)) {
			case '#':
			case 'W':
				room = new Wall(new Nothing(point));
				break;
			case '.':
			case 'x':
				room = new Target(new Nothing(point));
				break;
			case 'o':
			case 'C':
				room = new Floor(new Crate(point, Color.BLUE));
				break;
			case '↑':
				room = new Arrow(new Nothing(point), Dir.NORTH);
				break;
			case '→':
				room = new Arrow(new Nothing(point), Dir.EAST);
				break;
			case '↓':
				room = new Arrow(new Nothing(point), Dir.SOUTH);
				break;
			case '←':
				room = new Arrow(new Nothing(point), Dir.WEST);
				break;
			case '@':
			case 'H':
				room = new Floor(new Human(point, Dir.WEST));
				break;
			default:
				room = new Floor(new Nothing(point));
			}
			board.putRoom(room);
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
