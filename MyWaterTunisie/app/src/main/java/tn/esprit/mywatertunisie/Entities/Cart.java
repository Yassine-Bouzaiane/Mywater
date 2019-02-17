package tn.esprit.mywatertunisie.Entities;

public class Cart {
    private int idCart;
    private int idUser;
    private int idProd;
    private int quantiteCart;

    private String reference;
    private String nom;
    private String description;
    private int prix;
    private String categorie;
    private String img;
    private int quantite;

    public Cart() {
    }

//    public Cart(int idCart, int idUser, int idProd, int quantiteCart) {
//        this.idCart = idCart;
//        this.idUser = idUser;
//        this.idProd = idProd;
//        this.quantiteCart = quantiteCart;
//    }
//
//    public int getIdCart() {
//        return idCart;
//    }
//
//    public void setIdCart(int idCart) {
//        this.idCart = idCart;
//    }
//
//    public int getIdUser() {
//        return idUser;
//    }
//
//    public void setIdUser(int idUser) {
//        this.idUser = idUser;
//    }
//
//    public int getIdProd() {
//        return idProd;
//    }
//
//    public void setIdProd(int idProd) {
//        this.idProd = idProd;
//    }
//
//    public int getQuantiteCart() {
//        return quantiteCart;
//    }
//
//    public void setQuantiteCart(int quantiteCart) {
//        this.quantiteCart = quantiteCart;
//    }


    public Cart(int idCart, int idUser, int idProd,
                int quantiteCart, String reference, String nom, String description,
                int prix, String categorie, String img, int quantite) {
        this.idCart = idCart;
        this.idUser = idUser;
        this.idProd = idProd;
        this.quantiteCart = quantiteCart;
        this.reference = reference;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.categorie = categorie;
        this.img = img;
        this.quantite = quantite;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public int getQuantiteCart() {
        return quantiteCart;
    }

    public void setQuantiteCart(int quantiteCart) {
        this.quantiteCart = quantiteCart;
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
        return "Cart{" +
                "idCart=" + idCart +
                ", idUser=" + idUser +
                ", idProd=" + idProd +
                ", quantiteCart=" + quantiteCart +
                ", reference='" + reference + '\'' +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", categorie='" + categorie + '\'' +
                ", img='" + img + '\'' +
                ", quantite=" + quantite +
                '}';
    }
}

