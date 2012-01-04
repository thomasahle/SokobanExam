package uk.ac.ox.cs.sokobanexam.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import uk.ac.ox.cs.sokobanexam.util.Point;

public class MazeView extends JComponent {
	private static final long serialVersionUID = 7808955267765088933L;
	
	private static final int GRID_WIDTH = 50;
	private static final int GRID_HEIGHT = 50;
	
	private ASModel mModel;
	
	public MazeView(ASModel model) {
		mModel = model;
		setFocusable(true);
	}
	public ASModel getModel() {
		return mModel;
	}
	public void setModel(ASModel model) {
		this.mModel = model;
		setPreferredSize(new Dimension(GRID_WIDTH,GRID_HEIGHT));
	}
	
	@Override
    public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		SpritePainter painter = new SpritePainter(g2);
		// Would be nice to have points in the sprites
    }
	public Point pos2Point(int x, int y) {
		Point point = Point.at(x/GRID_WIDTH, y/GRID_HEIGHT);
		if (mModel.getBoard().getContainedPoints().contains(point))
			return point;
		return null;
	}
	
}
