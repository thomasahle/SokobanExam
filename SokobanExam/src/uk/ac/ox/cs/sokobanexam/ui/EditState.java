package uk.ac.ox.cs.sokobanexam.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import uk.ac.ox.cs.sokobanexam.domainmodel.Board;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Floor;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Nothing;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Room;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Sprite;
import uk.ac.ox.cs.sokobanexam.util.Point;

public class EditState implements ControllerState, MouseListener,
		MouseMotionListener, KeyListener, ActionListener,
		SelectionChangeListener {
	
	private MazeView mView;
	private MazeModel mModel;
	private Board mBoardBackup;
	
	private Toolbar mToolbar;
	private JComponent mConfigurationPanel;
	
	public EditState(Toolbar toolbar) {
		mToolbar = toolbar;
		
		// Create the configuration panel with the delete button
		// TODO: We need to use JToolbarButtons
		mConfigurationPanel = new JPanel();
		mConfigurationPanel.setLayout(new FlowLayout());
		JButton deleteButton = new JButton("Delete");
		mConfigurationPanel.add(deleteButton);
		deleteButton.addActionListener(this);
	}
	
	@Override
	public void attach(MazeController controller, MazeModel model, MazeView view) {
		mView = view;
		mModel = model;
		mBoardBackup = mModel.getBoard().clone();
		mView.addMouseListener(this);
		mView.addMouseMotionListener(this);
		mView.addKeyListener(this);
		mView.requestFocusInWindow();
		mModel.addSelectionChangeListener(this);
		//mToolbar.add(mConfigurationPanel);
	}
	
	@Override
	public void detach() {
		mView.removeMouseListener(this);
		mView.removeMouseMotionListener(this);
		mView.removeKeyListener(this);
		mModel.removeSelectionChangeListener(this);
		mModel.setBoard(mBoardBackup);
		// TODO: Only if added
		mToolbar.remove(mConfigurationPanel);
	}
	
	@Override
	public void onSelectionChanged(MazeModel model) {
		// Clear any old, custom configuration
		while (mConfigurationPanel.getComponentCount() > 1)
			mConfigurationPanel.remove(1);
		// If nothing is selected, we simply hide the component
		if (model.getSelected() == null) {
			mConfigurationPanel.setVisible(false);
		}
		// Otherwise we generate a new component with the selected sprite
		else {
			Room room = model.getBoard().getRoom(model.getSelected());
			Sprite sprite = room.inner() instanceof Nothing ? room : room.inner();
			SpriteConfigurationCreator confCreator = new SpriteConfigurationCreator();
			sprite.accept(confCreator);
			mConfigurationPanel.add(confCreator.getResult());
			mConfigurationPanel.setVisible(true);
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		mModel.setHighlighted(mView.pos2Point(e.getX(), e.getY()));
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
	}
	
	@Override public void mouseClicked(MouseEvent e) {
		Point point = mView.pos2Point(e.getX(), e.getY());
		Room room = mModel.getBoard().getRoom(point);
		if (MazeModel.isEditable(room.inner().getClass())
				|| MazeModel.isEditable(room.getClass()))
			mModel.setSelected(point);
		else
			mModel.setSelected(null);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DELETE)
			deleteSelected();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// This is only connected to the delete button
		deleteSelected();
	}
	
	private void deleteSelected() {
		Point point = mModel.getSelected();
		if (point == null)
			// Nothing selected
			return;
		Board board = mModel.getBoard();
		Room room = board.getRoom(point);
		if (MazeModel.isEditable(room.inner().getClass()))
			board.putRoom(room.withInner(new Nothing(room.point())));
		else board.putRoom(new Floor(room.inner()));
	}
	
	@Override public void mousePressed(MouseEvent e) {}
	@Override public void mouseReleased(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
	@Override public void keyTyped(KeyEvent e) {}
	@Override public void keyReleased(KeyEvent e) {}

}
