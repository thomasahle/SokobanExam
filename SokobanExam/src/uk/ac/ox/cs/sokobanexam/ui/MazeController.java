package uk.ac.ox.cs.sokobanexam.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import uk.ac.ox.cs.sokobanexam.ui.ASModel.State;
import uk.ac.ox.cs.sokobanexam.util.Dir;
import uk.ac.ox.cs.sokobanexam.util.Point;

public class MazeController implements KeyListener, MouseListener, StateChangeListener {
	private MazeView mView;
	private ASModel mModel;
	public MazeController(ASModel model, MazeView view) {
		setModel(model);
		setMazeView(view);
	}
	public MazeView getMazeView() {
		return mView;
	}
	public ASModel getModel() {
		return mModel;
	}
	public void setMazeView(MazeView view) {
		this.mView = view;
		view.addKeyListener(this);
		view.addMouseListener(this);
	}
	public void setModel(ASModel model) {
		this.mModel = model;
		model.setStateChangeListener(this);
	}
	
	@Override
	public void onStateChanged(ASModel model) {
		if (model.getState() == State.PLAYING)
			mView.requestFocusInWindow();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {
		if (mModel.getState() != State.PLAYING)
			return;
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
			return;
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Point point = mView.pos2Point(e.getX(), e.getY());
		if (point != null)
			mModel.setSelected(point);
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
