package com.delvinstudio.einstournament.Model;

public class User {
    private String Username;
    private String Nama;
    private String Password;
    private String Squad;
    private String Nowa;

    public User() {
    }

    public User(String username, String nama, String password, String squad, String nowa) {
        Username = username;
        Nama = nama;
        Password = password;
        Squad = squad;
        Nowa = nowa;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getSquad() {
        return Squad;
    }

    public void setSquad(String squad) {
        Squad = squad;
    }

    public String getNowa() {
        return Nowa;
    }

    public void setNowa(String nowa) {
        Nowa = nowa;
    }
}