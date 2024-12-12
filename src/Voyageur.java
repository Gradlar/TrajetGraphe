import fr.ulille.but.sae_s2_2024.ModaliteTransport;

public class Voyageur {
    private String nom;
    private String critere;
    private ModaliteTransport modalité;

    public Voyageur(String nom, String critere, ModaliteTransport modaliteTransport){
        this.nom = nom;
        this.critere = critere;
        this.modalité = modaliteTransport;
    }

    public Voyageur(String nom, String critere){
        this(nom,critere, null);
    }

    public String getCritere() {
        return this.critere;
    }

    public String getNom() {
        return this.nom;
    }

    public ModaliteTransport getModalite() {
        return this.modalité;
    }

    public void setCritere(String critere) {
        this.critere = critere;
    }

    public void setModalité(ModaliteTransport modalité) {
        this.modalité = modalité;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    // private String choixCritere(){
    //     String tp  ="";
    //     boolean valide = false;
    //     try (Scanner sc = new Scanner(System.in)) {
    //         while(!valide){
    //             System.out.println("Utiliser soit 1(temps), 2(co2) ou 3(prix)");
    //             tp = sc.next();
    //             if(tp.length() == 1){
    //                 if(Integer.parseInt(tp) == 1){
    //                     valide = true;
    //                     tp = "temps";
    //                 }else if(Integer.parseInt(tp) == 2){
    //                     valide = true;
    //                     tp = "co2";
    //                 }else if(Integer.parseInt(tp) == 3){
    //                     valide = true;
    //                     tp = "prix";
    //                 }else{
    //                     System.out.println("Erreur nombre incorrect");
    //                 }
    //             }
    //         }
    //     } catch (NumberFormatException e) {
    //         e.printStackTrace();
    //     }
    //     return tp;
    // }
}
