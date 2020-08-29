import java.util.*;

/**
 * Die Dialogklasse für die Klasse Lager um mit dem Antwender zu kommunizieren.
 * 
 * @author Kiran & Stelz
 * @version 1.0
 */
public class LagerDialog {
    /**
     * Attribute
     */
    private Lager lager1;
    private Scanner in;

    /**
     * Klassenkonstanten
     */
    private static final int ERZEUGEN           = 1;
    private static final int ERZEUGEN_VORGABE   = 2;
    private static final int AUFLOESEN          = 3;
    private static final int ANLEGEN            = 4;
    private static final int ENTFERNEN          = 5;
    private static final int ZUGANG             = 6;
    private static final int ABGANG             = 7;
    private static final int PREISE_AENDERN     = 8;
    private static final int ANZAHL_GROESSE     = 9;
    private static final int ENDE               = 0;
    private static final String LAGER_AUFLOESEN =
        "Vorhandenes Lager erst aufloesen!";
    private static final String LAGER_ERZEUGEN =
        "Erst ein Lager erzeugen!";
    private static final String ARTIKEL_ANLEGEN =
        "Erst einen Artikel anlegen!";

    /**
     * Konstruktor mit dem Scanner
     */
    public LagerDialog() {
        lager1 = null;
        in = new Scanner(System.in);
    }

    /**
     * Hauptschleife des Testprogramms
     */
    public void start() {
        lager1 = null;
        int funktion = -1;

        while(funktion != ENDE) {
            try {
                funktion = einlesenFunktion();
                ausfuehrenFunktion(funktion);
            } catch(IllegalArgumentException e) {
                System.out.println(e);
            } catch(NullPointerException e) {
                System.out.println(e);
            } catch(InputMismatchException e) {
                System.out.println(e);
                in.nextLine();
            } catch(Exception e) {
                System.out.println(e);
                e.printStackTrace(System.out);
            }
        }
    }

    /**
     * Main-Methode zum Erzeugen des LagerDialog-Objektes
     * und zum Anstarten der Testschleife
     */
    public static void main(String[] args) {
        new LagerDialog().start();
    }

    /**
     * Mit dieser Methode wird die Auswahl an Funktionen angezeigt
     * 
     * @return auszuführende Funktion
     */
    private int einlesenFunktion() {
        System.out.println(
            ERZEUGEN            + ": Lager mit einer selbst gewählten Kapazitaet erzeugen \n" +
            ERZEUGEN_VORGABE    + ": Lager mit der vorgegebenen Kapazitaet 10 erzeugen \n" +
            AUFLOESEN           + ": Lager aufloesen \n" +
            ANLEGEN             + ": Artikel im Lager anlegen \n" +
            ENTFERNEN           + ": Artikel aus dem Lager entfernen \n" +
            ZUGANG              + ": Zugang buchen \n" +
            ABGANG              + ": Abgang buchen  \n" +
            PREISE_AENDERN      + ": Alle Preise auf einen eingegebenen Prozentwert aendern \n" +
            ANZAHL_GROESSE      + ": Anzahl der Artikel und Kapazitaet des Lagers anzeigen \n" +
            ENDE                + ": Beenden ");

        return in.nextInt();
    }

