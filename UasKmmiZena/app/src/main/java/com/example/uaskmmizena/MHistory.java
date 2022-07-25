package com.example.uaskmmizena;

public class MHistory {
    String surat, ayat, tanggal, nisn, nama_siswa;
    int id_hafalan;

    public MHistory (){

    }

    public MHistory (int id_hafalan, String nama_siswa, String surat, String ayat, String tanggal, String nisn){
        this.id_hafalan = id_hafalan;
        this.nama_siswa = nama_siswa;
        this.surat = surat;
        this.ayat = ayat;
        this.tanggal = tanggal;
        this.nisn = nisn;
    }

    public int getId_hafalan() {
        return id_hafalan;
    }
    public void setId_hafalan(int id_hafalan) {
        this.id_hafalan = id_hafalan;
    }

    public String getNamasiswa() {
        return nama_siswa;
    }
    public void setNamasiswa(String nama_siswa) {
        this.nama_siswa = nama_siswa;
    }

    public String getSurat() {
        return surat;
    }
    public void setSurat(String surat) {
        this.surat = surat;
    }

    public String getAyat() {
        return ayat;
    }
    public void setAyat(String ayat) {
        this.ayat = ayat;
    }

    public String getTanggal() {
        return tanggal;
    }
    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNisn() {
        return nisn;
    }
    public void setNisn(String nisn) { this.nisn = nisn;}
}
