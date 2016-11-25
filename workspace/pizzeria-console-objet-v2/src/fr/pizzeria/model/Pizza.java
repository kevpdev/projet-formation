package fr.pizzeria.model;

public class Pizza {
	
	private int id;
	private String code;
	private String nom;
	private double prix;
	public static int nbPizzas;
	
	public Pizza(String code, String nom, double prix) {
		super();
		nbPizzas++;
		this.setCode(code);
		this.setNom(nom);
		this.setPrix(prix);
	}
	
	public Pizza(int id, String code, String nom, double prix) {
		this(code, nom, prix);
		nbPizzas++;
		this.setId(id);

	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public double getPrix() {
		return prix;
	}


	public void setPrix(double prix) {
		this.prix = prix;
	}


	public static int getNbPizzas() {
		return nbPizzas;
	}


	public static void setNbPizzas(int nbPizzas) {
		Pizza.nbPizzas = nbPizzas;
	}


	@Override
	public String toString() {
		return "" + id + ") " + code + "-> " + nom + " (" + prix + "�)";
	}
	
	

}