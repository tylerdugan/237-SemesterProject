package cis232.semesterproject.brewer;

//REQ #4
//REQ #6
public class PureBeer implements BeerModel {
	/**
	 * Pure Beer only has 3 main ingredients
	 */
	
	int id;			//Beer ID
	String name;	//Beer Name
	String grains;	//Grain Name
	String yeast;	//Yeast Name
	String hops;	//Hops Name
	
	//Constructor used for adding recipe
	public PureBeer(String n, String g, String y, String h){
		this.name = n;
		this.grains = g;
		this.yeast = y;
		this.hops = h;
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


	@Override
	public double CornSugarCarbonation(Double v, Double s) {
		Double carbLvl = .455 * v / s;
		return carbLvl;
	}

	@Override
	public double TableSugarCarbonation(Double v, Double s) {
		Double carbLvl = .67 * v / s;
		return carbLvl;
	}

	public String getType(String m) {
		return m;
	}

	@Override
	public String getSpecial() {
		return null;
	}
}
