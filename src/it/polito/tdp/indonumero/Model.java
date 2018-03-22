package it.polito.tdp.indonumero;

import java.security.*;

public class Model {
	
		// QUESTA CLASSE CONTIENE I DATI
		// TRASFERIAMO L ALGORITMO
	//	al modello non deve interessare chi è il controller
	// e chi è la vista
	// solo dati e algoritmi, niente interfaccia con l'utente
	// niente logica con l utente (infatti la classe model non importa nulla di javaFX
		
		// STATO DELLA PARTITA
		private int NMAX = 100;
		private int TMAX = 7; // per indovinare un numero tra 1 e 100 ci vogliono 7 tentativi minimi

		
		// il programma indovinerà un numero segreto (da indovinare)
		private int segreto; // numero da indovinare
		private int tentativi; // tentativi gia fatti
				
		// aggiungo una booleana per capire se c'è una partita in corso oppure no
		private boolean inGame = false; // se la partita non è il corso, il num segreto e il num di tentativi non hanno senso
		

		// IL COSTRUTTORE DEL MODELLO INIZIALIZZA LE VARIABILI ALLO STATO INIZIALE
		public Model() {
			this.inGame=false;
		}
		
		// quali sono le azioni che posso fare?
		// 1. iniziare una partita nuova
		// 2. fare un tentativo
		// 3. eventualmente abbandona
		
		// 1
		/**
		 * Avvia una nuova partita, generando un nuovo numero segreto.
		 */
		public void newGame() {
			
		   	this.segreto = (int) (Math.random()*NMAX)+1;
	    	this.tentativi = 0;
	    	this.inGame = true;
		}
		
		// 2
		/**
		 * Fai un tentativo di indovinare il numero segreto
		 * @param t = valore numerico del tentativo
		 * @return 0 se è indovinato, +1 se è troppo grande, -1 se è troppo piccolo
		 */
		
		public int tentativo(int t) {
			// t è il valore tentato dall'utente
			// !!! non possiamo fare un tentativo con valori fuori range
			// e cosa capita se il numero di tentativi possibili è gia esaurito?
			// lo gestisco con delle ECCEZIONI!!!
			
			// il tentativo non si può fare se non sono in partita
			if(!inGame) throw new IllegalStateException("Partita non attiva!");
			// regole di validità di un dato !!!--> faccio un metodo
			//if(t<1 || t>this.NMAX) throw new InvalidParameterException("Tentativo fuori range!");
			if(!valoreValido(t)) throw new InvalidParameterException("Tentativo fuori range!");
			
			this.tentativi++;
			// qui mi accorgo se arriva al num di tentativi massimi --> partita finite
			if(this.tentativi==this.TMAX) {
				this.inGame=false; // gioco finito
			}
			
			if(t==this.segreto) {
				this.inGame = false; // gioco finito
				return 0; // qui l'utente indovina --> partita finita
			}
			if(t<this.segreto)
				return -1;
			return +1;
		}

		// definisco un metodo valido
		// delego a questa funzione la verifica
		/**
		 * Controlla se il tentativo fornito rispetta le regole formale
		 * del gioco, cioè è nel range [1, NMAX]
		 * @param tentativo
		 * @return {@code true} se il tentativo è valido
		 */
		public boolean valoreValido(int tentativo) {
			return tentativo >=1 && tentativo<=this.NMAX;
		}
		
		
		// rendo visibili al controllor i dati

		public boolean isInGame() {
			return inGame;
		}
		
		public int getTentativi() {
			return this.tentativi;
		}

		public int getNMAX() {
			return NMAX;
		}

		public int getTMAX() {
			return TMAX;
		}

		public int getSegreto() {
			return segreto;
		}

}
