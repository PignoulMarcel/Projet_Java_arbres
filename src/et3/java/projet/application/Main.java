package et3.java.projet.application;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import et3.java.projet.application.*;

import et3.java.projet.data.FileReader;

public class Main 
{
	private Mairie mairie;

	Association association = new Association();
	
	//Affichage sur la console et gestion des entr�es/sorties
	public static void GestionAffichage() {
		while(true) {

			StringBuilder sb = new StringBuilder();
			sb.append("Liste des actions possibles : \n");
			sb.append("1.Fin d'exercice budg�taire.\n");
			sb.append("2.Afficher la recette de cotisation d'un membre.\n");
			sb.append("3.Paiement d'une facture re�ue.\n");
			sb.append("4.Inscription ou suppression d'un donateur.\n");
			sb.append("5.R�aliser une visite d'un arbre remarquable et afficher son compte rendu.\n");
			sb.append("6.R�aliser un vote d'un membre pour la reconnaissance d'un arbre remarquable.\n");
			sb.append("7.Etre notifier de la plantation ou de l'abattage d'un arbre ou de la classification d'un arbre remarquable.\n");
			sb.append("\nMerci de r�aliser votre s�lection en indiquant un chiffre :");
			System.out.println(sb.toString());
		
			@SuppressWarnings("resource")
			int action = Integer.parseInt(new Scanner(System.in).nextLine());
			if(action == 1){
				System.out.println("1--Fin de l'exercice budg�taire en cours : \n");
				System.out.println("V�rification des cotisations... \n");
				association.CotisationNonR�gl�();
				
				
				System.out.println("G�n�ration du rapport d'activit�... \n");
				
				
				
			}
			if(action == 2){
				System.out.println("2--Affichage des recettes de cotisation d'un membre : ");
				Membre membre = new Membre();//temp
				membre.afficherRecettes;
			}
			if(action == 3){
				System.out.println("3--Paiement d'une facture re�ue : ");
				Association association = new Association();
				association.payer(membre);
			}
			if(action == 4){
				System.out.println("4--Inscription ou suppression d'un donateur : ");
				association.desinscription();

			}
			if(action == 5){
				System.out.println("5.Visite d'un arbre remarquable et affichage de son compte rendu : ");
				membre.visite();
			}
			if(action == 6){
				System.out.println("6--Vote d'un membre pour la reconnaissance d'un arbre remarquable : ");
				membre.vote();
			}
			if(action == 7){
				System.out.println("7.Notification de la plantation ou de l'abattage d'un arbre ou de la classification d'un arbre remarquable : ");
				mairie.operationArbre(abattage);
			}

			
		}
	}
	
	public static void main(String[] args) 
	{
		//Charge les arbres
		this.mairie.ChargerArbre();			//COMPREND PAS PK IL ME DIT DE METTRE UN 'STATIC'
		GestionAffichage();
		
	}
	
}
