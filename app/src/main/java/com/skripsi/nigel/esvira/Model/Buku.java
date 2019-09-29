package com.skripsi.nigel.esvira.Model;

public class Buku {
    /**
     * id : 1
     * nama : Wortel
     * jumlah : 3
     * berat : 3
     * harga : 10000
     * foto : sayur/zjPSFLK8lWFJKFiW5o2eW28tPAILziV6TL3Eha4i.jpeg
     * created_at : 2018-06-08 07:03:49
     * updated_at : 2018-06-08 07:03:49
     */

    private int id;
    private String nama;
    private int jumlah;
    private String penulis;
    private int harga;
    private String foto;
    private String kategori;
    private String created_at;
    private String updated_at;
    private int stok;



    public Buku(String foto, int id, String nama, int harga, String penulis, String kategori, int stok){
        this.foto = foto;
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.penulis = penulis;
        this.kategori = kategori;
        this.stok = stok;
    }

    public Buku(int id, int jumlah){
        this.id = id;
        this.jumlah = jumlah;
    }

    public Buku(String foto, int id, String nama, int harga, int jumlah , int stok){
        this.foto = foto;
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.jumlah = jumlah;
        this.stok = stok;
    }

    public Buku(){

    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(int berat) {
        this.penulis = penulis;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
