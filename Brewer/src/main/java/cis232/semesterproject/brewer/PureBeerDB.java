package cis232.semesterproject.brewer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PureBeerDB {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		
		//Initialize DB Connection and create statement
		try{
			conn = DriverManager.getConnection("jdbc:derby:db/Beers; create=true");
			System.out.println("Connection Initialized");
			stmt = conn.createStatement();
		}catch(SQLException e){
			System.out.printf("DB Error %s: ", e.getMessage());
			e.printStackTrace();
		}
		
		//Drops table if already exists
		try{
			String dropTable = "drop Table PureBeers";
			stmt.execute(dropTable);
			System.out.println("Pure Beers table dropped");
		}catch(SQLException e){
			System.out.println("Pure Beers table doesn't exist");
		}
		
		//Create table string
		String createBeersTable = "create table PureBeers(" +
							"id int not null primary key, " + 
							"name varchar(100), " +
							"grains varchar(50), " +
							"yeast varchar(50), " +
							"hops varchar(50) " +
							")";
		
		//Execute table creation
		try{
			stmt.execute(createBeersTable);
			System.out.println("Pure Beers table created.");
		} catch(SQLException e){
			System.out.printf("Error creating Beers table: %s", e.getMessage());
		}
		
		/**
		 * Add Pure Beers Below
		 */
		addBeer(conn, 01, "Saison", "6-row", "Wyeast1056", "Fuggle");
		addBeer(conn, 02, "Porter", "2-row", "US-05", "Saaz");
		addBeer(conn, 03, "PaleAle", "Pilsen", "WLP566", "Cascade");
		addBeer(conn, 04, "BarleyWine", "Crystal", "Wyeast1084", "Columbus");
		addBeer(conn, 05, "Stout", "Wheat", "Wyeast1764", "Calypso");
		
		/**
		 * Craft Beers
		 */
	}
	
	public static void addBeer(Connection conn, int id, String name, String grains, String yeast, String hops){
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			
			String insertBeer = String.format("Insert Into PureBeers (id, name, grains, yeast, hops)" +
												" values(%d, '%s', '%s', '%s', '%s')",
												 id, name, grains, yeast, hops);
			stmt.execute(insertBeer);
			System.out.printf("%s added %n", name);
		}catch(SQLException | NumberFormatException e){
			System.out.printf("Error adding beer: %s", e.getMessage());
		}
	}
}
