package uk.ac.ox.cs.sokobanexam;

import uk.ac.ox.cs.sokobanexam.model.Board;
import uk.ac.ox.cs.sokobanexam.model.Point;
import uk.ac.ox.cs.sokobanexam.model.sprites.Arrow;
import uk.ac.ox.cs.sokobanexam.model.sprites.Crate;
import uk.ac.ox.cs.sokobanexam.model.sprites.Floor;
import uk.ac.ox.cs.sokobanexam.model.sprites.Human;
import uk.ac.ox.cs.sokobanexam.model.sprites.SpriteVisitor;
import uk.ac.ox.cs.sokobanexam.model.sprites.Target;
import uk.ac.ox.cs.sokobanexam.model.sprites.Wall;
import uk.ax.ox.cs.sokobanexam.util.Dir;

public class SimpleBoardPrinter {
	public static void printBoard(Board board) {
		SpritePrinter printer = new SpritePrinter();
		for (int y = 0; y < board.getHeight(); y++) {
			for (int x = 0; x < board.getWidth(); x++) {
				board.getTopSpriteAt(Point.at(x,y)).accept(printer);
			}
			System.out.println();
		}
	}
	static class SpritePrinter implements SpriteVisitor {
		@Override
		public void visit(Human sprite) {
			System.out.print("☺");
		}
		@Override
		public void visit(Arrow sprite) {
			switch (sprite.getDirection()) {
			case NORTH:
				System.out.print("↑");
				break;
			case EAST:
				System.out.print("→");
				break;
			case SOUTH:
				System.out.print("↓");
				break;
			case WEST:
				System.out.print("←");
				break;
			}
		}
		@Override
		public void visit(Crate sprite) {
			System.out.print("O");
		}
		@Override
		public void visit(Floor sprite) {
			System.out.print(".");	
		}
		@Override
		public void visit(Target sprite) {
			System.out.print("x");
		}
		@Override
		public void visit(Wall sprite) {
			System.out.print("#");
		}
	}
}
