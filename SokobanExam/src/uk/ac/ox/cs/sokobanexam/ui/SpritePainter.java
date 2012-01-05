package uk.ac.ox.cs.sokobanexam.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;

import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Arrow;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Crate;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Floor;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Human;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Nothing;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.SpriteVisitor;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Target;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Wall;

public class SpritePainter implements SpriteVisitor {
	
	protected final Graphics2D g;
	private int size;
	
	private final static double ARROW_SIDE = 0.15;
	private final static double ARROW_PADDING = 0.25;
	private final static double HUMAN_PADDING = 0.15;
	private final static double CRATE_PADDING = 0.15;
	private final static double CRATE_ROUNDING = 0.25;
	private final static double TARGET_PADDING = 0.35;
	
	public SpritePainter(Graphics2D g, int gridSize) {
		this.g = g;
		this.size = gridSize;
	}
	
	@Override
	public void visit(Human sprite) {
		Graphics2D g2 = (Graphics2D)g.create();
		g2.translate(size*sprite.point().x, size*sprite.point().y);
		g2.setColor(Color.GREEN);
		g2.setStroke(new BasicStroke(2f));
		double pad = HUMAN_PADDING*size;
		double cen = size/2.;
		g2.rotate(-sprite.getDirection().angle, cen, cen);
		g2.draw(new Ellipse2D.Double(pad, pad, size - 2*pad, size - 2*pad));
		g2.draw(new Line2D.Double(cen, cen, size - pad, cen));
		g2.dispose();
	}
	
	@Override
	public void visit(Arrow sprite) {
		Graphics2D g2 = (Graphics2D)g.create();
		g2.translate(size*sprite.point().x, size*sprite.point().y);
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, size, size);
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		double pad = ARROW_PADDING*size;
		double cen = size/2.;
		double side = ARROW_SIDE*size;
		g2.rotate(-sprite.getDirection().angle, cen, cen);
		g2.draw(new Line2D.Double(pad, cen, size-pad, cen));
		g2.draw(new Line2D.Double(size-pad-side, cen-side, size-pad, cen));
		g2.draw(new Line2D.Double(size-pad-side, cen+side, size-pad, cen));
		g2.dispose();
		sprite.inner().accept(this);
	}
	
	@Override
	public void visit(Floor sprite) {
		g.setColor(Color.WHITE);
		g.fillRect(size*sprite.point().x, size*sprite.point().y, size, size);
		sprite.inner().accept(this);
	}
	
	@Override
	public void visit(Crate sprite) {
		g.setColor(new Color(0xff0000));
		double pad = CRATE_PADDING*size;
		double arc = CRATE_ROUNDING*size;
		g.fill(new RoundRectangle2D.Double(
				size*sprite.point().x + pad,
				size*sprite.point().y + pad,
				size - 2*pad, size - 2*pad,
				arc, arc));
	}
	
	@Override
	public void visit(Target sprite) {
		g.setColor(Color.WHITE);
		g.fillRect(size*sprite.point().x, size*sprite.point().y, size, size);
		g.setColor(new Color(0xff00ff));
		double pad = TARGET_PADDING*size;
		g.fill(new java.awt.geom.Ellipse2D.Double(
				size*sprite.point().x + pad,
				size*sprite.point().y + pad,
				size - 2*pad, size - 2*pad));
		sprite.inner().accept(this);
	}

	@Override
	public void visit(Wall sprite) {
		g.setColor(Color.BLACK);
		g.fillRect(size*sprite.point().x, size*sprite.point().y, size, size);
		sprite.inner().accept(this);
	}

	@Override
	public void visit(Nothing nothing) {
		// pass
	}
}
