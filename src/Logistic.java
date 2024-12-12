import fr.ulille.but.sae_s2_2024.AlgorithmeKPCC;
import fr.ulille.but.sae_s2_2024.Chemin;
import fr.ulille.but.sae_s2_2024.ModaliteTransport;
import fr.ulille.but.sae_s2_2024.MultiGrapheOrienteValue;
import fr.ulille.but.sae_s2_2024.Trancon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
    
public class Logistic {     //Initialisation du multigraphe
    private ArrayList<String[]> data;                                       //Données exploitables
    private ArrayList<Ville> villes;                                        //Liste des villes
    private ArrayList<Direction> directions;                                //Liste des villes
    private MultiGrapheOrienteValue mg;                                     //Graphe
    private ArrayList<String[]> correspondance;                             //Correspondances venant du csv
    private ArrayList<Correspondance> lieuCorrespondance;                   //Correspondances de type Correpondance
    private Voyageur utilisateur;
    private Ville depart;
    private Ville arrivee;
    private Historique historique;

    public Logistic(Voyageur utilisateur, Ville depart, Ville arrivee){
        this.data = new ArrayList<String[]>();
        this.villes = new ArrayList<Ville>();
        this.directions = new ArrayList<Direction>();
        this.mg = new MultiGrapheOrienteValue();
        this.correspondance = new ArrayList<String[]>();
        this.lieuCorrespondance = new ArrayList<Correspondance>();
        this.utilisateur = utilisateur;
        this.arrivee = arrivee;
        this.depart = depart;
        this.historique = new Historique();
    }

    // ──────────────────────────────────────────── { POO } ────────────────────────────────────────────\\

    // Ajouter mes villes dans la variable villes
    public void createVilles() {        //tested
        for (int i = 0; i < this.data.size(); i++) {
            Ville ville1 = new Ville(this.data.get(i)[0]);
            Ville ville2 = new Ville(this.data.get(i)[1]);
            if (!this.villes.contains(ville1)) {
                this. villes.add(ville1);
            }
            if (!this.villes.contains(ville2)) {
                this.villes.add(ville2);
            }
        }
    }

    //Convertis un String en modalité de transport
    private ModaliteTransport convertMT(String a) {
        if (a.toLowerCase().equals("train")) {
            return ModaliteTransport.TRAIN;
        } else if (a.toLowerCase().equals("bus")) {
            return ModaliteTransport.BUS;
        } else if (a.toLowerCase().equals("avion")) {
            return ModaliteTransport.AVION;
        }
        return null;
    }

    // Ajout  dans le graphe
    public void createDirection(){
        for (int i = 0; i < this.data.size(); i++) {
            if(utilisateur.getModalite() != null){
                if(utilisateur.getModalite().equals(convertMT(this.data.get(i)[2]))){
                    Direction temp = new Direction(new Ville(this.data.get(i)[0]),
                    new Ville(this.data.get(i)[1]),
                    convertMT(this.data.get(i)[2]),
                    Double.parseDouble(this.data.get(i)[3]),
                    Double.parseDouble(this.data.get(i)[4]),
                    Double.parseDouble(this.data.get(i)[5]));
                    if(!this.directions.contains(temp)) this.directions.add(temp);
                }
            }else{
                Direction temp = new Direction(new Ville(this.data.get(i)[0]),
                new Ville(this.data.get(i)[1]),
                convertMT(this.data.get(i)[2]),
                Double.parseDouble(this.data.get(i)[3]),
                Double.parseDouble(this.data.get(i)[4]),
                Double.parseDouble(this.data.get(i)[5]));
                if(!this.directions.contains(temp)) this.directions.add(temp);
            }
        }
    }

