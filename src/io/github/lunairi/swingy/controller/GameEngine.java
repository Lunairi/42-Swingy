package io.github.lunairi.swingy.controller;

import java.util.Date;
import java.util.Random;

import io.github.lunairi.swingy.view.GUI;

public class GameEngine {
	
	private static GUI gui;
	public static Player player;
	public static Map map;
	public static Random random;

	public GameEngine() {
		random = new Random((int)new Date().getTime());
		map = new Map();
		map.generateMap(1);
		gui = new GUI("Swingy RPG", 800, 600);
		player = new Player();
		player.initPosition();
		gui.initPanels();
	}

	public static void progressGame(int panelID) {
		gui.renderPanel(panelID);
	}


}
