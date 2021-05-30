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
	
	public void ajouterArbre(Arbre arbre) {
		if(ListeArbre.size()==0) {
			ListeArbre.add(arbre);
		}else {
			if(!ListeArbre.contains(arbre)) {//impossible car la date ne peut pas être identique, il faut un id d'arbre
				for (AmateurDArbres notifies : amateurDArbres) {
					notifies.getNotification(new OperationArbre(arbre, TypeModif.plantage, new Date()));
				}
				ListeArbre.add(arbre);
			}
		}
	}
	
	public void chargerArbres(File tempFile, Mairie mairie) {
		if(tempFile.exists())
		{
			System.out.println("Lecture du fichier " + tempFile.getName() + " ...");
			
	      	
			//We start by reading the CSV file
			FileReader.getDataFromCSVFile(tempFile.toString(), mairie);
				
			System.out.println("Fin du fichier " + tempFile.getName() + ".");
		}
		else
		{
				System.out.println("Aucun fichier " + tempFile.getName());
			
		}
	}

	public Mairie(){
		ListeArbre = new ArrayList<Arbre>();
		amateurDArbres = new ArrayList<AmateurDArbres>();
	}

	public void suppressionArbre(Arbre arbre){
		if(ListeArbre.contains(arbre)) {
			for (AmateurDArbres notifies : amateurDArbres) {
				notifies.getNotification(new OperationArbre(arbre, TypeModif.decoupage, new Date()));
			}
			ListeArbre.remove(arbre);
		}
	}

	public void ajoutRemarquable(Arbre arbre){
		if(ListeArbre.contains(arbre)) {
			for (AmateurDArbres notifies : amateurDArbres) {
				notifies.getNotification(new OperationArbre(arbre, TypeModif.classificationRemarquable, new Date()));
			}
			arbre.setRemarquable(new Date());
		}
	}
	public ArrayList<Arbre> getListeArbre(){
		return ListeArbre;
	}
	
}
