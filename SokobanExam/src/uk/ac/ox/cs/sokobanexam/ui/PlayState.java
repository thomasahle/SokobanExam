package uk.ac.ox.cs.sokobanexam.ui;

import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import uk.ac.ox.cs.sokobanexam.model.Maze;
import uk.ac.ox.cs.sokobanexam.model.ValidationResult;
import uk.ac.ox.cs.sokobanexam.model.sprites.Human;
import uk.ac.ox.cs.sokobanexam.util.Dir;

/**
 * The state of the MazeController in which you can actually play the game.
 * If this state is attached to a non playable model, it automatically switches
 * the current state to the CreateState so the problem can be fixed.
 */
public class PlayState implements ControllerState, KeyListener {
	
	private MazeView mView;
	private MazeModel mModel;
	private Maze mMazeBackup;
	
	@Override
	public void attach(MazeController controller, MazeModel model, MazeView view) {
		mView = view;
		mModel = model;
		mMazeBackup = mModel.getMaze().clone();
		mModel.setHighlighted(null);
		mView.addKeyListener(this);
		mView.requestFocusInWindow();
		
		ValidationResult result = mModel.validateMazePlayable();
		if (!result.isLegal()) {
			JOptionPane.showMessageDialog(view, "The game is not yet playable. " + result.getMessage() + ".");
			// We predict that if a game is not playable (but is valid) it is
			// probably because it doesn't have a player in the maze.
			controller.setCurrentState(new CreateState(Human.class));
		}
		
		if (mModel.isWon()) {
			mModel.setHoverMessage("Game won!");
		}
	}
	
	@Override
	public void detach() {
		mView.removeKeyListener(this);
		mModel.setHoverMessage(null);
		mModel.setMaze(mMazeBackup);
		// Recreate highlight
		// It can be discussed whether it is fair to make PlayState responsible
		// for shutting off highlights, since it doesn't use them anywhere
		// itself. However my guess is that later States are likely to use
		// highlights, and so PlayState is the exception and it will have to do
		// the extra work.
		java.awt.Point mousePos = MouseInfo.getPointerInfo().getLocation();
		java.awt.Point viewPos = mView.getLocationOnScreen();
		mModel.setHighlighted(mView.pos2Point(mousePos.x-viewPos.x, mousePos.y-viewPos.y));
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
		if (mModel.isWon()) {
			mModel.setHoverMessage("Game won!");
		}
	}
	
	@Override public void keyTyped(KeyEvent e) {}
	@Override public void keyReleased(KeyEvent e) {}
}
