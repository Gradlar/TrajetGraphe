import java.io.Serializable;
import java.util.ArrayList;

public class Historique implements Serializable{
    private ArrayList<String[]> historique;

    public Historique(){
        this.historique = new ArrayList<String []>();
    }

    public ArrayList<String[]> getHistorique() {
        return this.historique;
    }

    public void setHistorique(ArrayList<String[]> historique) {
        this.historique = historique;
    }

    @Override
    public String toString(){
        String tp = "";
        for(int i = 0; i < this.historique.size(); i++){
            for(int j = 0; j < this.historique.get(i).length; j++){
                tp += this.historique.get(i)[j] +"\n";
            }
        }
        return tp;
    }

}
