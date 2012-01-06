package uk.ac.ox.cs.sokobanexam.ui;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import uk.ac.ox.cs.sokobanexam.model.sprites.Sprite;

/**
 * The Toolbar is used to change between states, and to access state created controls.
 */
public class Toolbar extends JToolBar implements StateChangeListener {
	private static final long serialVersionUID = -3632217082483213540L;
	
	private ButtonGroup mButtonGroup;
	
	private JToggleButton mEditButton;
	private JToggleButton mPlayButton;
	private Map<Class<? extends Sprite>, JToggleButton> mCreateButtons
			= new HashMap<Class<? extends Sprite>, JToggleButton>();
	
	public Toolbar(final MazeController controller) {
		setMargin(new Insets(6,2,6,2));
		
		// The edit button
		mButtonGroup = new ButtonGroup();
		mEditButton = new JToggleButton("Edit");
		mEditButton.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				controller.setCurrentState(new EditState(Toolbar.this));
			}
		});
		
		// The create buttons
		mButtonGroup.add(mEditButton);
		for (final Map.Entry<Class<? extends Sprite>, String> entry : MazeController.PHYSICAL_SPRITES.entrySet()) {
			JToggleButton createButton = new JToggleButton("Create " + entry.getValue());
			mCreateButtons.put(entry.getKey(), createButton);
			createButton.addActionListener(new ActionListener() {
				@Override public void actionPerformed(ActionEvent e) {
					controller.setCurrentState(new CreateState(entry.getKey()));
				}
			});
			mButtonGroup.add(createButton);
		}
		
		// The play button
		mPlayButton = new JToggleButton("Play");
		mPlayButton.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				controller.setCurrentState(new PlayState());
			}
		});
		
		// Group the buttons so only one can be toggled the time
		mButtonGroup.add(mPlayButton);
		for (Enumeration<AbstractButton> e = mButtonGroup.getElements(); e.hasMoreElements(); )
			add(e.nextElement());
		
		
		// The states may add more buttons later on
		addSeparator();
		
		// Stay in sync
		controller.addStateChangeListener(this);
	}
	
	@Override
	public void onStateChanged(MazeController source) {
		if (source.getCurrentState() instanceof EditState)
			if (!mEditButton.isSelected())
				mEditButton.doClick();
		if (source.getCurrentState() instanceof CreateState) {
			JToggleButton createButton = mCreateButtons.get(((CreateState)source.getCurrentState()).getCreatedType());
			if (!createButton.isSelected())
				createButton.doClick();
		}
		if (source.getCurrentState() instanceof PlayState)
			if (!mPlayButton.isSelected())
				mPlayButton.doClick();
	}
}
