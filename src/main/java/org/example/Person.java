package org.example;

public class Person {
    private int antalenkronor;
    private int antalfemkronor;
    private int antaltior;
    private int antaltjogolapp;
    private int antalfemtiolappar;

    public void generateMoney()
    {
        antalenkronor = getRandomNumber(0,10);
        antalfemkronor = getRandomNumber(0,10);
        antaltior = getRandomNumber(0,10);
        antaltjogolapp = getRandomNumber(0,10);
        antalfemtiolappar = getRandomNumber(0,10);
    }

    public void getMoneyInfo()
    {
        System.out.println("Här är alla sedlar du har på dig.");
        System.out.println("Antal enkronor: " + antalenkronor);
        System.out.println("Antal femkronor: " + antalfemkronor);
        System.out.println("Antal tior: " + antaltior);
        System.out.println("Antal tjogolappar: " + antaltjogolapp);
        System.out.println("Antal femtiolappar: " + antalfemtiolappar);
    }

    public int getRandomNumber(int min, int max)
    {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public int getAntalenkronor() {
        return antalenkronor;
    }

    public void setAntalenkronor(int antalenkronor) {
        this.antalenkronor = antalenkronor;
    }

    public int getAntalfemkronor() {
        return antalfemkronor;
    }

    public void setAntalfemkronor(int antalfemkronor) {
        this.antalfemkronor = antalfemkronor;
    }

    public int getAntaltior() {
        return antaltior;
    }

    public void setAntaltior(int antaltior) {
        this.antaltior = antaltior;
    }

    public int getAntaltjogolapp() {
        return antaltjogolapp;
    }

    public void setAntaltjogolapp(int antaltjogolapp) {
        this.antaltjogolapp = antaltjogolapp;
    }

    public int getAntalfemtiolappar() {
        return antalfemtiolappar;
    }

    public void setAntalfemtiolappar(int antalfemtiolappar) {
        this.antalfemtiolappar = antalfemtiolappar;
    }
}
