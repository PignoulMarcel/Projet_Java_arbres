package et3.java.projet.application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import et3.java.projet.data.FileReader;

public class Mairie {
	
	private List<Arbre> ListeArbre = new ArrayList<Arbre>();
	private List<AmateurDArbres> amateurDArbres = new ArrayList<AmateurDArbres>();

	
	public void AjouterArbre(Arbre arbre) {
		ListeArbre.add(arbre);
	}
	
	public void ChargerArbre() {
		
		
		File tempFile = new File("date.csv");
		if(tempFile.exists())
		{
			System.out.println("Lecture du fichier " + tempFile.getName() + " ...");
						
			//We start by reading the CSV file
			FileReader.getDataFromCSVFile(tempFile.toString());
				
			System.out.println("Fin du fichier " + tempFile.getName() + ".");
		}
		else
		{
				System.out.println("Aucun fichier " + tempFile.getName());
			
		}
}
		
	

	public Mairie() {
		
	}

	public void generererArbreRemarquable(Arbre arbre){

	}

	public void operationArbre(){

	}


}