    // Rechercher correspondance entre deux mobilitées puis change dans directions
    public void correspondance(){
        ModaliteTransport tmpArrive;
        ModaliteTransport tmpDepart;
        int k;
        boolean trouve;
        for(int i = 0; i < this.directions.size(); i++){
            for(int j = 0; j < this.directions.size(); j++){
                if(this.directions.get(i).getArrivee().equals(this.directions.get(j).getDepart()) && this.directions.get(i).getModalite() != this.directions.get(j).getModalite()){
                    tmpDepart = this.directions.get(j).getModalite();
                    tmpArrive = this.directions.get(i).getModalite();
                    if(!isCorrespondance(this.directions.get(j))) {
                        trouve = false;
                        k = 0;
                        while(!trouve && k < this.correspondance.size()){
                            if((convertMT(this.correspondance.get(k)[1]).equals(tmpDepart)
                            && convertMT(this.correspondance.get(k)[2]).equals(tmpArrive)
                            || convertMT(this.correspondance.get(k)[2]).equals(tmpDepart)
                            && convertMT(this.correspondance.get(k)[1]).equals(tmpArrive))
                            &&this.directions.get(j).getDepart().equals(new Ville(this.correspondance.get(k)[0]))) {
                                this.directions.get(j).setTemps((this.directions.get(j).getTemps() + Double.parseDouble(this.correspondance.get(k)[3])));
                                this.directions.get(j).setPollution(this.directions.get(j).getPollution() + Double.parseDouble(this.correspondance.get(k)[4]));
                                this.directions.get(j).setPrix(this.directions.get(j).getPrix() + Double.parseDouble(this.correspondance.get(k)[5]));
                                this.lieuCorrespondance.add(new Correspondance( (Ville) this.directions.get(j).getDepart(), tmpDepart, tmpArrive));
                                trouve = true;
                            }
                            k++;
                        }
                    }
                }
            }
        }
    }

    // regarde si une direction est déjà une correspondance
    public boolean isCorrespondance(Direction d){
        for(int i = 0; i < lieuCorrespondance.size(); i ++){
            if(d.getArrivee().equals(lieuCorrespondance.get(i).getVille()) || d.getDepart().equals(lieuCorrespondance.get(i).getVille()) && d.getModalite().equals(lieuCorrespondance.get(i).getModaliteTransportDepart()) || d.getModalite().equals(lieuCorrespondance.get(i).getModaliteTransportArrivee())){
                return true;
            }
        }
        return false;
    }

    // Initialise toute les donnée dans une arrayList
    // public ArrayList<String[]> dataLogistic(){          //testable
    //     ArrayList<String[]> tp = new ArrayList<String[]>();
    //     for(int i = 0; i < rawData.length; i++){
    //         tp.add(rawData[i].split(";"));
    //     }
    //     return tp;
    // }

