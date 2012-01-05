package uk.ac.ox.cs.sokobanexam;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import uk.ac.ox.cs.sokobanexam.domainmodel.Board;
import uk.ac.ox.cs.sokobanexam.domainmodel.DefaultBoards;
import uk.ac.ox.cs.sokobanexam.domainmodel.DefaultRules;
import uk.ac.ox.cs.sokobanexam.domainmodel.Rules;
import uk.ac.ox.cs.sokobanexam.ui.MazeController;
import uk.ac.ox.cs.sokobanexam.ui.MazeModel;
import uk.ac.ox.cs.sokobanexam.ui.MazeView;
import uk.ac.ox.cs.sokobanexam.ui.Toolbar;

public class SokobanExamFrame extends JFrame {
	private static final long serialVersionUID = -6029887436154664351L;
	
	public SokobanExamFrame(Board board, Rules rules) {
		super("Sokoban Exam Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MazeModel model = new MazeModel(board, rules);
		MazeView view = new MazeView(model);
		MazeController controller = new MazeController(model, view);
		Toolbar toolbar = new Toolbar(controller);
		
		setLayout(new BorderLayout());
		add(BorderLayout.NORTH, toolbar);
		add(BorderLayout.CENTER, view);
		
		pack();
	}
	
	public static void main(String[] args) {
		Board board = DefaultBoards.oxfordRocksBoard();
		Rules rules = new DefaultRules();
		
		JFrame frame = new SokobanExamFrame(board, rules);
		frame.setVisible(true);
		
		System.out.println("Have fun!");
	}
}
