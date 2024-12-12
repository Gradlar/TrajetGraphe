import java.util.HashMap;

import fr.ulille.but.sae_s2_2024.Lieu;
import fr.ulille.but.sae_s2_2024.ModaliteTransport;
import fr.ulille.but.sae_s2_2024.Trancon;

public class Direction implements Trancon {
    private Lieu depart;
    private Lieu arrivee;
    private ModaliteTransport modaliteTransport;
    private HashMap <TypeCout, Double> map = new HashMap<TypeCout, Double>();

    public Direction(Lieu d, Lieu ar, ModaliteTransport mt, double temps, double pollution, double prix){
        this.depart = d;
        this.arrivee = ar;
        this.modaliteTransport = mt;
        this.map.put(TypeCout.PRIX, prix);
        this.map.put(TypeCout.POLLUTION, pollution);
        this.map.put(TypeCout.TEMPS, temps);
    }

    @Override
    public Lieu getDepart() {
        return this.depart;
    }

    @Override
    public Lieu getArrivee() {
        return this.arrivee;
    }

    @Override
    public ModaliteTransport getModalite() {
        return this.modaliteTransport;
    }

    public double getTemps() {
        return this.map.get(TypeCout.TEMPS);
    }

    public double getPrix(){
        return this.map.get(TypeCout.PRIX);
    }

    public double getPollution() {
        return this.map.get(TypeCout.POLLUTION);
    }

    public void setPrix(double prix){
        this.map.put(TypeCout.PRIX, prix);
    }

    public void setPollution(double pollution){
        this.map.put(TypeCout.POLLUTION, pollution);
    }

    public void setTemps(double temps){
        this.map.put(TypeCout.TEMPS, temps);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Direction direction = (Direction) o;
        
        if (!this.depart.equals(direction.depart)) return false;
        if (!this.arrivee.equals(direction.arrivee)) return false;
        if (this.modaliteTransport != direction.modaliteTransport) return false;
        return map.equals(direction.map);
    }

    @Override
    public int hashCode() {
        int result = this.depart.hashCode();
        result = 31 * result + this.arrivee.hashCode();
        result = 31 * result + this.modaliteTransport.hashCode();
        result = 31 * result + this.map.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "\n" + this.depart + " -> " + this.arrivee + " , par " + this.modaliteTransport + ": \n\n\t Temps : " +
        this.map.get(TypeCout.TEMPS) + "\n\t Pollution : " + this.map.get(TypeCout.POLLUTION) + "\n\t Prix :  " + this.map.get(TypeCout.PRIX)+ "\n";
    }
    
}