package com.example.pomniki;

public class Pomniki {
    String nazwa;
    String rodzaj;
    String miasto;
    Double latitude;
    Double longitude;

    public Pomniki(){

    }

    public Pomniki(String nazwa, String rodzaj, String miasto, Double latitude, Double longitude){
        this.nazwa = nazwa;
        this.rodzaj = rodzaj;
        this.miasto = miasto;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getNazwa(){
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getRodzaj(){
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    public String getMiasto(){
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public Double getLatitude(){
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude(){
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
