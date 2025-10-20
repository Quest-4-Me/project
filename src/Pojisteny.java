public class Pojisteny {
    // Atributy pojištěného
    private String jmeno;
    private String prijmeni;
    private int vek;
    private String telefon;

    // Konstruktor – vytvoří nového pojištěného s danými údaji
    public Pojisteny(String jmeno, String prijmeni, int vek, String telefon) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.vek = vek;
        this.telefon = telefon;
    }

    // Gettery pro přístup k atributům
    public String getJmeno() {
        return jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public int getVek() {
        return vek;
    }

    public String getTelefon() {
        return telefon;
    }

    // Přetížená metoda toString – vrací textovou reprezentaci pojištěného
    @Override
    public String toString() {
        return jmeno + " " + prijmeni + " " + vek + " " + telefon;
    }
}