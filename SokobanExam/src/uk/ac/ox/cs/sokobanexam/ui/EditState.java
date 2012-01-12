package uk.ac.ox.cs.sokobanexam.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uk.ac.ox.cs.sokobanexam.model.Maze;
import uk.ac.ox.cs.sokobanexam.model.ValidationResult;
import uk.ac.ox.cs.sokobanexam.model.sprites.Floor;
import uk.ac.ox.cs.sokobanexam.model.sprites.Nothing;
import uk.ac.ox.cs.sokobanexam.model.sprites.Room;
import uk.ac.ox.cs.sokobanexam.model.sprites.Sprite;
import uk.ac.ox.cs.sokobanexam.util.Point;

/**
 * The state of the MazeController in which new objects can be edited, deleted or moved.
 */
public class EditState implements ControllerState, MouseListener,
		MouseMotionListener, KeyListener, ActionListener,
		SelectionChangeListener, SpriteChangeListener {
	
	private MazeView mView;
	private MazeModel mModel;
	
	private Toolbar mToolbar;
	private JComponent mConfigurationPanel;
	private JButton mDeleteButton;
	
	public EditState(Toolbar toolbar) {
		mToolbar = toolbar;		
		mDeleteButton = new JButton("Delete");
		mDeleteButton.addActionListener(this);
	}
	
	@Override
	public void attach(MazeController controller, MazeModel model, MazeView view) {
		mView = view;
		mModel = model;
		mView.addMouseListener(this);
		mView.addMouseMotionListener(this);
		mView.addKeyListener(this);
		mView.requestFocusInWindow();
		mModel.addSelectionChangeListener(this);
		mModel.setSelectionVisible(true);
		mToolbar.add(mDeleteButton);
		onSelectionChanged(mModel, null, null);
	}
	
	@Override
	public void detach() {
		mView.removeMouseListener(this);
		mView.removeMouseMotionListener(this);
		mView.removeKeyListener(this);
		mModel.removeSelectionChangeListener(this);
		mModel.setSelectionVisible(false);
		if (mConfigurationPanel != null)
			mToolbar.remove(mConfigurationPanel);
		mToolbar.remove(mDeleteButton);
		forceDoLayout();
	}
	
	@Override
	public void onSelectionChanged(MazeModel model, Point from, Point to) {
		// Clear any old, custom configuration
		if (mConfigurationPanel != null)
			mToolbar.remove(mConfigurationPanel);
		// If nothing is selected, we must disable the delete button
		if (model.getSelected() == null) {
			mDeleteButton.setEnabled(false);
			mConfigurationPanel = null;
		}
		// Otherwise we generate a new component with the selected sprite
		else {
			mDeleteButton.setEnabled(true);
			Room room = model.getRoom(model.getSelected());
			Sprite sprite = MazeController.isEditableType(room.inner()) ? room.inner() : room;
			SpriteConfigurationCreator confCreator = new SpriteConfigurationCreator(this);
			sprite.accept(confCreator);
			mConfigurationPanel = confCreator.getResult();
			mToolbar.add(mConfigurationPanel);
		}
		
		forceDoLayout();
	}

	private void forceDoLayout() {
		// Hack to get relayouting to work.
		mToolbar.doLayout();
		mToolbar.revalidate();
		mToolbar.repaint();
		// This part is necessary for small maps
		if (mToolbar.getParent() != null) {
			Container parent = mToolbar.getParent();
			while (!(parent instanceof JFrame))
				parent = parent.getParent();
			((JFrame)parent).pack();
		}
	}
	
	@Override
	public void onSpriteChanged(Sprite oldSprite, Sprite newSprite) {
		if (newSprite instanceof Room)
			mModel.putRoom((Room)newSprite);
		else mModel.putRoom(mModel.getRoom(oldSprite.point()).withInner(newSprite));
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		mModel.setHighlighted(mView.pos2Point(e.getX(), e.getY()));
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		mModel.setHighlighted(null);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		Point point = mView.pos2Point(e.getX(), e.getY());
		if (point == null) {
			mModel.setSelected(null);
			return;
		}
		Room room = mModel.getRoom(point);
		if (MazeController.isEditableType(room.inner())
				|| MazeController.isEditableType(room)) {
			mModel.setSelected(point);
			// We request focus, so the delete keyboard shortcut works.
			mView.requestFocusInWindow();
		}
		else
			mModel.setSelected(null);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		Point point = mView.pos2Point(e.getX(), e.getY());
		if (mModel.getSelected() == null || point == null)
			return;
		
		Room from = mModel.getRoom(mModel.getSelected());
		Room to = mModel.getRoom(point);
		if (from.point().equals(to.point()))
			return;
		
		// Check if the target already has an editable type of the same level
		// as the sprite we are moving.
		if (MazeController.isEditableType(from.inner())
				&& MazeController.isEditableType(to.inner())
				|| MazeController.isEditableType(from)
				&& MazeController.isEditableType(to)) {
			JOptionPane.showMessageDialog(mView, "You can't drag on top of other things.");
			return;
		}
		
		Room newRoomFrom;
		Room newRoomTo;
		// See if we are moving an inner
		if (MazeController.isEditableType(from.inner())) {
			assert !MazeController.isEditableType(to.inner());
			newRoomTo = to.withInner(from.inner().move(to.point()));
			newRoomFrom = from.withInner(new Nothing(from.point()));
		}
		// If we are moving a room
		else {
			assert MazeController.isEditableType(from);
			assert !MazeController.isEditableType(to);
			newRoomTo = from.withInner(to.inner());
			newRoomFrom = new Floor(from.point());
		}
		
		// Check validation (without repainting)
		mModel.getMaze().putRoom(newRoomTo);
		mModel.getMaze().putRoom(newRoomFrom);
		ValidationResult result = mModel.validateMaze();
		if (!result.isLegal()) {
			// Restore maze
			mModel.getMaze().putRoom(to);
			mModel.getMaze().putRoom(from);
			JOptionPane.showMessageDialog(mView, result.getMessage() + ".");
			return;
		}
		
		// We dragged something selected, so we might as well keep it selected.
		mModel.setSelected(to.point());
		// The selection also takes care of repainting the two squares involved in the
		// transaction, so we don't need the following two lines:
		// mModel.putRoom(newRoomTo);
		// mModel.putRoom(newRoomFrom);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DELETE)
			deleteSelected();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		assert e.getSource() == mDeleteButton;
		deleteSelected();
	}
	
	private void deleteSelected() {
		Point point = mModel.getSelected();
		if (point == null)
			// Nothing selected
			return;
		Room room = mModel.getRoom(point);
		if (MazeController.isEditableType(room.inner()))
			mModel.putRoom(room.withInner(new Nothing(room.point())));
		else mModel.putRoom(new Floor(room.inner()));
		mModel.setSelected(null);
	}
	
	@Override public void mouseClicked(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void keyTyped(KeyEvent e) {}
	@Override public void keyReleased(KeyEvent e) {}
	@Override public void onHighlightChanged(MazeModel mazeModel, Point from, Point to) {}
	@Override public void onSelectionVisibilityChanged(MazeModel mazeModel) {}
}
