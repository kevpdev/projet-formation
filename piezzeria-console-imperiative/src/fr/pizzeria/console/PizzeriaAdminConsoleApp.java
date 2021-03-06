package fr.pizzeria.console;

import java.util.Locale;
import java.util.Scanner;

public class PizzeriaAdminConsoleApp {



	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[][] pizzas = {{"PEP" ,"P�p�roni" ,"(12.50�)"},
				{"MAR" ,"Margherita" ,"(14.00�)"},
				{"REI" ,"Reine" ,"(11.50�)"},
				{"FRO" ,"La 4 fromages" ,"(12.00�)"},
				{"CAN" ,"La cannibale" ,"(12.50�)"},
				{"SAV" ,"La Savoyage" ,"(13.00�)"},
				{"ORI" ,"L' orientale" ,"(13.50�)"},
				{"IND" ,"L'indienne" ,"(14.00�)"}};

		//affichage du menu
		afficheMenu(pizzas);

	}

	/**
	 * Cette methode affiche le menu
	 * @param tab
	 */
	public static void afficheMenu(String [][]tab){


		System.out.println("***** Pizzeria Administration *****\n"+
				"1. Lister les pizzas \n"+
				"2.Ajouter une nouvelle pizza \n"+
				"3.Mettre � jour une pizza \n"+
				"4.Supprimer une pizza \n"+
				"99. Sortir \n");

		System.out.print("Votre choix : ");
		Scanner sc = new Scanner(System.in);
		int nbSaisie = sc.nextInt(); 

		choix(nbSaisie, tab);

		sc.close();
	}

	/**
	 * Cette methode gere le choix de l'utilisateur
	 * @param choix
	 * @param tab
	 */
	public static void choix(int choix, String [][]tab){


		do {
			Scanner sc = new Scanner(System.in);

			switch (choix) {
			case 1:
				System.out.println("Liste des pizza");
				listePizzas(tab);
				afficheMenu(tab);

				break;
			case 2:
				System.out.println("Ajout d'une nouvelle pizza");

				System.out.print("Veuillez saisir le code: ");

				String code = sc.nextLine(); 

				System.out.print("Veuillez saisir le nom: ");
				String nom = sc.nextLine(); 

				System.out.print("Veuillez saisir le prix: ");
				String prix = sc.nextLine(); 

				tab = ajouterPizza(code, nom, prix, tab);

				listePizzas(tab);

				afficheMenu(tab);
				break;
			case 3:

				System.out.println("Mise � jour d'une pizza\n");
				listePizzas(tab);

				//Code � modifier
				System.out.print("Veuillez saisir le code de la pizza � modifier: ");
				String codeAmodifier = sc.nextLine(); 

				if (verifierSiCodeExixste(codeAmodifier, tab)) {

					System.out.print("Veuillez saisir le code: ");
					String nouveauCode = sc.nextLine(); 

					System.out.print("Veuillez saisir le nom: ");
					String nouveauNom = sc.nextLine(); 

					System.out.print("Veuillez saisir le prix: ");
					String nouveauPrix = sc.nextLine(); 

					//****** MISE A JOUR *******
					tab = miseAjour(codeAmodifier ,nouveauCode, nouveauNom, nouveauPrix, tab);

					listePizzas(tab);

				} else {
					System.out.println("code non trouv� !");

				}

				afficheMenu(tab);
				break;
			case 4:
				System.out.println("Suppression d'une pizza");
				listePizzas(tab);

				//Code � modifier
				System.out.print("Veuillez saisir le code de la pizza �  supprimer: ");
				String codeASupprimer = sc.nextLine(); 

				//je v�rifie si le code existe
				if (verifierSiCodeExixste(codeASupprimer, tab)) {

					tab = supprimerPizza(codeASupprimer, tab);
					//	System.out.println(tab.length);
					listePizzas(tab);
				} else {
					System.out.println("code non trouv� !");

				}

				afficheMenu(tab);

				break;
			case 99:
				System.out.println("Aurevoir");
				System.exit(0);
				break;

			default:
				System.out.println("Aurevoir");
				System.exit(0);
				break;
			}

		} while (choix != 99);


	}
	/**
	 * Cette methode affiche la liste des pizza
	 * @param tab
	 */
	public static void listePizzas(String tab[][]){
		int tailleTab = tab.length;
		//string affiche=""
		for (int i = 0; i < tailleTab; i++) {
			if(null==tab[i][0]){
				break;
			}else{
				System.out.println(tab[i][0]+"->"+tab[i][1]+tab[i][2]);
			}


		}

	}

	public static String[][] ajouterPizza(String code, String nom, String prix, String[][]tab){
		String tabAjout[][] = new String[1000][3];


		for (int i = 0; i < tab.length; i++) {

			for (int j = 0; j < tab[i].length; j++) {
				tabAjout[i][j] = tab[i][j];	
			}
		}

		//je r�cupere le premier index libre
		int dernier =0;
		for (int j = 0; j < tabAjout.length && tabAjout[j][0] != null; j++) {
			dernier ++;
		}


		System.out.println(dernier);
		tabAjout[dernier][0] = code;
		tabAjout[dernier][1] = nom;
		tabAjout[dernier][2] = "("+prix+")�";

		return tabAjout;


	}

	/**
	 * Cette methode met � jour une pizza
	 * @param code
	 * @param nouveauCode
	 * @param nouveauNom
	 * @param nouveauPrix
	 * @param tab
	 */
	public static String[][] miseAjour(String code, String nouveauCode, String nouveauNom, String nouveauPrix, String[][]tab){

		for (int i = 0; i < tab.length; i++) {
			if(code.equals(tab[i][0])){

				tab[i][0] = nouveauCode;
				tab[i][1] = nouveauNom;
				tab[i][2] = "("+nouveauPrix+")�";

			}	

		}

		return tab;

	}

	/**
	 * Cette methode verifie si le code existe
	 * @param code
	 * @param tab
	 * @return vrai si le code existe
	 */
	public static boolean verifierSiCodeExixste(String code, String tab[][]){

		for (int i = 0; i < tab.length; i++) {

			if(code.equals(tab[i][0])){			
				return true;
			}	
		}
		return false;
	}

	/**
	 * Cette methode permet de supprimer une pizza
	 * @param code
	 * @param tab
	 */
	public static String[][] supprimerPizza(String code, String tab[][]){


		String tabASupprimer[][] = new String[1000][3];

		//je charge toutes pizzas dans le nouveau tableau sauf celle a supprimer
		for (int i = 0; i < tab.length; i++) {

			for (int j = 0; j < tab[i].length; j++) {
				//System.out.println(tab[i][0]);
				if(null==tab[i][0]){
					break;
				}else{
					if (!code.equals(tab[i][0])) {
						//	System.out.println(tab[i][j]+ " i : "+i+" j :"+ j);
						tabASupprimer[i][j] = tab[i][j];	
					} 
				}		

			}
		}

		return tabASupprimer;



	}


}
