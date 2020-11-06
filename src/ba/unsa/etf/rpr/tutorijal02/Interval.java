package ba.unsa.etf.rpr.tutorijal02;

import static java.lang.Math.*;

public class Interval {
    private double pocetna;
    private double krajnja;
    private boolean pripadaPocetna;
    private boolean pripadaKrajnja;

    public Interval(double pocetna, double krajnja, boolean pripadaPocetna, boolean pripadaKrajnja) {
        if (pocetna > krajnja) throw new IllegalArgumentException("Početna tačka veća od krajnje");
        this.pocetna = pocetna;
        this.krajnja = krajnja;
        this.pripadaPocetna = pripadaPocetna;
        this.pripadaKrajnja = pripadaKrajnja;
    }

    public Interval() {
        pocetna = 0;
        krajnja = 0;
        pripadaPocetna = false;
        pripadaKrajnja = false;
    }

    public double getPocetna() {
        return pocetna;
    }

    public double getKrajnja() {
        return krajnja;
    }

    public boolean isPripadaPocetna() {
        return pripadaPocetna;
    }

    public boolean isPripadaKrajnja() {
        return pripadaKrajnja;
    }

    public boolean isNull() {
        return (pocetna == 0 && krajnja == 0 && !pripadaPocetna && !pripadaKrajnja);
    }


    @Override
    public String toString() {
        if (pocetna == 0 && krajnja == 0) return "()";
        if (pripadaPocetna && pripadaKrajnja)
            return ("[" + pocetna + "," + krajnja + "]");
        else if (pripadaPocetna && !pripadaKrajnja)
            return ("[" + pocetna + "," + krajnja + ")");
        else if (!pripadaPocetna && pripadaKrajnja)
            return ("(" + pocetna + "," + krajnja + "]");
        else
            return ("(" + pocetna + "," + krajnja + ")");
    }

    @Override
    public boolean equals(Object obj) {
        Interval i = (Interval) obj;
        return (pocetna == i.getPocetna() && krajnja == i.getKrajnja() &&
                pripadaPocetna == i.isPripadaPocetna() && pripadaKrajnja == i.isPripadaKrajnja());
    }

    public boolean isIn(double tacka) {
        if (tacka >= pocetna && tacka <= krajnja) {
            if (tacka == pocetna && isPripadaPocetna()) return true;
            else if (tacka == krajnja && isPripadaKrajnja()) return true;
            else if (tacka > pocetna && tacka < krajnja) return true;
        }
        return false;
    }

    public Interval intersect(Interval i) {
        double x = max(pocetna, i.getPocetna());
        double y = min(krajnja, i.getKrajnja());
        boolean pripada_pocetna = false;
        boolean pripada_krajnja = false;
        if ((x == pocetna && isPripadaPocetna()) || (x == i.getPocetna() && i.isPripadaPocetna()))
            pripada_pocetna = true;
        if ((y == krajnja && isPripadaKrajnja()) || (y == i.getKrajnja() && i.isPripadaKrajnja()))
            pripada_krajnja = true;
        return new Interval(x, y, pripada_pocetna, pripada_krajnja);
    }

    public static Interval intersect(Interval i1, Interval i2) {
        double x = max(i1.getPocetna(), i2.getPocetna());
        double y = min(i1.getKrajnja(), i2.getKrajnja());
        boolean pripada_pocetna = false;
        boolean pripada_krajnja = false;
        if ((x == i1.getPocetna() && i1.isPripadaPocetna()) || (x == i2.getPocetna() && i2.isPripadaPocetna()))
            pripada_pocetna = true;
        if ((y == i1.getKrajnja() && i1.isPripadaKrajnja()) || (y == i2.getKrajnja() && i2.isPripadaKrajnja()))
            pripada_krajnja = true;
        return new Interval(x, y, pripada_pocetna, pripada_krajnja);
    }
}
