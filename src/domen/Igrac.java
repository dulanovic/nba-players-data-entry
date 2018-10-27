package domen;

public class Igrac {

    private int igracId;
    private String ime;
    private String prezime;
    private String tim;

    public Igrac() {
    }

    public Igrac(int igracId, String ime, String prezime, String tim) {
        this.igracId = igracId;
        this.ime = ime;
        this.prezime = prezime;
        this.tim = tim;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public int getIgracId() {
        return igracId;
    }

    public void setIgracId(int igracId) {
        this.igracId = igracId;
    }

    public String getTim() {
        return tim;
    }

    public void setTim(String tim) {
        this.tim = tim;
    }

    @Override
    public String toString() {
        return igracId + " ---> " + ime + " " + prezime + " (" + tim + ")";
    }

}
