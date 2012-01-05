package uk.ac.ox.cs.sokobanexam.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import uk.ac.ox.cs.sokobanexam.domainmodel.Board;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Human;
import uk.ac.ox.cs.sokobanexam.util.Dir;

public class PlayState implements ControllerState, KeyListener {
	
	private MazeView mView;
	private MazeModel mModel;
	private Board mBoardBackup;
	
	@Override
	public void attach(MazeController controller, MazeModel model, MazeView view) {
		mView = view;
		mModel = model;
		mBoardBackup = mModel.getBoard().clone();
		view.addKeyListener(this);
		view.requestFocusInWindow();
		
		if (!model.isPlayable()) {
			JOptionPane.showMessageDialog(view, "The game is not yet playable. You probably need a player to move things around.");
			controller.setCurrentState(new CreateState(Human.class));
			// TODO: Update toolbar to represent new state
		}
	}
	
	@Override
	public void detach() {
		mView.removeKeyListener(this);
		mModel.setBoard(mBoardBackup);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP:
			mModel.move(Dir.NORTH);
			break;
		case KeyEvent.VK_RIGHT:
			mModel.move(Dir.EAST);
			break;
		case KeyEvent.VK_DOWN:
			mModel.move(Dir.SOUTH);
			break;
		case KeyEvent.VK_LEFT:
			mModel.move(Dir.WEST);
			break;
		default:
			break;
		}
	}
	@Override public void keyTyped(KeyEvent e) {}
	@Override public void keyReleased(KeyEvent e) {}

}
