package com.example.grudzina_bakowski.Fryzjer.Entity;

public class Wizyty_Obiekt {
    private String imie;
    private String data;
    private String godzina;
    private String rodzaj_uslugi;


    public Wizyty_Obiekt(String imie, String data, String godzina, String rodzaj_uslugi) {
        this.imie = imie;
        this.data = data;
        this.godzina = godzina;
        this.rodzaj_uslugi = rodzaj_uslugi;
    }
    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getGodzina() {
        return godzina;
    }

    public void setGodzina(String godzina) {
        this.godzina = godzina;
    }

    public String getRodzaj_uslugi() {
        return rodzaj_uslugi;
    }

    public void setRodzaj_uslugi(String rodzaj_uslugi) {
        this.rodzaj_uslugi = rodzaj_uslugi;
    }








}
