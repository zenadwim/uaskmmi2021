package com.example.uaskmmizena;

public class MSiswa {
    String nisn, nama_siswa, id_kelas, semester;

    public MSiswa(String nisn, String nama_siswa, String id_kelas, String semester) {
        this.nisn = nisn;
        this.nama_siswa = nama_siswa;
        this.id_kelas = id_kelas;
        this.semester = semester;
    }

    public MSiswa() {

    }

    public String getnisn() {
        return nisn;
    }

    public void setnisn(String nisn) {
        this.nisn = nisn;
    }

    public String getnama_siswa() {
        return nama_siswa;
    }

    public void setnama_siswa(String nama_siswa) {
        this.nama_siswa = nama_siswa;
    }

    public String getid_kelas() {
        return id_kelas;
    }

    public void setid_kelas(String id_kelas) {
        this.id_kelas = id_kelas;
    }

    public String getsemester() {
        return semester;
    }

    public void setsemester(String semester) {
        this.semester = semester;
    }

}
