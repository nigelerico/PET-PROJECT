package com.skripsi.nigel.esvira.Model;

public class AdminInprogress {
    private String textView_barang1;
    private String textView_barang2;
    private int imageView;
    private String textView_tanggal;
    private String textView_jam ,status,nomor,alamat,namauser,idtransaksi;
    private String kode_transaksi;
    private String created_at;
    private String metode_pembayaran;



    public AdminInprogress( String idtransaksi,  String kode_transaksi, String status, String created_at, String metode_pembayaran) {
        this.status = status;
        this.idtransaksi = idtransaksi;
        this.kode_transaksi = kode_transaksi;
        this.created_at = created_at;
        this.metode_pembayaran = metode_pembayaran;
    }



    public String getKode_transaksi() {
        return kode_transaksi;
    }

    public void setKode_transaksi(String kode_transaksi) {
        this.kode_transaksi = kode_transaksi;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getMetode_pembayaran() {
        return metode_pembayaran;
    }

    public void setMetode_pembayaran(String metode_pembayaran) {
        this.metode_pembayaran = metode_pembayaran;
    }

    public String getTextView_barang1() { return textView_barang1; }

    public String getTextView_barang2() {
        return textView_barang2;
    }

    public int getImageView() { return imageView; }

    public String getTextView_jam() { return textView_jam; }

    public String getTextView_tanggal() {
        return textView_tanggal;
    }
    public String getNomor() {
        return nomor;
    }

    public String getStatus() {
        return status;
    }

    public String getAlamat() {
        return alamat;
    }



    public void setTextView_barang1(String textView_barang1) {
        this.textView_barang1 = textView_barang1;
    }

    public void setTextView_barang2(String textView_barang2) {
        this.textView_barang2 = textView_barang2;
    }

    public void setTextView_barang3(String textView_barang3) {
        this.textView_barang2 = textView_barang3;
    }

    public void setTextView_jam(String textView_jam) {
        this.textView_jam = textView_jam;
    }

    public void setTextView_tanggal(String textView_tanggal) {
        this.textView_tanggal = textView_tanggal;
    }
    public void  setStatus(String status){
        this.status=status;
    }
    public void setNomor(String metode){
        this.nomor=nomor;
    }
    public  void  setAlamat(String alamat){
        this.alamat = alamat;
    }
    public void setNamauser(String iduser){
        this.namauser=namauser;
    }
    public void setTransaksi(String transaksi){
        this.idtransaksi=transaksi;
    }
    public String getIdtransaksi() {
        return idtransaksi;
    }
    public String getNamauser() {
        return namauser;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }


}
