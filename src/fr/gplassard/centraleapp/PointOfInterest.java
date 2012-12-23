package fr.gplassard.centraleapp;

public class PointOfInterest {
	private Long id;
	private String nom;
	private String secteur;
	private double longitude;
	private double latitude;
	private String urlImage;
	private String urlSmallImage;
	private String quartier;
	private String informations;
	private String categorie;
	private boolean favoris;

	public PointOfInterest() {
		favoris = false;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getUrlSmallImage() {
		return urlSmallImage;
	}

	public void setUrlSmallImage(String urlSmallImage) {
		this.urlSmallImage = urlSmallImage;
	}

	public String getQuartier() {
		return quartier;
	}

	public void setQuartier(String quartier) {
		this.quartier = quartier;
	}

	public String getInformations() {
		return informations;
	}

	public void setInformations(String informations) {
		this.informations = informations;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getSecteur() {
		return secteur;
	}

	public void setSecteur(String secteur) {
		this.secteur = secteur;
	}

	@Override
	public String toString() {
		return "PointOfInterest [nom=" + nom + ", secteur=" + secteur + ", longitude=" + longitude + ", latitude="
				+ latitude + ", urlImage=" + urlImage + ", urlSmallImage=" + urlSmallImage + ", quartier=" + quartier
				+ ", informations=" + informations + ", categorie=" + categorie + "]";
	}

	public String getShortDescription() {
		return quartier + " - " + secteur;
	}

	public void setFavoris(boolean favoris) {
		this.favoris = favoris;
	}

	public boolean isFavoris() {
		return favoris;
	}

	public boolean matches(String recherche) {
		return nom.toLowerCase().contains(recherche.toLowerCase());
	}

	public boolean isOfCategorie(String stringCategorie) {
		return categorie.contains(stringCategorie);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
