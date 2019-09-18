package com.didapteam.project.education;

import java.io.Serializable;

public class Users implements Serializable {
    private String nama;
    private String email;
    private String password;
    private String key;

    public Users(){

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


    public Users(String nm, String em, String pass){
        nama = nm;
        email = em;
        password = pass;
    }

    public String getKey(String key){
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
