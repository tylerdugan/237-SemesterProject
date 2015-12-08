package cis232.semesterproject.brewer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
 * Controller Class for Home Brewer GUI
 */

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BrewerGUIController {
	
	 @FXML
	    private Label LabelWarn;

	    @FXML
	    private ComboBox<String> ComboBoxHops;

	    @FXML
	    private TextField TextBoxCo2;

	    @FXML
	    private Button ButtonRein;

	    @FXML
	    private Button ButtonSubmit;

	    @FXML
	    private ComboBox<String> ComboBoxGrains;

	    @FXML
	    private ComboBox<String> ComboBoxStyle;

	    @FXML
	    private TextField TextBoxTarget;

	    @FXML
	    private Button ButtonDelete;

	    @FXML
	    private Button ButtonUpdate;

	    @FXML
	    private Button ButtonAdd;

	    @FXML
	    private ComboBox<String> ComboBoxYeast;

	    @FXML
	    private ComboBox<String> ComboBoxName;

	    @FXML
	    private Label LabelReinYes;

	    @FXML
	    private TextField TextBoxGallons;

	    @FXML
	    private TextField TextBoxSugar;

	    @FXML
	    private Label LabelReinNo;

	    @FXML
	    private TextArea TextAreaSpecial;
	    
	    @FXML
	    private TextField TextFieldCustomName;
	    
    @FXML
    private void initialize(){
    	LoadRecipeBook();
    }
    
    //Array Lists for each ingredient
	ObservableList<String> name = FXCollections.observableArrayList();
	ObservableList<String> grains = FXCollections.observableArrayList();
	ObservableList<String> yeast = FXCollections.observableArrayList();
	ObservableList<String> hops = FXCollections.observableArrayList();
    
	
	
	
	
	
	
	
	
	
	
	
	
    @FXML
    private void LoadRecipeBook(){
    	Connection conn = null;
    	Statement stmt = null;
    	
    	//Connect to database and create statement
    	try{
			conn = DriverManager.getConnection("jdbc:derby:db/Beers; create=true");
			System.out.println("Connection Initialized");
			stmt = conn.createStatement();
		}catch(SQLException e){
			System.out.printf("DB Error %s: ", e.getMessage());
			e.printStackTrace();
		}
    	
    	//Load Recipe Book values from PureBeers table
    	try{
    		ResultSet ingredients = stmt.executeQuery("select name, grains, yeast, hops from PureBeers");
    		
    		
    		
    		//Load PureBeer values to Observable List
    		while(ingredients.next()){
    			name.add(ingredients.getString("name"));
    			grains.add(ingredients.getString("grains"));
    			yeast.add(ingredients.getString("yeast"));
    			hops.add(ingredients.getString("hops"));
    		}
    		
    		//Load lists to GUI
    		ComboBoxName.setItems(name);
    		ComboBoxGrains.setItems(grains);
    		ComboBoxYeast.setItems(yeast);
    		ComboBoxHops.setItems(hops);
    		
    		ingredients = stmt.executeQuery("select name, grains, yeast, hops, Special from CraftBeers");
    		
    		//Load CraftBeers values to Observable List
    		while(ingredients.next()){
    			name.add(ingredients.getString("name"));
    			grains.add(ingredients.getString("grains"));
    			yeast.add(ingredients.getString("yeast"));
    			hops.add(ingredients.getString("hops"));
    		}
    		
    		//Load lists to GUI
    		ComboBoxName.setItems(name);
    		ComboBoxGrains.setItems(grains);
    		ComboBoxYeast.setItems(yeast);
    		ComboBoxHops.setItems(hops);
    		
    	}catch(SQLException e){
    		e.getMessage();
    	}
    }
    
    
    
    
    
    
    
    
    
    
    /*
     * Matches recipe book ingredients based off of 
     * Beer name selected
     */
    @FXML
    public void MatchRecipeValues(){
    	Connection conn = null;
    	Statement stmt = null;
    	
    	//Connect to database and create statement
    	try{
			conn = DriverManager.getConnection("jdbc:derby:db/Beers; create=true");
			stmt = conn.createStatement();
		}catch(SQLException e){
			System.out.printf("DB Error %s: ", e.getMessage());
			e.printStackTrace();
		}
    	
    	//Select beers from database
    	try{
    			//Matches values for Pure Beers
	    		ResultSet ingredients = stmt.executeQuery("select grains, yeast, hops from PureBeers where name='" + ComboBoxName.getValue() + "'");
	    		if(ingredients.next()){
		    		ComboBoxYeast.setValue(ingredients.getString("yeast"));
		    		ComboBoxGrains.setValue(ingredients.getString("grains"));
		    		ComboBoxHops.setValue(ingredients.getString("hops"));
		    		TextAreaSpecial.setText("");
	    		}
	    		
	    		//Matches values for Craft Beers
	    		ingredients = stmt.executeQuery("select grains, yeast, hops, Special from CraftBeers where name='" + ComboBoxName.getValue() + "'");
	    		if(ingredients.next()){
		    		ComboBoxYeast.setValue(ingredients.getString("yeast"));
		    		ComboBoxGrains.setValue(ingredients.getString("grains"));
		    		ComboBoxHops.setValue(ingredients.getString("hops"));
		    		TextAreaSpecial.setText(ingredients.getString("Special"));
	    		}
	    		
    	}catch(SQLException e){
    		e.getMessage();
    	}
    }
    
    
    
    
    
    
    
    
    //Toggle Purity label based on Special Ingredients box
    @FXML
    public void CheckPurity(){
    	if(TextAreaSpecial.getText().equals("")){
    		if(ComboBoxName.getValue() != null){
	    		LabelReinYes.setVisible(true);
	    		LabelReinNo.setVisible(false);
    		}
    	} else{
    		LabelReinYes.setVisible(false);
    		LabelReinNo.setVisible(true);
    	}

    }

    
    
    
    
    
    
    
    
    
    //Delete a recipe from book
    @FXML
    public void DeleteRecipe(){
    	Connection conn = null;
    	Statement stmt = null;
    	
    	//Connect to database and create statement
    	try{
			conn = DriverManager.getConnection("jdbc:derby:db/Beers; create=true");
			stmt = conn.createStatement();
		}catch(SQLException e){
			System.out.printf("DB Error %s: ", e.getMessage());
			e.printStackTrace();
		}
    	
    	//If Pure Beer, Delete from Pure Beers Table
    	if(TextAreaSpecial.getText().equals("")){
	    	try {
				stmt.executeUpdate("DELETE from PureBeers WHERE name='" + ComboBoxName.getValue() + "'");
				System.out.println("Recipe Deleted");
				ClearObservableLists();
				LoadRecipeBook();
				System.out.println("Recipe book re-loaded.");
			} catch (SQLException e) {
				e.getMessage();
			}
    	}
    	
    	//If Craft Beer, Delete from Craft Beers Table
    	if(!TextAreaSpecial.getText().equals("")){
	    	try {
				stmt.executeUpdate("DELETE from CraftBeers WHERE name='" + ComboBoxName.getValue() + "'");
				System.out.println("Recipe Deleted");
				ClearObservableLists();
				LoadRecipeBook();
				System.out.println("Recipe book re-loaded.");
			} catch (SQLException e) {
				e.getMessage();
			}
    	}
    }
    

    
    
    
    
    
    
    
    
    
    //Add Custom Beer to Recipe Book
    @FXML
    public void AddRecipe(){
    	Connection conn = null;
    	Statement stmt = null;
    	
    	//Connect to database and create statement
    	try{
			conn = DriverManager.getConnection("jdbc:derby:db/Beers; create=true");
			stmt = conn.createStatement();
		}catch(SQLException e){
			System.out.printf("DB Error %s: ", e.getMessage());
			e.printStackTrace();
		}
    	
    	//If special ingredient field is empty, add to PureBeerDB
    	if(TextAreaSpecial.getText().equals("")){
    		try {
				//Cast as pure beer
				PureBeer PureCustom = new PureBeer(TextFieldCustomName.getText(), ComboBoxGrains.getValue(), ComboBoxYeast.getValue(), ComboBoxHops.getValue());
				ClearObservableLists();
				
				//Add to PureBeerDB
				PureBeerDB.addPureBeer(conn, PureCustom.getName(), PureCustom.getGrains(), PureCustom.getYeast(), PureCustom.getHops());
				System.out.println("Recipe Added");			
				LoadRecipeBook();
				
				//Clear Text field and move name to drop down
				TextFieldCustomName.setText("");
				ComboBoxName.setValue(PureCustom.getName());

				System.out.println("Recipe book re-loaded.");
			} catch (Exception e) {
				e.getMessage();
			}
    	} else if(!TextAreaSpecial.getText().equals("")){
    		//Cast as Craft Beer
    		CraftBeer CraftCustom = new CraftBeer(TextFieldCustomName.getText(), ComboBoxGrains.getValue(), 
    											 ComboBoxYeast.getValue(), ComboBoxHops.getValue(), TextAreaSpecial.getText());
    		ClearObservableLists();
    		
    		//Add to CraftBeerDB
    		CraftBeerDB.addCraftBeer(conn, CraftCustom.getName(), CraftCustom.getGrains(), CraftCustom.getYeast(), CraftCustom.getHops(), CraftCustom.getSpecial());
    		System.out.println("Recipe Added");			
			LoadRecipeBook();
			
    		//Clear Text field and move name to drop down
			TextFieldCustomName.setText("");
			ComboBoxName.setValue(CraftCustom.getName());

			System.out.println("Recipe book re-loaded.");
    	}
    }
    
    
    
    
    
    //Update Recipe Book
    @FXML
    public void UpdateRecipe(){
    	Connection conn = null;
    	Statement stmt = null;
    	
    	//Connect to database and create statement
    	try{
			conn = DriverManager.getConnection("jdbc:derby:db/Beers; create=true");
			stmt = conn.createStatement();
		}catch(SQLException e){
			System.out.printf("DB Error %s: ", e.getMessage());
			e.printStackTrace();
		}
    	
    	
    	//Update Pure recipe
    	if(TextAreaSpecial.getText().equals("")){
    		try {
    			String tempName = ComboBoxName.getValue();
    			String updateTable = String.format("UPDATE PureBeers SET grains='%s', hops='%s', yeast='%s' WHERE name='" + ComboBoxName.getValue() + "'", ComboBoxGrains.getValue(),
						ComboBoxHops.getValue(), ComboBoxYeast.getValue());
				stmt.executeUpdate(updateTable);
				System.out.println("Recipe Updated.");
				ClearObservableLists();
				TextAreaSpecial.setText("");
				LoadRecipeBook();
				ComboBoxName.setValue(tempName);
			} catch (SQLException e) {
				e.getMessage();
			}
    	} 
    	
    	//Update Craft Recipe
    	else if(!TextAreaSpecial.getText().equals("")){
			try {
				String tempName = ComboBoxName.getValue();
				String updateTable = String.format("UPDATE CraftBeers SET grains='%s', hops='%s', yeast='%s', special='%s' WHERE name='" + ComboBoxName.getValue() + "'", ComboBoxGrains.getValue(),
						ComboBoxHops.getValue(), ComboBoxYeast.getValue(), TextAreaSpecial.getText());
				stmt.executeUpdate(updateTable);
				System.out.println("Recipe Updated.");
				ClearObservableLists();
				TextAreaSpecial.setText("");
				LoadRecipeBook();
				ComboBoxName.setValue(tempName);
			} catch (SQLException e) {
				e.getMessage();
			}
    	}
    }
    
    
    
    
    
    
    //Helper method when updating recipe book
    @FXML
    public void ClearObservableLists(){
    	name.clear();
    	grains.clear();
    	yeast.clear();
    	hops.clear();
    }
}
