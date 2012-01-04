package uk.ac.ox.cs.sokobanexam.ui;

import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import uk.ac.ox.cs.sokobanexam.domainmodel.Board;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Arrow;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Crate;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Human;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Nothing;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Room;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Sprite;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Target;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Wall;
import uk.ac.ox.cs.sokobanexam.ui.ASModel.State;

// TODO: We might have to listen to model.onStateChanged

public class Toolbar extends JToolBar implements SelectionChangeListener {
	private static final long serialVersionUID = -3632217082483213540L;
	
	private ASModel mModel;
	private MazeView mView;
	private JComponent mConfigurationPanel;
	private ButtonGroup mButtonGroup;
	
	@SuppressWarnings("serial")
	private static final Map<Class<? extends Sprite>, String> creatables
			= new HashMap<Class<? extends Sprite>, String>() {{
		put(Wall.class, "Wall");
		put(Crate.class, "Crate");
		put(Arrow.class, "Arrow");
		put(Target.class, "Target");
		put(Human.class, "Player");
	}};
	
	public Toolbar(ASModel model, MazeView view) {
		setModel(model);
		setMazeView(view);
		
		setMargin(new Insets(10,10,10,10));
		
		// Insert buttons into toolbar
		mButtonGroup = new ButtonGroup();
		JToggleButton editButton = new JToggleButton("Edit");
		editButton.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				onEditClicked();
			}
		});
		mButtonGroup.add(editButton);
		for (final Map.Entry<Class<? extends Sprite>, String> entry : creatables.entrySet()) {
			JToggleButton createButton = new JToggleButton("Create " + entry.getValue());
			createButton.addActionListener(new ActionListener() {
				@Override public void actionPerformed(ActionEvent e) {
					onCreateClicked(entry.getKey());
				}
			});
			mButtonGroup.add(createButton);
		}
		JToggleButton playButton = new JToggleButton("Play");
		playButton.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				onPlayClicked();
			}
		});
		mButtonGroup.add(playButton);
		for (Enumeration<AbstractButton> e = mButtonGroup.getElements(); e.hasMoreElements(); )
			add(e.nextElement());
		
		// Set edit button as default
		editButton.doClick();
		
		addSeparator();
		
		// Create the configuration panel and add delete button
		mConfigurationPanel = new JPanel();
		mConfigurationPanel.setLayout(new FlowLayout());
		JButton deleteButton = new JButton("Delete");
		mConfigurationPanel.add(deleteButton);
		deleteButton.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				mModel.deleteSelected();
			}
		});
	}
	
	private Board savedBoard = null;
	
	// TODO: Warning message when resetting game state, when changing state
	
	void onEditClicked() {
		if (!mModel.setState(State.EDITING)) {
			JOptionPane.showMessageDialog(this, "Can't do editing in the current state.");
		}
		else {
			if (savedBoard != null) {
				mModel.setBoard(savedBoard);
				savedBoard = null;
			}
		}
	}
	void onCreateClicked(Class<? extends Sprite> type) {
		if (!mModel.setState(State.INSERTING)) {
			JOptionPane.showMessageDialog(this, "Can't do inserting in the current state.");
		}
		else {
			if (savedBoard != null) {
				mModel.setBoard(savedBoard);
				savedBoard = null;
			}
			mModel.setTypeForInsertion(type);
		}
	}
	void onPlayClicked() {
		if (!mModel.setState(State.PLAYING)) {
			JOptionPane.showMessageDialog(this, "Can't start the game in the current state.");
		}
		else {
			savedBoard = mModel.getBoard().clone();
		}
	}
	
	
	public ASModel getModel() {
		return mModel;
	}
	public void setModel(ASModel model) {
		this.mModel = model;
		model.addSelectionChangeListener(this);
	}
	public void setMazeView(MazeView view) {
		mView = view;
	}
	public MazeView getMazeView() {
		return mView;
	}


	@Override
	public void onSelectionChanged(ASModel model) {
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
}
