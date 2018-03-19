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
	
	
	// STATO DELLA PARTITA
	// aggiungo due costanti
	private int NMAX = 100;
	private int TMAX = 7; // per indovinare un numero tra 1 e 100 ci vogliono 7 tentativi minimi
	// in 10 tentativi trovo un numero tra 1 e 1000 (dimezzando ogni volta)
	// a ogni tentativo buttiamo via metà dei numeri
	
	// il programma indovinerà un numero segreto (da indovinare)
	private int segreto; // numero da indovinare
	private int tentativi; // tentativi gia fatti
	
	// conoscendo queste variabili so a che punto è il gioco
	
	// aggiungo una booleana per capire se c'è una partita in corso oppure no
	private boolean inGame = false; // se la partita non è il corso, il num segreto e il num di tentativi non hanno senso
	
	// parto quindi con una partita non iniziata, 
	// quindi devo permettere all'utente di iniziare una nuova partita
	// --> implemento il metodo handleNuova
	

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
    	// viene chiamato quando l'utente clicca su Nuova Partita
    	// questo metodo deve INVENTARE il numero casuale (il numero segreto)
    	// ogni nuova partita ci sarà un numero diverso
    	
    	// uso Math.random()
    	// genera un numero casuale, ritorna un double positivo maggiore o uguale a zero
    	// e minore strettamente di uno 0<x<1
    	// a me serve un numero tra 1 e NMAX --> moltiplico per NMAX
    	// e poichè il numero segreto è un intero, faccio un cast
    	// avrò un numero tra 0 e 99, quindi aggiungo 1
    	this.segreto = (int) (Math.random()*NMAX)+1;
    	
    	// ADESSO INIZIALIZZO LO STATO DELLA PARTITA
    	// metto i tentativi gia fatti e inizio la partita
    	this.tentativi = 0;
    	this.inGame = true;
    	// a questo punto la partita è iniziata
    	// l'utente queste cose non le vede, a questo punto dovrò disattivare
    	// l'opzione di inizio partita e attivare il box del gioco
    	
    	// disabilito il bottone Nuova
    	btnNuova.setDisable(true); // setto il disable quindi va a true (doppia negazione)
    	
    	//abilito la box di gioco
    	boxGioco.setDisable(false);
    	
    	// metto 0 tentativi fatti su 7 nelle caselle di testo
    	// ma sono entrambi numeri interi quindi li metto a stringa con il String format
    	txtCurrent.setText(String.format("%d", this.tentativi));
    	txtMax.setText(String.format("%d", this.TMAX));
    	
    	// pulisco l'area di testo e il text field ad ogni nuova partita
    	txtLog.clear();
    	txtTentativo.clear();
    	
    	// dico anche all utente l intervallo del numero segreto
    	txtLog.setText(String.format("Indovina un numero tra %d e %d\n", 1, NMAX));
    }

    @FXML
    void handleProva(ActionEvent event) {
    	// ci sono tante cose da controllare
    	// se l'utente ha inserito un valore sbagliato e non ha indovinato
    	// se l'utente ha inserito il valore esatto e quindi vince la partita
    	// se ha inserito un valore sbagliato e devo dire se è basso o alto
    	/// se è l ultimo tentativo e non ha indovinato ha perso
    	
    	// estraggo il numero che l'utente ha scritto
    	// estraggo LA STRINGA, verificare che ci sia qualcosa dentro
    	// e converto in numero
    	
    	String numS = txtTentativo.getText(); // ciò che l'utente ha scritto nella casella di testa
    	
    	// verifico se ha scritto qualcosa
    	if(numS.length()==0) { // non mi va bene
    		txtLog.appendText("Devi inserire un numero\n");
    		return; // ESCO DAL GESTORE DI EVENTO
    		// tutto ciò che dovevo fare per gestire questa versione del bottone prova l'ho fatta
    	} 
    	
    	// se ha inserito qualcosa devo convertire la stringa in numero
    	// o il parseInt funziona, trova il numero e mi restituisce l'intero
    	// oppure trova una lettera e genera l'eccezione numberFormatException
    	// inserisco un try catch
    	try {
	    	// se entro qui il numero era effettivamente un intero
	    	int num = Integer.parseInt(numS);
	    	
	    	// controllo se il numero è fuori il range
	    	if(num<1 || num>NMAX) {
	    		txtLog.appendText("Valore fuori dall'intervallo consentito.\n");
	    		return; // esco dal metodo (prima di aver incrementato i tentativi)
	    		// non lo conto come tentativo ma è un errore di immissione
	    	}
	    	
	    	// caso 1: numero uguale al numero segreto
	    	if(num==this.segreto) {
	    		// ha indovinato
	    		// gli diciamo che ha indovinato e terminiamo la partita
	    		txtLog.appendText("Hai vinto!\n");
	    		
	    		// chiudo la partita
	    		// disabilito l'area di gioco e abilitare la nuova partita
	    		boxGioco.setDisable(true);
	    		btnNuova.setDisable(false); // non è disabilitato quindi è abilitato
	    		this.inGame = false; // non in gioco
	    		
	    	} else {
	    		// tentativo errato
	    		this.tentativi++; // hai sprecato un tentativo
	    		txtCurrent.setText(String.format("%d", this.tentativi)); // aggiorno l interfaccia
	    		
	    		// se non ha indovinato, il numero può essere troppo basso o troppo olto
	    		// oppure era l ultimo tentativo ed ha perso
	    		
	    		// caso 2: se è l'ultimo tentativo
	    		if(this.tentativi==this.TMAX) {
	    			//hai perso --> ti dico qual è il numero da indovinare e termino la partita
	    			txtLog.appendText(String.format("Hai perso! Il numero era %d\n",
	    					this.segreto));
	    			
	    			// chiudo la partita
	    			boxGioco.setDisable(true);
		    		btnNuova.setDisable(false); 
		    		this.inGame = false;
		    		
	    		} else {
	    			// sono ancora in gioco
	    			// il numero è troppo alto o troppo basso?
	    			// caso 3:
	    			if(num<segreto) {
	    				// troppo basso
	    				txtLog.appendText("Troppo basso\n");
	    			} else { // caso 4:
	    				// troppo alto
	    				txtLog.appendText("Troppo alto\n");
	    			}
	    		}
	    		
	    		
	    	}
	    	
	    	
    	} catch(NumberFormatException ex) {
    		// se entro qui non va bene
    		txtLog.appendText("Il dato inserito non è numerico\n");
    		return;
    		// creo un percorso a ostacoli, se sbagli qualcosa sei fuori
    		
    		// in ogni caso di errore, l'utente NON PERDE IL NUMERO DI TENTATIVI
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
}
