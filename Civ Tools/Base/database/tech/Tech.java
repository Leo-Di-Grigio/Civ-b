package database.tech;

import java.util.ArrayList;

public class Tech {
	
	private static int ID;
	
	// info
	private int id;
	private String title;
	private int scinceCost;
	
	// status
	private boolean learned;
	
	// graph
	private ArrayList<Tech> parents;
	private ArrayList<Tech> childs;
	
	public Tech(String title, int scinceCost){
		this.id = ID++;
		this.title = title;
		this.scinceCost = scinceCost;
		
		parents = new ArrayList<Tech>();
		childs = new ArrayList<Tech>();
	}
	
	public int getId(){
		return id;
	}
	
	public String getTitle(){
		return title;
	}
	
	public ArrayList<Tech> getChilds(){
		return childs;
	}
	
	public int getCost(){
		return scinceCost;
	}
	
	public boolean learned(){
		return learned;
	}
	
	public void learn(){
		this.learned = true;
	}
	
	private void addChild(Tech tech, TeamTech teamTech){
		tech.addParent(this);
		childs.add(tech);
		teamTech.techList.put(tech.getTitle(), tech);
		teamTech.techListId.put(tech.getId(), tech);
	}
	
	private void addParent(Tech tech){
		parents.add(tech);
	}

	public static TeamTech buildTechTree(){
		// reset
		TeamTech teamTech = new TeamTech();
		Tech.ID = 0;
		
		// Prehistory
		Tech stone = new Tech("Stone working", 0);
		Tech fire = new Tech("Fire", 10);
		Tech tailoring = new Tech("Tailoring", 10);
		Tech music = new Tech("Music", 10);
		Tech bow = new Tech("Bow", 10);
		Tech fishing = new Tech("Fishing", 10);
		Tech pottery = new Tech("Pottery", 10);
		Tech writing = new Tech("Writing", 10);
		Tech calendar = new Tech("Calendar", 10);
		Tech bronze = new Tech("Bronze working", 10);
		Tech irigation = new Tech("Irigation", 10);
		Tech iron = new Tech("Iron working", 10);
		Tech alphabet = new Tech("Alphabet", 10);
		Tech city = new Tech("City", 10);
		
		// root
		teamTech.root.addChild(stone, teamTech);
		
		// prehistory
		stone.addChild(fire, teamTech);
		fire.addChild(tailoring, teamTech);
		tailoring.addChild(music, teamTech);
		music.addChild(bow, teamTech);
		bow.addChild(fishing, teamTech);
		fishing.addChild(pottery, teamTech);
		pottery.addChild(writing, teamTech);
		writing.addChild(calendar, teamTech);
		calendar.addChild(bronze, teamTech);
		bronze.addChild(irigation, teamTech);
		irigation.addChild(iron, teamTech);
		iron.addChild(alphabet, teamTech);
		alphabet.addChild(city, teamTech);
		
		return teamTech;
	}
}