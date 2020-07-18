package com.delvinstudio.einstournament.Model;

public class ModelTurnamenMl {
    private String namaTurnamen, image;

    public ModelTurnamenMl(String namaTurnamen, String image) {
        this.namaTurnamen = namaTurnamen;
        this.image = image;
    }

    public ModelTurnamenMl() {
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
