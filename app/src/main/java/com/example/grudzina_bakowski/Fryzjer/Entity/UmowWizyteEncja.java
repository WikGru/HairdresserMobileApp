package com.example.grudzina_bakowski.Fryzjer.Entity;

public class UmowWizyteEncja {


    private static UmowWizyteEncja umowWizyteEncja = null;
    private String service;
    private String date;
    private String name;
    private String timeBeg;


    private String timeEnd;
    private String priceRange;

    private UmowWizyteEncja() {

    }

    public static UmowWizyteEncja getUmowWizyteEncja() {
        if (umowWizyteEncja == null) {
            umowWizyteEncja = new UmowWizyteEncja();
        }
        return umowWizyteEncja;
    }

    public String getService() {
        return service;
    }

    public void setService(String rodzaj) {
        this.service = rodzaj;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeBeg() {
        return timeBeg;
    }

    public void setTimeBeg(String timeBeg) {
        this.timeBeg = timeBeg;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }


    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String przedzial_cenowy) {
        this.priceRange = przedzial_cenowy;
    }
}
