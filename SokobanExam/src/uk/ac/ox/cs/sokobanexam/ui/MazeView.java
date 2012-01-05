package uk.ac.ox.cs.sokobanexam.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JComponent;

import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Room;
import uk.ac.ox.cs.sokobanexam.util.Point;

public class MazeView extends JComponent implements MazeChangeListener, SelectionChangeListener {
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
		model.addSelectionChangeListener(this);
	}
	
	@Override
    public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		SpritePainter painter = new SpritePainter(g2, GRID_SIZE);
		for (Room room : mModel.getBoard().getRooms())
			room.accept(painter);
		
		if (mModel.isSelectionVisible() && mModel.getSelected() != null) {
			Rectangle r = point2Rect(mModel.getSelected());
			g2.setColor(new Color(0xdd66bb88, true));
			g2.setStroke(new BasicStroke(2f));
			g2.drawRect(r.x+1, r.y+1, r.width-3, r.height-3);
		}
		
		if (mModel.getHighlighted() != null) {
			Rectangle r = point2Rect(mModel.getHighlighted());
			g2.setColor(new Color(0x4488ffbb, true));
			g2.fillRect(r.x, r.y, r.width, r.height);
		}
		
		// TODO: Show hover message
		
    }
	public Point pos2Point(int x, int y) {
		Point point = Point.at(x/GRID_SIZE, y/GRID_SIZE);
		if (mModel.getBoard().getPoints().contains(point))
			return point;
		return null;
	}
	public Rectangle point2Rect(Point point) {
		if (point != null)
			return new Rectangle(point.x*GRID_SIZE, point.y*GRID_SIZE, GRID_SIZE, GRID_SIZE);
		return new Rectangle();
	}
	@Override
	public void onChange(MazeModel board) {
		repaint();
	}
	@Override
	public void onSelectionChanged(MazeModel model, Point from, Point to) {
		Rectangle bonds = point2Rect(from);
		bonds.add(point2Rect(to));
		repaint(bonds);
	}
	@Override
	public void onHighlightChanged(MazeModel mazeModel, Point from, Point to) {
		Rectangle bonds = point2Rect(from);
		bonds.add(point2Rect(to));
		repaint(bonds);
	}
}
