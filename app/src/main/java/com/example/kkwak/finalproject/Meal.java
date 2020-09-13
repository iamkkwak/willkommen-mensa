package com.example.kkwak.finalproject;

public class Meal {
    String day;
    String gut;
    String gourmet;
    String wok;
    String prima;

    Meal(String day, String gut, String gourmet, String wok, String prima) {
        this.day = day;
        this.gut = gut;
        this.gourmet = gourmet;
        this.wok = wok;
        this.prima = prima;
    }

    String getDay() { return day; }
    String getGut() { return gut; }
    String getGourmet() { return gourmet; }
    String getWok() { return wok; }
    String getPrima() { return prima; }
}
