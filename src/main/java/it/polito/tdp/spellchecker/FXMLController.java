package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Dictionary model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnClearText;

    @FXML
    private Button btnSpellCheck;

    @FXML
    private ComboBox<String> cmbLanguage;

    @FXML
    private Label txtErrori;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private TextArea txtTesto;

    @FXML
    private Label txtTime;

    @FXML
    void doClearText(ActionEvent event) {
    	txtTesto.clear();
    	txtErrori.setText("");
    	txtRisultato.clear();
    	txtTime.setText("");
    	cmbLanguage.setValue(null);
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	
    	long inizio = System.currentTimeMillis();
    	
    	String parola = "src/main/resources/" + cmbLanguage.getValue()+".txt";
    	model.loadDictionary(parola);
    	String s = txtTesto.getText().toLowerCase().replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
    	
    	String [] frase = s.split(" ");
    	List<String> listaParole = new ArrayList<>();
    	for(String st : frase) {
    		listaParole.add(st);
    	}
    	List<RichWord> listaErrori = model.spellCheckText(listaParole);
    	txtErrori.setText("Il testo contiene" + listaErrori.size() + "errori");
    	String output = "";
    	for(RichWord r : listaErrori) {
    		output+=r.toString()+"\n";
    		
    	}
    	
    	txtRisultato.setText(output);
    	
    	long fine = System.currentTimeMillis();
    	
    	txtTime.setText("Tempo di esecuzione: " + Long.toString(fine - inizio) + " ms");
    	
    	
    	
    	

    }

    @FXML
    void initialize() {
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbLanguage != null : "fx:id=\"cmbLanguage\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrori != null : "fx:id=\"txtErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTesto != null : "fx:id=\"txtTesto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTime != null : "fx:id=\"txtTime\" was not injected: check your FXML file 'Scene.fxml'.";
        
        cmbLanguage.getItems().clear();
        cmbLanguage.getItems().add("English");
        cmbLanguage.getItems().add("Italian");


    }

	public void setModel(Dictionary model) {
		this.model = model;		
	}

}
