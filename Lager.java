/**
 * Klasse Lager für den Zweck der einfachen Bestandsführung
 * 
 * @author Kiran & Stelz
 * @version 1.0
 */
public class Lager {
	/**
	 * Klassenkonstanten
	 */
	private final int STANDARD_LAGER_GROESSE = 10;
    private static final int MIN_GROESSE = 1;
    private static final String LAGER_GROESSE_UNGUELTIG =
        "Die Kapazitaet des Lagers muss mind. 1 sein!";
    private static final String ARTIKEL_SCHON_ANGELEGT =
        "Dieser Artikel wurde bereits angelegt!";
    private static final String LAGER_VOLL =
        "Das Lager ist bereits voll!";
    private static final String ARTIKEL_NICHT_VORHANDEN =
        "Gesuchter Artikel ist nicht im Lager!";
    private static final String INDEX_UNGUELTIG =
        "Der Index ist zu gross oder zu klein!";

    /**
     * Attribute
     */
    private Artikel[] lager;
    int letzterBesetzterIndex;

    /**
     * Konstruktor zum Initialisieren der Kapazitaet
     * @param dimension
     */
    public Lager(int dimension) {
        if(dimension < MIN_DIMENSION) {
            throw new IllegalArgumentException(DIMENSION_UNGUELTIG);
        }

        lager = new Artikel[dimension];
        letzterBesetzterIndex = -1;

        for(int i = 0; i < dimension; i++) {
            lager[i] = null;
        }
    }

    /**
     * Konstruktor mit der Lagergroeße 10
     */
    public Lager() {
        this(DIMENSION_10);
    }

    /**
     * Legt einen neuen Artikel im Lager an
     * 
     * @param artikel Es darf keinen neuer Artikel angelegt werden, 
     * wenn die selbe Artikelnummer schon vergeben ist und 
     * wenn der Lager schon voll ist
     */
    public void legeAnArtikel(Artikel artikel) {
        if(sucheArtikel(artikel.getArtikelNr()) != -1) {
            throw new IllegalArgumentException(ARTIKEL_SCHON_ANGELEGT);
        }

        if(letzterBesetzterIndex >= lager.length - 1) {
            throw new IllegalArgumentException(LAGER_VOLL);
        }

        lager[++letzterBesetzterIndex] = artikel;
    }

    /**
     * Entfernt einen bereits angelegten Artikel
     * 
     * @param artikelNr Es muss mind. ein Artikel schon angelegt sein
     */
    public void entferneArtikel(int artikelNr) {
        int artikelOrt;
        int schieber;

        artikelOrt = sucheArtikel(artikelNr);

        if(artikelOrt == -1) {
            throw new IllegalArgumentException(ARTIKEL_NICHT_VORHANDEN);
        }

        lager[artikelOrt] = null;
        letzterBesetzterIndex--;

        for(schieber = artikelOrt; schieber <= letzterBesetzterIndex; schieber++) {
            lager[schieber] = lager[schieber + 1];
        }

        if(schieber + 1 < lager.length) {
            lager[schieber + 1] = null;
        }
    }

    /**
     * Bucht Zugang der Menge eines im Lager angelegten Artikels
     * 
     * @param artikelNr Es muss mind. ein Artikel schon angelegt sein
     * @param menge Die Menge muss mind. 1 sein
     */
    public void bucheZugang(int artikelNr, int menge) {
        int artikelIndex = sucheArtikel(artikelNr);

        if(artikelIndex == -1) {
            throw new IllegalArgumentException(ARTIKEL_NICHT_VORHANDEN);
        }

        lager[artikelIndex].bucheZugang(menge);
    }

    /**
     * Bucht Abgang der Menge eines im Lager angelegten Artikels
     * 
     * @param artikelNr Es muss mind. ein Artikel schon angelegt sein
     * @param menge Die Menge muss mind. 1 sein und nicht groesser als der Bestand sein
     */
    public void bucheAbgang(int artikelNr, int menge) {
        int artikelIndex = sucheArtikel(artikelNr);

        if(artikelIndex == -1) {
            throw new IllegalArgumentException(ARTIKEL_NICHT_VORHANDEN);
        }

        lager[artikelIndex].bucheAbgang(menge);
    }

    /**
     * Aendert die Preise aller angelegten Artikel auf einen
     * eingegebenen Prozentwert
     * 
     * @param prozent Darf mind. 0,00% sein
     */
    public void aenderePreisAllerArtikel(double prozent) {
        for(int i = 0; i <= letzterBesetzterIndex; i++) {
            lager[i].setProzent(prozent);
        }
    }

    /**
     * Hilfsmethode zum Suchen eines Artikels im Lager
     * 
     * @param sucheArtikelNr Der gesuchte Artikel muss existieren
     * @return Der Platz des Artikels im Lager
     */
    public int sucheArtikel(int sucheArtikelNr) {
        int i;
        int gefunden;

        for(i = 0, gefunden = -1; i <= letzterBesetzterIndex && gefunden == -1; i++) {
            if(lager[i].getArtikelNr() == sucheArtikelNr) {
                gefunden = i;
            }
        }

        return gefunden;
    }

    /**
     * Gibt die Anzahl der bereits angelegten Artikel zurück
     * 
     * @return Artikelindex
     */
    public int getArtikelAnzahl() {
        return letzterBesetzterIndex + 1;
    }

    /**
     * Gibt die Groesse des Lagers zurück
     * 
     * @return Lagergroesse
     */
    public int getLagerGroesse() {
        return lager.length;
    }

    /**
     * Teilt einem Artikel einen Indexwert zu
     * 
     * @param index Der Index eines Artikels muss groesser als 0 sein
     * @return Indexwert des Artikel im Lager
     */
    public Artikel getArtikel(int index) {
        if(index >= getLagerGroesse() || index < 0) {
            throw new IllegalArgumentException(INDEX_UNGUELTIG);
        }

        return lager[index];
    }

    /**
     * toString-Methode, um ein Lager-Objekt als Zeichenkette aufbereiten zu können
     */
    public String toString() {
        String lagerString = new String(
                "Im Lager sind von " + lager.length + " Lagerplaetzen " + (letzterBesetzterIndex + 1) +
                " mit den folgenden Artikeln belegt:\n");

        for(int i = 0; i <= letzterBesetzterIndex; i++) {
            lagerString += ((i + 1) + ".\t" + lager[i] + "\n");
        }

        return lagerString;
    }
}