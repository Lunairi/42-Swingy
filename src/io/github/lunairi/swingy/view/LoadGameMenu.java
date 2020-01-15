package io.github.lunairi.swingy.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import io.github.lunairi.swingy.controller.GameEngine;
import io.github.lunairi.swingy.controller.Player;
import io.github.lunairi.swingy.model.Hero;
import io.github.lunairi.swingy.view.GUI.Panels;

@SuppressWarnings("serial")
public class LoadGameMenu extends JPanel {
	
	public LoadGameMenu() {
		this.setSize(800, 600);
		this.setLayout(null);
		this.setVisible(true);
		this.loadSaveData();
	}
	
	private void loadSaveData() {
		Hero[] savedCharacters = Player.grabSavedCharacters();
		JComboBox<Hero> classList = new JComboBox<Hero>(savedCharacters);
		classList.setSelectedIndex(0);
		classList.setBounds(300, 140, 200, 40);
		classList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				@SuppressWarnings("unchecked")
//				JComboBox<String> selection = (JComboBox<String>)e.getSource();
//				classSelected = classes[selection.getSelectedIndex()];
//				setStatPreview();
			}
		});
		this.add(classList);
	}

//	private void initNewGameButton() {
//		JButton newGameButton = new JButton("New Game");
//		newGameButton.setBounds(260, 100, 300, 140);
//		newGameButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				GameEngine.progressGame(Panels.NewGame.ordinal());
//			}
//		});
//		this.add(newGameButton);
//	}
//	
//	private void initLoadGameButton() {
//		JButton loadGameButton = new JButton("Load Game");
//		loadGameButton.setBounds(260, 300, 300, 140);
//		this.add(loadGameButton);
//	}
}
