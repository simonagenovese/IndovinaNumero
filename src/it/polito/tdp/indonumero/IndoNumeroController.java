package it.polito.tdp.indonumero;

/**
 * Sample Skeleton for 'IndoNumero.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class IndoNumeroController {


	private Model model; // variabile che CONTIENE il riferimento al modello
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnNuova"
    private Button btnNuova; // Value injected by FXMLLoader

    @FXML // fx:id="txtCurrent"
    private TextField txtCurrent; // Value injected by FXMLLoader

    @FXML // fx:id="txtMax"
    private TextField txtMax; // Value injected by FXMLLoader

    @FXML // fx:id="boxGioco"
    private HBox boxGioco; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativo"
    private TextField txtTentativo; // Value injected by FXMLLoader

    @FXML // fx:id="txtLog"
    private TextArea txtLog; // Value injected by FXMLLoader

    @FXML
    void handleNuova(ActionEvent event) {
    	
    	model.newGame();
    	btnNuova.setDisable(true); 
       	boxGioco.setDisable(false);
    	
    	txtCurrent.setText(String.format("%d", model.getTentativi()));
    	txtMax.setText(String.format("%d", model.getTMAX()));
    	
    	txtLog.clear();
    	txtTentativo.clear();
    	
    	txtLog.setText(String.format("Indovina un numero tra %d e %d\n", 1, model.getNMAX()));
    }

    @FXML
    void handleProva(ActionEvent event) {
    
    	String numS = txtTentativo.getText(); 
    	// FARE IN MODO CHE IL MODELLO LAVORI SEMPRE CON OGGETTI E NON CON MESSAGGI!!    	
    	
    	// verifico se ha scritto qualcosa
    	if(numS.length()==0) { // non mi va bene
    		txtLog.appendText("Devi inserire un numero\n");
    		return; // ESCO DAL GESTORE DI EVENTO
    	} 
  
    	try {
	    	// se entro qui il numero era effettivamente un intero
	    	int num = Integer.parseInt(numS);
	    	
	    	if(!model.valoreValido(num)) {
	    		txtLog.appendText("Valore fuori dall'intervallo consentito.\n");
	    		return; 
	    	}
	   
	    	// A QUESTO PUNTO IL CONTROLLER HA IL VALORE, SA CHE È VALIDO
	    	// E QUINDI PROVA A FARE IL TENTATIVO
	    	int risultato = model.tentativo(num); 
	    	// aggiorno il numero di tentativi
	    	txtCurrent.setText(String.format("%d", model.getTentativi()));

	    	if(risultato == 0) { 
	    		txtLog.appendText("Hai vinto!\n");
	    	} else if(risultato<0) {
	    		txtLog.appendText("Troppo basso\n");
	    	} else {
	    		txtLog.appendText("Troppo alto\n");
	    	}
	    	// IL CONTROLLER NON CONOSCE LE REGOLE DEL GIOCO
	    	
	    	if(!model.isInGame()) {
	    		if(risultato != 0) {
		    		txtLog.appendText("Hai perso!\n");
		    		txtLog.appendText(
		    				String.format("Il numero segreto era: %d\n", 
		    						model.getSegreto()));

	    		}
	    		
	    		// chiudo la partita
	    		btnNuova.setDisable(false); 
	    		boxGioco.setDisable(true);
	    	}
	    	
    	} catch(NumberFormatException ex) {
    		txtLog.appendText("Il dato inserito non è numerico\n");
    		return;
    	}
    	
    	
    	
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnNuova != null : "fx:id=\"btnNuova\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtCurrent != null : "fx:id=\"txtCurrent\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtMax != null : "fx:id=\"txtMax\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert boxGioco != null : "fx:id=\"boxGioco\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtLog != null : "fx:id=\"txtLog\" was not injected: check your FXML file 'IndoNumero.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model; // salvo in model il riferimento che qualcun altro ha creato
		// L HA CREATO IL MAIN!!!!
	}
	
}
