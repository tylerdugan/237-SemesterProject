package cis232.semesterproject.brewer;

public class CraftBeer {
	/**
	 * Craft beers have 3 main ingredients and any number
	 * of special ingredients, retrieved from GUI text field.
	 * All special ingredients stored as a single long string.
	 */
	
	int id;			//Beer ID
	String name;	//Beer Name
	String grains;	//Grain Name
	String yeast;	//Yeast Name
	String hops;	//Hops Name
	String special; //Special ingredient names/amounts are store in one long string
	
	public CraftBeer(String n, String g, String y, String h, String spec){
		this.name = n;
		this.grains = g;
		this.yeast = y;
		this.hops = h;
		this.special = spec;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGrains() {
		return grains;
	}
	public void setGrains(String grains) {
		this.grains = grains;
	}
	public String getYeast() {
		return yeast;
	}
	public void setYeast(String yeast) {
		this.yeast = yeast;
	}
	public String getHops() {
		return hops;
	}
	public void setHops(String hops) {
		this.hops = hops;
	}
	public void setSpecial(String special){
		this.special = special;
	}
	public String getSpecial(){
		return special;
	}
}
