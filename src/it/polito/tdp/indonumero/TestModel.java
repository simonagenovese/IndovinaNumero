package it.polito.tdp.indonumero;

public class TestModel {

	public static void main(String[] args) {
		// questa classe gioca una partita intera con il modell
		
		Model model = new Model();
		
		// CONSOLE SENZA INTERAZIONE UTENTE CHE PROVI A GIOCARE UNA PARTITA
		
		model.newGame();
/*
		while(model.isInGame()) {
			int ris = model.tentativo(77); // finchè sono nel gioco
			// dico che il numero è  e poi perdo
			System.out.println(ris);
		}
		*/
		int min = 1;
		int max = model.getNMAX();
		while(model.isInGame()) {
			int t = (min+max)/2;
			System.out.format("Tra %d e %d provo %d\n", min, max, t);
			int ris = model.tentativo(t);
			if(ris>0) {
				// tentativo grande, riduco il max
				max = t-1;
			} else
				min = t+1;
			// in funzione del risultato aggiorno i limiti
			System.out.println(ris);
		}
	}

}
