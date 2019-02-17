package tn.esprit.mywatertunisie.Entities;

public class Electricite {

    int idElectricite;
    String titre;
    String decription;
    String imageElect;

    public Electricite() {
    }

    public Electricite(String titre, String decription, String imageElect) {
        this.titre = titre;
        this.decription = decription;
        this.imageElect = imageElect;
    }

    public int getIdElectricite() {
        return idElectricite;
    }

    public void setIdElectricite(int idElectricite) {
        this.idElectricite = idElectricite;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getImageElect() {
        return imageElect;
    }

    public void setImageElect(String imageElect) {
        this.imageElect = imageElect;
    }
}
