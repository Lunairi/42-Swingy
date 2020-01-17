package io.github.lunairi.swingy.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

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
//		int x = (GameEngine.player.getPosX() - 4) < 0 ? 0 : (GameEngine.player.getPosX() - 4);
//		if (x >= Map.mapSize - 4) {
//			x -= 1;
//		}
		int x = 0;
		for (int i = 0; i < 9; i++) {
			int currentIndex = 9 * i;
			int[] currentRow = Map.map.get(x++);
//			int y = (GameEngine.player.getPosY() - 4) < 0 ? 0 : (GameEngine.player.getPosY() - 4);
//			if (y >= Map.mapSize - 4) {
//				y -= 1;
//			}
			int y = 0;
			for (int j = 0; j < 9; j++) {
				this.mapButtons[currentIndex + j] = new JButton();
				this.mapButtons[currentIndex + j].setBounds(i * 50, j * 50, 50, 50);
				if ((x - 1) == GameEngine.player.getPosX() && y == GameEngine.player.getPosY()) {
					this.mapButtons[currentIndex + j].setIcon(this.playerIcon);
					this.mapButtons[currentIndex + j].setToolTipText("You");
				}
				else if (currentRow[y] == Obstacles.Nothing.ordinal()) {
					this.mapButtons[currentIndex + j].setIcon(this.icons[Obstacles.Nothing.ordinal()]);
					this.mapButtons[currentIndex + j].setToolTipText("Plains");
				}
				else if (currentRow[y] == Obstacles.Enemy.ordinal()) {
					this.mapButtons[currentIndex + j].setIcon(this.icons[Obstacles.Enemy.ordinal()]);
					this.mapButtons[currentIndex + j].setToolTipText("Enemy Encounter");
				}
				else if (currentRow[y] == Obstacles.Town.ordinal()) {
					this.mapButtons[currentIndex + j].setIcon(this.icons[Obstacles.Town.ordinal()]);
					this.mapButtons[currentIndex + j].setToolTipText("Town - Random Event");
				}
				else {
					this.mapButtons[currentIndex + j].setIcon(this.icons[Obstacles.Chest.ordinal()]);
					this.mapButtons[currentIndex + j].setToolTipText("Chest - Random Event");
				}
				this.add(this.mapButtons[currentIndex + j]);
				y++;
			}
		}
	}
	
	@Override
	public void setVisible(boolean state) {
		this.statMenu();
		super.setVisible(state);
	}
	
	private void statMenu() {
		if (Player.player == null) {
			return;
		}
		this.stats.setContentType("text/html");
		this.stats.setText("<center><h1>" + Player.player.get("NAME") + " Stats</h1><center>"
				+ "<p><b>Job:</b> " + Player.player.get("JOB") + " - "
				+ "<b>Level:</b> " + Player.player.get("LEVEL") + "</p>"
				+ "<p><b>Exp:</b> " + Player.player.get("XP") + "</p>"
				+ "<p><b>Health:</b> " + Player.player.get("HEALTH") + "</p>"
				+ "<p><b>Attack:</b> " + Player.player.get("ATTACK") + "</p>"
				+ "<p><b>Defense:</b> " + Player.player.get("DEFENSE") + "</p>"
				+ "<p><b>Speed:</b> " + Player.player.get("SPEED") + "</p>"
				+ "<p><b>Coordinates:</b> " + GameEngine.player.getPosX() + "x " + GameEngine.player.getPosY() + "y</p>"
				);
		this.stats.setBounds(450,0,350,310);
		this.stats.setEditable(false);
		this.stats.setBackground(Color.lightGray);
		this.add(stats);
	}
	
	private void refreshPanel() {
		clearMap();
		statMenu();
		generateMap();
		revalidate();
		repaint();
	}
	
	private void moveUpButton() {
		HashMap<String, int[]> buttons = new HashMap<String, int[]>() {{
			put("Move Up", new int[] {565, 320, 100, 50});
			put("Move Left", new int[] {455, 350, 100, 50});
			put("Move Right", new int[] {675, 350, 100, 50});
			put("Move Down", new int[] {565, 380, 100, 50});
		}};
		for (final Entry<String, int[]> buttonInfo : buttons.entrySet()) {
			int[] coords = buttonInfo.getValue();
			JButton button = new JButton(buttonInfo.getKey());
			button.setBounds(coords[0], coords[1], coords[2], coords[3]);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GameEngine.player.setPosition(buttonInfo.getKey());
					refreshPanel();
				}
			});
			this.add(button);
		}
	}
}







