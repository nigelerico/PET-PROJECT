package com.skripsi.nigel.esvira.Model;

public class Ongkir {

    private int id;
    private String kota;
    private int harga_ongkir;


    public Ongkir(int id, String kota, int harga_ongkir) {
        this.id = id;
        this.kota = kota;
        this.harga_ongkir = harga_ongkir;
    }

    public Ongkir() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public int getHarga_ongkir() {
        return harga_ongkir;
    }

    public void setHarga_ongkir(int harga_ongkir) {
        this.harga_ongkir = harga_ongkir;
    }
}
