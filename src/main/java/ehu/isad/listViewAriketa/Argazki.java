package ehu.isad.listViewAriketa;

public class Argazki {

    private String izena;
    private String irudia;

    @Override
    public String toString() {
        return izena;
    }

    public Argazki(String iz, String ir){
        izena = iz;
        irudia = ir;
    }

    public String getIzena() {
        return izena;
    }

    public String getIrudia() {
        return irudia;
    }
}
