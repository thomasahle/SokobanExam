package uk.ac.ox.cs.sokobanexam.domainmodel;

import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Arrow;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Crate;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Floor;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Human;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Nothing;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Room;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Target;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Wall;
import uk.ac.ox.cs.sokobanexam.util.Dir;
import uk.ac.ox.cs.sokobanexam.util.Point;

public class DefaultBoards {
	private static Board fromStrings(String... rows) {
		Board board = new DefaultBoard(rows[0].length(), rows.length);
		for (Point point : board.getPoints()) {
			Room room;
			char c = rows[point.y].charAt(point.x);
			switch (c) {
			case '#':
				room = new Wall(point);
				break;
			case '.':
				room = new Target(point);
				break;
			case '0':
				room = new Floor(new Crate(point));
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
				room = new Floor(new Human(point));
				break;
			case ' ':
				room = new Floor(point);
				break;
			default:
				room = new Wall(new Nothing(point), ""+c);
				break;
			}
			board.putRoom(room);
		}
		return board;
	}
	
	public static Board oxfordRocksBoard() {
		return fromStrings(
				"##  #Oxford# .0 #   ",
				"##0 #Rocks!#.0.@←   ",
				"##  ######## #↓##   ",
				"##0 #      #.←.#    ",
				"..  ←       0#0####↑",
				"..  ←   0   0  .    ",
				"##0 #  0  ##   #0   ",
				"##  #  ##0 #   #    ",
				"##0 #  ..0 ← # #    ",
				"##  #  ..  #        "
			);
	}
	public static Board board1() {
		return fromStrings(
				"#########",
				"# @ o . #",
				"#########"
			);
	}
	public static Board board2() {
		return fromStrings(
				"      ############ ",
				"     ##..    #   # ",
				"    ##..↑ 0    0 # ",
				"   ##..↑.# # # 0## ",
				"   #..↑.# # # 0  # ",
				"####...#  #    # # ",
				"#  ## #          # ",
				"# @0 0 ###  #   ## ",
				"# 0   0   # #   #  ",
				"###00   # # # # #  ",
				"  #   0   # # #####",
				"  # 0# #####      #",
				"  #0   #   #    # #",
				"  #  ###   ##     #",
				"  #  #      #    ##",
				"  ####      ###### ");
	}
}
