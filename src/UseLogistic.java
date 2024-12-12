import java.io.IOException;

import fr.ulille.but.sae_s2_2024.ModaliteTransport;

public class UseLogistic { //Temp class

    // public static void scenario1(){
    //     String data[] = new String[]{
    //          "villeA;villeB;Train;60;1.7;80",
    //          "villeB;villeD;Train;22;2.4;40",
    //          "villeA;villeC;Train;42;1.4;50",
    //          "villeB;villeC;Train;14;1.4;60",
    //          "villeC;villeD;Avion;110;150;22",
    //          "villeC;villeD;Train;65;1.2;90"
    //     };
    //     Voyageur voyageur = new Voyageur("Enzo","co2", ModaliteTransport.TRAIN);
    //     Logistic test = new Logistic(voyageur, new Ville("A"), new Ville("O"));
    //     test.createVilles();
    //     test.createDirection();
    //     test.initialisationGraphe();
    //     System.out.println(AlgorithmeKPCC.kpcc(test.getMg(), new Ville("Lille"), new Ville("Lyon"), 3));
    // }

    public static void scenario2() throws ClassNotFoundException, IOException{
        Voyageur voyageur1 = new Voyageur("Enzo","temps", ModaliteTransport.AVION);
        Voyageur voyageur2 = new Voyageur("Axel","prix");
        Logistic test = new Logistic(voyageur1, new Ville("L"), new Ville("G"));
        Logistic test2 = new Logistic(voyageur2, new Ville("A"), new Ville("O"));
        test.csvFileLogistic("./data.csv");
        test.csvFileLogistic("./dataCorrespondance.csv");
        test.createVilles();
        test.createDirection();
        test.correspondance();
        test.initialisationGraphe();

        test2.csvFileLogistic("./data.csv");
        test2.csvFileLogistic("./dataCorrespondance.csv");
        test2.createVilles();
        test2.createDirection();
        test2.correspondance();
        test2.initialisationGraphe();
        
        System.out.println(test.toStringCorrespondance(2,0));
        System.out.println(test2.toStringCorrespondance(2,1)); 
        test.csvFileLogistic("./save.csv");
        test2.csvFileLogistic("./save.csv");
        test.serializeHistory();
        System.out.println(test.deserializeHistory());
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        //scenario1();
        scenario2();
    }
}
