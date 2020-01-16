package io.github.lunairi.swingy.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Map {
	public static List<int[]> map = new ArrayList<int[]>();
	public static int mapSize;
	
	public enum Obstacles {
		Nothing, Enemy, Town, Chest
	}

	public Map() {}


	public void generateMap(int level) {
		map.clear();
		mapSize = (level - 1) * 5 + 10 - (level % 2);
		for (int i = 0; i < 10; i++) {
			int[] mapRow = new int[mapSize];
			for (int j = 0; j < mapSize; j++) {
				int rand = GameEngine.random.nextInt(100);
				if (rand % 7 == 0) {
					mapRow[j] = Obstacles.Enemy.ordinal();
				}
				else if (rand % 23 == 0) {
					mapRow[j] = Obstacles.Town.ordinal();
				}
				else if (rand % 37 == 0) {
					mapRow[j] = Obstacles.Chest.ordinal();
				}
				else {
					mapRow[j] = Obstacles.Nothing.ordinal();
				}
			}
			map.add(mapRow);
		}
	}
}
