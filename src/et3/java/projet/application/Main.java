package et3.java.projet.application;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import et3.java.projet.application.*;

import et3.java.projet.data.FileReader;

public class Main 
{

//	Association association = new Association();

	//Affichage sur la console et gestion des entrées/sorties
	public static void GestionAffichage(Mairie mairie, Association association) {
		while(true) {

			StringBuilder sb = new StringBuilder();
			sb.append("Liste des actions possibles : \n");
			sb.append("1.Fin d'exercice budgétaire.\n");
			sb.append("2.Afficher la recette de cotisation d'un membre.\n");
			sb.append("3.Paiement d'une facture reçue.\n");
			sb.append("4.Inscription ou suppression d'un donateur.\n");
			sb.append("5.Réaliser une visite d'un arbre remarquable et afficher son compte rendu.\n");
			sb.append("6.Réaliser un vote d'un membre pour la reconnaissance d'un arbre remarquable.\n");
			sb.append("7.Etre notifier de la plantation ou de l'abattage d'un arbre ou de la classification d'un arbre remarquable.\n");
			sb.append("\nMerci de réaliser votre sélection en indiquant un chiffre :\n");
			System.out.println(sb.toString());
		
			@SuppressWarnings("resource")
			int action = Integer.parseInt(new Scanner(System.in).nextLine());
			if(action == 1){
				System.out.println("1--Fin de l'exercice budgétaire en cours : \n");
				System.out.println("Vérification des cotisations... \n");
				association.CotisationNonRéglé();
				
				
				System.out.println("Génération du rapport d'activité... \n");
				association.genererRapport();
				
				association.ResetRapport();
			}
			if(action == 2){
				System.out.println("2--Affichage des recettes de cotisation d'un membre : \n");
				System.out.println("Merci d'entrer le prénom du membre : ");
				@SuppressWarnings("resource")
				String prenom = new Scanner(System.in).nextLine();
				System.out.println("\nMerci d'entrer le nom du membre : ");

				@SuppressWarnings("resource")
				String nom = new Scanner(System.in).nextLine();
				System.out.println("\n");

				Personne PersonneaRechercher = new Personne(nom, prenom, null, null);

				Membre membreaRechercher = association.ChercheMembre(PersonneaRechercher);
				System.out.println(membreaRechercher.getPrenom());
				if (membreaRechercher != null) {
					ArrayList<Operation> cotisation = membreaRechercher.getCotisations();
					for(int i =0; i <cotisation.size(); i++) {
						System.out.println(cotisation.get(i).getDate() + " - de " + cotisation.get(i).getCrediteur() + " a " + cotisation.get(i).getDebiteur() + " pour " + cotisation.get(i).getMontant() + "€");
					}
				}else {
					System.out.println(prenom + " " + nom + " ne correspond à aucun membre de l'association.\n");
				}
			}
			if(action == 3){
				System.out.println("3--Paiement d'une facture reçue : \n");
				System.out.println("Merci d'indiquer le montant de la facture : ");
				@SuppressWarnings("resource")
				float montant = Float.parseFloat(new Scanner(System.in).nextLine());
				System.out.println("\n");
				if(association.payerFacture(montant)) {
					System.out.println("La facture d'un montant de " + montant + " a bien été créditée.\n");

				}else {
					System.out.println("La facture d'un montant de " + montant + " n'a pas pu être créditée.\n");

				}
			}
			if(action == 4){
				System.out.println("4--Inscription ou suppression d'un donateur : \n");

				System.out.println("Souhaitez vous : \n1-- Inscrire un donateur\n2-- Supprimer un donateur");
				@SuppressWarnings("resource")
				int act = Integer.parseInt(new Scanner(System.in).nextLine());
				if(act == 1) {
					AmateurDArbres NouvelAmateur = new AmateurDArbres();
					association.ajouterDonateur(NouvelAmateur);
					
				}else if(act ==2) {
					System.out.println("Merci de renseigner les champs suivant : id du donateur\n");
					@SuppressWarnings("resource")
					int id = Integer.parseInt(new Scanner(System.in).nextLine());
					AmateurDArbres aSupprimer = new AmateurDArbres(id);
					association.supprimerDonateur(aSupprimer);
				}else {
					System.out.println("Erreur, impossible de trouver l'action demandée.");
				}


			}
			if(action == 5){
				System.out.println("5.Visite d'un arbre remarquable et affichage de son compte rendu :\n");
				association.genererVisites(mairie.getListeArbre());
			}
			if(action == 6){
				System.out.println("6--Vote d'un membre pour la reconnaissance d'un arbre remarquable :\n");
				association.genererVotes(mairie.getListeArbre());
			}
			if(action == 7){
				System.out.println("7.Notification de la plantation ou de l'abattage d'un arbre ou de la classification d'un arbre remarquable :\n");
				
			}

			
		}
	}
	
	public static void main(String[] args)
	{
		Mairie mairie = new Mairie();
		Association association = new Association("Save Tree");		
		File f = new File("src/et3/java/projet/data/data.csv");
		//Creation d'une liste de membre
		Date date = new Date();
		Personne p1 = new Personne("Cerise", "Albert", date,"3 rue du Lila, 34425");
		Personne p2 = new Personne("Pomme", "Jean", date,"34 avenue d'orient, 94024");
		Personne p3 = new Personne("Raisin","Louis", date,"8 impasse du Montmartre, 57294");
				
		association.inscription(p1);
		association.inscription(p2);
		association.inscription(p3);
		System.out.println(f.getAbsolutePath());
		mairie.chargerArbres(f);
		GestionAffichage(mairie, association);
		
	}//PAS ID
	
}