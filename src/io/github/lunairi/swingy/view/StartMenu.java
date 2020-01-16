package io.github.lunairi.swingy.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import io.github.lunairi.swingy.controller.GameEngine;
import io.github.lunairi.swingy.view.GUI.Panels;

@SuppressWarnings("serial")
public class StartMenu extends JPanel {
	
	public StartMenu() {
		this.setSize(800, 600);
		this.setLayout(null);
		this.initNewGameButton();
		this.initLoadGameButton();
		this.setVisible(true);
	}

	private void initNewGameButton() {
		JButton newGameButton = new JButton("New Game");
		newGameButton.setBounds(260, 100, 300, 140);
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameEngine.progressGame(Panels.NewGame.ordinal());
			}
		});
		this.add(newGameButton);
	}
	
	private void initLoadGameButton() {
		JButton loadGameButton = new JButton("Load Game");
		loadGameButton.setBounds(260, 300, 300, 140);
		loadGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadGameMenu.loadSaveData();
				GameEngine.progressGame(Panels.LoadGame.ordinal());
			}
		});
		this.add(loadGameButton);
	}
}
