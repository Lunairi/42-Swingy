package io.github.lunairi.swingy.model;

import java.util.HashMap;

public class Class {
	
	public String jobName;
	
	@SuppressWarnings("serial")
	public static HashMap<String, int[]> classes = new HashMap<String, int[]>(){{
		put("Knight", new int[] {15, 4, 20, 1});
		put("Fighter", new int[] {10, 10, 10, 10});
		put("Trickster", new int[] {8, 12, 5, 15});
		put("Chanter", new int[] {7, 20, 5, 8});
		put("Marksman", new int[] {8, 14, 6, 12});
	}};
	
	public enum Stats {
		Health, Attack, Defense, Speed
	}
	
	public Class() {};
	
//	public Class(String name, Stat stats) {
//		this.jobName = name;
//		stats.setStat(this.classes.get(name));
//	}
//
//}
}
