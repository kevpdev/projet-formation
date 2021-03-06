package fr.pizzeria.console;

import java.util.Locale;
import java.util.Scanner;

import fr.pizzeria.model.Pizza;

public class PizzeriaAdminConsoleApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		Pizza [] pizzas={
				new Pizza(0,"PEP" ,"P�p�roni", 12.50),
				new Pizza(1, "MAR" ,"Margherita", 14.00),
				new Pizza(2, "REI" ,"Reine", 11.50),
				new Pizza(3, "FRO" ,"La 4 fromages", 12.00),
				new Pizza(4, "CAN" ,"La cannibale", 12.50),
				new Pizza(5, "SAV" ,"La Savoyage", 13.00),
				new Pizza(6, "ORI" ,"L' orientale", 15.50),
				new Pizza(7, "IND" ,"L'indienne", 14.00)};
		//affichage du menu
		afficheMenu(pizzas);

	}

	/**
	 * Cette methode affiche le menu
	 * @param tab
	 */
	public static void afficheMenu(Pizza[]tab){


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
	public static void choix(int choix, Pizza[] tab){


		do {
			Scanner sc = new Scanner(System.in).useLocale(Locale.US);

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
				double prix = sc.nextDouble(); 

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
					double nouveauPrix = sc.nextDouble(); 

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
	public static void listePizzas(Pizza tab[]){
		int tailleTab = tab.length;
		int nbPizza = Pizza.nbPizzas = 0;
		for (int i = 0; i < tailleTab ; i++) {
			if (null == tab[i]) {
				break;
			}else{
				System.out.println(tab[i].id+") "+tab[i].code+"->"+tab[i].nom+" "+tab[i].prix+"�");

				nbPizza ++;
			}

		}

		System.out.println("-------  "+nbPizza+" pizzas cr��es depuis l�initialisation du programme");

	}

	public static Pizza[] ajouterPizza(String code, String nom, double prix, Pizza tab[]){
		Pizza tabAjout[] = new Pizza[1000];


		for (int i = 0; i < tab.length; i++) {

			tabAjout[i] = tab[i];	

		}

		//je r�cupere le premier index libre
		int dernier =0;
		for (int j = 0; j < tabAjout.length; j++) {
			if (null == tabAjout[j]) {
				break;
			}else{
				dernier ++;

			}

		}

		//	System.out.println(dernier);
		tabAjout[dernier] = new Pizza(dernier, code, nom, prix);

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
	public static Pizza[] miseAjour(String code, String nouveauCode, String nouveauNom, double nouveauPrix, Pizza tab[]){

		for (int i = 0; i < tab.length; i++) {
			if(code.equals(tab[i].code)){

				tab[i].code = nouveauCode;
				tab[i].nom = nouveauNom;
				tab[i].prix = nouveauPrix;

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
	public static boolean verifierSiCodeExixste(String code, Pizza tab[]){

		for (int i = 0; i < tab.length; i++) {

			if(code.equals(tab[i].code)){			
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
	public static Pizza[] supprimerPizza(String code, Pizza tab[]){

		Pizza tabASupprimer[] = new Pizza[1000];

		//je charge toutes pizzas dans le nouveau tableau sauf celle a supprimer
		for (int i = 0; i < tab.length; i++) {

			if (null == tab[i]) {
				break;
			}else{
				if (!code.equals(tab[i].code)) {
					//	System.out.println(tab[i][j]+ " i : "+i+" j :"+ j);
					tabASupprimer[i] = tab[i];	
				} 

			}	

		}

		return tabASupprimer;
	}


}
