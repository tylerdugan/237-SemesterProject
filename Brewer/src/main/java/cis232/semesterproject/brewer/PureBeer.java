package cis232.semesterproject.brewer;

public class PureBeer {
	/**
	 * Pure Beer only has 3 main ingredients
	 */
	
	int id;			//Beer ID
	String name;	//Beer Name
	String grains;	//Grain Name
	String yeast;	//Yeast Name
	String hops;	//Hops Name
	
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
}
