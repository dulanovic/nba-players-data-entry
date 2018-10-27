package kontroler;

import db.DatabaseBroker;
import domen.Igrac;

public class Kontroler {

    private static Kontroler instance;
    private DatabaseBroker db;

    private Kontroler() {
        db = new DatabaseBroker();
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public Igrac proveriIgraca(int igracId, int timId) {
        db.ucitajDriver();
        db.otvoriKonekciju();
        Igrac i = db.proveriIgraca(igracId, timId);
        db.potvrdiTransakciju();
        db.zatvoriKonekciju();
        return i;
    }
    
    public void unesiStatistiku(int igracId, int timId, String prethodnaSezona, String projekcije, int pocetnaCena) {
        db.ucitajDriver();
        db.otvoriKonekciju();
        db.unesiStatistiku(igracId, timId, prethodnaSezona, projekcije, pocetnaCena);
        db.potvrdiTransakciju();
        db.zatvoriKonekciju();
    }

}
