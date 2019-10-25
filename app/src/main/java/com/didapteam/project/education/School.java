package com.didapteam.project.education;

import java.io.Serializable;

public class School implements Serializable {
    String nama;
    String kota;
    String kebutuhan1;
    String kebutuhan2;
    String statusBos;
    String waktu;
    String dayaListrik;
    String aksesInternet;
    String sumberListrik;
    String karakteristik;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getKebutuhan1() {
        return kebutuhan1;
    }

    public void setKebutuhan1(String kebutuhan1) {
        this.kebutuhan1 = kebutuhan1;
    }

    public String getKebutuhan2() {
        return kebutuhan2;
    }

    public void setKebutuhan2(String kebutuhan2) {
        this.kebutuhan2 = kebutuhan2;
    }

    public String getStatusBos() {
        return statusBos;
    }

    public void setStatusBos(String statusBos) {
        this.statusBos = statusBos;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getDayaListrik() {
        return dayaListrik;
    }

    public void setDayaListrik(String dayaListrik) {
        this.dayaListrik = dayaListrik;
    }

    public String getAksesInternet() {
        return aksesInternet;
    }

    public void setAksesInternet(String aksesInternet) {
        this.aksesInternet = aksesInternet;
    }

    public String getSumberListrik() {
        return sumberListrik;
    }

    public void setSumberListrik(String sumberListrik) {
        this.sumberListrik = sumberListrik;
    }

    public String getKarakteristik() {
        return karakteristik;
    }

    public void setKarakteristik(String karakteristik) {
        this.karakteristik = karakteristik;
    }

}
