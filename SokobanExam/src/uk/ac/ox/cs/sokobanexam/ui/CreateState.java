package uk.ac.ox.cs.sokobanexam.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;

import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Room;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Sprite;
import uk.ac.ox.cs.sokobanexam.util.Point;

public class CreateState implements ControllerState, MouseListener,
		MouseMotionListener {
	
	private MazeView mView;
	private MazeModel mModel;
	
	private Class<? extends Sprite> mType;
	
	public CreateState(Class<? extends Sprite> type) {
		mType = type;
	}

	@Override
	public void attach(MazeController controller, MazeModel model, MazeView view) {
		mView = view;
		mModel = model;
		mView.addMouseListener(this);
		mView.addMouseMotionListener(this);
	}

	@Override
	public void detach() {
		mView.removeMouseListener(this);
		mView.removeMouseMotionListener(this);
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
		if (point == null)
			return;
		
		Sprite sprite = createSprite(point);
		// Test if we were allowed to create the sprite. Hopefully we always are. 
		if (sprite == null)
			return;
		Room room = mModel.getBoard().getRoom(point);
		
		// If inserting a non-room type
		if (!(sprite instanceof Room)) {
			if (MazeModel.isEditableType(room.inner())) {
				JOptionPane.showMessageDialog(mView, "You can't insert on top of other things.");
				return;
			}
			else {
				mModel.getBoard().putRoom(room.withInner(sprite));
			}
		}
		// If inserting a room type
		else {
			if (MazeModel.isEditableType(room)) {
				JOptionPane.showMessageDialog(mView, "You can't insert on top of other things.");
				return;
			}
			else {
				mModel.getBoard().putRoom(((Room)sprite).withInner(room.inner()));
			}
		}
		
		// Check validation
		if (!mModel.getRules().validateBoard(mModel.getBoard())) {
			// Restore board
			mModel.getBoard().putRoom(room);
			JOptionPane.showMessageDialog(mView, "Inserting here is against the rules.");
		}
	}
	
	/*
	 * Instantiates a sprite of type 'mType' at the given point.
	 * This is only used in one place, but because of the huge amount of possible
	 * exceptions, it is isolated here.
	 * It fails if the sprite doesn't have a mType(point) constructor, which is
	 * not as nice as I'd like. An alternative would be to use a factory pattern,
	 * but since the problem only arises just here, I've decided that creating a
	 * such, would be to over engineer the problem. After all we already have a
	 * Class object ready to make a newInstance.
	 */
	private Sprite createSprite(Point point) {
		String error = "Sorry, the app seams broken. Anyway the insertaion failed.";
		try {
			System.out.println(mType);
			Sprite sprite = mType.getDeclaredConstructor(Point.class).newInstance(point);
			return sprite;
		} catch (InstantiationException e1) {
			JOptionPane.showMessageDialog(mView, error);
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			JOptionPane.showMessageDialog(mView, error);
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			JOptionPane.showMessageDialog(mView, error);
			e1.printStackTrace();
		} catch (SecurityException e1) {
			JOptionPane.showMessageDialog(mView, error);
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			JOptionPane.showMessageDialog(mView, error);
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			JOptionPane.showMessageDialog(mView, error);
			e1.printStackTrace();
		}
		return null;
	}
	
	@Override public void mouseReleased(MouseEvent e) {}
	@Override public void mouseClicked(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
}
