package cis232.semesterproject.brewer;

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
    private ComboBox<?> ComboBoxHops;

    @FXML
    private TextField TextBoxCo2;

    @FXML
    private Button ButtonRein;

    @FXML
    private Button ButtonSubmit;

    @FXML
    private ComboBox<?> ComboBoxGrains;

    @FXML
    private ChoiceBox<?> ChoiceBoxStyle;

    @FXML
    private TextField TextBoxTarget;

    @FXML
    private ComboBox<?> ComboBoxYeast;

    @FXML
    private ComboBox<?> ComboBoxName;

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
}
