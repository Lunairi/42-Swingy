package io.github.lunairi.swingy.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Table("heroes")
public class Hero extends Model {

//	@NotNull
//	private String NAME;
//
//	@NotNull
//	private String job;
//	
//	@NotNull
//	@Min(1)
//	private int level;
//	
//	@NotNull 
//	@Min(0)
//	private int xp;
//	
//	@NotNull
//	@Size(min = 0, max = 9999)
//	private int health;
//	
//	@NotNull
//	@Size(min = 0, max = 9999)
//	private int attack;
//	
//	@NotNull
//	@Size(min = 0, max = 9999)
//	private int defense;
//	
//	@NotNull
//	@Size(min = 0, max = 9999)
//	private int speed; 

}
