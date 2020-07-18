package com.delvinstudio.einstournament.Model;

public class ModelTurnamenMl {
    private String namaTurnamen;
    private String image;

    public ModelTurnamenMl() {
    }

    public ModelTurnamenMl(String namaTurnamen, String image) {
        this.namaTurnamen = namaTurnamen;
        this.image = image;
    }

    public String getNamaTurnamen() {
        return namaTurnamen;
    }

    public void setNamaTurnamen(String namaTurnamen) {
        this.namaTurnamen = namaTurnamen;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
