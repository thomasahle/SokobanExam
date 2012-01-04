package uk.ac.ox.cs.sokobanexam;

import uk.ac.ox.cs.sokobanexam.domainmodel.Board;
import uk.ac.ox.cs.sokobanexam.domainmodel.DefaultBoards;
import uk.ac.ox.cs.sokobanexam.domainmodel.DefaultRules;
import uk.ac.ox.cs.sokobanexam.domainmodel.Model;

public class SokobanExam {
	public static void main(String[] args) {
		Board board = DefaultBoards.board2();
		Model model = new Model(board, new DefaultRules());
		
		SimpleBoardPrinter.printBoard(board);
	}
}
