package com.delvinstudio.einstournament.pref;

public class UserHelperClass {

    String username, password, as, nama, squad, nowa;

    public UserHelperClass(String username, String password, String as, String nama, String squad, String nowa) {
        this.username = username;
        this.password = password;
        this.as = as;
        this.nama = nama;
        this.squad = squad;
        this.nowa = nowa;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAs() {
        return as;
    }

    public void setAs(String as) {
        this.as = as;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSquad() {
        return squad;
    }

    public void setSquad(String squad) {
        this.squad = squad;
    }

    public String getNowa() {
        return nowa;
    }

    public void setNowa(String nowa) {
        this.nowa = nowa;
    }
}
