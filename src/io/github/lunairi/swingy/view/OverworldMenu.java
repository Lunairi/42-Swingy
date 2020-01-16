package io.github.lunairi.swingy.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import io.github.lunairi.swingy.controller.GameEngine;
import io.github.lunairi.swingy.controller.Map;
import io.github.lunairi.swingy.controller.Player;
import io.github.lunairi.swingy.controller.Map.Obstacles;
import io.github.lunairi.swingy.view.GUI.Panels;
import io.github.lunairi.swingy.model.Class;
import io.github.lunairi.swingy.model.Class.Stats;

@SuppressWarnings("serial")
public class OverworldMenu extends JPanel {
	
	private JButton[] mapButtons = new JButton[81];
	private Icon[] icons = new Icon[4];
	private Icon playerIcon;
	private JEditorPane stats = new JEditorPane();
	
	public OverworldMenu() {
		this.setSize(800, 600);
		this.setLayout(null);
		this.setVisible(false);
		this.loadIcons();
		this.generateMap();
//		this.statMenu();
		this.moveUpButton();
	}
	
	private void clearMap() {
		for (int i = 0; i < this.mapButtons.length; i++) {
			this.remove(this.mapButtons[i]);
		}
	}
	
	private void loadIcons() {
		this.icons[Obstacles.Nothing.ordinal()] = new ImageIcon(System.getProperty("user.dir") + "/assets/map_icons/grass.png");
		this.icons[Obstacles.Enemy.ordinal()] = new ImageIcon(System.getProperty("user.dir") + "/assets/map_icons/monster.png");
		this.icons[Obstacles.Town.ordinal()] = new ImageIcon(System.getProperty("user.dir") + "/assets/map_icons/town.png");
		this.icons[Obstacles.Chest.ordinal()] = new ImageIcon(System.getProperty("user.dir") + "/assets/map_icons/chest.png");
		this.playerIcon = new ImageIcon(System.getProperty("user.dir") + "/assets/map_icons/player.png"); 
	}
	
	private void generateMap() {
		int x = GameEngine.player.getPosX() - 4;
		for (int i = 0; i < 9; i++) {
			int currentIndex = 9 * i;
			int[] currentRow = Map.map.get(x++);
			int y = GameEngine.player.getPosY() - 4;
			for (int j = 0; j < 9; j++) {
				mapButtons[currentIndex + j] = new JButton();
				mapButtons[currentIndex + j].setBounds(i * 50, j * 50, 50, 50);
				if ((x - 1) == GameEngine.player.getPosX() && y == GameEngine.player.getPosY()) {
					mapButtons[currentIndex + j].setIcon(this.playerIcon);
					
				}
				else if (currentRow[y] == Obstacles.Nothing.ordinal()) {
					mapButtons[currentIndex + j].setIcon(this.icons[Obstacles.Nothing.ordinal()]);
				}
				else if (currentRow[y] == Obstacles.Enemy.ordinal()) {
					mapButtons[currentIndex + j].setIcon(this.icons[Obstacles.Enemy.ordinal()]);
				}
				else if (currentRow[y] == Obstacles.Town.ordinal()) {
					mapButtons[currentIndex + j].setIcon(this.icons[Obstacles.Town.ordinal()]);
				}
				else {
					mapButtons[currentIndex + j].setIcon(this.icons[Obstacles.Chest.ordinal()]);
				}
				this.add(mapButtons[currentIndex + j]);
				y++;
			}
		}
	}
	
	@Override
	public void setVisible(boolean state) {
		try {
			this.statMenu();
		}
		finally {
			super.setVisible(state);
		}
	}
	
	private void statMenu() {
		this.stats.setContentType("text/html");
		this.stats.setText("<center><h1>" + Player.player.get("NAME") + " Stats</h1><center>"
				+ "<p><b>Level:</b> " + Player.player.get("LEVEL") + "</p>"
				+ "<p><b>Exp:</b> " + Player.player.get("XP") + "</p>"
				+ "<p><b>Health:</b> " + Player.player.get("HEALTH") + "</p>"
				+ "<p><b>Attack:</b> " + Player.player.get("ATTACK") + "</p>"
				+ "<p><b>Defense:</b> " + Player.player.get("DEFENSE") + "</p>"
				+ "<p><b>Speed:</b> " + Player.player.get("SPEED") + "</p>"
				);
		this.stats.setBounds(450,0,350,300);
		this.stats.setEditable(false);
		this.stats.setBackground(Color.lightGray);
		this.add(stats);
	}
	
	private void moveUpButton() {
		JButton button = new JButton("Move Up");
		button.setBounds(580,300,100,50);
		this.add(button);
	}
//	private void initHeader() {
//		JLabel header = new JLabel();
//		header.setText("Create New Character");
//		header.setBounds(320, 20, 200, 40);
//		this.add(header);
//	}
//	
//	private void heroNameBox() {
//		this.nameBox = new JTextField();
//		this.nameBox.setText("   Input hero name here...");
//		this.nameBox.setBounds(300, 80, 200, 40);
//		this.add(this.nameBox);
//	}
//	
//	private void classSelection() {
//		String[] classes = {"Knight", "Fighter", "Trickster", "Chanter", "Marksman"};
//		JComboBox<String> classList = new JComboBox<String>(classes);
//		classList.setSelectedIndex(0);
//		classList.setBounds(300, 140, 200, 40);
//		classList.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				@SuppressWarnings("unchecked")
//				JComboBox<String> selection = (JComboBox<String>)e.getSource();
//				classSelected = classes[selection.getSelectedIndex()];
//				setStatPreview();
//			}
//		});
//		this.add(classList);
//	}
//	
//	private void setStatPreview() {
//		int[] stats = Class.classes.get(classSelected);
//		this.statPreview.setText(
//				"Health: [" + stats[Stats.Health.ordinal()] + 
//				"] - Attack: [" + stats[Stats.Attack.ordinal()] +
//				"] - Defense: [" + stats[Stats.Defense.ordinal()] +
//				"] - Speed [" + stats[Stats.Speed.ordinal()] + "]"
//		);
//	}
//	
//	private void previewStats() {
//		this.statPreview = new JLabel();
//		this.setStatPreview();
//		this.statPreview.setBounds(240, 200, 400, 40);
//		this.add(statPreview);
//	}
//	
//	private void confirmCharacterButton() {
//		JButton createButton = new JButton("Confirm Character Creation");
//		createButton.setBounds(240, 260, 300, 40);
//		createButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				Player.createCharacter(nameBox.getText(), classSelected, Class.classes.get(classSelected));
//				GameEngine.progressGame(Panels.StartMenu.ordinal()); // will have to change to game panel
//			}
//		});
//		this.add(createButton);
//	}
//	
//	private void backButton() {
//		JButton backButton = new JButton("Back");
//		backButton.setBounds(280, 310, 200, 40);
//		backButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				GameEngine.progressGame(Panels.StartMenu.ordinal());
//			}
//		});
//		this.add(backButton);
//	}
}







