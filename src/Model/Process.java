package Model;

import java.time.LocalDate;

public class Process{
    private String id;
    private int dlFazy;
    private int momZglosz;
    private int czasOczek;
    private int pozostalyCzas;

    public Process(String id, int dlFazy, int momZglosz) {
        this.id = id;
        this.dlFazy = dlFazy;
        this.momZglosz = momZglosz;
        this.czasOczek = 0;
        this.pozostalyCzas = dlFazy;
    }

    public static Process parse(String s) {
        String[] values = s.split(",");
        String id = values[0];
        int dlFazy = Integer.parseInt(values[1]);
        int momZglosz = Integer.parseInt(values[2]);
        return new Process(id, dlFazy, momZglosz);

    }

    @Override
    public String toString() {
        //return id + ":" + czasOczek;
        return id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDlFazy() {
        return dlFazy;
    }

    public void setDlFazy(int dlFazy) {
        this.dlFazy = dlFazy;
    }

    public int getMomZglosz() {
        return momZglosz;
    }

    public void setMomZglosz(int momZglosz) {
        this.momZglosz = momZglosz;
    }

    public int getCzasOczek() {
        return czasOczek;
    }

    public void setCzasOczek(int czasOczek) {
        this.czasOczek = czasOczek;
    }

    public int getPozostalyCzas() {
        return pozostalyCzas;
    }

    public void setPozostalyCzas(int pozostalyCzas) {
        this.pozostalyCzas = pozostalyCzas;
    }
}
