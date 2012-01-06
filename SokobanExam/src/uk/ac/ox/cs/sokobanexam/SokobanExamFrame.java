package uk.ac.ox.cs.sokobanexam;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import uk.ac.ox.cs.sokobanexam.model.DefaultRules;
import uk.ac.ox.cs.sokobanexam.model.Maze;
import uk.ac.ox.cs.sokobanexam.model.Rules;
import uk.ac.ox.cs.sokobanexam.ui.EditState;
import uk.ac.ox.cs.sokobanexam.ui.MazeController;
import uk.ac.ox.cs.sokobanexam.ui.MazeModel;
import uk.ac.ox.cs.sokobanexam.ui.MazeView;
import uk.ac.ox.cs.sokobanexam.ui.Toolbar;

/**
 * This is where the fun starts.
 */
public class SokobanExamFrame extends JFrame {
	private static final long serialVersionUID = -6029887436154664351L;
	
	public SokobanExamFrame(Maze maze, Rules rules) {
		super("Sokoban Exam Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MazeModel model = new MazeModel(maze, rules);
		MazeView view = new MazeView(model);
		MazeController controller = new MazeController(model, view);
		Toolbar toolbar = new Toolbar(controller);
		
		// Set the starting state
		controller.setCurrentState(new EditState(toolbar));
		
		setLayout(new BorderLayout());
		add(BorderLayout.NORTH, toolbar);
		add(BorderLayout.CENTER, view);
		pack();
	}
	
	public static void main(String[] args) {
		Maze maze = MazeLibrary.oxfordRocks();
		Rules rules = new DefaultRules();
		
		JFrame frame = new SokobanExamFrame(maze, rules);
		frame.setVisible(true);
		
		// Have fun!
	}
}
