package com.example.uaskmmizena;

public class MKelas {

    String id_kelas, nama_kelas, nip_guru,tahun_ajaran;
    public MKelas(String id_kelas, String nama_kelas, String nip_guru, String tahun_ajaran) {
        this.id_kelas = id_kelas;
        this.nama_kelas = nama_kelas;
        this.nip_guru = nip_guru;
        this.tahun_ajaran = tahun_ajaran;
    }

    public MKelas() {

    }

    public String getid_kelas() {
        return id_kelas;
    }

    public void setid_kelas(String id_kelas) {
        this.id_kelas = id_kelas;
    }

    public String getnama_kelas() { return nama_kelas; }

    public void setnama_kelas(String nama_kelas) {
        this.nama_kelas = nama_kelas;
    }

    public String getnip_guru() {
        return nip_guru;
    }

    public void setnip_guru(String nip_guru) {
        this.nip_guru = nip_guru;
    }

    public String gettahun_ajaran() {
        return tahun_ajaran;
    }

    public void setTahun_ajaran(String tahun_ajaran) {
        this.tahun_ajaran = tahun_ajaran;
    }
}
