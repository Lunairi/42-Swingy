package io.github.lunairi.swingy.controller;

import io.github.lunairi.swingy.model.Class.Stats;

import java.util.List;

import javax.swing.JTable;

import org.javalite.activejdbc.Base;

import io.github.lunairi.swingy.model.Hero;

public class Player {
	
	public static Hero player;
	
	private int posX;
	private int posY;

	public Player() {}
	
	public void initPosition() {
		this.posX = Map.mapSize / 2;
		this.posY = Map.mapSize / 2;
	}
	
	public void setPosition(String direction) {
		if (direction.equals("Move Up")) {
			this.posY = this.posY - 1 < 0 ? 0 : this.posY - 1;
		}
		else if (direction.equals("Move Left")) {
			this.posX = this.posX - 1 < 0 ? 0 : this.posX - 1;
		}
		else if (direction.equals("Move Right")) {
			this.posX = this.posX + 1 >= Map.mapSize ? Map.mapSize - 1 : this.posX + 1;
		}
		else if (direction.equals("Move Down")) {
			this.posY = this.posY + 1 >= Map.mapSize ? Map.mapSize - 1 : this.posY + 1;
		}
	}
	
	public int getPosX() {
		return this.posX;
	}
	
	public int getPosY() {
		return this.posY;
	}
	
	public int getLevel() {
		return (Integer)player.get("level");
	}
	
	public void loadCharacter(int index) {
		Base.open("org.sqlite.JDBC", "jdbc:sqlite:swingy.db", "root", "");
		player = Hero.findById(index);
		Base.close();
	}
	
	public static void createCharacter(String name, String job, int[] stats) {
		Base.open("org.sqlite.JDBC", "jdbc:sqlite:swingy.db", "root", "");
		player = new Hero().set(
				"name", name,
				"job", job,
				"level", 3,
				"xp", 0,
				"health", stats[Stats.Health.ordinal()],
				"attack", stats[Stats.Attack.ordinal()],
				"defense", stats[Stats.Defense.ordinal()],
				"speed", stats[Stats.Speed.ordinal()]);
		player.saveIt();
		Base.close();
	}
	
	public static Hero[] grabSavedCharacters() {
		Base.open("org.sqlite.JDBC", "jdbc:sqlite:swingy.db", "root", "");
		List<Hero> list = Hero.findAll();
		System.out.println(list.size());
		Hero[] savedCharacters = new Hero[list.size()];
		for (int i = 0; i < list.size(); i++) {
			savedCharacters[i] = list.get(i);
		}
		Base.close();
		return savedCharacters;
	}
	
	public static void deleteSavedCharacter(Hero savedData) {
		Base.open("org.sqlite.JDBC", "jdbc:sqlite:swingy.db", "root", "");
		savedData.delete();
		Base.close();
	}
}
