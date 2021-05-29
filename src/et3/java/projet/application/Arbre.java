package et3.java.projet.application;

import java.util.Date;


public class Arbre  {
	private String Genre;
	private String Espece;
	private String Nom;
	private int Circonference;
	private int Hauteur;
	private String Developpement;
	private String Adresse;
	private Float[] Coordonnees = new Float[2];
	private boolean Remarquable;
	private Date Date_remarquable;
	private int id;
	
	public Arbre(String Genre, String Espece, String Nom, int Circonference, int Hauteur, String Developpement, String Adresse, Float[] Coordonnees, boolean Remarquable, Date Date_remarquable, int id) {
		this.Genre = Genre;
		this.Espece = Espece;
		this.Nom = Nom;
		this.Circonference = Circonference;
		this.Hauteur = Hauteur;
		this.Developpement = Developpement;
		this.Adresse = Adresse;
		this.Coordonnees = Coordonnees;
		this.Remarquable = Remarquable;
		this.Date_remarquable = Date_remarquable;
		this.id = id;
		

	}

}