    //Pert de lire les CSVs et de les mettre dans les ArrayList correspondant
    public void csvFileLogistic(String csv){
        try (BufferedReader br = Files.newBufferedReader(Paths.get(csv))) {
            String delimiter = ";";
            String line = br.readLine();
            while (line != null) {
                if(csv.equals("./data.csv")){
                    this.data.add(line.split(delimiter));
                }else if(csv.equals("./dataCorrespondance.csv")){
                    this.correspondance.add(line.split(delimiter));
                }else if(csv.equals("./save.csv")){
                    this.historique.getHistorique().add(line.split("\n"));
                }
                line = br.readLine();      
            }
        }catch(FileNotFoundException fnfe){
            System.out.println("File not found: "); fnfe.printStackTrace(); 
        }catch (IOException ex) {
            System.out.println("Reading error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // permet le toString les lieux de correspondances avec le lieu de départ et le lieu d'arrivée
    public String toStringCorrespondance(int k, int numero){
        String tp = "";
        List<Chemin> chemins = AlgorithmeKPCC.kpcc(this.getMg(), depart, arrivee, k);
        List<Trancon> arretes = chemins.get(numero).aretes();
        int idx = 1;

        tp += "Lieu de départ : \n\t"+chemins.get(numero).aretes().get(0)+"\n";
        for (Trancon t : arretes) {
            for (int i = 0; i < lieuCorrespondance.size(); i++) {

                if (t.getDepart().equals(lieuCorrespondance.get(i).getVille()) && t.getModalite().equals(lieuCorrespondance.get(i).getModaliteTransportDepart())){
                    tp += "Lieu correspondance "+idx+" : "+t + "\n";
                    idx++;
                }
            }
        }
        if(!(correspondance.toString().equals(chemins.get(numero).aretes().get(chemins.get(numero).aretes().size()-1).toString()))){
            tp += "Lieu d'arrivée :  \n\t" + chemins.get(numero).aretes().get(chemins.get(numero).aretes().size()-1)+"\n";
        }
        tp += "Poids  : " + chemins.get(numero).poids() + "\n";
        save(tp);
        return tp;
    }

    public void save(String arretes){
        if (arretes != null) {
            try (FileWriter fw = new FileWriter("save.csv", true); PrintWriter pw = new PrintWriter(fw)) {
                pw.write("Nom : "+utilisateur.getNom() + "\n");
                pw.write("Critére : "+utilisateur.getCritere() + "\n");
                pw.write(arretes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void serializeHistory() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./historique.ser"))) {
            oos.writeObject(this.historique);
        }catch(Exception e){ e.printStackTrace();}
    }

    public String deserializeHistory() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./historique.ser"))) {
            Historique tp = (Historique) ois.readObject();
            return tp.toString();
        }catch(Exception e){ e.printStackTrace();}
        return "Error loading history";
    }


    // ──────────────────────────────────────────── { SETTER/GETTER } ────────────────────────────────────────────\\

    public MultiGrapheOrienteValue getMg() {
        return this.mg;
    }
    public ArrayList<Ville> getVilles() {
        return this.villes;
    }

    public ArrayList<Direction> getDirections() {
        return this.directions;
    }

    public ArrayList<String[]> getData() {
        return this.data;
    }

    public ArrayList<String[]> getCorrespondance(){
        return this.correspondance;
    }

    public ArrayList<Correspondance> getLieuCorrespondance() {
        return this.lieuCorrespondance;
    }

    public Voyageur getUtilisateur() {
        return this.utilisateur;
    }

    public Historique getHistorique() {
        return this.historique;
    }

    public Ville getDepart() {
        return depart;
    }

    public Ville getArrivee() {
        return arrivee;
    }

    public void setArrivee(Ville arrivee) {
        this.arrivee = arrivee;
    }

    public void setDepart(Ville depart) {
        this.depart = depart;
    }

    public void setCritere(String critere) {
        this.utilisateur.setCritere(critere);
    }

    public void setModalité(ModaliteTransport modalité) {
        this.utilisateur.setModalité(modalité);
    }

    public void setNom(String nom) {
        this.utilisateur.setNom(nom);
    }

    // ──────────────────────────────────────────── { GRAPHE } ────────────────────────────────────────────\\

    // Ajout les lieux dans le graphe
    public void addLieux(){
        for(int i = 0; i < this.villes.size(); i++){
            this.mg.ajouterSommet(this.villes.get(i));
            
        }
    }

    // Ajout les directions dans le graphe
    public void addDirections() {
        for(int i = 0; i < this.directions.size(); i++){
            if(utilisateur.getCritere().equals("temps")){
                this.mg.ajouterArete(this.directions.get(i), this.directions.get(i).getTemps());;
            }else if(utilisateur.getCritere().equals("co2")){
                this.mg.ajouterArete(this.directions.get(i), this.directions.get(i).getPollution());;
            }else if (utilisateur.getCritere().equals("prix")){
                this.mg.ajouterArete(this.directions.get(i), this.directions.get(i).getPrix());;
            }
        }
    }
    
    public void modifierPoidsGraphe(){
        for(int i = 0; i < this.directions.size(); i++){
            if(utilisateur.getCritere().equals("temps")){
                this.mg.modifierPoidsArete(this.directions.get(i), this.directions.get(i).getTemps());
            }else if(utilisateur.getCritere().equals("co2")){
                this.mg.modifierPoidsArete(this.directions.get(i), this.directions.get(i).getPollution());
            }else if (utilisateur.getCritere().equals("prix")){
                this.mg.modifierPoidsArete(this.directions.get(i), this.directions.get(i).getPrix());
            }
        }
    }

    // Initilise le graphe
    public void initialisationGraphe(){
        addLieux();
        addDirections();
        }
}