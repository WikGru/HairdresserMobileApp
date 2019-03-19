package com.example.grudzina_bakowski.Fryzjer.Database;

public  class ConnectionName {
     private String COLLECTION_NAME_PRICE="//" ;  //tabela ceny
     private String COLLECTION_NAME_USER="//"; // tabela użytkownicy
    private String COLLECTION_NAME_ADMIN="//"; // tabela użytkownicy
    private String COLLECTION_NAME_ZAPIS_WIZYTY="//";
    private String COLLECTION_NAME_USUN_WIZYTE="//";

    public String getCOLLECTION_NAME_USUN_WIZYTE() {
        return COLLECTION_NAME_USUN_WIZYTE;
    }

    public String getCOLLECTION_NAME_ZAPIS_WIZYTY() {
        return COLLECTION_NAME_ZAPIS_WIZYTY;
    }

    public String getCOLLECTION_NAME_ADMIN() {
        return COLLECTION_NAME_ADMIN;
    }

    public String getCOLLECTION_NAME_PRICE() {
        return COLLECTION_NAME_PRICE;
    }

    public String getCOLLECTION_NAME_USER() {
        return COLLECTION_NAME_USER;
    }


}
