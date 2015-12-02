package cis232.semesterproject.brewer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CraftBeerDB {

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
				String dropTable = "drop Table CraftBeers";
				stmt.execute(dropTable);
				System.out.println("Craft Beers table dropped");
			}catch(SQLException e){
				System.out.println("Craft Beers table doesn't exist");
			}
			
			//Create table string
			String createBeersTable = "create table CraftBeers(" +
								"beerid int" +
								"id int not null primary key, " + 
								"special varchar(500) " +
								")";
			
			//Execute table creation
			try{
				stmt.execute(createBeersTable);
				System.out.println("Craft Beers table created.");
			} catch(SQLException e){
				System.out.printf("Error creating Craft Beers table: %s", e.getMessage());
			}
			
			
			/**
			 * Add Craft Beers Below
			 */
		}
		
		public static void addBeer(Connection conn, int beerid, int id, String special){
			Statement stmt = null;
			try{
				stmt = conn.createStatement();
				
				String insertBeer = String.format("Insert Into PureBeers (beerid, id, special)" +
													" values(%d, %d, '%s')", beerid, id, special);
				stmt.execute(insertBeer);
			}catch(SQLException | NumberFormatException e){
				System.out.printf("Error adding beer: %s", e.getMessage());
			}
		}
}
