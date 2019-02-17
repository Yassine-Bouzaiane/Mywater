package tn.esprit.mywatertunisie.Entities;

public class Produit {

    private int idProd;
    private String reference;
    private String nom;
    private String description;
    private int prix;
    private String categorie;
    private String img;
    private int quantite;
    public Produit() {
    }


    public Produit(int idProd, String reference, String nom, String description, int prix, String categorie, String img, int quantite) {
        this.idProd = idProd;
        this.reference = reference;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.categorie = categorie;
        this.img = img;
        this.quantite = quantite;
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "reference='" + reference + '\'' +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", categorie='" + categorie + '\'' +
                ", img='" + img + '\'' +
                ", quantite=" + quantite +
                '}';
    }
}



