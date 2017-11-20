/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nadjos;

/**
 *
 * @author NADJIDOUMBAYE
 */

/*cc
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RELEX W8
 *
 *  La classe Employe
 * **********************************************************************/
abstract class Employe {
	private String nom;
	private String prenom;
	private int age;
	private String date;
	public String matricule;
	public int SalaireBase=110000;
	public Employe(String prenom, String nom, int age, String date,String matricule) {
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.date = date;
		this.matricule =matricule;
	}

	public abstract double calculerSalaire();

	public String getTitre()
		{
			return "L'employé" ;
		}
	
	public String getNom() {
		return getTitre() + prenom + " " + nom;
	}
}

/* **********************************************************************
 *  La classe Commercial (regroupe Vendeur et Représentant)
 * **********************************************************************/
abstract class Commercial extends Employe {
	final double chiffreAffaire;

	public Commercial(String prenom, String nom, int age, String date,String matricule,double chiffreAffaire) {
		super(prenom, nom, age, date, matricule);
		this.chiffreAffaire = chiffreAffaire;
	}

	public double getChiffreAffaire()
		{
			return chiffreAffaire;
		}
	
}

/* **********************************************************************
 *  La classe Vendeur
 * **********************************************************************/
class Vendeur extends Commercial {
	private final static double POURCENT_VENDEUR = 0.2;
	private final static int BONUS_VENDEUR = 100;
	public Vendeur(String prenom, String nom, int age, String date,String matricule,double chiffreAffaire) {
		super(prenom, nom, age, date,matricule,chiffreAffaire);
	}

	public double calculerSalaire() {
		return (POURCENT_VENDEUR * getChiffreAffaire()) + BONUS_VENDEUR +SalaireBase;
	}

	public String getTitre()
		{
			return "Le vendeur ";
		}

}

/* **********************************************************************
 *  La classe Représentant
 * **********************************************************************/
class Representant extends Commercial {
    private final static double POURCENT_REPRESENTANT = 0.2;
	private final static int BONUS_REPRESENTANT = 200;
	public Representant(String prenom, String nom, int age, String date, String matricule, double chiffreAffaire) {
		super(prenom, nom, age , date, matricule, chiffreAffaire );
	}

	public double calculerSalaire() {
		return (POURCENT_REPRESENTANT * getChiffreAffaire()) + BONUS_REPRESENTANT + SalaireBase ;
	}

	public String getTitre()
		{
			return "Le représentant ";
		}
}

/* **********************************************************************
 *  La classe Technicien (Production)
 * **********************************************************************/
class Technicien extends Employe {
	private final static double FACTEUR_UNITE = 5.0;
	

	private int unites;

	public Technicien(String prenom, String nom, int age, String date, String matricule, int unites) {
		super(prenom, nom, age, date,matricule);
		this.unites = unites;
	}

	public double calculerSalaire() {
		return FACTEUR_UNITE * unites + SalaireBase;
	}

	public String getTitre()
		{
			return "Le technicien ";
		}
}

/* **********************************************************************
 *  La classe Manutentionnaire
 * **********************************************************************/
class Manutentionnaire extends Employe {
	private final static double SALAIRE_HORAIRE = 65.0;
	private int heures;

	public Manutentionnaire(String prenom, String nom, int age, String date,String matricule, int heures) {
		super(prenom, nom, age, date,matricule);
		this.heures = heures;
	}

	public double calculerSalaire() {
		return SALAIRE_HORAIRE * heures + SalaireBase;
	}

	public String getTitre()
		{
			return "Le manut. " ;
		}
}

/* **********************************************************************
 *  L'interface d'employes Ã  risque
 * **********************************************************************/
interface ARisque {
	int PRIME = 25000;
}

/* **********************************************************************
 *  Une première sous-classe d'employés Ã  risque
 * **********************************************************************/
class TechnARisque extends Technicien implements ARisque {

	public TechnARisque(String prenom, String nom, int age, String date, String matricule, int unites) {
		super(prenom, nom, age, date, matricule, unites);
	}

	public double calculerSalaire() {
		return super.calculerSalaire() + PRIME;
	}
}

/* **********************************************************************
 *  Une autre sous-classe d'employés Ã  risque
 * **********************************************************************/
class ManutARisque extends Manutentionnaire implements ARisque {
	
	public ManutARisque(String prenom, String nom, int age, String date, String matricule, int heures) {
		super(prenom, nom, age, date, matricule, heures);
	}

	public double calculerSalaire() {
		return super.calculerSalaire() + PRIME;
	}
}

/* **********************************************************************
 *  La classe Personnel
 * **********************************************************************/
class Personnel {
	private Employe[] staff;
	private int nbreEmploye;
	private final static int MAXEMPLOYE = 400;

	public Personnel() {
		staff = new Employe[MAXEMPLOYE];
		nbreEmploye = 0;
	}

	public void ajouterEmploye(Employe e) {
		++nbreEmploye;
		if (nbreEmploye <= MAXEMPLOYE) {
			staff[nbreEmploye - 1] = e;
		} else {
			System.out.println("Pas plus de " + MAXEMPLOYE + " employés");
		}
	}

	public double salaireMoyen() {
		double somme = 0.0;
		for (int i = 0; i < nbreEmploye; i++) {
			somme += staff[i].calculerSalaire();
		}
		return somme / nbreEmploye;
	}

	public void afficherSalaires() {
		for (int i = 0; i < nbreEmploye; i++) {
			System.out.println(staff[i].getNom() + " gagne "
					+ staff[i].calculerSalaire() + " francs.");
		}
	}
}

// ======================================================================

class Salaires {
    
	public static void main(String[] args) {
		Personnel p = new Personnel();
		p.ajouterEmploye(new Vendeur("nadjos", "Grindel", 45, "1995", "12A098FS", 30000));		
		p.ajouterEmploye(new Representant("loic", "Chiba", 25,"15B037FS", "2000" , 20000));
		p.ajouterEmploye(new Technicien("joakim", "lepetit", 28, "1998", "17B123FS", 1000));
		p.ajouterEmploye(new Manutentionnaire("lionel", "labelle", 32, "1998","13B432FS", 45));
		p.ajouterEmploye(new TechnARisque("richard", "Bichi", 28, "2000","16B001FS", 1000));
		p.ajouterEmploye(new ManutARisque("hadassa", "pater", 30, "2001", "15A567FS", 45));

		p.afficherSalaires();
		System.out.println("Le salaire moyen dans l'entreprise est de "
				+ p.salaireMoyen() + " francs.");

	}

}

