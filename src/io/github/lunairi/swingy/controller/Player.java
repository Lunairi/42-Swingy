package io.github.lunairi.swingy.controller;

import io.github.lunairi.swingy.model.Class.Stats;

import java.util.List;

import javax.swing.JTable;

import org.javalite.activejdbc.Base;

import io.github.lunairi.swingy.model.Hero;

public class Player {
	
	public static Hero player;

	public Player() {
	}
	
	public static void createCharacter(String name, String job, int[] stats) {
		Base.open("org.sqlite.JDBC", "jdbc:sqlite:swingy.db", "root", "");
		player = new Hero().set(
				"name", name,
				"job", job,
				"level", 1,
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
		Hero[] savedCharacters = new Hero[list.size()];
		for (int i = 0; i < list.size(); i++) {
			savedCharacters[i] = list.get(i);
		}
		Base.close();
		return savedCharacters;
	}
}
