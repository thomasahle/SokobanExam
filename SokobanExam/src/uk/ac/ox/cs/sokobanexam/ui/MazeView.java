package uk.ac.ox.cs.sokobanexam.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;

import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Room;
import uk.ac.ox.cs.sokobanexam.util.Point;

public class MazeView extends JComponent implements MazeChangeListener {
	private static final long serialVersionUID = 7808955267765088933L;
	
	private static final int GRID_SIZE = 50;
	
	private MazeModel mModel;
	
	public MazeView(MazeModel model) {
		setModel(model);
		setFocusable(true);
	}
	public MazeModel getModel() {
		return mModel;
	}
	public void setModel(MazeModel model) {
		this.mModel = model;
		setPreferredSize(new Dimension(
				model.getBoard().getWidth()*GRID_SIZE,
				model.getBoard().getHeight()*GRID_SIZE));
		model.addMazeChangeListener(this);
	}
	
	@Override
    public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		SpritePainter painter = new SpritePainter(g2, GRID_SIZE);
		for (Room room : mModel.getBoard().getRooms())
			room.accept(painter);
		// Would be nice to have points in the sprites
    }
	public Point pos2Point(int x, int y) {
		Point point = Point.at(x/GRID_SIZE, y/GRID_SIZE);
		if (mModel.getBoard().getPoints().contains(point))
			return point;
		return null;
	}
	@Override
	public void onChange(MazeModel board) {
		// TODO: Show hover message
		repaint();
	}
}
