/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.Richword;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	Dictionary model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnclear;

    @FXML
    private Button btnspell;

    @FXML
    private ComboBox<String> cmblanguage;

    @FXML
    private Label lbltime;

    @FXML
    private Label lblwrong;

    @FXML
    private TextArea txttext;

    @FXML
    private TextArea txtwrong;

    @FXML
    void doClearText(ActionEvent event) {
    	txttext.clear();
    	txtwrong.clear();
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	long a=System.nanoTime();
    	String language=cmblanguage.getValue();
    	String text=txttext.getText().toLowerCase();
    	text.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_'~()\\[\\]\"]","");
    	if(language.compareTo("")==0 || text.compareTo("")==0) {
    		System.out.println("Inserire lingua e/o testo");
    		return;
    	}
    	List<String> InputText = new ArrayList<String>();
    	String Array[]=text.split(" ");
    	for(int i=0;i<Array.length;i++) {
    		InputText.add(Array[i]);
    	}
    	List<Richword> rich= model.spellCheckText(InputText, language);
    	int c=0;
    	for(Richword r: rich) {
    		if(!r.isCorrect()) {
    			txtwrong.appendText(r.getWord() + "\n");
    			c++;
    		}
    	}
    	lblwrong.setText("The text contains "+ c + " errors");
    	long b=System.nanoTime();
    	lbltime.setText("Spell check completed in " + (b-a)*0.000000001 + " seconds");
    }

    @FXML
    void initialize() {
        assert btnclear != null : "fx:id=\"btnclear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnspell != null : "fx:id=\"btnspell\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmblanguage != null : "fx:id=\"cmblanguage\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lbltime != null : "fx:id=\"lbltime\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblwrong != null : "fx:id=\"lblwrong\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txttext != null : "fx:id=\"txttext\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtwrong != null : "fx:id=\"txtwrong\" was not injected: check your FXML file 'Scene.fxml'.";
        cmblanguage.getItems().clear();
        cmblanguage.getItems().add("italian");
        cmblanguage.getItems().add("english");
    }


	public void setModel(Dictionary model) {
		this.model=model;
		model.loadDictionary("italian");
		model.loadDictionary("english");
	}
}


