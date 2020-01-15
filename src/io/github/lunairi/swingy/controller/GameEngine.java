package io.github.lunairi.swingy.controller;

import io.github.lunairi.swingy.view.GUI;

public class GameEngine {
	
	private static GUI gui;
	private static Player player;

	public GameEngine() {
		gui = new GUI("Swingy RPG", 800, 600);
		player = new Player();
		gui.initPanels();
	}

	public static void progressGame(int panelID) {
		gui.renderPanel(panelID);
	}


}
