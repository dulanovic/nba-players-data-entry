package db;

import domen.Igrac;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBroker {

    private Connection connection;

    public void ucitajDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException cnfex) {
            cnfex.printStackTrace();
        }
    }

    public void otvoriKonekciju() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/nba_liga", "admin", "admin");
            connection.setAutoCommit(false);
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
    }

    public void zatvoriKonekciju() {
        try {
            connection.close();
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
    }

    public void potvrdiTransakciju() {
        try {
            connection.commit();
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
    }

    public void ponistiTransakciju() {
        try {
            connection.rollback();
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
    }

    public Igrac proveriIgraca(int igracId, int timId) {
        try {
            String upit = "SELECT i.igrac_id, i.ime, i.prezime, t.tim_naziv FROM igrac i INNER JOIN tim t ON (i.tim = t.tim_id) WHERE igrac_id = " + igracId + " AND tim = " + timId;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(upit);
            Igrac i = null;
            while (rs.next()) {
                int igrac_id = rs.getInt(1);
                String ime = rs.getString(2);
                String prezime = rs.getString(3);
                String tim = rs.getString(4);

                i = new Igrac(igrac_id, ime, prezime, tim);
            }
            return i;
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            return null;
        }
    }

    public void unesiStatistiku(int igracId, int timId, String prethodnaSezona, String projekcije, int pocetnaCena) {
        try {
            String[] prethodnaSezonaNiz = prethodnaSezona.split("\t");
            String[] projekcijeNiz = projekcije.split("\t");

            String upitPrethodnaSezona = "";
            String upitProjekcije = "";

            if (!prethodnaSezona.equals("")) {
                Double minutazaPrethodnaSezona = Double.parseDouble("0" + prethodnaSezonaNiz[0]);
                Double sutIzIgrePrethodnaSezona = Double.parseDouble("0" + prethodnaSezonaNiz[1]);
                Double slobodnaBacanjaPrethodnaSezona = Double.parseDouble(prethodnaSezonaNiz[2]);
                Double pogodak3pPrethodnaSezona = Double.parseDouble(prethodnaSezonaNiz[3]);
                Double skokoviPrethodnaSezona = Double.parseDouble(prethodnaSezonaNiz[4]);
                Double asistencijePrethodnaSezona = Double.parseDouble(prethodnaSezonaNiz[5]);
                Double asistIzgLopPrethodnaSezona = Double.parseDouble(prethodnaSezonaNiz[6]);
                Double ukradeneLoptePrethodnaSezona = Double.parseDouble(prethodnaSezonaNiz[7]);
                Double blokadePrethodnaSezona = Double.parseDouble(prethodnaSezonaNiz[8]);
                Double izgubljeneLoptePrethodnaSezona = Double.parseDouble(prethodnaSezonaNiz[9]);
                Double poeniPrethodnaSezona = Double.parseDouble(prethodnaSezonaNiz[10]);

                upitPrethodnaSezona = "minutaza_prethodna_sezona = " + minutazaPrethodnaSezona + ", sut_iz_igre_prethodna_sezona = " + sutIzIgrePrethodnaSezona + ", slobodna_bacanja_prethodna_sezona = " + slobodnaBacanjaPrethodnaSezona + ", pogodak_3p_prethodna_sezona = " + pogodak3pPrethodnaSezona + ", skokovi_prethodna_sezona = " + skokoviPrethodnaSezona + ", asistencije_prethodna_sezona = " + asistencijePrethodnaSezona + ", asist_izglop_prethodna_sezona = " + asistIzgLopPrethodnaSezona + ", ukradene_lopte_prethodna_sezona = " + ukradeneLoptePrethodnaSezona + ", blokade_prethodna_sezona = " + blokadePrethodnaSezona + ", izgubljene_lopte_prethodna_sezona = " + izgubljeneLoptePrethodnaSezona + ", poeni_prethodna_sezona = " + poeniPrethodnaSezona + ", ";
            }

            if (!projekcije.equals("")) {
                Double minutazaProjekcija = Double.parseDouble("0" + projekcijeNiz[0]);
                Double sutIzIgreProjekcija = Double.parseDouble("0" + projekcijeNiz[1]);
                Double slobodnaBacanjaProjekcija = Double.parseDouble(projekcijeNiz[2]);
                Double pogodak3pProjekcija = Double.parseDouble(projekcijeNiz[3]);
                Double skokoviProjekcija = Double.parseDouble(projekcijeNiz[4]);
                Double asistencijeProjekcija = Double.parseDouble(projekcijeNiz[5]);
                Double asistIzgLopProjekcija = Double.parseDouble(projekcijeNiz[6]);
                Double ukradeneLopteProjekcija = Double.parseDouble(projekcijeNiz[7]);
                Double blokadeProjekcija = Double.parseDouble(projekcijeNiz[8]);
                Double izgubljeneLopteProjekcija = Double.parseDouble(projekcijeNiz[9]);
                Double poeniProjekcija = Double.parseDouble(projekcijeNiz[10]);

                upitProjekcije = "minutaza_projekcija = " + minutazaProjekcija + ", sut_iz_igre_projekcija = " + sutIzIgreProjekcija + ", slobodna_bacanja_projekcija = " + slobodnaBacanjaProjekcija + ", pogodak_3p_projekcija = " + pogodak3pProjekcija + ", skokovi_projekcija = " + skokoviProjekcija + ", asistencije_projekcija = " + asistencijeProjekcija + ", asist_izglop_projekcija = " + asistIzgLopProjekcija + ", ukradene_lopte_projekcija = " + ukradeneLopteProjekcija + ", blokade_projekcija = " + blokadeProjekcija + ", izgubljene_lopte_projekcija = " + izgubljeneLopteProjekcija + ", poeni_projekcija = " + poeniProjekcija + ", ";
            }

            String upit = "UPDATE igrac SET " + upitPrethodnaSezona + upitProjekcije + "pocetna_cena = " + pocetnaCena + " WHERE igrac_id = " + igracId + " AND tim = " + timId;
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(upit);
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }

    }

}
