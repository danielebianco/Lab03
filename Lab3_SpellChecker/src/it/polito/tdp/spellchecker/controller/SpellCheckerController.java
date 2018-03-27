/**
 * Sample Skeleton for 'SpellChecker.fxml' Controller Class
 */

package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class SpellCheckerController {
	
	Dictionary model;
	List<String> parole = new LinkedList<String>();
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="language"
    private ChoiceBox<String> language; // Value injected by FXMLLoader

    @FXML // fx:id="txtCheck"
    private TextArea txtCheck; // Value injected by FXMLLoader

    @FXML // fx:id="btnSpellCheck"
    private Button btnSpellCheck; // Value injected by FXMLLoader

    @FXML // fx:id="txtCanc"
    private TextArea txtCanc; // Value injected by FXMLLoader

    @FXML // fx:id="redText"
    private Text redText; // Value injected by FXMLLoader

    @FXML // fx:id="btnClearText"
    private Button btnClearText; // Value injected by FXMLLoader

    @FXML // fx:id="blackText"
    private Text blackText; // Value injected by FXMLLoader

    @FXML
    void doClearText(ActionEvent event) {
    	txtCheck.clear();
    	txtCanc.clear();
    	model.getWerr().clear();
    	redText.setText("");
    	blackText.setText("");
    }
        
    @FXML
    void doSpellCheck(ActionEvent event) {
    	
    	if(language.getValue().compareTo("Italian")==0)
    		model.setDizionarioCorrente(model.getDizionarioItaliano());
    	else
    		model.setDizionarioCorrente(model.getDizionarioInglese());
    	
    	String parola = txtCheck.getText().toLowerCase().replaceAll("[.,\\/#!?$%\\^&\\*;:{}=\\-_`~()\\[\\]]", "" );
    	
    	if(parola.length()==0) {
    		txtCanc.appendText("Devi inserire un testo!\n");
    		return;
    	}
    	
    	String[] parolaDaControllare = parola.split(" ");
    	for(int i=0; i<parolaDaControllare.length; i++) {
    		parole.add(parolaDaControllare[i]);
    	}
    	long l1 = System.nanoTime();
    	model.spellCheckTextLinear(parole);
    	long l2 = System.nanoTime();
    	txtCanc.setText(model.stampaString(model.getWerr()));
    	redText.setText("The text contains " + model.getErrori() + " errors");
    	blackText.setText("Spell check completed in " + (l2-l1)/1E9 + " seconds");
    	model.resetList(parole);
    	model.setErrori(0);
    	}

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert language != null : "fx:id=\"language\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtCheck != null : "fx:id=\"txtCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtCanc != null : "fx:id=\"txtCanc\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert redText != null : "fx:id=\"redText\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert blackText != null : "fx:id=\"blackText\" was not injected: check your FXML file 'SpellChecker.fxml'.";

    }
    
    public void setModel(Dictionary model) {
    	this.model = model;
    	this.language.getItems().add("English");
    	this.language.getItems().add("Italian");
    	this.model.loadAll();
    }
}

    
    
    
