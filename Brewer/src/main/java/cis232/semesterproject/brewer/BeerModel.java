package cis232.semesterproject.brewer;

//REQ #3
//REQ #5
//REQ #10
public interface BeerModel {
	public double CornSugarCarbonation(Double v, Double s);
	public double TableSugarCarbonation(Double v, Double s);
	public String getName();
	public String getHops();
	public String getGrains();
	public String getYeast();
	public String getSpecial();
	
	public static String getType(String m) {
		return m;
	}
}
