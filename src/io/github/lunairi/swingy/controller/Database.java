package io.github.lunairi.swingy.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.javalite.activejdbc.Base;


public class Database {
	
	public Database() {
		Base.open("org.sqlite.JDBC", "jdbc:sqlite:swingy.db", "root", "");
	}
	
	public void initTables() {
		this.createHeroesTable();
		Base.close();
	}
	
	private void createHeroesTable() {
		Base.exec("CREATE TABLE IF NOT EXISTS heroes (\n"
				+ "id integer PRIMARY KEY,\n"
				+ "name text NOT NULL,\n"
				+ "job text NOT NULL,\n"
				+ "level integer NOT NULL,\n"
				+ "xp integer NOT NULL,\n"
				+ "health integer NOT NULL,\n"
				+ "attack integer NOT NULL,\n"
				+ "defense integer NOT NULL,\n"
				+ "speed integer NOT NULL);");
	}
//		System.out.println(Stat.getTableName());
//		new Stat().set("level", "1").saveIt();
//		new Stat().set("level", "10").saveIt();
//		Stat test = Stat.findFirst("level = 1");
//		System.out.println(test.get("level"));
//		Hero test = new Hero().set("name", "Test");
//		test.saveIt();
//		Stat tests = new Stat().set(
//				"hero_id", test.getId(),
//				"level", 0,
//				"xp", 0,
//				"health", 10,
//				"attack", 10,
//				"defense", 10,
//				"speed", 10);
//		tests.saveIt();
//		test.add(tests);
//		test.saveIt();
//		System.out.println(test.get("name"));
//		System.out.println(test.getAll(Stat.class));
//		System.out.println(tests.parent(Hero.class));
	
}
