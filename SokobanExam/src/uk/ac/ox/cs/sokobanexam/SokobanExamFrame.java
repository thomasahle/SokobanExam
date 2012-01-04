package uk.ac.ox.cs.sokobanexam;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import uk.ac.ox.cs.sokobanexam.domainmodel.Board;
import uk.ac.ox.cs.sokobanexam.domainmodel.DefaultBoards;
import uk.ac.ox.cs.sokobanexam.domainmodel.DefaultRules;
import uk.ac.ox.cs.sokobanexam.domainmodel.Rules;
import uk.ac.ox.cs.sokobanexam.ui.ASModel;
import uk.ac.ox.cs.sokobanexam.ui.ASModel.State;
import uk.ac.ox.cs.sokobanexam.ui.MazeController;
import uk.ac.ox.cs.sokobanexam.ui.MazeView;
import uk.ac.ox.cs.sokobanexam.ui.Toolbar;

public class SokobanExamFrame extends JFrame {
	private static final long serialVersionUID = -6029887436154664351L;
	
	public SokobanExamFrame(Board board, Rules rules) {
		super("Sokoban Exam Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ASModel asmodel = new ASModel(board, rules, State.EDITING);
		MazeView view = new MazeView(asmodel);
		Toolbar toolbar = new Toolbar(asmodel, view);
		new MazeController(asmodel, view);
		
		setLayout(new BorderLayout());
		add(BorderLayout.NORTH, toolbar);
		add(BorderLayout.CENTER, view);
		
		// Do this when clicking "play"
		view.requestFocusInWindow();
		pack();
	}
	
	public static void main(String[] args) {
		Board board = DefaultBoards.board2();
		//Model model = new Model(board, new DefaultRules());
		
		SimpleBoardPrinter.printBoard(board);
		
		JFrame frame = new SokobanExamFrame(board, new DefaultRules());
		frame.setVisible(true);
	}
}
