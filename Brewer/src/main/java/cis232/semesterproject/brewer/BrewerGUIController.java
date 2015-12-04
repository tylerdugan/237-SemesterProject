package cis232.semesterproject.brewer;

import java.sql.Connection;
import java.sql.DriverManager;
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
    private ChoiceBox<String> ChoiceBoxStyle;

    @FXML
    private TextField TextBoxTarget;

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
    		
    		
    		
    		//Load database values to Observable List
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
			System.out.println("Connection Initialized");
			stmt = conn.createStatement();
		}catch(SQLException e){
			System.out.printf("DB Error %s: ", e.getMessage());
			e.printStackTrace();
		}
    	
    	//Select beers from database
    	try{
    		ResultSet ingredients = stmt.executeQuery("select grains, yeast, hops from PureBeers where name='" + ComboBoxName.getValue() + "'");
    		if(ingredients.next()){
	    		ComboBoxYeast.setValue(ingredients.getString("yeast"));
	    		ComboBoxGrains.setValue(ingredients.getString("grains"));
	    		ComboBoxHops.setValue(ingredients.getString("hops"));	
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
}
