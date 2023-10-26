package org.example;

public class Konto {
    public static int nextidKonto = 0;

    private final int id;
    private int kr = 0;
    private final String owner;
    private final String code;

    public Konto(String owner,String code)
    {
        this.id = nextidKonto;
        this.code = code;
        this.owner = owner;
        nextidKonto++;
    }

    public int getId() {
        return id;
    }

    public int getKr() {
        return kr;
    }

    public void setKr(int kr) {
        this.kr = kr;
    }

    public String getOwner() {
        return owner;
    }

    public String getCode() {
        return code;
    }

}