    /**
     * Die ausgewählte Funktion wird mit dieser Methode ausgeführt
     * 
     * @param funktion Die ausgewählte Funktion
     */
    private void ausfuehrenFunktion(int funktion) {
        if(funktion == ERZEUGEN) {
            int dimension;

            if(lager1 != null) {
                throw new IllegalArgumentException(LAGER_AUFLOESEN);
            } else {
                System.out.println("Geben Sie bitte die Groesse des Lagers ein: ");
                dimension = in.nextInt();
                lager1 = new Lager(dimension);
                System.out.println("Sie haben ein Lager mit der Groesse " +
                    lager1.getLagerGroesse() + " erzeugt.");
            }

        } else if(funktion == ERZEUGEN_VORGABE) {
            if(lager1 != null) {
                throw new IllegalArgumentException(LAGER_AUFLOESEN);
            } else {
                lager1 = new Lager();
                System.out.println("Sie haben ein Lager mit der Groesse " +
                    lager1.getLagerGroesse() + " erzeugt.");
            }

        } else if(funktion == AUFLOESEN) {
            if(lager1 == null) {
                throw new IllegalArgumentException(LAGER_ERZEUGEN);           
            } else {
                lager1 = null;
                System.out.println("Das Lager wurde aufgeloest!");
            }

        } else if(funktion == ANLEGEN) {
            int artikelNr;
            String bezeichnung;
            int bestand;
            double preis;

            if(lager1 == null) {
                throw new IllegalArgumentException(LAGER_ERZEUGEN);
            } else {
                System.out.println("ArtikelNr: ");
                artikelNr = in.nextInt();
                System.out.println("Bezeichnung: ");
                bezeichnung = in.next();
                System.out.println("Bestand: ");
                bestand = in.nextInt();
                System.out.println("Preis: ");
                preis = in.nextDouble();

                lager1.legeAnArtikel(new Artikel(artikelNr, bezeichnung, bestand, preis));
                System.out.println("Der aktuelle Zustand des Lagers: \n " + lager1);
            }

        } else if(funktion == ENTFERNEN) {
            int artikelNr;

            if(lager1 == null) {
                throw new IllegalArgumentException(LAGER_ERZEUGEN);
            } else if(lager1.letzterBesetzterIndex <= 0) {
                throw new IllegalArgumentException(ARTIKEL_ANLEGEN);
            } else {
                System.out.println("Geben Sie die Artikelnummer ein: ");
                artikelNr = in.nextInt();

                lager1.entferneArtikel(artikelNr);
                if(lager1.letzterBesetzterIndex >= 0) {
                    System.out.println("Der aktuelle Zustand des Lagers: \n " + lager1);
                }
            }

        } else if(funktion == ZUGANG) {
            int artikelNr;
            int menge;

            if(lager1 == null) {
                throw new IllegalArgumentException(LAGER_ERZEUGEN);
            } else if(lager1.letzterBesetzterIndex <= 0) {
                throw new IllegalArgumentException(ARTIKEL_ANLEGEN);
            } else {
                System.out.println("Geben Sie die Artikelnummer ein: ");
                artikelNr = in.nextInt();
                System.out.println("Geben Sie die Menge ein: ");
                menge = in.nextInt();

                lager1.bucheZugang(artikelNr, menge);
                System.out.println("Der aktuelle Zustand des Lagers: \n " + lager1);
            }

        } else if(funktion == ABGANG) {
            int artikelNr;
            int menge;

            if(lager1 == null) {
                throw new IllegalArgumentException(LAGER_ERZEUGEN);
            } else if(lager1.letzterBesetzterIndex <= 0) {
                throw new IllegalArgumentException(ARTIKEL_ANLEGEN);
            } else {
                System.out.println("Geben Sie die Artikelnummer ein: ");
                artikelNr = in.nextInt();
                System.out.println("Geben Sie die Menge ein: ");
                menge = in.nextInt();

                lager1.bucheAbgang(artikelNr, menge);
                System.out.println("Der aktuelle Zustand des Lagers: \n " + lager1);
            }

        } else if(funktion == PREISE_AENDERN) {
            double prozent;

            if(lager1 == null) {
                throw new IllegalArgumentException(LAGER_ERZEUGEN);
            } else if(lager1.letzterBesetzterIndex <= 0) {
                throw new IllegalArgumentException(ARTIKEL_ANLEGEN);
            } else {
                System.out.println("Geben Sie die Prozentzahl ein: ");
                prozent = in.nextDouble();
                lager1.aenderePreisAllerArtikel(prozent);
                System.out.println("Der aktuelle Zustand des Lagers: \n " + lager1);
            }

        } else if(funktion == ANZAHL_GROESSE) {
            if(lager1 == null) {
                throw new IllegalArgumentException(LAGER_ERZEUGEN);
            } else {
                System.out.println("Anzahl / Groesse: " +
                    lager1.getArtikelAnzahl() + " / " + lager1.getLagerGroesse());
            }

        } else if(funktion == ENDE) {
            System.out.println("Programmende!");

        } else {
            System.out.println("Falsche Eingabe!");
        }
    }
}