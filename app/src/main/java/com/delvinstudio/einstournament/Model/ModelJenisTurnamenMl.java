package com.delvinstudio.einstournament.Model;

public class ModelJenisTurnamenMl {
    private String namaTurnamen, imageTurnamen, hargaTurnamen, authorTurnamen,
                    deskripsiTurnamen, tanggalTurnamen;

    public ModelJenisTurnamenMl() {
    }

    public ModelJenisTurnamenMl(String namaTurnamen, String imageTurnamen, String hargaTurnamen, String authorTurnamen, String deskripsiTurnamen, String tanggalTurnamen) {
        this.namaTurnamen = namaTurnamen;
        this.imageTurnamen = imageTurnamen;
        this.hargaTurnamen = hargaTurnamen;
        this.authorTurnamen = authorTurnamen;
        this.deskripsiTurnamen = deskripsiTurnamen;
        this.tanggalTurnamen = tanggalTurnamen;
    }

    public String getNamaTurnamen() {
        return namaTurnamen;
    }

    public void setNamaTurnamen(String namaTurnamen) {
        this.namaTurnamen = namaTurnamen;
    }

    public String getImageTurnamen() {
        return imageTurnamen;
    }

    public void setImageTurnamen(String imageTurnamen) {
        this.imageTurnamen = imageTurnamen;
    }

    public String getHargaTurnamen() {
        return hargaTurnamen;
    }

    public void setHargaTurnamen(String hargaTurnamen) {
        this.hargaTurnamen = hargaTurnamen;
    }

    public String getAuthorTurnamen() {
        return authorTurnamen;
    }

    public void setAuthorTurnamen(String authorTurnamen) {
        this.authorTurnamen = authorTurnamen;
    }

    public String getDeskripsiTurnamen() {
        return deskripsiTurnamen;
    }

    public void setDeskripsiTurnamen(String deskripsiTurnamen) {
        this.deskripsiTurnamen = deskripsiTurnamen;
    }

    public String getTanggalTurnamen() {
        return tanggalTurnamen;
    }

    public void setTanggalTurnamen(String tanggalTurnamen) {
        this.tanggalTurnamen = tanggalTurnamen;
    }
}
