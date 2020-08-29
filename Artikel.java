/**
 * Klasse Artikel für den Zweck der einfachen Bestandsführung
 *
 * @author Kiran & Stelz
 * @version 1.0
 */
public class Artikel {
    /**
     * Klassenkonstanten
     */
    private static final int MIN_BESTAND		= 0;
    private static final int MIN_ARTIKELNR		= 1000;
    private static final int MAX_ARTIKELNR		= 9999;
    private static final int MIN_ZUGANG			= 1;
    private static final int MIN_ABGANG			= 1;
    private static final double MIN_PREIS		= 0.01;
    private static final double MIN_PROZENT		= 0.0;
    private static final String ARTIKELNUMMER =
        "Artikelnummer ist ungültig, da die Eingabe nicht 4-stellig und positiv ist!";
    private static final String ARTIKELBEZEICHNUNG =
        "Artikelbezeichnung ist ungültig, da die Eingabe leer ist!";
    private static final String ARTIKELBESTAND =
        "Artikelbestand ist ungültig, da die Eingabe < 0 ist!";
    private static final String ZUGANG_NEGATIV =
        "Die eingegebene Menge ist ungültig, da sie <= 0 ist!";
    private static final String ABGANG_NEGATIV =
        "Die eingegebene Menge ist ungültig, da sie <= 0 ist!";
    private static final String ABGANG_ZU_HOCH =
        "Der Abgang ist groeßer als die Groeße des Bestandes!";
    private static final String PREIS_ZU_NIEDRIG =
        "Der angegebene Preis muss mind. " + MIN_PREIS + "sein!";
    private static final String PROZENT_ZU_NIEDRIG =
        "Die uebergebene Prozentzahl muss mind. " + MIN_PROZENT + "sein!";

    /**
     * Attribute
     */
    private int     artikelNr;
    private String  bezeichnung;
    private int     bestand;
    private double	preis;

    /**
     * Erster Konstruktor mit 4 Parametern
     * 
     * @param artikelNr darf nicht < 1000 || > 9999 sein
     * @param bezeichnung darf nicht leer sein
     * @param bestand muss >= 0 sein
     * @param preis muss >= 0.0 sein
     */
    public Artikel(int artikelNr, String bezeichnung, int bestand, double preis) {
        if(artikelNr < MIN_ARTIKELNR || artikelNr > MAX_ARTIKELNR) {
            throw new IllegalArgumentException(ARTIKELNUMMER);
        }

        if(bezeichnung == null || bezeichnung.trim().isEmpty()) {
            throw new IllegalArgumentException(ARTIKELBEZEICHNUNG);
        }

        if(bestand < MIN_BESTAND) {
            throw new IllegalArgumentException(ARTIKELBESTAND);
        }

        if(preis < MIN_PREIS) {
            throw new IllegalArgumentException(PREIS_ZU_NIEDRIG);
        }

        this.artikelNr 	    = artikelNr;
        this.bezeichnung    = bezeichnung.trim();
        this.bestand 	    = bestand;
        this.preis			= Math.round(100.0 * preis) / 100.0;
    }

    /**
     * Zweiter Konstruktor mit 2 Parametern
     * 
     * @param artikelNr  darf nicht < 1000 || > 9999 sein
     * @param bezeichnung  darf nicht leer sein
     */
    public Artikel(int artikelNr, String bezeichnung) {
        this(artikelNr, bezeichnung, MIN_BESTAND, MIN_PREIS);
    }

    /**
     * Gibt die Artikelnummer zurück
     * 
     * @return Artikelnummer
     */
    public int getArtikelNr() {
        return artikelNr;
    }

    /**
     * Gibt die Artikelbezeichnung zurück
     * 
     * @return Artikelbezeichnung
     */
    public String getBezeichnung() {
        return bezeichnung;
    }

    /**
     * Gibt den Artikelbestand zurück
     * 
     * @return Artikelbestand
     */
    public int getBestand() {
        return bestand;
    }

    /**
     * Gibt den ArtikelPreis zurück
     * 
     * @return Artikelpreis
     */
    public double getPreis() {
        return preis;
    }

    /**
     * Legt die Artikelbezeichnung fest
     * 
     * @param bezeichnung
     */
    public void setBezeichnung(String bezeichnung) {
        if(bezeichnung == null || bezeichnung.trim().isEmpty()) {
            throw new IllegalArgumentException(ARTIKELBEZEICHNUNG);
        }

        this.bezeichnung = bezeichnung.trim();
    }

    /**
     * Legt den Artikelbestand fest
     * 
     * @param bestand
     */
    public void setBestand(int bestand) {
        if(bestand < MIN_BESTAND) {
            throw new IllegalArgumentException(ARTIKELBESTAND);
        }

        this.bestand = bestand;
    }

    /**
     * Legt den Artikelpreis fest
     * 
     * @param neuerPreis
     */
    public void setPreis(double neuerPreis) {
        if(neuerPreis < MIN_PREIS) {
            throw new IllegalArgumentException(PREIS_ZU_NIEDRIG);
        }

        preis = neuerPreis;
    }

    /**
     * Methode bucheZugang: bucht den Zugang und erhöht den Bestand
     * 
     * @param Menge darf nicht <= 0 sein
     */
    public void bucheZugang(int zugang) {
        if(zugang < MIN_ZUGANG) {
            throw new IllegalArgumentException(ZUGANG_NEGATIV);
        }

        bestand += zugang;
    }

    /**
     * Methode buche Abgang: bucht den Abgang und verringert den Bestand max. bis 0
     * 
     * @param menge darf nicht <= 0 oder größer als der Bestand sein
     */
    public void bucheAbgang(int abgang) {
        if(abgang < MIN_ABGANG) {
            throw new IllegalArgumentException(ABGANG_NEGATIV);
        }

        if(bestand - abgang < MIN_BESTAND) {
            throw new IllegalArgumentException(ABGANG_ZU_HOCH);
        }

        bestand -= abgang;
    }

    /**
     * Diese Methode aendert den Artikelpreis auf einen eigegeben Prozentwert
     * 
     * @param prozent darf nicht kleiner sein als -100,00%
     */
    public void setProzent(double prozent) {
        if(prozent < MIN_PROZENT) {
            throw new IllegalArgumentException(PROZENT_ZU_NIEDRIG);
        }

        preis -= preis * (100.0 - prozent) / 100.0;
    }

    /**
     * toString Methode, um ein Artikel-Objekt als Zeichenkette aufbereiten zu können
     */
    public String toString() {
        return("Artikel: " + artikelNr +
            "\t\tBezeichnung: " + bezeichnung +
            "\t\tBestand: " + bestand +
            "\t\tPreis: " + preis + "€" + " pro " + bezeichnung);
    }
}
