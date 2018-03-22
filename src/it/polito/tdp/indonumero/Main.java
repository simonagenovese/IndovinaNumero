package it.polito.tdp.indonumero;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			

			// CREO L'OGGETTO passandogli nel costruttore dove si trova (l'indirizzo)
			FXMLLoader loader = new FXMLLoader(getClass().getResource("IndoNumero.fxml"));
			// per ottenere il nodo radice chiamo il metodo load sull oggeto loader appena creato
			BorderPane root = (BorderPane)loader.load(); // chiedo alla classe loader di leggere l'FXML
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			// il main dovrà creare un istanza del mondo
			// da passare poi al controller
			Model model = new Model();
			
			// devo collegarlo al controller
			// ma l'oggetto controller non l ho creato io main
			// e allora chi è?
			// ME LO DEVO FAR DIRE DA CHI L'HA CREATO ---> FXML loader
			// solo che la chiamata usa un metodo statico che mi restituisce il nome radice
			// per potermi fare restituire le cose devo usare fxmlloader come oggetto
			// e non come metodo statico che ha un metodo get controller
			
			// visto che il controller l'hai creato tu loader
			// dimmi chi è il tuo controller
			((IndoNumeroController)loader.getController()).setModel(model);
			// gli devo dire esplicitamente di che tipo è il controller
			// perchè la classe loader non sa come ho chiamato io il controller
			// quindi faccio un cast della classe vera per fare il set model
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
