package et3.java.projet.application;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


import et3.java.projet.data.FileReader;

public class Mairie {
	
	private ArrayList<Arbre> ListeArbre;
	private ArrayList<AmateurDArbres> amateurDArbres;
	private Date date;
	
	public void ajouterArbre(Arbre arbre) {
		if(!ListeArbre.contains(arbre)) {
			for (AmateurDArbres notifies : amateurDArbres) {
				notifies.getNotification(new OperationArbre(arbre, TypeModif.plantage, date));
			}
			ListeArbre.add(arbre);
		}
	}
	
	public void chargerArbre() {
		
		
		File tempFile = new File("./data/data.csv");
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

	public Mairie(){

	}

	public Mairie(Date date) {
		this.date = date;
	}
	
	public void ajoutDate(Date date){
		this.date = new Date(this.date.getTime() + date.getTime());
	}

	public Date getDate() {
		return date;
	}

	public void suppressionArbre(Arbre arbre){
		if(ListeArbre.contains(arbre)) {
			for (AmateurDArbres notifies : amateurDArbres) {
				notifies.getNotification(new OperationArbre(arbre, TypeModif.decoupage, date));
			}
			ListeArbre.remove(arbre);
		}
	}

	public void ajoutRemarquable(Arbre arbre){
		if(ListeArbre.contains(arbre)) {
			for (AmateurDArbres notifies : amateurDArbres) {
				notifies.getNotification(new OperationArbre(arbre, TypeModif.classificationRemarquable, date));
			}
			arbre.setRemarquable(date);
		}
	}
	public ArrayList<Arbre> getListeArbre(){
		return ListeArbre;
	}
	
}
