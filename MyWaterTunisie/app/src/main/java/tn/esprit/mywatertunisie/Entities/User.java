package tn.esprit.mywatertunisie.Entities;

public class User {

    private int id;
    private String nom, email, password, adresse, typeUser, imageUser;

    public User() {
    }

    public User( int id, String nom, String email, String password, String adresse, String imageUser , String typeUser) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.adresse = adresse;
        this.imageUser = imageUser;
        this.typeUser = typeUser;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    public String getImageUser() {
        return imageUser;
    }

    public void setImageUser(String imageUser) {
        this.imageUser = imageUser;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", adresse='" + adresse + '\'' +
                ", typeUser='" + typeUser + '\'' +
                ", imageUser='" + imageUser + '\'' +
                '}';
    }
}
