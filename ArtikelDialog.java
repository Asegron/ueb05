                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            import java.util.*;

/**
 * Die Dialogklasse für die Klasse Artikel um mit dem Antwender zu kommunizieren.
 * 
 * @author Kiran & Stelz
 * @version 1.0
 */
public class ArtikelDialog {
    /**
     * Attribute
     */
    private Artikel artikel1;
    private Scanner in;

    /**
     * Klassenkonstanten
     */
    private static final int ANLEGEN            = 1;
    private static final int ZUGANG             = 2;
    private static final int ABGANG             = 3;
    private static final int SET_BEZEICHNUNG    = 4;
    private static final int SET_BESTAND        = 5;
    private static final int SET_PREIS          = 6;
    private static final int SET_PROZENT        = 7;
    private static final int ENDE               = 0;
    private static final String ARTIKEL_NICHT_VORHANDEN =
        "Es wurde noch kein Artikel angelegt!";
    private static final String ARTIKEL_LOESCHEN =
        "Erst einen Artikel loeschen!";
    private static final String FALSCHE_EINGABE =
        "Falsche Eingabe!";

    /**
     * Konstruktor inklusive Scanner
     */
    public ArtikelDialog() {
        in = new Scanner(System.in);
    }

    /**
     * Hauptschleife des Testprogramms
     */
    public void start() {
        artikel1 = null;
        int funktion = -1;

        while(funktion != ENDE) {
            try {
                funktion = einlesenFunktion();
                ausfuehrenFunktion(funktion);
            } catch(IllegalArgumentException e) {
                System.out.println(e);
            } catch(InputMismatchException e) {
                System.out.println(e);
                in.nextLine();
            } catch(Exception e) {
                System.out.println(e);
                e.printStackTrace(System.out);
            }
            if(artikel1 != null) {
                System.out.println("Der aktuelle Zustand des Artikels: \n" + artikel1);
            }
        }
    }

    /**
     * Main-Methode zum Erzeugen des ArtikelDialog-Objektes
     * und zum Anstarten der Testschleife
     */
    public static void main(String[] args) {
        new ArtikelDialog().start();
    }

    /**
     * Mit dieser Methode wird die Auswahl an Funktionen angezeigt
     * 
     * @return auszuführende Funktion
     */
    private int einlesenFunktion() {
        System.out.print(
            ANLEGEN         + ": Anlegen \n" +
            ZUGANG          + ": Zugang \n" +
            ABGANG          + ": Abgang  \n" +
            SET_BEZEICHNUNG + ": Neue Bezeichnung  \n" +
            SET_BESTAND     + ": Neuer Bestand \n" +
            SET_PREIS       + ": Neuer Preis \n" +
            SET_PROZENT     + ": Neuer Preis nach Änderung auf einen eingegeben Prozentwert \n" +
            ENDE            + ": Beenden ");

        return in.nextInt();
    }

    /**
     * Die ausgewählte Funktion wird mit dieser Methode ausgeführt
     * 
     * @param funktion
     */
    private void ausfuehrenFunktion(int funktion) {
        if(funktion == ANLEGEN) {
            artikel1 = anlegenArtikel();

        } else if(funktion == ZUGANG) {
            artikel1.bucheZugang(einlesenZugang());

        } else if(funktion == ABGANG) {
            artikel1.bucheAbgang(einlesenAbgang());

        } else if(funktion == SET_BEZEICHNUNG) {
            artikel1.setBezeichnung(einlesenBezeichnung());

        } else if(funktion == SET_BESTAND) {
            artikel1.setBestand(einlesenBestand());

        } else if(funktion == SET_PREIS) {
            artikel1.setPreis(einlesenPreis());

        } else if(funktion == SET_PROZENT) {
            artikel1.setProzent(einlesenProzent());

        } else if(funktion == ENDE) {
            System.out.println("Programmende!");

        } else {
            System.out.println(FALSCHE_EINGABE);
        }
    }

    /**
     * Methode, um ein Artikel anzulegen
     * 
     * @return angelegter Artikel
     */
    private Artikel anlegenArtikel() {
        int artikelNr;
        String bezeichnung;
        int bestand;
        double preis;

        if(artikel1 != null) {
            throw new IllegalArgumentException(ARTIKEL_LOESCHEN);
        } else {
            System.out.println("ArtikelNr: ");
            artikelNr = in.nextInt();
            System.out.println("Bezeichnung: ");
            bezeichnung = in.next();
            System.out.println("Bestand: ");
            bestand = in.nextInt();
            System.out.println("Preis: ");
            preis = in.nextDouble();

            return new Artikel(artikelNr, bezeichnung, bestand, preis);
        }
    }

    /**
     * Zugang einlesen und buchen
     * 
     * @return Ergebnis
     */
    private int einlesenZugang() {
        int zugang;

        if(artikel1 == null) {
            throw new RuntimeException(ARTIKEL_NICHT_VORHANDEN);
        }

        System.out.println("Zugang: ");
        zugang = in.nextInt();
        return zugang;
    }

    /**
     * Abgang einlesen und ausführen
     * 
     * @return Ergebnis
     */
    private int einlesenAbgang() {
        int abgang;

        if(artikel1 == null) {
            throw new RuntimeException(ARTIKEL_NICHT_VORHANDEN);
        }

        System.out.println("Abgang: ");
        abgang = in.nextInt();
        return abgang;
    }

    /**
     * Neue Bezeichnung für ein bereits angelegten Artikel anlegen
     * 
     * @return neue Bezeichnung
     */
    private String einlesenBezeichnung() {
        String bezeichnung;

        if(artikel1 == null) {
            throw new RuntimeException(ARTIKEL_NICHT_VORHANDEN);
        }

        System.out.println("Neue Bezeichnung: ");
        bezeichnung = in.next();
        return bezeichnung;
    }

    /**
     * Neuen Bestand für ein bereits angelegten Artikel anlegen
     * 
     * @return neuer Bestand
     */
    private int einlesenBestand() {
        int bestand;

        if(artikel1 == null) {
            throw new RuntimeException(ARTIKEL_NICHT_VORHANDEN);
        }

        System.out.println("Neuer Bestand: ");
        bestand = in.nextInt();
        return bestand;
    }

    /**
     * Neuen Preis für einen bereits angelegten Artikel anlegen
     * 
     * @return neuer Preis
     */
    private double einlesenPreis() {
        double preis;

        if(artikel1 == null) {
            throw new IllegalArgumentException(ARTIKEL_NICHT_VORHANDEN);
        }

        System.out.println("Neuer Preis: ");
        preis = in.nextDouble();
        return preis;
    }

    /**
     * 
     * @return neuer Preis nach Änderung auf einen eingegebenen Prozentwert
     */
    private double einlesenProzent() {
        double preis;

        if(artikel1 == null) {
            throw new IllegalArgumentException(ARTIKEL_NICHT_VORHANDEN);
        }

        System.out.println("Auf welchen Prozentwert?: ");
        preis = in.nextDouble();
        return preis;
    }
}