package com.didapteam.project.education;

import java.io.Serializable;

public class Users implements Serializable {
    private String nama;
    private String email;
    private String password;
    private String bidang;
    private String key;
    private String daerah;
    private String pilihan;
    private Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getPilihan() {
        return pilihan;
    }

    public void setPilihan(String pilihan) {
        this.pilihan = pilihan;
    }

    public Users(){

    }

    public Users(String nm, String em, String pass, String bid, String da, String pil, Boolean st){
        nama = nm;
        email = em;
        password = pass;
        bidang = bid;
        daerah = da;
        pilihan = pil;
        status = st;
    }

    public String getNama(){
        return nama;
    }

    public void  setNama(String nama){
        this.nama = nama;
    }

    public String getEmail(){
        return email;
    }

    public void  setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
         return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getBidang(){
        return bidang;
    }

    public void setBidang(String bidang) {
        this.bidang = bidang;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
