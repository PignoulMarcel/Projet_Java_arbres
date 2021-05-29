package et3.java.projet.application;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import et3.java.projet.application.Mairie;

import et3.java.projet.data.FileReader;

public class Main 
{
	Mairie mairie = new Mairie();

	
	//Affichage sur la console et gestion des entrées/sorties
	public static void GestionAffichage() {
		while(true) {

			StringBuilder sb = new StringBuilder();
			sb.append("Liste des actions possibles : \n 1.Fin d'exercice budgétaire.\n 2.Afficher la recette de cotisation d'un membre.");
			sb.append("3.Paiement d'une facture reçue.\n 4.Inscription ou suppression d'un donateur.\n 5.Réaliser une visite d'un arbre remarquable et afficher son compte rendu.\n");
			sb.append(" 6.Réaliser un vote d'un membre pour la reconnaissance d'un arbre remarquable.\n 7.Etre notifier de la plantation ou de l'abattage d'un arbre ou de la classification d'un arbre remarquable.\n");
			sb.append("\nMerci de réaliser votre sélection en indiquant un chiffre :");
			System.out.println(sb.toString());
		
			@SuppressWarnings("resource")
			int action = Integer.parseInt(new Scanner(System.in).nextLine());
			System.out.println(action);
			
		}
	}
	
	public static void main(String[] args) 
	{		
		//Charge les arbres
	//	mairie.chargerArbre();
		GestionAffichage();
		
	}
	
}
