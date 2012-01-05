package uk.ac.ox.cs.sokobanexam.ui;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Sprite;

// TODO: We might have to listen to model.onStateChanged

public class Toolbar extends JToolBar {
	private static final long serialVersionUID = -3632217082483213540L;
	
	private ButtonGroup mButtonGroup;
	
	public Toolbar(final MazeController controller) {
		
		setMargin(new Insets(10,10,10,10));
		
		// Insert buttons into toolbar
		mButtonGroup = new ButtonGroup();
		JToggleButton editButton = new JToggleButton("Edit");
		editButton.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				controller.setCurrentState(new EditState(Toolbar.this));
			}
		});
		mButtonGroup.add(editButton);
		for (final Map.Entry<Class<? extends Sprite>, String> entry : MazeModel.PHYSICAL_SPRITES.entrySet()) {
			JToggleButton createButton = new JToggleButton("Create " + entry.getValue());
			createButton.addActionListener(new ActionListener() {
				@Override public void actionPerformed(ActionEvent e) {
					controller.setCurrentState(new CreateState(entry.getKey()));
				}
			});
			mButtonGroup.add(createButton);
		}
		JToggleButton playButton = new JToggleButton("Play");
		playButton.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				controller.setCurrentState(new PlayState());
			}
		});
		mButtonGroup.add(playButton);
		for (Enumeration<AbstractButton> e = mButtonGroup.getElements(); e.hasMoreElements(); )
			add(e.nextElement());
		
		// Set edit button as default
		editButton.doClick();
		
		addSeparator();
	}
}
