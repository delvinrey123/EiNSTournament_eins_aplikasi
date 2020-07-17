package com.delvinstudio.einstournament.Model;

public class List_Turnamen {
    private String NamaTurnamen;
    private String Image;

    public List_Turnamen() {
    }

    public List_Turnamen(String namaTurnamen, String image) {
        NamaTurnamen = namaTurnamen;
        Image = image;
    }

    public String getNamaTurnamen() {
        return NamaTurnamen;
    }

    public void setNamaTurnamen(String namaTurnamen) {
        NamaTurnamen = namaTurnamen;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
