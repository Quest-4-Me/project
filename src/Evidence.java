import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Evidence {
    private List<Pojisteny> pojisteni = new ArrayList<>();
    private final String FILE_NAME = "pojisteni.txt";

    public Evidence() {
        nactiZeSouboru();
    }

    public void pridejPojisteneho(String jmeno, String prijmeni, int vek, String telefon) {
        Pojisteny novy = new Pojisteny(jmeno, prijmeni, vek, telefon);
        pridejPojisteneho(novy);
    }

    public void pridejPojisteneho(Pojisteny p) {
        if (p.getJmeno().isBlank() || p.getPrijmeni().isBlank() || p.getTelefon().isBlank()) {
            throw new IllegalArgumentException("Jméno, příjmení a telefon nesmí být prázdné.");
        }
        if (p.getVek() <= 0) {
            throw new IllegalArgumentException("Věk musí být kladné číslo.");
        }

        pojisteni.add(p);
        ulozDoSouboru(p);
    }

    public List<Pojisteny> getVsechny() {
        return new ArrayList<>(pojisteni);
    }

    public List<Pojisteny> vyhledej(String jmeno, String prijmeni) {
        List<Pojisteny> vysledky = new ArrayList<>();
        for (Pojisteny p : pojisteni) {
            if (p.getJmeno().equalsIgnoreCase(jmeno.trim()) &&
                    p.getPrijmeni().equalsIgnoreCase(prijmeni.trim())) {
                vysledky.add(p);
            }
        }
        return vysledky;
    }

    private void ulozDoSouboru(Pojisteny p) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(p.getJmeno() + ";" + p.getPrijmeni() + ";" + p.getVek() + ";" + p.getTelefon());
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Chyba při ukládání do souboru: " + e.getMessage());
        }
    }

    private void nactiZeSouboru() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String radek;
            while ((radek = br.readLine()) != null) {
                String[] casti = radek.split(";");
                if (casti.length == 4) {
                    String jmeno = casti[0];
                    String prijmeni = casti[1];
                    int vek = Integer.parseInt(casti[2]);
                    String telefon = casti[3];
                    pojisteni.add(new Pojisteny(jmeno, prijmeni, vek, telefon));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Chyba při načítání ze souboru: " + e.getMessage());
        }
    }
}