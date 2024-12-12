import fr.ulille.but.sae_s2_2024.ModaliteTransport;

public class Correspondance {
    private Ville ville;
    private ModaliteTransport modaliteTransportDepart;
    private ModaliteTransport modaliteTransportArrivee;

    public Correspondance(Ville ville, ModaliteTransport modaliteTransportDepart, ModaliteTransport modaliteTransportArrivee){
        this.ville = ville;
        this.modaliteTransportDepart = modaliteTransportDepart;
        this.modaliteTransportArrivee = modaliteTransportArrivee;
    }

    public ModaliteTransport getModaliteTransportDepart() {
        return this.modaliteTransportDepart;
    }

    public ModaliteTransport getModaliteTransportArrivee() {
        return this.modaliteTransportArrivee;
    }

    public Ville getVille() {
        return this.ville;
    }

    public String toString(){
        return this.ville + " : "+ this.modaliteTransportDepart+ " , " + this.modaliteTransportArrivee;
    }
}
