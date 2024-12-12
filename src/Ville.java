import fr.ulille.but.sae_s2_2024.Lieu;

public class Ville implements Lieu {
    private String nom;

    public Ville(String nom){
        this.nom = nom;
    }

    public String getNom(){
        return this.nom;
    }

    public String toString(){
        return this.nom;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Ville lieux = (Ville) obj;
        return nom.equals(lieux.nom);
    }

    @Override
    public int hashCode() {
        return this.nom.hashCode();
    }
}
