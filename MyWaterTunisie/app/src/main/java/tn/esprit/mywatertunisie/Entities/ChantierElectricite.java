package tn.esprit.mywatertunisie.Entities;

public class ChantierElectricite {

    private int id;
    private int idElect;
    private String imageChantier;

    public ChantierElectricite(int id, int idElect, String imageChantier) {
        this.id = id;
        this.idElect = idElect;
        this.imageChantier = imageChantier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdElect() {
        return idElect;
    }

    public void setIdElect(int idElect) {
        this.idElect = idElect;
    }

    public String getImageChantier() {
        return imageChantier;
    }

    public void setImageChantier(String imageChantier) {
        this.imageChantier = imageChantier;
    }

    @Override
    public String toString() {
        return "ChantierElectricite{" +
                "id=" + id +
                ", idElect=" + idElect +
                ", imageChantier='" + imageChantier + '\'' +
                '}';
    }
}
