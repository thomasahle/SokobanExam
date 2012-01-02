package uk.ac.ox.cs.sokobanexam;

import uk.ac.ox.cs.sokobanexam.model.Board;
import uk.ac.ox.cs.sokobanexam.model.DefaultBoards;
import uk.ac.ox.cs.sokobanexam.model.DefaultRules;
import uk.ac.ox.cs.sokobanexam.model.Model;

public class SokobanExam {
	public static void main(String[] args) {
		Board board = DefaultBoards.board2();
		Model model = new Model(board, new DefaultRules());
		
		SimpleBoardPrinter.printBoard(board);
	}
}
