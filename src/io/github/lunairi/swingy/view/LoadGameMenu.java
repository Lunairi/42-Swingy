package io.github.lunairi.swingy.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import io.github.lunairi.swingy.controller.GameEngine;
import io.github.lunairi.swingy.controller.Player;
import io.github.lunairi.swingy.model.Class;
import io.github.lunairi.swingy.model.Hero;
import io.github.lunairi.swingy.model.Class.Stats;
import io.github.lunairi.swingy.view.GUI.Panels;

@SuppressWarnings("serial")
public class LoadGameMenu extends JPanel {
	
	private static Hero[] savedCharacters;
	private static JComboBox<String> classList;
	private JLabel statPreview;
	
	public LoadGameMenu() {
		this.setSize(800, 600);
		this.setLayout(null);
		this.setVisible(false);
		this.displaySavedCharacters();
		this.previewStats();
		this.loadGameButton();
		this.deleteSavedCharacter();
		this.backButton();
	}
	
	public static void loadSaveData() {
		savedCharacters = Player.grabSavedCharacters();
		classList.removeAllItems();
		for (int i = 0; i < savedCharacters.length; i++) {
			classList.addItem((String)savedCharacters[i].get("name"));
		}
	}
	
	private String[] pullCharacterNames() {
		String[] characterNames = new String[savedCharacters.length];
		for (int i = 0; i < savedCharacters.length; i++) {
			characterNames[i] = (String)savedCharacters[i].get("name");
		}
		return characterNames;
	}
	
	private void displaySavedCharacters() {
		savedCharacters = Player.grabSavedCharacters();
		classList = new JComboBox<String>(this.pullCharacterNames());
		classList.setSelectedIndex(0);
		classList.setBounds(300, 140, 200, 40);
		classList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				@SuppressWarnings("unchecked")
//				JComboBox<String> selection = (JComboBox<String>)e.getSource();
				int index = classList.getSelectedIndex();
				setStatPreview(index == -1 ? 0 : index);
			}
		});
		this.add(classList);
	}
	
	private void setStatPreview(int index) {
		Hero savedChar = savedCharacters[index];
		this.statPreview.setText(
				"Level: [" + savedChar.get("level") +
				"] - Class: [" + savedChar.get("job") +
				"] - Health: [" + savedChar.get("health") + 
				"] - Attack: [" + savedChar.get("attack") +
				"] - Defense: [" + savedChar.get("defense") +
				"] - Speed [" + savedChar.get("speed") + "]"
		);
	}
	
	private void previewStats() {
		this.statPreview = new JLabel();
		this.setStatPreview(0);
		this.statPreview.setBounds(150, 200, 600, 40);
		this.add(statPreview);
	}
	
	private void loadGameButton() {
		JButton loadGame = new JButton("Start Playing");
		loadGame.setBounds(180, 260, 200, 40);
		loadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = classList.getSelectedIndex();
				int heroID = (int)savedCharacters[index].getId();
				GameEngine.player.loadCharacter(heroID);
				GameEngine.map.generateMap(GameEngine.player.getLevel());
				GameEngine.progressGame(Panels.WorldMap.ordinal());
			}
		});
		this.add(loadGame);
	}
	
	private void deleteSavedCharacter() {
		JButton deleteCharacter = new JButton("Delete");
		deleteCharacter.setBounds(400, 260, 200, 40);
		deleteCharacter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player.deleteSavedCharacter(savedCharacters[classList.getSelectedIndex()]);
				GameEngine.progressGame(Panels.StartMenu.ordinal());
			}
		});
		this.add(deleteCharacter);
	}

	private void backButton() {
		JButton backButton = new JButton("Back");
		backButton.setBounds(280, 330, 200, 40);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameEngine.progressGame(Panels.StartMenu.ordinal());
			}
		});
		this.add(backButton);
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
