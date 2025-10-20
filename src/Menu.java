
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static Scanner sc = new Scanner(System.in);
    private static Evidence evidence = new Evidence(); // instance evidence pojištěných

    // Hlavní metoda pro spuštění menu
    public static void start() {
        String volba;

        do {
            zobrazMenu(); // zobrazí nabídku
            volba = sc.nextLine();

            switch (volba) {
                case "1":
                    pridejPojisteneho(); // přidání nového pojištěného
                    break;
                case "2":
                    vypisPojistene(); // výpis všech pojištěných
                    break;
                case "3":
                    vyhledejPojisteneho(); // vyhledání pojištěného podle jména a příjmení
                    break;
                case "4":
                    System.out.println("Ukončuji program...");
                    break;
                default:
                    System.out.println("Neplatná volba. Zkuste znovu.");
            }

            // Pauza před návratem do menu
            if (!volba.equals("4")) {
                System.out.println("\nPokračujte libovolnou klávesou...");
                sc.nextLine();
            }

        } while (!volba.equals("4"));
    }

    // Vypíše hlavní nabídku programu
    private static void zobrazMenu() {
        System.out.println("==============================");
        System.out.println("Evidence pojištěných");
        System.out.println("==============================");
        System.out.println("1 - Přidat nového pojištěného");
        System.out.println("2 - Vypsat všechny pojištěné");
        System.out.println("3 - Vyhledat pojištěného");
        System.out.println("4 - Konec");
    }

    // Přidání nového pojištěného podle vstupu uživatele
    private static void pridejPojisteneho() {
        String jmeno = nactiNeprazdnyText("Zadejte jméno: ");
        String prijmeni = nactiNeprazdnyText("Zadejte příjmení: ");
        int vek = nactiCislo("Zadejte věk: ");
        String telefon = nactiNeprazdnyText("Zadejte telefon: ");

        Pojisteny p = new Pojisteny(jmeno, prijmeni, vek, telefon);
        evidence.pridejPojisteneho(p);
        System.out.println("Pojištěný byl uložen.");
    }

    // Vypíše všechny pojištěné
    private static void vypisPojistene() {
        List<Pojisteny> vsichni = evidence.getVsechny();
        if (vsichni.isEmpty()) {
            System.out.println("Žádní pojištění nejsou evidováni.");
        } else {
            for (Pojisteny p : vsichni) {
                System.out.println(p);
            }
        }
    }

    // Vyhledá pojištěného podle jména a příjmení
    private static void vyhledejPojisteneho() {
        String jmeno = nactiNeprazdnyText("Zadejte jméno: ");
        String prijmeni = nactiNeprazdnyText("Zadejte příjmení: ");
        List<Pojisteny> nalezeni = evidence.vyhledej(jmeno, prijmeni);

        if (nalezeni.isEmpty()) {
            System.out.println("Pojištěný nebyl nalezen.");
        } else {
            for (Pojisteny p : nalezeni) {
                System.out.println(p);
            }
        }
    }

    // Načte textový vstup a kontroluje, že není prázdný
    private static String nactiNeprazdnyText(String vyzva) {
        String vstup;
        do {
            System.out.print(vyzva);
            vstup = sc.nextLine().trim();
            if (vstup.isEmpty()) {
                System.out.println("Tento údaj nesmí být prázdný.");
            }
        } while (vstup.isEmpty());
        return vstup;
    }

    // Načte číselný vstup a ověří jeho platnost
    private static int nactiCislo(String vyzva) {
        while (true) {
            System.out.print(vyzva);
            String vstup = sc.nextLine();
            try {
                return Integer.parseInt(vstup);
            } catch (NumberFormatException e) {
                System.out.println("Zadejte platné číslo.");
            }
        }
    }
}
