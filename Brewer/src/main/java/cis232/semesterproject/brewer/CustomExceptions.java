package cis232.semesterproject.brewer;

public class CustomExceptions extends Exception {
	
	//REQ #12
	public CustomExceptions(String m){
		super(m);
		System.out.println("You Suck");
	}
}
