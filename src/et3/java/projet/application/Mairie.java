package et3.java.projet.application;

import java.io.File;

import et3.java.projet.data.FileReader;

public class Mairie {
	
	private Arbre[] ListeArbre;
	private AmateurDArbres[] amateurDArbres;

	public void LoadArbre(File F) {
		if(F.length > 0)
		{
			File tempFile = new File(F);
			
			if(tempFile.exists())
			{
				System.out.println("[Main] Reading the file " + F.getName() + " ...");
						
				//We start by reading the CSV file
				
				FileReader.getDataFromCSVFile(F);
				
				System.out.println("[Main] End of the file " + F.getName() + ".");
			}
			else
			{
				System.out.println("[Main] No file " + F.getName());
				
			}
		}
		else
		{
			System.out.println("[Main] You should enter the CSV file path as a parameter.");
		}
	}

	public Mairie() {
		
	}

	public void generererArbreRemarquable(){

	}

	public void operationArbre(){

	}


}
