package io.github.lunairi.swingy.controller;

import org.javalite.activejdbc.Base;

import io.github.lunairi.swingy.view.GUI;

public class Swingy {
	
	private static GameEngine game;
	private static Database db;
	
	public static void main(String[] args) {
		db = new Database();
		db.initTables();
		
		game = new GameEngine();
		
	}
}
